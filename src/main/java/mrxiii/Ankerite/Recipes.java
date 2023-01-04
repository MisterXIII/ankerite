package mrxiii.Ankerite;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> p_176532_) {
        //Ankerite smelting recipe
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(AnkeriteMod.ANKERITE_ORE_ITEM.get()), AnkeriteMod.ANKERITE.get(), 1.0F, 100, RecipeSerializer.SMELTING_RECIPE).group(getItemName(AnkeriteMod.ANKERITE_BLOCK_ITEM.get())).unlockedBy(getHasName(AnkeriteMod.ANKERITE_ORE_ITEM.get()), has(AnkeriteMod.ANKERITE_ORE_ITEM.get())).save(p_176532_, new ResourceLocation(AnkeriteMod.MODID, getItemName(AnkeriteMod.ANKERITE.get())+"_from_smelting_"+ getItemName(AnkeriteMod.ANKERITE_ORE.get())));
        // Ankerite block crafting recipe
        ShapedRecipeBuilder.shaped(AnkeriteMod.ANKERITE_BLOCK_ITEM.get()).define('#', AnkeriteMod.ANKERITE.get()).pattern("##").pattern("##").unlockedBy(getHasName(AnkeriteMod.ANKERITE.get()), has(AnkeriteMod.ANKERITE.get())).save(p_176532_);

    }
}
