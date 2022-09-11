package net.lukeiscoding.spigot.simplerules.commands;
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
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandRules implements CommandExecutor {

    private static final SimpleRules plugin = SimpleRules.getPlugin(SimpleRules.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "You need to be a player to use this command!");
            return true;
        }

        if (p.hasPermission(PermissionNodes.USE_RULES.getPermissionNode())) {
            if (cmd.getName().equalsIgnoreCase("rules")) {
                final List<String> rulesList = plugin.getConfig().getStringList("server-rules");
                for (String s : rulesList) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                }
            }
        }

        return false;
    }
}
