package android.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewDebug.IntToString;
import android.view.ViewGroup;
import android.view.ViewHierarchyEncoder;
import com.android.internal.R;

public class LayoutParams extends ViewGroup.MarginLayoutParams {
  @ExportedProperty(category = "layout", mapping = {@IntToString(from = -1, to = "NONE"), @IntToString(from = 0, to = "NONE"), @IntToString(from = 48, to = "TOP"), @IntToString(from = 80, to = "BOTTOM"), @IntToString(from = 3, to = "LEFT"), @IntToString(from = 5, to = "RIGHT"), @IntToString(from = 8388611, to = "START"), @IntToString(from = 8388613, to = "END"), @IntToString(from = 16, to = "CENTER_VERTICAL"), @IntToString(from = 112, to = "FILL_VERTICAL"), @IntToString(from = 1, to = "CENTER_HORIZONTAL"), @IntToString(from = 7, to = "FILL_HORIZONTAL"), @IntToString(from = 17, to = "CENTER"), @IntToString(from = 119, to = "FILL")})
  public int gravity = 0;
  
  public LayoutParams(int paramInt) {
    this(-2, -1, paramInt);
  }
  
  public LayoutParams(int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2);
    this.gravity = 8388627;
  }
  
  public LayoutParams(int paramInt1, int paramInt2, int paramInt3) {
    super(paramInt1, paramInt2);
    this.gravity = paramInt3;
  }
  
  public LayoutParams(LayoutParams paramLayoutParams) {
    super(paramLayoutParams);
    this.gravity = paramLayoutParams.gravity;
  }
  
  public LayoutParams(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionBar_LayoutParams);
    this.gravity = typedArray.getInt(0, 0);
    typedArray.recycle();
  }
  
  public LayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    super(paramLayoutParams);
  }
  
  protected void encodeProperties(ViewHierarchyEncoder paramViewHierarchyEncoder) {
    super.encodeProperties(paramViewHierarchyEncoder);
    paramViewHierarchyEncoder.addProperty("gravity", this.gravity);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActionBar$LayoutParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */