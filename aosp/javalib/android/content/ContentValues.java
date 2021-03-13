package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class ContentValues implements Parcelable {
  public static final Parcelable.Creator<ContentValues> CREATOR = new Parcelable.Creator<ContentValues>() {
      public ContentValues createFromParcel(Parcel param1Parcel) {
        return new ContentValues(param1Parcel);
      }
      
      public ContentValues[] newArray(int param1Int) {
        return new ContentValues[param1Int];
      }
    };
  
  public static final String TAG = "ContentValues";
  
  private final ArrayMap<String, Object> mMap;
  
  @Deprecated
  private HashMap<String, Object> mValues;
  
  public ContentValues() {
    this.mMap = new ArrayMap();
  }
  
  public ContentValues(int paramInt) {
    Preconditions.checkArgumentNonnegative(paramInt);
    this.mMap = new ArrayMap(paramInt);
  }
  
  public ContentValues(ContentValues paramContentValues) {
    Objects.requireNonNull(paramContentValues);
    this.mMap = new ArrayMap(paramContentValues.mMap);
  }
  
  private ContentValues(Parcel paramParcel) {
    ArrayMap<String, Object> arrayMap = new ArrayMap(paramParcel.readInt());
    this.mMap = arrayMap;
    paramParcel.readArrayMap(arrayMap, null);
  }
  
  @Deprecated
  private ContentValues(HashMap<String, Object> paramHashMap) {
    ArrayMap<String, Object> arrayMap = new ArrayMap();
    this.mMap = arrayMap;
    arrayMap.putAll(paramHashMap);
  }
  
  public static boolean isSupportedValue(Object paramObject) {
    return (paramObject == null) ? true : ((paramObject instanceof String) ? true : ((paramObject instanceof Byte) ? true : ((paramObject instanceof Short) ? true : ((paramObject instanceof Integer) ? true : ((paramObject instanceof Long) ? true : ((paramObject instanceof Float) ? true : ((paramObject instanceof Double) ? true : ((paramObject instanceof Boolean) ? true : ((paramObject instanceof byte[]))))))))));
  }
  
  public void clear() {
    this.mMap.clear();
  }
  
  public boolean containsKey(String paramString) {
    return this.mMap.containsKey(paramString);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof ContentValues) ? false : this.mMap.equals(((ContentValues)paramObject).mMap);
  }
  
  public Object get(String paramString) {
    return this.mMap.get(paramString);
  }
  
  public Boolean getAsBoolean(String paramString) {
    Object object = this.mMap.get(paramString);
    try {
      return (Boolean)object;
    } catch (ClassCastException classCastException) {
      boolean bool = object instanceof CharSequence;
      boolean bool1 = false;
      boolean bool2 = false;
      if (bool) {
        if (Boolean.valueOf(object.toString()).booleanValue() || "1".equals(object))
          bool2 = true; 
        return Boolean.valueOf(bool2);
      } 
      if (object instanceof Number) {
        bool2 = bool1;
        if (((Number)object).intValue() != 0)
          bool2 = true; 
        return Boolean.valueOf(bool2);
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot cast value for ");
      stringBuilder.append(paramString);
      stringBuilder.append(" to a Boolean: ");
      stringBuilder.append(object);
      Log.e("ContentValues", stringBuilder.toString(), classCastException);
      return null;
    } 
  }
  
  public Byte getAsByte(String paramString) {
    StringBuilder stringBuilder;
    Object object = this.mMap.get(paramString);
    Byte byte_ = null;
    if (object != null)
      try {
        byte_ = Byte.valueOf(((Number)object).byteValue());
      } catch (ClassCastException classCastException) {
        if (object instanceof CharSequence)
          try {
            return Byte.valueOf(object.toString());
          } catch (NumberFormatException numberFormatException) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Cannot parse Byte value for ");
            stringBuilder1.append(object);
            stringBuilder1.append(" at key ");
            stringBuilder1.append(paramString);
            Log.e("ContentValues", stringBuilder1.toString());
            return null;
          }  
        stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot cast value for ");
        stringBuilder.append(paramString);
        stringBuilder.append(" to a Byte: ");
        stringBuilder.append(object);
        Log.e("ContentValues", stringBuilder.toString(), classCastException);
        return null;
      }  
    return (Byte)stringBuilder;
  }
  
  public byte[] getAsByteArray(String paramString) {
    Object object = this.mMap.get(paramString);
    return (object instanceof byte[]) ? (byte[])object : null;
  }
  
  public Double getAsDouble(String paramString) {
    StringBuilder stringBuilder;
    Object object = this.mMap.get(paramString);
    Double double_ = null;
    if (object != null)
      try {
        double_ = Double.valueOf(((Number)object).doubleValue());
      } catch (ClassCastException classCastException) {
        if (object instanceof CharSequence)
          try {
            return Double.valueOf(object.toString());
          } catch (NumberFormatException numberFormatException) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot parse Double value for ");
            stringBuilder.append(object);
            stringBuilder.append(" at key ");
            stringBuilder.append(paramString);
            Log.e("ContentValues", stringBuilder.toString());
            return null;
          }  
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Cannot cast value for ");
        stringBuilder1.append(paramString);
        stringBuilder1.append(" to a Double: ");
        stringBuilder1.append(object);
        Log.e("ContentValues", stringBuilder1.toString(), (Throwable)stringBuilder);
        return null;
      }  
    return (Double)stringBuilder;
  }
  
  public Float getAsFloat(String paramString) {
    StringBuilder stringBuilder;
    Object object = this.mMap.get(paramString);
    Float float_ = null;
    if (object != null)
      try {
        float_ = Float.valueOf(((Number)object).floatValue());
      } catch (ClassCastException classCastException) {
        if (object instanceof CharSequence)
          try {
            return Float.valueOf(object.toString());
          } catch (NumberFormatException numberFormatException) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Cannot parse Float value for ");
            stringBuilder1.append(object);
            stringBuilder1.append(" at key ");
            stringBuilder1.append(paramString);
            Log.e("ContentValues", stringBuilder1.toString());
            return null;
          }  
        stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot cast value for ");
        stringBuilder.append(paramString);
        stringBuilder.append(" to a Float: ");
        stringBuilder.append(object);
        Log.e("ContentValues", stringBuilder.toString(), classCastException);
        return null;
      }  
    return (Float)stringBuilder;
  }
  
  public Integer getAsInteger(String paramString) {
    StringBuilder stringBuilder;
    Object object = this.mMap.get(paramString);
    Integer integer = null;
    if (object != null)
      try {
        integer = Integer.valueOf(((Number)object).intValue());
      } catch (ClassCastException classCastException) {
        if (object instanceof CharSequence)
          try {
            return Integer.valueOf(object.toString());
          } catch (NumberFormatException numberFormatException) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Cannot parse Integer value for ");
            stringBuilder1.append(object);
            stringBuilder1.append(" at key ");
            stringBuilder1.append(paramString);
            Log.e("ContentValues", stringBuilder1.toString());
            return null;
          }  
        stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot cast value for ");
        stringBuilder.append(paramString);
        stringBuilder.append(" to a Integer: ");
        stringBuilder.append(object);
        Log.e("ContentValues", stringBuilder.toString(), classCastException);
        return null;
      }  
    return (Integer)stringBuilder;
  }
  
  public Long getAsLong(String paramString) {
    StringBuilder stringBuilder;
    Object object = this.mMap.get(paramString);
    Long long_ = null;
    if (object != null)
      try {
        long_ = Long.valueOf(((Number)object).longValue());
      } catch (ClassCastException classCastException) {
        if (object instanceof CharSequence)
          try {
            return Long.valueOf(object.toString());
          } catch (NumberFormatException numberFormatException) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot parse Long value for ");
            stringBuilder.append(object);
            stringBuilder.append(" at key ");
            stringBuilder.append(paramString);
            Log.e("ContentValues", stringBuilder.toString());
            return null;
          }  
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Cannot cast value for ");
        stringBuilder1.append(paramString);
        stringBuilder1.append(" to a Long: ");
        stringBuilder1.append(object);
        Log.e("ContentValues", stringBuilder1.toString(), (Throwable)stringBuilder);
        return null;
      }  
    return (Long)stringBuilder;
  }
  
  public Short getAsShort(String paramString) {
    StringBuilder stringBuilder;
    Object object = this.mMap.get(paramString);
    Short short_ = null;
    if (object != null)
      try {
        short_ = Short.valueOf(((Number)object).shortValue());
      } catch (ClassCastException classCastException) {
        if (object instanceof CharSequence)
          try {
            return Short.valueOf(object.toString());
          } catch (NumberFormatException numberFormatException) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot parse Short value for ");
            stringBuilder.append(object);
            stringBuilder.append(" at key ");
            stringBuilder.append(paramString);
            Log.e("ContentValues", stringBuilder.toString());
            return null;
          }  
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Cannot cast value for ");
        stringBuilder1.append(paramString);
        stringBuilder1.append(" to a Short: ");
        stringBuilder1.append(object);
        Log.e("ContentValues", stringBuilder1.toString(), (Throwable)stringBuilder);
        return null;
      }  
    return (Short)stringBuilder;
  }
  
  public String getAsString(String paramString) {
    Object object = this.mMap.get(paramString);
    if (object != null) {
      object = object.toString();
    } else {
      object = null;
    } 
    return (String)object;
  }
  
  @Deprecated
  public ArrayList<String> getStringArrayList(String paramString) {
    return (ArrayList<String>)this.mMap.get(paramString);
  }
  
  public ArrayMap<String, Object> getValues() {
    return this.mMap;
  }
  
  public int hashCode() {
    return this.mMap.hashCode();
  }
  
  public boolean isEmpty() {
    return this.mMap.isEmpty();
  }
  
  public Set<String> keySet() {
    return this.mMap.keySet();
  }
  
  public void put(String paramString, Boolean paramBoolean) {
    this.mMap.put(paramString, paramBoolean);
  }
  
  public void put(String paramString, Byte paramByte) {
    this.mMap.put(paramString, paramByte);
  }
  
  public void put(String paramString, Double paramDouble) {
    this.mMap.put(paramString, paramDouble);
  }
  
  public void put(String paramString, Float paramFloat) {
    this.mMap.put(paramString, paramFloat);
  }
  
  public void put(String paramString, Integer paramInteger) {
    this.mMap.put(paramString, paramInteger);
  }
  
  public void put(String paramString, Long paramLong) {
    this.mMap.put(paramString, paramLong);
  }
  
  public void put(String paramString, Short paramShort) {
    this.mMap.put(paramString, paramShort);
  }
  
  public void put(String paramString1, String paramString2) {
    this.mMap.put(paramString1, paramString2);
  }
  
  public void put(String paramString, byte[] paramArrayOfbyte) {
    this.mMap.put(paramString, paramArrayOfbyte);
  }
  
  public void putAll(ContentValues paramContentValues) {
    this.mMap.putAll(paramContentValues.mMap);
  }
  
  public void putNull(String paramString) {
    this.mMap.put(paramString, null);
  }
  
  public void putObject(String paramString, Object paramObject) {
    if (paramObject == null) {
      putNull(paramString);
    } else if (paramObject instanceof String) {
      put(paramString, (String)paramObject);
    } else if (paramObject instanceof Byte) {
      put(paramString, (Byte)paramObject);
    } else if (paramObject instanceof Short) {
      put(paramString, (Short)paramObject);
    } else if (paramObject instanceof Integer) {
      put(paramString, (Integer)paramObject);
    } else if (paramObject instanceof Long) {
      put(paramString, (Long)paramObject);
    } else if (paramObject instanceof Float) {
      put(paramString, (Float)paramObject);
    } else if (paramObject instanceof Double) {
      put(paramString, (Double)paramObject);
    } else if (paramObject instanceof Boolean) {
      put(paramString, (Boolean)paramObject);
    } else {
      if (paramObject instanceof byte[]) {
        put(paramString, (byte[])paramObject);
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unsupported type ");
      stringBuilder.append(paramObject.getClass());
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  @Deprecated
  public void putStringArrayList(String paramString, ArrayList<String> paramArrayList) {
    this.mMap.put(paramString, paramArrayList);
  }
  
  public void remove(String paramString) {
    this.mMap.remove(paramString);
  }
  
  public int size() {
    return this.mMap.size();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (String str1 : this.mMap.keySet()) {
      String str2 = getAsString(str1);
      if (stringBuilder.length() > 0)
        stringBuilder.append(" "); 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str1);
      stringBuilder1.append("=");
      stringBuilder1.append(str2);
      stringBuilder.append(stringBuilder1.toString());
    } 
    return stringBuilder.toString();
  }
  
  public Set<Map.Entry<String, Object>> valueSet() {
    return this.mMap.entrySet();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mMap.size());
    paramParcel.writeArrayMap(this.mMap);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */