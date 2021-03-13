package android.hardware.camera2.params;

enum ReprocessType {
  NONE, PRIVATE, YUV;
  
  static {
    ReprocessType reprocessType = new ReprocessType("YUV", 2);
    YUV = reprocessType;
    $VALUES = new ReprocessType[] { NONE, PRIVATE, reprocessType };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination$ReprocessType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */