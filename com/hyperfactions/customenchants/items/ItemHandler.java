package com.hyperfactions.customenchants.items;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.hyperfactions.customenchants.config.Config;
import com.hyperfactions.customenchants.items.enchants.Enchantment;
import com.hyperfactions.customenchants.items.enchants.Enchantment.EnchantType;

public class ItemHandler 
{
	private Config config;

	public ItemHandler(Config config)
	{
		this.config = config;
	}

	public List<Enchantment> getEnchants(ItemStack itemStack)
	{
		List<String> lores = itemStack.getItemMeta().getLore();
		
		List<Enchantment> enchantments = new ArrayList<Enchantment>();
		
		for (String lore : lores)
		{
			if (lore.startsWith("ID: "))
			{
				int ID = Integer.parseInt(lore.substring(6));

				if (config.getList("items." + ID + ".enchants") != null)
				{
					@SuppressWarnings("unchecked")
					List<String> itemEnchants = (List<String>) config.getList("items." + ID + ".enchants");
					
					for (String enchantment : itemEnchants)
					{
						if (enchantment == Enchantment.EnchantType.HEAL_RIFT.toString())
						{
							Enchantment enchant = new Enchantment(EnchantType.HEAL_RIFT);
							enchant.setLevel(config.getInt("items." + ID + ".enchants." + enchantment + ".level"));
							enchantments.add(enchant);
							
						}
						if (enchantment == Enchantment.EnchantType.HOUND_DOG.toString())
						{
							Enchantment enchant = new Enchantment(EnchantType.HOUND_DOG);
							enchant.setLevel(config.getInt("items." + ID + ".enchants." + enchantment + ".level"));
							enchantments.add(enchant);
						}
						if (enchantment == Enchantment.EnchantType.KABOOM.toString())
						{
							Enchantment enchant = new Enchantment(EnchantType.KABOOM);
							
							enchant.setLevel(config.getInt("items." + ID + ".enchants." + enchantment + ".level"));
							enchantments.add(enchant);
						}
					}
				}
			}
		}
		
		if (enchantments.size() != 0)
		{
			return enchantments;
		}
		else return null;
	}

	public UUID getOwner()
	{
		return null;	
	}
}
