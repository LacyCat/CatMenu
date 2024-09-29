package org.LacyCat.catMenu;

import org.LacyCat.catMenu.commands.SpectatorCommand;
import org.LacyCat.catMenu.events.SpectatorMenuEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CatMenu extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new SpectatorMenuEvent(), this);
        Objects.requireNonNull(getCommand("spectatormenu")).setExecutor(new SpectatorCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
