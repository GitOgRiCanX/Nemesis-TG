package me.ogricanx.nemesis.tg.commands;

import java.util.List;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.nemesis.tg.util.Testgelände;

public class CreateCommand {

	private Main m;

	public CreateCommand(Main m) {
		this.m = m;
	}

	@Command(name = "testgelände.create", aliases = { "tg.c", "tg.create", "tgc"}, 
			 usage = "/help Nemesis-TG",permission = "nemesis.tg.create",description = "Erstellt eine Testgelände an deiner Position.", inGameOnly = true)
	public void tgCreate(CommandArgs args) {
		
		switch (args.getArgs().length) {
		case 0:
			args.getSender().sendMessage(Main.preferr + "/testgelände create <name>");
			break;
		case 1:
			if (Testgelände.doTestgeländeExists(args.getArgs(0).toLowerCase(), m)) {
				args.getSender().sendMessage(Main.preferr + "Dieses Testgelände Existiert bereits!");
				return;
			}
			
			Testgelände a = new Testgelände(args.getArgs(0).toLowerCase(), args.getPlayer().getLocation());
			List<Testgelände> values = Testgelände.getValues(m);
			values.add(a);
			Testgelände.setValues(values, m);
			m.saveConfig();
			
			args.getSender().sendMessage(Main.pref + "Testgelände " + args.getArgs(0).toLowerCase() + " erfolgreich erstellt.");
			break;
		default:
			args.getSender().sendMessage(Main.preferr + "/testgelände create <name>");
			break;
		}
	}
}
