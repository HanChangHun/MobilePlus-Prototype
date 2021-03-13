package android.graphics;

public enum Named {
  ACES, ACESCG, ADOBE_RGB, BT2020, BT709, CIE_LAB, CIE_XYZ, DCI_P3, DISPLAY_P3, EXTENDED_SRGB, LINEAR_EXTENDED_SRGB, LINEAR_SRGB, NTSC_1953, PRO_PHOTO_RGB, SMPTE_C, SRGB;
  
  static {
    LINEAR_SRGB = new Named("LINEAR_SRGB", 1);
    EXTENDED_SRGB = new Named("EXTENDED_SRGB", 2);
    LINEAR_EXTENDED_SRGB = new Named("LINEAR_EXTENDED_SRGB", 3);
    BT709 = new Named("BT709", 4);
    BT2020 = new Named("BT2020", 5);
    DCI_P3 = new Named("DCI_P3", 6);
    DISPLAY_P3 = new Named("DISPLAY_P3", 7);
    NTSC_1953 = new Named("NTSC_1953", 8);
    SMPTE_C = new Named("SMPTE_C", 9);
    ADOBE_RGB = new Named("ADOBE_RGB", 10);
    PRO_PHOTO_RGB = new Named("PRO_PHOTO_RGB", 11);
    ACES = new Named("ACES", 12);
    ACESCG = new Named("ACESCG", 13);
    CIE_XYZ = new Named("CIE_XYZ", 14);
    Named named = new Named("CIE_LAB", 15);
    CIE_LAB = named;
    $VALUES = new Named[] { 
        SRGB, LINEAR_SRGB, EXTENDED_SRGB, LINEAR_EXTENDED_SRGB, BT709, BT2020, DCI_P3, DISPLAY_P3, NTSC_1953, SMPTE_C, 
        ADOBE_RGB, PRO_PHOTO_RGB, ACES, ACESCG, CIE_XYZ, named };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Named.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */