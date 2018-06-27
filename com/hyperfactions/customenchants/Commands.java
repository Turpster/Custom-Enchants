package com.hyperfactions.customenchants;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.hyperfactions.customenchants.gui.Enchantments;
import com.hyperfactions.customenchants.items.Item;
import com.hyperfactions.customenchants.items.ItemHandler;
import com.hyperfactions.customenchants.items.enchants.EnchantmentHandler;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor
{
	private Enchantments enchantments;
//	private EnchantmentHandler enchantmentHandler;
	private ItemHandler itemHandler;

	public Commands(ItemHandler itemHandler, EnchantmentHandler enchantmentHandler, Enchantments enchantments)
	{
		this.enchantments = enchantments;
//		this.enchantmentHandler = enchantmentHandler;
		this.itemHandler = itemHandler;
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
		else if (label.equalsIgnoreCase("enchant"))
		{
			if (sender instanceof Player)
			{
				if (args.length == 1)
				{
					if (args[0].equalsIgnoreCase("healrift"))
					{

					}
					else if (args[0].equalsIgnoreCase("kaboom"))
					{

					}
					else if (args[0].equalsIgnoreCase("hounddog"))
					{

					}
				}
			}
		}
		else if (label.equalsIgnoreCase("getitem"))
		{
			if (sender instanceof Player)
			{
				if (args.length == 1)
				{
					Player player = (Player) sender;
					if (args[0].equalsIgnoreCase("successratedust"))
					{
						ItemStack successratedust = this.itemHandler.getNewItem(Item.ItemType.SUCCESS_RATE_DUST);
						player.getInventory().addItem(successratedust);
					}
				}
			}
		}
		return false;
	}
}
