package com.oldschoolminecraft.client.perks;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.api.API;
import java.util.HashMap;
import java.util.ArrayList;

public class PerkChecker {
    private HashMap<String, ArrayList<String>> perkList = new HashMap<>();

    public HashMap<String, ArrayList<String>> getPerkList()
    {
        return perkList;
    }

    public void fetchPerks()
    {
        //String res = api.api_request("perks/get_perks", "username=" + Client.username);
        String res = API.api_request("perks/get_perks", "username=" + "CodeKid0");

        if (!res.contains("perks"))
            return;
        if (res.contains("supporter_menu"))
        	Client.getInstance().isSupporter = true;
    }
}
