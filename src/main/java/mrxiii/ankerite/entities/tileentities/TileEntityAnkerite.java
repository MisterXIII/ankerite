package mrxiii.ankerite.entities.tileentities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class TileEntityAnkerite extends TileEntity
{
    private AxisAlignedBB regenBB;

    @Override
    public void updateEntity() {
        if (regenBB == null)
        {
            regenBB = AxisAlignedBB.getBoundingBox(xCoord - 3, yCoord - 3, zCoord - 3, xCoord + 3, yCoord + 3, zCoord + 3);
        }
        List<EntityLivingBase> affected = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, regenBB);
        for (EntityLivingBase e: affected) {
            e.addPotionEffect(new PotionEffect(10, 300, 1, false));
        }
    }

}
