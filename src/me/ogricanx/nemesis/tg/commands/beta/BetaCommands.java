//package me.ogricanx.nemesis.tg.commands.beta;
//
//import com.sk89q.worldedit.MaxChangedBlocksException;
//
//import de.pro_crafting.commandframework.Command;
//import de.pro_crafting.commandframework.CommandArgs;
//import me.ogricanx.nemesis.tg.main.Main;
//import me.ogricanx.nemesis.tg.util.Testgelände;
//import me.ogricanx.nemesis.tg.util.WordGuardExpansion;
//import me.ogricanx.worldedit.schematicAPI.LoadedSchematic.Facing;
//import me.ogricanx.worldedit.schematicAPI.LocalSessionExtended;
//import me.ogricanx.worldedit.schematicAPI.LocalSessionManager;
//import me.ogricanx.worldedit.schematicAPI.Schematic;
//import me.ogricanx.worldedit.schematicAPI.SchematicNotExistsExeption;
//import me.ogricanx.worldedit.schematicAPI.SeasonNotExistsExeption;
//
//public class BetaCommands {
//	Main m;
//	
//	public BetaCommands(Main m) {
//		this.m = m;
//	}
//	
//	@Command(name = "beta.paste",  usage = "/help Nemesis-TG",permission = "nemesis.beta",description = "Erstellt einen Testblock.", inGameOnly = true)
//	public void command1(CommandArgs args) {
//		switch (args.getArgs().length) {
//		case 1:
//			Schematic schem = new Schematic(args.getArgs(0));
//			if (schem.exists()) {
//				if (WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()) == null) {
//					args.getSender().sendMessage(Main.preferr + "Du bist auf keinem Testgelände.");
//					return;
//				}
//				if (Testgelände.doTestgeländeExists(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m)) {
//					Testgelände tg = Testgelände.getTestgelände(WordGuardExpansion.getRegionAtLocation(args.getPlayer().getLocation()).getId(), m);
//					if (args.getPlayer().getLocation().getBlockZ() <= tg.getTelLoc1().getBlockZ()) { //Norden
//						try {
//							schem.get().paste(tg.getPasteS1(), false, Facing.SOUTH, "test");
//							args.getPlayer().sendMessage(Main.pref+"Schematic erfoglreich gepastet.");
//						} catch (MaxChangedBlocksException e) {
//							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
//						} catch (SchematicNotExistsExeption e) {
//							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten. 'SchematicNotExistsExeption'");
//						} catch (SeasonNotExistsExeption e) {
//							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten. 'SeasonNotExistsExeption'");
//							e.printStackTrace();
//						}
//						return;
//					}else { //Süden
//						try {
//							schem.get().paste(tg.getPasteS2(), false, Facing.NORTH, "test");
//							args.getPlayer().sendMessage(Main.pref+"Schematic erfoglreich gepastet. ");
//						} catch (MaxChangedBlocksException e) {
//							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten. 'MaxChangedBlocksException'");
//						} catch (SchematicNotExistsExeption e) {
//							args.getPlayer().sendMessage(Main.pref+"Diesen Bug bitte umgehen Melden.");
//						} catch (SeasonNotExistsExeption e) {
//							args.getPlayer().sendMessage(Main.pref+"Es ist ein Fehler aufgetreten. 'SeasonNotExistsExeption'");
//							e.printStackTrace();
//						}
//						return;
//					}
//				}else {
//					args.getSender().sendMessage(Main.preferr + "Dieses testgelände existiert nicht.");
//					return;
//				}
//			}else {
//				args.getPlayer().sendMessage(Main.preferr+"Diese schematic existiert nicht.");
//				return;
//			}
//		default:
//			args.getPlayer().sendMessage(Main.preferr+"/testgelände paste <schematic>");
//			return;
//		}
//	}
//	
//	@Command(name = "beta.undo",  usage = "/help Nemesis-TG",permission = "nemesis.beta",description = "Erstellt einen Testblock.", inGameOnly = true)
//	public void command2(CommandArgs args) {
//		try {
//			LocalSessionExtended localSeassion = LocalSessionManager.getLocalSessionExtended("test");
//			localSeassion.undo(null);
//			LocalSessionManager.
//			args.getPlayer().sendMessage(Main.pref+"Testgelände undo command ausgeführt.");
//		} catch (SeasonNotExistsExeption e) {
//			args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SeasonNotExistsExeption'");
//		}
//		
//	}
//	
//
//	@Command(name = "beta.redo",  usage = "/help Nemesis-TG",permission = "nemesis.beta",description = "Erstellt einen Testblock.", inGameOnly = true)
//	public void command3(CommandArgs args) {
//		try {
//			LocalSessionExtended localSeassion = LocalSessionManager.getLocalSessionExtended("test");
//			localSeassion.redo(null);
//			args.getPlayer().sendMessage(Main.pref+"Testgelände redo command ausgeführt.");
//		} catch (SeasonNotExistsExeption e) {
//			args.getPlayer().sendMessage(Main.preferr+"Es ist ein Fehler aufgetreten. 'SeasonNotExistsExeption'");
//		}
//	}
//	
//}
