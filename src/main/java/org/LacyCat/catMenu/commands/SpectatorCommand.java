package org.LacyCat.catMenu.commands;

import org.LacyCat.catMenu.inventory.SpectatorMenu;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectatorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // 플레이어가 관전 모드인지 확인
            if (player.getGameMode() == GameMode.SPECTATOR) {
                SpectatorMenu menu = new SpectatorMenu();
                menu.open(player); // 관전자 전용 메뉴 열기
            } else {
                player.sendMessage("이 명령어는 관전 모드에서만 사용할 수 있습니다.");
            }
        } else {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
        }
        return true;
    }
}