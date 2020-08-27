package net.madmenyo.spacegenerator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

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
		Gdx.graphics.getGL20().glClearColor( .02f, .02f, .02f, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT |  GL20.GL_DEPTH_BUFFER_BIT );

		systemRenderer.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		systemRenderer.resize(width, height);
	}
}
