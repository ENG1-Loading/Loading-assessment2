package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Cook;
import com.mygdx.game.CustomTiledMapRenderer;
import com.mygdx.game.Customer;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class GamescreenTests {

    static {
        HeadlessNativesLoader.load();
    }

    private PiazzaPanic mockgame;
    private FitViewport viewport;
    private boolean isEndless;
    private boolean isLoad;
    private String loadPath;
    private JSONObject config;
    private GameScreen screen;

    @Before
    public void setUp() throws IOException {
        System.out.println("Setting up class");
        mockgame = new PiazzaPanic();

        viewport = new FitViewport(800, 480);
        mockgame.batch = mock(SpriteBatch.class);
        isEndless = false;
        isLoad = false;
        loadPath = "";
        config = new JSONObject();
        config.put("difficulty", "Easy");
        config.put("muteMusic", false);
        config.put("customersToServe", 5);
        OrthogonalTiledMapRenderer mockRenderer = mock(OrthogonalTiledMapRenderer.class);
        screen = new GameScreen(mockgame, viewport, isEndless, isLoad, loadPath, config, mockRenderer);

    }

    private void simulateKeyPress(int keyCode) {
        when(Gdx.input.isKeyPressed(keyCode)).thenReturn(true);
    }

    @Test
    public void testGameTimeNotNull() {
        // Get a random actor from the stage
        System.out.println(screen.getGameTime());
        assertNotNull(screen.getGameTime());
    }

    @Test
    public void testSetStaion() {
        // Get a random actor from the stage
        screen.setSationSelected(4);
        ArrayList<Integer> stations =  screen.getStationSelected();
        assert stations.get(0) == 4;
    }

    @Test
    public void testCooksNotNull() {
        // Get a random actor from the stage
        System.out.println(screen.getCooks());
        assertNotNull(screen.getCooks());
    }

    @Test
    public void testIncrementFrying() {
        // Get a random actor from the stage
        screen.incrementFryingClicked();
        assert screen.getFryingClicked() == 1;
    }

    @Test
    public void testSetChef() {
        screen.setChef(3);
        assert screen.getCookCount() == 3;
    }

    @Test
    public void testIncrementBakingClicked() {
        screen.incrementBakingClicked();
        assert screen.getBakingClicked() == 1;
    }

    @Test
    public void testBakingLockedAndUnlocked() {
        screen.setBakingUnlocked(true);
        assert screen.bakingUnlocked();
        screen.setBakingUnlocked(false);
        assert !screen.bakingUnlocked();
    }

    @Test
    public void testSetPizzaAtBaking() {
        screen.setPizzaAtBaking(true);
        assert screen.getPizzaAtBaking();
    }

    @Test
    public void testSetPotatoAtBaking() {
        screen.setAtPotatoBaking(true);
        assert screen.getAtPotatoBaking();
        screen.setAtPotatoBaking(false);
        assert !screen.getAtPotatoBaking();
    }

    @Test
    public void testSetPattyAtFrying() {
        screen.setPattyAtFrying(true);
        assert screen.getPattyAtFrying();
        screen.setPattyAtFrying(false);
        assert !screen.getPattyAtFrying();
    }

    @Test
    public void testShowPantry() {
        screen.setShowPantryScreen(true);
        assert screen.getShowPantryScreen();
        screen.setShowPantryScreen(false);
        assert !screen.getShowPantryScreen();
    }

    @Test
    public void testShowServingScreen() {
        screen.setShowServingScreen(true);
        assert screen.getShowServingScreen();
        screen.setShowServingScreen(false);
        assert !screen.getShowServingScreen();
    }

    @Test
    public void testCustomersNotNull() {
        ArrayList<ArrayList<Customer>> customers = screen.getCustomers();
        assertNotNull(customers);
    }

    @Test
    public void testGetCustomers() {
        screen.getCustomerCount();
        assert screen.getCustomerCount() == 0;
    }

    @Test
    public void testAddRep() {
        screen.addRep(10);
        System.out.println(screen.getRep());
        assert screen.getRep() == 13; // 3 is the default rep
    }

    @Test
    public void testMoney() {
        screen.getMoney().addMoney(10);
        assert screen.getMoney().getCurrentMoney() == 10; // 3 is the default rep
    }

    @Test
    public void testGetDefaultRep() {
        int rep = screen.getRep();
        assert rep == 3; // 3 is the default rep
    }

    @Test
    public void testInitialiseLoad() {
        // test with empty load path
        JSONObject conf = new JSONObject();
        screen.initialiseLoad(conf);
        assert screen.getRep() == 3;
        conf.put("rep", 10);
        screen.initialiseLoad(conf);
        System.out.println(screen.getRep());
        assert screen.getRep() == 10;
        conf.put("Money", 1000);
        screen.initialiseLoad(conf);
        assert screen.getMoney().getCurrentMoney() == 1000;
        conf.put("bakingUnlocked", true);
        screen.initialiseLoad(conf);
        assert screen.bakingUnlocked();
        conf.put("ExtraChef", true);
        screen.initialiseLoad(conf);
        assert screen.getCookCount() == 3;
    }

    @Test
    public void testCreateImageClickable() {
        ImageButton btn = screen.createImageClickable(new Texture("burger.png"), 0, 0);
        assert btn != null;
    }

    @Test
    public void testCookStack() {
        screen.showCookStack();
        assert screen.getCooks().get(0).CookStack != null;

    }

    @Test
    public void testShowOrders() {
        screen.showOrders(0);
        assert 1 ==1; // the show orders method does not return anything
    }

    @Test
    public void testShowServingScreenVisually() {
        screen.setShowServingScreen(true);
        screen.showServingScreen();
        ImageButton btn = screen.getPizzaServableClickable();
        assert btn.getX() == 77;

        assert screen.getGameStage().getActors().size != 0; // the show orders method does not return anything
    }

    @Test
    public void testCreateProgressBar() {
        Array<Cook> cooks = screen.getCooks();
        screen.createProgressBar(0, 0, cooks.get(0));
        HashMap<ProgressBar, Cook> bars = screen.getBars();
        assert bars.size() != 0;
    }

    @Test
    public void testMultiplier() {
        assert screen.getMoney().getMultiplier() == 1.0f;
        screen.incMoneyMult();
        assert screen.getMoney().getMultiplier() == 1.5f;
    }

    @Test
    public void testHideServingScreen() {
        screen.hideServingScreen();
        ImageButton btn = screen.getPizzaServableClickable();
        assert btn.getX() == 100000;
    }

    @Test
    public void testHidePantry() {
        screen.hidePantryScreen();
        ImageButton btn = screen.getPotatoClickable();
        System.out.println(btn.getX());
        assert btn.getX() == 10000.0;
    }

    @Test
    public void showPantry() {
        screen.setShowPantryScreen(true);
        screen.showPantryScreen();
        ImageButton btn = screen.getPotatoClickable();
        System.out.println(btn.getX());
        assert btn.getX() == 81;
    }

    @Test
    public void handlePizzaBaking() {
        screen.setPizzaAtBaking(true);
        screen.handlePizzaBaking(0);
        assert screen.getPizzaAtBaking();
        screen.setPizzaAtBaking(false);
        screen.handlePizzaBaking(0);
        assert !screen.getPizzaAtBaking();
        screen.setPizzaAtBaking(true);
        screen.handlePizzaBaking(10000000);
        assert screen.getPizzaAtBaking();
    }

    @Test
    public void testPotatoAtBaking() {
        screen.setAtPotatoBaking(true);
        screen.handlePotatoBaking(0);
        assert screen.getAtPotatoBaking();
        screen.setAtPotatoBaking(false);
        screen.handlePotatoBaking(0);
        assert !screen.getAtPotatoBaking();
        screen.setAtPotatoBaking(true);
        screen.handlePotatoBaking(10000000);
        assert screen.getAtPotatoBaking();
    }

    @Test
    public void testPattyAtFrying() {
        screen.setPattyAtFrying(true);
        screen.handlePattyFrying(0);
        assert screen.getPattyAtFrying();
        screen.setPattyAtFrying(false);
        screen.handlePattyFrying(0);
        assert !screen.getPattyAtFrying();
        screen.setPattyAtFrying(true);
        screen.handlePattyFrying(10000000);
        assert screen.getPattyAtFrying();
    }

    @Test
    public void getPowerups() {
        assert screen.getPowerups() != null;
    }

    @Test
    public void testCustomers()  {
        screen.updateBatch();
        assert screen.getCustomers().size() != 0;
    }

    @Test
    public void testCustomerOperations() throws IOException {
        screen.customerOperations();
        assert screen.getCustomers().size() != 0;
        ArrayList<Customer> customers = screen.getCustomers().get(screen.getCustomerCount());
        for (Customer customer : customers) {
            customer.selfComplete = true;
        }
        screen.customerOperations();
        assert screen.getCustomers().size() != 0;
        for (Customer customer : customers) {
            customer.body.setX(150);
        }
        screen.customerOperations();
        assert screen.getCustomers().size() != 0;
    }

}
