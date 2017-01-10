package com.dmonsters.main;

import com.dmonsters.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;
import java.io.File;

public class ModConfig {

    private static final String CATEGORY_GENERAL = "aGeneral";
    private static final String CATEGORY_BABY = "Unborn_Baby";
    private static final String CATEGORY_CLIMBER = "Climber";
    private static final String CATEGORY_ENTRAIL = "Entrail";
    private static final String CATEGORY_FREEZER = "Freezer";
    private static final String CATEGORY_MUTANT = "Mutant_Steve";
    private static final String CATEGORY_WIDEMAN = "Fallen_Leader";
    private static final String CATEGORY_WOMAN = "Bloody_Maiden";
    private static final String CATEGORY_ZOMBIECHICKEN = "Zombie_Chicken";
    private static final String CATEGORY_PRESENT = "Present";

    //
    //GENERAL
    //
    
    public static boolean mobsDisable = false;
    public static boolean disableContent = false;
    public static float healthMultiplier = 1;
    public static float strengthMultiplier = 1;
    public static float speedMultiplier = 1;
    
    //
    //MOBS
    //
    
    //baby
    public static float babyHealthMultiplier = 1;
    public static float babyStrengthMultiplier = 1;
    public static float babySpeedMultiplier = 1;
    public static int babySawnRate = 20;
    public static boolean babyDisabled = false;
	//climber
	public static float climberHealthMultiplier = 1;
	public static float climberStrengthMultiplier = 1;
	public static float climberSpeedMultiplier = 1;
	public static int climberSawnRate = 6;
    public static boolean climberDisabled = false;
	//entrail
	public static float entrailHealthMultiplier = 1;
	public static float entrailStrengthMultiplier = 1;
	public static float entrailSpeedMultiplier = 1;
	public static int entrailSawnRate = 16;
    public static boolean entrailDisabled = false;
	//freezer
	public static float freezerHealthMultiplier = 1;
	public static float freezerStrengthMultiplier = 1;
	public static float freezerSpeedMultiplier = 1;
	public static int freezerSawnRate = 6;
    public static boolean freezerDisabled = false;
	//mutantSteve
	public static float mutantSteveHealthMultiplier = 1;
	public static float mutantSteveStrengthMultiplier = 1;
	public static float mutantSteveSpeedMultiplier = 1;
	public static int mutantSteveSawnRate = 4;
    public static boolean mutantSteveDisabled = false;
	//wideman
	public static float fallenLeaderHealthMultiplier = 1;
	public static float fallenLeaderStrengthMultiplier = 1;
	public static float fallenLeaderSpeedMultiplier = 1;
	public static int fallenLeaderSawnRate = 16;
    public static boolean fallenLeaderDisabled = false;
	//woman
	public static float bloodyMaidenHealthMultiplier = 1;
	public static float bloodyMaidenStrengthMultiplier = 1;
	public static float bloodyMaidenSpeedMultiplier = 1;
	public static int bloodyMaidenSawnRate = 16;
    public static boolean bloodyMaidenDisabled = false;
	//woman
	public static float zombieChickenHealthMultiplier = 1;
	public static float zombieChickenStrengthMultiplier = 1;
	public static float zombieChickenSpeedMultiplier = 1;
	public static int zombieChickenSawnRate = 12;
    public static boolean zombieChickenDisabled = false;
	//woman
	public static float presentHealthMultiplier = 1;
	public static float presentStrengthMultiplier = 1;
	public static float presentSpeedMultiplier = 1;
	public static int presentSawnRate = 20;
    public static boolean presentDisabled = false;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initBabyConfig(cfg);
            initClimberConfig(cfg);
            initEntrailConfig(cfg);
            initFreezerConfig(cfg);
            initMutantSteveConfig(cfg);
            initWidemanConfig(cfg);
            initWomanConfig(cfg);
            initZombieChickenConfig(cfg);
            initPresentConfig(cfg);
        } catch (Exception e1) {
        	MainMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }
    
    public static void initConfig(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        CommonProxy.config = new Configuration(new File(directory.getPath(), "DeadlyMonsters.cfg"));
        readConfig();
    }
    
    public static void saveConfig(FMLPostInitializationEvent e) {
        if (CommonProxy.config.hasChanged()) {
        	CommonProxy.config.save();
        }
    }
    
    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        mobsDisable = cfg.getBoolean("disableMobs", CATEGORY_GENERAL, mobsDisable, "Set to true if you want to disable additional mobs");
        healthMultiplier = cfg.getFloat("healthMultiplier", CATEGORY_GENERAL, 1, 0.01F, 999, "Health multiplier for all mod mobs");
        strengthMultiplier = cfg.getFloat("strengthMultiplier", CATEGORY_GENERAL, 1, 0.01F, 999, "Strength multiplier the all mod mobs");
        speedMultiplier = cfg.getFloat("speedMultiplier", CATEGORY_GENERAL, 1, 0.01F, 999, "Speed multiplier for the mod mobs");
    }
    
    private static void initBabyConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_BABY, "Unborn Baby");
        babyDisabled = cfg.getBoolean("babyDisabled", CATEGORY_BABY, babyDisabled, "Set to true if you want to disable Unborn Baby");
        babyHealthMultiplier = cfg.getFloat("babyHealthMultiplier", CATEGORY_BABY, 1, 0.01F, 999, "Unborn Baby health multiplier");
        babyStrengthMultiplier = cfg.getFloat("babyStrengthMultiplier", CATEGORY_BABY, 1, 0.01F, 999, "Unborn Baby strenght multiplier");
        babySpeedMultiplier = cfg.getFloat("babySpeedMultiplier", CATEGORY_BABY, 1, 0.01F, 999, "Unborn Baby speed multiplier");
        babySawnRate = cfg.getInt("babySawnRate", CATEGORY_BABY, babySawnRate, 0, 999, "Unborn Baby spawn rate. Default for Zombie is 8.");
    }
    
    private static void initClimberConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_CLIMBER, "Climber");
        climberDisabled = cfg.getBoolean("climberDisabled", CATEGORY_CLIMBER, climberDisabled, "Set to true if you want to disable Climber");
        climberHealthMultiplier = cfg.getFloat("climberHealthMultiplier", CATEGORY_CLIMBER, 1, 0.01F, 999, "Climber health multiplier");
        climberStrengthMultiplier = cfg.getFloat("climberStrengthMultiplier", CATEGORY_CLIMBER, 1, 0.01F, 999, "Climber strenght multiplier");
        climberSpeedMultiplier = cfg.getFloat("climberSpeedMultiplier", CATEGORY_CLIMBER, 1, 0.01F, 999, "Climber speed multiplier");
        climberSawnRate = cfg.getInt("climberSawnRate", CATEGORY_CLIMBER, climberSawnRate, 0, 999, "Climber spawn rate. Default for Zombie is 8.");
    }
    
    private static void initEntrailConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_ENTRAIL, "Entrail");
        entrailDisabled = cfg.getBoolean("entrailDisabled", CATEGORY_ENTRAIL, entrailDisabled, "Set to true if you want to disable Entrail");
        entrailHealthMultiplier = cfg.getFloat("entrailHealthMultiplier", CATEGORY_ENTRAIL, 1, 0.01F, 999, "Entrail health multiplier");
        entrailStrengthMultiplier = cfg.getFloat("entrailStrengthMultiplier", CATEGORY_ENTRAIL, 1, 0.01F, 999, "Entrail strenght multiplier");
        entrailSpeedMultiplier = cfg.getFloat("entrailSpeedMultiplier", CATEGORY_ENTRAIL, 1, 0.01F, 999, "Entrail speed multiplier");
        entrailSawnRate = cfg.getInt("entrailSawnRate", CATEGORY_ENTRAIL, entrailSawnRate, 0, 999, "Entrail spawn rate. Default for Zombie is 8.");
    }
    
    private static void initFreezerConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_FREEZER, "Freezer");
        freezerDisabled = cfg.getBoolean("freezerDisabled", CATEGORY_FREEZER, freezerDisabled, "Set to true if you want to disable Freezer");
        freezerHealthMultiplier = cfg.getFloat("freezerHealthMultiplier", CATEGORY_FREEZER, 1, 0.01F, 999, "Freezer health multiplier");
        freezerStrengthMultiplier = cfg.getFloat("freezerStrengthMultiplier", CATEGORY_FREEZER, 1, 0.01F, 999, "Freezer strenght multiplier");
        freezerSpeedMultiplier = cfg.getFloat("freezerSpeedMultiplier", CATEGORY_FREEZER, 1, 0.01F, 999, "Freezer speed multiplier");
        freezerSawnRate = cfg.getInt("freezerSawnRate", CATEGORY_FREEZER, freezerSawnRate, 0, 999, "Freezer spawn rate. Default for Zombie is 8.");
    }
    
    private static void initMutantSteveConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_MUTANT, "Mutant Steve");
        mutantSteveDisabled = cfg.getBoolean("mutantSteveDisabled", CATEGORY_MUTANT, mutantSteveDisabled, "Set to true if you want to disable Mutant Steve");
        mutantSteveHealthMultiplier = cfg.getFloat("mutantSteveHealthMultiplier", CATEGORY_MUTANT, 1, 0.01F, 999, "Mutant Steve health multiplier");
        mutantSteveStrengthMultiplier = cfg.getFloat("mutantSteveStrengthMultiplier", CATEGORY_MUTANT, 1, 0.01F, 999, "Mutant Steve strenght multiplier");
        mutantSteveSpeedMultiplier = cfg.getFloat("mutantSteveSpeedMultiplier", CATEGORY_MUTANT, 1, 0.01F, 999, "Mutant Steve speed multiplier");
        mutantSteveSawnRate = cfg.getInt("mutantSteveSawnRate", CATEGORY_MUTANT, mutantSteveSawnRate, 0, 999, "Mutant Steve spawn rate. Default for Zombie is 8.");
    }
    
    private static void initWidemanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_WIDEMAN, "Fallen Leader");
        fallenLeaderDisabled = cfg.getBoolean("fallenLeaderDisabled", CATEGORY_WIDEMAN, fallenLeaderDisabled, "Set to true if you want to disable Fallen Leader");
        fallenLeaderHealthMultiplier = cfg.getFloat("fallenLeaderHealthMultiplier", CATEGORY_WIDEMAN, 1, 0.01F, 999, "Fallen Leader health multiplier");
        fallenLeaderStrengthMultiplier = cfg.getFloat("fallenLeaderStrengthMultiplier", CATEGORY_WIDEMAN, 1, 0.01F, 999, "Fallen Leader strenght multiplier");
        fallenLeaderSpeedMultiplier = cfg.getFloat("fallenLeaderSpeedMultiplier", CATEGORY_WIDEMAN, 1, 0.01F, 999, "Fallen Leader speed multiplier");
        fallenLeaderSawnRate = cfg.getInt("fallenLeaderSawnRate", CATEGORY_WIDEMAN, fallenLeaderSawnRate, 0, 999, "Fallen Leader spawn rate. Default for Zombie is 8.");
    }
    
    private static void initWomanConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_WOMAN, "Bloody Maiden");
        bloodyMaidenDisabled = cfg.getBoolean("bloodyMaidenDisabled", CATEGORY_WOMAN, bloodyMaidenDisabled, "Set to true if you want to disable Bloody Maiden");
        bloodyMaidenHealthMultiplier = cfg.getFloat("bloodyMaidenHealthMultiplier", CATEGORY_WOMAN, 1, 0.01F, 999, "Bloody Maiden health multiplier");
        bloodyMaidenStrengthMultiplier = cfg.getFloat("bloodyMaidenStrengthMultiplier", CATEGORY_WOMAN, 1, 0.01F, 999, "Bloody Maiden strenght multiplier");
        bloodyMaidenSpeedMultiplier = cfg.getFloat("bloodyMaidenSpeedMultiplier", CATEGORY_WOMAN, 1, 0.01F, 999, "Bloody Maiden speed multiplier");
        bloodyMaidenSawnRate = cfg.getInt("bloodyMaidenSawnRate", CATEGORY_WOMAN, bloodyMaidenSawnRate, 0, 999, "Bloody Maiden spawn rate. Default for Zombie is 8.");
    }
    
    private static void initZombieChickenConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_ZOMBIECHICKEN, "Zombie Chicken");
        zombieChickenDisabled = cfg.getBoolean("zombieChickenDisabled", CATEGORY_ZOMBIECHICKEN, zombieChickenDisabled, "Set to true if you want to disable Zombie Chicken");
        zombieChickenHealthMultiplier = cfg.getFloat("zombieChickenHealthMultiplier", CATEGORY_ZOMBIECHICKEN, 1, 0.01F, 999, "Zombie Chicken health multiplier");
        zombieChickenStrengthMultiplier = cfg.getFloat("zombieChickenStrengthMultiplier", CATEGORY_ZOMBIECHICKEN, 1, 0.01F, 999, "Zombie Chicken strenght multiplier");
        zombieChickenSpeedMultiplier = cfg.getFloat("zombieChickenSpeedMultiplier", CATEGORY_ZOMBIECHICKEN, 1, 0.01F, 999, "Zombie Chicken speed multiplier");
        zombieChickenSawnRate = cfg.getInt("zombieChickenSawnRate", CATEGORY_ZOMBIECHICKEN, zombieChickenSawnRate, 0, 999, "Zombie Chicken spawn rate. Default for Zombie is 8.");
    }
    
    private static void initPresentConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_PRESENT, "Present");
        presentDisabled = cfg.getBoolean("presentDisabled", CATEGORY_PRESENT, presentDisabled, "Set to true if you want to disable Zombie Chicken");
        presentHealthMultiplier = cfg.getFloat("presentHealthMultiplier", CATEGORY_PRESENT, 1, 0.01F, 999, "Zombie Chicken health multiplier");
        presentStrengthMultiplier = cfg.getFloat("presentStrengthMultiplier", CATEGORY_PRESENT, 1, 0.01F, 999, "Zombie Chicken strenght multiplier");
        presentSpeedMultiplier = cfg.getFloat("presentSpeedMultiplier", CATEGORY_PRESENT, 1, 0.01F, 999, "Zombie Chicken speed multiplier");
        presentSawnRate = cfg.getInt("presentSawnRate", CATEGORY_PRESENT, presentSawnRate, 0, 999, "Zombie Chicken spawn rate. Default for Zombie is 8.");
    }
}