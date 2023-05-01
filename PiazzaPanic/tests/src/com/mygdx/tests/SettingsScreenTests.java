package com.mygdx.tests;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.ConfigHandler;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.SettingsScreen;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
@RunWith(GdxTestRunner.class)
public class SettingsScreenTests {
    private PiazzaPanic mockgame;
    private ConfigHandler configHandler;
    private SettingsScreen settingsScreen;
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

        settingsScreen = new SettingsScreen(mockgame, configHandler);
        font = new BitmapFont();
    }
    @Test
    public void testLabelNotNull() {
        Label label = settingsScreen.createLabel("test", 0, 0, font);
        assertNotNull(label);
    }

    @Test
    public void testStage() {
        settingsScreen.show();
        Stage stage = settingsScreen.getGameStage();
        stage.getActors();
        assertEquals(9, stage.getActors().size);

    }


}
