package fr.romain.manager.lobby;

import fr.romain.manager.lobby.managers.PluginMessageManager;
import fr.romain.manager.lobby.managers.WaitingListManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static fr.romain.manager.lobby.managers.PluginMessageManager.sendPluginMessage;
import static fr.romain.manager.lobby.managers.WaitingListManager.connectFirstPlayer;

public class Commands implements CommandExecutor {
    public Commands(Core core) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1){

            List<Player> waitingList = Core.waitingLists.getOrDefault(args[0], new ArrayList<>());
            waitingList.add(player);
            Core.waitingLists.put(args[0], waitingList);

            WaitingListManager.connectFirstPlayer(args[0]);
        }

        return false;
    }
}
