package com.mygdx.tests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.*;
import com.mygdx.game.Clickables.BunClickable;
import com.mygdx.game.Clickables.BurgerClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Order;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class BurgerTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private BurgerClickable burger;
    private Helpers helpers;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);

        when(gameScreen.getCooks()).thenReturn(new Array<Cook>());
        when(gameScreen.getCustomers()).thenReturn(new ArrayList<ArrayList<Customer>>());
        when(gameScreen.getCustomerCount()).thenReturn(0);
        when(gameScreen.getMoney()).thenReturn(Mockito.mock(Money.class));
        burger = new BurgerClickable(game, utils,gameScreen);
    }

    @Test
    public void bunClickableIsNotNull() {
        ImageButton burgerClickable = burger.getBurgerClickable();
        assertNotNull(burgerClickable);
    }

//    @Test
//    public void testBurgerClick() throws IOException {
//        Cook cook = Mockito.mock(Cook.class);
//        cook.CookStack = new Stack<Ingredient>();
//        gameScreen.getCooks().add(cook);
//
//        Customer customer = Mockito.mock(Customer.class);
//        Order order = Mockito.mock(Order.class);
//        Order mockOrder = Mockito.mock(Order.class);
//
//        when(customer.customerOrder).thenReturn(new Order("burger", null, new Burger()));
//        when(customer.selfComplete).thenReturn(false);
//        ArrayList<Customer> customerList = new ArrayList<>();
//        customerList.add(customer);
//        gameScreen.getCustomers().add(customerList);
//
//        InputEvent touchDownEvent = new InputEvent();
//        touchDownEvent.setType(InputEvent.Type.touchDown);
//        burger.getBurgerClickable().fire(touchDownEvent);
//
//        InputEvent touchUpEvent = new InputEvent();
//        touchUpEvent.setType(InputEvent.Type.touchUp);
//        burger.getBurgerClickable().fire(touchUpEvent);
//
//        verify(gameScreen.getMoney()).addMoney(100);
//        verify(cook.CookStack.isEmpty());
//        assertTrue(customer.selfComplete);
//    }
}
