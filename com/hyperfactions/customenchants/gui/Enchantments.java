package com.hyperfactions.customenchants.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Enchantments extends GUI 
{
	public Enchantments() 
	{
		super(Bukkit.createInventory(null, 54));
		this.setupInventory();
	}
	
	private void setupInventory()
	{
		super.inventory = Bukkit.createInventory(null, 54, "Custom Enchantments");
		
		ItemStack healrift = new ItemStack(Material.POTION, 1, (short) 16471);
		super.inventory.setItem(10, healrift);
		
		ItemStack kaboom = new ItemStack(Material.TNT);
		super.inventory.setItem(11, kaboom);
		
		ItemStack hounddog = new ItemStack(Material.MONSTER_EGG, 1, (short) 95);
		super.inventory.setItem(12, hounddog);
		
	}
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e)
	{
		if (e.getClickedInventory().getName().equals("Custom Enchantments"))
		{
			e.setCancelled(true);
		}
	}
}
