package com.ogren.menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Meals {

	private static final String MEALS_FILE = "meals.properties";
	private List<Meal> meals;
	private boolean loaded;

	private static class SingletonHolder {
		private static final Meals INSTANCE = new Meals(new ArrayList<>());
	}

	private Meals(List<Meal> meals) {
		this.meals = meals;
		this.loaded = false;
	}

	public static Meals getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public void addMeal(Meal meal) {
		meals.add(meal);
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Meals load() throws InvalidPropertiesFormatException, IOException {
		meals.clear();
		Properties properties = new Properties();
		try (InputStream inputStream = new FileInputStream(MEALS_FILE)) {
			properties.load(inputStream);
			for (Map.Entry<?, ?> entry : properties.entrySet()) {
				String id = (String) entry.getKey();
				String mealValues = (String) entry.getValue();
				String[] values = mealValues.split(";", -10);
				Meal meal = new Meal(id, values[0], values[1], values[2], values[3]);
				addMeal(meal);
			}
		}
		loaded = true;
		return this;
	}

	public void save() throws InvalidPropertiesFormatException, IOException {
		Properties properties = new Properties();
		try (OutputStream outputStream = new FileOutputStream(MEALS_FILE)) {
			for (Meal meal : getMeals()) {
				properties.setProperty(meal.getId(), meal.save());
			}
			properties.store(outputStream, null);
		}
	}

	public Meal get(String id) throws InvalidPropertiesFormatException, IOException {
		ensureMeals();
		for (Meal meal : meals) {
			if (meal.getId().equals(id)) {
				return meal;
			}
		}
		return null;
	}

	private void ensureMeals() throws InvalidPropertiesFormatException, IOException {
		if (!loaded) {
			load();
		}
	}

}
