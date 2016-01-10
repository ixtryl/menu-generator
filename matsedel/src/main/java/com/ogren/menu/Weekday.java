package com.ogren.menu;

public enum Weekday {

	MONDAY("Måndag"), TUESDAY("Tisdag"), WEDNESDAY("Onsdag"), THURSDAY("Torsdag"), FRIDAY("Fredag"), SATURDAY(
			"Lördag"), SUNDAY("Söndag");

	private String name;

	private Weekday(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
