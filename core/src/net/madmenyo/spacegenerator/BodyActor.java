package net.madmenyo.spacegenerator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BodyActor extends Button {
    private SpaceBody body;
    private Label textLabel;

    public BodyActor(Skin skin, SpaceBody body) {
        super(skin);
        this.body = body;

        textLabel = new Label(body.getName(), skin);

        setDebug(true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }
}
