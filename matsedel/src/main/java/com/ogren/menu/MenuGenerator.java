package com.ogren.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MenuGenerator {

	public MenuGenerator() {
	}

	public WeekMenu generate(WeekMenu weekMenu) throws InvalidPropertiesFormatException, IOException {
		Meals meals = Meals.getInstance().load();
		WeekMenu lastWeek = WeekMenus.getInstance().getLastWeek();
		List<Meal> allowedMeals = new ArrayList<>(meals.getMeals());

		DayMenu lastDayMenu = null;
		if (lastWeek != null) {
			lastDayMenu = lastWeek.getDays().get(Weekday.SUNDAY);
			removeLastWeek(lastWeek, allowedMeals);
		}

		for (Weekday weekday : Weekday.values()) {
			List<Meal> allowedDayMeals = new ArrayList<>(allowedMeals);
			removeSpecificWeekdayMeals(allowedDayMeals, weekday);

			List<MealCategory> forbiddenCategories = new ArrayList<>();
			if (lastDayMenu != null) {
				for (Meal meal : lastDayMenu.getMeals()) {
					forbiddenCategories.add(meal.getMealCategory());
				}
			}
			removeMealCategories(allowedDayMeals, forbiddenCategories);

			DayMenu dayMenu = weekMenu.getDays().get(weekday);
			if (dayMenu.shallEatLunch()) {
				List<Meal> lunchMeals = new ArrayList<>(allowedDayMeals);
				removeOtherMealTypes(lunchMeals, MealType.LUNCH);
				Meal lunchMeal = getRandomMeal(lunchMeals);
				dayMenu.setLunchMeal(lunchMeal);
				removeMeal(allowedDayMeals, lunchMeal);
				removeMeal(allowedMeals, lunchMeal);
				removeMealCategories(allowedDayMeals, lunchMeal.getMealCategory());
			}
			if (dayMenu.shallEatDinner()) {
				List<Meal> dinnerMeals = new ArrayList<>(allowedDayMeals);
				removeOtherMealTypes(dinnerMeals, MealType.DINNER);
				Meal dinnerMeal = getRandomMeal(dinnerMeals);
				dayMenu.setDinnerMeal(dinnerMeal);
				removeMeal(allowedMeals, dinnerMeal);
			}
			lastDayMenu = dayMenu;
		}
		return weekMenu;
	}

	private void removeMeal(List<Meal> meals, Meal meal) {
		meals.remove(meal);
	}

	private Meal getRandomMeal(List<Meal> meals) {
		int index = ThreadLocalRandom.current().nextInt(meals.size());
		return meals.get(index);
	}

	private void removeOtherMealTypes(List<Meal> meals, MealType mealType) {
		List<Meal> mealsToRemove = new ArrayList<>();
		for (Meal meal : meals) {
			if (meal.getAllowedMealType().size() > 0 && !meal.getAllowedMealType().contains(mealType)) {
				mealsToRemove.add(meal);
			}
		}
		meals.removeAll(mealsToRemove);
	}

	private void removeSpecificWeekdayMeals(List<Meal> meals, Weekday weekday) {
		List<Meal> mealsToRemove = new ArrayList<>();
		for (Meal meal : meals) {
			if (meal.getAllowedDays().size() > 0 && !meal.getAllowedDays().contains(weekday)) {
				mealsToRemove.add(meal);
			}
		}
		meals.removeAll(mealsToRemove);
	}

	private void removeMealCategories(List<Meal> meals, List<MealCategory> forbiddenCategories) {
		List<Meal> mealsToRemove = new ArrayList<>();
		for (Meal meal : meals) {
			if (forbiddenCategories.contains(meal.getMealCategory())) {
				mealsToRemove.add(meal);
			}
		}
		meals.removeAll(mealsToRemove);
	}

	private void removeMealCategories(List<Meal> meals, MealCategory mealCategory) {
		List<Meal> mealsToRemove = new ArrayList<>();
		for (Meal meal : meals) {
			if (mealCategory == meal.getMealCategory()) {
				mealsToRemove.add(meal);
			}
		}
		meals.removeAll(mealsToRemove);
	}

	private void removeLastWeek(WeekMenu lastWeek, List<Meal> allowedMeals) {
		List<Meal> lastWeekMeals = lastWeek.getAllMeals();
		allowedMeals.removeAll(lastWeekMeals);
	}

	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
		WeekMenu weekMenu = WeekMenus.getInstance().getNewWeek();
		weekMenu.getDays().get(Weekday.MONDAY).setEatLunch(false);
		weekMenu.getDays().get(Weekday.TUESDAY).setEatLunch(false);
		weekMenu.getDays().get(Weekday.WEDNESDAY).setEatLunch(false);
		weekMenu.getDays().get(Weekday.THURSDAY).setEatLunch(false);
		weekMenu.getDays().get(Weekday.FRIDAY).setEatLunch(false);
		new MenuGenerator().generate(weekMenu);
		WeekMenus.getInstance().addWeekMenu(weekMenu);
		WeekMenus.getInstance().save();
	}

}
