package cn.ksmcbrigade.elb;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("elb")
public class ELB {

    public ELB() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < values.length; i++) {
                while (num >= values[i]) {
                    sb.append(symbols[i]);
                    num -= values[i];
                }
            }
        }
        catch (Exception e){
            sb = new StringBuilder(String.valueOf(num));
        }
        return sb.toString();
    }
}
