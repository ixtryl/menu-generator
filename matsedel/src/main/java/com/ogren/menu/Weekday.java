package com.ogren.menu;

public enum Weekday {

	MONDAY("M�ndag"), TUESDAY("Tisdag"), WEDNESDAY("Onsdag"), THURSDAY("Torsdag"), FRIDAY("Fredag"), SATURDAY(
			"L�rdag"), SUNDAY("S�ndag");

	private String name;

	private Weekday(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
