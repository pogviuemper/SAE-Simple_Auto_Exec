package me.sjts98k;

import me.sjts98k.commands.*;
import me.sjts98k.listeners.OnPlayerDeath;
import me.sjts98k.listeners.OnPlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class Main extends JavaPlugin {
    public void onEnable() {
        System.out.println("SimpleAutoExec is starting");

        getCommand("addexec").setExecutor(new addexec());
        getCommand("addexec").setPermission("sae.addexec");
        getCommand("reexec").setExecutor(new reexec());
        getCommand("reexec").setPermission("sae.reexec");
        getCommand("delexec").setExecutor(new delexec());
        getCommand("delexec").setPermission("sae.delexec");
        getCommand("listexec").setExecutor(new listexec());
        getCommand("listexec").setPermission("sae.listexec");
        getCommand("flagexec").setExecutor(new flagexec());
        getCommand("flagexec").setPermission("sae.flagexec");
        getCommand("flagexeclist").setExecutor(new flagexeclist());
        getCommand("flagexeclist").setPermission("sae.flagexec.list");
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);

        File file = new File("SAE_AutoExec.txt");
        if (!file.exists()) {
            try {
                Files.copy(this.getResource(file.getName()), file.toPath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String aline;
                while ((aline = reader.readLine()) != null) {
                    if (!aline.startsWith("// ")&&!aline.toLowerCase().endsWith(" //banned")&&!aline.toLowerCase().endsWith(" //onplayerjoin")
                            &&!aline.toLowerCase().endsWith(" //onplayerdeath")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), aline);
                    }
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 1L);
    }
}