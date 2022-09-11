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

import net.lukeiscoding.spigot.simplerules.util.PermissionNodes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandSimpleRulesAdmin implements CommandExecutor {

    private static final Inventory gui = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.translateAlternateColorCodes('&', "&d&l&nSimpleRules Admin"));

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "You need to be a player to use this command!");
            return true;
        }

        final ItemStack stack = new ItemStack(Material.REDSTONE_BLOCK);
        final ItemMeta meta = stack.getItemMeta();
        final List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Reloads the config.yml file.");
        meta.setDisplayName(ChatColor.RED + "Reload the config.");
        meta.setLore(Collections.singletonList(lore.get(0)));
        stack.setItemMeta(meta);
        gui.addItem(stack);

        if (p.hasPermission(PermissionNodes.ADMIN.getPermissionNode())) {
            if (cmd.getName().equalsIgnoreCase("simplerulesadmin")) {
                p.openInventory(gui);
            }
        }

        return false;
    }

    public Inventory getGui() {
        return gui;
    }
}
