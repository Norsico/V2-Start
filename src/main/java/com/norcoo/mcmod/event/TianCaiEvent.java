package com.norcoo.mcmod.event;

import com.norcoo.mcmod.item.ModItemGroups;
import com.norcoo.mcmod.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TianCaiEvent {

    // 存储正在下落的铁砧
    private static final Set<FallingBlockEntity> fallingAnvils = new HashSet<>();

    public static void register() {
        // 注册一个事件，用于监听所有实体的加载事件
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof FallingBlockEntity fallingBlock && fallingBlock.getBlockState().getBlock() == Blocks.ANVIL) {
                // 将正在下落的铁砧添加到集合中
                fallingAnvils.add(fallingBlock);
            }
        });

        // 注册一个服务器刻事件，检测铁砧是否停止
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world instanceof ServerWorld serverWorld) {
                checkAnvils(serverWorld);
            }
        });
    }

    // 检查铁砧是否已停止
    private static void checkAnvils(ServerWorld world) {
        // 遍历集合中的所有铁砧
        for (FallingBlockEntity fallingAnvil : fallingAnvils) {
            // 检查铁砧是否已停止
            if (fallingAnvil.isOnGround()) {
                // 获取铁砧的最终落地点
                BlockPos pos = new BlockPos((int)fallingAnvil.getX(), (int)fallingAnvil.getY(), (int)fallingAnvil.getZ());

                // 获取该位置附近的掉落物实体
                List<ItemEntity> nearbyItems = world.getEntitiesByClass(ItemEntity.class, new Box(pos)
                        .expand(10.0), itemEntity -> true);

                // 筛选出符合条件的掉落物（例如甜菜tc）
                List<ItemEntity> beetroots = nearbyItems.stream()
                        .filter(itemEntity -> itemEntity.getStack().getItem() == Items.BEEF)
                        .toList();

                if (!beetroots.isEmpty()) {
                    ItemStack ironIngot = new ItemStack(ModItems.TC2);
                    ItemEntity ironIngotEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ironIngot);
                    world.spawnEntity(ironIngotEntity);
                    // 删除所有甜菜实体
                    nearbyItems.forEach(ItemEntity::discard);
                }
                // 移除已经停止的铁砧
                fallingAnvils.remove(fallingAnvil);
            }
        }
    }
}
