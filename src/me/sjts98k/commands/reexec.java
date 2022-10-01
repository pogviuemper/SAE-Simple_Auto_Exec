package me.sjts98k.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class reexec implements CommandExecutor, Listener {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("SAE_AutoExec.txt"));
            String aline;
            while ((aline = reader.readLine()) != null) {
                if (!aline.startsWith("// ")&&!aline.toLowerCase().endsWith(" //banned")&&!aline.toLowerCase().endsWith
                        (" //onplayerjoin")&&!aline.toLowerCase().endsWith(" //onplayerdeath")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), aline);
                }}
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);}
        return true;
    }
}