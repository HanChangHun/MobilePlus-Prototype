package android.animation;

import android.util.PathParser;

class PathDataEvaluator implements TypeEvaluator<PathParser.PathData> {
  private final PathParser.PathData mPathData = new PathParser.PathData();
  
  private PathDataEvaluator() {}
  
  public PathParser.PathData evaluate(float paramFloat, PathParser.PathData paramPathData1, PathParser.PathData paramPathData2) {
    if (PathParser.interpolatePathData(this.mPathData, paramPathData1, paramPathData2, paramFloat))
      return this.mPathData; 
    throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorInflater$PathDataEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */