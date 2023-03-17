package com.Harbinger.Spore.ExtremelySusThings;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Spore;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BiomeModification implements BiomeModifier {
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER =
            RegistryObject.create(new ResourceLocation(Spore.MODID, "inf_spawns"),
                    ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Spore.MODID);

    public BiomeModification() {
    }

    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            for (String str : SConfig.DATAGEN.spawns.get()){
                String[] string = str.split("\\|" );
                EntityType<?> entity = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(string[0]));
                if (entity != null && biome.is(TagKey.create(Registry.BIOME_REGISTRY , new ResourceLocation("minecraft:is_overworld")))){
                builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER)
                        .add(new MobSpawnSettings.SpawnerData(entity, Integer.parseUnsignedInt(string[1]), Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3])));
                }
            }
        }
    }

    public Codec codec() {
        return SERIALIZER.get();
    }

    public static Codec<BiomeModification> makeCodec() {
        return Codec.unit(BiomeModification::new);
    }





}
