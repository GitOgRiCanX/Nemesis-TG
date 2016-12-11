package me.ogricanx.nemesis.tg.util;

import java.util.ArrayList;
import java.util.List;

public class Help {
	
	public String cmd;
	public String message;
	public String[] aliases;
	
	public static List<Help> values = new ArrayList<>();
	
	public Help(String cmd,String message,String... aliases) {
		this.cmd = cmd;
		this.message = message;
		this.aliases = aliases;
		values.add(this);
	}
	
	@Override
	public String toString() {
		String al = aliases[0];
		for (int i = 1; i < aliases.length; i++) {
			al = al+" | "+aliases[i];
		}
		return "§6"+cmd+":\n§f " + message +"§8 [§7"+al+"§8]";
	}
}
