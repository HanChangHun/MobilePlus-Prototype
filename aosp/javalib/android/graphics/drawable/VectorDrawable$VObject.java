package android.graphics.drawable;

import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Property;
import com.android.internal.util.VirtualRefBasePtr;

abstract class VObject {
  VirtualRefBasePtr mTreePtr = null;
  
  abstract void applyTheme(Resources.Theme paramTheme);
  
  abstract boolean canApplyTheme();
  
  abstract long getNativePtr();
  
  abstract int getNativeSize();
  
  abstract Property getProperty(String paramString);
  
  abstract boolean hasFocusStateSpecified();
  
  abstract void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme);
  
  abstract boolean isStateful();
  
  boolean isTreeValid() {
    boolean bool;
    VirtualRefBasePtr virtualRefBasePtr = this.mTreePtr;
    if (virtualRefBasePtr != null && virtualRefBasePtr.get() != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  abstract boolean onStateChange(int[] paramArrayOfint);
  
  void setTree(VirtualRefBasePtr paramVirtualRefBasePtr) {
    this.mTreePtr = paramVirtualRefBasePtr;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */