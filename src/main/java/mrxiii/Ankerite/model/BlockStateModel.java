package mrxiii.Ankerite.model;

import mrxiii.Ankerite.blocks.BlockRegister;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateModel extends BlockStateProvider {
    public BlockStateModel(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(BlockRegister.ANKERITE_BLOCK.get(), cubeAll(BlockRegister.ANKERITE_BLOCK.get()));
        simpleBlockWithItem(BlockRegister.ANKERITE_ORE.get(), cubeAll(BlockRegister.ANKERITE_ORE.get()));
        simpleBlockWithItem(BlockRegister.DEEPSLATE_ANKERITE_ORE.get(), cubeAll(BlockRegister.DEEPSLATE_ANKERITE_ORE.get()));
    }
}
