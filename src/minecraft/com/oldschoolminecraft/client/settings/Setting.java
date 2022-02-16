package com.oldschoolminecraft.client.settings;

import java.util.ArrayList;
import java.util.Arrays;

public class Setting<T>
{
    private String key;
    private T value;
    private ArrayList<String> requiredPerks;

    public Setting(String key, T value, String... requiredPerks)
    {
        this.key = key;
        this.value = value;
        this.requiredPerks.addAll(Arrays.asList(requiredPerks));
    }

    public String getKey()
    {
        return key;
    }

    public T getValue()
    {
        return value;
    }
}
