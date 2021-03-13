package android.telephony.data;

import java.util.List;

final class DataCallListChangedIndication {
  public final IDataServiceCallback callback;
  
  public final List<DataCallResponse> dataCallList;
  
  DataCallListChangedIndication(List<DataCallResponse> paramList, IDataServiceCallback paramIDataServiceCallback) {
    this.dataCallList = paramList;
    this.callback = paramIDataServiceCallback;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$DataCallListChangedIndication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */