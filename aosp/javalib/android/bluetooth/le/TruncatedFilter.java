package android.bluetooth.le;

import android.annotation.SystemApi;
import java.util.List;

@SystemApi
public final class TruncatedFilter {
  private final ScanFilter mFilter;
  
  private final List<ResultStorageDescriptor> mStorageDescriptors;
  
  public TruncatedFilter(ScanFilter paramScanFilter, List<ResultStorageDescriptor> paramList) {
    this.mFilter = paramScanFilter;
    this.mStorageDescriptors = paramList;
  }
  
  public ScanFilter getFilter() {
    return this.mFilter;
  }
  
  public List<ResultStorageDescriptor> getStorageDescriptors() {
    return this.mStorageDescriptors;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/TruncatedFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */