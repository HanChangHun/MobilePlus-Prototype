package android.app.assist;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.PooledStringWriter;
import android.util.Log;
import java.util.ArrayList;

final class ParcelTransferWriter extends Binder {
  AssistStructure.ViewStackEntry mCurViewStackEntry;
  
  int mCurViewStackPos;
  
  int mCurWindow;
  
  int mNumWindows;
  
  int mNumWrittenViews;
  
  int mNumWrittenWindows;
  
  final boolean mSanitizeOnWrite;
  
  final float[] mTmpMatrix = new float[9];
  
  final ArrayList<AssistStructure.ViewStackEntry> mViewStack = new ArrayList<>();
  
  final boolean mWriteStructure;
  
  ParcelTransferWriter(AssistStructure paramAssistStructure, Parcel paramParcel) {
    this.mSanitizeOnWrite = AssistStructure.access$000(paramAssistStructure);
    this.mWriteStructure = paramAssistStructure.waitForReady();
    paramParcel.writeInt(AssistStructure.access$100(paramAssistStructure));
    paramParcel.writeInt(AssistStructure.access$200(paramAssistStructure));
    paramParcel.writeLong(AssistStructure.access$300(paramAssistStructure));
    paramParcel.writeLong(AssistStructure.access$400(paramAssistStructure));
    int i = AssistStructure.access$500(paramAssistStructure).size();
    this.mNumWindows = i;
    if (this.mWriteStructure && i > 0) {
      paramParcel.writeInt(i);
    } else {
      paramParcel.writeInt(0);
    } 
  }
  
  void pushViewStackEntry(AssistStructure.ViewNode paramViewNode, int paramInt) {
    AssistStructure.ViewStackEntry viewStackEntry;
    if (paramInt >= this.mViewStack.size()) {
      viewStackEntry = new AssistStructure.ViewStackEntry();
      this.mViewStack.add(viewStackEntry);
    } else {
      viewStackEntry = this.mViewStack.get(paramInt);
    } 
    viewStackEntry.node = paramViewNode;
    viewStackEntry.numChildren = paramViewNode.getChildCount();
    viewStackEntry.curChild = 0;
    this.mCurViewStackEntry = viewStackEntry;
  }
  
  boolean writeNextEntryToParcel(AssistStructure paramAssistStructure, Parcel paramParcel, PooledStringWriter paramPooledStringWriter) {
    AssistStructure.ViewStackEntry viewStackEntry1;
    AssistStructure.ViewStackEntry viewStackEntry2 = this.mCurViewStackEntry;
    if (viewStackEntry2 != null) {
      if (viewStackEntry2.curChild < this.mCurViewStackEntry.numChildren) {
        AssistStructure.ViewNode viewNode = this.mCurViewStackEntry.node.mChildren[this.mCurViewStackEntry.curChild];
        viewStackEntry1 = this.mCurViewStackEntry;
        viewStackEntry1.curChild++;
        writeView(viewNode, paramParcel, paramPooledStringWriter, 1);
        return true;
      } 
      do {
        int j = this.mCurViewStackPos - 1;
        this.mCurViewStackPos = j;
        if (j < 0) {
          this.mCurViewStackEntry = null;
          break;
        } 
        viewStackEntry1 = this.mViewStack.get(j);
        this.mCurViewStackEntry = viewStackEntry1;
      } while (viewStackEntry1.curChild >= this.mCurViewStackEntry.numChildren);
      return true;
    } 
    int i = this.mCurWindow;
    if (i < this.mNumWindows) {
      AssistStructure.WindowNode windowNode = AssistStructure.access$500((AssistStructure)viewStackEntry1).get(i);
      this.mCurWindow++;
      paramParcel.writeInt(286331153);
      windowNode.writeSelfToParcel(paramParcel, paramPooledStringWriter, this.mTmpMatrix);
      this.mNumWrittenWindows++;
      AssistStructure.ViewNode viewNode = windowNode.mRoot;
      this.mCurViewStackPos = 0;
      writeView(viewNode, paramParcel, paramPooledStringWriter, 0);
      return true;
    } 
    return false;
  }
  
  void writeToParcel(AssistStructure paramAssistStructure, Parcel paramParcel) {
    String str;
    int i = paramParcel.dataPosition();
    this.mNumWrittenWindows = 0;
    this.mNumWrittenViews = 0;
    boolean bool = writeToParcelInner(paramAssistStructure, paramParcel);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Flattened ");
    if (bool) {
      str = "partial";
    } else {
      str = "final";
    } 
    stringBuilder.append(str);
    stringBuilder.append(" assist data: ");
    stringBuilder.append(paramParcel.dataPosition() - i);
    stringBuilder.append(" bytes, containing ");
    stringBuilder.append(this.mNumWrittenWindows);
    stringBuilder.append(" windows, ");
    stringBuilder.append(this.mNumWrittenViews);
    stringBuilder.append(" views");
    Log.i("AssistStructure", stringBuilder.toString());
  }
  
  boolean writeToParcelInner(AssistStructure paramAssistStructure, Parcel paramParcel) {
    if (this.mNumWindows == 0)
      return false; 
    PooledStringWriter pooledStringWriter = new PooledStringWriter(paramParcel);
    while (writeNextEntryToParcel(paramAssistStructure, paramParcel, pooledStringWriter)) {
      if (paramParcel.dataSize() > 65536) {
        paramParcel.writeInt(0);
        paramParcel.writeStrongBinder((IBinder)this);
        pooledStringWriter.finish();
        return true;
      } 
    } 
    pooledStringWriter.finish();
    this.mViewStack.clear();
    return false;
  }
  
  void writeView(AssistStructure.ViewNode paramViewNode, Parcel paramParcel, PooledStringWriter paramPooledStringWriter, int paramInt) {
    paramParcel.writeInt(572662306);
    paramInt = paramViewNode.writeSelfToParcel(paramParcel, paramPooledStringWriter, this.mSanitizeOnWrite, this.mTmpMatrix);
    this.mNumWrittenViews++;
    if ((0x100000 & paramInt) != 0) {
      paramParcel.writeInt(paramViewNode.mChildren.length);
      paramInt = this.mCurViewStackPos + 1;
      this.mCurViewStackPos = paramInt;
      pushViewStackEntry(paramViewNode, paramInt);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$ParcelTransferWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */