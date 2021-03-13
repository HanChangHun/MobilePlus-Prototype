package android.app.assist;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

class null implements Parcelable.Creator<AssistStructure.HtmlInfoNode> {
  public AssistStructure.HtmlInfoNode createFromParcel(Parcel paramParcel) {
    AssistStructure.HtmlInfoNodeBuilder htmlInfoNodeBuilder = new AssistStructure.HtmlInfoNodeBuilder(paramParcel.readString());
    String[] arrayOfString2 = paramParcel.readStringArray();
    String[] arrayOfString1 = paramParcel.readStringArray();
    if (arrayOfString2 != null && arrayOfString1 != null)
      if (arrayOfString2.length != arrayOfString1.length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HtmlInfo attributes mismatch: names=");
        stringBuilder.append(arrayOfString2.length);
        stringBuilder.append(", values=");
        stringBuilder.append(arrayOfString1.length);
        Log.w("AssistStructure", stringBuilder.toString());
      } else {
        for (byte b = 0; b < arrayOfString2.length; b++)
          htmlInfoNodeBuilder.addAttribute(arrayOfString2[b], arrayOfString1[b]); 
      }  
    return htmlInfoNodeBuilder.build();
  }
  
  public AssistStructure.HtmlInfoNode[] newArray(int paramInt) {
    return new AssistStructure.HtmlInfoNode[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$HtmlInfoNode$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */