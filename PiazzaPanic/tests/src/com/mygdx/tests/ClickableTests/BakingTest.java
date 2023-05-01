package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;

import com.mygdx.game.Clickables.*;


import com.mygdx.game.Cook;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



@RunWith(GdxTestRunner.class)
public class BakingTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Baking baking;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        baking = new Baking(game, utils, gameScreen);
    }

    private void simulateClick2(ImageButton button) {
        ClickListener clickListener = null;
        for (EventListener listener: button.getListeners()) {
            if (listener instanceof ClickListener) {
                clickListener = (ClickListener) listener;
                break;
            }
        }
        if (clickListener == null) {
            throw new RuntimeException("No click listener found");
        }

        InputEvent event = mock(InputEvent.class);
        Mockito.when(event.getListenerActor()).thenReturn(button);
        when(event.getTarget()).thenReturn(button);
        clickListener.clicked(event, 0, 0);
    }
    public void simulateClick(ImageButton imageButton) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.setType(InputEvent.Type.touchDown);
        imageButton.fire(inputEvent);

        inputEvent.setType(InputEvent.Type.touchUp);
        imageButton.fire(inputEvent);
    }
    @Test
    public void bakingClickableIsNotNull() {
        ImageButton bakingClickable = baking.getBakingClickable();
        assertNotNull(bakingClickable);
    }




    // Add more tests as needed for other aspects of the Baking class
}

