package mrxiii.Ankerite;

import com.mojang.logging.LogUtils;
import mrxiii.Ankerite.blocks.AnkeriteBlock;
import mrxiii.Ankerite.entities.AnkeriteBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(AnkeriteMod.MODID)
public class AnkeriteMod
{
    public static final String MODID = "ankeritemod";
    // Logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Deferred Register for Blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Deferred Register for Items
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    //Deferred Register for Block Entities
    public static  final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    // Creates Ankerite Block
    public static final RegistryObject<Block> ANKERITE_BLOCK = BLOCKS.register("ankerite_block", () -> new AnkeriteBlock(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates item for the block
    public static final RegistryObject<Item> ANKERITE_BLOCK_ITEM = ITEMS.register("ankerite_block_item", () -> new BlockItem(ANKERITE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    // Creates Ankerite Ore
    public static final RegistryObject<Block> ANKERITE_ORE = BLOCKS.register("ankerite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates item for tha block
    public static final RegistryObject<Item> ANKERITE_ORE_ITEM = ITEMS.register("ankerite_ore_item", () -> new BlockItem(ANKERITE_ORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    // Creates item Ankerite
    public static final RegistryObject<Item> ANKERITE = ITEMS.register("ankerite", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));


    // Create a BlockEntityType for the Ankerite Block
    // Each instance of a block entity will store information about the area of effect for the Ankerite blocks
    // so it is easier to
  public static final RegistryObject<BlockEntityType<AnkeriteBlockEntity>> ANKERITE_BLOCK_ENTITY = BLOCK_ENTITY_TYPE.register("ankerite_block_entity", () -> BlockEntityType.Builder.of(AnkeriteBlockEntity::new, ANKERITE_BLOCK.get()).build(null));

    public AnkeriteMod()
    {
        // Get the mod event bus to so the registries can catch events to registering themselves to the game
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);

        // Register the Deferred Register to the mod event bus so the block entity types get registered
        BLOCK_ENTITY_TYPE.register(modEventBus);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Mod registered");
    }




}
