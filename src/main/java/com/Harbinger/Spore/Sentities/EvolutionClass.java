package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Scamper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;

public class EvolutionClass {
    public static void Evolve(Infected livingEntity, List<? extends String> value){
        if (livingEntity != null && value != null && livingEntity.level instanceof ServerLevel world){
            Level level = livingEntity.level;
            RandomSource random = RandomSource.create();
            if (Math.random() < 0.9) {
                Random rand = new Random();
                for (int i = 0; i < 1; ++i) {
                    int randomIndex = rand.nextInt(value.size());
                    ResourceLocation randomElement1 = new ResourceLocation(value.get(randomIndex));
                    EntityType<?> randomElement = ForgeRegistries.ENTITY_TYPES.getValue(randomElement1);
                    Entity waveentity = randomElement.create(level);
                    waveentity.setPos(livingEntity.getX(), livingEntity.getY() + 0.5D, livingEntity.getZ());
                    waveentity.setCustomName(livingEntity.getCustomName());
                    if (waveentity instanceof Infected infected){
                        infected.setKills(livingEntity.getKills());
                        infected.setSearchPos(livingEntity.getSearchPos());
                        infected.setLinked(livingEntity.getLinked());
                        infected.finalizeSpawn(world, livingEntity.level.getCurrentDifficultyAt(new BlockPos((int) livingEntity.getX(),(int)  livingEntity.getY(),(int)  livingEntity.getZ())), MobSpawnType.NATURAL, null, null);
                    }
                    level.addFreshEntity(waveentity);

                    livingEntity.discard();
                }
            }else {
                Scamper scamper = new Scamper(Sentities.SCAMPER.get(), level);
                scamper.setPos(livingEntity.getX(), livingEntity.getY() + 0.5D, livingEntity.getZ());
                scamper.setCustomName(livingEntity.getCustomName());
                scamper.setKills(livingEntity.getKills());
                scamper.setSearchPos(livingEntity.getSearchPos());
                scamper.setLinked(livingEntity.getLinked());
                level.addFreshEntity(scamper);
                livingEntity.discard();
            }
            if (level instanceof ServerLevel serverLevel){
                double x0 = livingEntity.getX() - (random.nextFloat() - 0.1) * 0.1D;
                double y0 = livingEntity.getY() + (random.nextFloat() - 0.25) * 0.15D * 5;
                double z0 = livingEntity.getZ() + (random.nextFloat() - 0.1) * 0.1D;
                serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x0, y0, z0, 2, 0, 0, 0, 1);
            }
        }
    }
}
