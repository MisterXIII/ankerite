package mrxiii.Ankerite.loot;

import mrxiii.Ankerite.blocks.BlockRegister;
import mrxiii.Ankerite.items.ItemRegister;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

/**
 * Handles adding to the loot table, so block drops are registered
 *
 * @author MisterXIII
 */
public class Loot extends BlockLoot {

    /**
     * Adds the block drops
     */
    @Override
    protected void addTables() {
        // Using predefined methods to help add loot for blocks
        dropOther(BlockRegister.ANKERITE_ORE.get(), ItemRegister.ANKERITE.get());
        dropOther(BlockRegister.DEEPSLATE_ANKERITE_ORE.get(), ItemRegister.ANKERITE.get());
        dropSelf(BlockRegister.ANKERITE_BLOCK.get());
    }

    /**
     * Get all blocks registered by the mod
     * @return A list of blocks registered, by the mod
     */
    @Override
    protected Iterable<Block> getKnownBlocks() {
        // Give all the blocks created by Ankerite Mod
        return BlockRegister.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
