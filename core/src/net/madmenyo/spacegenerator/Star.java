package net.madmenyo.spacegenerator;

import java.awt.Color;

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

	public enum Type{
		O(0.00003f, 30000, 50000, Color.BLUE),
		B(0.125f, 10000, 30000, Color.),
		A(0.00003f, 30000, 50000, Color.BLUE),
		F(0.00003f, 30000, 50000, Color.BLUE),
		G(0.00003f, 30000, 50000, Color.BLUE),
		K(0.00003f, 30000, 50000, Color.BLUE),
		M(0.00003f, 30000, 50000, Color.BLUE);

		float occurancyPercentage;
		float minTemperature;
		float maxTemperature;
		Color color;

		Type(float occurancyPercentage, float minTemperature,
			 float maxTemperature, Color color) {
			this.occurancyPercentage = occurancyPercentage;
			this.minTemperature = minTemperature;
			this.maxTemperature = maxTemperature;
			this.color = color;
		}
	}

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
	private float solarMass;

	/**
	 * Luminosity in suns
	 */
	private float lumonosity;

	public Star(String name, long orbitRadius, float rotation, float solarMass) {
		super(name, orbitRadius, rotation);
		this.solarMass = solarMass;
	}

	public float getMassInKilogram(){
		return (float)(solarMass * SOLAR_MASS_KG);
	}

	@Override
	public double getMassInKg() {
		return solarMass * SOLAR_MASS_KG;
	}
}
