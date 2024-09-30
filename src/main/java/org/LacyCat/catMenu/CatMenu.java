package org.LacyCat.catMenu;

import org.LacyCat.catMenu.commands.SpectatorCommand;
import org.LacyCat.catMenu.events.PlayerDeathListener;
import org.LacyCat.catMenu.events.PlayerJoinListener;
import org.LacyCat.catMenu.events.SpectatorMenuEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CatMenu extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CatMenu 플러그인이 활성화되었습니다!");

        // 명령어 등록
        if (getCommand("SpectatorCommand") != null) {
            getCommand("SpectatorCommand").setExecutor(new SpectatorCommand());
            getLogger().info("spectatormenu 명령어가 성공적으로 등록되었습니다.");
        } else {
            getLogger().severe("spectatormenu 명령어가 null입니다. plugin.yml 파일을 확인하세요.");
        }

        // 이벤트 리스너 등록
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(),this);
        getServer().getPluginManager().registerEvents(new SpectatorMenuEvent(),this);
    }

    @Override
    public void onDisable() {
        getLogger().info("CatMenu 플러그인이 비활성화되었습니다!");
    }
}