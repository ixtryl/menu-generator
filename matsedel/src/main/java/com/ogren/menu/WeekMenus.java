package com.ogren.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

public class WeekMenus {

	private static final String WEEK_MENUS_FILE = "weekMenus.properites";
	private static final String KEY_MENU_COUNT = "menu.count";
	private static final String LS = System.getProperty("line.separator");

	private List<WeekMenu> weekMenus;
	private boolean loaded;

	private static class SingletonHolder {
		private static final WeekMenus INSTANCE = new WeekMenus();
	}

	private WeekMenus() {
		loaded = false;
		weekMenus = new ArrayList<>();
	}

	public static WeekMenus getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public void addWeekMenu(WeekMenu weekMenu) {
		weekMenus.add(weekMenu);
	}

	public WeekMenus load() throws InvalidPropertiesFormatException, IOException {
		weekMenus.clear();
		Properties properties = new Properties();
		if (new File(WEEK_MENUS_FILE).exists()) {
			try (InputStream inputStream = new FileInputStream(WEEK_MENUS_FILE)) {
				properties.load(inputStream);
				int menuCount = Integer.parseInt((String) properties.get(KEY_MENU_COUNT));
				for (int weekId = 0; weekId < menuCount; weekId++) {
					String weekValues = (String) properties.get(String.valueOf(weekId));
					weekMenus.add(new WeekMenu(String.valueOf(weekId)).load(weekValues));
				}
			}
		}
		loaded = true;
		return this;
	}

	public void save() throws InvalidPropertiesFormatException, IOException {
		if (!loaded) {
			throw new IllegalArgumentException("Must have loaded menus before saving them!");
		}
		Properties properties = new Properties();
		try (OutputStream outputStream = new FileOutputStream(WEEK_MENUS_FILE)) {
			properties.put(KEY_MENU_COUNT, String.valueOf(weekMenus.size()));
			for (WeekMenu weekMenu : weekMenus) {
				properties.put(weekMenu.getId(), weekMenu.save());
			}
			properties.store(outputStream, null);
		}
	}

	public WeekMenu getLastWeek() throws InvalidPropertiesFormatException, IOException {
		ensureWeekMenus();
		if (weekMenus.size() > 0) {
			return weekMenus.get(weekMenus.size() - 1);
		}
		return null;
	}

	private void ensureWeekMenus() throws InvalidPropertiesFormatException, IOException {
		if (!loaded) {
			load();
		}
	}

	public WeekMenu getNewWeek() throws InvalidPropertiesFormatException, IOException {
		ensureWeekMenus();
		return new WeekMenu(String.valueOf(weekMenus.size()));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Week Menus").append(LS);
		sb.append("==========").append(LS);
		for (WeekMenu weekMenu : weekMenus) {
			sb.append(weekMenu.toString()).append(LS);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
		System.out.println(WeekMenus.getInstance().load().toString());
	}

}
