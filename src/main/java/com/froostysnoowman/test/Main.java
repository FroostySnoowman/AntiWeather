package com.froostysnoowman.test;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.froostysnoowman.test.Commands.*;


public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {

        Bukkit.getServer().getConsoleSender().sendMessage("AntiWeather loaded.");

        // Commands
        getCommand("fly").setExecutor(new FlyCommand(this));

        // config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
