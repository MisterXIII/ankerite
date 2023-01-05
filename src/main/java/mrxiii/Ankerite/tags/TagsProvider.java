package mrxiii.Ankerite.tags;

import mrxiii.Ankerite.blocks.BlockRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class TagsProvider extends BlockTagsProvider  {
    public TagsProvider(DataGenerator p_126511_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, modId, existingFileHelper);
    }

    // Add Tags to blocks so the game can pick up their respective attributes
    @Override
    protected void addTags() {
        // An Iron Level tool tag for the blocks
        tag(BlockTags.NEEDS_IRON_TOOL).add(
                BlockRegister.ANKERITE_ORE.get(),
                BlockRegister.DEEPSLATE_ANKERITE_ORE.get(),
                BlockRegister.ANKERITE_BLOCK.get());
        // A pickaxe tool for the blocks
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockRegister.ANKERITE_ORE.get(),
                BlockRegister.DEEPSLATE_ANKERITE_ORE.get(),
                BlockRegister.ANKERITE_BLOCK.get());
    }
}
