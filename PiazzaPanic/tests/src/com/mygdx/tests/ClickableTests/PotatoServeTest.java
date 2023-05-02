package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.Pantry;
import com.mygdx.game.Clickables.PotatoServeClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Order;
import com.mygdx.game.Food.Potato;
import com.mygdx.game.Money;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class PotatoServeTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private PotatoServeClickable potatoServeClickable;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        when(gameScreen.getMoney()).thenReturn(Mockito.mock(Money.class));

        potatoServeClickable = new PotatoServeClickable(game, utils,gameScreen);
    }

    @Test
    public void potatoClickableIsNotNull() {
        ImageButton potatoServeClickable1 = potatoServeClickable.getPotatoServeClickable();
        assertNotNull(potatoServeClickable1);
    }

    @Test
    public void testClick() throws IOException {

        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        Ingredient potato = new Ingredient("potato", null, null, null);
        potato.prepare();
        cook.CookStack.push(potato);
        Customer customer = new Customer(new com.badlogic.gdx.scenes.scene2d.Actor(), true);
        customer.customerOrder= new Order("potato", null, new Potato());
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(customer);
        ArrayList<ArrayList<Customer>> customersA = new ArrayList<ArrayList<Customer>>();
        customersA.add(customers);
        when(gameScreen.getCustomers()).thenReturn(customersA);
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        potatoServeClickable.onClick(gameScreen);
        verify(gameScreen).hideServingScreen();

    }
}
