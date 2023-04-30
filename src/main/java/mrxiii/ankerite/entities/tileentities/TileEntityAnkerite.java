package mrxiii.ankerite.entities.tileentities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * The Ankerite TileEntity. Used to apply regeneration to nearby entities.
 * @author MrXIII
 */
public class TileEntityAnkerite extends TileEntity
{

    /**
     * The bounding box where all entities will have the regeneration effect applied.
     */
    private AxisAlignedBB regenBB;

    /**
     * Called every tick to apply the regeneration effect to nearby entities.
     */
    @Override
    public void updateEntity() {

        // If the bounding box is not instantiated, create it.
        if (regenBB == null)
        {
            regenBB = AxisAlignedBB.getBoundingBox(xCoord - 5, yCoord - 5, zCoord - 5, xCoord + 5, yCoord + 5, zCoord + 5);
        }

        // Get all entities within the bounding box.
        List<EntityLivingBase> affected = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, regenBB);

        // Apply the regeneration effect to all entities.
        for (EntityLivingBase e: affected) {
            if(!e.isPotionActive(10) || (e.getActivePotionEffect(Potion.regeneration).getDuration() < 10)) {
                e.addPotionEffect(new PotionEffect(10, 80, 0, false));
            }
        }
    }

}
