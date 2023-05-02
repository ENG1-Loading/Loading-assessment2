package com.mygdx.game.Clickables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

public class Cutting {
    ImageButton cuttingClickable;
    /*
        * Constructor for the cutting station
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public Cutting(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.cuttingClickable =  utils.createImageClickable(32, 32);
        // function exectutes when you press on the cutting station on screen
        // it sets the cutting station as the currently selected station - this moves the cook to the cutting station

        cuttingClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClicked(screen);
            }
        });
    }

    public void onClicked(GameScreen screen) {
        screen.setSationSelected(4);
        Array<Cook> cooks = screen.getCooks();
        int selected = screen.getSelected();
        if ((Math.abs(cooks.get(selected).CookBody.getY() - 28f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 48f) < 2)) {
            // Can only prep if the cook is not busy
            if (!(cooks.get(selected).isBusy)) {
                // used to limit to preping only one ingredient per press
                boolean ingredientDone = false;
                // preps the first vegetable in the current cook's stack after pressing the station again
                // while busy creates a progress bar to indicate when the cook can move again
                Ingredient selectedIngredient = null;
                for (Ingredient ingredient : cooks.get(selected).CookStack) {
                    if ((ingredient.name == "lettuce") && (!ingredient.getState()) && (!ingredientDone)) {
                        selectedIngredient = ingredient;
                    } else if ((ingredient.name == "tomato") && (!ingredient.getState()) && (!ingredientDone)) {
                        selectedIngredient = ingredient;
                    }
                }
                if (!(selectedIngredient == null)) {
                    cooks.get(selected).isBusy = true;
                    screen.createProgressBar(40, 50, cooks.get(selected));
                    selectedIngredient.prepare();
                    selectedIngredient.updateCurrentTexture();
                    ingredientDone = true;
                }
            }
        }
    }
    /*
        * Getter for the cutting station
        *
        * @param none
        *
        * @return the cutting station
     */
    public ImageButton getCuttingClickable() {
        return cuttingClickable;
    }

}
