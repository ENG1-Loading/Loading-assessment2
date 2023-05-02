package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.Clickables.BunClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class BunTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private BunClickable bun;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        bun = new BunClickable(utils,gameScreen);
    }

    @Test
    public void bunClickableIsNotNull() {
        ImageButton bunClickable = bun.getBunClickable();
        assertNotNull(bunClickable);
    }

    @Test
    public void testClick() {
        Array<Cook> cooks = new Array<Cook>();
        Cook cook = new Cook(new Actor());
        cook.CookBody.setY(32f);
        cook.CookBody.setX(0f);
        cook.CookStack.push(new Ingredient("buns", null, null, null));
        cooks.add(cook);

        when(gameScreen.getCooks()).thenReturn(cooks);
        bun.onClick(gameScreen);
        assert cook.CookStack.size() == 2;
    }


}
