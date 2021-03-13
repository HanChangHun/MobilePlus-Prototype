package android.graphics.drawable;

import android.util.FloatProperty;

class null extends FloatProperty<VectorDrawable.VGroup> {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(VectorDrawable.VGroup paramVGroup) {
    return Float.valueOf(paramVGroup.getScaleX());
  }
  
  public void setValue(VectorDrawable.VGroup paramVGroup, float paramFloat) {
    paramVGroup.setScaleX(paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VGroup$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */