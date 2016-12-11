package me.ogricanx.nemesis.tg.commands;

import com.sk89q.worldedit.MaxChangedBlocksException;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgelände;
import me.ogricanx.nemesis.tg.util.WordGuardExpansion;
import me.ogricanx.worldedit.schematicAPI.LoadedSchematic;
import me.ogricanx.worldedit.schematicAPI.LoadedSchematic.Facing;
import me.ogricanx.worldedit.schematicAPI.Schematic;
import me.ogricanx.worldedit.schematicAPI.SchematicNotExistsExeption;

public class BlockCommands {

	Main m;

	public BlockCommands(Main m) {
		this.m = m;
	}
	
//	/tg block
	@Command(name = "testgelände.block", aliases = { "tg.block", "tgblock", "tg.b"},  usage = "/help Nemesis-TG",permission = "nemesis.tg.block",description = "Erstellt einen Testblock.", inGameOnly = true)
	public void blockCreate(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				Testgelände tg = Testgelände.getTestgelände(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
				if (args.getPlayer().getLocation().getBlockZ() <= tg.getTelLoc1().getBlockZ()) { //Spieler is in norden
					try {
						LoadedSchematic schem = new LoadedSchematic("tg/block");
						schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption' ("+e.getSchematicName()+")");
					}
				}else { //Spieler is Süden
					try {
						LoadedSchematic schem = new LoadedSchematic("tg/block");
						schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption' ("+e.getSchematicName()+")");
						}
				}
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			break;
		case 1:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				Testgelände tg = Testgelände.getTestgelände(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
				
				switch (args.getArgs(0)) {
				case "norden":
					try {
						Schematic schem = new Schematic("tg/block");
						schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				case "n":
					try {
						Schematic schem = new Schematic("tg/block");
						schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				case "süden":
					try {
						Schematic schem = new Schematic("tg/block");
						schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				case "s":
					try {
						Schematic schem = new Schematic("tg/block");
						schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				default:
					args.getPlayer().sendMessage(Main.preferr+"/tg block [mini] [norden/süden]");
					return;
				}
				
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			break;

		default:
			break;
		}
	}
	
//	/tg block mini
	@Command(name = "testgelände.block.mini", aliases = { "tg.block.mini", "tgblock.mini", "tg.b.mini"},  usage = "/help Nemesis-TG",permission = "nemesis.tg.block",description = "Erstellt einen Mini-Testblock.", inGameOnly = true)
	public void blockCreateMini(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				Testgelände tg = Testgelände.getTestgelände(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
				if (args.getPlayer().getLocation().getBlockZ() <= tg.getTelLoc1().getBlockZ()) { //Spieler is in norden
					try {
						LoadedSchematic schem = new LoadedSchematic("tg/block_mini");
						schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Mini-Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption' ("+e.getSchematicName()+")");
					}
				}else { //Spieler is Süden
					try {
						LoadedSchematic schem = new LoadedSchematic("tg/block_mini");
						schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Mini-Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption' ("+e.getSchematicName()+")");
						}
				}
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			break;
		case 1:
			if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
				Testgelände tg = Testgelände.getTestgelände(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
				
				switch (args.getArgs(0)) {
				case "norden":
					try {
						Schematic schem = new Schematic("tg/block_mini");
						schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Mini-Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				case "n":
					try {
						Schematic schem = new Schematic("tg/block_mini");
						schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				case "süden":
					try {
						Schematic schem = new Schematic("tg/block_mini");
						schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				case "s":
					try {
						Schematic schem = new Schematic("tg/block_mini");
						schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, args.getPlayer());
						args.getPlayer().sendMessage(Main.pref+"Testblock erfoglreich erstellt.");
					} catch (MaxChangedBlocksException e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
					} catch (SchematicNotExistsExeption e) {
						args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
					}
					break;
				default:
					args.getPlayer().sendMessage(Main.preferr+"/tg block [mini] [norden/süden]");
					return;
				}
				
			}else {
				args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
				return;
			}
			break;

		default:
			break;
		}
	}
}
