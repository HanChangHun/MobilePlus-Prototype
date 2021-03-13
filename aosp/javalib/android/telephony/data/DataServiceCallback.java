package android.telephony.data;

import android.annotation.SystemApi;
import android.os.RemoteException;
import com.android.telephony.Rlog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@SystemApi
public class DataServiceCallback {
  private static final boolean DBG = true;
  
  public static final int RESULT_ERROR_BUSY = 3;
  
  public static final int RESULT_ERROR_ILLEGAL_STATE = 4;
  
  public static final int RESULT_ERROR_INVALID_ARG = 2;
  
  public static final int RESULT_ERROR_UNSUPPORTED = 1;
  
  public static final int RESULT_SUCCESS = 0;
  
  private static final String TAG = DataServiceCallback.class.getSimpleName();
  
  private final IDataServiceCallback mCallback;
  
  public DataServiceCallback(IDataServiceCallback paramIDataServiceCallback) {
    this.mCallback = paramIDataServiceCallback;
  }
  
  public void onDataCallListChanged(List<DataCallResponse> paramList) {
    if (this.mCallback != null) {
      try {
        Rlog.d(TAG, "onDataCallListChanged");
        this.mCallback.onDataCallListChanged(paramList);
      } catch (RemoteException remoteException) {
        Rlog.e(TAG, "Failed to onDataCallListChanged on the remote");
      } 
    } else {
      Rlog.e(TAG, "onDataCallListChanged: callback is null!");
    } 
  }
  
  public void onDeactivateDataCallComplete(int paramInt) {
    if (this.mCallback != null) {
      try {
        Rlog.d(TAG, "onDeactivateDataCallComplete");
        this.mCallback.onDeactivateDataCallComplete(paramInt);
      } catch (RemoteException remoteException) {
        Rlog.e(TAG, "Failed to onDeactivateDataCallComplete on the remote");
      } 
    } else {
      Rlog.e(TAG, "onDeactivateDataCallComplete: callback is null!");
    } 
  }
  
  public void onRequestDataCallListComplete(int paramInt, List<DataCallResponse> paramList) {
    IDataServiceCallback iDataServiceCallback = this.mCallback;
    if (iDataServiceCallback != null) {
      try {
        iDataServiceCallback.onRequestDataCallListComplete(paramInt, paramList);
      } catch (RemoteException remoteException) {
        Rlog.e(TAG, "Failed to onRequestDataCallListComplete on the remote");
      } 
    } else {
      Rlog.e(TAG, "onRequestDataCallListComplete: callback is null!");
    } 
  }
  
  public void onSetDataProfileComplete(int paramInt) {
    IDataServiceCallback iDataServiceCallback = this.mCallback;
    if (iDataServiceCallback != null) {
      try {
        iDataServiceCallback.onSetDataProfileComplete(paramInt);
      } catch (RemoteException remoteException) {
        Rlog.e(TAG, "Failed to onSetDataProfileComplete on the remote");
      } 
    } else {
      Rlog.e(TAG, "onSetDataProfileComplete: callback is null!");
    } 
  }
  
  public void onSetInitialAttachApnComplete(int paramInt) {
    IDataServiceCallback iDataServiceCallback = this.mCallback;
    if (iDataServiceCallback != null) {
      try {
        iDataServiceCallback.onSetInitialAttachApnComplete(paramInt);
      } catch (RemoteException remoteException) {
        Rlog.e(TAG, "Failed to onSetInitialAttachApnComplete on the remote");
      } 
    } else {
      Rlog.e(TAG, "onSetInitialAttachApnComplete: callback is null!");
    } 
  }
  
  public void onSetupDataCallComplete(int paramInt, DataCallResponse paramDataCallResponse) {
    if (this.mCallback != null) {
      try {
        Rlog.d(TAG, "onSetupDataCallComplete");
        this.mCallback.onSetupDataCallComplete(paramInt, paramDataCallResponse);
      } catch (RemoteException remoteException) {
        Rlog.e(TAG, "Failed to onSetupDataCallComplete on the remote");
      } 
    } else {
      Rlog.e(TAG, "onSetupDataCallComplete: callback is null!");
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ResultCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataServiceCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */