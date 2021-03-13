package android.gesture;

import android.graphics.Matrix;
import android.graphics.Path;

public class OrientedBoundingBox {
  public final float centerX;
  
  public final float centerY;
  
  public final float height;
  
  public final float orientation;
  
  public final float squareness;
  
  public final float width;
  
  OrientedBoundingBox(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
    this.orientation = paramFloat1;
    this.width = paramFloat4;
    this.height = paramFloat5;
    this.centerX = paramFloat2;
    this.centerY = paramFloat3;
    paramFloat1 = paramFloat4 / paramFloat5;
    if (paramFloat1 > 1.0F) {
      this.squareness = 1.0F / paramFloat1;
    } else {
      this.squareness = paramFloat1;
    } 
  }
  
  public Path toPath() {
    Path path = new Path();
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = -this.width / 2.0F;
    arrayOfFloat[1] = this.height / 2.0F;
    Matrix matrix = new Matrix();
    matrix.setRotate(this.orientation);
    matrix.postTranslate(this.centerX, this.centerY);
    matrix.mapPoints(arrayOfFloat);
    path.moveTo(arrayOfFloat[0], arrayOfFloat[1]);
    arrayOfFloat[0] = -this.width / 2.0F;
    arrayOfFloat[1] = -this.height / 2.0F;
    matrix.mapPoints(arrayOfFloat);
    path.lineTo(arrayOfFloat[0], arrayOfFloat[1]);
    arrayOfFloat[0] = this.width / 2.0F;
    arrayOfFloat[1] = -this.height / 2.0F;
    matrix.mapPoints(arrayOfFloat);
    path.lineTo(arrayOfFloat[0], arrayOfFloat[1]);
    arrayOfFloat[0] = this.width / 2.0F;
    arrayOfFloat[1] = this.height / 2.0F;
    matrix.mapPoints(arrayOfFloat);
    path.lineTo(arrayOfFloat[0], arrayOfFloat[1]);
    path.close();
    return path;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/OrientedBoundingBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */