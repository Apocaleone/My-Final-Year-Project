package com.themetanoia.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.AdHandler;

public class DesktopLauncher implements AdHandler {

	public static AdHandler handler;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		caller(config);
	}

	public static void caller(LwjglApplicationConfiguration config){
		new LwjglApplication(new Lone_Warrior1(handler), config);
	}

	@Override
	public void showAds(boolean show) {

	}

	@Override
	public void showOrLoadInterstital() {

	}
}
