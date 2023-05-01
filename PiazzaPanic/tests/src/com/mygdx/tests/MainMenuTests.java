package com.mygdx.tests;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.ConfigHandler;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.SettingsScreen;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class MainMenuTests {
    private PiazzaPanic mockgame;
    private ConfigHandler configHandler;
    private MainMenuScreen mainMenuScreen;
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

        mainMenuScreen = new MainMenuScreen(mockgame, configHandler);
        font = new BitmapFont();
    }

    @Test
    public void testStage() {
        // Get a random actor from the stage
        mainMenuScreen.show();
        assertEquals(mainMenuScreen.loadfileBtn.getListeners().size, 2);

    }

}
