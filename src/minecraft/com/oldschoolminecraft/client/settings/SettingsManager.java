package com.oldschoolminecraft.client.settings;

import com.oldschoolminecraft.client.Client;

import java.util.HashMap;

public class SettingsManager
{
    private final HashMap<String, Boolean> boolsMap = new HashMap<>();

    public void setBool(String key, boolean value)
    {
        boolsMap.put(key, value);
    }

    public void toggleBool(String key, boolean backup)
    {
        // weird way of writing this, i know
        boolean old = boolsMap.getOrDefault(key, !backup);
        setBool(key, !old);
    }

    public void toggleBool(String key, boolean backup, String... requiredPerks)
    {
        for (String p : requiredPerks)
            if (!Client.getInstance().perkManager.hasPerk(p)) return;
        toggleBool(key, backup);
    }

    public boolean getBool(String key, boolean defaultValue)
    {
        return boolsMap.getOrDefault(key, defaultValue);
    }

    public void loadFromDisk()
    {
        //TODO: eventually all these settings should be persisted in storage
    }
}
