package mrxiii.ankerite.blocks;

import mrxiii.ankerite.AnkeriteObjectHolder;
import mrxiii.ankerite.entities.tileentities.TileEntityAnkerite;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAnkeriteOre extends Block implements ITileEntityProvider{
    private AxisAlignedBB regenBB;

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

    // I'm using a TileEntity because I want the Ankerite Ores to each emit a regeneration aura
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAnkerite();
    }
}
