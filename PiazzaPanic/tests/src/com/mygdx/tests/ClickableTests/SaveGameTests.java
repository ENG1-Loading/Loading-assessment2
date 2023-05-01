package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.Savegame;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import com.mygdx.game.Clickables.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class SaveGameTests {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private Savegame savegame;

    @Before
    public void setUp() throws IOException {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        savegame = new Savegame(game, utils, gameScreen);

    }

    @Test
    public void saveClickableIsNotNull() {
        ImageButton saveClickable = savegame.getSaveClickable();
        assertNotNull(saveClickable);
    }

}
