package org.LacyCat.catMenu.events;

import org.LacyCat.catMenu.inventory.SpectatorMenu;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // 플레이어가 관전 모드인지 확인
        if (player.getGameMode() == GameMode.SPECTATOR) {
            // 플레이어가 완전히 서버에 접속한 후에 인벤토리를 여는 코드
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("CatMenu"), () -> {
                SpectatorMenu menu = new SpectatorMenu();
                menu.open(player); // 관전자 전용 메뉴 열기
            }, 20L); // 20 ticks (1초) 후에 메뉴를 열도록 지연시킴
        }
    }
}