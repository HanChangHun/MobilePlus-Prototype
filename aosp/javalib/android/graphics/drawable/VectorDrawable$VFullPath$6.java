package android.graphics.drawable;

import android.util.FloatProperty;

class null extends FloatProperty<VectorDrawable.VFullPath> {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(VectorDrawable.VFullPath paramVFullPath) {
    return Float.valueOf(paramVFullPath.getFillAlpha());
  }
  
  public void setValue(VectorDrawable.VFullPath paramVFullPath, float paramFloat) {
    paramVFullPath.setFillAlpha(paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VFullPath$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */