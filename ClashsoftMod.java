package clashsoft.clashsoftapi;

import clashsoft.clashsoftapi.util.CSFontRenderer;
import clashsoft.clashsoftapi.util.CSItemRenderer;
import clashsoft.clashsoftapi.util.CSUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "ClashsoftAPI", name = "Clashsoft API", version = CSUtil.CURRENT_VERION)
public class ClashsoftMod
{	
	public static CSFontRenderer csFontRenderer = new CSFontRenderer(Minecraft.getMinecraft().gameSettings, "/font/default.png", Minecraft.getMinecraft().renderEngine, false);
	public static CSItemRenderer csItemRenderer = new CSItemRenderer();
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		csFontRenderer = new CSFontRenderer(Minecraft.getMinecraft().gameSettings, "/font/default.png", Minecraft.getMinecraft().renderEngine, false);
		Minecraft.getMinecraft().fontRenderer = csFontRenderer;
	}
}