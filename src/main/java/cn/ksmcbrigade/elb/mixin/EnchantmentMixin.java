package cn.ksmcbrigade.elb.mixin;

import cn.ksmcbrigade.elb.ELB;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = {Enchantment.class},remap = false)
public class EnchantmentMixin {
    @Inject(method = "getMaxLevel*",at = @At("RETURN"), cancellable = true)
    public void max(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(Integer.MAX_VALUE - 1);
    }

    @Inject(method = "getFullname",at = @At(value = "INVOKE",target = "Lnet/minecraft/network/chat/MutableComponent;append(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;"),locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void fullName(int p_44701_, CallbackInfoReturnable<Component> cir, MutableComponent mutablecomponent){
        boolean change = false;
        if(p_44701_>10 && p_44701_<=200000){
            mutablecomponent.append(" ").append(ELB.intToRoman(p_44701_));
            change = true;
        }
        else if(p_44701_>200000){
            mutablecomponent.append(" ").append(String.valueOf(p_44701_));
            change = true;
        }
        if(change){
            cir.setReturnValue(mutablecomponent);
            cir.cancel();
        }
    }
}
