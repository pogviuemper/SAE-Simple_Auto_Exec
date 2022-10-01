package me.sjts98k.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OnPlayerDeath implements Listener {
    @EventHandler
    public void onpd(PlayerDeathEvent event){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("SAE_AutoExec.txt"));
            String aline;
            while ((aline = reader.readLine()) != null){
                if (!aline.startsWith("// ")&&aline.toLowerCase().endsWith(" //onplayerdeath")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), aline.replace(" //onplayerdeath", ""));
                }}
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);}
    }
}
