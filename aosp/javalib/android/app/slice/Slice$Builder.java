package android.app.slice;

import android.app.PendingIntent;
import android.app.RemoteInput;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Builder {
  private ArrayList<String> mHints = new ArrayList<>();
  
  private ArrayList<SliceItem> mItems = new ArrayList<>();
  
  private SliceSpec mSpec;
  
  private final Uri mUri;
  
  public Builder(Builder paramBuilder) {
    this.mUri = paramBuilder.mUri.buildUpon().appendPath("_gen").appendPath(String.valueOf(this.mItems.size())).build();
  }
  
  @Deprecated
  public Builder(Uri paramUri) {
    this.mUri = paramUri;
  }
  
  public Builder(Uri paramUri, SliceSpec paramSliceSpec) {
    this.mUri = paramUri;
    this.mSpec = paramSliceSpec;
  }
  
  public Builder addAction(PendingIntent paramPendingIntent, Slice paramSlice, String paramString) {
    Objects.requireNonNull(paramPendingIntent);
    Objects.requireNonNull(paramSlice);
    List<String> list = paramSlice.getHints();
    Slice.access$002(paramSlice, null);
    this.mItems.add(new SliceItem(paramPendingIntent, paramSlice, "action", paramString, list.<String>toArray(new String[list.size()])));
    return this;
  }
  
  public Builder addBundle(Bundle paramBundle, String paramString, List<String> paramList) {
    Objects.requireNonNull(paramBundle);
    this.mItems.add(new SliceItem(paramBundle, "bundle", paramString, paramList));
    return this;
  }
  
  public Builder addHints(List<String> paramList) {
    this.mHints.addAll(paramList);
    return this;
  }
  
  public Builder addIcon(Icon paramIcon, String paramString, List<String> paramList) {
    Objects.requireNonNull(paramIcon);
    this.mItems.add(new SliceItem(paramIcon, "image", paramString, paramList));
    return this;
  }
  
  public Builder addInt(int paramInt, String paramString, List<String> paramList) {
    this.mItems.add(new SliceItem(Integer.valueOf(paramInt), "int", paramString, paramList));
    return this;
  }
  
  public Builder addLong(long paramLong, String paramString, List<String> paramList) {
    this.mItems.add(new SliceItem(Long.valueOf(paramLong), "long", paramString, paramList.<String>toArray(new String[paramList.size()])));
    return this;
  }
  
  public Builder addRemoteInput(RemoteInput paramRemoteInput, String paramString, List<String> paramList) {
    Objects.requireNonNull(paramRemoteInput);
    this.mItems.add(new SliceItem(paramRemoteInput, "input", paramString, paramList));
    return this;
  }
  
  public Builder addSubSlice(Slice paramSlice, String paramString) {
    Objects.requireNonNull(paramSlice);
    this.mItems.add(new SliceItem(paramSlice, "slice", paramString, paramSlice.getHints().<String>toArray(new String[paramSlice.getHints().size()])));
    return this;
  }
  
  public Builder addText(CharSequence paramCharSequence, String paramString, List<String> paramList) {
    this.mItems.add(new SliceItem(paramCharSequence, "text", paramString, paramList));
    return this;
  }
  
  @Deprecated
  public Builder addTimestamp(long paramLong, String paramString, List<String> paramList) {
    return addLong(paramLong, paramString, paramList);
  }
  
  public Slice build() {
    ArrayList<SliceItem> arrayList = this.mItems;
    ArrayList<String> arrayList1 = this.mHints;
    return new Slice(arrayList, arrayList1.<String>toArray(new String[arrayList1.size()]), this.mUri, this.mSpec);
  }
  
  public Builder setCallerNeeded(boolean paramBoolean) {
    if (paramBoolean) {
      this.mHints.add("caller_needed");
    } else {
      this.mHints.remove("caller_needed");
    } 
    return this;
  }
  
  public Builder setSpec(SliceSpec paramSliceSpec) {
    this.mSpec = paramSliceSpec;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/Slice$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */