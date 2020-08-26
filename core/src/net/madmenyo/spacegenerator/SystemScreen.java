package net.madmenyo.spacegenerator;

import com.badlogic.gdx.ScreenAdapter;

/**
 * SpaceGenerator [2020]
 * By Menno Gouw
 */

public class SystemScreen extends ScreenAdapter
{
	/**
	 * Likely a star, but perhaps ever need a binary star or something else as the root
	 */
	private SpaceBody systemRoot;

	private ISystemRenderer systemRenderer;

	public SystemScreen(SpaceBody systemRoot)
	{
		this.systemRoot = systemRoot;

		systemRenderer = new SystemRenderer(systemRoot);
	}

	@Override
	public void show()
	{
		super.show();
	}

	@Override
	public void render(float delta)
	{
		systemRenderer.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		systemRenderer.resize(width, height);
	}
}
