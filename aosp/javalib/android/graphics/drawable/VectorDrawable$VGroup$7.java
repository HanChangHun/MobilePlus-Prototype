package android.graphics.drawable;

import android.util.FloatProperty;

class null extends FloatProperty<VectorDrawable.VGroup> {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(VectorDrawable.VGroup paramVGroup) {
    return Float.valueOf(paramVGroup.getPivotY());
  }
  
  public void setValue(VectorDrawable.VGroup paramVGroup, float paramFloat) {
    paramVGroup.setPivotY(paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VGroup$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */