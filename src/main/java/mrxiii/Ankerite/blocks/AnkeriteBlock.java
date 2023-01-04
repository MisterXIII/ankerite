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


public class AnkeriteBlock extends Block implements EntityBlock {
// Initializes Ankerite Block which will be a block that holds a Block Entity
    public AnkeriteBlock(Properties p_49795_) {
        super(p_49795_);

    }

    // Provides the relevant block entity for this block
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new AnkeriteBlockEntity(p_153215_, p_153216_);

    }


    // Since this block entity will have to perform actions at every tick, override the getTicker method so
    // it uses the tick method we want
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        // Even after testing, not entirely sure when this condition would be false, but it's safer
        // not to deviate from the documentation
        return BlockEntityRegister.ANKERITE_BLOCK_ENTITY.get() == p_153214_ ? AnkeriteBlockEntity::tick: null;
    }

}
