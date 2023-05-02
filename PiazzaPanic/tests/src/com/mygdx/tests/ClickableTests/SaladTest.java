package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.RepPowerup;
import com.mygdx.game.Clickables.SaladClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Order;
import com.mygdx.game.Food.Potato;
import com.mygdx.game.Money;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(GdxTestRunner.class)
public class SaladTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private SaladClickable saladClickable;
    private Powerups powerups;

    @Before
    public void setUp() {
        game = mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = mock(GameScreen.class);
        saladClickable = new SaladClickable( game,utils, gameScreen);
        when(gameScreen.getMoney()).thenReturn(mock(Money.class));
    }

    @Test
    public void saladClickableIsNotNull() {
        ImageButton saladClickable1 = saladClickable.getSaladClickable();
        assertNotNull(saladClickable1);
    }

    @Test
    public void testClick() throws IOException {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        Ingredient tomato = new Ingredient("tomato", null, null, null);
        tomato.prepare();
        cook.CookStack.push(tomato);
        Ingredient lettuce = new Ingredient("lettuce", null, null, null);
        lettuce.prepare();
        cook.CookStack.push(lettuce);
        Customer customer = new Customer(new com.badlogic.gdx.scenes.scene2d.Actor(), true);
        customer.customerOrder= new Order("salad", null, new Potato());
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(customer);
        ArrayList<ArrayList<Customer>> customersA = new ArrayList<ArrayList<Customer>>();
        customersA.add(customers);
        when(gameScreen.getCustomers()).thenReturn(customersA);
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        saladClickable.onClick(gameScreen);
        verify(gameScreen).hideServingScreen();
    }
}
