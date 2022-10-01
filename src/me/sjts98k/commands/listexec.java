package me.sjts98k.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;

public class listexec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        File file = new File("SAE_AutoExec.txt");
        try {
            int nofbanned = 0;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String aline;
            sender.sendMessage(ChatColor.GOLD + "---All commands in the list are---");
            while ((aline = reader.readLine()) != null) {
                if(aline.toLowerCase().endsWith(" //banned")){
                    nofbanned++;}
                if(!aline.toLowerCase().endsWith(" //banned")&&!aline.startsWith("// ")){
                    sender.sendMessage(aline);}}
            sender.sendMessage(ChatColor.DARK_GRAY + "+" + nofbanned + " banned commands");
            sender.sendMessage(ChatColor.GOLD + "-----------------------------");
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);}
        return true;
    }
}