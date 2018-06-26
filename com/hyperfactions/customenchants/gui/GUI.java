package com.hyperfactions.customenchants.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class GUI implements Listener
{
	private Inventory inventory;
	
	public GUI(GUIHandler handler, Inventory inventory)
	{
		this.inventory = inventory;
		handler.GUIs.add(this);
	}
	
	public void openInventory(Player player)
	{
		player.openInventory(inventory);
	}
	
	@EventHandler
	public abstract void inventoryClick(InventoryClickEvent e);
	
	public Inventory getInventory()
	{
		return inventory;
	}
}
