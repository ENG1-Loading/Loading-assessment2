package com.mygdx.game.Clickables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

public class Bin {
    ImageButton binClickable;
    /*
        * Constructor for the bin station
        * @param _game the game object
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public Bin(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.binClickable =  utils.createImageClickable(32, 32);
        // function exectutes when you press on the bin station on screen
        // it sets the bin station as the currently selected station - this moves the cook to the bin station
        // if the cook is by the bin and presses on the bin it deletes the top ingredient on the current cook's stack

        binClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick(screen);
            }
        });
    }

    public void onClick(GameScreen screen) {
        Array<Cook> cooks = screen.getCooks();
        int selected = screen.getSelected();
        screen.setSationSelected(3);
        // if statement that checks if the current cook is at the bin
        if ((Math.abs(cooks.get(selected).CookBody.getY() - 32f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 0f) < 2)) {
            if (cooks.get(selected).CookStack.size() > 0) {
                cooks.get(selected).CookStack.pop();
            }
        }
    }
    /*
        * Getter for the bin station
        *
        * @param none
        *
        * @return the bin station
     */
    public ImageButton getBinClickable() {
        return binClickable;
    }
}
