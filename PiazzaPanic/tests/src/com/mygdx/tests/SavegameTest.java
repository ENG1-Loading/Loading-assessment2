package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.SaladClickable;
import com.mygdx.game.Clickables.Savegame;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
@RunWith(GdxTestRunner.class)
public class SavegameTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Savegame savegame;

    @Before
    public void setUp() throws IOException {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        savegame = new Savegame( game,utils, gameScreen);
    }

    @Test
    public void saladClickableIsNotNull() {
        ImageButton saveClickable = savegame.getSaveClickable();
        assertNotNull(saveClickable);
    }
}
