package me.ogricanx.nemesis.tg.commands;

import com.sk89q.worldedit.MaxChangedBlocksException;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgel�nde;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;
import me.ogricanx.worldedit.schematicAPI.LoadedSchematic.Facing;
import me.ogricanx.worldedit.schematicAPI.Schematic;
import me.ogricanx.worldedit.schematicAPI.SchematicNotExistsExeption;

public class PasteCommands {
	
	Main m;

	public PasteCommands(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgel�nde.paste", aliases = { "tg.paste", "tgpaste", "tg.paste", "tg.p" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.paste",description = "Pastet die angegeben schematic.", inGameOnly = true)
	public void tgPaste(CommandArgs args) {
		switch (args.getArgs().length) {
		case 1:
			Schematic schem = new Schematic(args.getArgs(0));
			if (schem.exists()) {
				if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
					args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
					return;
				}
				if (Testgel�nde.doTestgel�ndeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
					Testgel�nde tg = Testgel�nde.getTestgel�nde(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
					if (args.getPlayer().getLocation().getBlockZ() <= tg.getTelLoc1().getBlockZ()) { //Norden
						try {
							schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, args.getPlayer());
							args.getPlayer().sendMessage(Main.pref+"Schematic erfoglreich gepastet.");
						} catch (MaxChangedBlocksException e) {
							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
						} catch (SchematicNotExistsExeption e) {
							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten.");
						}
						return;
					}else { //S�den
						try {
							schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
							args.getPlayer().sendMessage(Main.pref+"Schematic erfoglreich gepastet. ");
						} catch (MaxChangedBlocksException e) {
							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
						} catch (SchematicNotExistsExeption e) {
							args.getPlayer().sendMessage(Main.pref+"Diesen Bug bitte umgehen Melden.");
						}
						return;
					}
				}else {
					args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
					return;
				}
			}else {
				args.getPlayer().sendMessage(Main.preferr+"Diese schematic existiert nicht.");
				return;
			}
		default:
			args.getPlayer().sendMessage(Main.preferr+"/testgel�nde paste <schematic>");
			return;
		}
	}

	@Command(name = "testgel�nde.paste.norden", aliases = { "tg.paste.norden", "tgpaste.norden", "tg.paste.norden", "tg.paste.n","tg.p.norden" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.paste",description = "Pastet die angegeben schematic.", inGameOnly = true)
	public void tgPasteSeiteNorden(CommandArgs args) {
		switch (args.getArgs().length) {
		case 1:
			Schematic schem = new Schematic(args.getArgs(0));
			if (schem.exists()) {
				if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
					args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
					return;
				}
				if (Testgel�nde.doTestgel�ndeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
					Testgel�nde tg = Testgel�nde.getTestgel�nde(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
					try {
						schem.get().paste(tg.getPasteS1(),false,Facing.SOUTH,args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Schematic erfoglreich gepastet.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten.");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.pref+"Diesen Bug bitte umgehen Melden.");
					}
					return;
					
				}else {
					args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
					return;
				}
			}else {
				args.getPlayer().sendMessage(Main.preferr+"Diese schematic existiert nicht.");
				return;
			}
		default:
			args.getPlayer().sendMessage(Main.preferr+"/testgel�nde paste <schematic>");
			return;
		}
	}
	
	@Command(name = "testgel�nde.paste.s�den", aliases = { "tg.paste.s�den", "tgpaste.s�den", "tg.paste.s�den", "tg.paste.s","tg.p.s�den" }, 
			 usage = "/help Nemesis-TG", permission = "nemesis.tg.paste",description = "Pastet die angegeben schematic.", inGameOnly = true)
	public void tgPasteSeiteS�den(CommandArgs args) {
		switch (args.getArgs().length) {
		case 1:
			Schematic schem = new Schematic(args.getArgs(0));
			if (schem.exists()) {
				if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
					args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgel�nde.");
					return;
				}
				if (Testgel�nde.doTestgel�ndeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
					Testgel�nde tg = Testgel�nde.getTestgel�nde(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
					try {
						schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Schematic erfoglreich gepastet.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten.");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.pref+"Diesen Bug bitte umgehen Melden.");
					}
					return;
				}else {
					args.getSender().sendMessage(Main.preferr + "Dieses testgel�nde existiert nicht.");
					return;
				}
			}else {
				args.getPlayer().sendMessage(Main.preferr+"Diese schematic existiert nicht.");
				return;
			}
		default:
			args.getPlayer().sendMessage(Main.preferr+"/testgel�nde paste <schematic>");
			return;
		}
	}
}
