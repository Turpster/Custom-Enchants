package com.hyperfactions.customenchants.items.enchants;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public abstract class EnchantmentHandler implements Listener
{
	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent e)
	{
		Player player, target;
		
		if (e.getEntity() instanceof Player) 
		{
			target = (Player) e.getEntity();
		}
		if (e.getDamager() instanceof Projectile)
		{
			player = (Player) ((Projectile) e.getDamager()).getShooter();
			Projectile projectile = (Projectile) e.getDamager();
			
			if (projectile.getType() == EntityType.SPLASH_POTION)
			{
				
			}
		}
		else if (e.getDamager() instanceof Player)
		{
			player = (Player) e.getDamager();
		}
	}
	
	@EventHandler
	public void onHurtEvent(EntityDamageEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			double playerhealth = player.getHealth() - e.getFinalDamage();
			
			for (int x = 0; x < player.getInventory().getSize(); x++)
			{
				itemHandler.hasEnchant(player.getInventory().getItem(x));
			}
		}
	}
}
