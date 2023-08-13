package com.para_mada.maryvidad.items.totems;

import com.para_mada.maryvidad.effects.EffectManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NormalTotem {

    @SuppressWarnings("unused")
    public void performResurrection(Entity resurrected) {
        resurrected.getWorld().sendEntityStatus(resurrected, EntityStatuses.USE_TOTEM_OF_UNDYING);
    }


    @SuppressWarnings("unused")
    public void postRevive(Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.setHealth(1.0f);
            living.clearStatusEffects();
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 9));
            living.addStatusEffect(new StatusEffectInstance(EffectManager.KODCAKE_COOLDOWN, 60 * 5 * 20, 0));
        }
    }
}