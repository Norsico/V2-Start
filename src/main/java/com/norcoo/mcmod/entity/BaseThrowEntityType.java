package com.norcoo.mcmod.entity;

import com.norcoo.mcmod.MCmod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import org.jetbrains.annotations.Nullable;

public class BaseThrowEntityType<T extends Entity> implements ToggleableFeature, TypeFilter<Entity, T> {

public static final EntityType<BaseThrownItemEntity> iron_egg = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(MCmod.MOD_ID, "iron_egg"),
        EntityType.Builder.<BaseThrownItemEntity>create(BaseThrownItemEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
                .build()
);

    public static void registerModEntities() {
        // 在这里调用实体注册
        System.out.println("实体已注册");
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return null;
    }

    @Nullable
    @Override
    public T downcast(Entity obj) {
        return null;
    }

    @Override
    public Class<? extends Entity> getBaseClass() {
        return null;
    }
}
