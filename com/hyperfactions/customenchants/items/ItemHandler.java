package com.hyperfactions.customenchants.items;

import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.hyperfactions.customenchants.config.Config;

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
}
