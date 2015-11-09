package de.neuenberger.pmp.processes;

import generated.CplxDefinition;

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

	public CplxDefinition createDefinition1() {
		CplxDefinition definition = new CplxDefinition();
		definition.setName(getCnSimplified()+" "+getCnPny2());
		definition.setDescription(getDefinition());
		return definition;
	}
	
	public CplxDefinition createDefinition2() {
		CplxDefinition definition = new CplxDefinition();
		definition.setName(getCnSimplified());
		definition.setDescription(getDefinition());
		return definition;
	}
	
	public CplxDefinition createDefinition3() {
		CplxDefinition definition = new CplxDefinition();
		definition.setName(getCnSimplified());
		definition.setDescription(getCnPny2());
		return definition;
	}
}
