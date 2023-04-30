package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;

// burger recipe made up of Ingredients
public class Burger implements Recipe {
    ArrayList<Ingredient> recipe;
    Texture bugerTex;
    Texture speechBubble;

    boolean isBurnt = false;

    /*
        * Constructor for the burger recipe
        * @param none
        * @return none
     */
    public Burger() {
        this.recipe = new ArrayList<Ingredient>();
        Ingredient patty = new Ingredient("patty", new Texture("rawPatty.png"), new Texture("prepdPatty.png"), new Texture("burntPatty.png"));
        patty.prepare();
        recipe.add(patty);
        Ingredient buns = new Ingredient("buns", new Texture("buns.png"), new Texture("buns.png"), null);
        buns.prepare();
        recipe.add(buns);
        Ingredient lettuce = new Ingredient("lettuce", new Texture("lettuce.png"), new Texture("prepdLettuce.png"), null);
        lettuce.prepare();
        recipe.add(lettuce);
        this.bugerTex = new Texture("burger.png");
        this.speechBubble = new Texture("orderBurgerBubble.png");
    }
    /*
        * Returns the recipe for the burger
        * @param none
        * @return recipe the recipe for the burger
     */
    @Override
    public ArrayList<Ingredient> getRecipe() {
        return recipe;
    }
    /*
        * Returns the texture for the burger
        * @param none
        * @return bugerTex the texture for the burger
     */
    @Override
    public Texture getTexture() {
        return bugerTex;
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
        * Returns whether the burger is burnt
        * @param none
        * @return isBurnt whether the burger is burnt
     */
    public Boolean getIsBurnt() { return isBurnt; }
    /*
        * Sets the burger to burnt
        * @param none
        * @return none
     */
    public void setBurnt() {
        isBurnt = true;
    }
    /*
        * Returns whether the burger is ready
        * @param ingredients the ingredients in the stack
        * @return whether the burger is ready
     */
    @Override
    public Boolean has(Stack<Ingredient> ingredients) {
        boolean foundBuns = false;
        boolean foundPatty = false;
        boolean foundLettuce = false;
        Ingredient buns = new Ingredient("buns", null, null, null);
        buns.prepare();
        Ingredient patty = new Ingredient("patty", null, null, null);
        patty.prepare();
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        lettuce.prepare();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.equals(buns)) {
                foundBuns = true;
            }
            if (ingredient.equals(patty) && !ingredient.getBurnt()) {
                foundPatty = true;
                if (patty.getBurnt()) {
                    isBurnt = true;
                }
            }
            if (ingredient.equals(lettuce)) {
                foundLettuce = true;
            }
        }
        return foundBuns && foundLettuce && foundPatty;
    }
}
