package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.Clickables.Frying;
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
public class FryingTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Frying frying;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        frying = new Frying(game, utils, gameScreen);
    }

    @Test
    public void fryingClickableIsNotNull() {
        ImageButton fryingClickable = frying.createFryingClickable();
        assertNotNull(fryingClickable);
    }

    @Test
    public void testClick() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cook.CookBody.setY(28f);
        cook.CookBody.setX(0f);
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        frying.onClick(gameScreen);
        assert gameScreen.getPattyAtFrying() == false;

    }

    @Test
    public void testValidClick() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cook.CookBody.setY(64f);
        cook.CookBody.setX(32f);
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(new Ingredient("patty", null, null, null));
        cook.CookStack = ingredients;
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        frying.onClick(gameScreen);
        verify(gameScreen).setPattyAtFrying(false);
    }

    @Test
    public void testValidClickPattyDone() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cook.CookBody.setY(64f);
        cook.CookBody.setX(32f);
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(new Ingredient("patty", null, null, null));
        cook.CookStack = ingredients;
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        when(gameScreen.getFryingClicked()).thenReturn(3);
        frying.onClick(gameScreen);
        verify(gameScreen).setPattyAtFrying(true);
    }

    @Test
    public void testCookBusy() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cook.CookBody.setY(64f);
        cook.CookBody.setX(32f);
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(new Ingredient("lettuce", null, null, null));
        cook.CookStack = ingredients;
        cook.isBusy = false;
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        when(gameScreen.getFryingClicked()).thenReturn(3);
        when(gameScreen.getPattyAtFrying()).thenReturn(true);
        frying.onClick(gameScreen);
        verify(gameScreen).setPattyAtFrying(false);
    }
}
