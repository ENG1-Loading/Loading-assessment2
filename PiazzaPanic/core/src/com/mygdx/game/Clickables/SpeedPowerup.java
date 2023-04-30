package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;

public class SpeedPowerup {
    ImageButton speedClickable;
    /*
        * Constructor for the speed powerup
        *   @param utils the utils object
        *  @param screen the game screen
        * @param powerups the powerups object
        * @return none
        *
     */
    public SpeedPowerup(Utils utils, final GameScreen screen, final Powerups powerups ) {
        speedClickable = utils.createImageClickable(new Texture("runningManPowerup.png"), 24, 24);
        speedClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                powerups.setSpeedMultiplier(2);
            }
        });
    }
    /*
        * Getter for the speed powerup
        * @param none
        * @return the speed powerup
     */
    public ImageButton getSpeedClickable() {
        return speedClickable;
    }
}