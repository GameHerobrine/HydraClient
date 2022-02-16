package com.oldschoolminecraft.client.perks;

import com.oldschoolminecraft.client.api.API;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class PerkManager
{
    private ArrayList<String> perkList = new ArrayList<>();

    public ArrayList<String> getPerkList()
    {
        return perkList;
    }

    public final boolean hasPerk(String perk)
    {
        return perkList.contains(perk);
    }

    public final void fetchPerks(String username)
    {
        String res = API.api_request("perks/get_perks", "username=" + username);

        try
        {
            JSONObject obj = new JSONObject(res);
            if (obj.has("perks"))
            {
                JSONArray perks = obj.getJSONArray("perks");
                for (int i = 0; i < perks.length(); i++) perkList.add(perks.getString(i));
            }
        } catch (Exception ex) {
            System.out.println("There was a problem getting perks for: " + username);
            ex.printStackTrace();
        }
    }
}
