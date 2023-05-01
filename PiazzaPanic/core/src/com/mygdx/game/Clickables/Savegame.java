package com.mygdx.game.Clickables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.prefs.Preferences;

public class Savegame {

    ImageButton saveClickable;
    GameScreen screen;
    /*
    * Constructor for the savegame
    *   @param _game the game object
    *   @param utils the utils object
    *   @param _screen the game screen
    *
    *   @return none
     */
    public Savegame(PiazzaPanic _game, Utils utils, final GameScreen _screen) throws IOException {
        this.screen = _screen;
        this.saveClickable = utils.createImageClickable(new Texture("Save.png"),24,24);
        saveClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveState();
            }
        });
    }

    /*
        * Saves the state of the game
        *  @param none
        *
        * @return true if the game was saved successfully, false otherwise
     */
    public Boolean saveState() {
        DateFormat dform = new SimpleDateFormat("ddMMyySS");
        Date obj = new Date();
        String path = String.format("save%s.json",dform.format(obj) );
        JSONObject json = new JSONObject();
        int money = screen.getMoney().getCurrentMoney();
        long timetaken = System.currentTimeMillis() - screen.getGameTime();
        int rep = screen.getRep();
        int customersServed = screen.getCustomerCount();
        Powerups powerups = screen.getPowerups();
        ArrayList<String> activePowerups = powerups.getPowerupsActive();
        ArrayList<String> possiblePowerups = new ArrayList<>();
        possiblePowerups.add("Speed");
        possiblePowerups.add("ExtraLife");
        possiblePowerups.add("FastStations");
        possiblePowerups.add("ExtraTime");
        possiblePowerups.add("DoubleMoney");
        possiblePowerups.add("ExtraChef");

        try {
            json.put("Money", money);
            json.put("timetaken", timetaken);
            json.put("rep", rep);
            json.put("customersLeft", customersServed);
            for (String powerup : possiblePowerups) {
                json.put(powerup, false);
            }
            for (String powerup : activePowerups) {
                json.put(powerup, true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
        * Getter for the savegame
        *  @param none
        *
        * @return the savegame
     */
    public ImageButton getSaveClickable() {return saveClickable;}
}

