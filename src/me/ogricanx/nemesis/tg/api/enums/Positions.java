package me.ogricanx.nemesis.tg.api.enums;

public enum Positions {
	MINIMUM_MAP,
	MAXIMUM_MAP,
	NORDEN_PASTE,
	NORDEN_TIER1_MINIMUM,
	NORDEN_TIER1_MAXIMUM,
	NORDEN_TIER2_MINIMUM,
	NORDEN_TIER2_MAXIMUM,
	S�DEN_TIER1_MINIMUM,
	S�DEN_TIER1_MAXIMUM,
	S�DEN_TIER2_MINIMUM,
	S�DEN_TIER2_MAXIMUM;
	
	@Override
	public String toString() {
		switch (this) {
		case MAXIMUM_MAP:
			return "MaxMap";
		case MINIMUM_MAP:
			return "MinMap";
		case NORDEN_PASTE:
			return "pasteS1";
		case NORDEN_TIER1_MAXIMUM:
			return "t1maxS1";
		case NORDEN_TIER1_MINIMUM:
			return "t1minS1";
		case NORDEN_TIER2_MAXIMUM:
			return "t2maxS1";
		case NORDEN_TIER2_MINIMUM:
			return "t2minS1";
		case S�DEN_TIER1_MAXIMUM:
			return "t1maxS2";
		case S�DEN_TIER1_MINIMUM:
			return "t1minS2";
		case S�DEN_TIER2_MAXIMUM:
			return "t2maxS2";
		case S�DEN_TIER2_MINIMUM:
			return "t2minS2";
		default: 
			System.err.println(new Exception("Wie genau hast du das bitte hin gekrigt?"));
			return null;
	}
		
	}
}
