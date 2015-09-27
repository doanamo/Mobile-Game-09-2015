package com.gunstarpl.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen implements Screen
{
    private Application context;

    private Skin skin;
    private Stage stage;
    private Table table;

    TextButton buttonContinue;

    public MenuScreen(final Application context)
    {
        final MenuScreen self = this;
        this.context = context;

        // Create the skin.
        skin = new Skin();

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = context.font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.overFontColor = new Color(1.0f, 0.31f, 0.0f, 1.0f);
        textButtonStyle.disabledFontColor = new Color(0.8f, 0.8f, 0.8f, 1.0f);
        skin.add("default", textButtonStyle);

        // Create a stage.
        stage = new Stage(new FitViewport(720, 1280));

        // Create a table.
        table = new Table();
        table.setDebug(false);
        table.setFillParent(true);
        table.setY(40.0f);
        stage.addActor(table);

        // Add a logo image.
        Texture texture = new Texture(Gdx.files.internal("target.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Image imageLogo = new Image(texture);
        imageLogo.setOrigin(texture.getWidth() / 2, 0);
        imageLogo.setScale(2.0f, 2.0f);
        table.add(imageLogo).pad(100.0f).row();

        // Add a continue button.
        buttonContinue = new TextButton("Continue", skin);
        buttonContinue.setDisabled(true);

        buttonContinue.addListener(new ChangeListener()
        {
            public void changed(ChangeEvent event, Actor actor)
            {
                assert(context.gameScreen != null);
                context.setScreen(context.gameScreen);
            }
        });

        table.add(buttonContinue).pad(20.0f).row();

        // Add a new game button.
        TextButton buttonNewGame = new TextButton("New Game", skin);

        buttonNewGame.addListener(new ChangeListener()
        {
            public void changed(ChangeEvent event, Actor actor)
            {
                if(context.gameScreen != null)
                {
                    context.gameScreen.dispose();
                    context.gameScreen = null;
                }

                context.gameScreen = new GameScreen(context);
                context.setScreen(context.gameScreen);
            }
        });

        table.add(buttonNewGame).pad(20.0f).row();

        // Add a quit button.
        TextButton buttonQuit = new TextButton("Quit", skin);

        buttonQuit.addListener(new ChangeListener()
        {
            public void changed(ChangeEvent event, Actor actor)
            {
                Gdx.app.exit();
            }
        });

        table.add(buttonQuit).pad(20.0f).row();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);

        buttonContinue.setDisabled(context.gameScreen == null);
    }

    @Override
    public void render(float dt)
    {
        Gdx.gl.glClearColor(0.44f, 0.69f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
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
