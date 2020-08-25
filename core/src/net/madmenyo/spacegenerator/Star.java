package net.madmenyo.spacegenerator;

/**
 * SpaceGenerator [2020]
 * By Menno Gouw
 */

public class Star extends SpaceBody
{
	//public static double SOLAR_MASS_KG = 1.98847e30;
	public static double SOLAR_MASS_KG = 1.9891e30;
	public static float SOLAR_RADIUS_KM = 696340;
	public static float SOLAR_LUMINOSITY_W = (float)3.828e26;

	private String spectralType;

	/**
	 * Temperature in Kelvin
	 */
	private int temperature;

	/**
	 * Radius in suns
	 */
	private float radius;

	/**
	 * Mass in suns
	 */
	private float mass;

	/**
	 * Luminosity in suns
	 */
	private float lumonosity;


	public Star(String name, long orbitRadius, float rotation)
	{
		super(name, orbitRadius, rotation);
	}

	public Star(String name, long orbitRadius, float rotation, float mass) {
		super(name, orbitRadius, rotation);
		this.mass = mass;
	}

	public float getMassInKilogram(){
		return (float)(mass * SOLAR_MASS_KG);
	}
}
