package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.ConfigHandler;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.EndGameScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.tests.GdxTestRunner;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class EndgameTests {
    private PiazzaPanic mockgame;
    private ConfigHandler configHandler;
    private EndGameScreen endGameScreen;
    private BitmapFont font;
    @Before
    public void setUp() throws IOException {
        System.out.println("Setting up class");
        mockgame = mock(PiazzaPanic.class);
        configHandler = mock(ConfigHandler.class);
        JSONObject config = new JSONObject();
        config.put("difficulty", "Easy");
        config.put("muteMusic", false);
        config.put("customersToServe", 5);
        mockgame.batch = mock(com.badlogic.gdx.graphics.g2d.SpriteBatch.class);
        configHandler = new ConfigHandler(config);

        endGameScreen = new EndGameScreen(mockgame, 10, 1, false, 2);
        font = new BitmapFont();
    }

    @Test
    public void testStage() {
        // Get a random actor from the stage
        endGameScreen.show();
        System.out.println(endGameScreen.getStage().getActors().size);
        assertEquals(endGameScreen.getStage().getActors().size, 2);

    }
}