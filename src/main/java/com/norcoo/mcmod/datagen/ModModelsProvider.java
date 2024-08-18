package com.norcoo.mcmod.datagen;

import com.norcoo.mcmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TC, Models.GENERATED);
        itemModelGenerator.register(ModItems.GZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.T, Models.GENERATED);
        itemModelGenerator.register(ModItems.ZT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DAME, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TC2, Models.GENERATED);
        itemModelGenerator.register(ModItems.TD, Models.GENERATED);

    }
}
