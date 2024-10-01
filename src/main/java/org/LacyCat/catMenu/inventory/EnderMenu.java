package org.LacyCat.catMenu.inventory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EnderMenu extends JavaPlugin implements Listener {

    private HashMap<UUID, Inventory> playerInventories = new HashMap<>();
    private Gson gson = new Gson();
    private File dataFolder;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        dataFolder = new File(getDataFolder(), "inventories");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();

        // 왼손에 든 아이템이 "휴대용 엔더상자"인지 확인
        if (offHandItem != null && offHandItem.getType() == Material.ENDER_CHEST) {
            if (offHandItem.getItemMeta() != null && "휴대용 엔더상자".equals(offHandItem.getItemMeta().getDisplayName())) {
                UUID playerId = player.getUniqueId();

                // JSON 파일에서 인벤토리를 불러오기
                if (!playerInventories.containsKey(playerId)) {
                    Inventory personalInventory = loadInventory(player);
                    if (personalInventory == null) {
                        personalInventory = Bukkit.createInventory(player, 27, "Your Personal Chest");
                    }
                    playerInventories.put(playerId, personalInventory);
                }

                // 플레이어에게 개인 인벤토리 열기
                Inventory personalInventory = playerInventories.get(playerId);
                player.openInventory(personalInventory);

                // 스왑 이벤트를 취소하여 오른손으로 넘어가지 않게 함
                event.setCancelled(true);
            }
        }
    }

    // 서버 종료 시 인벤토리를 저장
    @Override
    public void onDisable() {
        for (UUID playerId : playerInventories.keySet()) {
            saveInventory(Bukkit.getPlayer(playerId), playerInventories.get(playerId));
        }
    }

    // 인벤토리를 JSON 파일로 저장
    private void saveInventory(Player player, Inventory inventory) {
        if (player == null) return;

        File file = new File(dataFolder, player.getUniqueId() + ".json");

        Map<Integer, ItemStack> items = new HashMap<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                items.put(i, item);
            }
        }

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JSON 파일에서 인벤토리를 불러오기
    private Inventory loadInventory(Player player) {
        File file = new File(dataFolder, player.getUniqueId() + ".json");
        if (!file.exists()) return null;

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<Integer, ItemStack>>() {}.getType();
            Map<Integer, ItemStack> items = gson.fromJson(reader, type);

            Inventory inventory = Bukkit.createInventory(player, 27, "Your Personal Chest");
            for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
                inventory.setItem(entry.getKey(), entry.getValue());
            }
            return inventory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}