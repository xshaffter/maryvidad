package com.para_mada.maryvidad.effects;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.effects.custom.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectManager {

    public static final StatusEffect KODCAKE_COOLDOWN = new KodcakeCD();
    public static final StatusEffect SECOND_SKILL_COOLDOWN = new SecondSkillCD();
    public static final StatusEffect MONALISA_EFFECT = new MonalisaEffect();
    public static final StatusEffect FIRST_SKILL_COOLDOWN = new FirstSkillCD();
    public static final StatusEffect RADAR_COOLDOWN = new RadarCD();
    public static final StatusEffect THIRD_SKILL_COOLDOWN = new ThirdSkillCD();
    public static final StatusEffect PARAMADA_EFFECT = new ParamadaEffect();

    public static void registerStatusEffects() {
        registerStatusEffect("monalisa_effect", MONALISA_EFFECT);
        registerStatusEffect("paramada_effect", PARAMADA_EFFECT);

        registerStatusEffect("kodcake_cooldown", KODCAKE_COOLDOWN);
        registerStatusEffect("radar_cooldown", RADAR_COOLDOWN);

        registerStatusEffect("first_skill", FIRST_SKILL_COOLDOWN);
        registerStatusEffect("second_skill", SECOND_SKILL_COOLDOWN);
    }

    /*
    BOLT: impacto en el suelo AOE
    monalisa: pull, BOLT y push
    lethaltoxo: comida infinita, regeneracion natural reducida
    galahad: abre la tienda y le da frascos de estus (curacion instantanea bebible instant) recargables
    kodcake: totem a cooldown (hace falta que salgan unos hotcakes)
    fideo: punzos en los guantes
    para_mada: informacion de la aventura (doxeo, marcado de zonas importantes y revelar enemigos en un area)
    pancho: camisa de colo colo
    oscar master: informacion de la aventura (no me acuerdo)
     */

    private static void registerStatusEffect(String name, StatusEffect statusEffect) {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(Maryvidad.MOD_ID, name), statusEffect);
    }
}
