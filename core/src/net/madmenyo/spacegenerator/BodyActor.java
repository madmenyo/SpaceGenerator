package net.madmenyo.spacegenerator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class BodyActor extends Button {
    private SpaceBody body;
    private Label textLabel;

    private float virtualScreenDistance;

    private float positionOffset;



    public BodyActor(Skin skin, SpaceBody body, float virtualScreenDistance, float positionOffset) {
        super(skin);
        this.body = body;
        this.virtualScreenDistance = virtualScreenDistance;
        this.positionOffset = positionOffset;

        textLabel = new Label(body.getName(), skin);

        setDebug(true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        body.update(delta);

        Vector2 position = SpaceMath.ToCarthesian(virtualScreenDistance, body.getRotation());
        setPosition(position.x + positionOffset, position.y + positionOffset, Align.center);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }
}
