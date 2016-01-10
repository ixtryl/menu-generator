package com.ogren.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Meal implements Comparable<Meal> {

	private String id;
	private String name;
	private MealCategory mealCategory;
	private List<Weekday> allowedDays;
	private List<MealType> allowedMealType;

	public Meal(String id, String name, MealCategory mealCategory) {
		this(id, name, mealCategory, new Weekday[0]);
	}

	public Meal(String id, String name, MealCategory mealCategory, Weekday[] allowedDays) {
		this(id, name, mealCategory, allowedDays, new MealType[0]);
	}

	public Meal(String id, String name, MealCategory mealCategory, MealType[] allowedMealType) {
		this(id, name, mealCategory, new Weekday[0], allowedMealType);
	}

	public Meal(String id, String name, MealCategory mealCategory, Weekday[] allowedDays, MealType[] allowedMealType) {
		this.id = id;
		this.name = name;
		this.mealCategory = mealCategory;
		this.allowedDays = Arrays.asList(allowedDays);
		this.allowedMealType = Arrays.asList(allowedMealType);
	}

	public Meal(String id, String name, String mealCategory, String allowedDays, String allowedMealTypes) {
		this.id = id;
		this.name = name;
		this.mealCategory = MealCategory.valueOf(mealCategory);
		this.allowedDays = getAllowedDaysAsList(allowedDays);
		this.allowedMealType = getAllowedMealTypesAsList(allowedMealTypes);
	}

	private List<MealType> getAllowedMealTypesAsList(String allowedMealTypes) {
		List<MealType> allowedMealTypeList = new ArrayList<>();
		for (String allowedMealType : allowedMealTypes.split(",")) {
			if (!allowedMealType.isEmpty()) {
				allowedMealTypeList.add(MealType.valueOf(allowedMealType));
			}
		}
		return allowedMealTypeList;
	}

	private List<Weekday> getAllowedDaysAsList(String allowedDays) {
		List<Weekday> allowedDaysList = new ArrayList<>();
		for (String allowedDay : allowedDays.split(",")) {
			if (!allowedDay.isEmpty()) {
				allowedDaysList.add(Weekday.valueOf(allowedDay));
			}
		}
		return allowedDaysList;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public MealCategory getMealCategory() {
		return mealCategory;
	}

	public void setMealCategory(MealCategory mealCategory) {
		this.mealCategory = mealCategory;
	}

	public List<Weekday> getAllowedDays() {
		return allowedDays;
	}

	public void setAllowedDays(List<Weekday> allowedDays) {
		this.allowedDays = allowedDays;
	}

	public List<MealType> getAllowedMealType() {
		return allowedMealType;
	}

	public void setAllowedMealType(List<MealType> allowedMealType) {
		this.allowedMealType = allowedMealType;
	}

	public String save() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(";");
		sb.append(getMealCategory().toString()).append(";");

		StringBuilder sbWeekday = new StringBuilder();
		for (Weekday weekday : allowedDays) {
			if (sbWeekday.length() > 0) {
				sbWeekday.append(",");
			}
			sbWeekday.append(weekday.toString());
		}
		sb.append(sbWeekday.toString()).append(";");

		StringBuilder sbType = new StringBuilder();
		for (MealType mealType : allowedMealType) {
			if (sbType.length() > 0) {
				sbType.append(",");
			}
			sbType.append(mealType.toString());
		}
		sb.append(sbType.toString()).append(";");
		return sb.toString();
	}

	@Override
	public int compareTo(Meal o) {
		return id.compareTo(o.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Meal other = (Meal) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
