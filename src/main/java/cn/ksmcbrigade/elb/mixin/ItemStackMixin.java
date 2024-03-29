package cn.ksmcbrigade.elb.mixin;

import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = ItemStack.class,remap = false)
public class ItemStackMixin {
    @Inject(method = "enchant",at = @At(value = "INVOKE",target = "Lnet/minecraft/nbt/ListTag;add(Ljava/lang/Object;)Z"),locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void add(Enchantment p_41664_, int p_41665_, CallbackInfo ci, ListTag listtag){
        listtag.add(EnchantmentHelper.storeEnchantment(EnchantmentHelper.getEnchantmentId(p_41664_), p_41665_));
        ci.cancel();
    }
}
