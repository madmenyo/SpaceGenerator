package net.madmenyo.spacegenerator;

import com.badlogic.gdx.Game;

public class SpaceGenerator extends Game
{
	
	@Override
	public void create () {
		testOrbitalEquations();

		new BasicSystemGenerator();

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

		//float orbitalPeriodInSeconds = SpaceMath.CalculateOrbitalPeriodInSeconds(sun.getMassInKilogram(), earth.getOrbitRadius() * 1000);
		//float orbitalPeriodInDays = orbitalPeriodInSeconds / 60 / 60 / 24;

		System.out.println(SpaceMath.CalculateOrbitalPeriodInYears(sun.getMassInKilogram(), earth.getOrbitRadius() * 1000));
	}

	/**
	 * Creates the solar system with hardcoded numbers
	 * Used for testing
	 * @return the solar system with all it's planets
	 */
	private Star createSolarSystem() {
		Star star = new Star("Sol", 0, (float)Math.toRadians(90), 1);
		Planet planet = new Planet("Mercury", 57000000, (float)Math.toRadians(90), "Rocky", 0.0553f);
		star.addChild(planet);
		planet = new Planet("Venus", 108000000L, (float)Math.toRadians(90), "Rocky", 0.815f);
		star.addChild(planet);
		planet = new Planet("Earth", 149600000L, (float)Math.toRadians(90), "Terrestrial", 1);
		star.addChild(planet);
		planet = new Planet("Mars", 228000000L, (float)Math.toRadians(90), "Rocky", 0.11f);
		star.addChild(planet);
		planet = new Planet("Jupiter", 779000000L, (float)Math.toRadians(90), "Gas Giant", 317.8f);
		star.addChild(planet);
		planet = new Planet("Saturn", 1430000000L, (float)Math.toRadians(90), "Gas Giant", 95.2f);
		star.addChild(planet);
		planet = new Planet("Uranus", 2880000000L, (float)Math.toRadians(90), "Ice Giant", 14.6f);
		star.addChild(planet);
		planet = new Planet("Neptune", 4500000000L, (float)Math.toRadians(90), "Ice Giant", 17.2f);
		star.addChild(planet);
		return star;
	}
}
