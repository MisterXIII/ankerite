package mrxiii.Ankerite;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import mrxiii.Ankerite.blocks.AnkeriteBlock;
import mrxiii.Ankerite.entities.AnkeriteBlockEntity;
import mrxiii.Ankerite.worldgen.Ores;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.List;

@Mod(AnkeriteMod.MODID)
public class AnkeriteMod
{
    public static final String MODID = "ankeritemod";
    // Logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // TODO: Clean up and organize Register and Registry declarations. A LOT.

    // Deferred Register for Blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Deferred Register for Items
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Deferred Register for Block Entities
    public static  final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    // Deferred Register for Biome Modifier Serializers
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);

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


    // Create ResourceLocation and ResourceKey for Ankerite Ore Generation configuration json file
    private static final ResourceLocation ANKERITE_ORE_GEN_RL = new ResourceLocation(MODID, "ankerite_ore");
    private static final ResourceKey<PlacedFeature> ANKERITE_ORE_GEN_KEY = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, ANKERITE_ORE_GEN_RL);


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

    private void onGatherData(GatherDataEvent event)
    {
        // Extract some basic required elements to make and read our json files
        final DataGenerator gen = event.getGenerator();
        final ExistingFileHelper helper = event.getExistingFileHelper();
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());

        // This allows us to give a proper registry key directory
        final ResourceKey<ConfiguredFeature<?,?>> ankeriteFeatureKey =
                Ores.ANKERITE_ORE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get();

        // Need to obtain the Ankerite Feature Holder from Registry ops as static holder in Ores won't work
        final Holder<ConfiguredFeature<?, ?>> ankeriteFeatureHolder = ops.registry(Registry.CONFIGURED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(ankeriteFeatureKey);

        final PlacedFeature ankeriteFeature = new PlacedFeature(ankeriteFeatureHolder, List.of(CountPlacement.of(1), BiomeFilter.biome()));

        final HolderSet.Named<Biome> overworldTag = new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD);

        final BiomeModifier addAnkeriteFeature =  new ForgeBiomeModifiers.AddFeaturesBiomeModifier(overworldTag,
                HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, ANKERITE_ORE_GEN_KEY.location()))), GenerationStep.Decoration.UNDERGROUND_ORES);


    }




}
