#指定代码的压缩级别
-optimizationpasses 5

#包名不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

#优化 不优化输入的类文件
-dontoptimize

#混淆时是否记录日志
-verbose

#混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*

#忽略警告
-ignorewarnings

#保持native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持系统组件类不被混淆
-keep public class * extends android.app.Fragment{*;}
-keep public class * extends android.support.v4.app.Fragment{*;}
-keep public class * extends android.support.v4.app.FragmentActivity{*;}
-keep public class * extends android.app.Activity{*;}
-keep public class * extends android.app.Application{*;}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#不混淆资源类
-keep class **.R$* {
   public static <fields>;
}

# 避免混淆泛型，混淆报错建议关掉
-keepattributes Signature

#保持butterknife不被混淆
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

