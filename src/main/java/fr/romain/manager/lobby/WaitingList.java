package fr.romain.manager.lobby;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WaitingList {

    private List<Player> waitingList = new ArrayList<>();

    private final String server;

    public WaitingList(String pServer){
        server = pServer;
    }

    public void addPlayer(Player p) { this.waitingList.add(p); }

    public List<Player> getWaitingList() { return waitingList; }
}
