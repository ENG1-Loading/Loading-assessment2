package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Clickables.Pantry;
import com.mygdx.game.Clickables.RepPowerup;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.Money;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(GdxTestRunner.class)
public class RepTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private RepPowerup rep;
    private Powerups powerups;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        powerups = Mockito.mock(Powerups.class);
        rep = new RepPowerup( utils,gameScreen, powerups);
    }
    @Test
    public void repClickableIsNotNull() {
        ImageButton repButton = rep.getRepButton();
        assertNotNull(repButton);
    }

    @Test
    public void testClick() {
        when(powerups.buyRep()).thenReturn(true);
        rep.onRepButtonClicked(powerups, gameScreen);
        verify(powerups, times(1)).buyRep();
        verify(gameScreen, times(1)).addRep(1);
    }

}
