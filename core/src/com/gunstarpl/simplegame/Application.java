package com.gunstarpl.simplegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Application extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;

    private Array<Circle> targets;
    private float spawnTimer = 0.0f;

    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        texture = new Texture(Gdx.files.internal("target.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        targets = new Array<Circle>();
    }

    @Override
    public void render()
    {
        spawnTimer -= Gdx.graphics.getDeltaTime();

        if(spawnTimer <= 0.0f)
        {
            final float targetRadius = 80.0f;
            final float screenMargin = 10.0f;

            Circle target = new Circle();
            target.x = MathUtils.random(screenMargin + targetRadius, Gdx.graphics.getWidth() - targetRadius - screenMargin);
            target.y = MathUtils.random(screenMargin + targetRadius, Gdx.graphics.getHeight() - targetRadius - screenMargin);
            target.radius = targetRadius;
            targets.add(target);

            spawnTimer += MathUtils.random(0.5f, 1.5f);
        }

        if(Gdx.input.justTouched())
        {
            Vector3 touchPosition = new Vector3();
            touchPosition.x = Gdx.input.getX();
            touchPosition.y = Gdx.input.getY();
            camera.unproject(touchPosition);

            for(int i = targets.size - 1; i >= 0; --i)
            {
                Circle target = targets.get(i);

                if(target.contains(touchPosition.x, touchPosition.y))
                {
                    targets.removeIndex(i);
                    break;
                }
            }
        }

        Gdx.gl.glClearColor(0.44f, 0.69f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for(Circle target : targets)
        {
            batch.draw(texture, -texture.getWidth() * 0.5f + target.x, -texture.getHeight() * 0.5f + target.y);
        }

        batch.end();
    }
}
