package android.graphics.drawable;

import android.util.PathParser;
import android.util.Property;

class null extends Property<VectorDrawable.VPath, PathParser.PathData> {
  null(Class paramClass, String paramString) {
    super(paramClass, paramString);
  }
  
  public PathParser.PathData get(VectorDrawable.VPath paramVPath) {
    return paramVPath.getPathData();
  }
  
  public void set(VectorDrawable.VPath paramVPath, PathParser.PathData paramPathData) {
    paramVPath.setPathData(paramPathData);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VPath$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */