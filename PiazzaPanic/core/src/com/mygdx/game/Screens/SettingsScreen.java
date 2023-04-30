package com.mygdx.game.Screens;

import java.io.IOException;

import com.badlogic.gdx.graphics.Color;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ConfigHandler;
import com.mygdx.game.PiazzaPanic;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class SettingsScreen implements Screen {
    PiazzaPanic game;
    Stage gameStage;
    Texture checkedBox = new Texture("checked.png");
    Texture uncheckedBox = new Texture("unchecked_box.png");
    Texture backBtnTex = new Texture("backBtn.png");
    Texture backBtnTexHover = new Texture("backBtn2.png");
    Texture diffBtnTex;
    Texture diffBtnTexHover;


    Texture hardBtnTexHover;
    FitViewport view;
    TextureRegion checkedBoxRegion;
    TextureRegion uncheckedBoxRegion;
    TextureRegionDrawable checkedBoxDrawable;
    TextureRegionDrawable uncheckedBoxDrawable;
    ImageButton checkedBoxBtn;
    ImageButton uncheckedBoxBtn;
    Boolean checked = true;
    ConfigHandler configHandler;
    TextureRegion backBtnRegion;
    TextureRegionDrawable backBtnDrawable;
    ImageButton backBtn;
    TextureRegion backBtnRegionHover;
    TextureRegionDrawable backBtnDrawableHover;

    JSONObject config;
    String currentDifficulty = "Easy";
    /*
        * Constructor for the settings screen
        * @param game the game object
        * @param configHandler the config handler object
        * @return none
     */
    public SettingsScreen(PiazzaPanic game, ConfigHandler configHandler) {
        this.game = game;
        this.configHandler = configHandler;
        this.config = configHandler.getConfig();
    }

    private Label createLabel(String text, float x, float y, BitmapFont font) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = com.badlogic.gdx.graphics.Color.BLACK;
        Label label = new Label(text, labelStyle);
        label.setPosition(x, y);
        return label;
    }
    /*
        * Renders the settings screen
        * @param delta the time between frames
        * @return none
     */
    @Override
    public void show() {
        System.out.println("SettingsScreen");
        currentDifficulty = config.getString("difficulty");
        checked = config.getBoolean("muteMusic");
        view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        view.getCamera().position.set(game.GAME_WIDTH / 2, game.GAME_HEIGHT / 2, 1f);
        gameStage = new Stage(view, game.batch);

        backBtnRegion = new TextureRegion(backBtnTex);
        backBtnDrawable = new TextureRegionDrawable(backBtnRegion);
        backBtn = new ImageButton(backBtnDrawable);

        backBtnRegionHover = new TextureRegion(backBtnTexHover);
        backBtnDrawableHover = new TextureRegionDrawable(backBtnRegionHover);

        checkedBoxRegion = new TextureRegion(checkedBox);
        checkedBoxDrawable = new TextureRegionDrawable(checkedBoxRegion);

        uncheckedBoxRegion = new TextureRegion(uncheckedBox);
        uncheckedBoxDrawable = new TextureRegionDrawable(uncheckedBoxRegion);

        checkedBoxBtn = new ImageButton(checked ? checkedBoxDrawable : uncheckedBoxDrawable);
        checkedBoxBtn.setPosition((game.GAME_WIDTH / 2) - (checkedBox.getWidth() / 2), game.GAME_HEIGHT - 350);

        BitmapFont font = new BitmapFont();
        TextField.TextFieldFilter numOnly = new TextField.TextFieldFilter.DigitsOnlyFilter();
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = com.badlogic.gdx.graphics.Color.BLACK;
        int currentServe = config.getInt("customersToServe");
        final TextField numberInput = new TextField(String.valueOf(currentServe), textFieldStyle);
        numberInput.setPosition((game.GAME_WIDTH / 2) - (numberInput.getWidth() / 2), game.GAME_HEIGHT - 300);
        numberInput.setSize(200, 40);
        Texture inputBg = new Texture("inp.png");
        NinePatch inputNinePatch = new NinePatch(inputBg, 10, 10, 10, 10);
        NinePatchDrawable inputBoxBackground = new NinePatchDrawable(inputNinePatch);
        inputBoxBackground.setLeftWidth(10);
        numberInput.getStyle().background = inputBoxBackground;
        numberInput.setTextFieldFilter(numOnly);

        TextButton.TextButtonStyle diffButtonStyle = new TextButton.TextButtonStyle();
        diffButtonStyle.font = font;
        diffButtonStyle.fontColor = Color.BLACK;
        diffButtonStyle.up = new NinePatchDrawable(new NinePatch(new Texture("inp.png"), 10,10,10,10));
        diffButtonStyle.down = new NinePatchDrawable(new NinePatch(new Texture("inp.png"), 10,10,10,10));
        final TextButton difficultyButton = new TextButton(currentDifficulty, diffButtonStyle);
        difficultyButton.setPosition((game.GAME_WIDTH / 2) - (difficultyButton.getWidth() -2), 200);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = com.badlogic.gdx.graphics.Color.BLACK;
        textButtonStyle.up = new NinePatchDrawable(new NinePatch(new Texture("inp.png"), 10, 10, 10, 10));
        textButtonStyle.down = new NinePatchDrawable(new NinePatch(new Texture("inp.png"), 10, 10, 10, 10));
        TextButton button = new TextButton("Submit", textButtonStyle);
        button.setPosition((game.GAME_WIDTH / 2) - (button.getWidth() / 2), 300);
        checkedBoxBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checked = !checked; // Toggle the checked state
                ImageButton.ImageButtonStyle newStyle = new ImageButton.ImageButtonStyle(checkedBoxBtn.getStyle());
                if (checked) {
                    System.out.println("Checked");
                    newStyle.imageUp = checkedBoxDrawable;
                } else {
                    System.out.println("Unchecked");
                    newStyle.imageUp = uncheckedBoxDrawable;
                }
                checkedBoxBtn.setStyle(newStyle);
                super.clicked(event, x, y);
            }
        });

        difficultyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Difficulty pressed, "+ currentDifficulty);
                if (currentDifficulty == "Easy") {
                    currentDifficulty = "Medium";

                } else if (currentDifficulty == "Medium") {
                    currentDifficulty = "Hard";
                } else {
                    currentDifficulty = "Easy";
                }
                difficultyButton.setText(currentDifficulty);
                super.clicked(event, x, y);
            }

        });

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button clicked");
                String inputtedNum = numberInput.getText();
                System.out.println(inputtedNum);
                if (inputtedNum.equals("")) {
                    inputtedNum = "5";
                }
                configHandler.setCustomersToServe(Integer.parseInt(inputtedNum));
                configHandler.setMuteMode(checked);
                configHandler.setDifficulty(currentDifficulty);
                super.clicked(event, x, y);
            }
        });

        backBtn.addListener(new ClickListener() {
            final ImageButton backNormal = new ImageButton(backBtnDrawable);
            final ImageButton backHover = new ImageButton(backBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backBtn.setStyle(backHover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                backBtn.setStyle(backNormal.getStyle());
            }
        });
        backBtn.setPosition(0, game.GAME_HEIGHT - backBtn.getHeight());

        gameStage.addActor(checkedBoxBtn);
        gameStage.addActor(numberInput);
        gameStage.addActor(button);
        gameStage.addActor(backBtn);
        gameStage.addActor(difficultyButton);

        checkedBoxBtn.setPosition(game.GAME_WIDTH / 2 - checkedBoxBtn.getWidth()/2, (float) (game.GAME_HEIGHT * 0.5));
        numberInput.setPosition(game.GAME_WIDTH / 2 - numberInput.getWidth() /2, (float) (game.GAME_HEIGHT * 0.3));
        button.setPosition(game.GAME_WIDTH/2 - button.getWidth()/2, (float) (game.GAME_HEIGHT * 0.1));
        difficultyButton.setPosition(game.GAME_WIDTH / 2 - difficultyButton.getWidth()/2, (float) (game.GAME_HEIGHT * 0.7));

        Label checkedBoxLabel = createLabel("Mute Music", game.GAME_WIDTH / 2 - checkedBoxBtn.getWidth() / 2 - 90, (float) (game.GAME_HEIGHT * 0.5), font);
        Label numberInputLabel = createLabel("Customers to Serve", game.GAME_WIDTH / 2 - numberInput.getWidth() / 2, (float) (game.GAME_HEIGHT * 0.3 + numberInput.getHeight() + 10), font);
        Label submitButtonLabel = createLabel("Submit", game.GAME_WIDTH / 2 - button.getWidth() / 2, (float) (game.GAME_HEIGHT * 0.1 + button.getHeight() + 10), font);
        Label difficultyButtonLabel = createLabel("Difficulty", game.GAME_WIDTH / 2 - difficultyButton.getWidth() / 2, (float) (game.GAME_HEIGHT * 0.7 + difficultyButton.getHeight() + 10), font);
        gameStage.addActor(checkedBoxLabel);
        gameStage.addActor(numberInputLabel);
        gameStage.addActor(submitButtonLabel);
        gameStage.addActor(difficultyButtonLabel);

    }
    /*
        * Render loop for the screen
        *
        * @param delta The time in seconds since the last render.
        *
        * @return none
     */
    public void render(float delta) {
        gameStage.act();
        gameStage.draw();

        ScreenUtils.clear(1, 1, 1, 1);
        view.apply();

        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();
        game.batch.end();

        gameStage.getViewport().apply();

        if (backBtn.isPressed()) {
            try {
                game.setScreen(new MainMenuScreen(game));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Gdx.input.setInputProcessor(gameStage);
        gameStage.draw();
    }

    /*
     * Resizes the settings screen graphics
     * @param width - width of the screen
     * @param height - height of the screen
     * @return none
     */
    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);
    }
    /*
     * Pauses settings screen graphics
     * @param none
     * @return none
     */
    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }
    /*
     * Resumes the settings screen graphics
     * @param none
     * @return none
     */
    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
    /*
     * Hides the settings screen graphics
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
        gameStage.dispose();
    }

}
