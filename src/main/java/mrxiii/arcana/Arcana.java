package mrxiii.arcana;

import mrxiii.arcana.proxy.CommonProxy;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import java.util.List;

/**
 * Entry point for the Arcana Mod.
 * @author MrXIII
 */
@Mod(modid = Arcana.MODID, name = "Arcana", version = Arcana.VERSION)
public class Arcana
{

    /**
     * The proxy for the mod.
     */
    @SidedProxy(clientSide = "mrxiii.arcana.proxy.ClientProxy", serverSide = "mrxiii.arcana.proxy.CommonProxy")
    public static CommonProxy proxy;


    /**
     * The mod ID.
     */
    public static final String MODID = "arcana";

    /**
     * The mod version.
     */
    public static final String VERSION = "0.1";

    /**
     * The mod's pre-initialization event handler.
     * Registers the items, blocks and TileEntities to the game.
     * @param event
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    /**
     * The mod's initialization event handler.
     * Adds the smelting and crafting recipes.
     * @param event
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
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
