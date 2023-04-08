package mrxiii.ankerite.blocks;

import mrxiii.ankerite.AnkeriteObjectHolder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * The Ankerite Ore class, which is the ore that spawns in caves and drops
 * Ankerite crystals when broken.
 * @author MrXIII
 */
public class BlockAnkeriteOre extends Block{

    /**
     * The constructor for the Ankerite Ore class.
     */
    public BlockAnkeriteOre() {
        super(Material.rock);
    }

    /**
     * Specifies the item that drops when the block is broken.
     * @param i
     * @param random A randomizer.
     * @param i1
     * @return An instance of an item.
     */
    @Override
    public Item getItemDropped(int i, Random random, int i1) {
        return AnkeriteObjectHolder.ankerite;
    }

    /**
     * Specifies the quantity of Ankerite that will drop when the block is broken.
     * @param rand A randomizer.
     * @return The quantity of items that drop.
     */
    @Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(2) + 1;
    }
}
