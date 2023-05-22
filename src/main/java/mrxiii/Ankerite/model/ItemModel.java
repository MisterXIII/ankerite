package mrxiii.Ankerite.model;

import mrxiii.Ankerite.items.ItemRegister;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModel extends ItemModelProvider {
    public ItemModel(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegister.ANKERITE.get());


    }
}
