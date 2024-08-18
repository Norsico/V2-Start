package com.norcoo.mcmod.datagen;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class AnvilLandEvent {

    private static final Map<FallingBlockEntity, BlockPos> fallingAnvils = new HashMap<>();

    public static void register() {
        // 注册服务器每tick事件
        ServerTickEvents.END_SERVER_TICK.register(server -> onServerTick(server.getOverworld()));
    }

    // 服务器每tick执行该方法
    private static void onServerTick(World world) {
        fallingAnvils.entrySet().removeIf(entry -> {
            FallingBlockEntity anvil = entry.getKey();
            BlockPos lastPos = entry.getValue();

            // 检查铁砧是否已经落地
            if (anvil.isOnGround() || world.getBlockState(anvil.getBlockPos()).getBlock() == Blocks.ANVIL) {
                System.out.println("铁砧落地，最终坐标: " + lastPos);
                return true; // 从map中移除该铁砧
            } else {
                entry.setValue(anvil.getBlockPos()); // 更新铁砧位置
                return false; // 保持该铁砧在map中
            }
        });
    }

    public static void onAnvilFall(FallingBlockEntity anvil) {
        if (anvil.getBlockState().getBlock() == Blocks.ANVIL) {
            fallingAnvils.put(anvil, anvil.getBlockPos());
        }
    }
}