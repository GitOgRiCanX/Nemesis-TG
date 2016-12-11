package me.ogricanx.nemesis.tg.commands;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgelände;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;

public class TNTCommands {
	
	Main m;

	public TNTCommands(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgelände.tnt", aliases = { "tg.tnt", "tgtnt", "tg.tnt", "tnt" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.tnt",description = "Ändert den Status des TNTs.", inGameOnly = true)
	public void tgTNT(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getFlag(DefaultFlag.TNT) == StateFlag.State.ALLOW) {
					WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).setFlag(DefaultFlag.TNT, StateFlag.State.DENY);
					args.getSender().sendMessage(Main.pref + "TNT erflogreich auf 'Deny' gestellt.");
					return;
				}else {
					WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).setFlag(DefaultFlag.TNT, StateFlag.State.ALLOW);
					args.getSender().sendMessage(Main.pref + "TNT erflogreich auf 'Allow' gestellt.");
				}
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			break;
		case 1:
		default:
			break;
		}
	}
	
	@Command(name = "testgelände.tnt.allow", aliases = { "tg.tnt.allow", "tgtnt.allow", "tnt.allow", "tnt.a" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.tnt",description = "Setzte tnt auf allow", inGameOnly = true)
	public void tgTNTAllow(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).setFlag(DefaultFlag.TNT, StateFlag.State.ALLOW);
				args.getSender().sendMessage(Main.pref + "TNT erflogreich auf 'Allow' gestellt.");
				return;
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
		default:
			args.getPlayer().sendMessage(Main.preferr+"/tnt [allow/deny]");
			return;
		}
	}
	
	@Command(name = "testgelände.tnt.deny", aliases = { "tg.tnt.deny", "tgtnt.deny","tnt.deny", "tnt.d" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.tnt",description = "Setzte tnt auf deny", inGameOnly = true)
	public void tgTNTDeny(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).setFlag(DefaultFlag.TNT, StateFlag.State.DENY);
				args.getSender().sendMessage(Main.pref + "TNT erflogreich auf 'Deny' gestellt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			
			break;
		default:
			args.getPlayer().sendMessage(Main.preferr+"/tnt [allow/deny]");
			return;
		}
	}
	
}
