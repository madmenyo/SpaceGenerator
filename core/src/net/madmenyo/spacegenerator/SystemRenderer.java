package net.madmenyo.spacegenerator;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemRenderer implements ISystemRenderer {

    private ShapeRenderer shapeRenderer;

    private SpaceBody systemRoot;

    private Viewport viewport;
    private Stage stage;

    private float farthestBodyDistance = 400;
    private float closestBodyDistance = 24;
    private float bodySymbolRadius = 8;
	private float bodyRange = farthestBodyDistance - closestBodyDistance - bodySymbolRadius;

    private Map<SpaceBody, Float> virtualDistanceMap = new HashMap<>();

    public SystemRenderer(SpaceBody systemRoot) {
        this.systemRoot = systemRoot;

        viewport = new ExtendViewport(farthestBodyDistance, farthestBodyDistance);
        stage = new Stage(viewport);


        //logNumber(0.16, 4);
        //logNumber(16, 4);

        //logNumber(SpaceMath.ConvertAuToKm(0.16f), 4);
        //logNumber(SpaceMath.ConvertAuToKm(16f), 4);

        //logNumber(0.16 / (.08), 2);
        //logNumber(16 / (.08), 2);

        System.out.println("Test near: " + logNumber(.16 / .16, 2));
        System.out.println("Test far : " + logNumber(16f / .16f, 2));

        mapScreenDistances(systemRoot);
    }

	/**
	 * Maps distances for all satelites of given central body
	 * @param centerBody the parent body of the child bodies being mapped.
	 */
	private void mapScreenDistances(SpaceBody centerBody){
		// Keep redundant log factor for now in order to make quick adjustments
		// At the moment just using closest body for division in the logFunction for each distance
        float closestBody = getClosestOrbitingBodyDistance(centerBody);
        float logFactor = closestBody;

        float farthestBody = getFarthestOrbitingBodyDistance(centerBody);
		// Farthest planet should be at near the edge of the system bounds
        float farthestLogNumber = (float)logNumber(farthestBody / logFactor, 2);
        float screenFactor = bodyRange / farthestLogNumber;
		System.out.println(screenFactor);

        for (SpaceBody body : centerBody.getChildren()){
            // map from 1 to 1+ with log function
            float logDistance = (float)logNumber(body.getOrbitRadius() / logFactor, 2);
			System.out.println(body.getName() + " : " + logDistance);
            float screenRadius = (logDistance * screenFactor) + closestBodyDistance;
			System.out.println(body.getName() + " : " + screenRadius);
            virtualDistanceMap.put(body, screenRadius);
        }
    }


    /**
     * Gets the distance of the closes orbiting child of the specified body
     * Used to calculate camera distance/screen size.
     * @param body body to test children on
     * @return orbital radius of closest child
     */
    private long getClosestOrbitingBodyDistance(SpaceBody body){
        long min = Long.MAX_VALUE;
        if (body.getChildren().isEmpty()) return 0;
        for (SpaceBody orbitingChild : body.getChildren()){
            if (orbitingChild.getOrbitRadius() < min) min = orbitingChild.getOrbitRadius();
        }
        return min;
    }

	/**
	 * Gets the distance of the farthest orbiting child of the specified body
	 * Used to calculate camera distance/screen size.
	 * @param body body to test children on
	 * @return orbital radius of farthest child
	 */
	private long getFarthestOrbitingBodyDistance(SpaceBody body){
		long max = 0;
		if (body.getChildren().isEmpty()) return 0;
		for (SpaceBody orbitingChild : body.getChildren()){
			if (orbitingChild.getOrbitRadius() > max) max = orbitingChild.getOrbitRadius();
		}
		return max;
	}

    @Override
    public void draw() {

    }

    @Override
    public void resize(int width, int height) {


    }

    private double logNumber(double nr, double base){
        double a = Math.log(nr) / Math.log(base);
        //System.out.println("Logfunction for " + nr + " is " + a);
        return a;
    }
}
