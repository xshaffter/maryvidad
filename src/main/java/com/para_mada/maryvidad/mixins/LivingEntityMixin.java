package com.para_mada.maryvidad.mixins;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.effects.EffectManager;
import com.para_mada.maryvidad.items.ItemManager;
import com.para_mada.maryvidad.items.totems.NormalTotem;
import com.para_mada.maryvidad.util.IEntityDataSaver;
import com.para_mada.maryvidad.util.LivingEntityBridge;
import com.para_mada.maryvidad.util.PlayerEntityBridge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements IEntityDataSaver {

    @Unique
    private NbtCompound persistentData;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
    public void useCustomTotem(DamageSource source, CallbackInfoReturnable<Boolean> callback) {
        //initializes PlayerEntity entity, which is a copy of this cast to Living Entity and then PlayerEntity
        if (source.isOf(DamageTypes.OUT_OF_WORLD)) {
            callback.setReturnValue(false);
            return;
        }
        //noinspection ConstantValue
        if (((Entity)this) instanceof ServerPlayerEntity player) {
            if (player.hasStatusEffect(EffectManager.KODCAKE_COOLDOWN)){
                return;
            }
            var totem_item = new NormalTotem();
            totem_item.performResurrection(this);
            totem_item.postRevive(this);
            callback.setReturnValue(true);
        }

    }


    @Inject(at = @At("HEAD"), method = "tick")
    protected void onTick(CallbackInfo ci) {
        if (!(((Entity)this) instanceof ServerPlayerEntity player)){
            return;
        }

        var inventory = LivingEntityBridge.getInventory(this);
        if (inventory == null) {
            return;
        }

        if (LivingEntityBridge.isCreative(this)) {
            return;
        }

        if (inventory.containsAny(Set.of(ItemManager.MARY_COIN, ItemManager.HELADERIA))) {
            var persistent = LivingEntityBridge.getPersistentData(this);
            for (var slot = 0; slot < inventory.size(); slot++) {
                var stack = inventory.getStack(slot);
                if (stack.isOf(ItemManager.HELADERIA)) {
                    if (persistent.getBoolean("unlocked_ice_cream")) {
                        continue;
                    }

                    inventory.setStack(slot, ItemStack.EMPTY);
                    persistent.putBoolean("unlocked_ice_cream", true);
                } else if (stack.isOf(ItemManager.MARY_COIN)) {
                    PlayerEntityBridge.addMoney(player, stack.getCount());
                    inventory.setStack(slot, ItemStack.EMPTY);
                }
            }
        }
    }

    @Override
    public NbtCompound maryvidad$getPersistentData() {
        if (persistentData == null) {
            persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    protected void writeNbt(NbtCompound nbt, CallbackInfo info) {
        if (persistentData != null) {
            nbt.put("%s.data".formatted(Maryvidad.MOD_ID), persistentData);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    protected void injectedReadNBT(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("%s.data".formatted(Maryvidad.MOD_ID), NbtElement.COMPOUND_TYPE)) {
            persistentData = nbt.getCompound("%s.data".formatted(Maryvidad.MOD_ID));
        }
    }

}