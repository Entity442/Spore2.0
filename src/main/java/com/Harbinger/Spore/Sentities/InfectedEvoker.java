package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class InfectedEvoker extends EvolvedInfected implements InventoryCarrier {
    public InfectedEvoker(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
    private final SimpleContainer inventory = new SimpleContainer(5);

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_pil_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inf_pil_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.inf_pil_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 1);

    }

    public void addAdditionalSaveData(CompoundTag p_33300_) {
        super.addAdditionalSaveData(p_33300_);
        ListTag listtag = new ListTag();

        for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                listtag.add(itemstack.save(new CompoundTag()));
            }
        }

        p_33300_.put("Inventory", listtag);
    }

    public void readAdditionalSaveData(CompoundTag p_33291_) {
        super.readAdditionalSaveData(p_33291_);
        ListTag listtag = p_33291_.getList("Inventory", 10);

        for(int i = 0; i < listtag.size(); ++i) {
            ItemStack itemstack = ItemStack.of(listtag.getCompound(i));
            if (!itemstack.isEmpty()) {
                this.inventory.addItem(itemstack);
            }
        }

        this.setCanPickUpLoot(false);
    }

    @Override
    public SimpleContainer getInventory() {
        return this.inventory;
    }
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33282_, DifficultyInstance p_33283_, MobSpawnType p_33284_, @Nullable SpawnGroupData p_33285_, @Nullable CompoundTag p_33286_) {
        RandomSource randomsource = p_33282_.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, p_33283_);
        this.populateDefaultEquipmentEnchantments(randomsource, p_33283_);
        return super.finalizeSpawn(p_33282_, p_33283_, p_33284_, p_33285_, p_33286_);
    }
    public SlotAccess getSlot(int p_149743_) {
        int i = p_149743_ - 300;
        return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(p_149743_);
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.INF_GROWL.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
}
