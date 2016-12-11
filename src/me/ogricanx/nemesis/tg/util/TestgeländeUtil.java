package me.ogricanx.nemesis.tg.util;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;

public class TestgeländeUtil {
	
	@SuppressWarnings("unchecked")
	public static Location locationDeserialize(Object obj) {
	    if (obj instanceof Location) {
	      return (Location) obj;
	    }

	    Map<String, Object> section = new HashMap<String, Object>();
	    if (obj instanceof MemorySection) {
	      MemorySection sec = (MemorySection) obj;
	      for (String key : sec.getKeys(true)) {
	        section.put(key, sec.get(key));
	      }
	    } else if (obj instanceof ConfigurationSection) {
	      ConfigurationSection sec = (ConfigurationSection) obj;
	      for (String k : sec.getKeys(true)) {
	        section.put(k, sec.get(k));
	      }
	    } else {
	      section = (Map<String, Object>) obj;
	    }

	    try {
	      if (section == null) {
	        return null;
	      }

	      double x = Double.valueOf(section.get("x").toString());
	      double y = Double.valueOf(section.get("y").toString());
	      double z = Double.valueOf(section.get("z").toString());
	      float yaw = Float.valueOf(section.get("yaw").toString());
	      float pitch = Float.valueOf(section.get("pitch").toString());
	      World w = Bukkit.getWorld(section.get("world").toString());
	      if (w == null) {
	        return null;
	      }

	      return new Location(w, x, y, z, yaw, pitch);
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return null;
	}
	
	public static Map<String, Object> LocationToHashMap(Location loc) {
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("x", loc.getX());
		list.put("y", loc.getY());
		list.put("z", loc.getZ());
		list.put("yaw", loc.getYaw());
		list.put("pitch", loc.getPitch());
		list.put("world", loc.getWorld().getName());
		return list;
	}
	
	public static String LocationToString(Location loc) {
		return "world:"+loc.getWorld().getName()+";x:"+loc.getX()+";y:"+loc.getY()+";z:"+loc.getZ()+";pitch:"+loc.getPitch()+";Yaw:"+loc.getYaw();
	}
	
	
}
