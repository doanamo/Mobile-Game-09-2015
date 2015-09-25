package com.gunstarpl.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen
{
    private Application context;
    private OrthographicCamera camera;

    public MenuScreen(final Application context)
    {
        this.context = context;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

        camera.update();

        context.batch.setProjectionMatrix(camera.combined);
        context.batch.begin();
            context.font.getData().setScale(4.0f);
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
