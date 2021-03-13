package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.incremental.IIncrementalServiceConnector;
import android.os.incremental.IncrementalFileSystemControlParcel;

public class FileSystemControlParcel implements Parcelable {
  public static final Parcelable.Creator<FileSystemControlParcel> CREATOR = new Parcelable.Creator<FileSystemControlParcel>() {
      public FileSystemControlParcel createFromParcel(Parcel param1Parcel) {
        FileSystemControlParcel fileSystemControlParcel = new FileSystemControlParcel();
        fileSystemControlParcel.readFromParcel(param1Parcel);
        return fileSystemControlParcel;
      }
      
      public FileSystemControlParcel[] newArray(int param1Int) {
        return new FileSystemControlParcel[param1Int];
      }
    };
  
  public IPackageInstallerSessionFileSystemConnector callback;
  
  public IncrementalFileSystemControlParcel incremental;
  
  public IIncrementalServiceConnector service;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      if (paramParcel.readInt() != 0) {
        this.incremental = (IncrementalFileSystemControlParcel)IncrementalFileSystemControlParcel.CREATOR.createFromParcel(paramParcel);
      } else {
        this.incremental = null;
      } 
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.service = IIncrementalServiceConnector.Stub.asInterface(paramParcel.readStrongBinder());
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.callback = IPackageInstallerSessionFileSystemConnector.Stub.asInterface(paramParcel.readStrongBinder());
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder;
    int i = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    if (this.incremental != null) {
      paramParcel.writeInt(1);
      this.incremental.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    IIncrementalServiceConnector iIncrementalServiceConnector1 = this.service;
    IIncrementalServiceConnector iIncrementalServiceConnector2 = null;
    if (iIncrementalServiceConnector1 != null) {
      iBinder = iIncrementalServiceConnector1.asBinder();
    } else {
      iIncrementalServiceConnector1 = null;
    } 
    paramParcel.writeStrongBinder((IBinder)iIncrementalServiceConnector1);
    IPackageInstallerSessionFileSystemConnector iPackageInstallerSessionFileSystemConnector = this.callback;
    iIncrementalServiceConnector1 = iIncrementalServiceConnector2;
    if (iPackageInstallerSessionFileSystemConnector != null)
      iBinder = iPackageInstallerSessionFileSystemConnector.asBinder(); 
    paramParcel.writeStrongBinder(iBinder);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i);
    paramParcel.writeInt(paramInt - i);
    paramParcel.setDataPosition(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/FileSystemControlParcel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */