package android.hardware.display;

import android.graphics.Rect;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class DisplayViewport {
  public static final int VIEWPORT_EXTERNAL = 2;
  
  public static final int VIEWPORT_INTERNAL = 1;
  
  public static final int VIEWPORT_VIRTUAL = 3;
  
  public int deviceHeight;
  
  public int deviceWidth;
  
  public int displayId;
  
  public boolean isActive;
  
  public final Rect logicalFrame = new Rect();
  
  public int orientation;
  
  public final Rect physicalFrame = new Rect();
  
  public Byte physicalPort;
  
  public int type;
  
  public String uniqueId;
  
  public boolean valid;
  
  public static String typeToString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("UNKNOWN (");
          stringBuilder.append(paramInt);
          stringBuilder.append(")");
          return stringBuilder.toString();
        } 
        return "VIRTUAL";
      } 
      return "EXTERNAL";
    } 
    return "INTERNAL";
  }
  
  public void copyFrom(DisplayViewport paramDisplayViewport) {
    this.valid = paramDisplayViewport.valid;
    this.isActive = paramDisplayViewport.isActive;
    this.displayId = paramDisplayViewport.displayId;
    this.orientation = paramDisplayViewport.orientation;
    this.logicalFrame.set(paramDisplayViewport.logicalFrame);
    this.physicalFrame.set(paramDisplayViewport.physicalFrame);
    this.deviceWidth = paramDisplayViewport.deviceWidth;
    this.deviceHeight = paramDisplayViewport.deviceHeight;
    this.uniqueId = paramDisplayViewport.uniqueId;
    this.physicalPort = paramDisplayViewport.physicalPort;
    this.type = paramDisplayViewport.type;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof DisplayViewport))
      return false; 
    paramObject = paramObject;
    if (this.valid != ((DisplayViewport)paramObject).valid || this.isActive != ((DisplayViewport)paramObject).isActive || this.displayId != ((DisplayViewport)paramObject).displayId || this.orientation != ((DisplayViewport)paramObject).orientation || !this.logicalFrame.equals(((DisplayViewport)paramObject).logicalFrame) || !this.physicalFrame.equals(((DisplayViewport)paramObject).physicalFrame) || this.deviceWidth != ((DisplayViewport)paramObject).deviceWidth || this.deviceHeight != ((DisplayViewport)paramObject).deviceHeight || !TextUtils.equals(this.uniqueId, ((DisplayViewport)paramObject).uniqueId) || this.physicalPort != ((DisplayViewport)paramObject).physicalPort || this.type != ((DisplayViewport)paramObject).type)
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    int i = 1 + 1 * 31 + this.valid;
    i += i * 31 + this.isActive;
    i += i * 31 + this.displayId;
    i += i * 31 + this.orientation;
    i += i * 31 + this.logicalFrame.hashCode();
    i += i * 31 + this.physicalFrame.hashCode();
    i += i * 31 + this.deviceWidth;
    i += i * 31 + this.deviceHeight;
    int j = i + i * 31 + this.uniqueId.hashCode();
    Byte byte_ = this.physicalPort;
    i = j;
    if (byte_ != null)
      i = j + j * 31 + byte_.hashCode(); 
    return i + i * 31 + this.type;
  }
  
  public DisplayViewport makeCopy() {
    DisplayViewport displayViewport = new DisplayViewport();
    displayViewport.copyFrom(this);
    return displayViewport;
  }
  
  public String toString() {
    Integer integer;
    Byte byte_ = this.physicalPort;
    if (byte_ == null) {
      byte_ = null;
    } else {
      integer = Integer.valueOf(Byte.toUnsignedInt(byte_.byteValue()));
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DisplayViewport{type=");
    stringBuilder.append(typeToString(this.type));
    stringBuilder.append(", valid=");
    stringBuilder.append(this.valid);
    stringBuilder.append(", isActive=");
    stringBuilder.append(this.isActive);
    stringBuilder.append(", displayId=");
    stringBuilder.append(this.displayId);
    stringBuilder.append(", uniqueId='");
    stringBuilder.append(this.uniqueId);
    stringBuilder.append("', physicalPort=");
    stringBuilder.append(integer);
    stringBuilder.append(", orientation=");
    stringBuilder.append(this.orientation);
    stringBuilder.append(", logicalFrame=");
    stringBuilder.append(this.logicalFrame);
    stringBuilder.append(", physicalFrame=");
    stringBuilder.append(this.physicalFrame);
    stringBuilder.append(", deviceWidth=");
    stringBuilder.append(this.deviceWidth);
    stringBuilder.append(", deviceHeight=");
    stringBuilder.append(this.deviceHeight);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ViewportType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayViewport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */