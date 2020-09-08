package net.madmenyo.spacegenerator;

import com.badlogic.gdx.math.MathUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BasicSystemGenerator implements ISystemGenerator {

    private long seed;
    public long getSeed() {
        return seed;
    }

    public BasicSystemGenerator() {
        this(MathUtils.random(Long.MAX_VALUE));
    }

    public BasicSystemGenerator(long seed) {
        setSeed(seed);

        gamifyProbability();
    }

    public void setSeed(long seed){
        this.seed = seed;
        MathUtils.random.setSeed(seed);
    }

    @Override
    public SpaceBody generateSystem() {
        return null;
    }

	/**
	 * Generates star base don probability.
	 * However the real probabilty makes it very unlikely for certain stars to be generated.
	 * This needs to be altered for different needs then a real star galaxy.
	 */
	private void generateStar(){
        // Get probabilities
		float nr = MathUtils.random.nextFloat() * 100;

		Star.TypeRealism[] typesByPercentage = Star.TypeRealism.values();
		Arrays.sort(typesByPercentage, new Comparator<Star.TypeRealism>()
		{
			@Override
			public int compare(Star.TypeRealism o1, Star.TypeRealism o2)
			{
				if (o1.occurancyPercentage < o2.occurancyPercentage) return -1;
				else if (o2.occurancyPercentage < o1.occurancyPercentage) return 1;
				else return 0;
			}
		});

		for (int i = 0; i < 100; i++){
			float total = 0;
			for (Star.TypeRealism t : typesByPercentage){
				total += t.occurancyPercentage;
				if (nr <= total){
					System.out.println("Generate: " + t.name() + " star.");
				}
				// else there should be about 1.25 percentage left generate M star
			}
		}
    }

	/**
	 * Returns a list with star probabilities that are more game viable. Realistic probability of a
	 * O type star is 1 : 3.300.000
	 * @return
	 */
	private List<Star.TypeRealism> gamifyProbability(){
		float oldMin = 0.00006f;
		float oldMax = 75;

		float newMin = 5;
		float newMax = 100;

		float oldRange = oldMax - oldMin;
		float newRange = newMax - newMin;

		float oldValue = oldMin;
		float newValue = (((oldValue - oldMin) * newRange) / oldRange) + newMin;

		System.out.println(newValue);

		oldValue = .6f;
		newValue = (((oldValue - oldMin) * newRange) / oldRange) + newMin;
		System.out.println(newValue);

		return null;

	}

	/**
	 * Changes a number in a old range to the value of the given new range
	 * @param oldMin old min range
	 * @param oldMax old max range
	 * @param newMin new min range
	 * @param newMax new max range
	 * @param oldValue the value to change
	 * @return the number in positioned relatively in the new range
	 */
	private float changeNumberRange(float oldMin, float oldMax, float newMin, float newMax, float oldValue){
		float oldRange = oldMax - oldMin;
		float newRange = newMax - newMin;
		return  (((oldValue - oldMin) * newRange) / oldRange) + newMin;

	}

    @Override
    public SpaceBody getLastGeneratedSystem() {
        return null;
    }
}
