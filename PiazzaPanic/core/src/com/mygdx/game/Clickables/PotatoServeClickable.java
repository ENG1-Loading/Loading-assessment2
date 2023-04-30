package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Potato;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class PotatoServeClickable {
    ImageButton potatoServeClickable;
    /*
        * Constructor for the potato station
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public PotatoServeClickable(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.potatoServeClickable = utils.createImageClickable(new Texture("potatoCooked.png"), 24, 24);
        potatoServeClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Array<Cook> cooks = screen.getCooks();
                int selected = screen.getSelected();
                ArrayList<ArrayList<Customer>> customers = screen.getCustomers();
                int customerCount = screen.getCustomerCount();
                Potato recipe = new Potato();
                Ingredient potato = new Ingredient("potato", null, null, null);
                potato.prepare();
                if (recipe.has(cooks.get(selected).CookStack)) {
                    for (int i=0; i<customers.get(customerCount).size();i++) {
                        if (!customers.get(customerCount).get(i).selfComplete == true) {
                            cooks.get(selected).CookStack.remove(potato);
                            customers.get(customerCount).get(i).selfComplete = true;
                            screen.getMoney().addMoney(100);
                            screen.hideServingScreen();
                            cooks.get(selected).isBusy = false;
                            return;
                        }
                    }
                } else {
                }
            }
        });
    }
    /*
        * Getter for the potato serve clickable
        *
        * @param none
        *
        * @return potatoServeClickable
     */
    public ImageButton getPotatoServeClickable() {
        return potatoServeClickable;
    }
}
