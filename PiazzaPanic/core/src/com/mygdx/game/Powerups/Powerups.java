package com.mygdx.game.Powerups;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Customer;
import com.mygdx.game.Money;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

import java.util.ArrayList;
import java.util.HashMap;

public class Powerups {
    public HashMap<String, Powerup> allPowerups = new HashMap<String, Powerup>();

    BitmapFont font;
    Money money;
    private PiazzaPanic game;
    private Screen screen;
    /*
        * Constructor for the Powerups class
        * @param _game the game
        * @param _money the money
        * @param _screen the screen
        * @param difficulty the difficulty of the game
        * @return none
     */
    public Powerups(PiazzaPanic _game, Money _money, Screen _screen, String difficulty) {
        this.game = _game;
        int priceIncrease;
        if (difficulty.equals("Easy")) {
            priceIncrease = 0;
        } else if (difficulty.equals("Medium")) {
            priceIncrease = 25;
        } else {
            priceIncrease = 50;
        }
        Powerup speedPowerup = new Powerup("Speed", 5000, 0, 1, 100, 0, priceIncrease);
        Powerup extraLife = new Powerup("ExtraLife", 1000000000, 0, 1, 300, 0, priceIncrease);
        Powerup stationSpeed = new Powerup("FastStations", 10000, 0, 1f, 300, 0, priceIncrease);
        Powerup orderTimeUp = new Powerup("ExtraTime", 1000000000, 0, 10, 100, 0, priceIncrease);
        Powerup thirdChef = new Powerup("ExtraChef", 1000000000, 0, 2, 100, 0, priceIncrease);
        Powerup doubleMoney = new Powerup("DoubleMoney", 120, 0, 1, 300, 0, priceIncrease);
        allPowerups.put("Speed", speedPowerup);
        allPowerups.put("ExtraLife", extraLife);
        allPowerups.put("FastStations", stationSpeed);
        allPowerups.put("ExtraTime", orderTimeUp);
        allPowerups.put("ExtraChef", thirdChef);
        allPowerups.put("DoubleMoney", doubleMoney);
        this.font = new BitmapFont();
        this.money = _money;
        font.setColor(Color.BLACK);
        font.getData().setScale(0.25f);
        this.screen = _screen;
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public float getSpeedMultiplier() {
        return allPowerups.get("Speed").getValue();
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public void setSpeedMultiplier(int multiplier) {
        boolean bought = buyPowerup("Speed");
        if (bought) {
            allPowerups.get("Speed").setInitialisedTime(System.currentTimeMillis());
            allPowerups.get("Speed").setValue(multiplier);
        } else {
            System.out.println("Not enough money for this.");
        }
    }
    public void setSpeedMultiplierFree(int multiplier) {
        allPowerups.get("Speed").setInitialisedTime(System.currentTimeMillis());
        allPowerups.get("Speed").setValue(multiplier);
    }

    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public boolean setChefCount(int count) {
        boolean bought = buyPowerup("ExtraChef");
        if (bought) {
            allPowerups.get("ExtraChef").setInitialisedTime(System.currentTimeMillis());
            allPowerups.get("ExtraChef").setValue(3);
            return true;
        } else {
            System.out.println("Not enough money for this");
            return false;
        }
    }
    /*
        * Returns the chef count
        * @param none
        * @return the number of chefs
     */
    public float getChefCount() {
        return allPowerups.get("ExtraChef").getValue();
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public boolean hasExtraTime() {
        // return true;
        return allPowerups.get("ExtraTime").getTimesBought() != 0;
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public float getStationSpeed() {
        return allPowerups.get("FastStations").getValue();
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public void setStationSpeed(float multiplier) {
        boolean bought = buyPowerup("FastStations");
        if (bought) {
            allPowerups.get("FastStations").setInitialisedTime(System.currentTimeMillis());
            allPowerups.get("FastStations").setValue(multiplier);
        } else {
            System.out.println("Not enough money for this.");
        }
    }

    public void setStationSpeedFree(float multiplier) {
        allPowerups.get("FastStations").setInitialisedTime(System.currentTimeMillis());
        allPowerups.get("FastStations").setValue(multiplier);
    }

    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public boolean buyExtraTime() {
        if (allPowerups.get("ExtraTime").getTimesBought() > 0) {
            return false;
        }
        boolean bought = buyPowerup("ExtraTime");
        if (!bought) {
            System.out.println("invalid");
        }
        for (ArrayList<Customer> customers : ((GameScreen) screen).getCustomers()) {
            for (Customer c : customers) {
                c.customerOrder.setOrderTime(c.customerOrder.getOrderTime() + 10);
            }
        }

        return bought;
    }
    public void buyExtraTimeFree() {
        for (ArrayList<Customer> customers : ((GameScreen) screen).getCustomers()) {
            for (Customer c : customers) {
                c.customerOrder.setOrderTime(c.customerOrder.getOrderTime() + 10);
            }
        }
    }
    public float getExtraTime() {
        return allPowerups.get("ExtraTime").getValue();
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public boolean buyRep() {
        if (allPowerups.get("ExtraLife").getTimesBought() > 0) {
            return false;
        }
        boolean bought = buyPowerup("ExtraLife");
        if (!bought) {
            System.out.println("invalid");
        }
        System.out.println("Returning ");
        System.out.println(bought);
        return bought;
    }

    public void buyRepFree() {
        allPowerups.get("ExtraLife").setInitialisedTime(System.currentTimeMillis());
        allPowerups.get("ExtraLife").setValue(1);
    }
    public float getRep() {
        return allPowerups.get("ExtraLife").getValue();
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public boolean buyDoubleMoney() {
        boolean bought = buyPowerup("DoubleMoney");
        if (bought) {
            ((GameScreen) screen).incMoneyMult();
        }

        return bought;
    }
    public void buyDoubleMoneyFree() {
        ((GameScreen) screen).incMoneyMult();
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public boolean buyPowerup(String powerup) {
        int price = allPowerups.get(powerup).getPrice();
        System.out.println("got here");
        if (price <= money.getCurrentMoney() && (allPowerups.get(powerup).getInitialisedTime() == 0)) {
            System.out.println("not here");
            money.removeMoney(price);
            allPowerups.get(powerup).incrementTimesBought();
            return true;
        } else {
            System.out.println("Got here too");
            return false;
        }
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public void resetPowerup(String k) {
        allPowerups.get(k).setInitialisedTime(0);
        allPowerups.get(k).setValue(1);
    }
    /*
        * Returns the powerup
        * @param name the name of the powerup
        * @return the powerup
     */
    public void checkPowerups() {
        for (String k : allPowerups.keySet()) {
            if (allPowerups.get(k).getInitialisedTime() != 0) {
                if (allPowerups.get(k).getExpiryTime() < System.currentTimeMillis()) {
                    resetPowerup(k);
                }
            }
        }
    }
    public ArrayList<String> getPowerupsActive() {
        ArrayList<String> activePowerups = new ArrayList<String>();
        for (String k : allPowerups.keySet()) {
            if (allPowerups.get(k).getInitialisedTime() != 0) {
                activePowerups.add(k);
            }
        }
        return activePowerups;
    }

//    public void render() {
//        String message = "POWERUPS ACTIVE : ";
//        StringBuilder messageBuilder = new StringBuilder(message);
//        for (String k : allPowerups.keySet()) {
//            if (allPowerups.get(k).getInitialisedTime() != 0) {
//                messageBuilder.append(" ").append(k);
//            }
//        }
//        message = messageBuilder.toString();
//        game.batch.begin();
//        font.draw(game.batch, message, 127, 122);
//        game.batch.end();
//    }

}
