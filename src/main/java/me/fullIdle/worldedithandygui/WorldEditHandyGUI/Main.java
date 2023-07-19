package me.fullIdle.worldedithandygui.WorldEditHandyGUI;

import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptEngineManager;

public class Main extends JavaPlugin {
    public static Main main;

    public static ScriptEngineManager ScriptManager;

    @Override
    public void onEnable() {
        reloadConfig();
        main = this;

        getCommand("worldedithandygui").setExecutor(new CMD());

        getServer().getPluginManager().registerEvents(new PlayerListener(),this);

        getLogger().info("插件已载入");
    }

    @Override
    public void reloadConfig() {
        ScriptManager = new ScriptEngineManager();
        saveDefaultConfig();
        if (main != null) {
            super.reloadConfig();
        }
    }
}