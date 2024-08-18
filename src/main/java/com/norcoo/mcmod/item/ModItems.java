package com.norcoo.mcmod.item;

import com.norcoo.mcmod.MCmod;

import com.norcoo.mcmod.entity.TDProjectileEntity;
import com.norcoo.mcmod.material.IceMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ModItems {
    public static final Item ICE = registerItems("ice",
            new SwordItem(IceMaterial.ICE,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(
                            IceMaterial.ICE, 3,-2.0f))));
    public static final Item CHEESE = registerItems("cheese",
            new Item(new Item.Settings().food(ModFoodComponents.T)));

    public static final Item TC = registerItems("tc",
            new Item(new Item.Settings().food(ModFoodComponents.T)));
    public static final Item GZ = registerItems("gz",
            new Item(new Item.Settings().food(ModFoodComponents.T)));
    public static final Item T = registerItems("t",
            new Item(new Item.Settings().food(ModFoodComponents.T)));

    public static final Item ZT = registerItems("zt",
            new Item(new Item.Settings().food(ModFoodComponents.BRICK)));

    public static final Item DAME = registerItems("dame",
        new Item(new Item.Settings().food(ModFoodComponents.DAME)));

    public static final Item SNAKE = registerItems("snake",
            new Item(new Item.Settings()));

    public static final Item TC2 = registerItems("tc2",
            new Item(new Item.Settings()));



    private static Item registerItems(String id, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(MCmod.MOD_ID,id),item);
    }

    public static void registerModItems(){
        MCmod.LOGGER.info("Registering Items");
    }

}
