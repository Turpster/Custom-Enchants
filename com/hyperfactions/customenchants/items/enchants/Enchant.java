package com.hyperfactions.customenchants.items.enchants;

public enum Enchant 
{
	HEAL_RIFT,
	KABOOM,
	HOUND_DOG;
	
	int level;
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public String getName()
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
