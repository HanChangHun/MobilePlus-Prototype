package android.graphics.drawable;

import android.util.FloatProperty;

class null extends FloatProperty<VectorDrawable.VectorDrawableState> {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(VectorDrawable.VectorDrawableState paramVectorDrawableState) {
    return Float.valueOf(paramVectorDrawableState.getAlpha());
  }
  
  public void setValue(VectorDrawable.VectorDrawableState paramVectorDrawableState, float paramFloat) {
    paramVectorDrawableState.setAlpha(paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VectorDrawableState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */