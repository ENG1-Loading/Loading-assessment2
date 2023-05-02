package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Salad;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

import java.util.ArrayList;

public class PotatoClickable {
    ImageButton potatoClickable;
    /*
        * Constructor for the potato station
        * @param utils the utils object
        * @param screen the game screen
        *
        * @return none
     */
    public PotatoClickable(Utils utils, final GameScreen screen) {
        this.potatoClickable = utils.createImageClickable(new Texture("potato.png"), 24,24);
        potatoClickable.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               onClick(screen);

           }
        });

    }
    public void onClick(GameScreen screen) {
        if (screen.bakingUnlocked()) {
            Array<Cook> cooks = screen.getCooks();
            int selected = screen.getSelected();
            if (cooks.get(selected).CookStack.size() < 5) {
                cooks.get(selected).CookStack.push(new Ingredient("potato", new Texture("potato.png"), new Texture("potatoCooked.png"), null));

            }
        }
    }
    /*
        * Getter for the potato station
        *
        * @param none
        *
        * @return the potato station
     */
    public ImageButton getPotatoClickable() {
        return potatoClickable;
    }
}

