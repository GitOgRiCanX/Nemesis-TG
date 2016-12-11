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
import me.ogricanx.nemesis.tg.util.Testgelände;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;
import mkremins.fanciful.FancyMessage;

public class TGCommands {
	Main m;

	public TGCommands(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgelände", aliases = { "tg" }, usage = "/testgelände help", description = "Diser befehl existiert nich. ^^", inGameOnly = true)
	public void tg(CommandArgs args) {
		args.getPlayer().sendMessage(Main.preferr+"Dieser Command existiert nicht. Wixxa");
	}
	
	public void crashPlayer(Player p) {
        
    }
	
	@Command(name = "testgelände.list", aliases = { "tg.list","tglist","tgl" }, 
			usage = "/testgelände help",permission = "nemesis.tg.list", description = "Listet alle Existierenden Testgelände.", inGameOnly = true)
	public void tgList(CommandArgs args) {
		args.getPlayer().sendMessage(Main.pref+"Liste aller Testgelände:");
		int i = 1;
		for (Testgelände tg : Testgelände.getValues(m)) {
			new FancyMessage(i+": ")
							.color(ChatColor.GOLD)
						.then(tg.getName())
							.color(ChatColor.GOLD)
							.tooltip("§6Klicke hier, um zu dem testgelände §c" + tg.getName() + "§6 teleportiert zu werden.")
								.color(ChatColor.GRAY)
							.command("/tg tp "+ tg.getName())
					.send(args.getPlayer());
							
			i += 1;
		}
		
	}

	@Command(name = "testgelände.reset", aliases = { "tg.reset", "tgreset","tg.reset", "tgclear", "tg.clear", "testgelände.clear" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.clear",description = "Setzt das testgelände auf dem man sich befindet zurück, rückgängig machbar mit //undo", inGameOnly = true)
	public void tgReset(CommandArgs args) {
		if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
			args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
			return;
		}
		if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
			Testgelände tg = Testgelände.getTestgelände(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
			boolean b =tg.clearTG(args.getPlayer());
			if (b) {
				args.getSender().sendMessage(Main.pref + "Testgelände erfolgreich zurückgesetzt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Es ist ein Fehler aufgetreten.");
			}
		}else {
			args.getSender().sendMessage(Main.preferr + "Dieses testgelände existiert nicht.");
			return;
		}
	}
	
	@Command(name = "testgelände.select", aliases = {"tg.select", "tg.s", "tgselect"}, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.select",description = "Setzt deine WorldEdit selection auf das gesamte Testgelände.", inGameOnly = true)
	public void tgSelect(CommandArgs args) {
		
		
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				Testgelände tg = Testgelände.getTestgelände(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
				

				Location min = new Location(tg.getMaxMap().getWorld(), tg.getMaxMap().getBlockX(), tg.getMaxMap().getBlockY(), tg.getMaxMap().getBlockZ()); 
				Location max = new Location(tg.getMaxMap().getWorld(), tg.getMinMap().getBlockX(), tg.getMinMap().getBlockY(), tg.getMinMap().getBlockZ()); 
				
				((WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit")).setSelection(args.getPlayer(), new CuboidSelection(min.getWorld(), min, max));
				args.getSender().sendMessage(Main.pref + "Testgelände erfogreich ausgewählt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			break;
			
		case 1:
			if (Testgelände.doTestgeländeExists(args.getArgs(0), m)) {
				Testgelände tg = Testgelände.getTestgelände(args.getArgs(0),m);
				((WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit")).setSelection(args.getPlayer(), new CuboidSelection(tg.getMaxMap().getWorld(), tg.getMinMap(), tg.getMaxMap()));
				args.getSender().sendMessage(Main.pref + "Testgelände erfogreich ausgewählt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Dieses testgelände existiert nicht.");
				return;
			}
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgelände select [tg]");
			break;
		}
	}
	
	@Command(name = "help.testgelände", aliases = {"help.tg","tg.?", "tghelp", "helptg"}, usage = "/help Nemesis-TG", permission = "nemesis.tg.help",description = "Help Command.", inGameOnly = true)
	public void helpTG(CommandArgs args) {
		HelpManager.sendHelpMessage(args.getPlayer());
	}
	
	@Command(name = "testgelände.help", aliases = {"tg.help","tg.?", "tg.h", }, usage = "/help Nemesis-TG", permission = "nemesis.tg.help",description = "Help Command.", inGameOnly = true)
	public void tgHelp(CommandArgs args) {
		HelpManager.sendHelpMessage(args.getPlayer());
	}
}
