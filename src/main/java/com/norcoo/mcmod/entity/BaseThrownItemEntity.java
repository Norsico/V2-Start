package com.norcoo.mcmod.entity;

import com.norcoo.mcmod.damage.CustomDamageSource;
import com.norcoo.mcmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;

import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class BaseThrownItemEntity extends ThrownItemEntity {

    public BaseThrownItemEntity(EntityType<? extends BaseThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BaseThrownItemEntity(World world, LivingEntity owner) {
        super(BaseThrowEntityType.iron_egg, owner, world);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.iron_egg;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) this.getWorld();
            // 判断碰撞对象是否是生物
            if (hitResult instanceof EntityHitResult entityHitResult && entityHitResult.getEntity()  instanceof LivingEntity livingEntity) {
                Vec3d centerPos = this.getPos(); // 实体位置

                // 获取生物的脚下方块位置
                BlockPos blockPos = livingEntity.getBlockPos().down();
                // 计算粒子效果的位置
                BlockState blockState = this.getWorld().getBlockState(blockPos);

                double radius = 3.5; // 固定半径，生成周围的粒子

                // 获取半径内的所有生物
                List<LivingEntity> nearbyEntities = this.getWorld().getEntitiesByClass(LivingEntity.class,
                        new Box(centerPos.subtract(radius, radius, radius), centerPos.add(radius, radius, radius)),
                        entity -> entity != null && entity.isAlive() && entity != livingEntity);

                DamageSource damageSource = new DamageSource(
                        serverWorld.getRegistryManager()
                                .get(RegistryKeys.DAMAGE_TYPE)
                                .entryOf(CustomDamageSource.TATER_DAMAGE));

                // 对所有半径内的生物造成伤害
                for (LivingEntity entity : nearbyEntities) {
                    entity.damage(damageSource, 5.0F); // 10.0F 是伤害值，可以根据需要调整
                }

                // 生成周围一圈的粒子效果
                int particleCount = 300; // 固定粒子数量
                for (int j = 0; j < particleCount; j++) {
                    double angle = Math.random() * 2 * Math.PI;
                    double xOffset = radius * Math.cos(angle);
                    double zOffset = radius * Math.sin(angle);

                    // 生成周围静止的粒子（只稍微偏移位置）
                    serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                            centerPos.x + xOffset, centerPos.y, centerPos.z + zOffset,
                            1, 0.1, 0.1, 0.1, 0.05F); // 轻微运动的粒子
                }

                // 生成中间向上运动的粒子，呈现圆周分布
                int centerParticleCount = 200; // 中间粒子数量
                for (int j = 0; j < centerParticleCount; j++) {
                    // 根据距离圆心的随机半径控制生成概率，越靠近圆心生成概率越高
                    double distanceFromCenter = Math.sqrt(Math.random()) * radius; // 随机半径，靠近圆心概率高
                    double angle = Math.random() * 2 * Math.PI; // 随机角度
                    double xOffset = distanceFromCenter * Math.cos(angle); // X轴偏移
                    double zOffset = distanceFromCenter * Math.sin(angle); // Z轴偏移

                    double initialVerticalSpeed = 4.0 + Math.random() * 0.5; // 中间粒子的上升速度随机

                    // 中心位置的粒子生成，按距离圆心随机分布
                    serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                            centerPos.x + xOffset, centerPos.y, centerPos.z + zOffset,
                            1, 0, initialVerticalSpeed, 0, 1F); // 粒子上升

                }

//                // 计算粒子效果的位置
//                Vec3d vec3d = blockPos.toCenterPos().add(0.0, 0.5, 0.0);
//                int i = 200;
//                // 生成粒子效果
//                serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), vec3d.x, vec3d.y, vec3d.z, i, 0.3F, 0.3F, 0.3F, 0.15F);

            }else {
                Vec3d centerPos = this.getPos(); // 实体位置
                double radius = 3.5;
                // 获取radius内的所有生物
                List<LivingEntity> nearbyEntities = this.getWorld().getEntitiesByClass(LivingEntity.class,
                        new Box(centerPos.subtract(radius, radius, radius), centerPos.add(radius, radius, radius)),
                        entity -> entity != null && entity.isAlive());
                // 对所有半径内的生物造成伤害
                for (LivingEntity entity : nearbyEntities) {
                    entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 10.0F); // 10.0F 是伤害值，可以根据需要调整
                }
                // 判断是否存在生物
                if (!nearbyEntities.isEmpty()) {
                    // 获取其中某个生物实体
                    LivingEntity entity = nearbyEntities.get(0);
                    // 计算实体位置
                    Vec3d entityPos = entity.getPos();
                    // 获取实体脚下方块位置
                    BlockPos blockPos = entity.getBlockPos().down();
                    // 计算粒子效果的位置
                    BlockState blockState = this.getWorld().getBlockState(blockPos);
                    // 生成周围一圈的粒子效果
                    int particleCount = 300; // 固定粒子数量
                    for (int j = 0; j < particleCount; j++) {
                        double angle = Math.random() * 2 * Math.PI;
                        double xOffset = radius * Math.cos(angle);
                        double zOffset = radius * Math.sin(angle);

                        // 生成周围静止的粒子（只稍微偏移位置）
                        serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                                centerPos.x + xOffset, centerPos.y, centerPos.z + zOffset,
                                1, 0.1, 0.1, 0.1, 0.05F); // 轻微运动的粒子
                    }

                    // 生成中间向上运动的粒子，呈现圆周分布
                    int centerParticleCount = 200; // 中间粒子数量
                    for (int j = 0; j < centerParticleCount; j++) {
                        // 根据距离圆心的随机半径控制生成概率，越靠近圆心生成概率越高
                        double distanceFromCenter = Math.sqrt(Math.random()) * radius; // 随机半径，靠近圆心概率高
                        double angle = Math.random() * 2 * Math.PI; // 随机角度
                        double xOffset = distanceFromCenter * Math.cos(angle); // X轴偏移
                        double zOffset = distanceFromCenter * Math.sin(angle); // Z轴偏移

                        double initialVerticalSpeed = 4.0 + Math.random() * 0.5; // 中间粒子的上升速度随机

                        // 中心位置的粒子生成，按距离圆心随机分布
                        serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                                centerPos.x + xOffset, centerPos.y, centerPos.z + zOffset,
                                1, 0, initialVerticalSpeed, 0, 1F); // 粒子上升

                    }
                }
            }


            // 发送状态更新以显示破裂效果
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);

//            // 生成粒子效果
//            spawnExplosionParticles(serverWorld, this.getPos());

//            // 创建爆炸效果
//            this.getWorld().createExplosion(null,
//                    this.getX(), this.getY(), this.getZ(),
//                    2.0F, // 爆炸半径
//                    World.ExplosionSourceType.TNT // 爆炸来源类型
//            ); // 爆炸破坏类型

            // 从世界中移除实体
            this.discard();
        }
    }
    private void spawnExplosionParticles(ServerWorld world, Vec3d position) {
        // 在当前实体的位置生成粒子效果
        world.spawnParticles(ParticleTypes.EXPLOSION, position.x, position.y, position.z, 30, 0.5, 0.5, 0.5, 0.1);
    }


    @Override
    // 实体击中时，对实体造成伤害
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 15.0F);
    }

    @Override
    // 粒子效果: 在碰撞或命中时，在当前位置生成多个粒子，模拟破裂效果
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            double d = 0.08;
            for (int i = 0; i < 8; i++) {
                this.getWorld()
                        .addParticle(
                                new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ((double)this.random.nextFloat() - 0.5) * 0.08,
                                ((double)this.random.nextFloat() - 0.5) * 0.08,
                                ((double)this.random.nextFloat() - 0.5) * 0.08
                        );
            }
        }
    }

}
