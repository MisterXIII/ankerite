package mrxiii.Ankerite.loot;

import mrxiii.Ankerite.blocks.BlockRegister;
import mrxiii.Ankerite.items.ItemRegister;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class Loot extends BlockLoot {
    @Override
    protected void addTables() {
        // Using predefined methods to help add loot for blocks
        dropOther(BlockRegister.ANKERITE_ORE.get(), ItemRegister.ANKERITE.get());
        dropOther(BlockRegister.DEEPSLATE_ANKERITE_ORE.get(), ItemRegister.ANKERITE.get());
        dropSelf(BlockRegister.ANKERITE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // Give all the blocks created by Ankerite Mod
        return BlockRegister.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
