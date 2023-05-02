package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.Clickables.Bin;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import com.mygdx.game.Cook;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class BinTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Bin bin;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        bin = new Bin(game, utils, gameScreen);
    }

    @Test
    public void binClickableIsNotNull() {
        ImageButton binClickable = bin.getBinClickable();
        assertNotNull(binClickable);
    }

    @Test
    public void testClick() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new Actor());
        cook.CookBody.setY(32f);
        cook.CookBody.setX(0f);
        cook.CookStack.push(new Ingredient("buns", null, null, null));
        cooks.add(cook);

        when(gameScreen.getCooks()).thenReturn(cooks);
        bin.onClick(gameScreen);
        assert cook.CookStack.size() == 0;
    }
}
