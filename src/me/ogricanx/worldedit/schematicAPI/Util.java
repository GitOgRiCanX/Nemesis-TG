package me.ogricanx.worldedit.schematicAPI;

import java.io.File;

import org.bukkit.Bukkit;

import com.sk89q.worldedit.LocalConfiguration;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class Util {
	/**
	 * @return the WorldEdit DirectoryFile (the schematic Folder)
	 * @see WorldEdit, {@link File}
	 */
	public static File getWorldEditFile() {
    	WorldEdit we = ((WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit")).getWorldEdit();
        LocalConfiguration config = we.getConfiguration();
        return we.getWorkingDirectoryFile(config.saveDir);	
    }
	
	/**
	 * @return the WorldEditPlugin Object.
	 * @see WorldEdit
	 */
	public static WorldEditPlugin getWorldEditPlugin() {
        return (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }
	
	/**
	 * @return the WorldEdit Plugin.
	 * @see WorldEdit
	 */
	public static WorldEdit getWorldEdit() {
		return ((WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit")).getWorldEdit();
    	
    }
	
	
	public void bla() {
		
	}
	
}
