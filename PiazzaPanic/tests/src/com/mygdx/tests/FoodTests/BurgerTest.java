package com.mygdx.tests.FoodTests;

import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

@RunWith(GdxTestRunner.class)
public class BurgerTest {
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void burgerIsNotNull() {
        assert burger != null;
    }

    @Test
    public void burgerHasRecipe() {
        assert burger.getRecipe() != null;
    }

    @Test
    public void burgerHasTexture() {
        assert burger.getTexture() != null;
    }

    @Test
    public void burgerHasSpeech() {
        assert burger.getSpeechBubbleTexture() != null;
    }

    @Test
    public void burgerNotBurned() {
        assert burger.getIsBurnt() == false;
    }

    @Test
    public void burgerCanBurn() {
        burger.setBurnt();
        assert burger.getIsBurnt() == true;
    }

    @Test
    public void burgerCanServe() {
        Ingredient buns = new Ingredient("buns", null, null, null);
        buns.prepare();
        Ingredient patty = new Ingredient("patty", null, null, null);
        patty.prepare();
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        lettuce.prepare();
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(buns);
        ingredients.push(patty);
        ingredients.push(lettuce);
        assert burger.has(ingredients) == true;
    }

    @Test
    public void burgerNotServe() {
        Ingredient buns = new Ingredient("buns", null, null, null);
        buns.prepare();
        Ingredient patty = new Ingredient("patty", null, null, null);
        patty.prepare();
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        lettuce.prepare();
        Stack<Ingredient> ingredients = new Stack<Ingredient>();
        ingredients.push(buns);
        ingredients.push(lettuce);
        assert burger.has(ingredients) == false;
    }
}
