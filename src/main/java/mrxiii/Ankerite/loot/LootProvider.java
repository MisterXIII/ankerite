package mrxiii.Ankerite.loot;

import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Handles modification to the loot table
 *
 * @author MisterXIII
 */
public class LootProvider extends LootTableProvider {
    /**
     * Creates an instance of the Loot Table Provider for this mod
     * @param dataGenerator Data generator of the initialization
     */
    public LootProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }


    /**
     * This method needs to be overridden so Mojang's special loot validation process doesn't run again
     * @param map
     * @param validationtracker
     */
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }


    /**
     * @return All created loot tables
     */
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return List.of(
                Pair.of(Loot::new, LootContextParamSets.BLOCK)
        );
    }
}
