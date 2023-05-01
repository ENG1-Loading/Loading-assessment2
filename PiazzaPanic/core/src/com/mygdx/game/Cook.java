package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Powerups.Powerups;

import java.util.ArrayList;
import java.util.Stack;

// it would probably be a good idea to move a lot of the code from GameScreen to here

public class Cook {
    public Stack<Ingredient> CookStack;
    public Actor CookBody;
    public float[][] locations = {{0, 64}, {32, 64}, {64, 64}, {0, 32}, {48, 28}, {80, 48}, {0,-25}};
    public boolean isBusy = false;
    private float speed = 1f;
    private float x;
    private float y;
    public boolean moveable;
    /*
        * Constructor for the cook
        * @param skin the actor to be used as the cook
        * @return none
     */
    public Cook(Actor skin) {
        this.CookBody = skin;
        this.speed = 50f;
        this.CookStack = new Stack<>();
        this.moveable = false;
    }

    /*
        * Method to move a cook from their current position to a station
        * @param index the index of the station to move to
        * @param cook the cook to move
        * @param stations the list of stations
        * @param powerups the powerups object
        * @param tomove the index of the station to move to
        * @param isAutoMove whether the cook is being moved automatically
        * @return none
     */
    public void move(int index, Actor cook, ArrayList<Integer> stations, Powerups powerups, int tomove, boolean isAutoMove) {
        boolean canMove = true;
        if (moveable) {
            return;
        }
        if (!isAutoMove) {
            for (int i = 0; i < stations.size(); i++) {
                if ((stations.get(i) == index) && (i != tomove)) {
                    canMove = false;
                }
            }
        }
        if (canMove) {
            // method to move a cook from their current position to a station
            if (cook.getX() != locations[index][0] || cook.getY() != locations[index][1]) {
                // calculate the difference between 2 points to move the sprite towards
                float pathX = locations[index][0] - cook.getX();
                float pathY = locations[index][1] - cook.getY();
                // use Pythagoras to find the distance between current position and final position
                float distance = (float) Math.sqrt(pathX * pathX + pathY * pathY);
                float directionX = pathX / distance;
                float directionY = pathY / distance;
                // stop cook from moving once close enough to the station
                float speed;
                if (distance < 1) {
                    speed = 0f * powerups.getSpeedMultiplier();
                } else {
                    speed = 50f * powerups.getSpeedMultiplier();
                }

                cook.setX(cook.getX() + directionX * (speed * Gdx.graphics.getDeltaTime()));
                cook.setY(cook.getY() + directionY * (speed * Gdx.graphics.getDeltaTime()));
            }
        }
    }
    /*
    * Method to move a cook from their current position to a station
        * @param cook the cook to move
        * @return none
     */
    public void doUserInput(Cook cook) {
        //if (!moveable) {
        //    return;
        //}
        x = cook.CookBody.getX();
        y = cook.CookBody.getY();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += speed* Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += speed* Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= speed* Gdx.graphics.getDeltaTime();
        }
        if (!cook.isBusy) {
            cook.CookBody.setX(x);
            cook.CookBody.setY(y);
        }

    }

}
