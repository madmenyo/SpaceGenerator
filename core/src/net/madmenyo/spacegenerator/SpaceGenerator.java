package net.madmenyo.spacegenerator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGenerator extends Game
{
	
	@Override
	public void create () {
		Star star = new Star("Sol", 0, (float)Math.toRadians(90));
		Planet planet = new Planet("Mercury", 57000000, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Venus", 108000000L, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Earth", 150000000L, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Mars", 228000000L, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Jupiter", 779000000L, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Saturn", 1430000000L, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Uranus", 2880000000L, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Neptune", 4500000000L, (float)Math.toRadians(90));
		star.addChild(planet);



		setScreen(new SystemScreen(star));
	}
}
