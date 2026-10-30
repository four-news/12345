package com.example.sixtyseven;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

public class SixtySevenMod implements ModInitializer {
	public static final String MOD_ID = "sixtyseven";

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItemGroups.initialize();
	}
}
