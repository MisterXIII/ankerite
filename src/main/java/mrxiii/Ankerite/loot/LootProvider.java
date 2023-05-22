package mrxiii.Ankerite.loot;

import com.mojang.datafixers.util.Pair;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Handles modification to the loot table
 *
 * @author MisterXIII
 */
public class LootProvider extends LootTableProvider {
    /**
     * Creates an instance of the Loot Table Provider for this mod
     * @param output Data generator of the initialization
     */
    public LootProvider(PackOutput output) {
        super(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(Loot::new, LootContextParamSets.BLOCK)));
    }


    /**
     * This method needs to be overridden so Mojang's special loot validation process doesn't run again
     * @param map
     * @param validationtracker
     */
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }



}
