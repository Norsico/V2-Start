package com.norcoo.mcmod.item;

import com.norcoo.mcmod.MCmod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final RegistryKey<ItemGroup> ICE_GROUP = register("ice");
    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MCmod.MOD_ID,id));
    }
    public static void registerModItemGroups(){
        Registry.register(Registries.ITEM_GROUP, ICE_GROUP,
                ItemGroup.create(ItemGroup.Row.TOP,7)
                        .displayName(Text.translatable("itemGroup.ice_group"))
                        .icon(()->new ItemStack(ModItems.ICE))
                        .entries((displayContext, entries)->{
                            // 在下面添加物品
                            entries.add(ModItems.ICE);
                            entries.add(ModItems.CHEESE);

                        }).build());
        MCmod.LOGGER.info("Registering Mod Item Groups");

    }
}
