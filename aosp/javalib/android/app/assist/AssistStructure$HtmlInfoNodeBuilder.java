package android.app.assist;

import android.view.ViewStructure;
import java.util.ArrayList;

final class HtmlInfoNodeBuilder extends ViewStructure.HtmlInfo.Builder {
  private ArrayList<String> mNames;
  
  private final String mTag;
  
  private ArrayList<String> mValues;
  
  HtmlInfoNodeBuilder(String paramString) {
    this.mTag = paramString;
  }
  
  public ViewStructure.HtmlInfo.Builder addAttribute(String paramString1, String paramString2) {
    if (this.mNames == null) {
      this.mNames = new ArrayList<>();
      this.mValues = new ArrayList<>();
    } 
    this.mNames.add(paramString1);
    this.mValues.add(paramString2);
    return this;
  }
  
  public AssistStructure.HtmlInfoNode build() {
    return new AssistStructure.HtmlInfoNode(this, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$HtmlInfoNodeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */