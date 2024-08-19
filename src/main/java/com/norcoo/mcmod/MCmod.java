package com.norcoo.mcmod;

import com.norcoo.mcmod.event.IronEgg;
import com.norcoo.mcmod.event.TianCaiEvent;
import com.norcoo.mcmod.item.ModItemGroups;
import com.norcoo.mcmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.norcoo.mcmod.entity.BaseThrowEntityType;

public class MCmod implements ModInitializer {

	public static final String MOD_ID = "mcmod";  // 确保这是你在注册物品时使用的 MOD_ID
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
//		ModItems.registerModItems();
//		ModItemGroups.registerModItemGroups();
//		TianCaiEvent.register();
//		IronEgg.register();
//		// 注册实体
//		BaseThrowEntityType.registerModEntities(); // 确保在这里调用
//		EntityRendererRegistry.register(BaseThrowEntityType.iron_egg, FlyingItemEntityRenderer::new);

	}
}
