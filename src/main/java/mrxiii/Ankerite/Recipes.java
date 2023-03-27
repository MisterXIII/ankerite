package mrxiii.Ankerite;

import mrxiii.Ankerite.items.ItemRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
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
     * @param dataGenerator Data generator of the initialization process
     */
    public Recipes(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    /**
     * Registers the recipes
     * @param finishedRecipeConsumer
     */
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {
        //Ankerite smelting recipe
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemRegister.ANKERITE_ORE_ITEM.get()), ItemRegister.ANKERITE.get(), 1.0F, 100, RecipeSerializer.SMELTING_RECIPE).group(getItemName(ItemRegister.ANKERITE.get())).unlockedBy(getHasName(ItemRegister.ANKERITE_ORE_ITEM.get()), has(ItemRegister.ANKERITE_ORE_ITEM.get())).save(finishedRecipeConsumer, new ResourceLocation(AnkeriteMod.MODID, getItemName(ItemRegister.ANKERITE.get())+"_from_smelting_ankerite_ore"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemRegister.DEEPSLATE_ANKERITE_ORE_ITEM.get()), ItemRegister.ANKERITE.get(), 1.0F, 100, RecipeSerializer.SMELTING_RECIPE).group(getItemName(ItemRegister.ANKERITE.get())).unlockedBy(getHasName(ItemRegister.DEEPSLATE_ANKERITE_ORE_ITEM.get()), has(ItemRegister.DEEPSLATE_ANKERITE_ORE_ITEM.get())).save(finishedRecipeConsumer, new ResourceLocation(AnkeriteMod.MODID, getItemName(ItemRegister.ANKERITE.get())+"_from_smelting_deepslate_ankerite_ore"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemRegister.ANKERITE.get()), Items.QUARTZ, 1.0F, 100, RecipeSerializer.SMELTING_RECIPE).group(getItemName(Items.QUARTZ)).unlockedBy(getHasName(ItemRegister.ANKERITE.get()), has(ItemRegister.ANKERITE.get())).save(finishedRecipeConsumer, new ResourceLocation(AnkeriteMod.MODID, getItemName(Items.QUARTZ) + "_from_smelting_" + getItemName(ItemRegister.ANKERITE.get())));
        // Ankerite block crafting recipe
        ShapedRecipeBuilder.shaped(ItemRegister.ANKERITE_BLOCK_ITEM.get()).define('#', ItemRegister.ANKERITE.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(ItemRegister.ANKERITE.get()), has(ItemRegister.ANKERITE.get())).save(finishedRecipeConsumer);

    }
}
