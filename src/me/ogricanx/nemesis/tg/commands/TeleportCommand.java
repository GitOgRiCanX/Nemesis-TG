package me.ogricanx.nemesis.tg.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgelände;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;

public class TeleportCommand {
	
	Main m;

	public TeleportCommand(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgelände.teleport", aliases = { "testgelände.tp", "tg.tp", "tgtp" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.tp",description = "Teleporiert dich zu dem nächsten freien tg, falls keins angegeben ist.", inGameOnly = true)
	public void tgTP(CommandArgs args) {
		
		switch (args.getArgs().length) {
		case 0:
			TG:
			for (Testgelände tg : Testgelände.getValues(m)) {
				for (Player p :  Bukkit.getOnlinePlayers()) {
					if (WordGuardExpansion.getRegionAtLocation(p.getLocation()) == null) {
						continue;
					}
					
					if (WordGuardExpansion.getRegionAtLocation(p.getLocation()).getId().equalsIgnoreCase(tg.getName())) {
						continue TG;
					}
				}
				args.getPlayer().teleport(tg.getTelLoc1());
				args.getPlayer().sendMessage(Main.pref+"Du wurdest zu dem Testgelände " + tg.getName() + " Teleportiert.");
				return;
			}
			args.getSender().sendMessage(Main.preferr + "Es gibt keine Freien Testgelände.");
			break;
		case 1:
			if (Testgelände.doTestgeländeExists(args.getArgs(0), m)) {
				Testgelände tg = Testgelände.getTestgelände(args.getArgs(0), m);
				args.getPlayer().teleport(tg.getTelLoc1());
				args.getPlayer().sendMessage(Main.pref+"Du wurdest zu dem Testgelände " + tg.getName() + " Teleportiert.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Dieses testgelände existiert nicht.");
				return;
			}
				
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgelände tp [tg]");
			break;
		}
	}
}
