package com.dmonsters.main;

import com.dmonsters.items.BabyEye;
import com.dmonsters.items.Dagon;
import com.dmonsters.items.EntrailFlesh;
import com.dmonsters.items.FlyingDagon;
import com.dmonsters.items.LuckyEgg;
import com.dmonsters.items.MobSpawnerItem;
import com.dmonsters.items.ModItem;
import com.dmonsters.items.PurgePill;
import com.dmonsters.items.Rebar;
import com.dmonsters.items.WidemanSpine;
import com.dmonsters.items.WomanHeart;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
    public static Rebar rebar;
    public static LuckyEgg luckyEgg;
    public static BabyEye babyEye;
    public static WomanHeart womanHeart;
    public static WidemanSpine widemanSpine;
    public static EntrailFlesh entrailFlesh;
    public static PurgePill purgePill;
    public static Dagon dagon;
    public static FlyingDagon flyingDagon;
    public static ModItem modItem;
    public static MobSpawnerItem mobSpawnerItem_baby;
    public static MobSpawnerItem mobSpawnerItem_climber;
    public static MobSpawnerItem mobSpawnerItem_entrail;
    public static MobSpawnerItem mobSpawnerItem_freezer;
    public static MobSpawnerItem mobSpawnerItem_mutantSteve;
    public static MobSpawnerItem mobSpawnerItem_wideman;
    public static MobSpawnerItem mobSpawnerItem_woman;
    public static MobSpawnerItem mobSpawnerItem_zombieChicken;
    public static MobSpawnerItem mobSpawnerItem_present;
    public static MobSpawnerItem mobSpawnerItem_stranger;
    
	@Mod.EventBusSubscriber(modid = MainMod.MODID)
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			init();
			registry.register(rebar.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(luckyEgg.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(babyEye.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(womanHeart.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(widemanSpine.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(entrailFlesh.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(purgePill.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(dagon.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(flyingDagon.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(modItem.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_baby.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_climber.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_entrail.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_freezer.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_mutantSteve.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_wideman.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_woman.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_zombieChicken.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_present.setCreativeTab(MainMod.MOD_CREATIVETAB));
			registry.register(mobSpawnerItem_stranger.setCreativeTab(MainMod.MOD_CREATIVETAB));
		}
	}

    public static void init() {
    	rebar = new Rebar();
    	luckyEgg = new LuckyEgg();
    	babyEye = new BabyEye();
    	womanHeart = new WomanHeart();
    	widemanSpine = new WidemanSpine();
    	entrailFlesh = new EntrailFlesh();
    	purgePill = new PurgePill();
    	dagon = new Dagon();
    	flyingDagon = new FlyingDagon();
    	modItem = new ModItem();
    	mobSpawnerItem_baby = new MobSpawnerItem("baby");
    	mobSpawnerItem_climber = new MobSpawnerItem("climber");
    	mobSpawnerItem_entrail = new MobSpawnerItem("entrail");
    	mobSpawnerItem_freezer = new MobSpawnerItem("freezer");
    	mobSpawnerItem_mutantSteve = new MobSpawnerItem("mutantSteve");
    	mobSpawnerItem_wideman = new MobSpawnerItem("wideman");
    	mobSpawnerItem_woman = new MobSpawnerItem("woman");
    	mobSpawnerItem_zombieChicken = new MobSpawnerItem("zombieChicken");
    	mobSpawnerItem_present = new MobSpawnerItem("present");
    	mobSpawnerItem_stranger = new MobSpawnerItem("stranger");
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	init();
    	luckyEgg.initModel();
    	babyEye.initModel();
    	womanHeart.initModel();
    	widemanSpine.initModel();
    	entrailFlesh.initModel();
    	purgePill.initModel();
    	dagon.initModel();
    	flyingDagon.initModel();
    	modItem.initModel();
    	mobSpawnerItem_baby.initModel();
    	mobSpawnerItem_climber.initModel();
    	mobSpawnerItem_entrail.initModel();
    	mobSpawnerItem_freezer.initModel();
    	mobSpawnerItem_mutantSteve.initModel();
    	mobSpawnerItem_wideman.initModel();
    	mobSpawnerItem_woman.initModel();
    	mobSpawnerItem_zombieChicken.initModel();
    	mobSpawnerItem_present.initModel();
    	mobSpawnerItem_stranger.initModel();
    }
}