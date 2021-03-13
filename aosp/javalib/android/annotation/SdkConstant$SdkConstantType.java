package android.annotation;

public enum SdkConstantType {
  ACTIVITY_INTENT_ACTION, BROADCAST_INTENT_ACTION, FEATURE, INTENT_CATEGORY, SERVICE_ACTION;
  
  static {
    INTENT_CATEGORY = new SdkConstantType("INTENT_CATEGORY", 3);
    SdkConstantType sdkConstantType = new SdkConstantType("FEATURE", 4);
    FEATURE = sdkConstantType;
    $VALUES = new SdkConstantType[] { ACTIVITY_INTENT_ACTION, BROADCAST_INTENT_ACTION, SERVICE_ACTION, INTENT_CATEGORY, sdkConstantType };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/SdkConstant$SdkConstantType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */