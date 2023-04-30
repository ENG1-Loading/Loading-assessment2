package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ConfigHandler;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Order {
    Texture orderTex;
    Recipe orderRecipe;
    String name;
    ConfigHandler configHandler;
    //default order time, 40 seconds
    public Integer orderTime = 40;
    /*
        * Constructor for the order
        * @param name the name of the order
        * @param orderTexture the texture for the order
        * @param orderRecipe the recipe for the order
        * @return none
     */
    public Order(String name,Texture orderTexture, Recipe orderRecipe) throws IOException {
        String difficulty;
        try {

            configHandler = new ConfigHandler();

            difficulty = configHandler.getDifficulty();
        } catch (NoSuchFileException e) {
            difficulty = "Easy";
        }
        this.name = name;
        this.orderTex = orderTexture;
        this.orderRecipe = orderRecipe;
        switch (difficulty) {
            case "Medium":
                orderTime = 35;
                break;
            case "Hard":
                orderTime = 30;
                break;
            default:
                orderTime = 40;
        }
    }
    /*
        * Returns the texture for the order
        * @param none
        * @return orderTex the texture for the order
     */
    public Texture getOrderTexture(){
        return this.orderTex;
    }
    /*
        * Returns the recipe for the order
        * @param none
        * @return orderRecipe the recipe for the order
     */
    public Recipe getRecipe(){
        return this.orderRecipe;
    }
    /*
        * Returns the name of the order
        * @param none
        * @return name the name of the order
     */
    public String getName(){
        return this.name;
    }
    /*
        * Returns the order time
        * @param none
        * @return orderTime the order time
     */
    public Integer getOrderTime(){
        return this.orderTime;
    }
    /*
        * Sets the order time
        * @param orderTime the order time
        * @return none
     */
    public void setOrderTime(Integer orderTime){
        this.orderTime = orderTime;
    }
}
