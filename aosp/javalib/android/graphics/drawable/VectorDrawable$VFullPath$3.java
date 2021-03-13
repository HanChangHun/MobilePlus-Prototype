package android.graphics.drawable;

import android.util.IntProperty;

class null extends IntProperty<VectorDrawable.VFullPath> {
  null(String paramString) {
    super(paramString);
  }
  
  public Integer get(VectorDrawable.VFullPath paramVFullPath) {
    return Integer.valueOf(paramVFullPath.getStrokeColor());
  }
  
  public void setValue(VectorDrawable.VFullPath paramVFullPath, int paramInt) {
    paramVFullPath.setStrokeColor(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VFullPath$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */