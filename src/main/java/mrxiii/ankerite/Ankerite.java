package mrxiii.ankerite;

import mrxiii.ankerite.blocks.BlockAnkerite;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Entry point for the Ankerite mod. Adds a new ore, which can be used to create a block that heals you.
 * @author MrXIII
 */
@Mod(modid = Ankerite.MODID, name = "Ankerite Mod", version = Ankerite.VERSION)
public class Ankerite
{

    /**
     * The proxy for the mod.
     */
    @SidedProxy(clientSide = "mrxiii.ankerite.proxy.ClientProxy", serverSide = "mrxiii.ankerite.proxy.CommonProxy")
    public static CommonProxy proxy;


    /**
     * The mod ID.
     */
    public static final String MODID = "ankerite";

    /**
     * The mod version.
     */
    public static final String VERSION = "0.2";

    /**
     * The mod's pre-initialization event handler.
     * Registers the items, blocks and TileEntities to the game.
     * @param event
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        // Register items
        GameRegistry.registerItem(new ItemAnkerite().setUnlocalizedName("ankerite").setCreativeTab(CreativeTabs.tabAllSearch).setTextureName(MODID+":ankerite"), "ankerite");

        // Register ores
        GameRegistry.registerBlock(new BlockAnkeriteOre().setHardness(3.0f).setStepSound(Block.soundTypeStone).setBlockName("ankerite_ore").setCreativeTab(CreativeTabs.tabAllSearch).setBlockTextureName(MODID + ":ankerite_ore"), "ankerite_ore");
        GameRegistry.registerBlock(new BlockAnkerite().setHardness(3.0f).setStepSound(Block.soundTypeGlass).setBlockName("ankerite_block").setCreativeTab(CreativeTabs.tabAllSearch).setBlockTextureName(MODID + ":ankerite_block"), "ankerite_block");

        // Register TileEntities
       GameRegistry.registerTileEntity(TileEntityAnkerite.class, "ankerite");

    }

    /**
     * The mod's initialization event handler.
     * Adds the smelting and crafting recipes.
     * @param event
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Test message
        FMLInterModComms.sendMessage("ankerite", "info-message", "Hello");

        // Setup ore generator
        AnkeriteGen debug = new AnkeriteGen();

        // Add smelting recipe
        GameRegistry.addSmelting(AnkeriteObjectHolder.ankerite, new ItemStack(Items.quartz, 1), 3);

        // Add crafting recipe
        GameRegistry.addShapelessRecipe(new ItemStack(AnkeriteObjectHolder.ankerite_block, 1), AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite, AnkeriteObjectHolder.ankerite);
    }

    /**
     * The mod's inter-mod communication event handler. Just testing the messaging system.
     * But just use IG?
     * @param event
     */
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
