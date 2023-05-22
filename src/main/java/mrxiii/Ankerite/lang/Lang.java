package mrxiii.Ankerite.lang;

import mrxiii.Ankerite.blocks.BlockRegister;
import mrxiii.Ankerite.items.ItemRegister;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class Lang extends LanguageProvider {
    public Lang(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        addItem(ItemRegister.ANKERITE, "Ankerite");
        addBlock(BlockRegister.ANKERITE_BLOCK, "Ankerite Block");
        addBlock(BlockRegister.ANKERITE_ORE, "Ankerite Ore");
        addBlock(BlockRegister.DEEPSLATE_ANKERITE_ORE, "Deepslate Ankerite Ore");

    }
}
