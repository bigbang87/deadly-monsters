package com.dmonsters.main;

import com.dmonsters.entity.EntityBaby;
import com.dmonsters.entity.EntityClimber;
import com.dmonsters.entity.EntityEntrail;
import com.dmonsters.entity.EntityFreezer;
import com.dmonsters.entity.EntityMutantSteve;
import com.dmonsters.entity.EntityPresent;
import com.dmonsters.entity.EntityStranger;
import com.dmonsters.entity.EntityWideman;
import com.dmonsters.entity.EntityWoman;
import com.dmonsters.entity.EntityZombieChicken;
import com.dmonsters.entityProjectile.EntityLuckyEgg;
import com.dmonsters.render.RenderBaby;
import com.dmonsters.render.RenderClimber;
import com.dmonsters.render.RenderEntrail;
import com.dmonsters.render.RenderFreezer;
import com.dmonsters.render.RenderMutantSteve;
import com.dmonsters.render.RenderPresent;
import com.dmonsters.render.RenderStranger;
import com.dmonsters.render.RenderWideman;
import com.dmonsters.render.RenderWoman;
import com.dmonsters.render.RenderZombieChicken;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    public static void init() {
        int id = 1;
        ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/baby.png");
    	if (!ModConfig.mobsDisable) {
	        //Zombie Steve
	        EntityRegistry.registerModEntity(mobTexture, EntityMutantSteve.class, "mutantSteve", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.mutantSteveDisabled)
	        	EntityRegistry.addSpawn(EntityMutantSteve.class, ModConfig.mutantSteveSawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityMutantSteve.LOOT);
	        
	        //Freezer
	        EntityRegistry.registerModEntity(mobTexture, EntityFreezer.class, "freezer", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.freezerDisabled)
	        	EntityRegistry.addSpawn(EntityFreezer.class, ModConfig.freezerSawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityFreezer.LOOT);
	        
	        //Climber
	        EntityRegistry.registerModEntity(mobTexture, EntityClimber.class, "climber", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.climberDisabled)
	        	EntityRegistry.addSpawn(EntityClimber.class, ModConfig.climberSawnRate, 1, 5, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityClimber.LOOT);
	        
	        //Zombie Chicken
	        EntityRegistry.registerModEntity(mobTexture, EntityZombieChicken.class, "zombieChicken", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.zombieChickenDisabled)
	        	EntityRegistry.addSpawn(EntityZombieChicken.class, ModConfig.zombieChickenSawnRate, 2, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityZombieChicken.LOOT);
	        
	        //Baby
	        EntityRegistry.registerModEntity(mobTexture, EntityBaby.class, "baby", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.babyDisabled)
	        	EntityRegistry.addSpawn(EntityBaby.class, ModConfig.babySawnRate, 2, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityBaby.LOOT);
	        
	        //Wideman
	        EntityRegistry.registerModEntity(mobTexture, EntityWideman.class, "wideman", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.fallenLeaderDisabled)
	        	EntityRegistry.addSpawn(EntityWideman.class, ModConfig.fallenLeaderSawnRate, 2, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityWideman.LOOT);
	        
	        //Woman
	        EntityRegistry.registerModEntity(mobTexture, EntityWoman.class, "woman", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.bloodyMaidenDisabled)
	        	EntityRegistry.addSpawn(EntityWoman.class, ModConfig.bloodyMaidenSawnRate, 2, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityWoman.LOOT);
	        
	        //Entrail
	        EntityRegistry.registerModEntity(mobTexture, EntityEntrail.class, "entrail", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.entrailDisabled)
	        	EntityRegistry.addSpawn(EntityEntrail.class, ModConfig.entrailSawnRate, 2, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityEntrail.LOOT);
	        
	        //Present
	        EntityRegistry.registerModEntity(mobTexture, EntityPresent.class, "present", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.presentDisabled)
	        	EntityRegistry.addSpawn(EntityPresent.class, ModConfig.presentSawnRate, 2, 8, EnumCreatureType.MONSTER, BiomesProvider.getSnowBiomes());
	        LootTableList.register(EntityPresent.LOOT);
	        
	        //Stranger
	        EntityRegistry.registerModEntity(mobTexture, EntityStranger.class, "stranger", id++, MainMod.instance, 64, 3, true);
	        if (!ModConfig.strangerDisabled)
	        	EntityRegistry.addSpawn(EntityPresent.class, ModConfig.strangerSawnRate, 2, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
	        LootTableList.register(EntityStranger.LOOT);
    	}
        
        //Lucky Egg
        EntityRegistry.registerModEntity(mobTexture, EntityLuckyEgg.class, "luckyEgg", id++, MainMod.instance, 64, 3, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
		RenderingRegistry.registerEntityRenderingHandler(EntityMutantSteve.class, RenderMutantSteve.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFreezer.class, RenderFreezer.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityClimber.class, RenderClimber.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieChicken.class, RenderZombieChicken.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBaby.class, RenderBaby.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityWideman.class, RenderWideman.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityWoman.class, RenderWoman.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEntrail.class, RenderEntrail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPresent.class, RenderPresent.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityStranger.class, RenderStranger.FACTORY);
    }
    
    @SideOnly(Side.CLIENT)
    public static void initHackModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLuckyEgg.class, new RenderSnowball<EntityLuckyEgg>(Minecraft.getMinecraft().getRenderManager(), ModItems.luckyEgg, Minecraft.getMinecraft().getRenderItem()));

    }
}