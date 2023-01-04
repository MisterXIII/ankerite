package mrxiii.Ankerite.tags;

import mrxiii.Ankerite.AnkeriteMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class TagsProvider extends BlockTagsProvider  {
    public TagsProvider(DataGenerator p_126511_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.NEEDS_IRON_TOOL).add(AnkeriteMod.ANKERITE_ORE.get(), AnkeriteMod.ANKERITE_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(AnkeriteMod.ANKERITE_ORE.get(), AnkeriteMod.ANKERITE_BLOCK.get());
    }
}
