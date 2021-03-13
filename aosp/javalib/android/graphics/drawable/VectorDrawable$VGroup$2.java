package android.graphics.drawable;

import android.util.FloatProperty;

class null extends FloatProperty<VectorDrawable.VGroup> {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(VectorDrawable.VGroup paramVGroup) {
    return Float.valueOf(paramVGroup.getTranslateX());
  }
  
  public void setValue(VectorDrawable.VGroup paramVGroup, float paramFloat) {
    paramVGroup.setTranslateX(paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VGroup$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */