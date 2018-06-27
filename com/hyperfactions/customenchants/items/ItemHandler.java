package com.hyperfactions.customenchants.items;

import java.util.List;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import com.hyperfactions.customenchants.config.Config;

import net.md_5.bungee.api.ChatColor;

public class ItemHandler implements Listener
{	
	private Config config;

	public ItemHandler(Config config)
	{
		this.config = config;
	}

	public Item getItem(ItemStack item)
	{
		List<String> lores = item.getItemMeta().getLore();

		for (String lore : lores)
		{
			if (lore.startsWith("ID: "))
			{
				int ID;
				try
				{
					ID = Integer.parseInt(lore.substring(6));
				}
				catch (IndexOutOfBoundsException e)
				{
					continue; /* MIGHT BE AN INFINITE LOOP OR PROBLEM */
				}
				if (config.contains("items." + ID + ".type"))
				{
					String itemType = config.getString("items." + ID + ".type");

					if (itemType == Item.ItemType.SUCCESS_RATE_DUST.toString())
					{
						return new Item(Item.ItemType.SUCCESS_RATE_DUST);
					}
				}
			}
		}
		return null;
	}
	
	@EventHandler
	public void onItemCraft(CraftItemEvent e)
	{ 
		ItemStack[] items = e.getClickedInventory().getContents();
		
		for (ItemStack item : items)
		{
			if (this.getItem(item) != null)
			{
				Item customitem = this.getItem(item);
				if (customitem.type == Item.ItemType.SUCCESS_RATE_DUST)
				{
					e.setCancelled(true);
					for (HumanEntity humanEntity : e.getViewers())
					{
						humanEntity.sendMessage(ChatColor.GREEN + "Craft> " + ChatColor.RESET + "You cannot use " + customitem.type.toString() + " for crafting.");
					}
				}
			}
		}
	}
}
