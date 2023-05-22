package mrxiii.Ankerite;

import com.google.common.collect.ImmutableList;
import mrxiii.Ankerite.blocks.BlockRegister;
import mrxiii.Ankerite.items.ItemRegister;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Consumer;

/**
 * Register the crafting and furnace recipes
 */
public class Recipes extends RecipeProvider {
    /**
     * Creates an instance of the Recipe provider for the mod
     * @param output Datapack output information
     */
    public Recipes(PackOutput output) {
        super(output);
    }

    /**
     * Registers the recipes
     * @param finishedRecipeConsumer
     */
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {
        //Ankerite smelting recipe
        oreSmelting(finishedRecipeConsumer, ImmutableList.of(BlockRegister.ANKERITE_ORE.get(), BlockRegister.DEEPSLATE_ANKERITE_ORE.get()), RecipeCategory.MISC, ItemRegister.ANKERITE.get(), 0.7F, 200, "ankerite");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemRegister.ANKERITE.get()), RecipeCategory.MISC, Items.QUARTZ, 1.0F, 200).unlockedBy("has_ankerite", has(ItemRegister.ANKERITE.get())).save(finishedRecipeConsumer);
        // Ankerite block crafting recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegister.ANKERITE_BLOCK_ITEM.get()).define('#', ItemRegister.ANKERITE.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(ItemRegister.ANKERITE.get()), has(ItemRegister.ANKERITE.get())).save(finishedRecipeConsumer);

    }
}
