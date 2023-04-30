package com.mygdx.game.Powerups;

public class Powerup {
    private String name;
    private long duration;
    private long initialisedTime;
    private float value;
    private int price;
    private int timesBought;
    /*
        * Constructor for the powerup
        * @param name the name of the powerup
        * @param duration the duration of the powerup
        * @param initialisedTime the time the powerup was initialised
        * @param value the value of the powerup
        * @param price the price of the powerup
        * @param timesBought the number of times the powerup has been bought
        * @return none
     */
    public Powerup(String name, long duration, long initialisedTime, float value, int price, int timesBought,
            int _priceIncrease) {
        this.name = name;
        this.duration = duration;
        this.initialisedTime = initialisedTime;
        this.value = value;
        this.price = price + _priceIncrease;
        this.timesBought = timesBought;

    }
    /*
        * Increments the number of times the powerup has been bought
        * @param none
        * @return none
     */
    public void incrementTimesBought() {
        timesBought++;
    }
    /*
        * Returns the number of times the powerup has been bought
        * @param none
        * @return timesBought the number of times the powerup has been bought
     */
    public int getTimesBought() {
        return timesBought;
    }
    /*
        * Returns the name of the powerup
        * @param none
        * @return name the name of the powerup
     */
    public String getName() {
        return name;
    }
    /*
        * Returns the value of the powerup
        * @param none
        * @return value the value of the powerup
     */
    public float getValue() {
        return value;
    }
    /*
        * Sets the value of the powerup
        * @param _value the value of the powerup
        * @return none
     */
    public void setValue(float _value) {
        value = _value;
    }
    /*
        * Sets the duration of the powerup
        * @param _duration the duration of the powerup
        * @return none
     */
    public void setInitialisedTime(long initialisedTime) {
        this.initialisedTime = initialisedTime;
    }
    /*
        * Returns the price of the powerup
        * @param none
        * @return price the price of the powerup
     */
    public int getPrice() {
        return price;
    }
    /*
        * Returns the duration of the powerup
        * @param none
        * @return duration the duration of the powerup
     */
    public long getDuration() {
        return duration;
    }
    /*
        * Returns the time the powerup was initialised
        * @param none
        * @return initialisedTime the time the powerup was initialised
     */
    public long getInitialisedTime() {
        return initialisedTime;
    }
    /*
        * Returns the time the powerup will expire
        * @param none
        * @return initialisedTime + duration the time the powerup will expire
     */
    public long getExpiryTime() {
        return initialisedTime + duration;
    }

}
