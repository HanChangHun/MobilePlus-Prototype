package android.content;

import android.net.Uri;
import java.util.ArrayList;

public final class Entity {
  private final ArrayList<NamedContentValues> mSubValues;
  
  private final ContentValues mValues;
  
  public Entity(ContentValues paramContentValues) {
    this.mValues = paramContentValues;
    this.mSubValues = new ArrayList<>();
  }
  
  public void addSubValue(Uri paramUri, ContentValues paramContentValues) {
    this.mSubValues.add(new NamedContentValues(paramUri, paramContentValues));
  }
  
  public ContentValues getEntityValues() {
    return this.mValues;
  }
  
  public ArrayList<NamedContentValues> getSubValues() {
    return this.mSubValues;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Entity: ");
    stringBuilder.append(getEntityValues());
    for (NamedContentValues namedContentValues : getSubValues()) {
      stringBuilder.append("\n  ");
      stringBuilder.append(namedContentValues.uri);
      stringBuilder.append("\n  -> ");
      stringBuilder.append(namedContentValues.values);
    } 
    return stringBuilder.toString();
  }
  
  public static class NamedContentValues {
    public final Uri uri;
    
    public final ContentValues values;
    
    public NamedContentValues(Uri param1Uri, ContentValues param1ContentValues) {
      this.uri = param1Uri;
      this.values = param1ContentValues;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */