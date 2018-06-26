package com.hyperfactions.customenchants.items;

import java.util.List;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.hyperfactions.customenchants.config.Config;
import com.hyperfactions.customenchants.items.enchants.Enchant;

public class ItemHandler 
{
	private Config config;

	public ItemHandler(Config config)
	{
		this.config = config;
	}

	public List<Enchant> getEnchants(ItemStack itemStack)
	{
		List<String> lores = itemStack.getItemMeta().getLore();
		for (String lore : lores)
		{
			if (lore.startsWith("ID: "))
			{
				int ID = Integer.parseInt(lore.substring(6));

				if (config.getList("items." + ID + ".enchants") != null)
				{
					@SuppressWarnings("unchecked")
					List<String> itemEnchants = (List<String>) config.getList( "items." + ID + ".enchants");
					
					for (String enchantments : itemEnchants)
					{
						if (enchantments == Enchant.HEAL_RIFT.getName())
						{
							
						}
					}
				}
			}
		}
	}

	public UUID getOwner()
	{
		return null;	
	}
}
