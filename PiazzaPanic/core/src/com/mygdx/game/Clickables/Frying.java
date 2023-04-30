package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

public class Frying {
    ImageButton fryingClickable;

    boolean upgraded = false;
    /*
        * Constructor for the frying station
        *
        * @param utils the utils object
        * @param screen the game screen
        * @param game the game object
        *
        * @return none
     */
    public Frying(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.fryingClickable = utils.createImageClickable(32, 32);

        // function executes when you press on the frying station on screen
        // it sets the frying station as the currently selected station - this moves the
        // cook to the frying station

        fryingClickable.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                Array<Cook> cooks = screen.getCooks();
                int selected = screen.getSelected();
                screen.setSationSelected(1);
                // stationSelected.set(selected, 1);
                // boolean ingredientAtStation = false;
                Ingredient cookedPatty = new Ingredient("patty", new Texture("rawPatty.png"),
                        new Texture("prepdPatty.png"), new Texture("burntPatty.png"));
                cookedPatty.prepare();
                cookedPatty.updateCurrentTexture();
                if ((Math.abs(screen.getCooks().get(screen.getSelected()).CookBody.getY() - 64f) < 2)
                        && (Math.abs(cooks.get(selected).CookBody.getX() - 32f) < 2)) {
                    if (!(screen.getCooks().get(screen.getSelected()).isBusy)) {
                        // used to limit to preparing only one ingredient per press
                        boolean ingredientDone = false;
                        Ingredient selectedIngredient = null;
                        // preps the first vegetable in the current cook's stack after pressing the
                        // station again
                        // while busy creates a progress bar to indicate when the cook can move again
                        for (Ingredient ingredient : screen.getCooks().get(screen.getSelected()).CookStack) {
                            if ((ingredient.name == "patty") && (!ingredient.getState()) && (!ingredientDone)) {
                                selectedIngredient = ingredient;
                            }
                        }
                        if (!(selectedIngredient == null)) {

                            cooks.get(selected).isBusy = true;
                            screen.createProgressBar(24, 86, cooks.get(selected));
                            screen.incrementFryingClicked();
                            // used for the flipping mechanism (the station has to be pressed twice for the
                            // patty to be prepared)
                            if ((screen.getFryingClicked()) % 2 == 0) {
                                ingredientDone = true;
                                cooks.get(selected).CookStack.push(cookedPatty);
                                screen.setPattyAtFrying(false);
                            } else {
                                cooks.get(selected).CookStack.remove(selectedIngredient);
                                screen.setPattyAtFrying(true);
                            }
                        } else {
                            // create message to indicate that there are no ingredients in the current
                            // cook's stack to be prepared
                            if (screen.getPattyAtFrying()) {
                                cooks.get(selected).isBusy = true;
                                screen.createProgressBar(24, 86, cooks.get(selected));
                                screen.incrementFryingClicked();
                                cooks.get(selected).CookStack.push(cookedPatty);
                                screen.setPattyAtFrying(false);
                            }
                        }
                    }
                }
            }
        });
    }
    /*
        * Getter for the frying station
        *
        * @param none
        *
        * @return fryingClickable the frying station
     */
    public ImageButton createFryingClickable() {
        return fryingClickable;
    }



}
