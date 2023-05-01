package com.mygdx.tests.FoodTests;

import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Salad;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;


@RunWith(GdxTestRunner.class)
public class SaladTest {
    private Salad salad;

    @Before
    public void setUp() {
        salad = new Salad();
    }

    @Test
    public void saladIsNotNull() {
        assert salad != null;
    }

    @Test
    public void saladHasRecipe() {
        assert salad.getRecipe() != null;
    }

    @Test
    public void saladHasTexture() {
        assert salad.getTexture() != null;
    }

    @Test
    public void saladHasSpeech() {
        assert salad.getSpeechBubbleTexture() != null;
    }

    @Test
    public void saladCanServe() {
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        lettuce.prepare();
        Ingredient tomato = new Ingredient("tomato", null, null, null);
        tomato.prepare();
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(lettuce);
        ingredients.push(tomato);
        assert salad.has(ingredients) == true;
    }

    @Test
    public void saladCantServe() {
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        Ingredient tomato = new Ingredient("tomato", null, null, null);
        tomato.prepare();
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(lettuce);
        assert salad.has(ingredients) == false;
    }



}
