package com.dmonsters.main;

import com.dmonsters.items.BabyEye;
import com.dmonsters.items.EntrailFlesh;
import com.dmonsters.items.LuckyEgg;
import com.dmonsters.items.MobSpawnerItem;
import com.dmonsters.items.ModItem;
import com.dmonsters.items.PurgePill;
import com.dmonsters.items.Rebar;
import com.dmonsters.items.WidemanSpine;
import com.dmonsters.items.WomanHeart;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
    public static Rebar rebar;
    public static LuckyEgg luckyEgg;
    public static BabyEye babyEye;
    public static WomanHeart womanHeart;
    public static WidemanSpine widemanSpine;
    public static EntrailFlesh entrailFlesh;
    public static PurgePill purgePill;
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

    public static void init() {
    	rebar = new Rebar();
    	luckyEgg = new LuckyEgg();
    	babyEye = new BabyEye();
    	womanHeart = new WomanHeart();
    	widemanSpine = new WidemanSpine();
    	entrailFlesh = new EntrailFlesh();
    	purgePill = new PurgePill();
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
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	rebar.initModel();
    	luckyEgg.initModel();
    	babyEye.initModel();
    	womanHeart.initModel();
    	widemanSpine.initModel();
    	entrailFlesh.initModel();
    	purgePill.initModel();
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
    }
}