package mrxiii.ankerite.blocks;

import mrxiii.ankerite.entities.tileentities.TileEntityAnkerite;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAnkerite extends Block implements ITileEntityProvider {
    public BlockAnkerite() {
        super(Material.glass);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAnkerite();
    }


}
