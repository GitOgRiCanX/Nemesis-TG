package me.ogricanx.nemesis.tg.commands;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgel�nde;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;
import me.ogricanx.worldedit.schematicAPI.Schematic;
import me.ogricanx.worldedit.schematicAPI.SchematicNotExistsExeption;

public class BackupCommand {
	
	Main m;

	public BackupCommand(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgel�nde.backup", aliases = { "tg.backup", "tgbackup"},
			 usage = "/help Nemesis-TG",permission = "nemesis.tg.backup",description = "Erstellt eine Backup schematic." , inGameOnly = true)
	public void tgBackup(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
				return;
			}
			if (Testgel�nde.doTestgel�ndeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				Testgel�nde tg = Testgel�nde.getTestgel�nde(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
				Schematic schem = new Schematic("backup_"+args.getPlayer().getUniqueId().toString());
				@SuppressWarnings("unused")
				boolean b = schem.createNew(tg.getMinMap(), tg.getMaxMap());
				args.getSender().sendMessage(Main.pref + "Backup erforglreich erstellt.");
			}else {
				args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
				return;
			}
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde backup");
			break;
		}
	}
	
	@Command(name = "testgel�nde.backup.load", aliases = { "tg.backup.load", "tgbackup.load" },
			 usage = "/help Nemesis-TG",permission = "nemesis.tg.backup",description = "L�dt die backup Schematic und pastet sie auf deinem tg." , inGameOnly = true)
	public void tgBackupLoad(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			Schematic schem = new Schematic("backup_"+args.getPlayer().getUniqueId().toString());
			if (schem.exists()) {

				if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
					args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
					return;
				}
				if (Testgel�nde.doTestgel�ndeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
					Testgel�nde tg = Testgel�nde.getTestgel�nde(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
					try {
						schem.get().paste(tg.getMinMap(), false, args.getPlayer());
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler auftereten. 'SchematicNotExistsExeption'");
					}
					args.getSender().sendMessage(Main.pref + "Backup erforglreich geladen.");
					return;
				}else {
					args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
					return;
				}
			}else {
				args.getSender().sendMessage(Main.preferr + "Du hast bis jetzt noch nie einen Backup erstellt.");
				return;
			}
		default:
			break;
		}
	}
}
