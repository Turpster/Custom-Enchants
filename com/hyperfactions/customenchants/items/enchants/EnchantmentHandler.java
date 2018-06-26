package com.hyperfactions.customenchants.items.enchants;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hyperfactions.customenchants.items.ItemHandler;

public abstract class EnchantmentHandler implements Listener
{
	private ItemHandler itemHandler;

	public EnchantmentHandler(ItemHandler itemHandler)
	{
		this.itemHandler = itemHandler;
	}

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
				/*
				 * TODO
				 * Add Splash Potions
				 */
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

			ItemStack[] armour = player.getInventory().getArmorContents();

			int healRiftTotalLevel = 0;

			for (ItemStack item : armour)
			{
				if (itemHandler.getEnchants(item) != null)
				{
					List<Enchantment> enchantments = itemHandler.getEnchants(item);
					for (Enchantment enchant : enchantments)
					{
						if (enchant.getType() == Enchantment.EnchantType.HEAL_RIFT)
						{
							healRiftTotalLevel += enchant.level;
						}
					}
				}
			}
			if (healRiftTotalLevel > 0)
			{
				if (healRiftTotalLevel > Enchantment.EnchantType.HEAL_RIFT.getMaxLevel())
				{
					healRiftTotalLevel = Enchantment.EnchantType.HEAL_RIFT.getMaxLevel();
				}
				
				if (playerhealth <= Math.round(0.6f * healRiftTotalLevel))
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4 * healRiftTotalLevel, Math.round(0.6f * healRiftTotalLevel)));
				}
			}
		}
	}
}
