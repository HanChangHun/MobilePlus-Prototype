package android.graphics;

public enum Config {
  ALPHA_8(1),
  ARGB_4444(1),
  ARGB_8888(1),
  HARDWARE(1),
  RGBA_F16(1),
  RGB_565(3);
  
  private static Config[] sConfigs;
  
  final int nativeInt;
  
  static {
    ARGB_4444 = new Config("ARGB_4444", 2, 4);
    ARGB_8888 = new Config("ARGB_8888", 3, 5);
    RGBA_F16 = new Config("RGBA_F16", 4, 6);
    Config config1 = new Config("HARDWARE", 5, 7);
    HARDWARE = config1;
    Config config2 = ALPHA_8;
    Config config3 = RGB_565;
    Config config4 = ARGB_4444;
    Config config5 = ARGB_8888;
    Config config6 = RGBA_F16;
    $VALUES = new Config[] { config2, config3, config4, config5, config6, config1 };
    sConfigs = new Config[] { null, config2, null, config3, config4, config5, config6, config1 };
  }
  
  Config(int paramInt1) {
    this.nativeInt = paramInt1;
  }
  
  static Config nativeToConfig(int paramInt) {
    return sConfigs[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Bitmap$Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */