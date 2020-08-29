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

	public enum TypeRealism
	{
		O(0.00003f, new Color(.1f, .35f, .95f, 1),
				30000, 50000,
				16, 20,
				6.6f,10f),
		B(0.125f, new Color(.2f, .45f, .95f, 1),
				10000, 30000,
				2.1f, 16,
				1.8f,6.6f),
		A(0.6f, new Color(.3f, .6f, .9f, 1),
				7500, 10000,
				1.4f, 2.1f,
				1.4f,1.8f),
		F(3f, new Color(.55f, .8f, .8f, 1),
				6000, 7500,
				1.04f, 1.4f,
				1.15f,1.8f),
		G(7.6f, new Color(.9f, .9f, .8f, 1),
				5200, 6000,
				0.8f, 1.04f,
				0.96f,1.15f),
		K(12.1f, new Color(.95f, .7f, .4f, 1),
				3700, 5200,
				0.45f, 0.8f,
				0.7f,0.96f),
		M(76.45f, new Color(.95f, .5f, .2f, 1),
				2400, 3700,
				0.08f,0.45f,
				0.7f,0.45f
				);

		final float occurancyPercentage;
		final float minTemperature;
		final float maxTemperature;
		final float minMass;
		final float maxMass;
		final float minRadius;
		final float maxRadius;
		final Color color;

		TypeRealism(float occurancyPercentage, Color color,
					float minTemperature, float maxTemperature,
					float minMass, float maxMass,
					float minRadius, float maxRadius) {
			this.occurancyPercentage = occurancyPercentage;
			this.color = color;
			this.minTemperature = minTemperature;
			this.maxTemperature = maxTemperature;
			this.minMass = minMass;
			this.maxMass = maxMass;
			this.minRadius = minRadius;
			this.maxRadius = maxRadius;
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

	private TypeRealism typeRealism;

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
