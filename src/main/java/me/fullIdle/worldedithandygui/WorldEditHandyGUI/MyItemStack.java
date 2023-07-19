package me.fullIdle.worldedithandygui.WorldEditHandyGUI;

import org.bukkit.inventory.ItemStack;

public class MyItemStack extends ItemStack {
    public enum ItemStackType {
        RUNCMD,
        RUNCODE,
    }

    private ItemStack originalItem;
    private ItemStackType itemStackType;

    public String[] getMyData() {
        return myData;
    }

    private String[] myData;

    public ItemStack getOriginalItem() {
        return originalItem;
    }

    public ItemStackType getItemStackType() {
        return itemStackType;
    }

    MyItemStack(ItemStack itemStack, ItemStackType itemStackType, String[] myData){
        super(itemStack);
        this.originalItem = itemStack;
        this.itemStackType = itemStackType;
        this.myData = myData;
    }
}
