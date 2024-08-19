package com.norcoo.mcmod.datagen;

import com.norcoo.mcmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENUSlanProvider extends FabricLanguageProvider {
    public ModENUSlanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput,"zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.ICE,"互摸之杖");
        translationBuilder.add(ModItems.CHEESE,"知识");
        translationBuilder.add(ModItems.TC,"甜菜根");
        translationBuilder.add(ModItems.GZ,"甘蔗");
        translationBuilder.add(ModItems.T,"糖");
        translationBuilder.add(ModItems.ZT,"砖头");
        translationBuilder.add(ModItems.DAME,"法老");
        translationBuilder.add(ModItems.SNAKE,"九头蛇");
        translationBuilder.add(ModItems.TC2,"牛麦.zip");
        translationBuilder.add(ModItems.iron_egg,"铁蛋");
    }
}
