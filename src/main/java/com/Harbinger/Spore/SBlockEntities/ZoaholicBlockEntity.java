package com.Harbinger.Spore.SBlockEntities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.SblockEntities;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Screens.ZoaholicMenu;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.Organoids.Proto;
import com.Harbinger.Spore.Sentities.Signal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ZoaholicBlockEntity extends BlockEntity implements AnimatedEntity , MenuProvider {
    private int ticks;
    private boolean hasBrain = false;
    private boolean hasHeart = false;
    private int amountOfInnards = 0;
    private int biomass = 0;
    private int processing = 0;
    private int side;
    public ZoaholicBlockEntity(BlockPos pos, BlockState state) {
        super(SblockEntities.ZOAHOLIC.get(), pos, state);
        side = setSide(state);
    }
    private int setSide(BlockState state){
        if (state.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty directionProperty){
            return state.getValue(directionProperty).get3DDataValue();
        }
        return 2;
    }

    public int getAmountOfInnards() {
        return amountOfInnards;
    }
    public void setAmountOfInnards(int amountOfInnards) {
        this.amountOfInnards = amountOfInnards;
    }
    public boolean HasBrain() {
        return hasBrain;
    }
    public void setBrain(boolean hasBrain) {
        this.hasBrain = hasBrain;
    }
    public boolean HasHeart() {
        return hasHeart;
    }
    public void setHasHeart(boolean hasHeart) {
        this.hasHeart = hasHeart;
    }
    public boolean hasEnoughInnards(){
        return getAmountOfInnards() >= 2;
    }
    public int getBiomass() {
        return biomass;
    }
    public void setBiomass(int biomass) {
        this.biomass = biomass;
    }
    public void addBiomass(int value){setBiomass(getBiomass()+value);}
    public int getProcessing() {
        return processing;
    }
    public void setProcessing(int processing) {
        this.processing = processing;
    }
    public void setSide(int i){this.side = i;}
    public int getSide(){return this.side;}

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putInt("innards",getAmountOfInnards());
        tag.putInt("biomass",getBiomass());
        tag.putBoolean("brain",HasBrain());
        tag.putBoolean("heart",HasHeart());
        tag.putInt("side",getSide());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        setAmountOfInnards(tag.getInt("innards"));
        setBiomass(tag.getInt("biomass"));
        setBrain(tag.getBoolean("brain"));
        setHasHeart(tag.getBoolean("heart"));
        setSide(getSide());
        super.load(tag);
    }

    public void lowerBiomass(){
        this.setBiomass(this.getBiomass()-1);
    }
    public int getTicks() {
        return ticks;
    }
    public boolean isActive(){
        return HasHeart() && HasBrain() && hasEnoughInnards() && getBiomass() > 1;
    }

    public void tickBlockEntity(){
        if (ticks < 360){
            ticks++;
        }else{
            ticks = 0;
        }
    }
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }


    public static <E extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, ZoaholicBlockEntity e) {
        if (e.isActive()){
            e.lowerBiomass();
            if (e.getProcessing() >0){
                e.setProcessing(e.getProcessing()-1);
                if (e.getProcessing() == 198){
                    level.playSound(null, pos, Ssounds.PRINTING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                if (e.getProcessing() == 1){
                    e.writeDocument(level,pos);
                }
            }
            if (e.getBiomass() % 60 == 0){
                e.spreadMadness(level,pos);
                level.playSound(null, pos, Ssounds.HEART_BEAT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    public static <E extends BlockEntity> void clientTick(Level level, BlockPos pos, BlockState state, ZoaholicBlockEntity e) {
        e.tickBlockEntity();
    }
    @Nullable
    protected LivingEntity getAnomaly(Level level){
        RandomSource source = RandomSource.create();
        List<LivingEntity> entities = new ArrayList<>();
        if (level instanceof ServerLevel serverLevel){
            for (Entity entity : serverLevel.getAllEntities()){
                if (entity instanceof LivingEntity living){
                    if (living instanceof Proto || living instanceof Calamity){
                        entities.add(living);
                    }
                }
            }
        }
        if (entities.isEmpty()){
            return null;
        }else {
            int size = entities.size();
            return entities.get(source.nextInt(size));
        }
    }
    public void writeDocument(Level level,BlockPos blockPos){
        LivingEntity livingEntity = getAnomaly(level);
        RandomSource randomSource = RandomSource.create();
        ItemStack stack = new ItemStack(Items.PAPER);
        if (livingEntity != null){
            if (Math.random() < 0.2f){
                alertAnomaly(blockPos,livingEntity);
                stack.setHoverName(Component.translatable("zoaholic.line_5"));
            }else{
                int x = livingEntity.getBlockX() + randomSource.nextInt(-50,50);
                int z = livingEntity.getBlockZ() + randomSource.nextInt(-50,50);
                String component = Component.translatable("zoaholic.line_3").getString();
                stack.setHoverName(Component.literal(component+" X:"+x + " Z:"+z));
            }
        }else{
            stack.setHoverName(Component.translatable("zoaholic.line_4"));
        }
        ItemEntity item = new ItemEntity(level,blockPos.getX(),blockPos.getY()+0.5,blockPos.getZ(),stack);
        level.addFreshEntity(item);
    }

    private void alertAnomaly(BlockPos pos,LivingEntity livingEntity){
        if (livingEntity instanceof Proto proto){
            proto.setSignal(new Signal(true,pos));
        }else if (livingEntity instanceof Calamity calamity){
            calamity.setSearchArea(pos);
        }
    }

    public void spreadMadness(Level level,BlockPos blockPos){
        if (Math.random() < 0.1){
            AABB aabb = AABB.ofSize(new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()), 16, 16, 16);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, aabb,e -> SConfig.SERVER.proto_sapient_target.get().contains(e.getEncodeId()) || e instanceof Player);
            for(LivingEntity entity : entities) {
                entity.addEffect(new MobEffectInstance(Seffects.MADNESS.get(),2400,0,false,false));
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.spore.zoaholic");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ZoaholicMenu(i,inventory);
    }
}
