package fr.romain.manager.lobby.managers;

import org.bukkit.entity.Player;

import static fr.romain.manager.lobby.Core.logger;
import static fr.romain.manager.lobby.Core.waitingLists;
import static fr.romain.manager.lobby.managers.PluginMessageManager.sendPluginMessage;

public class WaitingListManager {

    public static void connectFirstPlayer(String serverName){
        if(!waitingLists.containsKey(serverName)){
            logger(4, "Server " + serverName + " not found !");
            return;
        }
        if(waitingLists.get(serverName).isEmpty()){
            logger("Any player in the waiting list for " + serverName);
            return;
        }

        Player player = waitingLists.get(serverName).get(0);
        waitingLists.get(serverName).remove(0);
        sendPluginMessage("PlaceLibre", serverName, player);

    }
}
