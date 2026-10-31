package glory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GloryMod implements ModInitializer {
    public static final String MOD_ID = "glory";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Block GLORY_BLOCK = new GloryBlock(
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(3.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
    );

    public static final BlockItem GLORY_BLOCK_ITEM = new BlockItem(GLORY_BLOCK, new Item.Settings());

    public static final SoundEvent GLORY_SOUND = SoundEvent.of(new Identifier(MOD_ID, "glory_song"));

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "glory_block"), GLORY_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "glory_block"), GLORY_BLOCK_ITEM);
        Registry.register(Registries.SOUND_EVENT, new Identifier(MOD_ID, "glory_song"), GLORY_SOUND);

        ItemGroup group = FabricItemGroup.builder()
                .icon(() -> new ItemStack(GLORY_BLOCK_ITEM))
                .displayName(Text.translatable("itemGroup.glory.glory"))
                .build();
        RegistryKey<ItemGroup> groupKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "glory"));
        Registry.register(Registries.ITEM_GROUP, groupKey, group);
        ItemGroupEvents.modifyEntriesEvent(groupKey).register(entries -> entries.add(GLORY_BLOCK_ITEM));

        LOGGER.info("[Glory] 光辉块 mod 已加载，可用拉杆激活播放《光辉岁月》。");
    }
}