package com.hyperfactions.customenchants.items.enchants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.hyperfactions.customenchants.config.Config;
import com.hyperfactions.customenchants.items.enchants.Enchantment.EnchantType;

public abstract class EnchantmentHandler implements Listener
{
	private Plugin plugin;
	private Config config;

	public EnchantmentHandler(Plugin plugin, Config config)
	{
		this.plugin = plugin;
		this.config = config;
	}

	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent e)
	{
		Player player;

		if (e.getDamager() instanceof Projectile)
		{
			player = (Player) ((Projectile) e.getDamager()).getShooter();
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
			if (this.getEnchants(item) != null)
			{
				List<Enchantment> enchants = this.getEnchants(item);
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
					Random r = new Random();
					
					if (kaboomtotallevel != 0)
					{
						if (kaboomtotallevel > Enchantment.EnchantType.KABOOM.getMaxCombinedLevel())
						{
							kaboomtotallevel = Enchantment.EnchantType.KABOOM.getMaxCombinedLevel();
						}
						
						
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
						
						int chance = r.nextInt(100000);
						int percentage = 1000 * kaboomtotallevel;
						
						if (percentage >= chance)
						{
							Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
							wolf.setOwner(player);
							wolf.setTarget((LivingEntity) e.getEntity());
							
							new BukkitRunnable()
							{
								@Override
								public void run() 
								{
									wolf.setHealth(0);	
								}
							}.runTaskLater(plugin, 20 * (3 * hounddogtotallevel));
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
				if (this.getEnchants(item) != null)
				{
					List<Enchantment> enchantments = this.getEnchants(item);
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
	
	public List<Enchantment> getEnchants(ItemStack itemStack)
	{
		List<String> lores = itemStack.getItemMeta().getLore();
		
		List<Enchantment> enchantments = new ArrayList<Enchantment>();
		
		for (String lore : lores)
		{
			if (lore.startsWith("ID: "))
			{
				int ID = Integer.parseInt(lore.substring(6));

				if (config.getList("items." + ID + ".enchants") != null)
				{
					@SuppressWarnings("unchecked")
					List<String> itemEnchants = (List<String>) config.getList("items." + ID + ".enchants");
					
					for (String enchantment : itemEnchants)
					{
						if (enchantment == Enchantment.EnchantType.HEAL_RIFT.toString())
						{
							Enchantment enchant = new Enchantment(EnchantType.HEAL_RIFT);
							enchant.setLevel(config.getInt("items." + ID + ".enchants." + enchantment + ".level"));
							enchantments.add(enchant);
							
						}
						if (enchantment == Enchantment.EnchantType.HOUND_DOG.toString())
						{
							Enchantment enchant = new Enchantment(EnchantType.HOUND_DOG);
							enchant.setLevel(config.getInt("items." + ID + ".enchants." + enchantment + ".level"));
							enchantments.add(enchant);
						}
						if (enchantment == Enchantment.EnchantType.KABOOM.toString())
						{
							Enchantment enchant = new Enchantment(EnchantType.KABOOM);
							
							enchant.setLevel(config.getInt("items." + ID + ".enchants." + enchantment + ".level"));
							enchantments.add(enchant);
						}
					}
				}
			}
		}
		
		if (enchantments.size() != 0)
		{
			return enchantments;
		}
		else return null;
	}
}
