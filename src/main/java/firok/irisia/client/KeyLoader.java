package firok.irisia.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.ClientRegistry;

public class KeyLoader {
	public static KeyBinding debug;
	
	public KeyLoader()
	{
		KeyLoader.debug
				= new KeyBinding(
						"key.irisia.debug",
				Keyboard.KEY_I,
				"key.categories.irisia");

        ClientRegistry.registerKeyBinding(KeyLoader.debug);
	}

}
