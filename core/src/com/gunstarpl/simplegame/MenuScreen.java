package com.gunstarpl.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    public MenuScreen(final Application context)
    {
        final MenuScreen self = this;
        this.context = context;

        // Create the skin.
        skin = new Skin();

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = context.font;
        skin.add("default", textButtonStyle);

        // Create a stage.
        stage = new Stage(new FitViewport(720, 1280));
        Gdx.input.setInputProcessor(stage);

        // Create a table.
        table = new Table();
        table.setDebug(false);
        table.setFillParent(true);
        stage.addActor(table);

        // Create a new game button.
        TextButton buttonNewGame = new TextButton("New Game", skin);

        buttonNewGame.addListener(new ChangeListener()
        {
            public void changed(ChangeEvent event, Actor actor)
            {
                context.setScreen(new GameScreen(context));
                self.dispose();
            }
        });

        table.add(buttonNewGame).pad(20.0f).row();

        // Create a quit button.
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
    public void render(float dt)
    {
        Gdx.gl.glClearColor(0.44f, 0.69f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
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
