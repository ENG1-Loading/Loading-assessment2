package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;

public class Pizza implements Recipe {
    ArrayList<Ingredient> recipe;
    Texture pizzaTex;
    Texture speechBubble;

    boolean isBurnt;
    /*
        * Constructor for the pizza recipe
        * @param none
        * @return none
     */
    public Pizza() {
        this.recipe = new ArrayList<Ingredient>();
        Ingredient pizzaBase = new Ingredient("pizza", new Texture("rawPizza.png"), new Texture("prepdPizza.png"), null);
        pizzaBase.prepare();
        recipe.add(pizzaBase);
        Ingredient lettuce = new Ingredient("lettuce", new Texture("lettuce.png"), new Texture("prepdLettuce.png"), null);
        lettuce.prepare();
        recipe.add(lettuce);

        this.pizzaTex = new Texture("prepdPizza.png");
        this.speechBubble = new Texture("orderPizzaBubble.png");
    }
    /*
        * Returns the recipe for the pizza
        * @param none
        * @return recipe the recipe for the pizza
     */
    @Override
    public ArrayList<Ingredient> getRecipe() {
        return recipe;
    }
    /*
        * Returns the texture for the pizza
        * @param none
        * @return pizzaTex the texture for the pizza
     */
    @Override
    public Texture getTexture() {
        return pizzaTex;
    }
    /*
        * Returns the texture for the speech bubble
        * @param none
        * @return speechBubble the texture for the speech bubble
     */
    @Override
    public Texture getSpeechBubbleTexture() {
        return speechBubble;
    }
    /*
        * Returns whether the stack of ingredients matches the recipe
        * @param ingredients the stack of ingredients to be checked
        * @return true if the stack of ingredients matches the recipe, false otherwise
     */
    @Override
    public Boolean has(Stack<Ingredient> ingredients) {
        boolean foundPizzaBase = false;
        boolean foundLettuce = false;
        Ingredient pizzaBase = new Ingredient("pizza", null, null, null);
        pizzaBase.prepare();
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        lettuce.prepare();
        for (Ingredient ingredient: ingredients) {
            if (ingredient.equals(pizzaBase) && !ingredient.getBurnt()) {
                foundPizzaBase = true;
            }
            if (ingredient.equals(lettuce)) {
                foundLettuce = true;
            }
        }
        return foundPizzaBase && foundLettuce;
    }
    /*
        * Sets the pizza texture to the burnt pizza texture
        * @param none
        * @return none
     */
    public void setBurnt() {
        isBurnt = true;
        pizzaTex = new Texture("burntPizza.png");
    }
    /*
        * Returns whether the pizza is burnt
        * @param none
        * @return true if the pizza is burnt, false otherwise
     */
    public Boolean getIsBurnt() {
        return isBurnt;
    }
}
