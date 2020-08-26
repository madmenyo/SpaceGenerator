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

    private float systemRadius = 400;
    private float symbolRadius = 8;

    private Map<SpaceBody, Float> virtualDistanceMap = new HashMap<>();

    public SystemRenderer(SpaceBody systemRoot) {
        this.systemRoot = systemRoot;

        viewport = new ExtendViewport(systemRadius, systemRadius);
        stage = new Stage(viewport);

        /*
        logNumber(0.16, 4);
        logNumber(16, 4);

        logNumber(SpaceMath.ConvertAuToKm(0.16f), 4);
        logNumber(SpaceMath.ConvertAuToKm(16f), 4);

        logNumber(0.16 / (.08), 2);
        logNumber(16 / (.08), 2);

        System.out.println(Math.log(.16f) / .08f);
        System.out.println(Math.log(16f) / .08f);

         */

        mapScreenDistances(systemRoot);

    }

    private void mapScreenDistances(SpaceBody centerBody){
        float closestBody = getClosestOrbitingBodyDistance(centerBody);
        float logFactor = closestBody / 2;

        List<float> logDistances = new ArrayList<>();
        for (SpaceBody body : centerBody.getChildren()){
            // map from 1 to 1+ with log function
            double logDistance = logNumber(body.getOrbitRadius() / logFactor, 2);
            System.out.println(body.getName() + " : " + logDistance);


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

    @Override
    public void draw() {

    }

    @Override
    public void resize(int width, int height) {


    }

    private double logNumber(double nr, double base){
        double a = Math.log(nr) / Math.log(base);
        System.out.println("Logfunction for " + nr + " is " + a);
        return a;
    }
}
