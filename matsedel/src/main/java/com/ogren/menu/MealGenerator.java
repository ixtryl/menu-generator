package com.ogren.menu;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

public class MealGenerator {

	public Meals generate() {
		Meals.getInstance().addMeal(new Meal("1", "Stekt torsk m mos", MealCategory.FISH));
		Meals.getInstance().addMeal(new Meal("2", "Stekt torsk m hollandaissås", MealCategory.FISH));
		Meals.getInstance().addMeal(new Meal("3", "Stekt torsk m pepparrotssås", MealCategory.FISH));
		Meals.getInstance().addMeal(new Meal("4", "Stekt lax m mos", MealCategory.FISH));
		Meals.getInstance().addMeal(new Meal("5", "Stekt lax m pasta och kall sås", MealCategory.FISH));
		Meals.getInstance().addMeal(new Meal("6", "Fiskpinnar m mos", MealCategory.FISH));

		Meals.getInstance().addMeal(new Meal("7", "Falukorv m senapssås", MealCategory.SAUSAGE));
		Meals.getInstance().addMeal(new Meal("8", "Falukorv m vita bönor", MealCategory.SAUSAGE));
		Meals.getInstance().addMeal(new Meal("9", "Falukorv m mos", MealCategory.SAUSAGE));
		Meals.getInstance().addMeal(new Meal("10", "Korv Stroganoff", MealCategory.SAUSAGE));
		Meals.getInstance()
				.addMeal(new Meal("11", "Korv i bröd", MealCategory.SAUSAGE,
						new Weekday[] { Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY },
						new MealType[] { MealType.DINNER }));

		Meals.getInstance().addMeal(new Meal("12", "Curts kött", MealCategory.MEAT,
				new Weekday[] { Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY }));
		Meals.getInstance().addMeal(new Meal("13", "Kasslergratäng", MealCategory.MEAT));
		Meals.getInstance().addMeal(new Meal("14", "Pytt i panna", MealCategory.MEAT));
		Meals.getInstance().addMeal(new Meal("15", "Skinkpaj", MealCategory.MEAT));
		Meals.getInstance().addMeal(new Meal("16", "Fläskfile i ugn med sparris och pås­sås", MealCategory.MEAT));
		Meals.getInstance().addMeal(new Meal("17", "Fläskfile i ugn m banan och curry", MealCategory.MEAT));
		Meals.getInstance().addMeal(new Meal("18", "Pastagratäng m skinka", MealCategory.MEAT));
		Meals.getInstance().addMeal(new Meal("19", "Senapsfylld lövbiff m rösti", MealCategory.MEAT,
				new Weekday[] { Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY }));
		Meals.getInstance().addMeal(new Meal("20", "Pasta m skinka, spenat och örter", MealCategory.MEAT));

		Meals.getInstance()
				.addMeal(new Meal("21", "Tacos", MealCategory.MINCED_MEAT,
						new Weekday[] { Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY },
						new MealType[] { MealType.DINNER }));
		Meals.getInstance().addMeal(new Meal("22", "Lasagne", MealCategory.MINCED_MEAT));
		Meals.getInstance().addMeal(new Meal("23", "Köttfärssås", MealCategory.MINCED_MEAT));
		Meals.getInstance().addMeal(new Meal("24", "Köttbullar", MealCategory.MINCED_MEAT));
		Meals.getInstance().addMeal(new Meal("25", "Köttfärspaj", MealCategory.MINCED_MEAT));
		Meals.getInstance().addMeal(new Meal("26", "Hamburgare", MealCategory.MINCED_MEAT,
				new Weekday[] { Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY }));
		Meals.getInstance()
				.addMeal(new Meal("27", "Gulaschsoppa", MealCategory.MINCED_MEAT, new MealType[] { MealType.DINNER }));
		Meals.getInstance().addMeal(new Meal("28", "Chili con carne", MealCategory.MINCED_MEAT));

		Meals.getInstance().addMeal(new Meal("29", "Kyckling m rotfrukter i ugn", MealCategory.CHICKEN));
		Meals.getInstance().addMeal(new Meal("30", "Kycklingwook", MealCategory.CHICKEN));
		Meals.getInstance()
				.addMeal(new Meal("31", "Kycklingwraps", MealCategory.CHICKEN, new MealType[] { MealType.DINNER }));
		Meals.getInstance().addMeal(new Meal("32", "Kycklingpaj", MealCategory.CHICKEN));
		Meals.getInstance().addMeal(new Meal("33", "Grillad kyckling", MealCategory.CHICKEN,
				new Weekday[] { Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY }));
		Meals.getInstance().addMeal(new Meal("34", "Flygande Jacob", MealCategory.CHICKEN));
		Meals.getInstance().addMeal(new Meal("35", "Pastasås med kyckling och curry", MealCategory.CHICKEN));

		Meals.getInstance().addMeal(new Meal("36", "Fläskpannkaka", MealCategory.OTHER));
		Meals.getInstance()
				.addMeal(new Meal("37", "Pannkakor", MealCategory.OTHER, new MealType[] { MealType.DINNER }));
		Meals.getInstance()
				.addMeal(new Meal("38", "Varma mackor", MealCategory.OTHER,
						new Weekday[] { Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY },
						new MealType[] { MealType.DINNER }));
		Meals.getInstance().addMeal(new Meal("39", "Blodpudding", MealCategory.OTHER));
		Meals.getInstance().addMeal(new Meal("40", "Potatisbullar", MealCategory.OTHER));
		Meals.getInstance().addMeal(new Meal("41", "Ärtsoppa", MealCategory.OTHER, new MealType[] { MealType.DINNER }));
		Meals.getInstance().addMeal(new Meal("42", "Piroger", MealCategory.OTHER, new MealType[] { MealType.DINNER }));

		return Meals.getInstance();
	}

	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
		new MealGenerator().generate().save();
	}

}
