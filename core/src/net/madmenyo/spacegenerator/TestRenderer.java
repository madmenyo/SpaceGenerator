package net.madmenyo.spacegenerator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javafx.stage.Stage;

/**
 * SpaceGenerator [2020]
 * By Menno Gouw
 */

public class TestRenderer implements ISystemRenderer
{
	private SpaceBody root;

	private SpaceBody currentBody;

	private Viewport viewport;
	private ShapeRenderer shapeRenderer;
	private BitmapFont bitmapFont;
	private SpriteBatch batch;

	private int minWorldWidth = 720, minWorldHeight = 720;


	public TestRenderer(SpaceBody root)
	{
		this.root = root;
		currentBody = root;

		viewport = new ExtendViewport(minWorldWidth, minWorldHeight);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		batch = new SpriteBatch();
		//bitmapFont = new BitmapFont(Gdx.files.internal("gui/default.fnt"));
		bitmapFont = new BitmapFont(Gdx.files.internal("gui/roboto_small.fnt"));

		//calculateScreenDimensions();

	}

	/**
	 * Testing method
	 */
	@Deprecated
	private void calculateScreenDimensions()
	{

		long farthest = getFarthestOrbitingBodyDistance(root);
		long closest = getClosestOrbitingBodyDistance(root);

		float logFarthest = (float)logNumber(farthest / (closest / 2f), 4);
		float logClosest = (float)logNumber(closest / (closest / 2f), 4);

		float factor = (minWorldWidth / 2f) / logFarthest;
		float farPosition = factor * logFarthest;
		float closePosition = factor * logClosest;


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
		for (SpaceBody orbitingChild : root.getChildren()){
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
		if (root.getChildren().isEmpty()) return 0;
		for (SpaceBody orbitingChild : root.getChildren()){
			if (orbitingChild.getOrbitRadius() > max) max = orbitingChild.getOrbitRadius();
		}
		return max;
	}

	@Override
	public void draw()
	{
		Gdx.gl.glClearColor(.02f, .02f, .02f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
		shapeRenderer.begin();
		drawChildBodies(currentBody);
		shapeRenderer.end();
	}

	/**
	 * Uses log function to stretch out children distances of given body within screen space.
	 * @param body body to draw children on
	 */
	private void drawChildBodies(SpaceBody body)
	{
		// Get closest and furthest body to calculate scaling factor
		long farthest = getFarthestOrbitingBodyDistance(body);
		long closest = getClosestOrbitingBodyDistance(body);

		// Log function to spread distances closer together
		float logFarthest = (float)logNumber(farthest / (closest / 1.5f), 4);

		// Calculate factor by using the world width and the farthest body
		float factor = (minWorldWidth / 2f) / logFarthest;

		// Draw orbit line and body representation
		for (SpaceBody b : body.getChildren()){
			float distance = factor * (float)logNumber(b.getOrbitRadius() / (closest / 1.5f), 4);
			shapeRenderer.setColor(Color.DARK_GRAY);
			shapeRenderer.circle(0, 0, distance, 100);

			shapeRenderer.setColor(Color.FOREST);
			Vector2 position = toCarthesian(distance, b.getRotation());
			shapeRenderer.circle(position.x, position.y, 5);
		}

		// Draw name
		bitmapFont.setColor(Color.GRAY);
		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		for (SpaceBody b : body.getChildren()){
			float distance = factor * (float)logNumber(b.getOrbitRadius() / (closest / 1.5f), 4);
			Vector2 position = toCarthesian(distance, b.getRotation());
			bitmapFont.draw(batch, b.getName(), position.x, position.y);
		}
		batch.end();

	}

	@Override
	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}

	/**
	 * Logarithmic function with base
	 * @param number the number, in this case the distance of a space body
	 * @param base the base to divide the Log for more control.
	 * @return The result of Log(n) / Log(b)
	 */
	public double logNumber(double number, double base){
		return Math.log(number) / Math.log(base);
	}

	/**
	 * Converts polar coordinates (distance, rotation) to cartesian (x, y)
	 * @param distance distance from origin
	 * @param rotation rotation from origin
	 * @return cartesian coordinates in Vector2
	 */
	public Vector2 toCarthesian(double distance, double rotation){
		return new Vector2((float)(distance * Math.sin(rotation)), (float)(distance * Math.cos(rotation)));
	}
}
