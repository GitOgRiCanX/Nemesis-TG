package me.ogricanx.worldedit.schematicAPI;

import java.io.IOException;

import org.bukkit.Location;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.CuboidClipboard.FlipDirection;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;

@SuppressWarnings("deprecation")
public class LoadedSchematic extends Schematic {
	private CuboidClipboard c;
	
	/**
	 * Loads Schematic directly while Creating it.
	 * @see {@link Schematic#Schematic(String)}
	 * @throws SchematicNotExistsExeption when the Schematic not exists or an other Error occurs while loading.
	 */
	public LoadedSchematic(String path) throws SchematicNotExistsExeption {
		
		super(path);
		if (this.exists() == false) {
			throw new SchematicNotExistsExeption(this);
		}
		try {
			this.c = CuboidClipboard.loadSchematic(getFile());
		} catch (DataException | IOException e) {
			e.printStackTrace();
			throw new SchematicNotExistsExeption(this);
		}
	}
	
	public boolean paste(Location loc,boolean noAir,org.bukkit.entity.Player p) { 
		EditSession es = new EditSession(new BukkitWorld(loc.getWorld()), -1);
		LocalSession session = LocalSessionManager.getLocalSessionFromPlayer(p);
		try {
			es.enableQueue();
			c.paste(es, new Vector(loc.getX(), loc.getY(), loc.getZ()), noAir);
			session.remember(es);
			return true;
		} catch (MaxChangedBlocksException e) {
			return false;
		}
	}
	
	public void paste(Location loc,boolean noAir, Facing f, org.bukkit.entity.Player p) throws MaxChangedBlocksException {
		Vector min = BukkitUtil.toVector(loc).add(c.getOffset());
		Vector max = min.add(c.getSize());
		EditSession es = new EditSession(new BukkitWorld(loc.getWorld()), -1);
		
		Integer w1 = min.getBlockZ(); //Wert 1
		Integer w2 = max.getBlockZ(); //Wert 2
		Integer a = loc.getBlockZ(); //Mitte

		final LocalSession session = LocalSessionManager.getLocalSessionFromPlayer(p);
		
		if (w1 > a && w2 > a) {
			//Schaut nach Norden
			if (f== Facing.SOUTH) {
				this.getCuboidClipboard().rotate2D(180);
				this.c.paste(es, BukkitUtil.toVector(loc), noAir);
				session.remember(es);
				return;
			}else {
				this.c.paste(es, BukkitUtil.toVector(loc), noAir);
				session.remember(es);
				return;
			}
		}else if (w1 <= a && w2 <= a) {
			//Schaut nach Süden
			if (f== Facing.NORTH) {
				this.getCuboidClipboard().rotate2D(180);
				this.c.paste(es, BukkitUtil.toVector(loc), noAir);
				session.remember(es);
				return;
			}else {
				this.c.paste(es, BukkitUtil.toVector(loc), noAir);
				session.remember(es);
				return;
			}
		}else {
			int bigger = Math.abs((Math.abs(a) - Math.abs(w1))) > Math.abs((Math.abs(a) - Math.abs(w2)))? w1 : w2;
			if (bigger > a) {
				//Steht im Süden
				if (f== Facing.SOUTH) {
					this.getCuboidClipboard().rotate2D(180);
					this.c.paste(es, BukkitUtil.toVector(loc), noAir);
					session.remember(es);
					return;
				}else {
					this.c.paste(es, BukkitUtil.toVector(loc), noAir);
					session.remember(es);
					return;
				}
			}else {
				//Steht im Norden
				if (f== Facing.NORTH) {
					this.getCuboidClipboard().rotate2D(180);
					this.c.paste(es, BukkitUtil.toVector(loc), noAir);
					session.remember(es);
					return;
				}else {
					this.c.paste(es, BukkitUtil.toVector(loc), noAir);
					session.remember(es);
					return;
				}
			}
		}
	}
	
	/**
	 * @param dir
	 * @see com.sk89q.worldedit.CuboidClipboard#flip(com.sk89q.worldedit.CuboidClipboard.FlipDirection)
	 */
	public void flip(FlipDirection dir) {
		c.flip(dir);
	}


	/**
	 * @param position
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 * @see com.sk89q.worldedit.CuboidClipboard#getBlock(com.sk89q.worldedit.Vector)
	 */
	public BaseBlock getBlock(Vector position) throws ArrayIndexOutOfBoundsException {
		return c.getBlock(position);
	}


	/**
	 * @return
	 * @see com.sk89q.worldedit.CuboidClipboard#getHeight()
	 */
	public int getHeight() {
		return c.getHeight();
	}


	/**
	 * @return
	 * @see com.sk89q.worldedit.CuboidClipboard#getLength()
	 */
	public int getLength() {
		return c.getLength();
	}


	/**
	 * @return
	 * @see com.sk89q.worldedit.CuboidClipboard#getOffset()
	 */
	public Vector getOffset() {
		return c.getOffset();
	}


	/**
	 * @return
	 * @see com.sk89q.worldedit.CuboidClipboard#getOrigin()
	 */
	public Vector getOrigin() {
		return c.getOrigin();
	}


	/**
	 * @return
	 * @see com.sk89q.worldedit.CuboidClipboard#getSize()
	 */
	public Vector getSize() {
		return c.getSize();
	}


	/**
	 * @return
	 * @see com.sk89q.worldedit.CuboidClipboard#getWidth()
	 */
	public int getWidth() {
		return c.getWidth();
	}


	/**
	 * @param arg0
	 * @see com.sk89q.worldedit.CuboidClipboard#rotate2D(int)
	 */
	public void rotate2D(int arg0) {
		c.rotate2D(arg0);
	}


	/**
	 * @param position
	 * @param block
	 * @see com.sk89q.worldedit.CuboidClipboard#setBlock(com.sk89q.worldedit.Vector, com.sk89q.worldedit.blocks.BaseBlock)
	 */
	public void setBlock(Vector position, BaseBlock block) {
		c.setBlock(position, block);
	}


	/**
	 * @param offset
	 * @see com.sk89q.worldedit.CuboidClipboard#setOffset(com.sk89q.worldedit.Vector)
	 */
	public void setOffset(Vector offset) {
		c.setOffset(offset);
	}


	/**
	 * @param origin
	 * @see com.sk89q.worldedit.CuboidClipboard#setOrigin(com.sk89q.worldedit.Vector)
	 */
	public void setOrigin(Vector origin) {
		c.setOrigin(origin);
	}


	public CuboidClipboard getCuboidClipboard() {
		return c;
	}

	public void setCuboidClipboard(CuboidClipboard c) {
		this.c = c;
	}

	public enum Facing {
		NORTH,
		SOUTH;
	}
	
}
