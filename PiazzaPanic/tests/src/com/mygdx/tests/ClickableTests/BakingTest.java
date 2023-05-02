package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

import com.mygdx.game.Clickables.*;


import com.mygdx.game.Cook;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Stack;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(GdxTestRunner.class)
public class BakingTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Baking baking;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        baking = new Baking(game, utils, gameScreen);
    }

    public void simulateClick(ImageButton imageButton) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.setType(InputEvent.Type.touchDown);
        imageButton.fire(inputEvent);

        inputEvent.setType(InputEvent.Type.touchUp);
        imageButton.fire(inputEvent);
    }
    @Test
    public void bakingClickableIsNotNull() {
        ImageButton bakingClickable = baking.getBakingClickable();
        assertNotNull(bakingClickable);
    }

    @Test
    public void testClickPizza() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new Actor());
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        when(gameScreen.getSelected()).thenReturn(0);
        when(gameScreen.getPizzaAtBaking()).thenReturn(true);
        gameScreen.getCooks().get(0).CookBody.setX(64);
        gameScreen.getCooks().get(0).CookBody.setY(64);
        Ingredient potato = new Ingredient("potato", null, null, null);
        potato.prepare();
        Stack<Ingredient> stack = new Stack<>();
        stack.push(potato);
        gameScreen.getCooks().get(0).CookStack = stack;

        when(gameScreen.bakingUnlocked()).thenReturn(true);
        baking.onBakingButtonClicked(gameScreen);
        verify(gameScreen, times(1)).setPizzaAtBaking(false);

    }

    @Test
    public void testClickPotato() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new Actor());
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        when(gameScreen.getSelected()).thenReturn(0);
        when(gameScreen.getAtPotatoBaking()).thenReturn(true);
        gameScreen.getCooks().get(0).CookBody.setX(64);
        gameScreen.getCooks().get(0).CookBody.setY(64);
        Ingredient potato = new Ingredient("potato", null, null, null);
        potato.prepare();
        Stack<Ingredient> stack = new Stack<>();
        stack.push(potato);
        gameScreen.getCooks().get(0).CookStack = stack;

        when(gameScreen.bakingUnlocked()).thenReturn(true);
        baking.onBakingButtonClicked(gameScreen);
        verify(gameScreen, times(1)).setAtPotatoBaking(false);
    }




    // Add more tests as needed for other aspects of the Baking class
}

