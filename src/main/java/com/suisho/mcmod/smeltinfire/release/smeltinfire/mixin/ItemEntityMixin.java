package com.suisho.mcmod.smeltinfire.release.smeltinfire.mixin;

import com.suisho.mcmod.smeltinfire.release.smeltinfire.SmeltInFire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    @Shadow
    public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void injectItemEntity(CallbackInfo ci) {
        //这个最好照抄
        if (this.getStack().isEmpty()) {
            this.discard();
            ci.cancel();
        }
        ItemStack oreItem = this.getStack();
        if (this.isOnFire()) {
            Item spawnItem = SmeltInFire.getVanillaOre(oreItem.getItem().toString());
            if (spawnItem != null) {
                ItemEntity ingotEntity = new ItemEntity(getWorld(), getX(), getY() + 0.28d, getZ(), new ItemStack(spawnItem, this.getStack().getCount()), SmeltInFire.itemSpawnVelocity(), 0.23d, SmeltInFire.itemSpawnVelocity());
                if (getWorld().spawnEntity(ingotEntity)) {
                    ingotEntity.setOnFire(false);
                    ExperienceOrbEntity experienceOrbEntity = new ExperienceOrbEntity(getWorld(), getX(), getY(), getZ(),oreItem.getCount() * 2);
                    getWorld().spawnEntity(experienceOrbEntity);
                }
                this.kill();
            }
        }
    }

    @Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
    public void injectFireImmune(CallbackInfoReturnable<Boolean> cir) {
        if (SmeltInFire.hasFireImmune(this.getStack().getItem().toString())) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
