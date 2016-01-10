package com.ogren.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class DayMenu {

	private static final String LS = System.getProperty("line.separator");
	private boolean eatLunch = true;
	private boolean eatDinner = true;
	private Meal lunchMeal;
	private Meal dinnerMeal;
	private Weekday weekday;

	public DayMenu(Weekday weekday) {
		this.weekday = weekday;
	}

	public String save() {
		StringBuilder sb = new StringBuilder();
		sb.append(weekday.toString()).append(";");
		sb.append(eatLunch).append(";");
		sb.append(eatDinner).append(";");
		if (lunchMeal != null) {
			sb.append(lunchMeal.getId());
		}
		sb.append(";");
		if (dinnerMeal != null) {
			sb.append(dinnerMeal.getId());
		}
		return sb.toString();
	}

	public static DayMenu load(String value) throws InvalidPropertiesFormatException, IOException {
		String[] values = value.split(";", -10);
		Weekday weekday = Weekday.valueOf(values[0]);
		DayMenu dayMenu = new DayMenu(weekday);
		dayMenu.eatLunch = Boolean.parseBoolean(values[1]);
		dayMenu.eatDinner = Boolean.parseBoolean(values[2]);
		dayMenu.lunchMeal = Meals.getInstance().get(values[3]);
		dayMenu.dinnerMeal = Meals.getInstance().get(values[4]);
		return dayMenu;
	}

	public Weekday getWeekday() {
		return weekday;
	}

	public Meal getLunchMeal() {
		return lunchMeal;
	}

	public void setLunchMeal(Meal lunchMeal) {
		this.lunchMeal = lunchMeal;
	}

	public Meal getDinnerMeal() {
		return dinnerMeal;
	}

	public void setDinnerMeal(Meal dinnerMeal) {
		this.dinnerMeal = dinnerMeal;
	}

	public boolean shallEatLunch() {
		return eatLunch;
	}

	public void setEatLunch(boolean eatLunch) {
		this.eatLunch = eatLunch;
	}

	public boolean shallEatDinner() {
		return eatDinner;
	}

	public void setEatDinner(boolean eatDinner) {
		this.eatDinner = eatDinner;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getWeekday().getName()).append(LS);
		if (shallEatLunch()) {
			sb.append("  ").append(MealType.LUNCH.getName()).append(": ").append(lunchMeal.getName()).append(LS);
		}
		if (shallEatDinner()) {
			sb.append("  ").append(MealType.DINNER.getName()).append(": ").append(dinnerMeal.getName()).append(LS);
		}
		return sb.toString();
	}

	public List<Meal> getMeals() {
		List<Meal> allMeals = new ArrayList<>();
		if (lunchMeal != null) {
			allMeals.add(lunchMeal);
		}
		if (dinnerMeal != null) {
			allMeals.add(dinnerMeal);
		}
		return allMeals;
	}

}
