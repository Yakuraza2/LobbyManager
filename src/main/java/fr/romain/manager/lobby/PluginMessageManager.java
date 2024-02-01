package fr.romain.manager.lobby;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessageManager implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("ConnectRush")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }

    public void sendPluginMessage(String subChannel, String arg, Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(subChannel);
        out.writeUTF(arg);

        player.sendPluginMessage(Core.getPlugin(Core.class), "BungeeCord", out.toByteArray());
    }

    public void sendPluginMessage(String subChannel, String arg){
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
