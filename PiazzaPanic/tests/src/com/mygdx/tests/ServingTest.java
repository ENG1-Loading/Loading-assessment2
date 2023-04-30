package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.SaladClickable;
import com.mygdx.game.Clickables.Serving;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

@RunWith(GdxTestRunner.class)
public class ServingTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Serving serving;
    private Powerups powerups;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        serving = new Serving( game,utils, gameScreen);
    }

    @Test
    public void servingClickableIsNotNull() {
        ImageButton servingClickable = serving.getServingClickable();
        assertNotNull(servingClickable);
    }
}
