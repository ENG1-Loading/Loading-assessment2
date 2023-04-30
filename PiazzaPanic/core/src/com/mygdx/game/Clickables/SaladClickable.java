package com.mygdx.game.Clickables;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

public class SaladClickable {
    ImageButton saladClickable;
    /*
        * Constructor for the salad station
        *
        * @param _game the game object
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public SaladClickable(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.saladClickable = utils.createImageClickable(new Texture("salad.png"),24,24);
        saladClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Array<Cook> cooks = screen.getCooks();
                ArrayList<ArrayList<Customer>> customers = screen.getCustomers();
                int selected = screen.getSelected();
                int customerCount = screen.getCustomerCount();
                com.mygdx.game.Food.Salad recipe = new com.mygdx.game.Food.Salad();
                Ingredient tomato = new Ingredient("tomato", null, null, null);
                tomato.prepare();
                Ingredient lettuce = new Ingredient("lettuce", null, null, null);
                lettuce.prepare();
                if (recipe.has(cooks.get(selected).CookStack)) {
                    for (int i = 0; i < customers.get(customerCount).size(); i++) {
                    if (customers.get(customerCount).get(i).customerOrder.getName() == "salad") {
                        if (!customers.get(customerCount).get(i).selfComplete) {
                            cooks.get(selected).CookStack.remove(tomato);
                            cooks.get(selected).CookStack.remove(lettuce);
                            customers.get(customerCount).get(i).selfComplete = true;
                            screen.getMoney().addMoney(100);
                            screen.hideServingScreen();
                            cooks.get(selected).isBusy = false;
                            return;
                        }
                        }
                        
                }
                } else {
                    // some or all ingredients are not in the current cook's stack
                }
                
            }
        });

    }
    /*
        * Getter for the salad clickable
        *
        * @param none
        *
        * @return the salad clickable
     */
    public ImageButton getSaladClickable() {
        return saladClickable;
    }
}
