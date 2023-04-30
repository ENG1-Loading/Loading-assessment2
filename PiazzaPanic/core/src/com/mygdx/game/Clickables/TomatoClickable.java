package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Screens.GameScreen;

public class TomatoClickable {
    ImageButton tomatoClickable;
    /*
        * Constructor for the tomato clickable
        * @param utils the utils object
        * @param screen the game screen
        * @return none
     */
    public TomatoClickable(Utils utils, final GameScreen screen) {
        tomatoClickable = utils.createImageClickable(new Texture("tomato.png"), 24, 24);
        tomatoClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Array<Cook> cooks = screen.getCooks();
                int selected = screen.getSelected();
                if (cooks.get(selected).CookStack.size() < 5) {
                    cooks.get(selected).CookStack.push(new Ingredient("tomato", new Texture("tomato.png"), new Texture("prepdTomato.png"), null));
                }
            }
        });


    }
    /*
        * Getter for the tomato clickable
        * @param none
        * @return the tomato clickable
     */
    public ImageButton getTomatoClickable() {
        return tomatoClickable;
    }
}
