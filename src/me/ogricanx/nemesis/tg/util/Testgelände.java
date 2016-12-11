package me.ogricanx.nemesis.tg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.ogricanx.nemesis.tg.main.Main;
import me.ogricanx.worldedit.schematicAPI.Cuboid;
import me.ogricanx.worldedit.schematicAPI.Schematic;
import me.ogricanx.worldedit.schematicAPI.SchematicNotExistsExeption;

@SerializableAs("Testgelände")
public class Testgelände implements ConfigurationSerializable {
	
	private String name = null;
	private Location TelLoc1 = null;
	private Location MinMap = null;
	private Location MaxMap = null;
	
	private Location pasteS1 = null;
	private Location t1minS1 = null;
	private Location t1maxS1 = null;
	private Location t2minS1 = null;
	private Location t2maxS1= null;
	private Location pasteS2 = null;
	private Location t1minS2 = null;
	private Location t1maxS2 = null;
	private Location t2minS2 = null;
	private Location t2maxS2= null;
	
	private static List<Testgelände> values = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public Testgelände(Object tg) {
		Map<String, Object>  deSerialize = new TreeMap<String, Object>();
		if (tg instanceof MemorySection) {
			 MemorySection sec = (MemorySection) tg;
		      for (String key : sec.getKeys(false)) {
		        deSerialize.put(key, sec.get(key));
		      }
		}else if (tg instanceof Map<?, ?>) {
			deSerialize = (HashMap<String, Object>) tg;
		}else if (tg instanceof ConfigurationSection) {
			ConfigurationSection sec = (ConfigurationSection) tg;
  			for (String key : sec.getKeys(false)) {
  				deSerialize.put(key, sec.get(key));
  			}
		}else {
			System.err.println("Es ist ein Fehler Aufgetreten!");
			return;
		}
		
		if (deSerialize.get("name") != null) this.name = deSerialize.get("name").toString();
		if (deSerialize.get("TelLoc1") != null) this.TelLoc1 = TestgeländeUtil.locationDeserialize(deSerialize.get("TelLoc1"));
		if (deSerialize.get("MinMap") != null) this.MinMap = TestgeländeUtil.locationDeserialize(deSerialize.get("MinMap"));
		if (deSerialize.get("MaxMap") != null) this.MaxMap = TestgeländeUtil.locationDeserialize(deSerialize.get("MaxMap"));
		
		if (deSerialize.get("pasteS1") != null) this.pasteS1 = TestgeländeUtil.locationDeserialize(deSerialize.get("pasteS1"));
		if (deSerialize.get("pasteS2") != null) this.pasteS2 = TestgeländeUtil.locationDeserialize(deSerialize.get("pasteS2"));
		
		if (deSerialize.get("t1minS1") != null) this.t1minS1 = TestgeländeUtil.locationDeserialize(deSerialize.get("t1minS1"));
		if (deSerialize.get("t1minS2") != null) this.t1minS1 = TestgeländeUtil.locationDeserialize(deSerialize.get("t1minS2"));
		
		if (deSerialize.get("t1maxS1") != null) this.t1maxS1 = TestgeländeUtil.locationDeserialize(deSerialize.get("t1maxS1"));
		if (deSerialize.get("t1maxS2") != null) this.t1maxS2 = TestgeländeUtil.locationDeserialize(deSerialize.get("t1maxS2"));
		
		if (deSerialize.get("t2minS1") != null) this.t2minS1 = TestgeländeUtil.locationDeserialize(deSerialize.get("t2minS1"));
		if (deSerialize.get("t2minS2") != null) this.t2minS2 = TestgeländeUtil.locationDeserialize(deSerialize.get("t2minS2"));
		
		if (deSerialize.get("t2maxS1") != null) this.t2maxS1 = TestgeländeUtil.locationDeserialize(deSerialize.get("t2maxS1"));
		if (deSerialize.get("t2maxS2") != null) this.t2maxS2 = TestgeländeUtil.locationDeserialize(deSerialize.get("t2maxS2"));
		
		if (TelLoc1 == null) TelLoc1 = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
		if (MinMap == null) MinMap = new Location(TelLoc1.getWorld(), TelLoc1.getX()-56, TelLoc1.getY(), TelLoc1.getZ()-91);
		if (MaxMap == null) MaxMap = new Location(TelLoc1.getWorld(), TelLoc1.getX()+56, TelLoc1.getY()+60, TelLoc1.getZ()+91);
		
//		S1 = norden
		if (pasteS1 == null) pasteS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX(), TelLoc1.getY(), TelLoc1.getZ()-25);
		if (t1minS1 == null) t1minS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-11, TelLoc1.getY(), TelLoc1.getZ()-48);
		if (t1maxS1 == null) t1maxS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+11, TelLoc1.getY()+22, TelLoc1.getZ()-24);
		if (t2minS1 == null) t2minS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-33, TelLoc1.getY(), TelLoc1.getZ()-62);
		if (t2maxS1 == null) t2maxS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+33, TelLoc1.getY()+40, TelLoc1.getZ()-26);
		
//		S2 = süden
		if (pasteS2 == null) pasteS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX(), TelLoc1.getY(), TelLoc1.getZ()+25);
		if (t1minS2 == null) t1minS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+11, TelLoc1.getY(), TelLoc1.getZ()+48);
		if (t1maxS2 == null) t1maxS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-11, TelLoc1.getY()+22, TelLoc1.getZ()+24);
		if (t2minS2 == null) t2minS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+33, TelLoc1.getY(), TelLoc1.getZ()+62);
		if (t2maxS2 == null) t2maxS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-33, TelLoc1.getY()+40, TelLoc1.getZ()+26);
	}
	
	public Testgelände(String name, Location TelLoc1) {
		this.name = name;
		this.TelLoc1 = TelLoc1;
		MinMap = new Location(TelLoc1.getWorld(), TelLoc1.getX()-56, TelLoc1.getY() , TelLoc1.getZ()-91);
		MaxMap = new Location(TelLoc1.getWorld(), TelLoc1.getX()+56, TelLoc1.getY()+60, TelLoc1.getZ()+91);

//		S1 = norden
		pasteS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX(), TelLoc1.getY(), TelLoc1.getZ()-25);
		t1minS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-11, TelLoc1.getY(), TelLoc1.getZ()-48);
		t1maxS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+11, TelLoc1.getY()+22, TelLoc1.getZ()-26);
		t2minS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-33, TelLoc1.getY(), TelLoc1.getZ()-62);
		t2maxS1 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+33, TelLoc1.getY()+40, TelLoc1.getZ()-26);
		
//		S2 = süden
		pasteS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX(), TelLoc1.getY(), TelLoc1.getZ()+25);
		t1minS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+11, TelLoc1.getY(), TelLoc1.getZ()+48);
		t1maxS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-11, TelLoc1.getY()+22, TelLoc1.getZ()+26);
		t2minS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()+33, TelLoc1.getY(), TelLoc1.getZ()+62);
		t2maxS2 = new Location(TelLoc1.getWorld(), TelLoc1.getX()-33, TelLoc1.getY()+40, TelLoc1.getZ()+26);
		boolean b = this.createTGRegion();
		if (!b) {
			System.out.println(new NullPointerException("Region unable to create!"));
		}
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> sel = new TreeMap<String, Object>();
		sel.put("name", name);
		sel.put("TelLoc1", TestgeländeUtil.LocationToHashMap(TelLoc1));
		sel.put("MinMap", TestgeländeUtil.LocationToHashMap(MinMap));
		sel.put("MaxMap", TestgeländeUtil.LocationToHashMap(MaxMap));
		

		sel.put("pasteS1", TestgeländeUtil.LocationToHashMap(pasteS1));
		sel.put("t2maxS1", TestgeländeUtil.LocationToHashMap(t2maxS1));
		sel.put("t1minS1", TestgeländeUtil.LocationToHashMap(t1minS1));
		sel.put("t1maxS1", TestgeländeUtil.LocationToHashMap(t1maxS1));
		sel.put("t2minS1", TestgeländeUtil.LocationToHashMap(t2minS1));
		
		sel.put("pasteS2", TestgeländeUtil.LocationToHashMap(pasteS2));
		sel.put("t1maxS2", TestgeländeUtil.LocationToHashMap(t1maxS2));
		sel.put("t2minS2", TestgeländeUtil.LocationToHashMap(t2minS2));
		sel.put("t1minS2", TestgeländeUtil.LocationToHashMap(t1minS2));
		sel.put("t2maxS2", TestgeländeUtil.LocationToHashMap(t2maxS2));
		return sel;
	}
	
	public static boolean doTestgeländeExists(String tg,Main m) {
		for (Testgelände a : getValues(m)) {
			if (a.getName().equals(tg)) {
				return true;
			}
		}
		return false;
	}
	
	public static Testgelände getTestgelände(String s,Main m) {
		for (Testgelände a : getValues(m)) {
			if (a.getName().equals(s)) {
				return a;
			}
		}
		return null;
	}
	
	public static void setValues(List<Testgelände> values, Main m) {
		Testgelände.values = values;
		int i = 0;
		for (Testgelände a : values) {
			m.getConfig().createSection("tg."+i, a.serialize());
			i += 1;
		}
		m.saveConfig();
	}
	
	
	
	public static List<Testgelände> getValues(Main m) {
		if (values.size() > 0) {
			return Testgelände.values;
		}
		int i = 0;
		List<Testgelände> list = new ArrayList<>();
		while (m.getConfig().getConfigurationSection("tg."+i) != null) {
			list.add(new Testgelände(m.getConfig().getConfigurationSection("tg."+i)));
			i += 1;
		}
		return list;
	}

	public static List<String> getStringValues(Main m) {
		List<String> l = new ArrayList<>();
		for (Testgelände tg : getValues(m)) {
			l.add(tg.getName());
		}
		return l;
	}
	
	public boolean isOnTestgelände(Player p) {
		if (WordGuardExpansion.getRegionAtLocation(p.getLocation()) == null) {
			return false;
		}
		if (WordGuardExpansion.getRegionAtLocation(p.getLocation()).getId().equalsIgnoreCase(getName())) {
			return true;
		}else {
			return false;
		}
	}
	
	public static HashMap<Integer, Testgelände> getHashValues(Main m) {
		int i = 0;
		HashMap<Integer, Testgelände> list = new HashMap<>();
		while (m.getConfig().get("tg."+i) != null) {
			list.put(i, new Testgelände(m.getConfig().createSection("tg."+i)));
			i += 1;
		}
		return list;
	}
	

	public boolean createTGRegion() {
		ProtectedRegion region = WordGuardExpansion.getRegion(this.getName());
		if( region == null) {
			region =createRegion(new Cuboid(this.getMinMap(), this.getMaxMap()), this.getName());
			region.setFlag(DefaultFlag.TNT, StateFlag.State.ALLOW);
			region.setFlag(DefaultFlag.GREET_MESSAGE, Main.pref+"Du hast das Testgelände "+this.getName()+ " Betreten!");
			region.setFlag(DefaultFlag.FAREWELL_MESSAGE, Main.pref+"Du hast das Testgelände "+this.getName()+ " Verlassen!");
			region.setFlag(DefaultFlag.ENTRY_DENY_MESSAGE, Main.preferr+"Du darfst das Testgelände "+this.getName()+ " nicht Betreten!");
			region.setFlag(DefaultFlag.EXIT_DENY_MESSAGE, Main.preferr+"Du darfst das Testgelände "+this.getName()+ " nicht Verlassen!");
			region.setFlag(DefaultFlag.BUILD, StateFlag.State.ALLOW);
			region.setFlag(DefaultFlag.ENDERPEARL, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.GAME_MODE, GameMode.CREATIVE);
			return true;
		}else{
			return false;
		}
	}
	
	public static ProtectedRegion createRegion(Cuboid c, String name) {
		BlockVector min = new BlockVector(c.getLowerX(),c.getLowerY(),c.getLowerZ());
		BlockVector max = new BlockVector(c.getUpperX(),c.getUpperY(),c.getUpperZ());
		ProtectedCuboidRegion pr = new ProtectedCuboidRegion(name,min,max);
		RegionContainer container = WordGuardExpansion.getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(c.getWorld());
		regions.addRegion(pr);
		return pr;
	}
	
	public boolean clearTG(Player p) {
		Schematic schem = new Schematic("tg/clear");
		try {
			return schem.get().paste(this.getTelLoc1(), false, p);
		} catch (SchematicNotExistsExeption e) {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getTelLoc1() {
		return TelLoc1;
	}
	public void setTelLoc1(Location telLoc1) {
		TelLoc1 = telLoc1;
	}
	public Location getMinMap() {
		return MinMap;
	}
	public void setMinMap(Location minMap) {
		MinMap = minMap;
	}
	public Location getMaxMap() {
		return MaxMap;
	}
	public void setMaxMap(Location maxMap) {
		MaxMap = maxMap;
	}
	public Location getPasteS1() {
		return pasteS1;
	}

	public void setPasteS1(Location pasteS1) {
		this.pasteS1 = pasteS1;
	}

	public Location getT1minS1() {
		return t1minS1;
	}

	public void setT1minS1(Location t1minS1) {
		this.t1minS1 = t1minS1;
	}

	public Location getT1maxS1() {
		return t1maxS1;
	}

	public void setT1maxS1(Location t1maxS1) {
		this.t1maxS1 = t1maxS1;
	}

	public Location getT2minS1() {
		return t2minS1;
	}

	public void setT2minS1(Location t2minS1) {
		this.t2minS1 = t2minS1;
	}

	public Location getT2maxS1() {
		return t2maxS1;
	}

	public void setT2maxS1(Location t2maxS1) {
		this.t2maxS1 = t2maxS1;
	}

	public Location getPasteS2() {
		return pasteS2;
	}

	public void setPasteS2(Location pasteS2) {
		this.pasteS2 = pasteS2;
	}

	public Location getT1minS2() {
		return t1minS2;
	}

	public void setT1minS2(Location t1minS2) {
		this.t1minS2 = t1minS2;
	}

	public Location getT1maxS2() {
		return t1maxS2;
	}

	public void setT1maxS2(Location t1maxS2) {
		this.t1maxS2 = t1maxS2;
	}

	public Location getT2minS2() {
		return t2minS2;
	}

	public void setT2minS2(Location t2minS2) {
		this.t2minS2 = t2minS2;
	}

	public Location getT2maxS2() {
		return t2maxS2;
	}

	public void setT2maxS2(Location t2maxS2) {
		this.t2maxS2 = t2maxS2;
	}

	public boolean isinLocation(Player p, Location min, Location max) {
		if (p.getLocation().getBlockX() > min.getBlockX() && p.getLocation().getBlockX() < max.getBlockX()) {
			if (p.getLocation().getBlockY() > min.getBlockY() && p.getLocation().getBlockY() < max.getBlockY()) {
				if (p.getLocation().getBlockZ() > min.getBlockZ() && p.getLocation().getBlockZ() < max.getBlockZ()) {
					return true;
				}
			}
		}
		return false;
	}
	
}
