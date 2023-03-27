package mrxiii.Ankerite.blocks;

import mrxiii.Ankerite.entities.AnkeriteBlockEntity;
import mrxiii.Ankerite.entities.BlockEntityRegister;
import net.minecraft.core.BlockPos;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/**
 * Ankerite Block, which is crafted from Ankerite
 * @author MisterXIII
 */
public class AnkeriteBlock extends Block implements EntityBlock {

    /**
     * Initializes Ankerite Block which will be a block that holds a Block Entity
     * @param blockProperties Properties of block
     */
    public AnkeriteBlock(Properties blockProperties) {
        super(blockProperties);

    }

    /**
     * Provides the relevant block entity for this block
     * @param blockPos Position of block
     * @param blockState State of block
     * @return BlockEntity with given properties
     */
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AnkeriteBlockEntity(blockPos, blockState);

    }


    /**
     * Since this block entity will have to perform actions at every tick, override the getTicker method so
     * it uses the tick method we want
     * @param level World object
     * @param blockState State of block
     * @param blockEntityType Type of block entity
     * @return Object which runs for the block entity at every tick
     * @param <T> Type of BlockEntity
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        // Even after testing, not entirely sure when this condition would be false, but it's safer
        // not to deviate from the documentation
        return BlockEntityRegister.ANKERITE_BLOCK_ENTITY.get() == blockEntityType ? AnkeriteBlockEntity::tick: null;
    }

}
