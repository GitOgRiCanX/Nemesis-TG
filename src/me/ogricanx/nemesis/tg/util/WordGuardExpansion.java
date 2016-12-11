package me.ogricanx.nemesis.tg.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.ogricanx.nemesis.tg.main.Main;

public class WordGuardExpansion {
	public static WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = Main.getTGPlugin().getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        System.err.println("Worldguard not Loaded!");
	        return null;
	    }
	    
	    return (WorldGuardPlugin) plugin;
	}

	public static ApplicableRegionSet getRegionsAtLocation(Location loc) {
		return WGBukkit.getRegionManager(loc.getWorld()).getApplicableRegions(loc);
	}

	public static ProtectedRegion getRegionAtLocation(Location loc) {
		for (ProtectedRegion rg : getRegionsAtLocation(loc)) {
			return rg;
		}
		return null;
	}
	
	public static ProtectedRegion getRegion(String id,World w) {
		RegionContainer container = getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(w);
		if (regions != null) {
		    return regions.getRegion(id);
		} else {
		    return null;
		}
	}
	
	public static ProtectedRegion getRegion(String id) {
		RegionContainer container = getWorldGuard().getRegionContainer();
		
		for (World w : Bukkit.getWorlds()) {
			RegionManager regions = container.get(w);
			if (regions != null) {
			    return regions.getRegion(id);
			} else {
			    return null;
			}
		}
		return null;
	}
	
	public static RegionManager getRegionManager(World world) {
		return getWorldGuard().getRegionManager(world);
	}
	
	
	
}
