package android.graphics.drawable;

import android.util.PathParser;
import android.util.Property;

abstract class VPath extends VectorDrawable.VObject {
  private static final Property<VPath, PathParser.PathData> PATH_DATA = new Property<VPath, PathParser.PathData>(PathParser.PathData.class, "pathData") {
      public PathParser.PathData get(VectorDrawable.VPath param2VPath) {
        return param2VPath.getPathData();
      }
      
      public void set(VectorDrawable.VPath param2VPath, PathParser.PathData param2PathData) {
        param2VPath.setPathData(param2PathData);
      }
    };
  
  int mChangingConfigurations;
  
  protected PathParser.PathData mPathData;
  
  String mPathName;
  
  public VPath() {
    this.mPathData = null;
  }
  
  public VPath(VPath paramVPath) {
    PathParser.PathData pathData2 = null;
    this.mPathData = null;
    this.mPathName = paramVPath.mPathName;
    this.mChangingConfigurations = paramVPath.mChangingConfigurations;
    PathParser.PathData pathData1 = paramVPath.mPathData;
    if (pathData1 == null) {
      pathData1 = pathData2;
    } else {
      pathData1 = new PathParser.PathData(pathData1);
    } 
    this.mPathData = pathData1;
  }
  
  public PathParser.PathData getPathData() {
    return this.mPathData;
  }
  
  public String getPathName() {
    return this.mPathName;
  }
  
  Property getProperty(String paramString) {
    return PATH_DATA.getName().equals(paramString) ? PATH_DATA : null;
  }
  
  public void setPathData(PathParser.PathData paramPathData) {
    this.mPathData.setPathData(paramPathData);
    if (isTreeValid())
      VectorDrawable.access$3600(getNativePtr(), this.mPathData.getNativePtr()); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */