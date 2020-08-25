package net.madmenyo.spacegenerator;

import com.badlogic.gdx.Game;

public class SpaceGenerator extends Game
{
	
	@Override
	public void create () {
		testOrbitalEquations();

		// Create solar system by hand and load system screen with it
		Star star = createSolarSystem();
		setScreen(new SystemScreen(star));
	}

	/**
	 * Test for orbital equations using the earth and the sun.
	 */
	private void testOrbitalEquations() {
		Star sun = new Star("Sol", 0, 0, 1);
		Planet earth = new Planet("Earth", 149600000L, (float)Math.toRadians(90), "Terrestrial", 1f);

		float orbitalPeriodInSeconds = SpaceMath.CalculateOrbitalPeriod(sun.getMassInKilogram(), earth.getOrbitRadius() * 1000);
		float orbitalPeriodInDays = orbitalPeriodInSeconds / 60 / 60 / 24;

		System.out.println(orbitalPeriodInDays);
	}

	/**
	 * Creates the solar system with hardcoded numbers
	 * Used for testing
	 * @return the solar system with all it's planets
	 */
	private Star createSolarSystem() {
		Star star = new Star("Sol", 0, (float)Math.toRadians(90));
		Planet planet = new Planet("Mercury", 57000000, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Venus", 108000000L, (float)Math.toRadians(90));
		star.addChild(planet);
		planet = new Planet("Earth", 149600000L, (float)Math.toRadians(90));
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
		return star;
	}
}
