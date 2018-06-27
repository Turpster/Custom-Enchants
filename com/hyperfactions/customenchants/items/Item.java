package com.hyperfactions.customenchants.items;

import java.util.UUID;

public class Item
{
	ItemType type;
	public Item(ItemType type)
	{
		this.type = type;
	}
	
	public enum ItemType
	{
		SUCCESS_RATE_DUST;

		@Override
		public String toString()
		{
			switch(this)
			{
			case SUCCESS_RATE_DUST:
				return "Success Rate Dust";
			default:
				return null;
			}
		}
	}
	
	public UUID owner;
}

