package android.hardware.camera2.params;

enum SizeThreshold {
  MAXIMUM, PREVIEW, RECORD, VGA, s1440p, s720p;
  
  static {
    PREVIEW = new SizeThreshold("PREVIEW", 1);
    RECORD = new SizeThreshold("RECORD", 2);
    MAXIMUM = new SizeThreshold("MAXIMUM", 3);
    s720p = new SizeThreshold("s720p", 4);
    SizeThreshold sizeThreshold = new SizeThreshold("s1440p", 5);
    s1440p = sizeThreshold;
    $VALUES = new SizeThreshold[] { VGA, PREVIEW, RECORD, MAXIMUM, s720p, sizeThreshold };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination$SizeThreshold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */