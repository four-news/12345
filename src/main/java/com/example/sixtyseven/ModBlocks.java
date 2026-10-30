package com.example.sixtyseven;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
	public static final BlockItemId SIXTY_SEVEN_TEST_BLOCK_ID = create("sixty_seven_test_block");

	public static final Block SIXTY_SEVEN_TEST_BLOCK = register(
			SIXTY_SEVEN_TEST_BLOCK_ID,
			Block::new,
			BlockBehaviour.Properties.of().strength(1.5F, 6.0F)
	);

	private static BlockItemId create(String name) {
		Identifier id = SixtySevenMod.id(name);
		return BlockItemId.create(id, id);
	}

	private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		// Create the block instance
		Block block = blockFactory.apply(properties.setId(id.block()));
		Registry.register(BuiltInRegistries.BLOCK, id.block(), block);

		// Create the block item instance
		BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
		Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);
		return block;
	}

	public static void initialize() {
	}
}
