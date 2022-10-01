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

public class delexec implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("Usage: " + cmd.getUsage());

        } else {
            try {
            File file = new File("SAE_AutoExec.txt");
            Path path_f = Paths.get("SAE_AutoExec.txt");
            List<String> list = Files.readAllLines(new File("SAE_AutoExec.txt").toPath(), Charset.defaultCharset());

            if (args[0].equals("all")) {
                    new FileOutputStream(file).close();
                    FileWriter fw = new FileWriter(path_f.toFile(), true);
                    fw.write("// This file shows what commands will be automatically executed on server start up.\n" +
                            "// Add commands here manually by editing this file, or by using \"/autoexec\" command in-game.\n" +
                            "// Every command should be written just like in-game (but without the slash) and should be under this text box on a new line.\n" +
                            "// If a command ends with \"//\" then it means that the command is flagged. Use /flagexeclist command to see all flags.\n");
                    list.stream().filter(l -> l.endsWith(" //banned")).forEach((line) -> {
                        try {
                            fw.write(line + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);}});
                    sender.sendMessage("All commands have been removed.");
                    fw.close();

            } else {
                try {
                    String cmdline = StringUtils.join(args, " ", 0, args.length);
                    if (!list.contains(cmdline)) {
                        sender.sendMessage(ChatColor.RED + "This command is not on the list.");

                    }else {
                        File file_temp = new File("SAE_AutoExec_empty.txt");
                        Path path_ft = Paths.get("SAE_AutoExec_empty.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file_temp));
                        list.stream().filter(l -> !l.equals(cmdline)).forEach((line) -> {
                            try {
                                writer.write(line + "\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);}});
                        writer.close();
                        file.delete();
                        Files.move(path_ft, path_ft.resolveSibling("SAE_AutoExec.txt"));
                        sender.sendMessage("The command has been removed.");
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);}}
            list.clear();
            }catch (IOException e) {
                throw new RuntimeException(e);}
            }
            return true;
    }
}