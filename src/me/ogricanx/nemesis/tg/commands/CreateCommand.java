package me.ogricanx.nemesis.tg.commands;

import java.util.List;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgel�nde;

public class CreateCommand {

	private Main m;

	public CreateCommand(Main m) {
		this.m = m;
	}

	@Command(name = "testgel�nde.create", aliases = { "tg.c", "tg.create", "tgc"}, 
			 usage = "/help Nemesis-TG",permission = "nemesis.tg.create",description = "Erstellt eine Testgel�nde an deiner Position.", inGameOnly = true)
	public void tgCreate(CommandArgs args) {
		
		switch (args.getArgs().length) {
		case 0:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde create <name>");
			break;
		case 1:
			if (Testgel�nde.doTestgel�ndeExists(args.getArgs(0).toLowerCase(), m)) {
				args.getSender().sendMessage(Main.preferr + "Dieses Testgel�nde Existiert bereits!");
				return;
			}
			
			Testgel�nde a = new Testgel�nde(args.getArgs(0).toLowerCase(), args.getPlayer().getLocation());
			List<Testgel�nde> values = Testgel�nde.getValues(m);
			values.add(a);
			Testgel�nde.setValues(values, m);
			m.saveConfig();
			
			args.getSender().sendMessage(Main.pref + "Testgel�nde " + args.getArgs(0).toLowerCase() + " erfolgreich erstellt.");
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgel�nde create <name>");
			break;
		}
	}
}
