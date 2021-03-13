package android.app.assist;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.PooledStringWriter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewRootImpl;

public class WindowNode {
  final int mDisplayId;
  
  final int mHeight;
  
  final AssistStructure.ViewNode mRoot;
  
  final CharSequence mTitle;
  
  final int mWidth;
  
  final int mX;
  
  final int mY;
  
  WindowNode(AssistStructure.ParcelTransferReader paramParcelTransferReader) {
    Parcel parcel = paramParcelTransferReader.readParcel(286331153, 0);
    paramParcelTransferReader.mNumReadWindows++;
    this.mX = parcel.readInt();
    this.mY = parcel.readInt();
    this.mWidth = parcel.readInt();
    this.mHeight = parcel.readInt();
    this.mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    this.mDisplayId = parcel.readInt();
    this.mRoot = new AssistStructure.ViewNode(paramParcelTransferReader, 0);
  }
  
  WindowNode(AssistStructure paramAssistStructure, ViewRootImpl paramViewRootImpl, boolean paramBoolean, int paramInt) {
    View view = paramViewRootImpl.getView();
    Rect rect = new Rect();
    view.getBoundsOnScreen(rect);
    this.mX = rect.left - view.getLeft();
    this.mY = rect.top - view.getTop();
    this.mWidth = rect.width();
    this.mHeight = rect.height();
    this.mTitle = paramViewRootImpl.getTitle();
    this.mDisplayId = paramViewRootImpl.getDisplayId();
    AssistStructure.ViewNode viewNode = new AssistStructure.ViewNode();
    this.mRoot = viewNode;
    AssistStructure.ViewNodeBuilder viewNodeBuilder = new AssistStructure.ViewNodeBuilder(paramAssistStructure, viewNode, false);
    if ((paramViewRootImpl.getWindowFlags() & 0x2000) != 0)
      if (paramBoolean) {
        view.onProvideAutofillStructure(viewNodeBuilder, resolveViewAutofillFlags(view.getContext(), paramInt));
      } else {
        view.onProvideStructure(viewNodeBuilder);
        viewNodeBuilder.setAssistBlocked(true);
        return;
      }  
    if (paramBoolean) {
      view.dispatchProvideAutofillStructure(viewNodeBuilder, resolveViewAutofillFlags(view.getContext(), paramInt));
    } else {
      view.dispatchProvideStructure(viewNodeBuilder);
    } 
  }
  
  public int getDisplayId() {
    return this.mDisplayId;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public int getLeft() {
    return this.mX;
  }
  
  public AssistStructure.ViewNode getRootViewNode() {
    return this.mRoot;
  }
  
  public CharSequence getTitle() {
    return this.mTitle;
  }
  
  public int getTop() {
    return this.mY;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  int resolveViewAutofillFlags(Context paramContext, int paramInt) {
    return ((paramInt & 0x1) != 0 || paramContext.isAutofillCompatibilityEnabled()) ? 1 : 0;
  }
  
  void writeSelfToParcel(Parcel paramParcel, PooledStringWriter paramPooledStringWriter, float[] paramArrayOffloat) {
    paramParcel.writeInt(this.mX);
    paramParcel.writeInt(this.mY);
    paramParcel.writeInt(this.mWidth);
    paramParcel.writeInt(this.mHeight);
    TextUtils.writeToParcel(this.mTitle, paramParcel, 0);
    paramParcel.writeInt(this.mDisplayId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$WindowNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */