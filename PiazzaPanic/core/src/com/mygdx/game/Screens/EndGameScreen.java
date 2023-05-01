package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ConfigHandler;
import com.mygdx.game.PiazzaPanic;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

// screen which displays after the game finishes

public class EndGameScreen implements Screen {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Odin Rounded - Bold.otf"));
    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    BitmapFont font;
    PiazzaPanic game;
    FitViewport view;
    Stage screenStage;
    Texture levelCompleteFrame = new Texture("LevelCompleteFrame.png");
    Texture exitBtnTex;
    Texture exitBtnTexHover;
    ImageButton exitBtn;
    Texture restartBtnTex;
    Texture restartBtnTexHover;
    ImageButton restartBtn;
    TextureRegionDrawable exitBtnDrawable;
    TextureRegionDrawable exitBtnDrawableHover;
    TextureRegionDrawable restartBtnDrawable;
    TextureRegionDrawable restartBtnDrawableHover;
    int Rep;
    String levelTimeString;
    Boolean endless;
    int customersServed;
    ConfigHandler configHandler;
    JSONObject config;
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    public EndGameScreen(PiazzaPanic game, long levelCompletedIn, int RepPoints, boolean endless, int customersServed)
            throws IOException {
        // generate the styling information for the data given to this screen
        this.configHandler = new ConfigHandler();
        this.config = configHandler.getConfig();
        this.game = game;
        this.endless = endless;
        this.customersServed = customersServed;
        parameter.size = 48;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        levelTimeString = humanReadableFormat(levelCompletedIn);
        this.Rep = RepPoints;
    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    @Override
    public void show() {
        // size information
        view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        view.getCamera().position.set(game.GAME_WIDTH / 2, game.GAME_HEIGHT / 2, 1f);
        screenStage = new Stage(view, game.batch);

        // draw buttons
        exitBtnTex = new Texture("exitBtn.png");
        exitBtnTexHover = new Texture("exitBtn2.png");
        restartBtnTex = new Texture("RestartBtn.png");
        restartBtnTexHover = new Texture("RestartBtn2.png");

        exitBtnDrawable = new TextureRegionDrawable(new TextureRegion(exitBtnTex));
        exitBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(exitBtnTexHover));
        exitBtn = new ImageButton(exitBtnDrawable);
        exitBtn.addListener(new ClickListener() {
            final ImageButton normal = new ImageButton(exitBtnDrawable);
            final ImageButton hover = new ImageButton(exitBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitBtn.setStyle(hover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitBtn.setStyle(normal.getStyle());
            }
        });

        restartBtnDrawable = new TextureRegionDrawable(new TextureRegion(restartBtnTex));
        restartBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(restartBtnTexHover));
        restartBtn = new ImageButton(restartBtnDrawable);
        restartBtn.addListener(new ClickListener() {
            final ImageButton normal = new ImageButton(restartBtnDrawable);
            final ImageButton hover = new ImageButton(restartBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                restartBtn.setStyle(hover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                restartBtn.setStyle(normal.getStyle());
            }
        });

        screenStage.addActor(exitBtn);
        exitBtn.setPosition(game.GAME_WIDTH / 2 - exitBtn.getWidth() / 2,
                game.GAME_HEIGHT / 2 - exitBtn.getHeight() / 2 - 250);
        screenStage.addActor(restartBtn);
        restartBtn.setPosition(game.GAME_WIDTH / 2 - restartBtn.getWidth() / 2,
                game.GAME_HEIGHT / 2 - restartBtn.getHeight() / 2 - 100);
    }

    public Stage getStage() {
        return screenStage;
    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    @Override
    public void render(float delta) {
        screenStage.act();

        ScreenUtils.clear(1, 1, 1, 1);
        view.apply();
        Gdx.input.setInputProcessor(screenStage);

        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();
        game.batch.draw(levelCompleteFrame, ((game.GAME_WIDTH / 2) - (levelCompleteFrame.getWidth() / 2)), 10);
        if (endless) {
            font.draw(game.batch, "YOU SERVED " + customersServed + " Customers", 420, 480);
        } else {
            font.draw(game.batch, "REPUTATION:" + Rep, 420, 425);
        }

        game.batch.end();

        screenStage.getViewport().apply();

        if (restartBtn.isPressed()) {
            try {
                game.setScreen(new GameScreen(game, view, false, false, "", this.config));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (exitBtn.isPressed()) {
            dispose();
            try {
                game.setScreen(new MainMenuScreen(game));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        screenStage.draw();
    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    @Override
    public void resize(int width, int height) {
        screenStage.getViewport().update(width, height);
    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    @Override
    public void dispose() {
        levelCompleteFrame.dispose();
        screenStage.dispose();
    }
    /*
        * Converts a long to a human readable time format
        * @param millis the time in milliseconds
        *
        * @return the time in a human readable format
     */
    private String humanReadableFormat(long duration) {

        long secs = TimeUnit.MILLISECONDS.toSeconds(duration);
        // format the time information
        return (String.format("%ss", secs));
    }

}
