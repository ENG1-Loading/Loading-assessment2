package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.SaladClickable;
import com.mygdx.game.Clickables.TomatoClickable;
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
public class TomatoTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private TomatoClickable tomatoClickable;
    private Powerups powerups;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        tomatoClickable = new TomatoClickable( utils, gameScreen);
    }

    @Test
    public void saladClickableIsNotNull() {
        ImageButton tomatoClickable1 = tomatoClickable.getTomatoClickable();
        assertNotNull(tomatoClickable1);
    }
}
