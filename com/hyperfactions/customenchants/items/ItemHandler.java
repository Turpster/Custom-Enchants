package com.hyperfactions.customenchants.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
		if (item.getItemMeta() != null)
		{
			if (item.getItemMeta().getLore() != null)
			{
				List<String> lores = item.getItemMeta().getLore();
				for (String lore : lores)
				{
					System.out.println("testing before");

					if (lore.startsWith("ID: "))
					{
						System.out.println("testing after");
						int ID;
						try
						{
							ID = Integer.parseInt(lore.substring(6));
						}
						catch (IndexOutOfBoundsException e)
						{
							ID = 0;
						}
						if (ID != 0)
						{
							if (config.contains("items." + ID + ".type"))
							{
								String itemType = config.getString("items." + ID + ".type");
								
								if (itemType.equals(Item.ItemType.SUCCESS_RATE_DUST.toString()))
								{
									
									return new Item(Item.ItemType.SUCCESS_RATE_DUST);
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	Random r = new Random();
	public ItemStack getNewItem(Item.ItemType itemType)
	{
		int randomint = r.nextInt();
		if (itemType == Item.ItemType.SUCCESS_RATE_DUST)
		{
			config.set("items." + randomint + ".type", Item.ItemType.SUCCESS_RATE_DUST.toString());
			config.saveConfig();
			ItemStack glowstone = new ItemStack(Material.GLOWSTONE_DUST, 1);
			ItemMeta itemMeta = glowstone.getItemMeta();

			List<String> lore = new ArrayList<String>();
			lore.add("ID: " + ChatColor.GRAY + randomint);

			itemMeta.setLore(lore);
			glowstone.setItemMeta(itemMeta);
			return glowstone;
		}
		return null;
	}

	@EventHandler
	public void onItemCraft(CraftItemEvent e)
	{ 
		ItemStack[] items = e.getClickedInventory().getContents();

		outerloop:
			for (ItemStack item : items)
			{
				System.out.println("testing before event");
				if (this.getItem(item) != null)
				{
					System.out.println("testing after event");

					Item customitem = this.getItem(item);
					if (customitem.type == Item.ItemType.SUCCESS_RATE_DUST)
					{
						e.setCancelled(true);
						for (HumanEntity humanEntity : e.getViewers())
						{
							humanEntity.sendMessage(ChatColor.GREEN + "Craft> " + ChatColor.RESET + "You cannot use " + customitem.type.toString() + " for crafting.");
							break outerloop;
						}
					}
				}


			}
	}
}
