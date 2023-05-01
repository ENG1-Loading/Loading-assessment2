package com.mygdx.tests;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

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
        rep = new RepPowerup( utils,gameScreen, powerups);
    }
    @Test
    public void repClickableIsNotNull() {
        ImageButton repButton = rep.getRepButton();
        assertNotNull(repButton);
    }
//    @Test
//    public void testClick() {
//        ClickListener clickListener = null;
//        for (EventListener listener: rep.getRepButton().getListeners()) {
//            if (listener instanceof ClickListener) {
//                clickListener = (ClickListener) listener;
//                System.out.println("Found a click listener");
//                break;
//            }
//        }
//
//        if (clickListener != null) {
//            InputEvent event = new InputEvent();
//            event.setType(InputEvent.Type.touchDown);
//            event.setButton(Input.Buttons.LEFT);
//            clickListener.touchDown(event, 0, 0, 0, 0); // touchDown event
//            clickListener.touchUp(event, 0, 0, 0, 0); // touchUp event
//        }
//
//        verify(gameScreen, times(1)).addRep(1);
//    }
}
