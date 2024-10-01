package org.LacyCat.catMenu;

import org.LacyCat.catMenu.commands.MainMenuCommand;
import org.LacyCat.catMenu.events.PlayerDeathListener;
import org.LacyCat.catMenu.events.PlayerJoinListener;
import org.LacyCat.catMenu.events.SpectatorMenuEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CatMenu extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CatMenu 플러그인이 활성화되었습니다!");

        // 이벤트 리스너 등록
        getServer().getPluginManager().registerEvents(
                new PlayerJoinListener(), this
        );
        getServer().getPluginManager().registerEvents(
                new PlayerDeathListener(),this
        );
        getServer().getPluginManager().registerEvents(
                new SpectatorMenuEvent(),this
        );
    }

    @Override
    public void onDisable() {
        getLogger().info(
                "CatMenu 플러그인이 비활성화되었습니다!"
        );
    }
}