package net.anevereto.bundlestweaks.mixin;

import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Adjusts the weight of stackable and non-stackable items to match
 * the weight calculation of stackable items with a maximum stack size of 64.
 *
 * <p>This adjustment ensures consistency in weight calculations across all items types.</p>
 *
 * <ul>
 *     <li>The original behavior for nested bundles remains unchanged.</li>
 *     <li>The original behavior of beehives and bee nests remains unchanged (subject to potential future changes).</li>
 *     <li>The weight of non-stackable items and stackable items with a maximum stack size of 16 is now 1, consistent with stackable items by 64.</li>
 * </ul>
 *
 * @author Anevereto (Ane)
 * @version 1.0.0
 * @since 1.0.0
 */



@Mixin(BundleContentsComponent.class)
public class BundleItemMixin {

    @Inject(method = "getOccupancy(Lnet/minecraft/item/ItemStack;)Lorg/apache/commons/lang3/math/Fraction;", at = @At("TAIL"), cancellable = true)
    private static void modifyGetOccupancy(ItemStack stack, CallbackInfoReturnable<Fraction> cir) {
        /**
         * Modifies the occupancy calculation for non-stackable items and items with a maximum
         * stack size of 16.
         *
         * <p>This method checks if the provided ItemStack is non-stackable or if it has a
         * maximum count of 16. If either condition is true, the method sets the return value to
         * represent a weight of 1, equivalent to stackable items with a maximum stack size of 64.</p>
         *
         * @param stack The ItemStack for which the occupancy is being calculated.
         * @param cir   The callback information to set the return value.
         */

        if(!stack.isStackable() || stack.getMaxCount() == 16) {
            cir.setReturnValue(Fraction.getFraction(1, 64));
        }
    }

}






