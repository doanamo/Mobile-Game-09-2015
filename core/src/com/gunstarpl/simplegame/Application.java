package com.gunstarpl.simplegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Application extends Game
{
    public SpriteBatch batch;
    public BitmapFont font;

    public MenuScreen menuScreen;
    public GameScreen gameScreen;

    @Override
    public void create()
    {
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 64;
        parameter.borderWidth = 2.0f;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();

        menuScreen = new MenuScreen(this);
        gameScreen = null;

        this.setScreen(menuScreen);
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        font.dispose();

        menuScreen.dispose();

        if(gameScreen != null)
            gameScreen.dispose();
    }

    @Override
    public void render()
    {
        super.render();
    }
}
