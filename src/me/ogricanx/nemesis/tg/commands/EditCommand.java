package me.ogricanx.nemesis.tg.commands;

import java.util.List;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgel�nde;

public class EditCommand {
	
	Main m;

	public EditCommand(Main m) {
		this.m = m;
	}
	
	@Command(name = "testgel�nde.set", aliases = { "tg.set", "tgset", "tg.edit", "tgedit" }, 
			usage = "/help Nemesis-TG",permission = "nemesis.tg.edit",description = "Editiert das angegebene Testgel�nde.", inGameOnly = true)
	public void tgPosition(CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde set <tg> <min/max/tel>");
			break;
		case 1:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde set <tg> <min/max/tel>");
			break;
		case 2:
			if (!Testgel�nde.doTestgel�ndeExists(args.getArgs(0), m)) {
				args.getSender().sendMessage(Main.preferr + "Dieses Testgel�nde Existiert nicht!");
				return;
			}
			
			List<Testgel�nde> list = Testgel�nde.getValues(m);
			switch (args.getArgs(1).toLowerCase()) {
			case "min":
				for (int i = 0; i< list.size(); i++)  {
					if (list.get(i).getName().equalsIgnoreCase(args.getArgs(0))) {
						Testgel�nde a = list.get(i);
						a.setMinMap(args.getPlayer().getLocation());
						list.set(i, a);
						break;
					}
				}
				Testgel�nde.setValues(list,m);
				args.getPlayer().sendMessage(Main.pref+"Minimale Position des Testgel�ndes gesetzt.");
				break;
			case "max":
				for (int i = 0; i< list.size(); i++)  {
					if (list.get(i).getName().equalsIgnoreCase(args.getArgs(0))) {
						Testgel�nde a = list.get(i);
						a.setMaxMap(args.getPlayer().getLocation());
						list.set(i, a);
						break;
					}
				}
				Testgel�nde.setValues(list,m);
				args.getPlayer().sendMessage(Main.pref+"Maximale Position des Testgel�ndes gesetzt.");
				break;
			case "tel":
				for (int i = 0; i< list.size(); i++)  {
					if (list.get(i).getName().equalsIgnoreCase(args.getArgs(0))) {
						Testgel�nde a = list.get(i);
						a.setTelLoc1(args.getPlayer().getLocation());
						list.set(i, a);
						break;
					}
				}
				Testgel�nde.setValues(list,m);
				args.getPlayer().sendMessage(Main.pref+"Spawnpunkt des Testgel�ndes gesetzt.");
				break;
			default:
				args.getSender().sendMessage(Main.preferr + "/testgel�nde set <tg> <min/max/tel>");
				break;
			}
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde set <tg> <min/max/tel>");
			break;
		}
	}
	
}
