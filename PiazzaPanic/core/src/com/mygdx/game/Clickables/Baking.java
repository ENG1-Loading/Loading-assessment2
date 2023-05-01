package com.mygdx.game.Clickables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Screens.GameScreen;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.badlogic.gdx.graphics.Texture;

public class Baking {
    ImageButton bakingClickable;

    /*
        * Constructor for the baking station
        * @param _game the game object
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public Baking(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.bakingClickable = utils.createImageClickable(32, 32);
        // function exectutes when you press on the baking station on screen
        // it sets the baking station as the currently selected station - this moves the
        // cook to the baking station

        bakingClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Baking Clicked");
                Array<Cook> cooks = screen.getCooks();
                int selected = screen.getSelected();
                screen.setSationSelected(2);

                Ingredient bakedPizza = new Ingredient("pizza", new Texture("rawPizza.png"),
                        new Texture("prepdPizza.png"), new Texture("burntPizza.png"));
                bakedPizza.prepare();
                bakedPizza.updateCurrentTexture();
                Ingredient bakedPotato = new Ingredient("potato", new Texture("potato.png"), new Texture("potatoCooked.png"), new Texture("burntPotato.png"));
                bakedPotato.prepare();
                bakedPotato.updateCurrentTexture();
                if ((Math.abs(screen.getCooks().get(screen.getSelected()).CookBody.getY() - 64f) < 2)
                        && (Math.abs(cooks.get(selected).CookBody.getX() - 64f) < 2) && screen.bakingUnlocked()) {
                    if (!(screen.getCooks().get(screen.getSelected()).isBusy)) {
                        // used to limit to preping only one ingredient per press
                        boolean ingredientDone = false;
                        Ingredient selectedIngredient = null;
                        // preps the first vegetable in the current cook's stack after pressing the
                        // station again
                        // while busy creates a progress bar to indicate when the cook can move again
                        for (Ingredient ingredient : screen.getCooks().get(screen.getSelected()).CookStack) {
                            if ((ingredient.name == "pizza" || ingredient.name == "potato") && (!ingredient.getState()) && (!ingredientDone)) {
                                selectedIngredient = ingredient;
                            } else {
                            }
                        }
                        if (!(selectedIngredient == null)) {
                            cooks.get(selected).isBusy = true;
                            screen.createProgressBar(80, 86, cooks.get(selected));
                            screen.incrementBakingClicked();
                            // used for the flipping mechanism (the station has to be pressed twice for the
                            // patty to be prepared)
                            if ((screen.getBakingClicked()) % 2 == 0) {
                                ingredientDone = true;
                                if (selectedIngredient.name == "pizza") {
                                    cooks.get(selected).CookStack.push(bakedPizza);
                                    screen.setPizzaAtBaking(false);
                                } else if (selectedIngredient.name == "potato") {
                                    cooks.get(selected).CookStack.push(bakedPotato);
                                    screen.setAtPotatoBaking(false);
                                }

                            } else {
                                cooks.get(selected).CookStack.remove(selectedIngredient);
                                if (selectedIngredient.name == "pizza") {
                                    screen.setPizzaAtBaking(true);

                                } else if(selectedIngredient.name == "potato") {
                                    screen.setAtPotatoBaking(true);
                                }
                            }
                        } else {
                            if (screen.getPizzaAtBaking()) {
                                cooks.get(selected).isBusy = true;
                                screen.createProgressBar(80, 86, cooks.get(selected));
                                screen.incrementBakingClicked();
                                cooks.get(selected).CookStack.push(bakedPizza);
                                screen.setPizzaAtBaking(false);
                            } else if (screen.getAtPotatoBaking()) {
                                cooks.get(selected).isBusy = true;
                                screen.createProgressBar(80, 86, cooks.get(selected));
                                screen.incrementBakingClicked();
                                cooks.get(selected).CookStack.push(bakedPotato);
                                screen.setAtPotatoBaking(false);
                            }
                        }
                    }
                } else {

                }
            }
        });
    }
    /*
        * Getter for the baking station
        * @param none
        *
        * @return the baking station
     */
    public ImageButton getBakingClickable() {
        return bakingClickable;
    }

}
