package com.Harbinger.Spore.Compat;

import com.Harbinger.Spore.Core.Sitems;
import com.Harbinger.Spore.Recipes.InjectionRecipe;
import com.Harbinger.Spore.Sentities.VariantKeeper;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;


public class InjectionCraftingCategory implements IRecipeCategory<InjectionRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Spore.MODID, "injection");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Spore.MODID,
            "textures/gui/injection_gui.png");
    public static final RecipeType<InjectionRecipe> INJECTION_TYPE =
            new RecipeType<>(UID, InjectionRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public InjectionCraftingCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 0, 0, 176, 82).setTextureSize(176,166).build();
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Sitems.SYRINGE.get()));
    }

    @Override
    public RecipeType<InjectionRecipe> getRecipeType() {
        return INJECTION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("item.spore.syringe");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, InjectionRecipe injectionRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 97, 17).addIngredients(Ingredient.of(Sitems.SYRINGE.get()));
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 97, 53).addIngredients(Ingredient.of(injectionRecipe.getResultItem()));
    }

    @Override
    public void draw(InjectionRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null){
            ResourceLocation location = new ResourceLocation(recipe.getEntityId());
            Entity entity = ForgeRegistries.ENTITY_TYPES.getValue(location).create(level);
            if (entity instanceof LivingEntity living){
                if (living instanceof VariantKeeper keeper){
                    keeper.setVariant(recipe.getEntityType());
                    renderEntityInInventoryFollowsAngle(stack,34,70,20,0f,0f,living);
                }else{
                    renderEntityInInventoryFollowsAngle(stack,34,70,20,0f,0f,living);
                }
            }
        }
        IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
    }

    private void renderEntityInInventoryFollowsAngle(PoseStack poseStack, int x, int y, int scale, float angleXComponent, float angleYComponent, LivingEntity entity) {
        poseStack.pushPose();
        // Rotate entity for proper display
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));  // Rotate around Z-axis
        poseStack.mulPose(Vector3f.XP.rotationDegrees(angleYComponent * 20.0F));  // Rotate around X-axis
        poseStack.translate(0,y,x);

        // Store previous entity rotation states
        float prevBodyRot = entity.yBodyRot;
        float prevYRot = entity.getYRot();
        float prevXRot = entity.getXRot();
        float prevHeadRotO = entity.yHeadRotO;
        float prevHeadRot = entity.yHeadRot;

        // Apply new rotation angles
        entity.yBodyRot = 180.0F + angleXComponent * 20.0F;
        entity.setYRot(180.0F + angleXComponent * 40.0F);
        entity.setXRot(-angleYComponent * 20.0F);
        entity.yHeadRot = entity.getYRot();
        entity.yHeadRotO = entity.getYRot();

        // Render entity
        InventoryScreen.renderEntityInInventory(x, y, scale, angleXComponent, angleYComponent, entity);

        // Restore previous rotation states
        entity.yBodyRot = prevBodyRot;
        entity.setYRot(prevYRot);
        entity.setXRot(prevXRot);
        entity.yHeadRotO = prevHeadRotO;
        entity.yHeadRot = prevHeadRot;

        poseStack.popPose();
    }


}