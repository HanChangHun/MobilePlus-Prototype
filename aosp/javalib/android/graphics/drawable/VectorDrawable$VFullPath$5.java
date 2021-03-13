package android.graphics.drawable;

import android.util.IntProperty;

class null extends IntProperty<VectorDrawable.VFullPath> {
  null(String paramString) {
    super(paramString);
  }
  
  public Integer get(VectorDrawable.VFullPath paramVFullPath) {
    return Integer.valueOf(paramVFullPath.getFillColor());
  }
  
  public void setValue(VectorDrawable.VFullPath paramVFullPath, int paramInt) {
    paramVFullPath.setFillColor(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VFullPath$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */