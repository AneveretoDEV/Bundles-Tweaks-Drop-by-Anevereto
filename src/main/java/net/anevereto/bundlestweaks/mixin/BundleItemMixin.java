package net.anevereto.bundlestweaks.mixin;

import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleContentsComponent.class)
public class BundleItemMixin {

    @Inject(method = "getOccupancy(Lnet/minecraft/item/ItemStack;)Lorg/apache/commons/lang3/math/Fraction;", at = @At("HEAD"), cancellable = true)
    private static void modifyGetOccupancy(ItemStack stack, CallbackInfoReturnable<Fraction> cir) {
        if(!stack.isStackable()) {
            cir.setReturnValue(Fraction.getFraction(1, 64));
        } else if (stack.getMaxCount() == 16) {
            cir.setReturnValue(Fraction.getFraction(1, 64));
        }
    }

}


