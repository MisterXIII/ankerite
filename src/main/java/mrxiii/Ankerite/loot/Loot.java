package mrxiii.Ankerite.loot;

import mrxiii.Ankerite.blocks.BlockRegister;
import mrxiii.Ankerite.items.ItemRegister;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

/**
 * Handles adding to the loot table, so block drops are registered
 *
 * @author MisterXIII
 */
public class Loot extends BlockLootSubProvider {

    protected Loot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    /**
     * Adds the block drops
     */
    @Override
    protected void generate() {
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
