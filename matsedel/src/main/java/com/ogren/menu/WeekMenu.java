package com.ogren.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

public class WeekMenu {

	private static final String LS = System.getProperty("line.separator");
	private String id;
	private Map<Weekday, DayMenu> days;

	public WeekMenu(String id) {
		this.id = id;
		this.days = new HashMap<>();
		for (Weekday weekday : Weekday.values()) {
			getDays().put(weekday, new DayMenu(weekday));
		}
	}

	public Map<Weekday, DayMenu> getDays() {
		return days;
	}

	public String save() {
		StringBuilder sb = new StringBuilder();
		for (Weekday weekday : Weekday.values()) {
			if (sb.length() > 0) {
				sb.append("///");
			}
			String daySave = getDays().get(weekday).save();
			sb.append(daySave);
		}
		return sb.toString();
	}

	public WeekMenu load(String value) throws InvalidPropertiesFormatException, IOException {
		String[] values = value.split("///");
		for (String daySave : values) {
			DayMenu dayMenu = DayMenu.load(daySave);
			getDays().put(dayMenu.getWeekday(), dayMenu);
		}
		return this;
	}

	public String getId() {
		return id;
	}

	public List<Meal> getAllMeals() {
		List<Meal> allMeals = new ArrayList<>();
		for (Weekday weekday : Weekday.values()) {
			allMeals.addAll(getDays().get(weekday).getMeals());
		}
		return allMeals;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Week " + id).append(LS);
		sb.append("--------").append(LS);
		for (Weekday weekday : Weekday.values()) {
			sb.append(getDays().get(weekday).toString());
		}
		return sb.toString();
	}

}
