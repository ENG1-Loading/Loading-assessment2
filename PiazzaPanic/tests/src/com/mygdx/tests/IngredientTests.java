package com.mygdx.tests;


import com.badlogic.gdx.graphics.Texture;
import org.junit.Test;
import com.mygdx.game.Food.Ingredient;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class IngredientTests {

    @Test
    public void testInitialState() {
        Texture notPreparedTexture = new Texture("rawPatty.png");
        Texture preparedTexture = new Texture("prepdPatty.png");
        Texture burntTexture = new Texture("burntPatty.png");
        Ingredient ingredient = new Ingredient("patty", notPreparedTexture, preparedTexture, burntTexture);



        assertEquals("patty", ingredient.name);
        assertFalse(ingredient.getState());
        assertSame(notPreparedTexture, ingredient.getCurrentTexture());
    }

    @Test
    public void testPrepare() {
        // Create a new ingredient
        Texture notPreparedTexture = new Texture("rawPatty.png");
        Texture preparedTexture = new Texture("prepdPatty.png");
        Texture burntTexture = new Texture("burntPatty.png");
        Ingredient ingredient = new Ingredient("patty", notPreparedTexture, preparedTexture, burntTexture);



        // Ensure that the ingredient is not prepared yet
        assertFalse(ingredient.getState());
        assertSame(notPreparedTexture, ingredient.getCurrentTexture());

        // Call the prepare method
        ingredient.prepare();

        // Ensure that the ingredient is now prepared
        assertTrue(ingredient.getState());
    }

    @Test
    public void testEquals() {
        Texture notPreparedTexture1 = new Texture("rawPatty.png");
        Texture preparedTexture1 = new Texture("prepdPatty.png");
        Texture burntTexture = new Texture("burntPatty.png");
        Ingredient ingredient1 = new Ingredient("patty", notPreparedTexture1, preparedTexture1, burntTexture);

        Texture notPreparedTexture2 = new Texture("rawPatty.png");
        Texture preparedTexture2 = new Texture("prepdPatty.png");
        Texture burntTexture2 = new Texture("burntPatty.png");

        Ingredient ingredient2 = new Ingredient("patty", notPreparedTexture2, preparedTexture2, burntTexture2);

        Texture notPreparedTexture3 = new Texture("buns.png");
        Texture preparedTexture3 = new Texture("buns.png");
        Ingredient ingredient3 = new Ingredient("bun", notPreparedTexture3, preparedTexture3, null);



        // Test if two ingredients with the same name and same prepared state are equal
        assertEquals(ingredient1, ingredient2);

        // Test if two ingredients with the same name but different prepared state are not equal
        ingredient2.prepare();
        assertNotEquals(ingredient1, ingredient2);

        // Test if two ingredients with different names and different prepared states are not equal
        assertNotEquals(ingredient1, ingredient3);
    }

    @Test
    public void testUpdateCurrentTexture() {
        Texture notPreparedTexture = new Texture("rawPatty.png");
        Texture preparedTexture = new Texture("prepdPatty.png");
        Texture burntTexture = new Texture("burntPatty.png");

        Ingredient ingredient = new Ingredient("patty", notPreparedTexture, preparedTexture, burntTexture);


        // Ensure that the texture is initially set to notPreparedTexture
        assertSame(notPreparedTexture, ingredient.getCurrentTexture());

        // Prepare the ingredient and update the texture
        ingredient.prepare();
        ingredient.updateCurrentTexture();

        // Ensure that the texture has been updated to preparedTexture
        assertSame(preparedTexture, ingredient.getCurrentTexture());
    }
}
