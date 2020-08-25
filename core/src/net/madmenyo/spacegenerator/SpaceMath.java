package net.madmenyo.spacegenerator;

public class SpaceMath {

    /**
     * Gravitational constant in m3/(kg·s2)
     */
    public static double G = (float)6.67384e-11;


    /**
     * Calculates velocity of a circular orbit
     * @param mass Mass of parent body in kg
     * @param radius Radius from parent body in m
     * @return velocity in m/s
     */
    public static float CalculateVelocity(float mass, float radius){
        return (float)Math.sqrt(( G * mass) / radius );
    }

    /**
     * Calculates
     * @param parentMass Mass of parent body in kg
     * @param orbitalRadius Radius from parent body in m
     * @return
     */
    public static float CalculateOrbitalPeriod(float parentMass, float orbitalRadius){
        return (float)(2 * Math.PI * Math.sqrt(Math.pow(orbitalRadius, 3) / (G * parentMass)));
    }
}
