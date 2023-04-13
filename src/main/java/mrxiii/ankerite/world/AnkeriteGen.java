package mrxiii.ankerite.world;

import mrxiii.ankerite.AnkeriteObjectHolder;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

/**
 * The class specifying how Ankerite ore is generated. The world height is used as a weight
 * to make Ankerite ore more likely to spawn at Y level 20.
 * @author MrXIII
 */
public class AnkeriteGen extends WorldGenMinable {

    /**
     * The constructor for the Ankerite ore generator. Adds this class to ORE_GEN_BUS, for generating ores.
     * @see net.minecraftforge.common.MinecraftForge#ORE_GEN_BUS
     */
    public AnkeriteGen() {
        super(AnkeriteObjectHolder.ankerite_ore, 5);
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    /**
     * The method that generates Ankerite ore. This method is called by the ORE_GEN_BUS.
     * @param event The event that is called from ORE_GEN_BUS.
     * @see net.minecraftforge.common.MinecraftForge#ORE_GEN_BUS
     */
    @SubscribeEvent
    public void generateAnkerite(OreGenEvent.Post event)
    {
        if(TerrainGen.generateOre(event.world, event.rand, this, event.worldX, event.worldZ, OreGenEvent.GenerateMinable.EventType.CUSTOM))
            genStandard(event);

    }

      /**
     * The logic for generating Ankerite ore. Called once during the generation of each chunk.
     * At most, 3 ores can generate per chunk, and the ore is most likely to spawn at Y level 20.
     * @param event The event that is called from ORE_GEN_BUS.
     */
    public void genStandard(OreGenEvent.Post event)
    {
        // Generate at most 3 Ankerite ore per chunk.
        for (int i = 0; i < 3; i++) {
            int i1 = event.worldX + event.rand.nextInt(16);
            int j1 = event.rand.nextInt(40 );
            int k1 = event.worldZ + event.rand.nextInt(16);

            // Make Ankerite most likely to spawn at Y level 20 and less at other points
            int chance = event.rand.nextInt(21);
            if(Math.abs(j1 - 20) <= chance) {
                this.generate(event.world, event.rand, i1, j1, k1);
            }
        }
    }
}
