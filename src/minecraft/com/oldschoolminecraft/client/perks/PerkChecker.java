package com.oldschoolminecraft.client.perks;

import com.oldschoolminecraft.client.Client;
import com.oldschoolminecraft.client.api.api;

public class PerkChecker {
    
    public static void run() {
        //String res = api.api_request("perks/get_perks", "username=" + Client.username);
        String res = api.api_request("perks/get_perks", "username=" + "CodeKid0");

        if (!res.contains("perks")) {
            return;
        }
        if (res.contains("supporter_menu")) {
        	Client.isSupporter = true;
        }
    }
    
}
