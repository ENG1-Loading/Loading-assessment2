package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.BunClickable;
import com.mygdx.game.Clickables.LettuceClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
@RunWith(GdxTestRunner.class)
public class LettuceTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private LettuceClickable lettuce;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        lettuce = new LettuceClickable(utils,gameScreen);
    }

    @Test
    public void lettuceClickableIsNotNull() {
        ImageButton lettuceClickable = lettuce.getLettuceClickable();
        assertNotNull(lettuceClickable);
    }
}
