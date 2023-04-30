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

    public Texture getOrderTexture(){
        return this.orderTex;
    }

    public Recipe getRecipe(){
        return this.orderRecipe;
    }

    public String getName(){
        return this.name;
    }

    public Integer getOrderTime(){
        return this.orderTime;
    }

    public void setOrderTime(Integer orderTime){
        this.orderTime = orderTime;
    }
}
