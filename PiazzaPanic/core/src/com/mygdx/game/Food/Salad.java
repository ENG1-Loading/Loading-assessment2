package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;

// salad recipe made up of Ingredients
public class Salad implements Recipe {
    ArrayList<Ingredient> recipe;
    Texture saladTex;
    Texture speechBubble;

    public Salad() {
        this.recipe = new ArrayList<Ingredient>();

        Ingredient lettuce = new Ingredient("lettuce", new Texture("lettuce.png"), new Texture("prepdLettuce.png"), null);
        lettuce.prepare();
        recipe.add(lettuce);
        Ingredient tomato = new Ingredient("tomato", new Texture("tomato.png"), new Texture("prepdTomato.png"), null);
        tomato.prepare();
        recipe.add(tomato);

        this.saladTex = new Texture("salad.png");
        this.speechBubble = new Texture("orderSaladBubble.png");
    }

    @Override
    public ArrayList<Ingredient> getRecipe() {
        return recipe;
    }

    @Override
    public Texture getTexture() {
        return saladTex;
    }

    @Override
    public Texture getSpeechBubbleTexture() {
        return speechBubble;
    }

    public Boolean has(Stack<Ingredient> ingredients) {
        boolean foundTomato = false;
        boolean foundLettuce = false;
        Ingredient tomato = new Ingredient("tomato", null, null, null);
        tomato.prepare();
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        lettuce.prepare();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.equals(tomato)) {
                foundTomato = true;
            }
            if (ingredient.equals(lettuce)) {
                foundLettuce = true;
            }
        }
        return foundTomato && foundLettuce;
    }

    @Override
    public Boolean getIsBurnt() {
        return null;
    }
}
