package com.ogren.menu;

public enum MealType {
	LUNCH("Lunch"), DINNER("Middag");

	private String name;

	MealType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
