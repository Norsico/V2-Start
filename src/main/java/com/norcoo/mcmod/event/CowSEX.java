package com.norcoo.mcmod.event;

import com.norcoo.mcmod.item.ModItems;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CowSEX {

    // 注册事件处理函数
    public static void register() {
        UseEntityCallback.EVENT.register(CowSEX::onFeedAnimal);
    }

    private static ActionResult onFeedAnimal(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult entityHitResult) {
        // 检查实体是否为牛并且是否是小牛
        if (entity instanceof CowEntity cow) {
            // 获取玩家手中的物品
            ItemStack itemStack = player.getStackInHand(hand);

            // 如果玩家手持的物品是TC2
            if (itemStack.getItem() == ModItems.TC2) {

                // 吃掉一份物品
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                // 让小牛进入繁殖模式
                cow.lovePlayer(player);

                // 找到周围的另一只牛
                for (Entity nearbyEntity : world.getOtherEntities(cow, cow.getBoundingBox().expand(20.0))) {
                    if (nearbyEntity instanceof CowEntity nearbyCow && nearbyCow.isInLove()) {
                        // 让两只牛相互靠近
                        Vec3d cowPosition = nearbyCow.getPos();
                        cow.getNavigation().startMovingTo(cowPosition.x, cowPosition.y, cowPosition.z, 3.0); // 移动速度 1.0

//                        // 移除繁殖冷却时间
//                        nearbyCow.setBreedingAge(0); // 这只牛可以再次繁殖
//                        cow.setBreedingAge(0); // 当前牛也可以再次繁殖

                        break;
                    }
                }

                // 播放繁殖粒子效果
                world.addParticle(ParticleTypes.HEART, cow.getX(), cow.getBodyY(0.5), cow.getZ(), 0.0, 0.0, 0.0);
                // 播放繁殖粒子效果
                world.addParticle(ParticleTypes.HEART, cow.getX(), cow.getBodyY(0.2), cow.getZ(), 0.0, 0.0, 0.0);

                return ActionResult.SUCCESS; // 处理事件并阻止进一步处理
            }
        }
        return ActionResult.PASS; // 继续其他事件处理
    }
}
