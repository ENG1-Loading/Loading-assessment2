package com.mygdx.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;

import static java.nio.file.Files.writeString;
import static java.nio.file.Path.of;

public class ConfigHandler {
    JSONObject config;
    String configPath = Gdx.files.getLocalStoragePath() + "core\\src\\com\\mygdx\\game\\config.json";
    /*
        * Creates a new config handler
        *
        * @throws IOException if the config file cannot be created or read
        *
        * @param none
        *
        * @return none

        *
     */
    public ConfigHandler() throws IOException {
        if (!Files.exists(Paths.get(configPath))) {
            Files.createFile(Paths.get(configPath));
            Files.write(Paths.get(configPath), "{\"difficulty\":\"Easy\",\"muteMusic\":false,\"customersToServe\":5,\"maxTimeToServe\":10}".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }
        String content = new String(Files.readAllBytes(Paths.get(configPath)));
        config = new JSONObject(content);
    }

    /*
     * Creates a new config handler
     *
     * @throws IOException if the config file cannot be created or read
     *
     * @param path the path to the config file
     *
     * @return none
     *
     */
    public ConfigHandler(String path) throws IOException {
        configPath = path;
        if (!Files.exists(Paths.get(configPath))) {
            Files.createFile(Paths.get(configPath));
            Files.write(Paths.get(configPath), "{\"difficulty\":\"Easy\",\"muteMusic\":false,\"customersToServe\":5,\"maxTimeToServe\":10}".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }
        String content = new String(Files.readAllBytes(Paths.get(configPath)));
        config = new JSONObject(content);
    }

    /*
        * Gets the config
        *
        * @param none
        * @return the config
     */
    public JSONObject getConfig() {
        return config;
    }
    /*
        * Gets the customers to serve
        *
        * @param none
        * @return the customers to serve
     */
    public int getCustomersToServe() {
        return config.getInt("customersToServe");
    }

    /*
        * Gets the difficulty
        *
        * @param none
        * @return difficulty
     */
    public String getDifficulty() {
        return config.getString("difficulty");
    }
    /*
        * Gets the mute mode
        *
        * @param none
        * @return mute mode
     */
    public boolean muteMode() {
        return config.getBoolean("muteMusic");
    }
    /*
        * Sets the customers to serve
        *
        * @param customersToServe the customers to serve
        * @return none
     */
    public void setCustomersToServe(int customersToServe) {
        config.put("customersToServe", customersToServe);
        saveConfig();
    }
    /*
        * Sets the mute mode
        *
        * @param muteMode the mute mode
        * @return none
     */
    public void setMuteMode(boolean muteMode) {
        config.put("muteMusic", muteMode);
        saveConfig();
    }
    /*
        * Sets the difficulty
        *
        * @param difficulty the difficulty
        * @return none
     */
    public void setDifficulty(String difficulty) {
        config.put("difficulty", difficulty);
        saveConfig();
    }
    /*
        * Saves the config
        *
        * @param none
        * @return none
     */
    private void saveConfig() {
        try {
            Files.write(Paths.get(configPath), config.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error saving config: " + e.getMessage());
        }
    }

}
