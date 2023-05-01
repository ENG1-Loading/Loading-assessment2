package com.mygdx.tests.FoodTests;

import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Potato;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

@RunWith(GdxTestRunner.class)
public class PizzaTest {
    private Potato potato;
    @Before
    public void setUp() {
        potato = new Potato();
    }

    @Test
    public void potatoIsNotNull() {
        assert potato != null;
    }

    @Test
    public void potatoHasRecipe() {
        assert potato.getRecipe() != null;
    }

    @Test
    public void potatoHasTexture() {
        assert potato.getTexture() != null;
    }

    @Test
    public void potatoHasSpeech() {
        assert potato.getSpeechBubbleTexture() != null;
    }

    @Test
    public void potatoNotBurned() {
        assert potato.getIsBurnt() == false;
    }

    @Test
    public void potatoCanBurn() {
        potato.setBurnt();
        assert potato.getIsBurnt() == true;
    }

    @Test
    public void potatoCanServe() {
        Ingredient potatoS = new Ingredient("potato", null, null, null);
        potatoS.prepare();
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(potatoS);
        assert potato.has(ingredients) == true;
    }
    @Test
    public void potatoCanNotServe() {
        Ingredient potatoS = new Ingredient("potato", null, null, null);
        potatoS.prepare();
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        assert potato.has(ingredients) == false;
    }
}
