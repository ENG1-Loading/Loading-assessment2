package com.mygdx.game.Powerups;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
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

    public float getSpeedMultiplier() {
        return allPowerups.get("Speed").getValue();
    }

    public void setSpeedMultiplier(int multiplier) {
        boolean bought = buyPowerup("Speed");
        if (bought) {
            allPowerups.get("Speed").setInitialisedTime(System.currentTimeMillis());
            allPowerups.get("Speed").setValue(multiplier);
        } else {
            System.out.println("Not enough money for this.");
        }

    }

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

    public boolean hasExtraTime() {
        // return true;
        return allPowerups.get("ExtraTime").getTimesBought() != 0;
    }

    public float getStationSpeed() {
        return allPowerups.get("FastStations").getValue();
    }

    public void setStationSpeed(float multiplier) {
        boolean bought = buyPowerup("FastStations");
        if (bought) {
            allPowerups.get("FastStations").setInitialisedTime(System.currentTimeMillis());
            allPowerups.get("FastStations").setValue(multiplier);
        } else {
            System.out.println("Not enough money for this.");
        }
    }

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

    public boolean buyRep() {
        if (allPowerups.get("ExtraLife").getTimesBought() > 0) {
            return false;
        }
        boolean bought = buyPowerup("ExtraLife");
        if (!bought) {
            System.out.println("invalid");
        }

        return bought;
    }

    public boolean buyDoubleMoney() {
        boolean bought = buyPowerup("DoubleMoney");
        if (bought) {
            ((GameScreen) screen).incMoneyMult();
        }

        return bought;
    }

    public boolean buyPowerup(String powerup) {
        int price = allPowerups.get(powerup).getPrice();

        if (price <= money.getCurrentMoney() && (allPowerups.get(powerup).getInitialisedTime() == 0)) {
            money.removeMoney(price);
            allPowerups.get(powerup).incrementTimesBought();
            return true;
        } else {
            return false;
        }
    }

    public void resetPowerup(String k) {
        allPowerups.get(k).setInitialisedTime(0);
        allPowerups.get(k).setValue(1);
    }

    public void checkPowerups() {
        for (String k : allPowerups.keySet()) {
            if (allPowerups.get(k).getInitialisedTime() != 0) {
                if (allPowerups.get(k).getExpiryTime() < System.currentTimeMillis()) {
                    resetPowerup(k);
                }
            }
        }
    }

    public void render() {
        String message = "POWERUPS ACTIVE : ";
        StringBuilder messageBuilder = new StringBuilder(message);
        for (String k : allPowerups.keySet()) {
            if (allPowerups.get(k).getInitialisedTime() != 0) {
                messageBuilder.append(" ").append(k);
            }
        }
        message = messageBuilder.toString();
        game.batch.begin();
        font.draw(game.batch, message, 127, 122);
        game.batch.end();
    }

}
