package mrxiii.Ankerite.worldgen;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import mrxiii.Ankerite.AnkeriteMod;
import mrxiii.Ankerite.blocks.BlockRegister;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import java.util.List;
import java.util.function.Supplier;

public class OreConfigurations {
        /**
         * Deferred register for configured features
         */
        public static DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, AnkeriteMod.MODID);

        /**
         * Target blocks for Anekrite Ore and their respective replacements
         * Value needs to be cached first, else invocation exception is thrown during data task
         * Value seems to change somehow
         */
        public static Supplier<List<OreConfiguration.TargetBlockState>> ANKERITE_REPLACEABLES = Suppliers.memoize(() -> List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegister.ANKERITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegister.DEEPSLATE_ANKERITE_ORE.get().defaultBlockState())
        ));

        /**
         * Ore Configuration for ankerite, specifying the blocks and its vein size
         */
        public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_ANKERITE_FEATURE = CONFIGURED_FEATURES.register("ore_ankerite", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ANKERITE_REPLACEABLES.get(), 4)));


}
