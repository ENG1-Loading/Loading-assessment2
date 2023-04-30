package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Screens.GameScreen;

public class Money {

    private int currentMoney;
    private BitmapFont font;
    private PiazzaPanic game;
    private String difficulty;

    private double multiplier;

    /*
        * Constructor for the Money class
        * @param _game the game object
        * @param difficulty the difficulty of the game
     */
    public Money(PiazzaPanic _game, String difficulty) {
        this.font = new BitmapFont();
        font.setColor(new Color(0.0f, 1.0f, 0.39607844f, 1.0f));
        font.getData().setScale(0.45f);
        this.currentMoney = 0;
        this.game = _game;
        this.difficulty = difficulty;
        this.multiplier = 1.0;
    }
    /*
        * Constructor for the Money class
        * @param _game the game object
        * @param difficulty the difficulty of the game
     */
    public Money(PiazzaPanic _game, BitmapFont font) {
        this.font = font;
        font.setColor(new Color(0.52156866f, 0.73333335f, 0.39607844f, 1.0f));
        font.getData().setScale(0.45f);
        this.currentMoney = 0;
        this.game = _game;
        this.multiplier = 1.0;
    }
    /*
        * Adds money to the current money
        * @param amount the amount of money to add
     */
    public void addMoney(int amount) {
        if (difficulty.equals("Easy")) {
            currentMoney += amount;
        } else if (difficulty.equals("Medium")) {
            currentMoney += amount * 0.9;
        } else if (difficulty.equals("Hard")) {
            currentMoney += amount * 0.7;
        }
    }
    /*
        * Gets the current money
        * @return the current money
     */
    public int getCurrentMoney() {
        return currentMoney;
    }
    /*
        * Removes money from the current money
        * @param amount the amount of money to remove
     */
    public void removeMoney(int amount) {
        currentMoney -= amount;
    }
    /*
        * Adds a multiplier to the current multiplier
     */
    public void addMultiplier() {
        multiplier = multiplier + 0.5;
    }
    /*
        * Render method for the money class
        *
        * @param none
        * @return none
     */
    public void render() {
        game.batch.begin();
        font.draw(game.batch, "$" + currentMoney, 127, 132);
        game.batch.end();
    }

}
