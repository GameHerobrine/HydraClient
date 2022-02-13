package com.oldschoolminecraft.client.util;

import java.awt.Color;

public class Utils {
	public static int rainbow() {
		return Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0f, 0.8f, 0.8f);
	}
}
