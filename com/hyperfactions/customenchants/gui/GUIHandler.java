package com.hyperfactions.customenchants.gui;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;

public class GUIHandler 
{
	ArrayList<GUI> GUIs = new ArrayList<GUI>();
	
	public GUI getGUI(Inventory inventory)
	{
		for (GUI gui : GUIs)
		{
			if (gui.getInventory() == inventory)
			{
				return gui;
			}
		}
		return null;
	}
}
