package mrxiii.Ankerite.worldgen;

import mrxiii.Ankerite.AnkeriteMod;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class Ores {
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ANKERITE_ORE = FeatureUtils.register
            ("ore_ankerite", Feature.ORE,
                    new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                            AnkeriteMod.ANKERITE_ORE.get().defaultBlockState(), 12));


}
