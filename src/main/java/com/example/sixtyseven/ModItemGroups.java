package com.example.sixtyseven;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModItemGroups {
	public static final ResourceKey<CreativeModeTab> TEST_BLOCKS_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			SixtySevenMod.id("test_blocks")
	);

	public static final CreativeModeTab TEST_BLOCKS = FabricCreativeModeTab.builder()
			.icon(() -> new ItemStack(ModBlocks.SIXTY_SEVEN_TEST_BLOCK.asItem()))
			.title(Component.translatable("itemGroup.sixtyseven.test_blocks"))
			.displayItems((params, output) -> output.accept(ModBlocks.SIXTY_SEVEN_TEST_BLOCK))
			.build();

	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TEST_BLOCKS_KEY, TEST_BLOCKS);
	}
}
