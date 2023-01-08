package mrxiii.ankerite.world;

import mrxiii.ankerite.AnkeriteObjectHolder;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class AnkeriteGen extends WorldGenMinable {
    public AnkeriteGen() {
        super(AnkeriteObjectHolder.ankerite_ore, 12);
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void generateAnkerite(OreGenEvent.Post event)
    {
        if(TerrainGen.generateOre(event.world, event.rand, this, event.worldX, event.worldZ, OreGenEvent.GenerateMinable.EventType.CUSTOM))
            genStandard(event);

    }

    public void genStandard(OreGenEvent.Post event)
    {
        for (int i = 0; i < 3; i++) {
            int i1 = event.worldX + event.rand.nextInt(16);
            int j1 = event.rand.nextInt(40);
            int k1 = event.worldZ + event.rand.nextInt(16);

            // Make Ankerite most likely to spawn at Y level 20 and less at other points
            int chance = event.rand.nextInt(21);
            if(Math.abs(j1 - 20) <= chance) {
                this.generate(event.world, event.rand, i1, j1, k1);
            }
        }
    }
}
