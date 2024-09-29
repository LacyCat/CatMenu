package org.LacyCat.catMenu.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity(); // 사망한 플레이어 가져오기
        player.setGameMode(GameMode.SPECTATOR); // 관전 모드로 전환
    }
}