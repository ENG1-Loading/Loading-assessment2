package com.mygdx.game.Clickables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

public class Serving {
    ImageButton servingClickable;
    /*
        * Constructor for the serving station
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public Serving(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.servingClickable =  utils.createImageClickable(32, 56);
        // function exectutes when you press on the serving station on screen
        // it sets the serving station as the currently selected station - this moves the cook to the serving station
        // when the cook arrives the serving screen is shown
        servingClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setSationSelected(5);
                Array<Cook> cooks = screen.getCooks();
                int selected = screen.getSelected();

                if ((Math.abs(cooks.get(selected).CookBody.getY() - 48f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 80f) < 2)) {
                    screen.setShowServingScreen(true);
                    cooks.get(selected).isBusy = true;
                }
            }
        });
    }
    /*
        * Getter for the serving station
        * @param none
        *
        * @return servingClickable the serving station
     */
    public ImageButton getServingClickable() {
        return servingClickable;
    }

}
