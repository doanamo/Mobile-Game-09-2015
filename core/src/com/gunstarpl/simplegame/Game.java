package com.gunstarpl.simplegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;

    private float x = 0.0f;
    private float y = 0.0f;

    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        texture = new Texture("badlogic.jpg");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render()
    {
        if(Gdx.input.isTouched())
        {
            Vector3 touchPosition = new Vector3();
            touchPosition.x = Gdx.input.getX();
            touchPosition.y = Gdx.input.getY();
            camera.unproject(touchPosition);

            x = touchPosition.x;
            y = touchPosition.y;
        }

        Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture, -texture.getWidth() * 0.5f + x, -texture.getHeight() * 0.5f + y);
        batch.end();
    }
}
