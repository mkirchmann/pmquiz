package de.neuenberger.pmp.processes;

import generated.CplxDefinition;
import generated.CplxDefinitionWithLevels;
import generated.CplxGroup;
import generated.CplxLevelledDefinition;

public class HskLine {
	private static final int IDX_CN_SIMPLIFIED;
	private static final int IDX_CN_TRADITIONAL;
	private static final int IDX_PNY_1;
	private static final int IDX_PNY_2;
	private static final int IDX_DEFINITION;
	private static final int SIZE;
	static {
		int idx=0;
		IDX_CN_SIMPLIFIED=idx++;
		IDX_CN_TRADITIONAL=idx++;
		IDX_PNY_1=idx++;
		IDX_PNY_2=idx++;
		IDX_DEFINITION=idx++;
		SIZE=idx;
	}

	private String[] split;
	
	public HskLine(String readLine) {
		split = readLine.split("\\t");
		if (split.length!=SIZE) {
			throw new RuntimeException("Wrong amount of lines: "+split.length+". Expected: "+SIZE);
		}
	}
	
	public String getCnSimplified() {
		return split[IDX_CN_SIMPLIFIED];
	}
	
	public String getCnTraditional() {
		return split[IDX_CN_TRADITIONAL];
	}
	
	public String getCnPny1() {
		return split[IDX_PNY_1];
	}
	
	public String getCnPny2() {
		return split[IDX_PNY_2];
	}
	
	public String getDefinition() {
		return split[IDX_DEFINITION];
	}
	
	/**
	 * Creates a definition with one level 1 and two level 2.
	 * @return {@link CplxDefinitionWithLevels}.
	 */
	public CplxDefinitionWithLevels createLevelledDefinition() {
	    CplxDefinitionWithLevels lDefinition = new CplxDefinitionWithLevels();
	    lDefinition.getLevelledDefinition().add(createDefinition1());
	    lDefinition.getLevelledDefinition().add(createDefinition2());
	    lDefinition.getLevelledDefinition().add(createDefinition3());
	    return lDefinition;
	}

	/**
	 * Creates a simple definition, Chinese+Pinyin ./. English.
	 * @return Returns a simple definition, level 1.
	 */
	public CplxLevelledDefinition createDefinition1() {
		CplxLevelledDefinition definition = new CplxLevelledDefinition();
		definition.setName(getCnSimplified()+" "+getCnPny2());
		definition.setDescription(getDefinition());
		definition.setLevel(1);
		return definition;
	}
	
	/**
     * Creates a definition chinese ./. English.
     * @return Returns a simple definition, level 3.
     */
	public CplxLevelledDefinition createDefinition2() {
	    CplxLevelledDefinition definition = new CplxLevelledDefinition();
		definition.setName(getCnSimplified());
		definition.setDescription(getDefinition());
		definition.setLevel(2);
		return definition;
	}
	
	/**
     * Creates a definition Chinese ./. Pinyin.
     * @return Returns a simple definition, level 2.
     */
	public CplxLevelledDefinition createDefinition3() {
	    CplxLevelledDefinition definition = new CplxLevelledDefinition();
		definition.setName(getCnSimplified());
		definition.setDescription(getCnPny2());
		definition.setLevel(3);
		return definition;
	}

    public boolean isKangxi() {
        return getDefinition().toLowerCase().contains("(kangxi radical");
    }
}
