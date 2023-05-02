package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.BunClickable;
import com.mygdx.game.Clickables.LettuceClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Cook;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

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

    @Test
    public void testClick() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        lettuce.onClick(gameScreen);
        assert cook.CookStack.size() == 1;
    }
}
