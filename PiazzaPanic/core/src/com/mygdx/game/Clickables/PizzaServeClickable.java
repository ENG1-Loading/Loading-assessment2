package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Pizza;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class PizzaServeClickable {
    ImageButton pizzaServeClickable;
    /*
        * Constructor for the pizza station
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
        *
     */
    public PizzaServeClickable(PiazzaPanic _game, Utils utils, final GameScreen screen) {
        this.pizzaServeClickable = utils.createImageClickable(new Texture("prepdPizza.png"), 24, 24);
        pizzaServeClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Array<Cook> cooks = screen.getCooks();
                int selected = screen.getSelected();
                ArrayList<ArrayList<Customer>> customers = screen.getCustomers();
                int customerCount = screen.getCustomerCount();
                Pizza recipe = new Pizza();
                Ingredient pizzaBase = new Ingredient("pizza", null, null, null);
                pizzaBase.prepare();
                Ingredient lettuce = new Ingredient("lettuce", null, null,  null);
                lettuce.prepare();
                if (recipe.has(cooks.get(selected).CookStack)) {
                    for (int i=0;i<customers.get(customerCount).size(); i++) {
                        if (customers.get(customerCount).get(i).customerOrder.getName() == "pizza") {
                            if (!customers.get(customerCount).get(i).selfComplete == true) {
                                cooks.get(selected).CookStack.remove(pizzaBase);
                                cooks.get(selected).CookStack.remove(lettuce);

                                customers.get(customerCount).get(i).selfComplete = true;
                                screen.getMoney().addMoney(100);
                                screen.hideServingScreen();
                                cooks.get(selected).isBusy = false;
                                return ;
                            }
                        } else {
                        }
                    }
                }

            }
        });
    }
    /*
        * Getter for the pizza station
        *
        * @param none
        *
        * @return the pizza station
     */
    public ImageButton getPizzaServeClickable() {
        return pizzaServeClickable;
    }
}
