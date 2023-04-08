package mrxiii.ankerite.entities.tileentities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
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
            regenBB = AxisAlignedBB.getBoundingBox(xCoord - 5, yCoord - 5, zCoord - 5, xCoord + 5, yCoord + 5, zCoord + 5);
        }
        List<EntityLivingBase> affected = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, regenBB);

        for (EntityLivingBase e: affected) {
            if(!e.isPotionActive(10) || (e.getActivePotionEffect(Potion.regeneration).getDuration() < 10)) {
                e.addPotionEffect(new PotionEffect(10, 80, 0, false));
            }
        }
    }

}
