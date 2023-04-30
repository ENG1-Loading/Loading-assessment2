package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;

public interface Recipe {
    /*
        * Returns the recipe for the food
        * @param none
        * @return recipe the recipe for the food
     */
    ArrayList<Ingredient> getRecipe();
    /*
        * Returns the texture for the food
        * @param none
        * @return texture the texture for the food
     */
    Texture getTexture();
    /*
        * Returns the texture for the speech bubble
        * @param none
        * @return speechBubble the texture for the speech bubble
     */
    Texture getSpeechBubbleTexture();
    /*
        * Checks if the stack of ingredients has the food
        * @param ingredients the stack of ingredients
        * @return foundFood true if the stack of ingredients has the food, false otherwise
     */
    Boolean has(Stack<Ingredient> ingredients);
    /*
        * Returns whether the food is burnt
        * @param none
        * @return isBurnt true if the food is burnt, false otherwise
     */
    Boolean getIsBurnt();
}
