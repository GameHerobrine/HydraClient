package com.oldschoolminecraft.client.perks;

public enum Perks
{
    STAFF("staff_menu"),
    SUPPORTER("supporter_menu"),
    DEBUG("debug"),
    FLIGHT("flight");

    Perks(String perk)
    {
        this.perk = perk;
    }

    public String getPerk()
    {
        return perk;
    }

    private final String perk;
}
