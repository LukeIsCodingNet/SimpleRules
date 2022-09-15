package net.lukeiscoding.spigot.simplerules.events;
/*
        Copyright (C) 2022  Luke Is Coding

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import net.lukeiscoding.spigot.simplerules.SimpleRules;
import net.lukeiscoding.spigot.simplerules.util.PermissionNodes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventPlayerJoin implements Listener {

    private static final SimpleRules plugin = SimpleRules.getPlugin(SimpleRules.class);

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (plugin.getConfig().getBoolean("send-player-a-welcome-message") && !p.hasPermission(PermissionNodes.RULES_WELCOME_MESSAGE_BYPASS.getPermissionNode())) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-welcome-message").replaceAll("%player_name%", p.getDisplayName()).replaceAll("%server_name%", Bukkit.getServer().getName())));
        } else if (plugin.getConfig().getBoolean("send-player-a-welcome-message") || p.hasPermission(PermissionNodes.RULES_ACCEPT.getPermissionNode())) {
            SimpleRules.LOGGER.severe("Did not send the player the welcome message because its ether disabled in the config.yml or the player has already agreed to the rules.");
            return;
        } else if (p.hasPermission(PermissionNodes.RULES_WELCOME_MESSAGE_BYPASS.getPermissionNode())) {
            SimpleRules.LOGGER.severe("Did not send the player the welcome message, already agreed to the rules!");
            return;
        }
    }
}
