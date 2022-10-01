package me.sjts98k.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OnPlayerJoin implements Listener {
    @EventHandler
    public void onpj(PlayerJoinEvent event){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("SAE_AutoExec.txt"));
            String aline;
            while ((aline = reader.readLine()) != null){
                if (!aline.startsWith("// ")&&aline.toLowerCase().endsWith(" //onplayerjoin")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), aline.replace(" //onplayerjoin", ""));
                }}
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);}
    }
}
