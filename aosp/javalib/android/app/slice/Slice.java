package android.app.slice;

import android.app.PendingIntent;
import android.app.RemoteInput;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.ArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Slice implements Parcelable {
  public static final Parcelable.Creator<Slice> CREATOR = new Parcelable.Creator<Slice>() {
      public Slice createFromParcel(Parcel param1Parcel) {
        return new Slice(param1Parcel);
      }
      
      public Slice[] newArray(int param1Int) {
        return new Slice[param1Int];
      }
    };
  
  public static final String EXTRA_RANGE_VALUE = "android.app.slice.extra.RANGE_VALUE";
  
  @Deprecated
  public static final String EXTRA_SLIDER_VALUE = "android.app.slice.extra.SLIDER_VALUE";
  
  public static final String EXTRA_TOGGLE_STATE = "android.app.slice.extra.TOGGLE_STATE";
  
  public static final String HINT_ACTIONS = "actions";
  
  public static final String HINT_CALLER_NEEDED = "caller_needed";
  
  public static final String HINT_ERROR = "error";
  
  public static final String HINT_HORIZONTAL = "horizontal";
  
  public static final String HINT_KEYWORDS = "keywords";
  
  public static final String HINT_LARGE = "large";
  
  public static final String HINT_LAST_UPDATED = "last_updated";
  
  public static final String HINT_LIST = "list";
  
  public static final String HINT_LIST_ITEM = "list_item";
  
  public static final String HINT_NO_TINT = "no_tint";
  
  public static final String HINT_PARTIAL = "partial";
  
  public static final String HINT_PERMISSION_REQUEST = "permission_request";
  
  public static final String HINT_SEE_MORE = "see_more";
  
  public static final String HINT_SELECTED = "selected";
  
  public static final String HINT_SHORTCUT = "shortcut";
  
  public static final String HINT_SUMMARY = "summary";
  
  public static final String HINT_TITLE = "title";
  
  public static final String HINT_TOGGLE = "toggle";
  
  public static final String HINT_TTL = "ttl";
  
  public static final String SUBTYPE_COLOR = "color";
  
  public static final String SUBTYPE_CONTENT_DESCRIPTION = "content_description";
  
  public static final String SUBTYPE_LAYOUT_DIRECTION = "layout_direction";
  
  public static final String SUBTYPE_MAX = "max";
  
  public static final String SUBTYPE_MESSAGE = "message";
  
  public static final String SUBTYPE_MILLIS = "millis";
  
  public static final String SUBTYPE_PRIORITY = "priority";
  
  public static final String SUBTYPE_RANGE = "range";
  
  @Deprecated
  public static final String SUBTYPE_SLIDER = "slider";
  
  public static final String SUBTYPE_SOURCE = "source";
  
  public static final String SUBTYPE_TOGGLE = "toggle";
  
  public static final String SUBTYPE_VALUE = "value";
  
  private final String[] mHints;
  
  private final SliceItem[] mItems;
  
  private SliceSpec mSpec;
  
  private Uri mUri;
  
  protected Slice(Parcel paramParcel) {
    this.mHints = paramParcel.readStringArray();
    int i = paramParcel.readInt();
    this.mItems = new SliceItem[i];
    for (byte b = 0; b < i; b++)
      this.mItems[b] = (SliceItem)SliceItem.CREATOR.createFromParcel(paramParcel); 
    this.mUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel);
    this.mSpec = (SliceSpec)paramParcel.readTypedObject(SliceSpec.CREATOR);
  }
  
  Slice(ArrayList<SliceItem> paramArrayList, String[] paramArrayOfString, Uri paramUri, SliceSpec paramSliceSpec) {
    this.mHints = paramArrayOfString;
    this.mItems = paramArrayList.<SliceItem>toArray(new SliceItem[paramArrayList.size()]);
    this.mUri = paramUri;
    this.mSpec = paramSliceSpec;
  }
  
  private String toString(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < this.mItems.length; b++) {
      stringBuilder.append(paramString);
      if (Objects.equals(this.mItems[b].getFormat(), "slice")) {
        stringBuilder.append("slice:\n");
        Slice slice = this.mItems[b].getSlice();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString);
        stringBuilder1.append("   ");
        stringBuilder.append(slice.toString(stringBuilder1.toString()));
      } else if (Objects.equals(this.mItems[b].getFormat(), "text")) {
        stringBuilder.append("text: ");
        stringBuilder.append(this.mItems[b].getText());
        stringBuilder.append("\n");
      } else {
        stringBuilder.append(this.mItems[b].getFormat());
        stringBuilder.append("\n");
      } 
    } 
    return stringBuilder.toString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<String> getHints() {
    return Arrays.asList(this.mHints);
  }
  
  public List<SliceItem> getItems() {
    return Arrays.asList(this.mItems);
  }
  
  public SliceSpec getSpec() {
    return this.mSpec;
  }
  
  public Uri getUri() {
    return this.mUri;
  }
  
  public boolean hasHint(String paramString) {
    return ArrayUtils.contains((Object[])this.mHints, paramString);
  }
  
  public boolean isCallerNeeded() {
    return hasHint("caller_needed");
  }
  
  public String toString() {
    return toString("");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStringArray(this.mHints);
    paramParcel.writeInt(this.mItems.length);
    byte b = 0;
    while (true) {
      SliceItem[] arrayOfSliceItem = this.mItems;
      if (b < arrayOfSliceItem.length) {
        arrayOfSliceItem[b].writeToParcel(paramParcel, paramInt);
        b++;
        continue;
      } 
      this.mUri.writeToParcel(paramParcel, 0);
      paramParcel.writeTypedObject(this.mSpec, paramInt);
      return;
    } 
  }
  
  public static class Builder {
    private ArrayList<String> mHints = new ArrayList<>();
    
    private ArrayList<SliceItem> mItems = new ArrayList<>();
    
    private SliceSpec mSpec;
    
    private final Uri mUri;
    
    public Builder(Builder param1Builder) {
      this.mUri = param1Builder.mUri.buildUpon().appendPath("_gen").appendPath(String.valueOf(this.mItems.size())).build();
    }
    
    @Deprecated
    public Builder(Uri param1Uri) {
      this.mUri = param1Uri;
    }
    
    public Builder(Uri param1Uri, SliceSpec param1SliceSpec) {
      this.mUri = param1Uri;
      this.mSpec = param1SliceSpec;
    }
    
    public Builder addAction(PendingIntent param1PendingIntent, Slice param1Slice, String param1String) {
      Objects.requireNonNull(param1PendingIntent);
      Objects.requireNonNull(param1Slice);
      List<String> list = param1Slice.getHints();
      Slice.access$002(param1Slice, null);
      this.mItems.add(new SliceItem(param1PendingIntent, param1Slice, "action", param1String, list.<String>toArray(new String[list.size()])));
      return this;
    }
    
    public Builder addBundle(Bundle param1Bundle, String param1String, List<String> param1List) {
      Objects.requireNonNull(param1Bundle);
      this.mItems.add(new SliceItem(param1Bundle, "bundle", param1String, param1List));
      return this;
    }
    
    public Builder addHints(List<String> param1List) {
      this.mHints.addAll(param1List);
      return this;
    }
    
    public Builder addIcon(Icon param1Icon, String param1String, List<String> param1List) {
      Objects.requireNonNull(param1Icon);
      this.mItems.add(new SliceItem(param1Icon, "image", param1String, param1List));
      return this;
    }
    
    public Builder addInt(int param1Int, String param1String, List<String> param1List) {
      this.mItems.add(new SliceItem(Integer.valueOf(param1Int), "int", param1String, param1List));
      return this;
    }
    
    public Builder addLong(long param1Long, String param1String, List<String> param1List) {
      this.mItems.add(new SliceItem(Long.valueOf(param1Long), "long", param1String, param1List.<String>toArray(new String[param1List.size()])));
      return this;
    }
    
    public Builder addRemoteInput(RemoteInput param1RemoteInput, String param1String, List<String> param1List) {
      Objects.requireNonNull(param1RemoteInput);
      this.mItems.add(new SliceItem(param1RemoteInput, "input", param1String, param1List));
      return this;
    }
    
    public Builder addSubSlice(Slice param1Slice, String param1String) {
      Objects.requireNonNull(param1Slice);
      this.mItems.add(new SliceItem(param1Slice, "slice", param1String, param1Slice.getHints().<String>toArray(new String[param1Slice.getHints().size()])));
      return this;
    }
    
    public Builder addText(CharSequence param1CharSequence, String param1String, List<String> param1List) {
      this.mItems.add(new SliceItem(param1CharSequence, "text", param1String, param1List));
      return this;
    }
    
    @Deprecated
    public Builder addTimestamp(long param1Long, String param1String, List<String> param1List) {
      return addLong(param1Long, param1String, param1List);
    }
    
    public Slice build() {
      ArrayList<SliceItem> arrayList = this.mItems;
      ArrayList<String> arrayList1 = this.mHints;
      return new Slice(arrayList, arrayList1.<String>toArray(new String[arrayList1.size()]), this.mUri, this.mSpec);
    }
    
    public Builder setCallerNeeded(boolean param1Boolean) {
      if (param1Boolean) {
        this.mHints.add("caller_needed");
      } else {
        this.mHints.remove("caller_needed");
      } 
      return this;
    }
    
    public Builder setSpec(SliceSpec param1SliceSpec) {
      this.mSpec = param1SliceSpec;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SliceHint {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SliceSubtype {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/Slice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */