package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.*;
import com.mygdx.game.Clickables.*;
import com.mygdx.game.Helpers.*;

import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Order;
import com.mygdx.game.Food.Salad;
import com.mygdx.game.Clickables.UnlockBaking;
import com.mygdx.game.Powerups.Powerups;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameScreen implements Screen {

    private int customerNumber = 5;
    PiazzaPanic game;
    FitViewport view;
    Stage gameStage;
    Money money;
    Powerups powerups;
    // map and camera stuff
    TmxMapLoader mapLoader;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera gameCam;
    // Start the game with 3 reputation points
    int Rep = 3;
    Texture RepLabel = new Texture("REP.png");
    Texture RepPoint = new Texture("REPHeart.png");
    // customer number determines how many customers will spawn over the course of
    // the game
    // 0 means infinite
    private final Array<Cook> cooks;
    private final ArrayList<ArrayList<Customer>> customers;
    // sprite handling
    public float[][] locations = {{0, 64}, {32, 64}, {64, 64}, {0, 32}, {48, 28}, {80, 48}, {100000, -1}};
    Sprite alex;
    Sprite amelia;
    Sprite adam;
    FileHandle charIdles;
    Skin skin;
    Skin custSkins;
    ArrayList<Sprite> idles = new ArrayList<>();
    // cook and customer control variables
    int selected = 0;
    ArrayList<Integer> stationSelected = new ArrayList<>();
    // control the number of cooks
    int cookCount = 3; // control how many cooks spawn -> update to allow for the value to increase
    // take the time at the start of the game to display the time taken to complete
    // the round

    long gameTime = System.currentTimeMillis();

    // list of active orders
    ArrayList<Order> orders = new ArrayList<>();
    // used to count how much time has passed after an order is placed
    float timeCount = 0;
    // order timer font
    BitmapFont font = new BitmapFont();
    // progress bars
    HashMap<ProgressBar, Cook> bars;
    // music
    Music alienJazz = Gdx.audio.newMusic(Gdx.files.internal("Alien_Jazz_Ridley_Coyte.mp3"));
    // stations
    ImageButton pantryClickable;
    ImageButton fryingClickable;
    int fryingClicked = 0;
    int bakingClicked = 0;
    boolean pattyAtFrying = false;
    boolean pizzaAtBaking = false;
    boolean bakingUnlocked = false;
    boolean potatoAtBaking = false;
    Texture flipBtn = new Texture("flipBtn.png");
    ImageButton bakingClickable;
    ImageButton cuttingClickable;
    ImageButton binClickable;
    ImageButton servingClickable;
    ImageButton extraTimeClickable;
    // pantry and serving screen frames
    ImageButton extraChefClickable;
    TextureRegion pantryScreenFrameRegion;
    ImageButton pantryScreenFrame;
    TextureRegion servingScreenFrameRegion;
    ImageButton servingScreenFrame;
    // clickables
    ImageButton XbtnClickable;
    ImageButton lettuceClickable;
    ImageButton tomatoClickable;
    ImageButton bunsClickable;
    ImageButton pattyClickable;
    ImageButton burgerClickable;
    ImageButton saladClickable;
    ImageButton speedClickable;
    ImageButton repClickable;
    ImageButton saveClickable;
    ImageButton pizzaClickable;
    ImageButton unlockBakingClickable;
    ImageButton potatoClickable;
    ImageButton stationSpeedClickable;
    ImageButton cheeseClickable;
    ImageButton pizzaServableClickable;
    ImageButton potatoServeClickable;
    Helpers helpers;

    ImageButton doubleMoneyClickable;
    int NumServed = 0;

    // when you hover over a clickable it changes the cursor to a hand
    // this listener is added to all clickables
    ClickListener cursorHovering = new ClickListener() {
        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            Gdx.graphics.setSystemCursor(SystemCursor.Hand);
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
        }
    };
    // UI elements
    Texture plateTex = new Texture("plate.png");
    Texture cookStackTitle = new Texture("cookStackTitle.png");
    Texture selectedCook = new Texture("selected.png");
    // Order Textures
    Texture burgerOrderTexture = new Texture("orderBurger.png");
    Texture saladOrderTexture = new Texture("orderSalad.png");
    Boolean showPantryScreen = false;
    Boolean showServingScreen = false;

    Utils utils;
    Frying frying;
    Bin bin;
    Pantry pantry;
    Baking baking;
    Cutting cutting;
    Serving serving;
    SaladClickable saladC;
    BurgerClickable burger;
    LettuceClickable lettuce;
    PotatoClickable potato;
    TomatoClickable tomato;
    BunClickable bun;
    PattyClickable patty;
    Savegame save;
    PizzaClickable pizza;
    UnlockBaking unlock;
    PizzaServeClickable pizzaServe;
    PotatoServeClickable potatoServe;
    SpeedPowerup speedPowerup;

    RepPowerup repPowerup;
    ExtratimePowerup extratimePowerup;
    ExtraChef extraChefPowerup;

    DoubleMoneyPowerup doubleMoneyPowerup;
    Random rand = new Random();

    StationSpeedPowerup stationSpeedPowerup;

    private int customerCount = 0;

    private Boolean endless = false;
    JSONObject obj;
    JSONObject config;
    private HashMap<ProgressBar, Float> pattyTimers = new HashMap<>();
    private float pattyFryingTime = 0;
    private float potatoBakingTime = 0;
    private float pizzaBakingTime = 0;

    boolean isInitialMove = true;
    boolean thirdChefUnlocked = false;
    /*
        * Constructor for the game screen
        *
        * @param game the game object
        * @param port the viewport
        * @param isEndless whether the game is endless or not
        * @param isLoad whether the game is being loaded or not
        * @param Loadfile the file to load from
        * @param config the config file
        * @throws IOException
        *
        * @return none
     */

    public GameScreen(PiazzaPanic game, FitViewport port, Boolean isEndless, Boolean isLoad, String Loadfile,
            JSONObject config) throws IOException {

        // initialise the game
        this.game = game;
        this.view = port;
        this.config = config;
        this.customerNumber = config.getInt("customersToServe");
        this.helpers = new Helpers(game);

        this.utils = new Utils();
        this.frying = new Frying(game, utils, this);
        this.bin = new Bin(game, utils, this);
        this.pantry = new Pantry(game, utils, this);
        this.baking = new Baking(game, utils, this);
        this.cutting = new Cutting(game, utils, this);
        this.serving = new Serving(game, utils, this);
        this.saladC = new SaladClickable(game, utils, this);
        this.burger = new BurgerClickable(game, utils, this);
        this.bun = new BunClickable(utils, this);
        this.lettuce = new LettuceClickable(utils, this);
        this.tomato = new TomatoClickable(utils, this);
        this.patty = new PattyClickable(utils, this);
        this.pizza = new PizzaClickable(utils, this);
        this.potato = new PotatoClickable(utils, this);
        this.unlock = new UnlockBaking(utils, this);
        this.pizzaServe = new PizzaServeClickable(game, utils, this);
        this.potatoServe = new PotatoServeClickable(game, utils, this);
        try {
            this.save = new Savegame(game, utils, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        money = new Money(game, config.getString("difficulty"));

        powerups = new Powerups(game, money, this, config.getString("difficulty"));
        this.speedPowerup = new SpeedPowerup(utils, this, powerups);
        this.repPowerup = new RepPowerup(utils, this, powerups);
        this.stationSpeedPowerup = new StationSpeedPowerup(utils, this, powerups);
        this.extratimePowerup = new ExtratimePowerup(utils, this, powerups);
        this.extraChefPowerup = new ExtraChef(utils, this, powerups);
        this.doubleMoneyPowerup = new DoubleMoneyPowerup(utils, this, powerups);


        gameStage = new Stage(view, game.batch);

        // load the map and camera
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("KitchenMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam = new OrthographicCamera();
        view.setCamera(gameCam);
        view.setWorldSize(192, 144);
        gameCam.position.set(view.getWorldWidth() / 2, view.getWorldHeight() / 2, 0);
        // set order timer font color
        font.setColor(Color.BLACK);
        font.getData().setScale(0.5f);
        // sprite information from the texture atlas
        TextureAtlas atlasIdle = new TextureAtlas(Gdx.files.internal("charIdle.txt"));
        TextureAtlas customersLeft = new TextureAtlas(Gdx.files.internal("customersLeft.txt"));
        skin = new Skin();
        skin.addRegions(atlasIdle);
        custSkins = new Skin();
        custSkins.addRegions(customersLeft);
        charIdles = Gdx.files.internal("charIdle.txt");
        adam = skin.getSprite("Adam");
        alex = skin.getSprite("Alex");
        amelia = skin.getSprite("Amelia");
        idles.add(adam);
        idles.add(alex);
        idles.add(amelia);

        // music control
        // music composed by Ridley Coyte
        if (!config.getBoolean("muteMusic")) {
            alienJazz.setLooping(true);
            alienJazz.play();
        }

        // create the instances of the cooks and first customer.
        cooks = new Array<Cook>();
        spawnCooks();
        customers = new ArrayList<ArrayList<Customer>>();
        ArrayList<Customer> tmp = new ArrayList<Customer>();
        int tmpI = helpers.CustomersToServe(customerCount);


        for (int i = 0; i < tmpI; i++) {
            tmp.add(new Customer(new Actor(), bakingUnlocked));
        }
        System.out.print(tmp);
        customers.add(tmp);
        increaseNumServed(tmpI);
        // array of all progressbars created (used to update all of them in
        // updateProgressBars function)
        bars = new HashMap<ProgressBar, Cook>();
        // pantry station
        pantryClickable = pantry.getPantryClickable();
        gameStage.addActor(pantryClickable);
        // frying station
        fryingClickable = frying.createFryingClickable();
        gameStage.addActor(fryingClickable);
        // baking station
        bakingClickable = baking.getBakingClickable();
        gameStage.addActor(bakingClickable);
        // bin station
        binClickable = bin.getBinClickable();
        gameStage.addActor(binClickable);
        // cutting station
        cuttingClickable = cutting.getCuttingClickable();
        gameStage.addActor(cuttingClickable);
        // serving station
        servingClickable = serving.getServingClickable();
        gameStage.addActor(servingClickable);
        // save game
        saveClickable = save.getSaveClickable();
        gameStage.addActor(saveClickable);
        extraChefClickable = extraChefPowerup.getExtraChefClickable();
        extraChefClickable.setScale(0.7f);
        gameStage.addActor(extraChefClickable);

        // unlock baking
        unlockBakingClickable = unlock.getUnlockBakingButton();
        // pizza
        // adding the station clickables to the screen
        pantryClickable.setPosition(0, 64);
        fryingClickable.setPosition(32, 64);
        bakingClickable.setPosition(64, 64);
        binClickable.setPosition(0, 0);
        cuttingClickable.setPosition(32, 0);
        servingClickable.setPosition(96, 16);
        saveClickable.setPosition(170, 100);
        extraChefClickable.setPosition(110, 120);

        // close button for station pop ups
        XbtnClickable = createImageClickable(new Texture("Xbtn.png"), 16, 16);
        // function executes after clicking on the close button
        // hides any menus that pop up
        XbtnClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hidePantryScreen();
                hideServingScreen();
                cooks.get(selected).isBusy = false;
            }
        });

        // pantry screen frame
        pantryScreenFrameRegion = new TextureRegion(new Texture("pantryFrame.png"));
        pantryScreenFrame = new ImageButton(new TextureRegionDrawable(pantryScreenFrameRegion));
        pantryScreenFrame.setSize(140, 92);

        /*
         * Pantry screen buttons
         * The functions executes after clicking on any of the ingredient buttons on the
         * pantry screen
         * Addes the ingredient to the current cook's stack (if it's less than 5 items)
         */
        // unprepared lettuce button
        lettuceClickable = lettuce.getLettuceClickable();
        // unprepared tomato button
        tomatoClickable = tomato.getTomatoClickable();
        // unprepared buns button
        bunsClickable = bun.getBunClickable();
        // unprepared patty button
        pattyClickable = patty.getPattyClickable();
        potatoClickable = potato.getPotatoClickable();
        speedClickable = speedPowerup.getSpeedClickable();
        repClickable = repPowerup.getRepButton();
        stationSpeedClickable = stationSpeedPowerup.getStationClickable();
        extraTimeClickable = extratimePowerup.getExtraTimeClickable();
        pizzaClickable = pizza.getPizzaClickable();
        unlockBakingClickable = unlock.getUnlockBakingButton();
        pizzaServableClickable = pizzaServe.getPizzaServeClickable();
        potatoServeClickable = potatoServe.getPotatoServeClickable();
        doubleMoneyClickable = doubleMoneyPowerup.getDoubleMoneyClickable();
        // serving screen frame
        servingScreenFrameRegion = new TextureRegion(new Texture("servingFrame.png"));
        servingScreenFrame = new ImageButton(new TextureRegionDrawable(servingScreenFrameRegion));
        servingScreenFrame.setSize(140, 92);

        /*
         * Serving screen buttons
         * The functions executes after clicking on any of the ingredient buttons on the
         * serving screen
         * Serves the item if all the required prepared ingredients are in the current
         * cook's stack
         */
        // burger button
        burgerClickable = burger.getBurgerClickable();
        // salad button
        saladClickable = saladC.getSaladClickable();
        this.endless = isEndless;
        if (isLoad) {
            try {
                loadJSON(Loadfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /*
        * Gets the game time
        *
        * @return gameTime
     */
    public long getGameTime() {
        return gameTime;
    }
    /*
        * Gets stations currently being used
        *
        * @return none
     */
    public void setSationSelected(int value) {
        stationSelected.set(selected, value);
    }
    /*
        * Gets current cook selected
        *
        * @return selected
     */
    public int getSelected() {
        return selected;
    }

    /*
       * Gets the array of cooks
       *
       * @return cooks
     */
    public Array<Cook> getCooks() {
        return cooks;
    }
    /*
        * Increment frying clicked
        *
        * @return none
     */
    public void incrementFryingClicked() {
        fryingClicked++;
    }
    /*
        * Used to buy a third chef
        *
        * @param chefCount the number of chefs
        *
        * @return none
     */
    public void setChef(int chefCount) {
        thirdChefUnlocked = true;
        int freeStation = helpers.findFreeStation(stationSelected);
        isInitialMove = true;
        stationSelected.set(2, freeStation);
    }

//    public int findFreeStation() {
//        ArrayList<Integer> listA = new ArrayList<>();
//        for (int i=0;i<=5;i++) {
//            listA.add(i);
//        }
//        for (int i : stationSelected) {
//            for (int j = 0; j < listA.size(); j++) {
//                if (listA.get(j) == i) {
//                    listA.remove(j);
//                }
//            }
//        }
//        return listA.get(0);
//    }

    /*
        * Increments the number of times baking is clicked
        *
        * @return none
     */
    public void incrementBakingClicked() {
        bakingClicked++;
    }

    /*
        * Gets the number of times frying is clicked
        *
        * @return fryingClicked
     */
    public int getFryingClicked() {
        return fryingClicked;
    }
    /*
        * Gets the number of times baking is clicked
        *
        * @return bakingClicked
     */
    public int getBakingClicked() {
        return bakingClicked;
    }
    /*
        * Gets whether baking is unlocked
        *
        * @return bakingUnlocked
     */
    public boolean bakingUnlocked() {
        return bakingUnlocked;
    }
    /*
        * Unlocks baking
        *
        * @param value  whether baking is unlocked
        *
        * @return none
     */
    public void setBakingUnlocked(boolean value) {
        bakingUnlocked = value;
    }
    /*
        * Sets pizza to bake
        *
        * @param isBaking   whether pizza is baking
        *
        * @return none
     */
    public void setPizzaAtBaking(Boolean isBaking) {
        pizzaAtBaking = isBaking;
    }
    /*
        * Gets whether pizza is baking
        *
        * @return pizzaAtBaking
     */
    public boolean getPizzaAtBaking() {
        return pizzaAtBaking;
    }
    /*
        * Sets the potato to bake
        *
        * @param isBaking whether potato is baking
        *
        * @return none
     */
    public void setAtPotatoBaking(Boolean isBaking) {potatoAtBaking = isBaking;}
    /*
        * Gets whether potato is baking
        *
        * @return potatoAtBaking
     */
    public boolean getAtPotatoBaking() {return potatoAtBaking;}
    /*
        * Sets the patty to fry
        *
        * @param isFrying whether patty is frying
        *
        * @return none
     */
    public void setPattyAtFrying(Boolean isFrying) {
        pattyAtFrying = isFrying;
    }

    /*
        * Gets whether patty is frying
        *
        * @return pattyAtFrying
     */
    public boolean getPattyAtFrying() {
        return pattyAtFrying;
    }

    /*
        * Shows/hides the pantry screen
        *
        * @param show whether to show or hide the pantry screen
        *
        * @return none
     */
    public void setShowPantryScreen(Boolean show) {
        showPantryScreen = show;
    }


    /*
        * Shows/hides the serving screen
        *
        * @param value whether to show or hide the serving screen
        *
        * @return none
     */
    public void setShowServingScreen(Boolean value) {
        showServingScreen = value;
    }

    /*
        * Gets customers
        *
        * @return customers
     */
    public ArrayList<ArrayList<Customer>> getCustomers() {
        return customers;
    }

    /*
        * Gets the number of customers
        *
        * @return customerCount
     */
    public int getCustomerCount() {
        return customerCount;
    }

    /*
        * Changes the number of customers served
        *
        * @param amnt the amount to change by
        *
        * @return none
     */
    public void increaseNumServed(int amnt) {
        NumServed += amnt;
    }

    /*
        * Changes rep
        *
        * @param amount the amount to change rep by
        *
        * @return none
     */
    public void addRep(int amount) {
        Rep += amount;
    }

    /*
        * Gets the current money
        *
        * @param none
        *
        * @return money
        *
     */
    public Money getMoney() {
        return money;
    }

    /*
        * Gets the current rep
        *
        * @param none
        *
        * @return Rep
        *
     */
    public int getRep() {
        return Rep;
    }

    /*
        * Gets the powerups
        *
        * @param none
        *
        * @return powerups the powerups
        *
     */
    public Powerups getPowerups() {
        return powerups;
    }

    /*
        * Initialises the save file if supplied
        *
        * @param obj the JSONObject to initialise from
        *
        * @return none
     */
    public void initialiseLoad(JSONObject obj) {
        Rep = obj.optInt("rep", 3);
        int timeTaken = obj.optInt("timetaken", 0);
        gameTime = System.currentTimeMillis() + timeTaken;
        // customerCount = obj.optInt("customersLeft", defaultCustomerCountValue);
        int moneyToAdd = obj.optInt("Money", 0);
        money.addMoney(moneyToAdd);
        NumServed = obj.optInt("NumServed", 0);
        if (obj.optBoolean("bakingUnlocked", false)) {
            bakingUnlocked = true;
        }
        if (obj.optBoolean("pizzaAtBaking", false)) {
            pizzaAtBaking = true;
        }
        if (obj.optBoolean("Speed", false)) {
            powerups.setSpeedMultiplierFree(2);
        }
        if (obj.optBoolean("DoubleMoney", false)) {
            powerups.buyDoubleMoneyFree();
        }
        if (obj.optBoolean("ExtraTime", false)) {
            powerups.buyExtraTimeFree();
        }
        if (obj.optBoolean("ExtraChef", false)) {
            thirdChefUnlocked = true;
        }


    }

    /*
        * Loads the save file
        *
        * @param Loadfile the file to load from
        *
        * @return none
     */
    public void loadJSON(String Loadfile) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(Loadfile)));
        obj = new JSONObject(content);
        initialiseLoad(obj);
    }

    /*
        * Creates a clickable image
        *
        * @param texture the texture to use
        * @param width the width of the image
        * @param height the height of the image
        *
        * @return clickable the clickable image
     */
    private ImageButton createImageClickable(Texture texture, float width, float height) {
        TextureRegion region = new TextureRegion(texture);
        ImageButton clickable = new ImageButton(new TextureRegionDrawable(region));
        clickable.setSize(width, height);
        clickable.addListener(cursorHovering);
        return clickable;
    }

    /*
        * Renders the game
        *

        * @param delta the time since the last frame
        *
        * @return none
     */
    @Override
    public void render(float delta) {
        gameCam.update();
        renderer.setView(gameCam);

        ScreenUtils.clear(0, 0, 0, 0);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(gameStage);
        renderer.render();

        // call functions which determine key gameplay elements
        gameStage.act();

        updateProgressBars();
        updateBatch();
        showCookStack();
        showStationScreens();
        showOrders(delta);
//        showRepPoints();
        helpers.showRepPoints(RepLabel, Rep, RepPoint);
        try {
            customerOperations();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            processInput();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        powerups.render();
        powerups.checkPowerups();
        gameStage.draw();

        handlePattyFrying(Gdx.graphics.getDeltaTime());
        handlePizzaBaking(Gdx.graphics.getDeltaTime());
        handlePotatoBaking(Gdx.graphics.getDeltaTime());


        for (int i = 0; i < cookCount; i++) {
            if (!cooks.get(i).isBusy) {
                cooks.get(i).move(stationSelected.get(i), cooks.get(i).CookBody, stationSelected, powerups, selected, isInitialMove);

            }

        }
        if (isInitialMove) {
            boolean anynot = false;
            for (int i = 0; i<cookCount;i++) {
                if (locations[stationSelected.get(i)][0] - cooks.get(i).CookBody.getX() > 1|| locations[stationSelected.get(i)][1] - cooks.get(i).CookBody.getY() > 1) {
                    anynot = true;
                }
            }
            if (!anynot) {
                isInitialMove = false;
            }
        }

        if (thirdChefUnlocked) {
            extraChefClickable.setPosition(100000, -1);
        }

        money.render();

        // cooks.get(selected).doUserInput(cooks.get(selected));

    }
    /*
        * Handles the frying of the patty and controls burning
        *
        * @param delta the time since the last frame
        *
        * @return none
     */
    public void handlePattyFrying(float delta) {
        if (pattyAtFrying) {

            pattyFryingTime += delta;
            game.batch.begin();
            game.batch.draw(flipBtn, 30, 80);
            game.batch.end();
        } else {
            if (pattyFryingTime >= 10) {
                Ingredient flippedPatty = cooks.get(selected).CookStack.peek();
                flippedPatty.setBurnt();
                flippedPatty.updateCurrentTexture();
            }
            pattyFryingTime = 0;
        }
    }

    /*
        * Handles the baking of the potato and controls burning
        *
        * @param delta the time since the last frame
        *
        * @return none
     */
    public void handlePotatoBaking(float delta) {
            if (potatoAtBaking) {

                potatoBakingTime += delta;
                game.batch.begin();
                game.batch.draw(flipBtn, 85, 78);
                game.batch.end();
            } else {
                if (potatoBakingTime >= 10) {
                    Ingredient flippedPotato = cooks.get(selected).CookStack.peek();
                    flippedPotato.setBurnt();
                    flippedPotato.updateCurrentTexture();
                }
                potatoBakingTime = 0;
            }
    }
    /*
        * Handles the baking of the pizza and controls burning
        *
        * @param delta the time since the last frame
        *
        * @return none
     */
    public void handlePizzaBaking(float delta) {
        if (pizzaAtBaking) {
            pizzaBakingTime += delta;
            game.batch.begin();
            game.batch.draw(flipBtn, 85, 78);
            game.batch.end();
        } else {
            if (pizzaBakingTime >= 10) {
                Ingredient flippedPizza = cooks.get(selected).CookStack.peek();
                flippedPizza.setBurnt();
                flippedPizza.updateCurrentTexture();
            }
        }
    }

    /*
        * Controls the customers and their movement and actions
        *
        * @param none
        *
        * @throws IOException
        *
        * @return none
     */
    private void customerOperations() throws IOException {
        // Check if all customers have been served
        boolean allComplete = false;
        for (Customer c : customers.get(customerCount)) {
            if (!c.selfComplete) {
                allComplete = false;
                break;
            } else {
                allComplete = true;
            }
        }

        // move the customers to the counter
        for (int i = 0; i < customers.get(customerCount).size(); i++) {
            if (!customers.get(customerCount).get(i).atCounter) {
                customers.get(customerCount).get(i).move();
            } else if (allComplete) {
                // make the customer leave
                customers.get(customerCount).get(i).move();

                if (customers.get(customerCount).get(i).body.getX() > 148) {
                    customers.get(customerCount).get(i).body.remove();
                    if (!endless) {
                        // check if the game is in endless mode or not
                        if (NumServed < customerNumber) {
                            // spawn new customer

                            ArrayList<Customer> tmp = new ArrayList<Customer>();
                            int tmpI = helpers.CustomersToServe(customerCount);
                            for (int j = 0; j < tmpI; j++) {
                                tmp.add(new Customer(new Actor(), bakingUnlocked));
                            }
                            if (powerups.hasExtraTime()) {
                                for (Customer c : tmp) {
                                    c.customerOrder.setOrderTime(c.customerOrder.getOrderTime() + 10);
                                }
                            } else {
                            }

                            customers.add(tmp);
                            customerCount += 1;
                            increaseNumServed(tmpI);
                        } else {
                            // end game by taking the time at the game end and going to the time screen

                            long timeTaken = System.currentTimeMillis() - gameTime;
                            alienJazz.stop();
                            if (endless) {
                                int tosub = 0;
                                for (Customer c : customers.get(customerCount)) {
                                    if (!c.selfComplete) {
                                        tosub += 1;
                                    }
                                }
                                game.setScreen(new EndGameScreen(game, timeTaken, 0, true, NumServed- tosub));
                            } else {
                                game.setScreen(new EndGameScreen(game, timeTaken, Rep, false, 0));
                            }

                        }
                    } else {
                        // TODO endless mode
                        ArrayList<Customer> tmp = new ArrayList<Customer>();
                        int tmpI = helpers.CustomersToServe(customerCount);

                        for (int j = 0; j < tmpI; j++) {
                            tmp.add(new Customer(new Actor(), bakingUnlocked));
                        }
                        customers.add(tmp);
                        customerCount += 1;
                        increaseNumServed(tmpI);
                    }
                }
            }
        }

    }

    /*
        * Spawn the cooks and add them to the stage
        *
        * @param none
        *
        * @return none
     */
    private void spawnCooks() {
        for (int i = 0; i < cookCount; i++) {
            Cook cook = new Cook(new Actor());
            cook.CookBody.setWidth(16);
            cook.CookBody.setHeight(23);
            // scale information
            cook.CookBody.setScaleX(game.GAME_WIDTH / 16f);
            cook.CookBody.setScaleY(game.GAME_HEIGHT / 23f);
            // cooks are stored in an array to make it easier to keep track of all things
            // relating to them
            // I love arrays so much
            cooks.add(cook);
            gameStage.addActor(cook.CookBody);
            if (i == 2) {
                stationSelected.add(6);
            } else {
                stationSelected.add(i);
            }
        }
    }


    /*
        * Handles the input from the user
        *
        * @param none
        *
        * @throws IOException
        *
        * @return none
     */
    private void processInput() throws IOException {
        // number keys are used to select which cook is being controlled currently
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            selected = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            selected = 1;
        }

        // TODO add statements for adding more cooks here
        if (cookCount > 2 && Gdx.input.isKeyPressed(Input.Keys.NUM_3) && thirdChefUnlocked) {
            selected = 2;
        }
        for (int i = 0; i < cooks.size; i++) {
            if (cooks.get(i).CookBody.isTouchFocusTarget()) {
                selected = i;
            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            // return to main menu
            game.setScreen(new MainMenuScreen(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            Rep--;
        }

    }

    /*
        * Updates the batch with the sprites for the cooks
        *
        * @param none
        *
        * @return none
     */
    private void updateBatch() {
        // this section assigns each cook a sprite from the list idles
        // you could potentially update this to allow for animations for the cooks when
        // they move
        game.batch.begin();
        int index = 0;
        for (Cook cook : cooks) {
            game.batch.draw(idles.get(index), cook.CookBody.getX(), cook.CookBody.getY());
            index++;
        }
        game.batch.draw(plateTex, 164, 25);
        game.batch.draw(cookStackTitle, 164, 120);
        game.batch.draw(idles.get(selected), 168, 1);
        for (int i = 0; i < customers.get(customerCount).size(); i++) {
            game.batch.draw(custSkins.getSprite(customers.get(customerCount).get(i).name),
                    customers.get(customerCount).get(i).body.getX(), customers.get(customerCount).get(i).body.getY());
        }
        game.batch.end();
    }

    /*
        * Shows the orders at the top of the screen
        *
        * @param dt the time since the last frame
        *
        * @return none
     */
    private void showOrders(float dt) {
        // displays the orders at the top of the screen
        int x = 1;
        int y = 112;

        timeCount += dt;
        boolean shouldUpdateTimers = false; // Add a new flag to indicate whether timers should be updated

        // Check if one second has passed
        if (timeCount >= 1) {
            shouldUpdateTimers = true;
            timeCount = 0; // Reset timeCount outside the customer loop
        }

        for (ArrayList<Customer> customerArr : customers) {
            for (Customer customer : customerArr) {
                if ((customer.atCounter) && (!customer.selfComplete)) {
                    // Update order timer only if shouldUpdateTimers is true
                    if (shouldUpdateTimers) {
                        if (customer.customerOrder.getOrderTime() >= 0) {
                            customer.customerOrder.orderTime--;
                        }
                    }

                    if (customer.customerOrder.getOrderTime() == 0 && !customer.selfComplete
                            && !customer.orderExpired) {
                        // Uncomment line below if you want the customer to leave after the order timer
                        // is gone
                        // customer.orderComplete = true;
                        customer.orderExpired = true;
                        Rep--;
                        if (Rep <= 0) {
                            long timeTaken = System.currentTimeMillis() - gameTime;
                            try {
                                int tosub = 0;
                                for (Customer c : customers.get(customerCount)) {
                                    if (!c.selfComplete) {
                                        tosub += 1;
                                    }
                                }
                                game.setScreen(new EndGameScreen(game, timeTaken, 0, true, NumServed - tosub));
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }

                    game.batch.begin();
                    if (!customer.selfComplete) {
                        game.batch.draw(customer.customerOrder.getOrderTexture(), x, y);
                        game.batch.draw(customer.customerOrder.getRecipe().getSpeechBubbleTexture(),
                                customer.body.getX() - 10, customer.body.getY() + 17);
                        if (customer.customerOrder.getOrderTime() > -1) {
                            font.draw(game.batch, Integer.toString(customer.customerOrder.getOrderTime()), x + 30,
                                    y + 10);
                        } else {
                            font.draw(game.batch, "0", x + 30, y + 10);
                        }
                    }
                    // order timer sets to 0 when it reaches -1

                    game.batch.end();
                    // increase x value if there is more than one current order
                    x += 41;
                }
            }
        }
    }
    /*
        * Shows the selected cook's stack of ingredients
        *
        * @param none
        *
        * @return none
     */
    private void showCookStack() {
        // display the stack of ingredients being held by the current cook
        float x = 164;
        float y = 32;
        game.batch.begin();
        for (Ingredient ingredient : cooks.get(selected).CookStack) {
            game.batch.draw(ingredient.getCurrentTexture(), x, y);
            y += 18;
        }
        game.batch.end();
    }

    /*
        * Shows the station screens
        *
        * @param none
        *
        * @return none
     */
    private void showStationScreens() {
        for (Cook cook : cooks) {
            if ((Math.abs(cook.CookBody.getY() - 64f) < 2) && (Math.abs(cook.CookBody.getX() - 0f) < 2)) {
                showPantryScreen();
            }
            if ((Math.abs(cook.CookBody.getY() - 48f) < 2) && (Math.abs(cook.CookBody.getX() - 80f) < 2)) {
                showServingScreen();
            }
        }
    }

    /*
        * Shows the serving screen
        *
        * @param none
        *
        * @return none
     */
    private void showServingScreen() {
        if (showServingScreen) {
            gameStage.addActor(servingScreenFrame);
            gameStage.addActor(XbtnClickable);
            XbtnClickable.toFront();
            gameStage.addActor(burgerClickable);
            gameStage.addActor(saladClickable);
            gameStage.addActor(pizzaServableClickable);
            gameStage.addActor(potatoServeClickable);
            servingScreenFrame.setPosition(10, 10);
            XbtnClickable.setPosition(7, 88);
            burgerClickable.setPosition(25, 66);
            saladClickable.setPosition(53, 66);
            pizzaServableClickable.setPosition(77, 66);
            potatoServeClickable.setPosition(100, 66);
            pizzaServableClickable.toFront();
            showServingScreen = false;
        }
    }

    /*
        * Shows the pantry screen
        *
        * @param none
        *
        * @return none
     */
    private void showPantryScreen() {
        if (showPantryScreen) {
            gameStage.addActor(pantryScreenFrame);
            gameStage.addActor(XbtnClickable);
            XbtnClickable.toFront();
            gameStage.addActor(lettuceClickable);
            gameStage.addActor(tomatoClickable);
            gameStage.addActor(bunsClickable);
            gameStage.addActor(pattyClickable);
            gameStage.addActor(speedClickable);
            gameStage.addActor(repClickable);
            gameStage.addActor(stationSpeedClickable);
            gameStage.addActor(extraTimeClickable);
            gameStage.addActor(pizzaClickable);
            gameStage.addActor(unlockBakingClickable);
            gameStage.addActor(potatoClickable);
//            gameStage.addActor(cheeseClickable);
            gameStage.addActor(doubleMoneyClickable);
            pantryScreenFrame.setPosition(10, 10);
            XbtnClickable.setPosition(7, 88);
            lettuceClickable.setPosition(25, 66);
            tomatoClickable.setPosition(53, 66);
            bunsClickable.setPosition(81, 66);
            pattyClickable.setPosition(110, 72);
            speedClickable.setPosition(25, 40);
            repClickable.setPosition(53, 40);
            stationSpeedClickable.setPosition(75, 40);
            extraTimeClickable.setPosition(110, 40);
            pizzaClickable.setPosition(25, 17);
            potatoClickable.setPosition(81, 17);
//            cheeseClickable.setPosition(110, 17);
            doubleMoneyClickable.setPosition(110, 17);
            if (bakingUnlocked) {
                unlockBakingClickable.setPosition(10000, -1);
            } else {
                unlockBakingClickable.setPosition(53, 17);
            }

            showPantryScreen = false;
        }
    }
    /*
        * Hides the pantry screen
        *
        * @param none
        *
        * @return none
     */
    private void hidePantryScreen() {
        // moves pantry screen offscreen
        pantryScreenFrame.setPosition(10000, -1);
        XbtnClickable.setPosition(10000, -1);
        lettuceClickable.setPosition(10000, -1);
        tomatoClickable.setPosition(10000, -1);
        bunsClickable.setPosition(10000, -1);
        pattyClickable.setPosition(10000, -1);
        speedClickable.setPosition(10000, -1);
        repClickable.setPosition(10000, -1);
        stationSpeedClickable.setPosition(10000, -1);
        extraTimeClickable.setPosition(100000, -1);
        pizzaClickable.setPosition(10000, -1);
        unlockBakingClickable.setPosition(10000, -1);
        potatoClickable.setPosition(10000, -1);
//        cheeseClickable.setPosition(10000, -1);
        doubleMoneyClickable.setPosition(10000, -1);
    }
    /*
        * Hides the serving screen
        *
        * @param none
        *
        * @return none
     */
    public void hideServingScreen() {
        // moves serving screen offscreen
        servingScreenFrame.setPosition(10000, -1);
        XbtnClickable.setPosition(10000, -1);
        burgerClickable.setPosition(10000, -1);
        saladClickable.setPosition(10000, -1);
        pizzaServableClickable.setPosition(100000,-1);
        potatoServeClickable.setPosition(100000, -1);
    }

    /*
        * Creates a progress bar for the selected cook
        *
        * @param x - x position of the progress bar
        *
        * @param y - y position of the progress bar
        *
        * @param selectedCook - the cook that the progress bar is for
        *
        * @return none
     */
    public void createProgressBar(float x, float y, Cook selectedCook) {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = helpers.getColoredDrawable(20, 5, Color.GREEN);
        style.knob = helpers.getColoredDrawable(0, 5, Color.WHITE);
        style.knobAfter = helpers.getColoredDrawable(20, 5, Color.WHITE);
        float stepSize = powerups.getStationSpeed() * 0.05f;
        ProgressBar bar = new ProgressBar(0, 7, stepSize, false, style);
        bar.setWidth(30);
        bar.setHeight(5);
        bar.setValue(15f);
        bar.setX(x);
        bar.setY(y);
        gameStage.addActor(bar);
        bars.put(bar, selectedCook);
    }

    /*
        * Updates the progress bars
        *
        * @param none
        *
        * @return none
     */
    private void updateProgressBars() {
        if (!bars.isEmpty()) {
            for (ProgressBar bar : bars.keySet()) {
                bar.setValue(bar.getValue() - bar.getStepSize());
                if (bar.getValue() == 0) {
                    gameStage.getActors().removeValue(bar, false);
                    // unbusy the cook
                    bars.get(bar).isBusy = false;
                    bars.remove(bar);
                }
            }
        }
    }

    /*
        * Add a multiplier to the money
        *
        * @param none
        *
        * @return none
     */
    public void incMoneyMult() {
        money.addMultiplier();
    }

    /*
        * Controls window resizing
        *
        * @param width - width of the window
        *
        * @param height - height of the window
        *
        * @return none
     */
    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }
    /*
        * Renders the game
        *
        * @param none
        *
        * @return none
     */
    @Override
    public void show() {
    }
    /*
        * Pauses the game
        *
        * @param none
        *
        * @return none
     */
    @Override
    public void pause() {
    }

    /*
        * Resumes the game
        *
        * @param none
        *
        * @return none
     */
    @Override
    public void resume() {
    }
    /*
        * Hides the game
        *
        * @param none
        *
        * @return none
     */
    @Override
    public void hide() {
    }
    /*
        * Disposes of the game
        *
        * @param none
        *
        * @return none
     */
    @Override
    public void dispose() {
        game.batch.dispose();
        gameStage.dispose();
        alienJazz.dispose();
    }
}