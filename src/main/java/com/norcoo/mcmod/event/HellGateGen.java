package com.norcoo.mcmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HellGateGen {

    public static void register() {
        UseBlockCallback.EVENT.register(HellGateGen::onBlockUse);
    }

    private static ActionResult onBlockUse(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        BlockPos clickedPos = blockHitResult.getBlockPos();
        BlockPos firePos = clickedPos.up();

        // 创建一个新的计数器和检测逻辑
        final int[] ticksPassed = {0};
        final int maxTicks = 5; // 最多检测 5 游戏刻

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ticksPassed[0]++;
            if (ticksPassed[0] > maxTicks) {
                // 停止检测
                return;
            }

            BlockState state = world.getBlockState(firePos);

            if (state.getBlock() == Blocks.FIRE) {
                System.out.println("Fire detected at " + firePos);
                System.out.println(state.getBlock());

                // 获取火焰方块上下左右的位置
                BlockPos[] obsidianPositions = new BlockPos[]{
                        firePos.up(), firePos.down(), firePos.north(), firePos.south(), firePos.east(), firePos.west()
                };

                // 计数黑曜石的数量
                int obsidianCount = 0;
                for (BlockPos obsidianPos : obsidianPositions) {
                    if (world.getBlockState(obsidianPos).getBlock() == Blocks.OBSIDIAN) {
                        obsidianCount++;
                    }
                }

                if (obsidianCount >= 4){
                    // 简单地生成一个地狱门
                    world.setBlockState(firePos, Blocks.NETHER_PORTAL.getDefaultState(), 3);
                    playerEntity.playSound(SoundEvents.BLOCK_PORTAL_TRIGGER, 1.0F, 1.0F);
                }

                // 停止进一步的检测
                return;
            }
        });

        return ActionResult.PASS;
    }

}
