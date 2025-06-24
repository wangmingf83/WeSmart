package cn.iocoder.yudao.module.smart.lib;

public class YourClassInTheLibrary {
    static {
        try {
            System.load(YourClassInTheLibrary.class.getResource("/libWeWorkFinanceSdk_Java.so").getPath());
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }
}
