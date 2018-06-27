package com.hyperfactions.customenchants;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hyperfactions.customenchants.gui.Enchantments;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor
{
	private Enchantments enchantments;
	
	public Commands(Enchantments enchantments)
	{
		this.enchantments = enchantments;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (label.equalsIgnoreCase("ce"))
		{
			if (sender instanceof Player)
			{
				if (args.length == 0)
				{
					Player player = (Player) sender;
					enchantments.openInventory(player);
				}
				else
				{
					sender.sendMessage(ChatColor.GREEN + "CE> " + ChatColor.RESET + "Invalid Arguments, do " + ChatColor.GREEN + "/ce" + ChatColor.RESET + ".");
				}
			}
			else 
			{
				sender.sendMessage(ChatColor.GREEN + "CE> " + ChatColor.RESET + "Only players can use this command.");
			}
		}
		return false;
	}
}
