package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.Clickables.Cutting;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Stack;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class CuttingTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Cutting cutting;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        cutting = new Cutting(game, utils, gameScreen);
    }

    @Test
    public void cuttingClickableNotNull() {
        ImageButton cuttingClickable = cutting.getCuttingClickable();
        assertNotNull(cuttingClickable);
    }

    @Test
    public void testClick() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cook.CookBody.setY(28f);
        cook.CookBody.setX(0f);
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        cutting.onClicked(gameScreen);
        assert !cook.isBusy;
    }

    @Test
    public void testClickWithLettuce() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cook.CookBody.setY(28f);
        cook.CookBody.setX(48f);
        Stack<Ingredient> cookStack = new Stack<Ingredient>();
        cookStack.push(new Ingredient("lettuce", null, null, null));
        cook.CookStack = cookStack;
        cook.isBusy = false;
        cooks.add(cook);

        when(gameScreen.getCooks()).thenReturn(cooks);
        cutting.onClicked(gameScreen);
        System.out.println(cook.isBusy);
        assert cook.isBusy;
    }
}
