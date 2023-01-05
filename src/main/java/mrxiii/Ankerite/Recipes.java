package mrxiii.Ankerite;

import mrxiii.Ankerite.items.ItemRegister;
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
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemRegister.ANKERITE_ORE_ITEM.get()), ItemRegister.ANKERITE.get(), 1.0F, 100, RecipeSerializer.SMELTING_RECIPE).group(getItemName(ItemRegister.ANKERITE.get())).unlockedBy(getHasName(ItemRegister.ANKERITE_ORE_ITEM.get()), has(ItemRegister.ANKERITE_ORE_ITEM.get())).save(p_176532_, new ResourceLocation(AnkeriteMod.MODID, getItemName(ItemRegister.ANKERITE.get())+"_from_smelting_ankerite_ore"));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemRegister.DEEPSLATE_ANKERITE_ORE_ITEM.get()), ItemRegister.ANKERITE.get(), 1.0F, 100, RecipeSerializer.SMELTING_RECIPE).group(getItemName(ItemRegister.ANKERITE.get())).unlockedBy(getHasName(ItemRegister.DEEPSLATE_ANKERITE_ORE_ITEM.get()), has(ItemRegister.DEEPSLATE_ANKERITE_ORE_ITEM.get())).save(p_176532_, new ResourceLocation(AnkeriteMod.MODID, getItemName(ItemRegister.ANKERITE.get())+"_from_smelting_deepslate_ankerite_ore"));
        // Ankerite block crafting recipe
        ShapedRecipeBuilder.shaped(ItemRegister.ANKERITE_BLOCK_ITEM.get()).define('#', ItemRegister.ANKERITE.get()).pattern("##").pattern("##").unlockedBy(getHasName(ItemRegister.ANKERITE.get()), has(ItemRegister.ANKERITE.get())).save(p_176532_);

    }
}
