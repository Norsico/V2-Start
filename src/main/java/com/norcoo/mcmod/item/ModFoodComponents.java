package com.norcoo.mcmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent T = new FoodComponent.Builder()
            .nutrition(8).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,1800),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,40),1.0f)
            .alwaysEdible()
            .build();

    public static final FoodComponent BRICK = new FoodComponent.Builder()
            .nutrition(8).saturationModifier(0.8f)
            // 反胃
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,400),1.0f)
            // 中毒
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 400), 1.0f)
            // 凋零
            .statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 400), 1.0f)
            // 瞬间伤害
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 3), 1.0f)
            // 饥饿
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 1000), 1.0f)
            // 缓慢虚弱
            .statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 400), 1.0f)
            .alwaysEdible()
            .build();

    public static final FoodComponent DAME = new FoodComponent.Builder()
            .nutrition(8).saturationModifier(0.8f)
            // 生命回复
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400), 1.0f)
            // 力量
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 400), 1.0f)
            // 速度
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400), 1.0f)
            // 跳跃提升
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400), 1.0f)
            // 幸运
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 400), 1.0f)
            // 抗性提升
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 400), 1.0f)
            // 夜视
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400), 1.0f)
            // 瞬间治疗
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2), 1.0f)
            .alwaysEdible()
            .build();
}
