package me.fullIdle.worldedithandygui.WorldEditHandyGUI;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Map;

import static me.fullIdle.worldedithandygui.WorldEditHandyGUI.Main.*;

public class Util {
    public static String getMsg(String str){
        return str.replace("§","&");
    }

    //懒得一个个写,直接让插件自己生成配置然后自己读取算了
    public static Inventory setItemStackInInv(ConfigurationSection con, Inventory inv){
        for (String slot : con.getKeys(false)) {
            Map.Entry<Material,Short> materialD = getMaterial(getMsg(con.getString(slot+".Material")));
            ItemStack itemStack = new ItemStack(materialD.getKey(),1,materialD.getValue());
            MyItemStack.ItemStackType type = MyItemStack.ItemStackType.valueOf(con.getString(slot+".Type"));
            String[] data = con.getStringList(slot+".Data").stream().toArray(String[]::new);
            inv.setItem(Integer.parseInt(slot),new MyItemStack(itemStack,type,data));
        }
        return inv;
    }

    //分割 xxxx:xx
    public static Map.Entry<Material,Short> getMaterial(String str){
        String[] strings = str.split(":");
        Material material = Material.getMaterial(strings[0]);
        short da = 0;
        if (strings[1] != null||strings[0] != "") {
            Short.parseShort(strings[1]);
        }
        Map.Entry<Material,Short> entry = new AbstractMap.SimpleEntry<>(material,da);
        return entry;
    }

    public static void stringRunInvoke(Map<String,Object> variables,String... codes){
        StringBuilder builder = new StringBuilder();
        for (String s : codes) {
            builder.append(s+"\n");
        }
        String code = builder.toString();
        ScriptEngine engine = ScriptManager.getEngineByName("nashorn");
        for (Map.Entry<String, Object> variable : variables.entrySet()) {
            engine.put(variable.getKey(),variable.getValue());
        }
        try {
            engine.eval(code);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
