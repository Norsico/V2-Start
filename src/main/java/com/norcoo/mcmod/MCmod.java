package com.norcoo.mcmod;

import com.norcoo.mcmod.event.CowSEX;
import com.norcoo.mcmod.event.TianCaiEvent;
import com.norcoo.mcmod.item.ModItemGroups;
import com.norcoo.mcmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MCmod implements ModInitializer {

	public static final String MOD_ID = "mcmod";  // 确保这是你在注册物品时使用的 MOD_ID
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		TianCaiEvent.register();
		CowSEX.register();

//		ModEntities.registerEntities();
	}
}
