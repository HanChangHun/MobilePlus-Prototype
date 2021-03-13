package android.graphics.drawable;

import android.util.FloatProperty;

class null extends FloatProperty<RippleForeground> {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(RippleForeground paramRippleForeground) {
    return Float.valueOf(RippleForeground.access$700(paramRippleForeground));
  }
  
  public void setValue(RippleForeground paramRippleForeground, float paramFloat) {
    RippleForeground.access$702(paramRippleForeground, paramFloat);
    RippleForeground.access$802(paramRippleForeground, paramFloat);
    RippleForeground.access$600(paramRippleForeground);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleForeground$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */