package com.gunstarpl.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen
{
    private Application context;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture texture;

    private Array<Circle> targets;
    private float spawnTimer = 0.0f;

    public GameScreen(final Application context)
    {
        this.context = context;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        viewport = new FitViewport(720, 1280, camera);

        texture = new Texture(Gdx.files.internal("target.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        targets = new Array<Circle>();
    }

    @Override
    public void dispose()
    {
        texture.dispose();
    }

    @Override
    public void show()
    {
        camera.update();
        viewport.apply(true);

        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float dt)
    {
        spawnTimer -= dt;

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
            viewport.unproject(touchPosition);

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

        if(Gdx.input.isKeyJustPressed(Keys.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE))
        {
            context.setScreen(context.menuScreen);
            return;
        }

        Gdx.gl.glClearColor(0.44f, 0.69f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        context.batch.setProjectionMatrix(camera.projection);
        context.batch.setTransformMatrix(camera.view);

        context.batch.begin();
            for(Circle target : targets)
            {
                context.batch.draw(texture, -texture.getWidth() * 0.5f + target.x, -texture.getHeight() * 0.5f + target.y);
            }
        context.batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }
}
