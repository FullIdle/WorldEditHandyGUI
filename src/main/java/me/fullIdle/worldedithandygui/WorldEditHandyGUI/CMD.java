package me.fullIdle.worldedithandygui.WorldEditHandyGUI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CMD implements CommandExecutor {
    private List<String> subCmd = Arrays.asList(
            "help",
            "open",
            "get"
    );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        if (sender instanceof Player){
             player = (Player) sender;
        }

        if (args.length > 1){

        }else{
            if (player == null) {
                sender.sendMessage(getHelpMessage());
            }else{

/*
                player.openInventory();
*/

            }
        }
        return false;
    }

    public String getHelpMessage(){
        return  "§bWorldEditHGUI§7---§3HELP\n§r"+
                "§7[cmd] " + "help   不多说\n§r"+
                "§7[cmd] " + "open   打开GUI\n§r"+
                "§7[cmd] " + "get   获取快捷小金斧\n§r";
    }
}
