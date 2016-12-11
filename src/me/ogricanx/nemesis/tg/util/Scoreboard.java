package me.ogricanx.nemesis.tg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.ScoreboardManager;

import com.sk89q.worldguard.protection.flags.DefaultFlag;

import me.ogricanx.nemesis.tg.main.Main;
import me.winterguardian.easyscoreboards.ScoreboardUtil;

public class Scoreboard {

	public static HashMap<String, List<Player>> players = new HashMap<>();

	public static void updateScoreboard() {
		

		for (Testgelände tg : Testgelände.getValues(Main.getTGPlugin())) {
			
			List<Player> list = new ArrayList<>();
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (WordGuardExpansion.getRegionAtLocation(p.getLocation()) == null) {
					ScoreboardManager manager = Bukkit.getScoreboardManager();
					p.setScoreboard(manager.getNewScoreboard());
					continue;
				}
				
				if (tg.isOnTestgelände(p)) {
					list.add(p);
				}
			}
			players.put(tg.getName(), list);
		}
		
		for (Testgelände tg : Testgelände.getValues(Main.getTGPlugin())) {
			org.bukkit.scoreboard.Scoreboard bord = Bukkit.getScoreboardManager().getNewScoreboard();
			String val = WordGuardExpansion.getRegionAtLocation(tg.getTelLoc1()).getFlag(DefaultFlag.TNT) != null ? WordGuardExpansion.getRegionAtLocation(tg.getTelLoc1()).getFlag(DefaultFlag.TNT).name(): "-" ;
			List<String> list = new ArrayList<>();
			list.add(ChatColor.GOLD + "Testgelände");
			list.add(ChatColor.GRAY + "Name: " + tg.getName());
			list.add(ChatColor.GRAY + "===============");
			list.add(ChatColor.GRAY + "TNT: " + ChatColor.GOLD + val);
			list.add(ChatColor.GRAY + "=============== ");
			list.add(ChatColor.GOLD + "Spieler:");
			
			for (Player p : players.get(tg.getName())) {
				String name = p.getName().length() > 15 ? p.getName().substring(0, 14) : p.getName();
				list.add(ChatColor.GREEN+"-"+name);
			}
			String[] array = new String[list.size()];
			array = list.toArray(array);
			
			ScoreboardUtil.unrankedSidebarDisplay(players.get(tg.getName()), array, bord);
		}
	}
}
