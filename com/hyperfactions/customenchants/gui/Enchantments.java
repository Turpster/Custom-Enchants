package com.hyperfactions.customenchants.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Enchantments extends GUI 
{
	public Enchantments(GUIHandler handler) 
	{
		super(handler, Bukkit.createInventory(null, 53));
		this.setupInventory();
	}
	
	private void setupInventory()
	{
		/*
		 * TODO
		 * Setup Enchantments
		 */
	}
	
	@Override
	public void inventoryClick(InventoryClickEvent e) {
		// TODO Auto-generated method stub

	}

}
