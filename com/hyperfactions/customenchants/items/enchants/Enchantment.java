package com.hyperfactions.customenchants.items.enchants;

public class Enchantment
{
	public enum EnchantType
	{
		HEAL_RIFT,
		KABOOM,
		HOUND_DOG;

		public int getMaxLevel()
		{
			switch(this)
			{
			case HEAL_RIFT:
				return 5;
			case KABOOM:
				return 2;
			case HOUND_DOG:
				return 5;
			default:
				return 0;
			}
		}
		
		public int getMaxCombinedLevel()
		{
			switch(this)
			{
			case HEAL_RIFT:
				return 15;
			case KABOOM:
				return 8;
			case HOUND_DOG:
				return 15;
			default:
				return 0;
			}
		}
		
		@Override
		public String toString()
		{
			switch(this)
			{
			case HEAL_RIFT:
				return "Heal Rift";
			case KABOOM:
				return "Kaboom";
			case HOUND_DOG:
				return "Hound Dog";
			default:
				return null;
			}
		}

		public String getDescription()
		{
			switch(this)
			{
			case HEAL_RIFT:
				return "Gives regen when they are low on health.";
			case KABOOM:
				return "Has a chance to explode the target on hit.";
			case HOUND_DOG:
				return "Has a chance to spawn wolves to fight by your side.";
			default:
				return null;
			}
		}
	}
	int level;

	private EnchantType enchantType;
	public EnchantType getType()
	{
		return enchantType;
	}

	public Enchantment(EnchantType enchantType)
	{
		this.enchantType = enchantType;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}
}
