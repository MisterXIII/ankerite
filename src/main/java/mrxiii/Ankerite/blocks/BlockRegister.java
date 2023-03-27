package mrxiii.Ankerite.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mrxiii.Ankerite.AnkeriteMod.MODID;

/**
 * Handles block creation and registration
 * @author MisterXIII
 */
public class BlockRegister {
    /**
     * Register for all blocks of the mod
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    /**
     * Create Ankerite Block
     */
    public static final RegistryObject<Block> ANKERITE_BLOCK = BLOCKS.register("ankerite_block", () -> new AnkeriteBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F)));

    /**
     * Create Ankerite Ore
     */
    public static final RegistryObject<Block> ANKERITE_ORE = BLOCKS.register("ankerite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F)));

    /**
     * Create Deepslate Ankerite Ore
     */
    public static final RegistryObject<Block> DEEPSLATE_ANKERITE_ORE = BLOCKS.register("deepslate_ankerite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F)));

}
