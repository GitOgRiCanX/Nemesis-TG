package me.ogricanx.nemesis.tg.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.LocalConfiguration;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import de.pro_crafting.commandframework.CommandFramework;
import me.ogricanx.nemesis.tg.commands.BackupCommand;
import me.ogricanx.nemesis.tg.commands.BlockCommands;
import me.ogricanx.nemesis.tg.commands.CreateCommand;
import me.ogricanx.nemesis.tg.commands.EditCommand;
import me.ogricanx.nemesis.tg.commands.PasteCommands;
import me.ogricanx.nemesis.tg.commands.TGCommands;
import me.ogricanx.nemesis.tg.commands.TNTCommands;
import me.ogricanx.nemesis.tg.commands.TeleportCommand;
import me.ogricanx.nemesis.tg.util.Scoreboard;

public class Main extends JavaPlugin {
	
	public static String pref = "§8[§bNemesis-TG§8] §7";
	public static String preferr = "§8[§bNemesis-TG§8] §c";
	private static Main m;
	
	@Override
	public void onEnable() {
		m = this;
		registerCommands();
		
		if (this.getDataFolder().exists() == false) {
			this.getConfig().set("abc", "Das ist die Config von");
			this.saveConfig();
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Scoreboard.updateScoreboard();
			}
		}, 20, 60);
	}
	
	public void registerCommands() {
		CommandFramework framework = new CommandFramework(this);
		
		framework.registerCommands(new TGCommands(this));
		framework.registerCommands(new CreateCommand(this));
		framework.registerCommands(new BackupCommand(this));
		framework.registerCommands(new EditCommand(this));
		framework.registerCommands(new TeleportCommand(this));
		framework.registerCommands(new TNTCommands(this));
		framework.registerCommands(new BlockCommands(this));
		framework.registerCommands(new PasteCommands(this));
		
//		Beta Commands
//		framework.registerCommands(new BetaCommands(this));
		
		
		framework.setNoPermMessage("§7Du hast keine Rechte für diesen Befehl.");
        framework.setInGameOnlyMessage("Der Command muss von einem Spieler ausgeführt werden.");
	}
	
	public static Main getTGPlugin() {
		return Main.m;
	}

	public static File getWorldEditFile() {
    	WorldEdit we = ((WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit")).getWorldEdit();
        LocalConfiguration config = we.getConfiguration();
        return we.getWorkingDirectoryFile(config.saveDir);	
    }

	public static WorldEdit getWorldEdit() {
		return ((WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit")).getWorldEdit();
    	
    }
	
	
}
