package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.AI.BreakBlockGoal;
import com.Harbinger.Spore.Sentities.AI.FollowOthersGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class InfectedVillager extends Infected{
    public InfectedVillager(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {


        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 4.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });

        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.COMPOSTER , this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.SMOKER , this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.BARREL , this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.LOOM, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.BLAST_FURNACE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.BREWING_STAND, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.CAULDRON, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.FLETCHING_TABLE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.CARTOGRAPHY_TABLE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.LECTERN, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.SMITHING_TABLE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.STONECUTTER, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.GRINDSTONE, this,1,4));
        this.goalSelector.addGoal(7, new FollowOthersGoal(this , 1 , EvolvedInfected.class, 32, true));



        super.registerGoals();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_vil_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inf_vil_hp.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3);

    }

}
