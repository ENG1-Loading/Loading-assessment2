package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

// class for an ingredient
public class Ingredient {
    private final Texture prepdTex;
    private final Texture notPrepdTex;
    private final Texture burntTex;
    // patty, buns, lettuce, tomato
    public String name;
    private Texture currentTex;
    private Boolean prepared = false;

    private Boolean burnt = false;
    /*
        * Constructor for the ingredient
        * @param name the name of the ingredient
        * @param notPreparedTexture the texture for the ingredient when it is not prepared
        * @param preparedTexture the texture for the ingredient when it is prepared
        * @param burntTexture the texture for the ingredient when it is burnt
        * @return none
     */
    public Ingredient(String name, Texture notPreparedTexture, Texture preparedTexture, Texture burntTexture) {
        this.name = name;
        this.prepdTex = preparedTexture;
        this.notPrepdTex = notPreparedTexture;
        this.burntTex = burntTexture;
        currentTex = this.notPrepdTex;
    }
    /*
        * Returns the name of the ingredient
        * @param none
        * @return name the name of the ingredient
     */
    public boolean getState() {
        return prepared;
    }
    /*
        * Sets the ingredient to prepared
        * @param none
        * @return none
     */
    public void prepare() {
        this.prepared = true;
    }
    /*
        * Sets the ingredient to burnt
        * @param none
        * @return none
     */
    public void setBurnt() { this.burnt = true; }
    /*
        * Returns whether the ingredient is ready
        * @param ingredients the ingredients in the stack
        * @return whether the ingredient is ready
     */
    public void updateCurrentTexture() {
        if (prepared && !burnt) {
            currentTex = this.prepdTex;
        } else if (prepared && burnt) {
            currentTex = this.burntTex;
        } else {
            currentTex = this.notPrepdTex;
        }
    }
    /*
        * Returns whether the ingredient is ready
        * @param ingredients the ingredients in the stack
        * @return whether the ingredient is ready
     */
    @Override
    public boolean equals(Object ingredient) {
        Ingredient compare = (Ingredient) ingredient;
        if (this.name == compare.name) {
            return this.prepared == compare.prepared;
        }
        return false;
    }
    /*
        * Returns the texture for the ingredient
        * @param none
        * @return currentTex the texture for the ingredient
     */
    public Texture getCurrentTexture() {
        return currentTex;
    }
    /*
        * Returns whether the ingredient is burnt
        * @param none
        * @return burnt whether the ingredient is burnt
     */
    public Boolean getBurnt() {
        return burnt;
    }
}
