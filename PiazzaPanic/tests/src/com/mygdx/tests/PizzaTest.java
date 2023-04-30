package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.Pantry;
import com.mygdx.game.Clickables.PizzaClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
@RunWith(GdxTestRunner.class)
public class PizzaTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private PizzaClickable pizzaClickable;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        pizzaClickable= new PizzaClickable( utils,gameScreen);
    }

    @Test
    public void pizzaClickableIsNotNull() {
        ImageButton pizzaClickable1 = pizzaClickable.getPizzaClickable();
        assertNotNull(pizzaClickable1);
    }
}
