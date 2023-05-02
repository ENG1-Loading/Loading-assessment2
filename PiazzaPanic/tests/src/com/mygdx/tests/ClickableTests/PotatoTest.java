package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.Pantry;
import com.mygdx.game.Clickables.PotatoClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Cook;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.lang.reflect.Array;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
@RunWith(GdxTestRunner.class)
public class PotatoTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private PotatoClickable potatoClickable;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        potatoClickable = new PotatoClickable( utils,gameScreen);
    }

    @Test
    public void potatoClickableIsNotNull() {
        ImageButton potatoClickable1 = potatoClickable.getPotatoClickable();
        assertNotNull(potatoClickable1);
    }

    @Test
    public void testClick() {
        when(gameScreen.bakingUnlocked()).thenReturn(true);
        com.badlogic.gdx.utils.Array<Cook> cooks = new com.badlogic.gdx.utils.Array<Cook>();
        Cook cook = new Cook(new com.badlogic.gdx.scenes.scene2d.Actor());
        cooks.add(cook);
        when(gameScreen.getCooks()).thenReturn(cooks);
        potatoClickable.onClick(gameScreen);
        assert cook.CookStack.size() == 1;
    }

}
