package mrxiii.Ankerite;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import mrxiii.Ankerite.blocks.BlockRegister;
import mrxiii.Ankerite.entities.BlockEntityRegister;
import mrxiii.Ankerite.items.ItemRegister;
import mrxiii.Ankerite.loot.LootProvider;
import mrxiii.Ankerite.tags.TagsProvider;
import mrxiii.Ankerite.worldgen.OreConfigurations;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;


@Mod(AnkeriteMod.MODID)
public class AnkeriteMod
{
    public static final String MODID = "ankeritemod";
    // Logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // TODO: Clean up and organize Register and Registry declarations. A LOT.


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

        // Register the Deferred Register to the mod event bus so configured features get registered
        OreConfigurations.CONFIGURED_FEATURES.register(modEventBus);

        // Register the Deferred Register to the mod event bus so the block entity types get registered
        BlockEntityRegister.BLOCK_ENTITY_TYPE.register(modEventBus);

        modEventBus.addListener(this::onGatherData);






        LOGGER.info("Mod registered");
    }

    public void onGatherData(GatherDataEvent event)
    {
        // Extract some basic required elements to make and read our json files
        final DataGenerator gen = event.getGenerator();
        final ExistingFileHelper helper = event.getExistingFileHelper();
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
        final ResourceKey<ConfiguredFeature<?,?>> configuredFeatureKey = OreConfigurations.ORE_ANKERITE_FEATURE.getHolder().get().unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get();

        // When referring to holders, they must be referred from the Jsonops registry as it contains info not provided in the static holder and does some registry related stuff
        final Holder<ConfiguredFeature<?,?>> configuredFeatureHolder = ops.registry(Registry.CONFIGURED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(configuredFeatureKey);

        // Information about the frequency of Ankerite veins throughout the caves
        PlacedFeature added =  new PlacedFeature(configuredFeatureHolder, List.of(CountPlacement.of(30), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(192)), BiomeFilter.biome()));

        // Ankerite resource location and resource key used by forge to make appropriate files and store them in an appropriate file directory where forge will pick it up later
        ResourceLocation ankerite_rl = new ResourceLocation(MODID, "ore_ankerite_placed");
        ResourceKey<PlacedFeature> ankerite_rk = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, ankerite_rl);

        // A data provider containing information about the information to be serialized which will be added to the data generator for generation later
        JsonCodecProvider<PlacedFeature> provider = JsonCodecProvider.forDatapackRegistry(gen, helper, MODID, ops, Registry.PLACED_FEATURE_REGISTRY, Map.of(ankerite_rl, added));

        // A biome modifier that will be picked up by Biome Modifier serializers for adding ankerite ore into relevant biomes
        BiomeModifier addedAnkerite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(ankerite_rk)),
                GenerationStep.Decoration.UNDERGROUND_ORES);

        // Resource location with information about file name info will be serialized into
        ResourceLocation ADDED_ANKERITE = new ResourceLocation(MODID, "add_ankerite");
        JsonCodecProvider<BiomeModifier> bprovider = JsonCodecProvider.forDatapackRegistry(gen, helper, MODID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS, Map.of(ADDED_ANKERITE, addedAnkerite));


        gen.addProvider(event.includeServer(), provider);
        gen.addProvider(event.includeServer(), bprovider);
        // Register recipes serializers
        gen.addProvider(event.includeServer(), new Recipes(gen));
        // Register loot serializers
        gen.addProvider(event.includeServer(), new LootProvider(gen));
        // Register Block Tags
        gen.addProvider(event.includeServer(), new TagsProvider(gen, MODID, helper));



    }



}
