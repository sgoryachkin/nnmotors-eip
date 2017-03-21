package ru.nnmotors.eip.web.common.util;

import ru.nnmotors.eip.web.ui.pages.HeaderData;

public final class AttributeUtils {

	private AttributeUtils() {

	}

	public static String createNameForClass(Class<?> clazz) {
		return Character.toLowerCase(clazz.getSimpleName().charAt(0)) + HeaderData.class.getSimpleName().substring(1);
	}

}
