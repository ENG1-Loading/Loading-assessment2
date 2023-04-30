package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainMenuScreen implements Screen {
    PiazzaPanic game;
    Texture logo;
    Texture playBtnTex;
    Texture playBtnTexHover;
    Texture exitBtnTex;
    Texture exitBtnTexHover;
    Texture infoBtnTex;
    Texture infoBtnTexHover;
    Texture settingsBtnTex;
    Texture settingsBtnTexHover;
    Texture endlessBtnTex;
    Texture loadfileBtnTex;
    Texture loadfileBtnHover;
    FitViewport view;
    Stage gameStage;

    // image buttons for when not hovering on the button
    TextureRegion playBtnRegion;
    TextureRegionDrawable playBtnDrawable;
    ImageButton playBtn;

    TextureRegion exitBtnRegion;
    TextureRegionDrawable exitBtnDrawable;
    ImageButton exitBtn;

    TextureRegion infoBtnRegion;
    TextureRegionDrawable infoBtnDrawable;
    ImageButton infoBtn;

    TextureRegion endlessBtnRegion;
    TextureRegionDrawable endlessBtnDrawable;
    ImageButton endlessBtn;

    TextureRegion loadfileBtnRegion;
    TextureRegionDrawable loadfileBtnDrawable;
    ImageButton loadfileBtn;

    TextureRegion settingsBtnRegion;
    TextureRegionDrawable settingsBtnDrawable;
    ImageButton settingsBtn;

    // image buttons for when hovering on the button
    TextureRegion playBtnRegionHover;
    TextureRegionDrawable playBtnDrawableHover;
    ImageButton playBtnHover;

    TextureRegion exitBtnRegionHover;
    TextureRegionDrawable exitBtnDrawableHover;
    ImageButton exitBtnHover;

    TextureRegion infoBtnRegionHover;
    TextureRegionDrawable infoBtnDrawableHover;
    ImageButton infoBtnHover;

    TextureRegion loadfileBtnRegionHover;
    TextureRegionDrawable loadfileBtnDrawableHover;
    String filepath;
    Boolean fileOpening = false;

    TextureRegion settingsBtnRegionHover;
    TextureRegionDrawable settingsBtnDrawableHover;
    ImageButton settingsBtnHover;

    JSONObject config;
    ConfigHandler configHandler;
    /*
        * Constructor for the main menu screen
        * @param game the game object
        * @return none
     */
    public MainMenuScreen(PiazzaPanic game) throws IOException {
        this.configHandler = new ConfigHandler();
        this.config = configHandler.getConfig();
        this.game = game;

    }
    /*
        * Initializes the main menu screen graphics
        * @param none
        * @return none
     */
    @Override
    public void show() {
        logo = new Texture("Piazza_Panic_Logo.png");

        // textures for normal buttons
        playBtnTex = new Texture("playBtn.png");
        exitBtnTex = new Texture("exitBtn.png");
        infoBtnTex = new Texture("infoBtn.png");
        endlessBtnTex = new Texture("endless.png");
        loadfileBtnTex = new Texture("Save.png");
        settingsBtnTex = new Texture("settings.png");
        // textures for hovered buttons
        infoBtnTexHover = new Texture("infoBtn2.png");
        playBtnTexHover = new Texture("playBtn2.png");
        exitBtnTexHover = new Texture("exitBtn2.png");
        loadfileBtnHover = new Texture("Save.png");
        settingsBtnTexHover = new Texture("settings.png");

        view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        view.getCamera().position.set(game.GAME_WIDTH / 2, game.GAME_HEIGHT / 2, 1f);
        gameStage = new Stage(view, game.batch);

        // normal buttons
        playBtnRegion = new TextureRegion(playBtnTex);
        playBtnDrawable = new TextureRegionDrawable(playBtnRegion);
        playBtn = new ImageButton(playBtnDrawable);

        exitBtnRegion = new TextureRegion(exitBtnTex);
        exitBtnDrawable = new TextureRegionDrawable(exitBtnRegion);
        exitBtn = new ImageButton(exitBtnDrawable);

        infoBtnRegion = new TextureRegion(infoBtnTex);
        infoBtnDrawable = new TextureRegionDrawable(infoBtnRegion);
        infoBtn = new ImageButton(infoBtnDrawable);

        endlessBtnRegion = new TextureRegion(endlessBtnTex);
        endlessBtnDrawable = new TextureRegionDrawable(endlessBtnRegion);
        endlessBtn = new ImageButton(endlessBtnDrawable);

        loadfileBtnRegion = new TextureRegion(loadfileBtnTex);
        loadfileBtnDrawable = new TextureRegionDrawable(loadfileBtnRegion);
        loadfileBtn = new ImageButton(loadfileBtnDrawable);

        settingsBtnRegion = new TextureRegion(settingsBtnTex);
        settingsBtnDrawable = new TextureRegionDrawable(settingsBtnRegion);
        settingsBtn = new ImageButton(settingsBtnDrawable);

        // hovered buttons
        playBtnRegionHover = new TextureRegion(playBtnTexHover);
        playBtnDrawableHover = new TextureRegionDrawable(playBtnRegionHover);

        exitBtnRegionHover = new TextureRegion(exitBtnTexHover);
        exitBtnDrawableHover = new TextureRegionDrawable(exitBtnRegionHover);

        infoBtnRegionHover = new TextureRegion(infoBtnTexHover);
        infoBtnDrawableHover = new TextureRegionDrawable(infoBtnRegionHover);

        loadfileBtnRegionHover = new TextureRegion(loadfileBtnHover);
        loadfileBtnDrawableHover = new TextureRegionDrawable(loadfileBtnHover);

        settingsBtnRegionHover = new TextureRegion(settingsBtnTexHover);
        settingsBtnDrawableHover = new TextureRegionDrawable(settingsBtnRegionHover);
        // listeners for hovering on the buttons
        playBtn.addListener(new ClickListener() {
            final ImageButton playNormal = new ImageButton(playBtnDrawable);
            final ImageButton playHover = new ImageButton(playBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playBtn.setStyle(playHover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                playBtn.setStyle(playNormal.getStyle());
            }
        });

        exitBtn.addListener(new ClickListener() {
            final ImageButton exitNormal = new ImageButton(exitBtnDrawable);
            final ImageButton exitHover = new ImageButton(exitBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitBtn.setStyle(exitHover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitBtn.setStyle(exitNormal.getStyle());
            }
        });

        endlessBtn.addListener(new ClickListener() {

        });
        loadfileBtn.addListener(new ClickListener() {
        });
        infoBtn.addListener(new ClickListener() {
            final ImageButton infoNormal = new ImageButton(infoBtnDrawable);
            final ImageButton infoHover = new ImageButton(infoBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                infoBtn.setStyle(infoHover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                infoBtn.setStyle(infoNormal.getStyle());
            }
        });

        settingsBtn.addListener(new ClickListener() {
            final ImageButton settingsNormal = new ImageButton(settingsBtnDrawable);
            final ImageButton settingsHover = new ImageButton(settingsBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settingsBtn.setStyle(settingsHover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                settingsBtn.setStyle(settingsNormal.getStyle());
            }
        });

    }
    /*
        * Renders the main menu screen graphics
        * @param float delta
        * @return none
     */
    @Override
    public void render(float delta) {
        gameStage.act();

        ScreenUtils.clear(1, 1, 1, 1);
        view.apply();

        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();
        game.batch.draw(logo, ((game.GAME_WIDTH / 2) - (logo.getWidth() / 2)), 250);
        game.batch.end();

        gameStage.getViewport().apply();

        gameStage.addActor(playBtn);
        playBtn.setPosition((((game.GAME_WIDTH / 2) - (playBtn.getWidth() / 2)) - endlessBtn.getWidth() / 2) - 25, 125);
        gameStage.addActor(endlessBtn);
        endlessBtn.setPosition((((game.GAME_WIDTH / 2) - (playBtn.getWidth() / 2)) + endlessBtn.getWidth() / 2) + 25,
                125);
        gameStage.addActor(exitBtn);
        exitBtn.setPosition((game.GAME_WIDTH / 2) - (exitBtn.getWidth() / 2), 35);
        gameStage.addActor(loadfileBtn);
        loadfileBtn.setPosition((((game.GAME_WIDTH / 2) - (playBtn.getWidth() / 2)) + endlessBtn.getWidth() / 2) + 25,
                125);
        gameStage.addActor(infoBtn);
        infoBtn.setPosition(game.GAME_WIDTH - infoBtn.getWidth(), game.GAME_HEIGHT - infoBtn.getHeight());
        gameStage.addActor(settingsBtn);
        settingsBtn.setPosition(game.GAME_WIDTH - infoBtn.getWidth() - 70, game.GAME_HEIGHT - infoBtn.getHeight());

        if (playBtn.isPressed()) {
            try {
                game.setScreen(new GameScreen(game, view, false, false, "", this.config));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (exitBtn.isPressed()) {
            dispose();
            Gdx.app.exit();
        }

        if (infoBtn.isPressed()) {
            game.setScreen(new CreditsScreen(game));
        }
        if (endlessBtn.isPressed()) {
            try {
                game.setScreen(new GameScreen(game, view, true, false, "", this.config));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (settingsBtn.isPressed()) {
            game.setScreen(new SettingsScreen(game, this.configHandler));
        }
        if (loadfileBtn.isPressed()) {
            if (!fileOpening) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!fileOpening) {
                            fileOpening = true;
                            JFileChooser chooser = new JFileChooser();
                            FileNameExtensionFilter filter = new FileNameExtensionFilter("json file",
                                    new String[] { "json" });
                            chooser.setFileFilter(filter);
                            chooser.addChoosableFileFilter(filter);
                            int returnval = chooser.showOpenDialog(null);
                            if (returnval == JFileChooser.APPROVE_OPTION) {
                                filepath = chooser.getSelectedFile().getAbsolutePath();
                                fileOpening = false;

                            } else {
                            }
                        }

                    }
                });

            } else {}

        }
        if (!Objects.equals(filepath, "") && filepath != null) {
            try {
                game.setScreen(new GameScreen(game, view, false, true, filepath, this.config));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        // if (loadfileBtn.isPressed()) {
        // EventQueue.invokeLater(new Runnable() {
        // @Override
        // public void run() {
        // if (!fileOpening) {
        // fileOpening = true;
        // JFileChooser chooser = new JFileChooser();
        // FileNameExtensionFilter filter = new FileNameExtensionFilter("json file", new
        // String[] {"json"});
        // chooser.setFileFilter(filter);
        // chooser.addChoosableFileFilter(filter);
        // int returnval = chooser.showOpenDialog(null);
        // if (returnval == JFileChooser.APPROVE_OPTION) {
        // filepath = chooser.getSelectedFile().getAbsolutePath();
        // System.out.println(filepath);
        // fileOpening = false;

        // } else {
        // System.out.println(returnval);

        // }
        // } else {
        // System.out.println("Already selecting a file");
        // }
        //
        // }

        // });
        // }
        Gdx.input.setInputProcessor(gameStage);
        gameStage.draw();
    }
    /*
        * Resizes the main menu screen graphics
        * @param width - width of the screen
        * @param height - height of the screen
        * @return none
     */
    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);
    }
    /*
        * Pauses the main menu screen graphics
        * @param none
        * @return none
     */
    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }
    /*
        * Resumes the main menu screen graphics
        * @param none
        * @return none
     */
    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
    /*
        * Hides the main menu screen graphics
        * @param none
        * @return none
     */
    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }
    /*
        * Disposes the main menu screen graphics
        * @param none
        * @return none
     */
    @Override
    public void dispose() {
        logo.dispose();
        gameStage.dispose();
    }

}
