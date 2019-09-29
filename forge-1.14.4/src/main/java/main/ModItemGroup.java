package main;

import init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup extends ItemGroup
{
	public ModItemGroup()
	{
		super("deadlymonsters");
		this.setBackgroundImageName("mod.png");
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack(ModItems.mod_item);
	}
}