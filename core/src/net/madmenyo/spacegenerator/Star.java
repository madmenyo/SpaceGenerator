package net.madmenyo.spacegenerator;

/**
 * SpaceGenerator [2020]
 * By Menno Gouw
 */

public class Star extends SpaceBody
{
	String starType;

	public Star(String name, long orbitRadius, float rotation)
	{
		super(name, orbitRadius, rotation);
	}
}
