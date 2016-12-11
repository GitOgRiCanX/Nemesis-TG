package me.ogricanx.nemesis.tg.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgel�nde;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;

public class TeleportCommand {
	
	Main m;

	public TeleportCommand(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgel�nde.teleport", aliases = { "testgel�nde.tp", "tg.tp", "tgtp" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.tp",description = "Teleporiert dich zu dem n�chsten freien tg, falls keins angegeben ist.", inGameOnly = true)
	public void tgTP(CommandArgs args) {
		
		switch (args.getArgs().length) {
		case 0:
			TG:
			for (Testgel�nde tg : Testgel�nde.getValues(m)) {
				for (Player p :  Bukkit.getOnlinePlayers()) {
					if (WordGuardExpansion.getRegionAtLocation(p.getLocation()) == null) {
						continue;
					}
					
					if (WordGuardExpansion.getRegionAtLocation(p.getLocation()).getId().equalsIgnoreCase(tg.getName())) {
						continue TG;
					}
				}
				args.getPlayer().teleport(tg.getTelLoc1());
				args.getPlayer().sendMessage(Main.pref+"Du wurdest zu dem Testgel�nde " + tg.getName() + " Teleportiert.");
				return;
			}
			args.getSender().sendMessage(Main.preferr + "Es gibt keine Freien Testgel�nde.");
			break;
		case 1:
			if (Testgel�nde.doTestgel�ndeExists(args.getArgs(0), m)) {
				Testgel�nde tg = Testgel�nde.getTestgel�nde(args.getArgs(0), m);
				args.getPlayer().teleport(tg.getTelLoc1());
				args.getPlayer().sendMessage(Main.pref+"Du wurdest zu dem Testgel�nde " + tg.getName() + " Teleportiert.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
				return;
			}
				
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde tp [tg]");
			break;
		}
	}
}
