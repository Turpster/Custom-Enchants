package com.hyperfactions.customenchants;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hyperfactions.customenchants.config.ConfigManager;

public class CustomEnchants extends JavaPlugin
{
	private ConfigManager configManager;
	
	public ConfigManager getConfigManager()
	{
		return configManager;
	}
	
	@Override
	public void onEnable() 
	{
		Bukkit.getLogger().log(Level.INFO, "V" + this.getDescription().getVersion() + " is being enabled.");
		
		configManager = new ConfigManager(this);
	}
	
	@Override
	public void onDisable() 
	{
		Bukkit.getLogger().log(Level.INFO, "V" + this.getDescription().getVersion() + " is being disabled.");
	}
}