package com.mygdx.game.Clickables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

public class Pantry {
    ImageButton pantryClickable;
    /*
        * Constructor for the pantry
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public Pantry(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.pantryClickable =  utils.createImageClickable(32, 32);
        Array<Cook> cooks = screen.getCooks();
        // function exectutes when you press on the pantry on screen
        // it sets the pantry as the currently selected station - this moves the cook to the pantry
        // when the cook arrives the pantry screen is shown
        pantryClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Array<Cook> cooks = screen.getCooks();
                screen.setSationSelected(0);
                int selected = screen.getSelected();
                if ((Math.abs(cooks.get(selected).CookBody.getY() - 64f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 0f) < 2)) {
                    screen.setShowPantryScreen(true);
                    cooks.get(selected).isBusy = true;
                }
            }
        });

    }
    /*
        * Getter for the pantry
        * @param none
        *
        * @return pantryClickable the pantry
     */
    public ImageButton getPantryClickable() {
        return pantryClickable;
    }

}
