package mrxiii.Ankerite.worldgen;

import mrxiii.Ankerite.AnkeriteMod;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OreConfigurations {
        public static DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, AnkeriteMod.MODID);

        // Ore Configuration for ankerite, specifying the blocks and its vein size
        public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_ANKERITE_FEATURE = CONFIGURED_FEATURES.register("ore_ankerite", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, AnkeriteMod.ANKERITE_ORE.get().defaultBlockState(), 12)));


}
