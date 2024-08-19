package com.norcoo.mcmod.event;

import com.norcoo.mcmod.item.ModItems;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import com.norcoo.mcmod.entity.BaseThrownItemEntity;


public class IronEgg {
    public static void register() {
        UseItemCallback.EVENT.register(IronEgg::onUseItem);
    }

    private static TypedActionResult<ItemStack> onUseItem(PlayerEntity player, World world, Hand hand) {
        // 判断是否是铁蛋
        if (player.getStackInHand(hand).getItem() == ModItems.iron_egg) {
            if (!world.isClient) {
                // 创建一个投掷物实体
                BaseThrownItemEntity Entity = new BaseThrownItemEntity(world, player);
                Entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 0.5F, 1.0F);
                // 将实体添加到世界中
                world.spawnEntity(Entity);
                return TypedActionResult.success(player.getStackInHand(hand));
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));
    }
}
