package android.content.pm;

import android.annotation.SystemApi;
import android.content.ComponentName;

@SystemApi
public class DataLoaderParams {
  private final DataLoaderParamsParcel mData;
  
  public DataLoaderParams(int paramInt, ComponentName paramComponentName, String paramString) {
    DataLoaderParamsParcel dataLoaderParamsParcel = new DataLoaderParamsParcel();
    dataLoaderParamsParcel.type = paramInt;
    dataLoaderParamsParcel.packageName = paramComponentName.getPackageName();
    dataLoaderParamsParcel.className = paramComponentName.getClassName();
    dataLoaderParamsParcel.arguments = paramString;
    this.mData = dataLoaderParamsParcel;
  }
  
  DataLoaderParams(DataLoaderParamsParcel paramDataLoaderParamsParcel) {
    this.mData = paramDataLoaderParamsParcel;
  }
  
  public static final DataLoaderParams forIncremental(ComponentName paramComponentName, String paramString) {
    return new DataLoaderParams(2, paramComponentName, paramString);
  }
  
  public static final DataLoaderParams forStreaming(ComponentName paramComponentName, String paramString) {
    return new DataLoaderParams(1, paramComponentName, paramString);
  }
  
  public final String getArguments() {
    return this.mData.arguments;
  }
  
  public final ComponentName getComponentName() {
    return new ComponentName(this.mData.packageName, this.mData.className);
  }
  
  public final DataLoaderParamsParcel getData() {
    return this.mData;
  }
  
  public final int getType() {
    return this.mData.type;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/DataLoaderParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */