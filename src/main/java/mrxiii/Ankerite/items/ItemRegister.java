package mrxiii.Ankerite.items;

import mrxiii.Ankerite.blocks.BlockRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mrxiii.Ankerite.AnkeriteMod.MODID;

/**
 * Handles the creation and registration of items
 *
 * @author MisterXIII
 */
public class ItemRegister {

    /**
     * Deferred Register for Items
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    /**
     * Creates item for Ankerite Block
     */
    public static final RegistryObject<Item> ANKERITE_BLOCK_ITEM = ITEMS.register("ankerite_block_item", () -> new BlockItem(BlockRegister.ANKERITE_BLOCK.get(), new Item.Properties()));

    /**
     * Creates item for Ankerite Ore
     */
    public static final RegistryObject<Item> ANKERITE_ORE_ITEM = ITEMS.register("ankerite_ore_item", () -> new BlockItem(BlockRegister.ANKERITE_ORE.get(), new Item.Properties()));

    /**
     * Creates item for Deepslate Ankerite Ore
     */
    public static final RegistryObject<Item> DEEPSLATE_ANKERITE_ORE_ITEM =  ITEMS.register("deepslate_ankerite_ore_item", () -> new BlockItem(BlockRegister.DEEPSLATE_ANKERITE_ORE.get(), new Item.Properties()));

    /**
     * Creates item Ankerite
     */
    public static final RegistryObject<Item> ANKERITE = ITEMS.register("ankerite", () -> new Item(new Item.Properties()));

}
