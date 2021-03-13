package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import com.android.internal.R;
import org.xmlpull.v1.XmlPullParser;

public class PaintDrawable extends ShapeDrawable {
  public PaintDrawable() {}
  
  public PaintDrawable(int paramInt) {
    getPaint().setColor(paramInt);
  }
  
  protected boolean inflateTag(String paramString, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) {
    TypedArray typedArray;
    if (paramString.equals("corners")) {
      typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.DrawableCorners);
      int i = typedArray.getDimensionPixelSize(0, 0);
      setCornerRadius(i);
      int j = typedArray.getDimensionPixelSize(1, i);
      int k = typedArray.getDimensionPixelSize(2, i);
      int m = typedArray.getDimensionPixelSize(3, i);
      int n = typedArray.getDimensionPixelSize(4, i);
      if (j != i || k != i || m != i || n != i)
        setCornerRadii(new float[] { j, j, k, k, m, m, n, n }); 
      typedArray.recycle();
      return true;
    } 
    return super.inflateTag((String)typedArray, paramResources, paramXmlPullParser, paramAttributeSet);
  }
  
  public void setCornerRadii(float[] paramArrayOffloat) {
    if (paramArrayOffloat == null) {
      if (getShape() != null)
        setShape((Shape)null); 
    } else {
      setShape((Shape)new RoundRectShape(paramArrayOffloat, null, null));
    } 
    invalidateSelf();
  }
  
  public void setCornerRadius(float paramFloat) {
    float[] arrayOfFloat = null;
    if (paramFloat > 0.0F) {
      float[] arrayOfFloat1 = new float[8];
      byte b = 0;
      while (true) {
        arrayOfFloat = arrayOfFloat1;
        if (b < 8) {
          arrayOfFloat1[b] = paramFloat;
          b++;
          continue;
        } 
        break;
      } 
    } 
    setCornerRadii(arrayOfFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/PaintDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */