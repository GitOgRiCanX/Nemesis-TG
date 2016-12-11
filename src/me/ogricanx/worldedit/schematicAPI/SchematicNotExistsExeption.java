package me.ogricanx.worldedit.schematicAPI;

@SuppressWarnings("serial")

public class SchematicNotExistsExeption extends Exception {
	/**
	 * gets throwed when a Error occured while loading a {@link LoadedSchematic}
	 */
	Schematic s;
	
	public SchematicNotExistsExeption(Schematic schem) {
		super(schem.getName()+" could not be loaded.");
		s = schem;
	}
	
	public String getSchematicName() {
		return this.s.getName();
	}
	
}
