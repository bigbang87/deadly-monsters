package com.dmonsters.main;

import com.dmonsters.blocks.BarbedWire;
import com.dmonsters.blocks.ChristmasTree;
import com.dmonsters.blocks.Concrete;
import com.dmonsters.blocks.Dump;
import com.dmonsters.blocks.FallingConcrete;
import com.dmonsters.blocks.MeshFence;
import com.dmonsters.blocks.MeshFencePole;
import com.dmonsters.blocks.PresentBlock;
import com.dmonsters.blocks.PresentBox;
import com.dmonsters.blocks.RawConcrete;
import com.dmonsters.blocks.SoulEye;
import com.dmonsters.blocks.StrengthenedCobblestone;
import com.dmonsters.blocks.StrengthenedStone;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	public static Concrete concrete;
	public static RawConcrete rawConcrete;
	public static FallingConcrete fallingConcrete;
	public static StrengthenedStone strengthenedStone;
	public static StrengthenedCobblestone strengthenedCobblestone;
	public static BarbedWire barbedWire;
	public static MeshFence meshFence;
	public static MeshFencePole meshFencePole;
	public static Dump dump;
	public static SoulEye souleye;
	public static PresentBlock presentBlock;
	public static ChristmasTree christmasTree;
	public static PresentBox presentBox;

    public static void init() {
    	concrete = new Concrete();
    	rawConcrete = new RawConcrete();
    	fallingConcrete = new FallingConcrete();
    	strengthenedStone = new StrengthenedStone();
    	strengthenedCobblestone = new StrengthenedCobblestone();
    	barbedWire = new BarbedWire();
    	meshFence = new MeshFence();
    	meshFencePole = new MeshFencePole();
    	dump = new Dump();
    	souleye = new SoulEye();
    	presentBlock = new PresentBlock();
    	christmasTree = new ChristmasTree();
    	presentBox = new PresentBox();
    	
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	concrete.initModel();
    	rawConcrete.initModel();
    	fallingConcrete.initModel();
    	strengthenedStone.initModel();
    	strengthenedCobblestone.initModel();
    	barbedWire.initModel();
    	meshFence.initModel();
    	meshFencePole.initModel();
    	dump.initModel();
    	souleye.initModel();
    	presentBlock.initModel();
    	christmasTree.initModel();
    	presentBox.initModel();
    }
}
