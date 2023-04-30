package com.mygdx.game.Clickables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;

public class DoubleMoneyPowerup {

    ImageButton doubleMoneyClickable;

    public DoubleMoneyPowerup(Utils utils, final GameScreen screen, final Powerups powerups) {
        doubleMoneyClickable = utils.createImageClickable(new Texture("doublemoney.png"), 24, 24);
        doubleMoneyClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                powerups.buyDoubleMoney();
            }
        });
    }

    public ImageButton getDoubleMoneyClickable() {
        return doubleMoneyClickable;
    }

}
