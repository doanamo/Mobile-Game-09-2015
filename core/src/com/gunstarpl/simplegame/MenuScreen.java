package com.gunstarpl.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen
{
    private Application context;

    private OrthographicCamera camera;
    private Viewport viewport;

    public MenuScreen(final Application context)
    {
        this.context = context;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();

        viewport = new FitViewport(720, 1280, camera);
        viewport.apply(true);
    }

    @Override
    public void dispose()
    {
    }

    @Override
    public void render(float dt)
    {
        Gdx.gl.glClearColor(0.44f, 0.69f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        context.batch.setProjectionMatrix(camera.projection);
        context.batch.setTransformMatrix(camera.view);

        context.batch.begin();
            context.font.draw(context.batch, "Welcome to the game!", 50, 800);
            context.font.draw(context.batch, "Tap anywhere to begin...", 50, 700);
        context.batch.end();

        if(Gdx.input.isTouched())
        {
            context.setScreen(new GameScreen(context));
            this.dispose();
        }
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void show()
    {
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
