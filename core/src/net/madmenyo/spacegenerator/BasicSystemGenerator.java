package net.madmenyo.spacegenerator;

import com.badlogic.gdx.math.MathUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

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

        generateStar();
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

		Star.Type[] typesByPercentage = Star.Type.values();
		Arrays.sort(typesByPercentage, new Comparator<Star.Type>()
		{
			@Override
			public int compare(Star.Type o1, Star.Type o2)
			{
				if (o1.occurancyPercentage < o2.occurancyPercentage) return -1;
				else if (o2.occurancyPercentage < o1.occurancyPercentage) return 1;
				else return 0;
			}
		});

		for (int i = 0; i < 100; i++){
			float total = 0;
			for (Star.Type t : typesByPercentage){
				total += t.occurancyPercentage;
				if (nr <= total){
					System.out.println("Generate: " + t.name() + " star.");
				}
				// else there should be about 1.25 percentage left generate M star
			}
		}

    }

    @Override
    public SpaceBody getLastGeneratedSystem() {
        return null;
    }
}
