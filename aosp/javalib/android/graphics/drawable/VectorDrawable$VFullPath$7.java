package android.graphics.drawable;

import android.util.FloatProperty;

class null extends FloatProperty<VectorDrawable.VFullPath> {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(VectorDrawable.VFullPath paramVFullPath) {
    return Float.valueOf(paramVFullPath.getTrimPathStart());
  }
  
  public void setValue(VectorDrawable.VFullPath paramVFullPath, float paramFloat) {
    paramVFullPath.setTrimPathStart(paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VFullPath$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */