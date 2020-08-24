package net.madmenyo.spacegenerator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.madmenyo.spacegenerator.SpaceGenerator;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = false;
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new SpaceGenerator(), config);
	}
}
