package com.dmonsters.main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSounds {
	public static SoundEvent MUTANT_DEATH;
	public static SoundEvent MUTANT_HURT;
	public static SoundEvent MUTANT_AMBIENT;
	public static SoundEvent MUTANT_ATTACK;
	public static SoundEvent FREEZER_DEATH;
	public static SoundEvent FREEZER_HURT;
	public static SoundEvent FREEZER_AMBIENT;
	public static SoundEvent FREEZER_ATTACK;
	public static SoundEvent CLIMBER_DEATH;
	public static SoundEvent CLIMBER_HURT;
	public static SoundEvent CLIMBER_AMBIENT;
	public static SoundEvent CLIMBER_ATTACK;
	public static SoundEvent BABY_DEATH;
	public static SoundEvent BABY_HURT;
	public static SoundEvent BABY_AMBIENT;
	public static SoundEvent BABY_ATTACK;
	public static SoundEvent WIDEMAN_DEATH;
	public static SoundEvent WIDEMAN_HURT;
	public static SoundEvent WIDEMAN_AMBIENT;
	public static SoundEvent WIDEMAN_ATTACK;
	public static SoundEvent MAIDEN_DEATH;
	public static SoundEvent MAIDEN_HURT;
	public static SoundEvent MAIDEN_AMBIENT;
	public static SoundEvent MAIDEN_ATTACK;
	public static SoundEvent ENTRAIL_DEATH;
	public static SoundEvent ENTRAIL_HURT;
	public static SoundEvent ENTRAIL_AMBIENT;
	public static SoundEvent ENTRAIL_ATTACK;
	public static SoundEvent PRESENT_DEATH;
	public static SoundEvent PRESENT_HURT;
	public static SoundEvent PRESENT_AMBIENT;
	public static SoundEvent PRESENT_ATTACK;
	public static SoundEvent DUMP_MAKE;
	
	public static SoundEvent BLOCK_SOULEYE_KILL;
	
	public static void init() {
		DUMP_MAKE = registerSound("block.dump.make");
		BLOCK_SOULEYE_KILL = registerSound("block.souleye.kill");
		
		MUTANT_DEATH = registerSound("mob.mutant.death");
		MUTANT_HURT = registerSound("mob.mutant.hurt");
		MUTANT_AMBIENT = registerSound("mob.mutant.idle");
		MUTANT_ATTACK = registerSound("mob.mutant.attack");
		
		FREEZER_DEATH = registerSound("mob.freezer.death");
		FREEZER_HURT = registerSound("mob.freezer.hurt");
		FREEZER_AMBIENT = registerSound("mob.freezer.idle");
		FREEZER_ATTACK = registerSound("mob.freezer.attack");
		
		CLIMBER_DEATH = registerSound("mob.climber.death");
		CLIMBER_HURT = registerSound("mob.climber.hurt");
		CLIMBER_AMBIENT = registerSound("mob.climber.idle");
		CLIMBER_ATTACK = registerSound("mob.climber.attack");
		
		BABY_DEATH = registerSound("mob.baby.death");
		BABY_HURT = registerSound("mob.baby.hurt");
		BABY_AMBIENT = registerSound("mob.baby.idle");
		BABY_ATTACK = registerSound("mob.baby.attack");
		
		WIDEMAN_DEATH = registerSound("mob.wideman.death");
		WIDEMAN_HURT = registerSound("mob.wideman.hurt");
		WIDEMAN_AMBIENT = registerSound("mob.wideman.idle");
		WIDEMAN_ATTACK = registerSound("mob.wideman.attack");
		
		MAIDEN_DEATH = registerSound("mob.maiden.death");
		MAIDEN_HURT = registerSound("mob.maiden.hurt");
		MAIDEN_AMBIENT = registerSound("mob.maiden.idle");
		MAIDEN_ATTACK = registerSound("mob.maiden.attack");
		
		ENTRAIL_DEATH = registerSound("mob.entrail.death");
		ENTRAIL_HURT = registerSound("mob.entrail.hurt");
		ENTRAIL_AMBIENT = registerSound("mob.entrail.idle");
		ENTRAIL_ATTACK = registerSound("mob.entrail.attack");
		
		PRESENT_DEATH = registerSound("mob.present.death");
		PRESENT_HURT = registerSound("mob.present.hurt");
		PRESENT_AMBIENT = registerSound("mob.present.idle");
		PRESENT_ATTACK = registerSound("mob.present.attack");
	}
	
	private static SoundEvent registerSound(String id) {
		ResourceLocation soundID = new ResourceLocation(MainMod.MODID, id);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
}
