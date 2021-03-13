package android.app.assist;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.view.ViewStructure;
import java.util.ArrayList;
import java.util.List;

final class HtmlInfoNode extends ViewStructure.HtmlInfo implements Parcelable {
  public static final Parcelable.Creator<HtmlInfoNode> CREATOR = new Parcelable.Creator<HtmlInfoNode>() {
      public AssistStructure.HtmlInfoNode createFromParcel(Parcel param2Parcel) {
        AssistStructure.HtmlInfoNodeBuilder htmlInfoNodeBuilder = new AssistStructure.HtmlInfoNodeBuilder(param2Parcel.readString());
        String[] arrayOfString2 = param2Parcel.readStringArray();
        String[] arrayOfString1 = param2Parcel.readStringArray();
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
      
      public AssistStructure.HtmlInfoNode[] newArray(int param2Int) {
        return new AssistStructure.HtmlInfoNode[param2Int];
      }
    };
  
  private ArrayList<Pair<String, String>> mAttributes;
  
  private final String[] mNames;
  
  private final String mTag;
  
  private final String[] mValues;
  
  private HtmlInfoNode(AssistStructure.HtmlInfoNodeBuilder paramHtmlInfoNodeBuilder) {
    this.mTag = AssistStructure.HtmlInfoNodeBuilder.access$800(paramHtmlInfoNodeBuilder);
    if (AssistStructure.HtmlInfoNodeBuilder.access$900(paramHtmlInfoNodeBuilder) == null) {
      this.mNames = null;
      this.mValues = null;
    } else {
      this.mNames = new String[AssistStructure.HtmlInfoNodeBuilder.access$900(paramHtmlInfoNodeBuilder).size()];
      this.mValues = new String[AssistStructure.HtmlInfoNodeBuilder.access$1000(paramHtmlInfoNodeBuilder).size()];
      AssistStructure.HtmlInfoNodeBuilder.access$900(paramHtmlInfoNodeBuilder).toArray((Object[])this.mNames);
      AssistStructure.HtmlInfoNodeBuilder.access$1000(paramHtmlInfoNodeBuilder).toArray((Object[])this.mValues);
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<Pair<String, String>> getAttributes() {
    if (this.mAttributes == null && this.mNames != null) {
      this.mAttributes = new ArrayList<>(this.mNames.length);
      byte b = 0;
      while (true) {
        String[] arrayOfString = this.mNames;
        if (b < arrayOfString.length) {
          Pair<String, String> pair = new Pair(arrayOfString[b], this.mValues[b]);
          this.mAttributes.add(b, pair);
          b++;
          continue;
        } 
        break;
      } 
    } 
    return this.mAttributes;
  }
  
  public String getTag() {
    return this.mTag;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mTag);
    paramParcel.writeStringArray(this.mNames);
    paramParcel.writeStringArray(this.mValues);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$HtmlInfoNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */