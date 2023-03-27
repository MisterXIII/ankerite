package mrxiii.Ankerite.entities;

import mrxiii.Ankerite.blocks.BlockRegister;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mrxiii.Ankerite.AnkeriteMod.MODID;

/**
 * This handles the creation and registration of Block Entities
 *
 * @author MisterXIII
 */
public class BlockEntityRegister {
    /**
     * Deferred Register for Block Entity Types
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    /**
     * Create a BlockEntityType for the Ankerite Block
     * Each instance of a block entity will store information about the area of effect for the Ankerite blocks,
     * so it is easier to
     */
    public static final RegistryObject<BlockEntityType<AnkeriteBlockEntity>> ANKERITE_BLOCK_ENTITY = BLOCK_ENTITY_TYPE.register("ankerite_block_entity", () -> BlockEntityType.Builder.of(AnkeriteBlockEntity::new, BlockRegister.ANKERITE_BLOCK.get()).build(null));

}
