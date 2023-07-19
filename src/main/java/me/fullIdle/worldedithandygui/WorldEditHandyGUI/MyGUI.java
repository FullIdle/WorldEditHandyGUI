package me.fullIdle.worldedithandygui.WorldEditHandyGUI;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import static me.fullIdle.worldedithandygui.WorldEditHandyGUI.Main.main;

public class MyGUI implements InventoryHolder {
    public enum GUIType{
        MAIN,
        BRUSH
    }

    public GUIType getType() {
        return type;
    }

    private final MyGUI.GUIType type;

    private final Inventory inv;

    MyGUI(String title,int size,MyGUI.GUIType type){
        this.type = type;
        this.inv = Bukkit.createInventory(this, size, title);

        if (type == GUIType.MAIN){
            Util.setItemStackInInv(main.getConfig().getConfigurationSection("MainGUI.ItemStack"),this.inv);
            return;
        }

        if (type == GUIType.BRUSH){
            Util.setItemStackInInv(main.getConfig().getConfigurationSection("BrushGUI.ItemStack"),this.inv);
            return;
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
