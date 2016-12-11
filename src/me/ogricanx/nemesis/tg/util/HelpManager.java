package me.ogricanx.nemesis.tg.util;

import org.bukkit.entity.Player;

import me.ogricanx.nemesis.tg.main.Main;

public class HelpManager {
	
	public static void sendHelpMessage(Player p) {
		p.sendMessage(Main.pref+"Alle Befehle von Nemesis-TG:");
		Help backup = new Help("/testgelände backup", "Erstellt eine backup schematic des Aktuellen Testgeländes.",  "/tg backup", "/tgbackup" );
		Help load = new Help("/testgelände backup load", "Lädt die backup schematic und pastet sie auf deinem Aktuellen TG.",  "/tg backup load", "/tgbackup load" );
		Help block = new Help("/testgelände block", "Erstellt einen testblock auf der gegenüberligenden Seite deines Testgeländes.",  "/tg block", "/tgblock" );
		Help blockdir = new Help("/testgelände block norden/süden/n/s", "Erstellt einen testblock auf der angegeben Seite deines Testgeländes.",  "/tg block norden/süden/n/s", "/tgblock norden/süden/n/s" );
		Help create = new Help("/testgelände create", "Erstellt ein Testgelände an deiner Position.", "/tg c", "/tg create", "/tgc");
		Help tpHelp = new Help("/testgelände teleport", "Teleportiert dich zum nächsten Freien Testgelände.", "/testgelände tp", "/tg tp", "/tgtp");
		Help tpHelptg = new Help("/testgelände teleport <tg>", "Teleportiert dich zu dem Angegeben Testgelände.", "/testgelände tp <tg>", "/tg tp <tg>", "/tgtp <tg>");
		Help reset = new Help("/testgelände clear", "Clear dein Aktuelles TG.", "/tg reset", "/tgreset","/tg reset", "/tgclear", "/tg clear", "/testgelände clear");
		Help select = new Help("/testgelände select [tg]", "Setzt deine WorldEdit selection auf das gesamte Testgelände.", "/tg select", "/tg s", "/tgselect");
		Help list = new Help("/testgelände list", "Gibt eine Liste aller Testgelände aus.", "/tg list","/tglist","/tgl");
		Help tnt = new Help("/testgelände tnt", "Ändert den Status des TNTs.",  "tg.tnt", "tgtnt", "tg.tnt", "tnt" );
		Help tntdeny = new Help("/testgelände tnt deny", "Stellt TNT auf deinem Testgelände aus.",  "tg.tnt.deny", "tgtnt.deny", "tg.tnt.deny", "tnt.deny","tnt.d" );
		Help tntallow = new Help("/testgelände tnt allow", "Stellt TNT auf deinem Testgelände an.",  "tg.tnt.allow", "tgtnt.allow", "tg.tnt.allow", "tnt.allow","tnt.a" );
		
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