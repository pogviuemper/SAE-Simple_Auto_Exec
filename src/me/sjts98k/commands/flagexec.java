package me.sjts98k.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class flagexec implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        String flag1 = " //banned";
        String flag2 = " //onplayerjoin";
        String flag3 = " //onplayerdeath";

        int a = args.length - 1;
        String cmdunflagged = StringUtils.join(args, " ", 0, a);
        String cmdline = StringUtils.join(args, " ", 0, args.length).toLowerCase();

        if (args.length == 0) {
            sender.sendMessage("Usage: " + cmd.getUsage());

        } else if (cmdline.endsWith(flag1)||cmdline.endsWith(flag2)||cmdline.endsWith(flag3)){
            try {
                List<String> list = Files.readAllLines(new File("SAE_AutoExec.txt").toPath(), Charset.defaultCharset());
                if(list.contains(cmdunflagged + flag1)||list.contains(cmdunflagged + flag2)||list.contains(cmdunflagged + flag3)){
                    sender.sendMessage(ChatColor.RED + "\"" + cmdunflagged + "\" already has been flagged.");

                }else{
                    if(!list.contains(cmdunflagged)){
                        sender.sendMessage(ChatColor.RED + "\"" + cmdunflagged + "\" is not on the list.");

                    }else{
                        String lastarg = StringUtils.remove(cmdline, cmdunflagged).trim();
                        File file = new File("SAE_AutoExec.txt");
                        File file_temp = new File("SAE_AutoExec_empty.txt");
                        Path path_ft = Paths.get("SAE_AutoExec_empty.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file_temp));
                        list.forEach((line) -> {
                            try {
                                if(!line.equals(cmdunflagged)) {
                                    writer.write(line + "\n");
                                }else{
                                    writer.write(line + " " + lastarg + "\n");}
                            } catch (IOException e) {
                                throw new RuntimeException(e);}});
                        list.clear();
                        writer.close();
                        file.delete();
                        Files.move(path_ft, path_ft.resolveSibling("SAE_AutoExec.txt"));
                        sender.sendMessage("The command has been flagged.");}}

            } catch (IOException e) {
                throw new RuntimeException(e);}

        }else{
            sender.sendMessage(ChatColor.RED + "This is not a valid flag. Use /flagexeclist command to see all of them.");}

        return true;
    }
}