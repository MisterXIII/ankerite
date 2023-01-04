package mrxiii.Ankerite.entities;

import mrxiii.Ankerite.AnkeriteMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class AnkeriteBlockEntity extends BlockEntity {

    // Area of effect of regeneration of Ankerite Block
    AABB aoe;

    // Targeting conditions for Ankerite Block to give regeneration
    static TargetingConditions target = TargetingConditions.forNonCombat().ignoreLineOfSight();

    public AnkeriteBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(AnkeriteMod.ANKERITE_BLOCK_ENTITY.get(), p_155229_, p_155230_);

        // Initialize area of effect within a 6 block radius of Block Entity
        aoe =  this.getRenderBoundingBox().inflate(6, 6, 6);
    }

  public static void tick(Level level, BlockPos ps, BlockState state, BlockEntity b)
  {
      // Just in case the Game ticks the block entity with a different Block Entity object.
      // It shouldn't happen, but just in case.
      if(!(b instanceof AnkeriteBlockEntity))
      {
          return;
      }

      AnkeriteBlockEntity block = (AnkeriteBlockEntity)b;
      // Give all the entities a Regeneration effect
      List<LivingEntity> affected = level.getNearbyEntities(LivingEntity.class, target, null, block.aoe );
      affected.forEach(p -> {
          if(!p.hasEffect(MobEffects.REGENERATION) || p.getEffect(MobEffects.REGENERATION).getDuration() <= 10)
          {
              p.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80));
          }
      });
  }

}
