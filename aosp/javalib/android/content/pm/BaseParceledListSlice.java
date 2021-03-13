package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

abstract class BaseParceledListSlice<T> implements Parcelable {
  private static boolean DEBUG;
  
  private static final int MAX_IPC_SIZE;
  
  private static String TAG = "ParceledListSlice";
  
  private int mInlineCountLimit = Integer.MAX_VALUE;
  
  private final List<T> mList;
  
  static {
    DEBUG = false;
    MAX_IPC_SIZE = IBinder.getSuggestedMaxIpcSizeBytes();
  }
  
  BaseParceledListSlice(Parcel paramParcel, ClassLoader paramClassLoader) {
    int i = paramParcel.readInt();
    this.mList = new ArrayList<>(i);
    if (DEBUG) {
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Retrieving ");
      stringBuilder.append(i);
      stringBuilder.append(" items");
      Log.d(str, stringBuilder.toString());
    } 
    if (i <= 0)
      return; 
    Parcelable.Creator<?> creator = readParcelableCreator(paramParcel, paramClassLoader);
    Class<?> clazz = null;
    byte b;
    for (b = 0; b < i && paramParcel.readInt() != 0; b++) {
      T t = readCreator(creator, paramParcel, paramClassLoader);
      if (clazz == null) {
        clazz = t.getClass();
      } else {
        verifySameType(clazz, t.getClass());
      } 
      this.mList.add(t);
      if (DEBUG) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Read inline #");
        stringBuilder.append(b);
        stringBuilder.append(": ");
        List<T> list = this.mList;
        stringBuilder.append(list.get(list.size() - 1));
        Log.d(str, stringBuilder.toString());
      } 
    } 
    if (b >= i)
      return; 
    IBinder iBinder = paramParcel.readStrongBinder();
    while (b < i) {
      if (DEBUG) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Reading more @");
        stringBuilder.append(b);
        stringBuilder.append(" of ");
        stringBuilder.append(i);
        stringBuilder.append(": retriever=");
        stringBuilder.append(iBinder);
        Log.d(str, stringBuilder.toString());
      } 
      Parcel parcel2 = Parcel.obtain();
      Parcel parcel1 = Parcel.obtain();
      parcel2.writeInt(b);
      try {
        iBinder.transact(1, parcel2, parcel1, 0);
        while (b < i && parcel1.readInt() != 0) {
          T t = readCreator(creator, parcel1, paramClassLoader);
          verifySameType(clazz, t.getClass());
          this.mList.add(t);
          if (DEBUG) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Read extra #");
            stringBuilder.append(b);
            stringBuilder.append(": ");
            List<T> list = this.mList;
            stringBuilder.append(list.get(list.size() - 1));
            Log.d(str, stringBuilder.toString());
          } 
          b++;
        } 
        parcel1.recycle();
        parcel2.recycle();
      } catch (RemoteException remoteException) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failure retrieving array; only received ");
        stringBuilder.append(b);
        stringBuilder.append(" of ");
        stringBuilder.append(i);
        Log.w(str, stringBuilder.toString(), (Throwable)remoteException);
        return;
      } 
    } 
  }
  
  public BaseParceledListSlice(List<T> paramList) {
    this.mList = paramList;
  }
  
  private T readCreator(Parcelable.Creator<?> paramCreator, Parcel paramParcel, ClassLoader paramClassLoader) {
    return (T)((paramCreator instanceof Parcelable.ClassLoaderCreator) ? ((Parcelable.ClassLoaderCreator)paramCreator).createFromParcel(paramParcel, paramClassLoader) : paramCreator.createFromParcel(paramParcel));
  }
  
  private static void verifySameType(Class<?> paramClass1, Class<?> paramClass2) {
    if (!paramClass2.equals(paramClass1)) {
      String str1;
      String str2;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't unparcel type ");
      Class clazz = null;
      if (paramClass2 == null) {
        paramClass2 = null;
      } else {
        str2 = paramClass2.getName();
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" in list of type ");
      if (paramClass1 == null) {
        paramClass1 = clazz;
      } else {
        str1 = paramClass1.getName();
      } 
      stringBuilder.append(str1);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  public List<T> getList() {
    return this.mList;
  }
  
  protected abstract Parcelable.Creator<?> readParcelableCreator(Parcel paramParcel, ClassLoader paramClassLoader);
  
  public void setInlineCountLimit(int paramInt) {
    this.mInlineCountLimit = paramInt;
  }
  
  protected abstract void writeElement(T paramT, Parcel paramParcel, int paramInt);
  
  protected abstract void writeParcelableCreator(T paramT, Parcel paramParcel);
  
  public void writeToParcel(Parcel paramParcel, final int callFlags) {
    final int N = this.mList.size();
    paramParcel.writeInt(i);
    if (DEBUG) {
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Writing ");
      stringBuilder.append(i);
      stringBuilder.append(" items");
      Log.d(str, stringBuilder.toString());
    } 
    if (i > 0) {
      final Class<?> listElementClass = this.mList.get(0).getClass();
      writeParcelableCreator(this.mList.get(0), paramParcel);
      byte b;
      for (b = 0; b < i && b < this.mInlineCountLimit && paramParcel.dataSize() < MAX_IPC_SIZE; b++) {
        paramParcel.writeInt(1);
        T t = this.mList.get(b);
        verifySameType(clazz, t.getClass());
        writeElement(t, paramParcel, callFlags);
        if (DEBUG) {
          String str = TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Wrote inline #");
          stringBuilder.append(b);
          stringBuilder.append(": ");
          stringBuilder.append(this.mList.get(b));
          Log.d(str, stringBuilder.toString());
        } 
      } 
      if (b < i) {
        paramParcel.writeInt(0);
        Binder binder = new Binder() {
            protected boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
              if (param1Int1 != 1)
                return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
              param1Int2 = param1Parcel1.readInt();
              param1Int1 = param1Int2;
              if (BaseParceledListSlice.DEBUG) {
                String str = BaseParceledListSlice.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Writing more @");
                stringBuilder.append(param1Int2);
                stringBuilder.append(" of ");
                stringBuilder.append(N);
                Log.d(str, stringBuilder.toString());
                param1Int1 = param1Int2;
              } 
              while (param1Int1 < N && param1Parcel2.dataSize() < 65536) {
                param1Parcel2.writeInt(1);
                param1Parcel1 = BaseParceledListSlice.this.mList.get(param1Int1);
                BaseParceledListSlice.verifySameType(listElementClass, param1Parcel1.getClass());
                BaseParceledListSlice.this.writeElement(param1Parcel1, param1Parcel2, callFlags);
                if (BaseParceledListSlice.DEBUG) {
                  String str = BaseParceledListSlice.TAG;
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("Wrote extra #");
                  stringBuilder.append(param1Int1);
                  stringBuilder.append(": ");
                  stringBuilder.append(BaseParceledListSlice.this.mList.get(param1Int1));
                  Log.d(str, stringBuilder.toString());
                } 
                param1Int1++;
              } 
              if (param1Int1 < N) {
                if (BaseParceledListSlice.DEBUG) {
                  String str = BaseParceledListSlice.TAG;
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("Breaking @");
                  stringBuilder.append(param1Int1);
                  stringBuilder.append(" of ");
                  stringBuilder.append(N);
                  Log.d(str, stringBuilder.toString());
                } 
                param1Parcel2.writeInt(0);
              } 
              return true;
            }
          };
        if (DEBUG) {
          String str = TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Breaking @");
          stringBuilder.append(b);
          stringBuilder.append(" of ");
          stringBuilder.append(i);
          stringBuilder.append(": retriever=");
          stringBuilder.append(binder);
          Log.d(str, stringBuilder.toString());
        } 
        paramParcel.writeStrongBinder((IBinder)binder);
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/BaseParceledListSlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */