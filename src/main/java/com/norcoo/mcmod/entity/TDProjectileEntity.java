package com.norcoo.mcmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TDProjectileEntity extends ThrownEntity {
    private ItemStack itemStack;  // 用于保存物品信息

    public TDProjectileEntity(EntityType<? extends TDProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.itemStack = ItemStack.EMPTY;  // 默认初始化为空物品
    }

    public TDProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.TD_PROJECTILE, owner, world);
        this.itemStack = ItemStack.EMPTY;
    }

    // 自定义 setItem 方法
    public void setItem(ItemStack stack) {
        this.itemStack = stack.copy();  // 保存物品副本
    }

    // 获取物品的方法（如果需要）
    public ItemStack getItem() {
        return this.itemStack;
    }

//    @Override
//    protected void onCollision(HitResult hitResult) {
//        super.onCollision(hitResult);
//
//        if (!this.getWorld().isClient) {
//            if (hitResult instanceof EntityHitResult entityHitResult) {
//                // 使用自定义的 DamageSource
//                DamageSource damageSource = new DamageSource(Identifier.of(MCmod.MOD_ID, "td_projectile"), this, this.getOwner());
//
//                // 对命中的生物造成 5 点伤害
//                entityHitResult.getEntity().damage(damageSource, 5.0f);
//            }
//            this.discard(); // 删除投掷物
//        }
//    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }
}
