package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

class MarshalerParcelable extends Marshaler<T> {
  private final Class<T> mClass;
  
  private final Parcelable.Creator<T> mCreator;
  
  protected MarshalerParcelable(TypeReference<T> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableParcelable, paramTypeReference, paramInt);
    Class<T> clazz = paramTypeReference.getRawType();
    this.mClass = clazz;
    try {
      Field field = clazz.getDeclaredField("CREATOR");
      try {
        this.mCreator = (Parcelable.Creator<T>)field.get(null);
        return;
      } catch (IllegalAccessException illegalAccessException) {
        throw new AssertionError(illegalAccessException);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new AssertionError(illegalArgumentException);
      } 
    } catch (NoSuchFieldException noSuchFieldException) {
      throw new AssertionError(noSuchFieldException);
    } 
  }
  
  public int calculateMarshalSize(T paramT) {
    Parcel parcel = Parcel.obtain();
    try {
      paramT.writeToParcel(parcel, 0);
      return (parcel.marshall()).length;
    } finally {
      parcel.recycle();
    } 
  }
  
  public int getNativeSize() {
    return NATIVE_SIZE_DYNAMIC;
  }
  
  public void marshal(T paramT, ByteBuffer paramByteBuffer) {
    Parcel parcel = Parcel.obtain();
    try {
      paramT.writeToParcel(parcel, 0);
      if (!parcel.hasFileDescriptors()) {
        byte[] arrayOfByte = parcel.marshall();
        parcel.recycle();
        if (arrayOfByte.length != 0)
          return; 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("No data marshaled for ");
        stringBuilder1.append(paramT);
        throw new AssertionError(stringBuilder1.toString());
      } 
      UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Parcelable ");
      stringBuilder.append(paramT);
      stringBuilder.append(" must not have file descriptors");
      this(stringBuilder.toString());
      throw unsupportedOperationException;
    } finally {
      parcel.recycle();
    } 
  }
  
  public T unmarshal(ByteBuffer paramByteBuffer) {
    paramByteBuffer.mark();
    Parcel parcel = Parcel.obtain();
    try {
      int i = paramByteBuffer.remaining();
      byte[] arrayOfByte = new byte[i];
      paramByteBuffer.get(arrayOfByte);
      parcel.unmarshall(arrayOfByte, 0, i);
      parcel.setDataPosition(0);
      Parcelable parcelable = (Parcelable)this.mCreator.createFromParcel(parcel);
      i = parcel.dataPosition();
      if (i != 0) {
        paramByteBuffer.reset();
        paramByteBuffer.position(paramByteBuffer.position() + i);
        return this.mClass.cast(parcelable);
      } 
      AssertionError assertionError = new AssertionError();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("No data marshaled for ");
      stringBuilder.append(parcelable);
      this(stringBuilder.toString());
      throw assertionError;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableParcelable$MarshalerParcelable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */