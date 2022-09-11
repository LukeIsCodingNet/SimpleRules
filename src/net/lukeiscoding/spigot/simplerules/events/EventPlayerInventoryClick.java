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
import net.lukeiscoding.spigot.simplerules.commands.CommandSimpleRulesAdmin;
import net.lukeiscoding.spigot.simplerules.util.PermissionNodes;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerInventoryClick implements Listener {

    private static final CommandSimpleRulesAdmin admin = new CommandSimpleRulesAdmin();
    private static final SimpleRules plugin = SimpleRules.getPlugin(SimpleRules.class);

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        final ItemStack[] stack = admin.getGui().getContents();

        if (p.hasPermission(PermissionNodes.ADMIN.getPermissionNode())) {
            if (event.getInventory() == admin.getGui()) {
                if (event.isLeftClick()) {
                    for (int i = 0; i < stack.length; i++) {
                        if (i == 0) {
                            plugin.reloadConfig();
                            event.getInventory().clear();
                            p.closeInventory();
                            p.sendMessage(ChatColor.GREEN + "SimpleRules has been reloaded!");
                        }
                    }
                }
            }
        }
    }
}
