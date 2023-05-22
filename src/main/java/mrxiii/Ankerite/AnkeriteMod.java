package mrxiii.Ankerite;

import com.mojang.logging.LogUtils;
import mrxiii.Ankerite.blocks.BlockRegister;
import mrxiii.Ankerite.entities.BlockEntityRegister;
import mrxiii.Ankerite.items.ItemRegister;
import mrxiii.Ankerite.lang.Lang;
import mrxiii.Ankerite.loot.LootProvider;
import mrxiii.Ankerite.model.BlockStateModel;
import mrxiii.Ankerite.model.ItemModel;
import mrxiii.Ankerite.tags.TagsProvider;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Handles main setup, for Ankerite Mod.
 * Adds Ankerite to the game, a block that can heal you within a 6 block radius
 *
 * @author MisterXIII
 */
@Mod(AnkeriteMod.MODID)
public class AnkeriteMod
{
    /**
     * Mod ID
     */
    public static final String MODID = "ankerite";
    /**
     * Logger
     */
    private static final Logger LOGGER = LogUtils.getLogger();


    /**
     * Creates an instance of the mod
     */
    public AnkeriteMod()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Get the mod event bus to so the registries can catch events to registering themselves to the game
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // Register the Deferred Register to the mod event bus so blocks get registered
        BlockRegister.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
       ItemRegister.ITEMS.register(modEventBus);


        // Register the Deferred Register to the mod event bus so the block entity types get registered
        BlockEntityRegister.BLOCK_ENTITY_TYPE.register(modEventBus);
        
        // Add data generator to Event Bus
        modEventBus.addListener(this::onGatherData);

        // Add creative tab adder to Event Bus
        modEventBus.addListener(this::addCreative);






        LOGGER.info("Mod registered");
    }

    /**
     * Registers data generator, which creates the json files during the GenData run task
     * @param event
     */
    public void onGatherData(GatherDataEvent event)
    {

        CompletableFuture<HolderLookup.Provider> holderLookup = event.getLookupProvider();
        PackOutput packOutput = event.getGenerator().getPackOutput();


        // Extract some basic required elements to make and read our json files
        final DataGenerator gen = event.getGenerator();
        final ExistingFileHelper helper = event.getExistingFileHelper();

        RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, this::configureAnkerite)
                .add(Registries.PLACED_FEATURE, this::placeAnkerite)
                .add(ForgeRegistries.Keys.BIOME_MODIFIERS, this::serialize);


        // Resource location with information about file name info will be serialized into
        ResourceLocation ADDED_ANKERITE = new ResourceLocation(MODID, "add_ankerite");


        // Register recipes serializers
        gen.addProvider(event.includeServer(), new Recipes(packOutput));
        // Register loot serializers
        gen.addProvider(event.includeServer(), new LootProvider(packOutput));

        gen.addProvider(event.includeServer(), (DataProvider.Factory<DatapackBuiltinEntriesProvider>) output -> new DatapackBuiltinEntriesProvider(output, holderLookup, builder, Set.of(MODID)));
        // Register Block Tags
        gen.addProvider(event.includeServer(), new TagsProvider(packOutput, holderLookup, MODID, helper));
        gen.addProvider(event.includeServer(), new ItemModel(packOutput, MODID, helper));
   //     gen.addProvider(event.includeServer(), new BlockModel(packOutput, MODID, helper));
        gen.addProvider(event.includeServer(), new BlockStateModel(packOutput, MODID, helper));
        gen.addProvider(event.includeServer(), new Lang(packOutput, MODID, "en_us"));


    }

    /**
     * Register Ankerite Vein to data generator
     * @param context Context for Configured Feature
     */
    public void configureAnkerite(BootstapContext<ConfiguredFeature<?,?>> context)
    {
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MODID, "ore_ankerite")), new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockRegister.ANKERITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockRegister.DEEPSLATE_ANKERITE_ORE.get().defaultBlockState())), 4)));
    }

    /**
     * Adds Ankerite Placed Feature to data generator
     * @param context Context for Placed Feature
     */
    public void placeAnkerite(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MODID, "ore_ankerite_placed")), new PlacedFeature(configuredFeatures.getOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MODID, "ore_ankerite"))), List.of(CountPlacement.of(5), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(104)), BiomeFilter.biome())));
    }

    /**
     * Adds serializer for biome modifier to data generator
     * @param context Context for Biome Modifier
     */
    public void serialize(BootstapContext<BiomeModifier> context)
    {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        // Ankerite resource location and resource key used by forge to make appropriate files and store them in an appropriate file directory where forge will pick it up later
        ResourceLocation ankerite_rl = new ResourceLocation(MODID, "ore_ankerite_placed");
        ResourceKey<PlacedFeature> ankerite_rk = ResourceKey.create(Registries.PLACED_FEATURE, ankerite_rl);

        BiomeModifier addedAnkerite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(ankerite_rk)), GenerationStep.Decoration.UNDERGROUND_ORES);

        context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(MODID, "add_ankerite")), addedAnkerite);


    }

    /**
     * Adds mod items to creative tabs
     * @param event Event fired when building contents of creative tab
     */
    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if(event.getTab() == CreativeModeTabs.BUILDING_BLOCKS)
        {
            event.accept(ItemRegister.ANKERITE_BLOCK_ITEM);
            event.accept(ItemRegister.DEEPSLATE_ANKERITE_ORE_ITEM);
            event.accept(ItemRegister.ANKERITE_ORE_ITEM);
        }

        if(event.getTab() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(ItemRegister.ANKERITE);
        }
    }





}
