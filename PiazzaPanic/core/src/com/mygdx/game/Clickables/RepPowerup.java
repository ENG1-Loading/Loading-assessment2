package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;

public class RepPowerup {
    ImageButton repButton;
    public RepPowerup(Utils utils, final GameScreen screen, final Powerups powerups ) {
        repButton = utils.createImageClickable(new Texture("REPHeart.png"), 24, 24);
        repButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("buying rep");
                boolean repbought = powerups.buyRep();
                if (repbought) {
                    screen.addRep(1);
                }
            }
        });


    }

    public ImageButton getRepButton() {
        return repButton;
    }
}
