package fr.romain.manager.lobby;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static void create(final String fileName){
        if(!Core.getPluginDataFolder().exists()){
            Core.getPluginDataFolder().mkdir();
            System.out.println("[FILES] Le dossier " + Core.getPluginDataFolder().getName() + " n'existe pas.");
            System.out.println("[FILES] Création du dossier " + Core.getPluginDataFolder().getName());
        }else{
            Core.logger("Le dossier 'rush' existe");}

        File file = new File(Core.getPluginDataFolder(), fileName + ".yml");

        if(!file.exists()){
            System.out.println("[FILES] Le fichier " + fileName + ".yml n'existe pas.");
            try {
                file.createNewFile();
                System.out.println("[FILES] Création du fichier " + fileName + ".yml");
            } catch (IOException e) {
                Core.logger(4, "Error during creation of " + fileName);
                throw new RuntimeException(e);
            }
        }else{
            Core.logger("Le fichier " + fileName + " existe");}
    }

    public static File get(String fileName){return new File(Core.getPluginDataFolder(), fileName + ".yml");}

    public static void set(YamlConfiguration config, String path, int value){  config.set(path, value); }
    public static void set(YamlConfiguration config, String path, String value) {
        config.set(path, value);
    }

    public static void setLocation(YamlConfiguration config, String path, Location loc){
        config.set(path + ".x", loc.getBlockX());
        config.set(path + ".y", loc.getBlockY());
        config.set(path + ".z", loc.getBlockZ());
        config.set(path + ".yaw", (int) loc.getYaw());
        config.set(path + ".pitch", (int) loc.getPitch());
    }

    public static YamlConfiguration getConfig(String fileName){
        File gameFile = FileManager.get(fileName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(gameFile);
        config.options().copyDefaults(true);

        return config;
    }

    public static YamlConfiguration getConfig(){
        return getConfig("config");
    }

    public static String prefix(){
        return getConfig().getString("messages.prefix") + " ";
    }

    public static String getConfigMessage(String path, Player player, boolean prefix){
        String message = " ";

        if(prefix) message = prefix() + getConfig().getString("messages." + path);
        else message = getConfig().getString("messages." + path);

        if(player != null) message = message.replaceAll("<player>", player.getName());

        try{
            return message.replaceAll("&", "§");
        } catch(NullPointerException e){
            e.fillInStackTrace();
            Core.logger(4, "NullPointerException during searching of " + path + " message. message not found.");
            return "§cError: message not found";
        }

    }
    public static String getConfigMessage(String path, Player player){ return getConfigMessage(path, player, false); }

    public static void save(YamlConfiguration config, File file){
        try {
            config.save(file);
            System.out.println("Sauvegarde terminée !");
        } catch (IOException e) {
            Core.logger(4, "Erreur durant la sauvegarde de " + file.getName() + " :");
            throw new RuntimeException(e);
        }
    }
}
