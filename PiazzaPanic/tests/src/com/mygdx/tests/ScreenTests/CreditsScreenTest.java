package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ConfigHandler;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.CreditsScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.SettingsScreen;
import com.mygdx.tests.GdxTestRunner;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class CreditsScreenTest {
    private CreditsScreen creditsScreen;
    private PiazzaPanic game;
    @Before
    public void setUp() {
        System.out.println("Setting up class");
        game = mock(PiazzaPanic.class);
        game.batch = mock(SpriteBatch.class);
        creditsScreen = new CreditsScreen(game);
        System.out.println(creditsScreen);
    }

    @Test
    public void testCreditsScreen() {
        creditsScreen.show();
        System.out.println(creditsScreen.getStage().getActors().size);
        assertEquals(creditsScreen.getStage().getActors().size, 3);
    }
}
