1、首先打开patch文件，根据对应代码修改进行移植
2、编译push:
make libinputflinger
adb root
adb remount
adb push out/target/product/gemini/system/lib64 /system/
adb push out/target/product/gemini/system/lib /system/
adb reboot
3、测试
  adb shell dumpsys inputflinger disable ---关闭掉触摸
  adb shell dumpsys inputflinger enable  --开启触摸
