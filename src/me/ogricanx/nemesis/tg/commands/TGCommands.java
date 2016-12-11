package me.ogricanx.nemesis.tg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.HelpManager;
import me.ogricanx.nemesis.tg.util.Testgel�nde;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;
import mkremins.fanciful.FancyMessage;

public class TGCommands {
	Main m;

	public TGCommands(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgel�nde", aliases = { "tg" }, usage = "/testgel�nde help", description = "Diser befehl existiert nich. ^^", inGameOnly = true)
	public void tg(CommandArgs args) {
		args.getPlayer().sendMessage(Main.preferr+"Dieser Command existiert nicht. Wixxa");
	}
	
	public void crashPlayer(Player p) {
        
    }
	
	@Command(name = "testgel�nde.list", aliases = { "tg.list","tglist","tgl" }, 
			usage = "/testgel�nde help",permission = "nemesis.tg.list", description = "Listet alle Existierenden Testgel�nde.", inGameOnly = true)
	public void tgList(CommandArgs args) {
		args.getPlayer().sendMessage(Main.pref+"Liste aller Testgel�nde:");
		int i = 1;
		for (Testgel�nde tg : Testgel�nde.getValues(m)) {
			new FancyMessage(i+": ")
							.color(ChatColor.GOLD)
						.then(tg.getName())
							.color(ChatColor.GOLD)
							.tooltip("�6Klicke hier, um zu dem testgel�nde �c" + tg.getName() + "�6 teleportiert zu werden.")
								.color(ChatColor.GRAY)
							.command("/tg tp "+ tg.getName())
					.send(args.getPlayer());
							
			i += 1;
		}
		
	}

	@Command(name = "testgel�nde.reset", aliases = { "tg.reset", "tgreset","tg.reset", "tgclear", "tg.clear", "testgel�nde.clear" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.clear",description = "Setzt das testgel�nde auf dem man sich befindet zur�ck, r�ckg�ngig machbar mit //undo", inGameOnly = true)
	public void tgReset(CommandArgs args) {
		if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
			args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
			return;
		}
		if (Testgel�nde.doTestgel�ndeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
			Testgel�nde tg = Testgel�nde.getTestgel�nde(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
			boolean b =tg.clearTG(args.getPlayer());
			if (b) {
				args.getSender().sendMessage(Main.pref + "Testgel�nde erfolgreich zur�ckgesetzt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Es ist ein Fehler aufgetreten.");
			}
		}else {
			args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
			return;
		}
	}
	
	@Command(name = "testgel�nde.select", aliases = {"tg.select", "tg.s", "tgselect"}, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.select",description = "Setzt deine WorldEdit selection auf das gesamte Testgel�nde.", inGameOnly = true)
	public void tgSelect(CommandArgs args) {
		
		
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
				return;
			}
			if (Testgel�nde.doTestgel�ndeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				Testgel�nde tg = Testgel�nde.getTestgel�nde(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
				

				Location min = new Location(tg.getMaxMap().getWorld(), tg.getMaxMap().getBlockX(), tg.getMaxMap().getBlockY(), tg.getMaxMap().getBlockZ()); 
				Location max = new Location(tg.getMaxMap().getWorld(), tg.getMinMap().getBlockX(), tg.getMinMap().getBlockY(), tg.getMinMap().getBlockZ()); 
				
				((WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit")).setSelection(args.getPlayer(), new CuboidSelection(min.getWorld(), min, max));
				args.getSender().sendMessage(Main.pref + "Testgel�nde erfogreich ausgew�hlt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
				return;
			}
			break;
			
		case 1:
			if (Testgel�nde.doTestgel�ndeExists(args.getArgs(0), m)) {
				Testgel�nde tg = Testgel�nde.getTestgel�nde(args.getArgs(0),m);
				((WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit")).setSelection(args.getPlayer(), new CuboidSelection(tg.getMaxMap().getWorld(), tg.getMinMap(), tg.getMaxMap()));
				args.getSender().sendMessage(Main.pref + "Testgel�nde erfogreich ausgew�hlt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
				return;
			}
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde select [tg]");
			break;
		}
	}
	
	@Command(name = "help.testgel�nde", aliases = {"help.tg","tg.?", "tghelp", "helptg"}, usage = "/help Nemesis-TG", permission = "nemesis.tg.help",description = "Help Command.", inGameOnly = true)
	public void helpTG(CommandArgs args) {
		HelpManager.sendHelpMessage(args.getPlayer());
	}
	
	@Command(name = "testgel�nde.help", aliases = {"tg.help","tg.?", "tg.h", }, usage = "/help Nemesis-TG", permission = "nemesis.tg.help",description = "Help Command.", inGameOnly = true)
	public void tgHelp(CommandArgs args) {
		HelpManager.sendHelpMessage(args.getPlayer());
	}
}
