package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.BunClickable;
import com.mygdx.game.Clickables.PattyClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
@RunWith(GdxTestRunner.class)
public class PattyTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private PattyClickable patty;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        patty = new PattyClickable(utils,gameScreen);
    }

    @Test
    public void pattyClickableIsNotNull() {
        ImageButton pattyClickable = patty.getPattyClickable();
        assertNotNull(pattyClickable);
    }
}
