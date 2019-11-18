package coffeenotfound.milkflasks.common.item;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemMilkFlask extends Item {
	
	public ItemMilkFlask(Properties props) {
		super(props.maxStackSize(1));
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		// Consume item from stack
		PlayerEntity player = (entity instanceof PlayerEntity ? (PlayerEntity)entity : null);
		if(player == null || !player.abilities.isCreativeMode) {
			stack.shrink(1);
		}
		
		// Trigger critera
		if(player instanceof ServerPlayerEntity) {
			CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)player, stack);
		}
		
		// Update stats
		if(player != null) {
			player.addStat(Stats.ITEM_USED.get(this));
		}
		
		// Do consume logic
		if(player != null && !world.isRemote) {
			removeRandomPotionEffect(world, player);
		}
		
		// Add empty bottle to inventory
		if(player == null || !player.abilities.isCreativeMode) {
			// If stack is now empty, turn it into a glass bottle
			if(stack.isEmpty()) {
				return new ItemStack(Items.GLASS_BOTTLE);
			}
			// Else add empty glass bottle to inventory
			else if(player != null) {
				player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE, 1));
			}
		}
		return stack;
	}
	
	public boolean removeRandomPotionEffect(World world, PlayerEntity player) {
		Collection<EffectInstance> effectCollection = player.getActivePotionEffects();
		
		// If the player has no effects, do nothing
		if(effectCollection.isEmpty()) {
			return false;
		}
		else {
			ArrayList<EffectInstance> effectList = new ArrayList<>(effectCollection);
			BitSet triedBitSet = null;
			
			for(;;) {
				// Get random effect index that we haven't tried removing yet
				int randomIndex;
				do randomIndex = world.rand.nextInt(effectList.size());
				while(triedBitSet != null && triedBitSet.get(randomIndex));
				
				// Try removing effect
				EffectInstance effectInstance = effectList.get(randomIndex);
				
				if(player.removePotionEffect(effectInstance.getPotion())) {
					return true;
				}
				else {
					// Instantiate bit set
					if(triedBitSet == null) {
						triedBitSet = new BitSet(effectList.size());
					}
					
					// Set tried flag
					triedBitSet.set(randomIndex);
					
					// Check if we have no effects left to try to remove
					if(triedBitSet.cardinality() >= effectList.size()) {
						return false;
					}
				}
			}
		}
	}
	
	public int getUseDuration(ItemStack stack) {
		return 12;
	}
	
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
