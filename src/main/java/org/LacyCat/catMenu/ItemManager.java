package org.LacyCat.catMenu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

import java.util.Arrays;

public class  ItemManager {

    private static ItemStack buildItem(Material type, int amount, String displayName, boolean setHIDE, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setLore(Arrays.asList(lore));

            if (setHIDE) {
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            }

            stack.setItemMeta(meta);
        } else {
            System.out.println("Failed to create ItemMeta for " + type);
        }

        return stack;
    }

    public static final ItemStack Revive = buildItem(Material.TOTEM_OF_UNDYING, 1, ChatColor.GREEN + "부활", false, ChatColor.GREEN + "24시간에 한번 부활 가능");
}