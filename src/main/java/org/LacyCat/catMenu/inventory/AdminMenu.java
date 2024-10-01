package org.LacyCat.catMenu.inventory;

import org.LacyCat.catMenu.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AdminMenu {

    private final Inventory inv;

    public AdminMenu() {
        this.inv = Bukkit.createInventory(null, 27, "AdminMenu");

        // 아이템을 추가하는 로직을 여기 추가할 수 있습니다.
        inv.setItem(1,ItemManager.Revive);
        inv.setItem(2,ItemManager.Information);
        inv.setItem(26,ItemManager.Exit);
        // inv.setItem(slot, item); 형식으로 아이템을 추가할 수 있습니다.
    }

    public void open(Player player) {
        player.openInventory(inv);
    }
}