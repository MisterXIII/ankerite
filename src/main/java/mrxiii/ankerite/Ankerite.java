package mrxiii.ankerite;

import mrxiii.ankerite.blocks.BlockAnkeriteOre;
import mrxiii.ankerite.entities.tileentities.TileEntityAnkerite;
import mrxiii.ankerite.items.ItemAnkerite;
import mrxiii.ankerite.proxy.CommonProxy;
import mrxiii.ankerite.world.AnkeriteGen;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import java.util.List;

@Mod(modid = Ankerite.MODID, name = "Ankerite Mod", version = Ankerite.VERSION)
public class Ankerite
{

    @SidedProxy(clientSide = "mrxiii.ankerite.proxy.ClientProxy", serverSide = "mrxiii.ankerite.proxy.CommonProxy")
    public static CommonProxy proxy;


    public static final String MODID = "ankerite";
    public static final String VERSION = "0.2";


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        // Register items
        GameRegistry.registerItem(new ItemAnkerite().setUnlocalizedName("ankerite").setCreativeTab(CreativeTabs.tabAllSearch).setTextureName(MODID+":ankerite"), "ankerite");

        // Register ores
        GameRegistry.registerBlock(new BlockAnkeriteOre().setHardness(3.0f).setStepSound(Block.soundTypeStone).setBlockName("ankerite_ore").setCreativeTab(CreativeTabs.tabAllSearch).setBlockTextureName(MODID + ":ankerite_ore"), "ankerite_ore");

        // Register TileEntities
       GameRegistry.registerTileEntity(TileEntityAnkerite.class, "ankerite");

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Test message
        FMLInterModComms.sendMessage("ankerite", "info-message", "Hello");

        // Setup ore generator
        AnkeriteGen debug = new AnkeriteGen();

        // Add smelting recipe
        GameRegistry.addSmelting(AnkeriteObjectHolder.ankerite, new ItemStack(GameRegistry.findItem("minecraft", "quartz"), 1), 3);
    }

    @EventHandler
    public void interModComms(FMLInterModComms.IMCEvent event)
    {


        List<FMLInterModComms.IMCMessage> list = event.getMessages();
        for (FMLInterModComms.IMCMessage m: list)
        {
            System.out.println(m.getStringValue());
        }

    }

}
