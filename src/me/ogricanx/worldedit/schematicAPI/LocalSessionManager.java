package me.ogricanx.worldedit.schematicAPI;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.entity.Player;

public class LocalSessionManager {
	
	public static LocalSession getLocalSessionFromPlayer(org.bukkit.entity.Player player) {
		final WorldEditPlugin wep = Util.getWorldEditPlugin();
		Player actor = (Player) wep.wrapCommandSender(player);
		return wep.getWorldEdit().getSessionManager().get(actor);
	}
	
}
	