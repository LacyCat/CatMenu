package org.LacyCat.catMenu.events;

import org.LacyCat.catMenu.inventory.SpectatorMenu;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("CatMenu"), () -> {
            // 사망 후 관전 모드로 전환
            player.setGameMode(GameMode.SPECTATOR);

            // SpectatorMenu 열기
            SpectatorMenu menu = new SpectatorMenu();
            menu.open(player);
        }, 20L); // 약간의 딜레이 후 실행 (1초 후)
    }
}