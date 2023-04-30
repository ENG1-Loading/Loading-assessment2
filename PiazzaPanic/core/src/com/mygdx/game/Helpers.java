package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Food.Ingredient;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Helpers {
    PiazzaPanic game;
    /*
        * Constructor for the helpers class
        * @param _game the game object
        *
        * @return none
     */
    public Helpers(PiazzaPanic _game) {
        this.game = _game;
    }
    public int findFreeStation(ArrayList<Integer> stationSelected) {
        ArrayList<Integer> listA = new ArrayList<>();
        for (int i=0;i<=5;i++) {
            listA.add(i);
        }
        for (int i : stationSelected) {
            for (int j = 0; j < listA.size(); j++) {
                if (listA.get(j) == i) {
                    listA.remove(j);
                }
            }
        }
        return listA.get(0);
    }
    /*
        * Creates a clickable image button
        * @param texture the texture of the button
        * @param width the width of the button
        * @param height the height of the button
        * @return the image button
     */
    public static TextureRegionDrawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        return drawable;
    }
    /*
        * Shows the current reputation points
        * @param RepLabel the texture of the reputation label
        * @param Rep the number of reputation points
        * @param RepPoint the texture of the reputation point
        *
        * @return none
     */
    public void showRepPoints(Texture RepLabel, int Rep, Texture RepPoint) {
        game.batch.begin();
        int x = 146;
        game.batch.draw(RepLabel, 130, 134);
        for (int i = 0; i < Rep; i++) {
            game.batch.draw(RepPoint, x, 135);
            x += 6;
        }
        game.batch.end();
    }

    /*
        * Gets the number of customers to serve
        * @param customerCount the number of customers already served
        * @return the number of customers to serve
     */
    public int CustomersToServe(int customerCount) {
        int num = MathUtils.random(0, 10);
        if (customerCount <= 1) {

            if (num <= 7) {
                return 1;
            } else if (num <= 9) {
                return 2;
            } else {
                return 3;
            }
        } else if (customerCount == 2) {
            if (num <= 5) {
                return 1;
            } else if (num <= 8) {
                return 2;
            } else {
                return 3;
            }
        } else if (customerCount == 3) {
            if (num <= 3) {
                return 1;
            } else if (num <= 7) {
                return 2;
            } else {
                return 3;
            }
        } else if (customerCount <= 5) {
            if (num <= 2) {
                return 1;
            } else if (num <= 5) {
                return 2;
            } else {
                return 3;
            }
        }
        return num % 3;
    }





}
