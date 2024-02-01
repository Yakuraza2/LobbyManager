package fr.romain.manager.lobby;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashMap;

import static fr.romain.manager.lobby.Core.logger;

public class PluginMessageManager implements PluginMessageListener {

    public static HashMap<Player, String> playerServer =  new HashMap<>();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equalsIgnoreCase("romain:rush")) {
            logger("Other chanel");
            return;
        }
        logger("Plugin Message received");
        final ByteArrayDataInput in = ByteStreams.newDataInput(message);
        final  String subchannel = in.readUTF();
        if (subchannel.equals("PlaceLibre")) {
            String response = in.readUTF();
            Player playerToConnect = Bukkit.getPlayer(in.readUTF());
            String server = in.readUTF();

            if(playerToConnect == null) {
                logger("playerToConnect is null");
                return;
            }
            if(response.equalsIgnoreCase("YES")){
                connectToServer(playerToConnect, server);
                logger("Connecting " + playerToConnect + " to " + playerServer.get(playerToConnect));
            }else{
                playerToConnect.sendMessage("&bVous êtes actuellement en file d'attente");
                logger(playerToConnect.getDisplayName() + " is now in waiting queue.");
            }

        }
    }

    public static void sendPluginMessage(String subChannel, String arg, Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(subChannel);
        out.writeUTF(arg);
        out.writeUTF(player.getName());

        logger(subChannel + " " + arg + " for " + player.getName());

        player.sendPluginMessage(Core.getPlugin(Core.class), "romain:rush", out.toByteArray());
    }

    public static void sendPluginMessage(String subChannel, String arg){
        sendPluginMessage(subChannel, arg, Iterables.getFirst(Bukkit.getOnlinePlayers(), null));
    }

    public static void connectToServer(Player p, String serverName){

        //noms des serveurs: lobby, ou l'id du rush.

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);

        p.sendPluginMessage(Core.getPlugin(Core.class), "BungeeCord", out.toByteArray());
    }
}
