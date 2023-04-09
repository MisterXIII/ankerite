package mrxiii.ankerite.blocks;

import mrxiii.ankerite.entities.tileentities.TileEntityAnkerite;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * The Ankerite Block class, which is the block crafted from 9 Ankerite crystals.
 * @author MrXIII
 */
public class BlockAnkerite extends Block implements ITileEntityProvider {
    /**
     * The constructor for the Ankerite Block class. Sets the mining level to iron.
     */
    public BlockAnkerite() {
        super(Material.glass);
        setHarvestLevel("pickaxe", 2);
    }

    /**
     * Specifies the TileEntity that spawns into the world when the block is placed.
     * @param world The world the block is placed in.
     * @param i
     * @return An instance of a tile entity.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityAnkerite();
    }


}
