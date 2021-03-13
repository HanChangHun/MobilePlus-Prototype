package android.hardware.camera2.marshal;

import android.hardware.camera2.utils.TypeReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MarshalRegistry {
  private static final Object sMarshalLock = new Object();
  
  private static final HashMap<MarshalToken<?>, Marshaler<?>> sMarshalerMap;
  
  private static final List<MarshalQueryable<?>> sRegisteredMarshalQueryables = new ArrayList<>();
  
  static {
    sMarshalerMap = new HashMap<>();
  }
  
  private MarshalRegistry() {
    throw new AssertionError();
  }
  
  public static <T> Marshaler<T> getMarshaler(TypeReference<T> paramTypeReference, int paramInt) {
    synchronized (sMarshalLock) {
      StringBuilder stringBuilder;
      MarshalToken<?> marshalToken = new MarshalToken();
      this(paramTypeReference, paramInt);
      Marshaler marshaler1 = sMarshalerMap.get(marshalToken);
      Marshaler marshaler2 = marshaler1;
      if (marshaler1 == null)
        if (sRegisteredMarshalQueryables.size() != 0) {
          Marshaler<T> marshaler;
          Iterator<MarshalQueryable<?>> iterator = sRegisteredMarshalQueryables.iterator();
          while (true) {
            marshaler2 = marshaler1;
            if (iterator.hasNext()) {
              MarshalQueryable<T> marshalQueryable = (MarshalQueryable)iterator.next();
              if (marshalQueryable.isTypeMappingSupported(paramTypeReference, paramInt)) {
                marshaler = marshalQueryable.createMarshaler(paramTypeReference, paramInt);
                break;
              } 
              continue;
            } 
            break;
          } 
          if (marshaler != null) {
            sMarshalerMap.put(marshalToken, marshaler);
          } else {
            UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();
            stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Could not find marshaler that matches the requested combination of type reference ");
            stringBuilder.append(paramTypeReference);
            stringBuilder.append(" and native type ");
            stringBuilder.append(MarshalHelpers.toStringNativeType(paramInt));
            this(stringBuilder.toString());
            throw unsupportedOperationException;
          } 
        } else {
          AssertionError assertionError = new AssertionError();
          this("No available query marshalers registered");
          throw assertionError;
        }  
      return (Marshaler<T>)stringBuilder;
    } 
  }
  
  public static <T> void registerMarshalQueryable(MarshalQueryable<T> paramMarshalQueryable) {
    synchronized (sMarshalLock) {
      sRegisteredMarshalQueryables.add(paramMarshalQueryable);
      return;
    } 
  }
  
  private static class MarshalToken<T> {
    private final int hash;
    
    final int nativeType;
    
    final TypeReference<T> typeReference;
    
    public MarshalToken(TypeReference<T> param1TypeReference, int param1Int) {
      this.typeReference = param1TypeReference;
      this.nativeType = param1Int;
      this.hash = param1TypeReference.hashCode() ^ param1Int;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof MarshalToken;
      boolean bool1 = false;
      if (bool) {
        param1Object = param1Object;
        bool = bool1;
        if (this.typeReference.equals(((MarshalToken)param1Object).typeReference)) {
          bool = bool1;
          if (this.nativeType == ((MarshalToken)param1Object).nativeType)
            bool = true; 
        } 
        return bool;
      } 
      return false;
    }
    
    public int hashCode() {
      return this.hash;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/MarshalRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */