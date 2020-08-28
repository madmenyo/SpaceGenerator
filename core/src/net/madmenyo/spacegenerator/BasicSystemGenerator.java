package net.madmenyo.spacegenerator;

import com.badlogic.gdx.math.MathUtils;

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
    }

    public void setSeed(long seed){
        this.seed = seed;
        MathUtils.random.setSeed(seed);
    }

    @Override
    public SpaceBody generateSystem() {
        return null;
    }

    private void generateStar(){
        // Get probabilities

    }

    @Override
    public SpaceBody getLastGeneratedSystem() {
        return null;
    }
}
