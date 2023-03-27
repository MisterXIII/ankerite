package mrxiii.Ankerite.entities;

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

/**
 * Ankerite Block Entity, which is created at the position any Ankerite Block is placed in
 * the world, which would emit a regeneration aura within 6 blocks to any Entity
 *
 * @author MisterXIII
 */
public class AnkeriteBlockEntity extends BlockEntity {

    /**
     * Area of effect of regeneration of Ankerite Block
     */
    AABB aoe;

    /**
     * Targeting conditions for Ankerite Block to give regeneration
     */
    static TargetingConditions target = TargetingConditions.forNonCombat().ignoreLineOfSight();

    /**
     * Creates Ankerite Block entity with given properties
     * @param blockPos Position of block
     * @param blockState State of block
     */
    public AnkeriteBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegister.ANKERITE_BLOCK_ENTITY.get(), blockPos, blockState);

        // Initialize area of effect within a 6 block radius of Block Entity
        aoe =  this.getRenderBoundingBox().inflate(6, 6, 6);
    }

    /**
     * Called during every tick, emits regeneration effect to entities
     * that don't already have a regeneration effect
     * @param level World object
     * @param ps Position of Block Entity
     * @param state State of Block Entity
     * @param b Block Entity in question
     */
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
