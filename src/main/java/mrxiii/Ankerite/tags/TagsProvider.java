package mrxiii.Ankerite.tags;

import mrxiii.Ankerite.blocks.BlockRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class TagsProvider extends IntrinsicHolderTagsProvider<Block> {


    /**
     * Instance of Tags Provider for mod
     * @param packOutput Pack Output with information about output
     * @param completableFuture Holder Lookup Provider
     * @param modId Mod ID
     * @param existingFileHelper Existing File Helper
     */
    public TagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BLOCK, completableFuture, block -> ForgeRegistries.BLOCKS.getResourceKey(block).get(), modId, existingFileHelper);
    }

    /**
     * Adds tags to blocks
     * @param provider Holder Lookup Provider
     */
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Make these blocks only mineable with a pickaxe
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockRegister.ANKERITE_BLOCK.get(),
                BlockRegister.ANKERITE_ORE.get(),
                BlockRegister.DEEPSLATE_ANKERITE_ORE.get());

        // Make these blocks require iron level tool or better
        tag(BlockTags.NEEDS_IRON_TOOL).add(
                BlockRegister.ANKERITE_BLOCK.get(),
                BlockRegister.ANKERITE_ORE.get(),
                BlockRegister.DEEPSLATE_ANKERITE_ORE.get());
    }
}