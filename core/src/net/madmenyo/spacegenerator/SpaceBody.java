package net.madmenyo.spacegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * SpaceGenerator [2020]
 * By Menno Gouw
 *
 * A base class for all celestial bodies from asteroids, to moons, planets and stars.
 * Except for a star which is the origin and root of a system all others should have a parent to
 * orbit around. The orbit is relative
 */

public abstract class SpaceBody
{
	private String name;
	public String getName()
	{
		return name;
	}

	protected String bodyType;
	public String getBodyType() {
		return bodyType;
	}

	/**
	 * Orbit radius in km
	 */
	private long orbitRadius;
	public long getOrbitRadius()
	{
		return orbitRadius;
	}

	/**
	 * Rotation around origin 0,0 in radians
	 */
	private float rotation;
	public float getRotation()
	{
		return rotation;
	}

	/**
	 * The parent or in other words origin this body orbits
	 */
	private SpaceBody parent;
	public SpaceBody getParent()
	{
		return parent;
	}

	private List<SpaceBody> children = new ArrayList<>();
	public List<SpaceBody> getChildren()
	{
		return children;
	}

	public SpaceBody(String name, long orbitRadius, float rotation)
	{
		this.name = name;
		this.orbitRadius = orbitRadius;
		this.rotation = rotation;
	}

	public SpaceBody(String name, long orbitRadius, float rotation, SpaceBody parent)
	{
		this.name = name;
		this.orbitRadius = orbitRadius;
		this.rotation = rotation;
		this.parent = parent;
	}

	/**
	 * Adds a body to the list of children of this space body.
	 * @param body the child
	 */
	public void addChild(SpaceBody body){
		// Can only have one parent and thus should be this.
		body.parent = this;
		children.add(body);
	}

	public void update(float delta) {
		if (parent == null) return;
		float periodInSeconds = SpaceMath.CalculateOrbitalPeriodInSeconds((float)parent.getMassInKg(), orbitRadius * 1000);
		//float dayMultiplier = delta * 60 * 60 * 24;
		float timePassPerFrame = 1 * 60 * 60 * 24;

		//float rotationInDegrees = 360 * (dayMultiplier / periodInSeconds);
		float rotationInDegrees = 360 * (timePassPerFrame / periodInSeconds);
		//System.out.println(periodInSeconds);

		//System.out.println(rotationInDegrees);
		rotation += Math.toRadians(rotationInDegrees);

	}

	public abstract double getMassInKg();
}
