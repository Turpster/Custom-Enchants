package com.hyperfactions.customenchants;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hyperfactions.customenchants.config.Config;
import com.hyperfactions.customenchants.config.ConfigManager;
import com.hyperfactions.customenchants.gui.Enchantments;
import com.hyperfactions.customenchants.items.ItemHandler;
import com.hyperfactions.customenchants.items.enchants.EnchantmentHandler;

public class CustomEnchants extends JavaPlugin
{
	private ConfigManager configManager;
	private EnchantmentHandler enchantmentHandler;
	private ItemHandler itemHandler;
	private Commands commands;
	Enchantments enchantmentsGUI;
	
	Config database;
	
	public ConfigManager getConfigManager()
	{
		return configManager;
		
		
	}
	
	@Override
	public void onEnable() 
	{
		Bukkit.getLogger().log(Level.INFO, "V" + this.getDescription().getVersion() + " is being enabled.");
		
		
		configManager = new ConfigManager(this);
		database = configManager.getNewConfig("database.yml");
		enchantmentHandler = new EnchantmentHandler(this, database);
		itemHandler = new ItemHandler(database);
		enchantmentsGUI = new Enchantments();
		commands = new Commands(itemHandler, enchantmentHandler, enchantmentsGUI);
		
		this.getCommand("ce").setExecutor(commands);
		this.getCommand("enchant").setExecutor(commands);
		this.getCommand("getitem").setExecutor(commands);
		
		
		
		Bukkit.getPluginManager().registerEvents(enchantmentHandler, this);
		Bukkit.getPluginManager().registerEvents(enchantmentsGUI, this);
		Bukkit.getPluginManager().registerEvents(itemHandler, this);
		
	}
	
	@Override
	public void onDisable() 
	{
		Bukkit.getLogger().log(Level.INFO, "V" + this.getDescription().getVersion() + " is being disabled.");
	}
}