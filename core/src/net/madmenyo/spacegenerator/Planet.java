package net.madmenyo.spacegenerator;

/**
 * SpaceGenerator [2020]
 * By Menno Gouw
 */

public class Planet extends SpaceBody
{

	public static double EARTH_RADIUS_KM = 6371;
	//public static double EARTH_MASS_KG = (float)5972.37e24;
	public static double EARTH_MASS_KG = (float)5972e24;

	/**
	 * Rocky, Oceanic, Gas Giant/Dwarf, ice planet/giant, etc?
	 */
	private String type;

	/**
	 * Mass in earths
	 */
	private float mass;

	public Planet(String name, long orbitRadius, float rotation)
	{
		super(name, orbitRadius, rotation);
	}

	public Planet(String name, long orbitRadius, float rotation, String type, float mass) {
		super(name, orbitRadius, rotation);
		this.type = type;
		this.mass = mass;
	}

	public double getMassInKilogram(){
		return mass * EARTH_MASS_KG;
	}


}
