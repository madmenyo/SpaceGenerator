package net.madmenyo.spacegenerator;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

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
		O(0.00003f, 30000, 50000, new Color(.1f, .35f, .95f, 1)),
		B(0.125f, 10000, 30000, new Color(.2f, .45f, .95f, 1)),
		A(0.6f, 7500, 10000, new Color(.3f, .6f, .9f, 1)),
		F(3f, 6000, 7500, new Color(.55f, .8f, .8f, 1)),
		G(7.6f, 5200, 6000, new Color(.9f, .9f, .8f, 1)),
		K(12.1f, 3700, 5200, new Color(.95f, .7f, .4f, 1)),
		M(76.45f, 2400, 3700, new Color(.95f, .5f, .2f, 1));

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

		public String getType(int temperature){
			if (temperature < minTemperature || temperature > maxTemperature){
				throw new IllegalArgumentException("Temperature should be within min and max of this type. Provided + "
						+ temperature + "K and " + name() + " should be between " + minTemperature + "K and " + maxTemperature + "K.");
			}
			// Get classification from 0 to 9 by lerping the temperature between min and max
			float lerp = MathUtils.lerp(minTemperature, maxTemperature, temperature);
			// Correct for top value
			if (lerp == 1) lerp = .9f;
			int classification = (int)lerp * 10;

			return name() + classification;

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
