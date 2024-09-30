package org.LacyCat.catMenu.events;

import org.LacyCat.catMenu.ItemManager;
import org.LacyCat.catMenu.inventory.SpectatorMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class SpectatorMenuEvent implements Listener {

    // 플레이어의 마지막 부활 시간을 저장하는 HashMap
    private final HashMap<UUID, Long> lastRespawnTimes = new HashMap<>();

    // 24시간 (하루) 제한 시간 (밀리초)
    private final long DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.stripColor("SpectatorMenu"))) {
            event.setCancelled(true); // 인벤토리에서 아이템을 옮기지 못하게 방지

            if (event.getCurrentItem() == null) return;

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            // 플레이어가 부활 아이템을 클릭했는지 확인
            long currentTime = 0;
            if (clickedItem.isSimilar(ItemManager.Revive)) {
                UUID playerUUID = player.getUniqueId();
                currentTime = System.currentTimeMillis();

                // 마지막 부활 시간을 가져옴
                if (lastRespawnTimes.containsKey(playerUUID)) {
                    long lastRespawnTime = lastRespawnTimes.get(playerUUID);
                    long timeSinceLastRespawn = currentTime - lastRespawnTime;

                    // 24시간이 경과했는지 확인
                    if (timeSinceLastRespawn < DAY_IN_MILLIS) {
                        long remainingTime = (DAY_IN_MILLIS - timeSinceLastRespawn) / 1000; // 초 단위로 남은 시간 계산
                        player.sendMessage(ChatColor.RED + "부활하려면 " + remainingTime / 3600 + "시간 " + (remainingTime % 3600) / 60 + "분 남았습니다.");
                        return;
                    }
                }

                // 부활 처리
                player.sendMessage(ChatColor.GREEN + "부활했습니다! 내일 다시 사용할 수 있습니다.");
                lastRespawnTimes.put(playerUUID, currentTime); // 현재 시간을 마지막 부활 시간으로 저장

                // 부활시키는 처리 (예: 생명 증가, 게임 모드 변경 등)
                player.setHealth(player.getMaxHealth()); // 체력을 최대치로 회복
                player.setGameMode(org.bukkit.GameMode.SURVIVAL); // 생존 모드로 변경

                player.closeInventory();// 인벤토리 닫기

            } else if (clickedItem.isSimilar(ItemManager.Information)) {
                player.sendMessage(
                        ChatColor.GREEN + "버전: 1.0v (스냅샷)",
                        ChatColor.LIGHT_PURPLE + "서버: lacycat.kro.kr:25565",
                        ChatColor.RED + "시간: " + System.currentTimeMillis()
                );

            } else if (clickedItem.isSimilar(ItemManager.Exit)) {
                player.closeInventory();
            }
        }
    }
}