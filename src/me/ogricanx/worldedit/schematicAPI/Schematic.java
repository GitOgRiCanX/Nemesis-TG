package me.ogricanx.worldedit.schematicAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.Location;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;

/**
 * A Class for Easy Managing of Schematics.
 * @author McTNTCrafter
 */
public class Schematic {

	String name;
	String path;
	String absolutPath;
	File file;
	File WorkingDirecory = Util.getWorldEditFile();
	LoadedSchematic loaded;
	
	/**
	 * subfolders are possible to create them you just need to add / for the diretiroys. Example: 'new Schematic("test/name")'
	 * <p>
	 * ".schematic" is not necessarily, but possible.
	 * @param path creates a {@link Schematic} with the name path
	 */
	public Schematic(String path) {
		if (!path.endsWith(".schematic")) path = path + ".schematic";
		
		this.name = path.split("/")[path.split("/").length - 1].replace(".schematic", "");
		this.path = path;
		this.file = new File(WorkingDirecory, path);
		this.absolutPath = this.file.getAbsolutePath();
	}	
	
	/**
	 * Checks if the {@link Schematic} is loadable and if it is possible to execute the .get() Method.
	 * @return <b>true</b> when no exceptions where Throw
	 * <p> <b>false</b> if {@code new LoadedSchematic(getName())} throws a {@link SchematicNotExistsExeption}
	 */
	public boolean isLoadable() {
		try {
			new LoadedSchematic(getPath());
			return true;
		} catch (SchematicNotExistsExeption e) {
			return false;
		}
	}
	
	/**
	 * @return the  {@link LoadedSchematic} of the current  Object.
	 * @throws SchematicNotExistsExeption
	 * @see {@link LoadedSchematic}
	 */
	public LoadedSchematic get() throws SchematicNotExistsExeption{
		if (loaded == null) {
			try {
				return new LoadedSchematic(getPath());
			} catch (SchematicNotExistsExeption e) {
				throw new SchematicNotExistsExeption(this);
			}
		}else return loaded;
	}
	
	/**
	 * creates a 
	 * @param file the .schematic File.
	 */
	public Schematic(File file) {
		this.file = file;
		this.absolutPath = file.getAbsolutePath();
		this.path = absolutPath.replace(WorkingDirecory.getAbsolutePath(), "").replace("'\'", "//");
		this.name = path.split("/")[path.split("/").length - 1].replace(".schematic", "");
	}
	
	
	/**
	 * @return if .getFile() is a Directory.
	 */
	public boolean isDirectory() {
		if (file.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return if .getFile() exists.
	 */
	public boolean exists() {
		if (this.file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return returns the last arguments of path. Example: "123/test" --> returns "test"
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Path is the path form [WorldEdit Working Directory]/...
	 * @return returns the path Value.
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * @return the File generated form the path Var.
	 * <p>
	 * {@code new File(WorkingDirecory, path);
	 * }
	 */
	public File getFile() {
		return this.file;
	}
	
	
	public boolean createNew(Location l1, Location l2) {
		try {
			Location min = new Location(l1.getWorld(), l1.getBlockX(), l1.getBlockY(), l1.getBlockZ()); 
			Location max = new Location(l2.getWorld(), l2.getBlockX(), l2.getBlockY(), l2.getBlockZ()); 
			
			getFile().getParentFile().mkdirs();
			Vector vMin = BukkitUtil.toVector(min);
			Vector vMax = BukkitUtil.toVector(max);
			Region rg = new CuboidRegion(vMin,vMax);
	        Clipboard board = new BlockArrayClipboard(rg);
	        @SuppressWarnings("deprecation")
			EditSession es = Util.getWorldEdit().getEditSessionFactory().getEditSession(BukkitUtil.getLocalWorld(min.getWorld()), -1);
	        ForwardExtentCopy safd = new ForwardExtentCopy(es, rg, board, rg.getMinimumPoint());
	        try {
				Operations.complete(safd);
			} catch (WorldEditException e) {
				return false;
			}
	        
			ClipboardWriter writer;
	        FileOutputStream stream = new FileOutputStream(getFile());
			writer = ClipboardFormat.SCHEMATIC.getWriter(stream);
			writer.write(board, null);
			writer.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
}
