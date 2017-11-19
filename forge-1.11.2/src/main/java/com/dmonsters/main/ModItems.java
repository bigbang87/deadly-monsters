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
import com.dmonsters.items.SunlightDrop;
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
    public static Dagon dagon;
    public static FlyingDagon flyingDagon;
    public static SunlightDrop sunlightDrop;
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
    public static MobSpawnerItem mobSpawnerItem_hauntedCow;
    public static MobSpawnerItem mobSpawnerItem_topielec;

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
    	sunlightDrop = new SunlightDrop();
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
    	mobSpawnerItem_hauntedCow = new MobSpawnerItem("hauntedCow");
    	mobSpawnerItem_topielec = new MobSpawnerItem("topielec");
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
    	dagon.initModel();
    	flyingDagon.initModel();
    	sunlightDrop.initModel();
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
    	mobSpawnerItem_hauntedCow.initModel();
    	mobSpawnerItem_topielec.initModel();
    }
}