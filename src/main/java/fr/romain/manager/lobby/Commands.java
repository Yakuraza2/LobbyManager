package fr.romain.manager.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.romain.manager.lobby.PluginMessageManager.connectToServer;
import static fr.romain.manager.lobby.PluginMessageManager.sendPluginMessage;

public class Commands implements CommandExecutor {
    public Commands(Core core) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1){
            sendPluginMessage("PlaceLibre", args[0], player);
            PluginMessageManager.playerServer.put(player, args[0]);
        }

        return false;
    }
}
