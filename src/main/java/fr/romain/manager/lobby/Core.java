package fr.romain.manager.lobby;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Core extends JavaPlugin {

    private static File dataFolder;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        dataFolder = getDataFolder();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static File getPluginDataFolder() { return dataFolder; }

    public static void logger(String log) { logger(0, log);}
    public static void logger(int gravity, String log) {
        switch(gravity) {
            case 0:
                //if debug==false return;
                System.out.print("[Rush] ");
                break;
            case 1:
                System.out.print("§e[RUSH] ");
                break;
            case 2:
                System.out.print("§6[RUSH | PROBLEM] ");
                break;
            case 3:
                System.out.print("§c[RUSH | ERROR] ");
                break;
            default:
                System.out.print("§4[RUSH | ERROR] ");
                break;
        }

        System.out.println(log);

    }
}
