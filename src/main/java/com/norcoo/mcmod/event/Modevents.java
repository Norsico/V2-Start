package com.norcoo.mcmod.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class Modevents {

    public static void register() {AttackEntityCallback.EVENT.register(Modevents::attack);}

    private static ActionResult attack(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult entityHitResult) {
        if (entity instanceof LivingEntity target) {
            // 检查玩家是否手持木棍
            if (player.getMainHandStack().getItem() == Items.STICK) {
                // 在目标生物的位置召唤闪电
                LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightningBolt.setPosition(target.getX(), target.getY(), target.getZ());
            }
        }
        return ActionResult.PASS; // 继续其他事件处理
    }


}

