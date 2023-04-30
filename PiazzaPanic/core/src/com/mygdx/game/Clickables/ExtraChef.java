package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;

public class ExtraChef {
    ImageButton extraChefClickable;
    /*
        * Constructor for the extra chef powerup
        * @param utils the utils object
        * @param screen the game screen
        * @param powerups the powerups object
        *
        * @return none
     */
    public ExtraChef(Utils utils, final GameScreen screen, final Powerups powerups) {
        extraChefClickable = utils.createImageClickable(new Texture("stopwatchplus.png"), 24, 24);
        extraChefClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean bought = powerups.setChefCount(3);
                if (bought) {
                    screen.setChef(3);
                }
            }
        });
    }
    /*
        * Getter for the extra chef powerup
        *
        * @param none
        *
        * @return the extra chef powerup
     */
    public ImageButton getExtraChefClickable() {return extraChefClickable;}

}
