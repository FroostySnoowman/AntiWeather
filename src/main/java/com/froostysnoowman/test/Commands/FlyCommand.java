package com.froostysnoowman.test.Commands;

import com.froostysnoowman.test.Main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class FlyCommand implements TabExecutor {
    private Main main;

    public FlyCommand(Main main) {
        this.main = main;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix_message = main.getConfig().getString("prefix-message");
        if (sender instanceof Player) {
            if (sender.hasPermission("aw.fly")) {
                Player player = (Player) sender;
                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (sender.hasPermission("aw.fly.other")) {
                        if (target != null) {
                            if (target.getAllowFlight()) {
                                target.setAllowFlight(false);
                                String fly_disabled_other_message = main.getConfig().getString("fly-disabled-other-message");
                                String fly_disabled_message = main.getConfig().getString("fly-disabled-message");
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + fly_disabled_other_message).replaceAll("%target%", target.getName()));
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + fly_disabled_message));
                            } else {
                                target.setAllowFlight(true);
                                String fly_enabled_other_message = main.getConfig().getString("fly-enabled-other-message");
                                String fly_enabled_message = main.getConfig().getString("fly-enabled-message");
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + fly_enabled_other_message).replaceAll("%target%", target.getName()));
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + fly_enabled_message));
                            }
                        } else {
                            String invalid_player_message = main.getConfig().getString("invalid-player-message");
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + invalid_player_message));
                        }
                    } else {
                        String no_permission_message = main.getConfig().getString("no-permission-message");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + no_permission_message).replaceAll("%permission%", "aw.fly.other"));
                    }
                } catch (Exception e) {
                    if (player.getAllowFlight()) {
                        player.setAllowFlight(false);
                        String fly_disabled_message = main.getConfig().getString("fly-disabled-message");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + fly_disabled_message));
                    } else {
                        player.setAllowFlight(true);
                        String fly_enabled_message = main.getConfig().getString("fly-enabled-message");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + fly_enabled_message));
                    }
                }
            } else {
                String no_permission_message = main.getConfig().getString("no-permission-message");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + no_permission_message).replaceAll("%permission%", "aw.fly"));
            }
        } else {
            String non_player_message = main.getConfig().getString("non-player-message");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_message + ' ' + non_player_message));
        }
        return true;
    }
}
