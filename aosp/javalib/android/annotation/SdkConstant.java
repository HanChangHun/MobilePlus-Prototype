package android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
public @interface SdkConstant {
  SdkConstantType value();
  
  public enum SdkConstantType {
    ACTIVITY_INTENT_ACTION, BROADCAST_INTENT_ACTION, FEATURE, INTENT_CATEGORY, SERVICE_ACTION;
    
    static {
      SdkConstantType sdkConstantType = new SdkConstantType("FEATURE", 4);
      FEATURE = sdkConstantType;
      $VALUES = new SdkConstantType[] { ACTIVITY_INTENT_ACTION, BROADCAST_INTENT_ACTION, SERVICE_ACTION, INTENT_CATEGORY, sdkConstantType };
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/SdkConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */