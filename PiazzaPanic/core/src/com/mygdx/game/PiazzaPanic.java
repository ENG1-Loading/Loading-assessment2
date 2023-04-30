package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenuScreen;

public class PiazzaPanic extends Game {
    public final int GAME_WIDTH = 1280;
    public final int GAME_HEIGHT = 720;
    public SpriteBatch batch;
    // private FitViewport fit;
    /*
        * Creates the game
        * @param none
        * @return none
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        // fit = new FitViewport(GAME_WIDTH, GAME_HEIGHT);
        try {
            this.setScreen(new MainMenuScreen(this));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*
        * Renders the game
        * @param none
        * @return none
     */
    @Override
    public void render() {
        super.render();
    }

    /*
        * Disposes of the game
        * @param none
        * @return none
     */
    @Override
    public void dispose() {
        batch.dispose();
    }
}
