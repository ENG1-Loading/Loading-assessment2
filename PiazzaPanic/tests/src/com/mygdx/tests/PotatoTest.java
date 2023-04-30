package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.Pantry;
import com.mygdx.game.Clickables.PotatoClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

public class PotatoTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private PotatoClickable potatoClickable;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        potatoClickable = new PotatoClickable( utils,gameScreen);
    }

    @Test
    public void potatoClickableIsNotNull() {
        ImageButton potatoClickable1 = potatoClickable.getPotatoClickable();
        assertNotNull(potatoClickable1);
    }

}
