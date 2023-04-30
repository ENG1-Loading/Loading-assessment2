package com.mygdx.game.Clickables;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Helpers;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

public class BurgerClickable {
    ImageButton burgerClickable;
    public BurgerClickable(PiazzaPanic _game, Utils utils, final GameScreen screen){
        this.burgerClickable = utils.createImageClickable(new Texture("burger.png"), 24, 24);
        burgerClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Array<Cook> cooks = screen.getCooks();
                int selected = screen.getSelected();
                ArrayList<ArrayList<Customer>> customers = screen.getCustomers();
                int customerCount = screen.getCustomerCount();
                Burger recipe = new Burger();
                Ingredient buns = new Ingredient("buns", null, null, null);
                buns.prepare();
                Ingredient patty = new Ingredient("patty", null, null, null);
                patty.prepare();
                Ingredient lettuce = new Ingredient("lettuce", null, null, null);
                lettuce.prepare();
                if (recipe.has(cooks.get(selected).CookStack)) {
                    for (int i = 0; i < customers.get(customerCount).size(); i++) {
                        if (customers.get(customerCount).get(i).customerOrder.getName() == "burger") {
                            if (!customers.get(customerCount).get(i).selfComplete == true) {
                                cooks.get(selected).CookStack.remove(buns);
                                cooks.get(selected).CookStack.remove(patty);
                                cooks.get(selected).CookStack.remove(lettuce);
                                
                                customers.get(customerCount).get(i).selfComplete = true;
                                screen.getMoney().addMoney(100);
                                screen.hideServingScreen();
                                cooks.get(selected).isBusy = false;
                                return;
                            }
                            
                        } else {
                        }
                    }
                    
                } else {
                    // some or all ingredients are not in the current cook's stack
                }

            }
        });

    }

    public ImageButton getBurgerClickable() {
        return burgerClickable;
    }
}
