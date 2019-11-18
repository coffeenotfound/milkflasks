package coffeenotfound.milkflasks;

import coffeenotfound.milkflasks.common.item.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod(ModMilkflasks.MOD_ID)
@Mod.EventBusSubscriber(modid = ModMilkflasks.MOD_ID, bus = Bus.MOD)
public class ModMilkflasks {
	public static final String MOD_ID = "milkflasks";
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ModItems.registerItems(event.getRegistry());
	}
}
