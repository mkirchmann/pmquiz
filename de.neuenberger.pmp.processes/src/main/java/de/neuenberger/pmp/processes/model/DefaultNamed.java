package de.neuenberger.pmp.processes.model;

public class DefaultNamed implements Named {
	private final String name;
	
	public DefaultNamed(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
