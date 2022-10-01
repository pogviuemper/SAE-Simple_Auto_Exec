package me.sjts98k.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class addexec implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("Usage: " + cmd.getUsage());

        }else{
            try{
                String cmdline = StringUtils.join(args, " ", 0, args.length);
                List<String> list = Files.readAllLines(new File("SAE_AutoExec.txt").toPath(), Charset.defaultCharset());

                if(list.contains(cmdline + " //banned".toLowerCase())){
                    sender.sendMessage(ChatColor.RED + "This command is flagged as banned and it can't be added.");

                }else {
                    FileWriter fw = new FileWriter(Paths.get("SAE_AutoExec.txt").toFile(), true);
                    fw.write("\n" + cmdline);
                    sender.sendMessage("Command has been added to the list.");
                    fw.close();}

                list.clear();
            }catch (IOException e) {
                throw new RuntimeException(e);}}
        return true;
    }
}