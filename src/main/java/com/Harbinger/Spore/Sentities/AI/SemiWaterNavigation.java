package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;

public class SemiWaterNavigation extends WaterBoundPathNavigation {
    public SemiWaterNavigation(Mob p_26594_, Level p_26595_) {
        super(p_26594_, p_26595_);
    }

    @Override
    protected PathFinder createPathFinder(int p_26598_) {
        this.nodeEvaluator = new SwimNodeEvaluator(true);
        return new PathFinder(this.nodeEvaluator, p_26598_);
    }
}
