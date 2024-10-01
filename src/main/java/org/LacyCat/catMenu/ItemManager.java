package org.LacyCat.catMenu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

import javax.security.auth.callback.CallbackHandler;
import java.util.Arrays;

public class  ItemManager {

    private static ItemStack buildItem(Material type, int amount, String displayName, boolean setHIDE,  int customModelData, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setLore(Arrays.asList(lore));
            if (setHIDE) {
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            }
            meta.setCustomModelData(customModelData);
            stack.setItemMeta(meta);
        } else {
            System.out.println("Failed to create ItemMeta for " + type);
        }

        return stack;
    }
    //customModelData = 0 mean noneCustomModelData
    public static final ItemStack Revive = buildItem(
            Material.TOTEM_OF_UNDYING,
            1,
            ChatColor.GREEN + "부활",
            false,
            0,
            ChatColor.GREEN + "24시간에 한번 부활 가능"
    );
    public static final ItemStack Update = buildItem(
            Material.BARRIER,
            1,
            ChatColor.RED+"업데이트 예정",
            false,
            1,
            ChatColor.DARK_RED+"업데이트 되지 않음"
    );
    public static final ItemStack Information = buildItem(
            Material.KNOWLEDGE_BOOK,
            1,
            ChatColor.BLUE+"버전: 1.0 스냅샷",
            false,
            2,
            ChatColor.AQUA+"클릭하면 정보를 표기합니다"
    );
    public static final ItemStack Outline = buildItem(
            Material.CYAN_STAINED_GLASS_PANE,
            1,
            ChatColor.DARK_RED+"아무 것도 없는 거",
            false,
            0,
            ChatColor.LIGHT_PURPLE+"없어",
            ChatColor.LIGHT_PURPLE+"뭐 더 없어",
            ChatColor.LIGHT_PURPLE+"없다고"
    );
    public static final ItemStack Exit = buildItem(
            Material.REDSTONE_BLOCK,
            1,
            ChatColor.DARK_RED+"나가기",
            false,
            0,
            ChatColor.LIGHT_PURPLE+"뭐 말 그대로 나가기임",
            ChatColor.LIGHT_PURPLE+"뭐 더 없어",
            ChatColor.LIGHT_PURPLE+"..."
    );

    //여기부터 MainPANEL에 들어가는 아이템입니다
    public static final ItemStack TpSpawn = buildItem(
            Material.RED_BED,
            1,
            ChatColor.DARK_GREEN+"스폰포인트 가기",
            false,
            3,
            ChatColor.DARK_GRAY+"지정된 침대나 ",
            ChatColor.DARK_GRAY+"리스폰 정박기로 텔레포트합니다.",
            ChatColor.RED+"만약 스폰포인트가 없다면 [0,0]으로 갑니다"
    );
}