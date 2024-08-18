package com.norcoo.mcmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import com.norcoo.mcmod.MCmod;

public class ModEntities {
    public static final EntityType<TDProjectileEntity> TD_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MCmod.MOD_ID, "td_projectile"),
            FabricEntityTypeBuilder.<TDProjectileEntity>create(SpawnGroup.MISC, TDProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static void registerEntities() {
        // 注册其他实体（如果有）
    }
}
