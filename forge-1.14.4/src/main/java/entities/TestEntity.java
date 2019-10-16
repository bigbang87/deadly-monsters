package entities;

import entities.ai.MutantZombieSteveAttack;
import init.ModEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TestEntity extends MonsterEntity
{
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(TestEntity.class, DataSerializers.BOOLEAN);
	
	@SuppressWarnings("unchecked")
	public TestEntity(EntityType<? extends CreatureEntity> type, World worldIn)
	{
		super((EntityType<? extends MonsterEntity>) ModEntities.test_entity, worldIn);
		this.getDataManager().set(ARMS_RAISED, Boolean.valueOf(false));
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.getDataManager().register(ARMS_RAISED, false);
	}
	
	@OnlyIn(Dist.CLIENT)
    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED).booleanValue();
    }

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
	    //this.goalSelector.addGoal(2, new MutantZombieSteveAttack(this, 1.0D, false));
	    this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, (double)0.1F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0d);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.23F);
	    this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	    this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}
	
	public void setArmsRaised(boolean armsRaised)
	{
		this.getDataManager().set(ARMS_RAISED, Boolean.valueOf(armsRaised));
	}
}
