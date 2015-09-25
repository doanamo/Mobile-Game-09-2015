package com.gunstarpl.simplegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Application extends Game
{
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        font = new BitmapFont();

        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render()
    {
        super.render();
    }
}
