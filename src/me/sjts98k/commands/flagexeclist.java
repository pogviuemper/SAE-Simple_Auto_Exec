package me.sjts98k.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class flagexeclist implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "---All available flags are--------");
        sender.sendMessage(ChatColor.GRAY + "Flags should start with two slashes");
        sender.sendMessage(ChatColor.YELLOW + "//Banned" + ChatColor.WHITE + " - makes command unaddable to the list");
        sender.sendMessage(ChatColor.YELLOW + "//OnPlayerJoin" + ChatColor.WHITE + " - executes a command whenever any player joins");
        sender.sendMessage(ChatColor.YELLOW + "//OnPlayerDeath" + ChatColor.WHITE + " - executes a command whenever any player dies");
        sender.sendMessage(ChatColor.GOLD + "-----------------------------");
        return true;
    }
}
