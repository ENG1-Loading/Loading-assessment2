package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.Clickables.BunClickable;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

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
}
