package mrxiii.Ankerite.loot;

import mrxiii.Ankerite.AnkeriteMod;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class Loot extends BlockLoot {
    @Override
    protected void addTables() {
        dropOther(AnkeriteMod.ANKERITE_ORE.get(), AnkeriteMod.ANKERITE.get());
        dropSelf(AnkeriteMod.ANKERITE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AnkeriteMod.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
