package com.mygdx.tests;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Helpers;
import com.mygdx.game.PiazzaPanic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(GdxTestRunner.class)
public class HelpersTests {
    private PiazzaPanic game;
    private Helpers helpers;

    @Before
    public void setUp() {
        game = Mockito.mock(PiazzaPanic.class);
        helpers = new Helpers(game);
    }

    @Test
    public void testCustomersToServeOutput() {
        int returnValue = helpers.CustomersToServe(3);
        assertTrue(returnValue <= 3 && returnValue >= 1);
    }

    @Test
    public void testTexRegionNotNull() {
        TextureRegionDrawable drawable= helpers.getColoredDrawable(20, 5, Color.GREEN);
        assertNotNull(drawable);
    }

    @Test
    public void findFreeStation() {
        ArrayList<Integer> stations = new ArrayList<>();
        stations.add(2);
        stations.add(4);
        stations.add(1);
        int freeStation = helpers.findFreeStation(stations);
        assertTrue(freeStation == 0 || freeStation == 3 || freeStation == 5);

    }
}
