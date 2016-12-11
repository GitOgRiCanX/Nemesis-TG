package me.ogricanx.nemesis.tg.util;

import org.bukkit.entity.Player;

import me.ogricanx.nemesis.tg.main.Main;

public class HelpManager {
	
	public static void sendHelpMessage(Player p) {
		p.sendMessage(Main.pref+"Alle Befehle von Nemesis-TG:");
		Help backup = new Help("/testgel�nde backup", "Erstellt eine backup schematic des Aktuellen Testgel�ndes.",  "/tg backup", "/tgbackup" );
		Help load = new Help("/testgel�nde backup load", "L�dt die backup schematic und pastet sie auf deinem Aktuellen TG.",  "/tg backup load", "/tgbackup load" );
		Help block = new Help("/testgel�nde block", "Erstellt einen testblock auf der gegen�berligenden Seite deines Testgel�ndes.",  "/tg block", "/tgblock" );
		Help blockdir = new Help("/testgel�nde block norden/s�den/n/s", "Erstellt einen testblock auf der angegeben Seite deines Testgel�ndes.",  "/tg block norden/s�den/n/s", "/tgblock norden/s�den/n/s" );
		Help create = new Help("/testgel�nde create", "Erstellt ein Testgel�nde an deiner Position.", "/tg c", "/tg create", "/tgc");
		Help tpHelp = new Help("/testgel�nde teleport", "Teleportiert dich zum n�chsten Freien Testgel�nde.", "/testgel�nde tp", "/tg tp", "/tgtp");
		Help tpHelptg = new Help("/testgel�nde teleport <tg>", "Teleportiert dich zu dem Angegeben Testgel�nde.", "/testgel�nde tp <tg>", "/tg tp <tg>", "/tgtp <tg>");
		Help reset = new Help("/testgel�nde clear", "Clear dein Aktuelles TG.", "/tg reset", "/tgreset","/tg reset", "/tgclear", "/tg clear", "/testgel�nde clear");
		Help select = new Help("/testgel�nde select [tg]", "Setzt deine WorldEdit selection auf das gesamte Testgel�nde.", "/tg select", "/tg s", "/tgselect");
		Help list = new Help("/testgel�nde list", "Gibt eine Liste aller Testgel�nde aus.", "/tg list","/tglist","/tgl");
		Help tnt = new Help("/testgel�nde tnt", "�ndert den Status des TNTs.",  "tg.tnt", "tgtnt", "tg.tnt", "tnt" );
		Help tntdeny = new Help("/testgel�nde tnt deny", "Stellt TNT auf deinem Testgel�nde aus.",  "tg.tnt.deny", "tgtnt.deny", "tg.tnt.deny", "tnt.deny","tnt.d" );
		Help tntallow = new Help("/testgel�nde tnt allow", "Stellt TNT auf deinem Testgel�nde an.",  "tg.tnt.allow", "tgtnt.allow", "tg.tnt.allow", "tnt.allow","tnt.a" );
		
		p.sendMessage(backup.toString());
		p.sendMessage(load.toString());
		p.sendMessage(block.toString());
		p.sendMessage(blockdir.toString());
		p.sendMessage(tpHelp.toString());
		p.sendMessage(tpHelptg.toString());
		p.sendMessage(create.toString());
		p.sendMessage(reset.toString());
		p.sendMessage(select.toString());
		p.sendMessage(list.toString());
		p.sendMessage(tnt.toString());
		p.sendMessage(tntdeny.toString());
		p.sendMessage(tntallow.toString());
	}
}