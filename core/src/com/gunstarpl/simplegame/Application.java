package com.gunstarpl.simplegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Application extends Game
{
    public SpriteBatch batch;

    @Override
    public void create()
    {
        batch = new SpriteBatch();

        this.setScreen(new GameScreen(this));
    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }

    @Override
    public void render()
    {
        super.render();
    }
}
