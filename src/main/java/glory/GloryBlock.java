package glory;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GloryBlock extends Block {
    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public GloryBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        // 只在服务端处理并播放声音
        if (world.isClient) {
            return;
        }

        boolean powered = world.isReceivingRedstonePower(pos);
        if (powered && !state.get(POWERED)) {
            world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_LISTENERS);
            world.playSound(null, pos, GloryMod.GLORY_SOUND, SoundCategory.RECORDS, 1.0f, 1.0f);
        } else if (!powered && state.get(POWERED)) {
            world.setBlockState(pos, state.with(POWERED, false), Block.NOTIFY_LISTENERS);
        }
    }
}