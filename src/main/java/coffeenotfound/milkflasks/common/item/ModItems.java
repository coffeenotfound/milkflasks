package coffeenotfound.milkflasks.common.item;

import coffeenotfound.milkflasks.ModMilkflasks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	public static ItemMilkFlask itemMilkFlask = new ItemMilkFlask(new Item.Properties().group(ItemGroup.BREWING));
	
	public static void registerItems(IForgeRegistry<Item> registry) {
		reg("milk_flask", itemMilkFlask, registry);
	}
	
	protected static <I extends Item> I reg(String name, I item, IForgeRegistry<Item> registry) {
		// Set registry name
		item.setRegistryName(new ResourceLocation(ModMilkflasks.MOD_ID, name));
		
		// Register item
		registry.register(item);
		return item;
	}
}
