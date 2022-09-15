package net.lukeiscoding.spigot.simplerules;
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

import net.lukeiscoding.spigot.simplerules.commands.CommandRules;
import net.lukeiscoding.spigot.simplerules.commands.CommandRulesAccept;
import net.lukeiscoding.spigot.simplerules.commands.CommandRulesDeny;
import net.lukeiscoding.spigot.simplerules.commands.CommandSimpleRulesAdmin;
import net.lukeiscoding.spigot.simplerules.events.EventPlayerInventoryClick;
import net.lukeiscoding.spigot.simplerules.events.EventPlayerJoin;
import net.lukeiscoding.spigot.simplerules.util.PermissionNodes;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleRules extends JavaPlugin {

    private static final Permission acceptPermission = new Permission(PermissionNodes.RULES_ACCEPT.getPermissionNode());
    private static final Permission denyPermission = new Permission(PermissionNodes.RULES_DENY.getPermissionNode());
    private static final Permission usePermission = new Permission(PermissionNodes.USE_RULES.getPermissionNode());
    private static final Permission messageBypassPermission = new Permission(PermissionNodes.RULES_WELCOME_MESSAGE_BYPASS.getPermissionNode());

    public static final Logger LOGGER = Logger.getLogger("SimpleRules");

    @Override
    public void onEnable() {
        LOGGER.severe("SimpleRules has been enabled!");
        this.saveDefaultConfig();
        Bukkit.getServer().getPluginManager().addPermission(acceptPermission);
        Bukkit.getServer().getPluginManager().addPermission(denyPermission);
        Bukkit.getServer().getPluginManager().addPermission(usePermission);
        Bukkit.getServer().getPluginManager().addPermission(messageBypassPermission);

        registerCommands();
        registerEvents();
    }

    private void registerCommands() {
        this.getCommand("rules").setExecutor(new CommandRules());
        this.getCommand("rulesaccept").setExecutor(new CommandRulesAccept());
        this.getCommand("rulesdeny").setExecutor(new CommandRulesDeny());
        this.getCommand("simplerulesadmin").setExecutor(new CommandSimpleRulesAdmin());

    }

    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new EventPlayerJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EventPlayerInventoryClick(), this);
    }
}
