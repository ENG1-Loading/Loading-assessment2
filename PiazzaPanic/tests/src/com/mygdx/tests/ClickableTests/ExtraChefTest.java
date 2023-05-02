package com.mygdx.tests.ClickableTests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.Baking;
import com.mygdx.game.Clickables.ExtraChef;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Powerups.Powerup;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GdxTestRunner.class)
public class ExtraChefTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private ExtraChef extraChef;
    private Powerups powerups;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        powerups = Mockito.mock(Powerups.class);
        extraChef = new ExtraChef(utils, gameScreen, powerups);
    }

    @Test
    public void extraChefClickableIsNotNull() {
        ImageButton extraChefClickable = extraChef.getExtraChefClickable();
        assertNotNull(extraChefClickable);
    }

    @Test
    public void extraChefPowerupIsNotNull() {
        when(powerups.setChefCount(3)).thenReturn(true);
        extraChef.onExtraChefButtonClicked(powerups, gameScreen);
        System.out.println(gameScreen.getCookCount());
        verify(gameScreen).setChef(3);
    }


}
