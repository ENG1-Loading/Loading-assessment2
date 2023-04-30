package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.Pantry;
import com.mygdx.game.Clickables.PotatoServeClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

@RunWith(GdxTestRunner.class)
public class PotatoServeTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private PotatoServeClickable potatoServeClickable;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        potatoServeClickable = new PotatoServeClickable(game, utils,gameScreen);
    }

    @Test
    public void potatoClickableIsNotNull() {
        ImageButton potatoServeClickable1 = potatoServeClickable.getPotatoServeClickable();
        assertNotNull(potatoServeClickable1);
    }
}
