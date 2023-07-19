package me.fullIdle.worldedithandygui.WorldEditHandyGUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static me.fullIdle.worldedithandygui.WorldEditHandyGUI.Main.main;
import static me.fullIdle.worldedithandygui.WorldEditHandyGUI.Util.*;

public class PlayerListener implements Listener {
    public FileConfiguration config = main.getConfig();
    @EventHandler
    public void dropItem(PlayerDropItemEvent e){

    }

    @EventHandler
    public void clickInv(InventoryClickEvent e){
        MyGUI gui = getMyGuiHolder(e.getView().getTopInventory());
        if (gui == null) {
            return;
        }
        e.setCancelled(true);
        MyItemStack itemStack = (MyItemStack) e.getCurrentItem();
        MyItemStack.ItemStackType type = itemStack.getItemStackType();
        if (type == MyItemStack.ItemStackType.RUNCMD) {
            for (String myDatum : itemStack.getMyData()) {
                Bukkit.dispatchCommand(e.getWhoClicked(),myDatum);
            }
        }
        if (type == MyItemStack.ItemStackType.RUNCODE){
            Map<String,Object> va = new HashMap<>();
            va.put("event",e);
            va.put("player", e.getWhoClicked());
            va.put("GuiTitle",main.getConfig().getString("BrushGUI.Title"));
            Util.stringRunInvoke(va,itemStack.getMyData());
        }
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent e){
        MyGUI gui = getMyGuiHolder(e.getInventory());
        if (gui == null) {
            return;
        }
    }

    @EventHandler
    public void dragInv(InventoryDragEvent e){
        MyGUI gui = getMyGuiHolder(e.getView().getTopInventory());
        if (gui == null) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        Map.Entry<Material,Short> materialD = getMaterial(main.getConfig().getString("BindItem.Material"));
        ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
        if (!itemStack.getType().equals(materialD.getKey())
                &&itemStack.getDurability() != materialD.getValue()
                &&itemStack.getItemMeta().getDisplayName() != getMsg(main.getConfig().getString("BindItem.Name"))) return;
        String title = getMsg(main.getConfig().getString("MainGUI.Title"));
        e.getPlayer().openInventory(new MyGUI(title,6*9, MyGUI.GUIType.MAIN).getInventory());
    }

    public MyGUI getMyGuiHolder(Inventory inv){
        InventoryHolder holder = inv.getHolder();
        if (holder instanceof MyGUI) {
            return (MyGUI) holder;
        }
        return null;
    }
}
