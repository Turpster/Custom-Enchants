package com.hyperfactions.customenchants;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEnchants extends JavaPlugin
{
	@Override
	public void onEnable() 
	{
		Bukkit.getLogger().log(Level.INFO, "V" + this.getDescription().getVersion() + " is being enabled.");
	}
	
	@Override
	public void onDisable() 
	{
		Bukkit.getLogger().log(Level.INFO, "V" + this.getDescription().getVersion() + " is being disabled.");
	}
}