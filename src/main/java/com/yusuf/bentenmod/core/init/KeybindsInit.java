package com.yusuf.bentenmod.core.init;

import com.yusuf.bentenmod.BenTenMod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class KeybindsInit {
	
	public static KeyBinding fireBlassKey;
	
	
	public static void register(final FMLClientSetupEvent event) {
		
		fireBlassKey = create("fireblast_key", KeyEvent.VK_K);
		
		ClientRegistry.registerKeyBinding(fireBlassKey);
	} 
	
	private static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + BenTenMod.MOD_ID + "." + name , key, "key.category." + BenTenMod.MOD_ID);
	}
}	
