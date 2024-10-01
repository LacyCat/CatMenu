package org.LacyCat.catMenu.commands;

import org.LacyCat.catMenu.inventory.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             String[] args
    ) {
        if (sender instanceof Player
        ) {
            Player player = (Player) sender;

            // 플레이어가 관전 모드인지 확인
            MainMenu menu = new MainMenu();
            menu.open(
                    player
            ); // 관전자 전용 메뉴 열기
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(
                    "이 명령어는 플레이어만 사용할 수 있습니다."
            );
            return false;
        }
        return true;
    }
}
