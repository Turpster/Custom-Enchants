package com.hyperfactions.customenchants.items.enchants;

import java.util.List;
import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hyperfactions.customenchants.items.ItemHandler;

import net.minecraft.server.v1_12_R1.EnchantmentSlotType;

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
		}
		else if (e.getDamager().getType() == EntityType.SPLASH_POTION)
		{
			/*
			 * TODO
			 * Add Splash Potions
			 */
		}
		else if (e.getDamager() instanceof Player)
		{
			player = (Player) e.getDamager();
			ItemStack item = player.getInventory().getItemInMainHand();
			if (itemHandler.getEnchants(item) != null)
			{
				List<Enchantment> enchants = itemHandler.getEnchants(item);
				for (Enchantment enchant : enchants)
				{
					int kaboomtotallevel = 0;
					int hounddogtotallevel = 0;
					
					if (enchant.getType() == Enchantment.EnchantType.KABOOM)
					{
						kaboomtotallevel += enchant.level;
					}
					if (enchant.getType() == Enchantment.EnchantType.HOUND_DOG)
					{
						hounddogtotallevel += enchant.level;
					}
					
					if (kaboomtotallevel != 0)
					{
						if (kaboomtotallevel > Enchantment.EnchantType.KABOOM.getMaxCombinedLevel())
						{
							kaboomtotallevel = Enchantment.EnchantType.KABOOM.getMaxCombinedLevel();
						}
						
						Random r = new Random();
						
						int chance = r.nextInt(100000);
						int percentage = 1000 * kaboomtotallevel;
						
						if (percentage >= chance)
						{
							e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), Math.round(0.6f * kaboomtotallevel));
						}
					}
					if (hounddogtotallevel != 0)
					{
						if (hounddogtotallevel > Enchantment.EnchantType.HOUND_DOG.getMaxCombinedLevel())
						{
							hounddogtotallevel = Enchantment.EnchantType.HOUND_DOG.getMaxCombinedLevel();
						}
					}
				}
			}
			
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
				if (healRiftTotalLevel > Enchantment.EnchantType.HEAL_RIFT.getMaxCombinedLevel())
				{
					healRiftTotalLevel = Enchantment.EnchantType.HEAL_RIFT.getMaxCombinedLevel();
				}
				
				if (playerhealth <= Math.round(0.6f * healRiftTotalLevel))
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4 * healRiftTotalLevel, Math.round(0.6f * healRiftTotalLevel)));
				}
			}
		}
	}
}
