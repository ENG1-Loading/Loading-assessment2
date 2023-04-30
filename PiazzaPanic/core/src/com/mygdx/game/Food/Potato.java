package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;

public class Potato implements Recipe{
    ArrayList<Ingredient> recipe;
    Texture potatoTex;
    Texture speechBubble;
    boolean isBurnt = false;
    /*
        * Constructor for the potato recipe
        * @param none
        * @return none
     */
    public Potato() {
        this.recipe = new ArrayList<Ingredient>();
        Ingredient potato = new Ingredient("potato", new Texture("potato.png"), new Texture("potatoCooked.png"), new Texture("burntPotato.png"));
        potato.prepare();
        recipe.add(potato);
        this.potatoTex = new Texture("potatoCooked.png");
        this.speechBubble = new Texture("orderPotatoBubble.png");
    }
    /*
        * Returns the recipe for the potato
        * @param none
        * @return recipe the recipe for the potato
     */
    @Override
    public ArrayList<Ingredient> getRecipe() {
        return recipe;
    }
    /*
        * Returns the texture for the potato
        * @param none
        * @return potatoTex the texture for the potato
     */
    @Override
    public Texture getTexture() {
        return potatoTex;
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
        * Checks if the stack of ingredients has the potato
        * @param ingredients the stack of ingredients
        * @return foundPotato true if the stack of ingredients has the potato, false otherwise
     */
    @Override
    public Boolean has(Stack<Ingredient> ingredients) {

        boolean foundPotato = false;
        Ingredient potato = new Ingredient("potato", null, null, null);
        potato.prepare();
        for (Ingredient ingredient: ingredients) {

            if (ingredient.equals(potato) && !ingredient.getBurnt()) {
                foundPotato = true;
            } else {
            }
        }
        return foundPotato;
    }
    /*
        * Checks if the stack of ingredients matches the recipe
        * @param ingredients the stack of ingredients to be checked
        * @return true if the stack of ingredients matches the recipe, false otherwise
     */
    public void setBurnt() {
        isBurnt = true;
        potatoTex = new Texture("burntPotato.png");
    }
    /*
        * Returns whether the potato is burnt
        * @param none
        * @return isBurnt true if the potato is burnt, false otherwise
     */
    public Boolean getIsBurnt() {
        return isBurnt;
    }
}
