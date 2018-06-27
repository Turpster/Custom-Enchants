package com.hyperfactions.customenchants.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public abstract class GUI implements Listener
{
	public Inventory inventory;
	
	public GUI(Inventory inventory)
	{
		this.inventory = inventory;
	}
	
	public void openInventory(Player player)
	{
		player.openInventory(inventory);
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
}
