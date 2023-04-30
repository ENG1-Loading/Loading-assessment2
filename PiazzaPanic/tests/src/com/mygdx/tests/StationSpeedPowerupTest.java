package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Clickables.SaladClickable;
import com.mygdx.game.Clickables.StationSpeedPowerup;
import com.mygdx.game.Clickables.Utils;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Powerups.Powerups;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

@RunWith(GdxTestRunner.class)
public class StationSpeedPowerupTest {
    private PiazzaPanic game;
    private Utils utils;
    private GameScreen gameScreen;
    private StationSpeedPowerup stationSpeedPowerup;
    private Powerups powerups;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        utils = new Utils();
        gameScreen = Mockito.mock(GameScreen.class);
        stationSpeedPowerup = new StationSpeedPowerup(utils, gameScreen, powerups);
    }

    @Test
    public void stationClickableIsNotNull() {
        ImageButton stationClickable = stationSpeedPowerup.getStationClickable();
        assertNotNull(stationClickable);
    }
}
