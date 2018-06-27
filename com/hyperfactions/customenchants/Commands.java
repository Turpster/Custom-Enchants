package com.hyperfactions.customenchants;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hyperfactions.customenchants.config.ConfigManager;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor
{
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (label.equalsIgnoreCase("ce"))
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;
				
				/*
				 * TODO
				 * Open GUI
				 * - List of enchantments 
				 *   - The effect of the enchantments
				 *   - A description of the enchantments
				 *     - An effect on the armour and sword
				 */
			}
			else 
			{
				sender.sendMessage(ChatColor.GREEN + "CE> " + ChatColor.RESET + "Only players can use this command.");
			}
		}
		return false;
	}
}
