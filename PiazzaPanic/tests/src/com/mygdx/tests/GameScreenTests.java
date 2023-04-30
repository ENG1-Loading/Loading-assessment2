package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ConfigHandler;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.json.JSONObject;
import org.mockito.Mockito;

@RunWith(GdxTestRunner.class)
public class GameScreenTests {
    private GameScreen piazzaPanic;
    private FitViewport fitViewport;
    private Boolean isEndless;
    private Boolean isLoad;
    private String loadFile;
    private JSONObject config;
    private PiazzaPanic game;


    @Before
    public void setUp() throws IOException {
        fitViewport = mock(FitViewport.class);
        isEndless=false;
        isLoad = false;
        loadFile = "";
        config = mock(JSONObject.class);
        game = Mockito.mock(PiazzaPanic.class);
        config = new JSONObject();
        config.put("difficulty", "Easy");
        config.put("customersToServe", 5);
        game.batch = Mockito.mock(SpriteBatch.class);
        piazzaPanic = new GameScreen(game, fitViewport, isEndless, isLoad, loadFile, config);
    }


    @Test
    public void alwaysSuccess() {
        assertTrue(true);
    }

}
