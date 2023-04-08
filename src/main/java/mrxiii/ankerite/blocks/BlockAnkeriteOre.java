package mrxiii.ankerite.blocks;

import mrxiii.ankerite.AnkeriteObjectHolder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockAnkeriteOre extends Block{

    public BlockAnkeriteOre() {
        super(Material.rock);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return AnkeriteObjectHolder.ankerite;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(2) + 1;
    }
}
