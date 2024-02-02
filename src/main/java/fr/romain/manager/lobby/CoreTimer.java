package fr.romain.manager.lobby;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static fr.romain.manager.lobby.Core.waitingLists;
import static fr.romain.manager.lobby.managers.FileManager.getConfig;
import static fr.romain.manager.lobby.managers.WaitingListManager.connectFirstPlayer;

public class CoreTimer extends BukkitRunnable {

    private int timer = getConfig().getInt("slots-number-verif");
    private int staticTimer;
    public CoreTimer(){
        staticTimer  = timer;
    }
    @Override
    public void run() {
        if(timer<=0) timer = staticTimer;

        for(String server : waitingLists.keySet()){

            connectFirstPlayer(server);

            for(Player player : waitingLists.get(server)){
                //Mettre a jour les affichages.
            }
        }

        timer--;
    }
}
