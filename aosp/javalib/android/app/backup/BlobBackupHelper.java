package android.app.backup;

import android.os.ParcelFileDescriptor;
import android.util.ArrayMap;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public abstract class BlobBackupHelper implements BackupHelper {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "BlobBackupHelper";
  
  private final int mCurrentBlobVersion;
  
  private final String[] mKeys;
  
  public BlobBackupHelper(int paramInt, String... paramVarArgs) {
    this.mCurrentBlobVersion = paramInt;
    this.mKeys = paramVarArgs;
  }
  
  private long checksum(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null)
      try {
        CRC32 cRC32 = new CRC32();
        this();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
        this(paramArrayOfbyte);
        paramArrayOfbyte = new byte[4096];
        while (true) {
          int i = byteArrayInputStream.read(paramArrayOfbyte);
          if (i >= 0) {
            cRC32.update(paramArrayOfbyte, 0, i);
            continue;
          } 
          return cRC32.getValue();
        } 
      } catch (Exception exception) {} 
    return -1L;
  }
  
  private byte[] deflate(byte[] paramArrayOfbyte) {
    ByteArrayOutputStream byteArrayOutputStream1 = null;
    ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream1;
    if (paramArrayOfbyte != null)
      try {
        byteArrayOutputStream2 = new ByteArrayOutputStream();
        this();
        DataOutputStream dataOutputStream = new DataOutputStream();
        this(byteArrayOutputStream2);
        dataOutputStream.writeInt(this.mCurrentBlobVersion);
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream();
        this(byteArrayOutputStream2);
        deflaterOutputStream.write(paramArrayOfbyte);
        deflaterOutputStream.close();
        byte[] arrayOfByte = byteArrayOutputStream2.toByteArray();
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to process payload: ");
        stringBuilder.append(iOException.getMessage());
        Log.w("BlobBackupHelper", stringBuilder.toString());
        byteArrayOutputStream2 = byteArrayOutputStream1;
      }  
    return (byte[])byteArrayOutputStream2;
  }
  
  private byte[] inflate(byte[] paramArrayOfbyte) {
    ByteArrayInputStream byteArrayInputStream1 = null;
    ByteArrayInputStream byteArrayInputStream2 = byteArrayInputStream1;
    if (paramArrayOfbyte != null)
      try {
        byteArrayInputStream2 = new ByteArrayInputStream();
        this(paramArrayOfbyte);
        DataInputStream dataInputStream = new DataInputStream();
        this(byteArrayInputStream2);
        int i = dataInputStream.readInt();
        if (i > this.mCurrentBlobVersion) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Saved payload from unrecognized version ");
          stringBuilder.append(i);
          Log.w("BlobBackupHelper", stringBuilder.toString());
          return null;
        } 
        InflaterInputStream inflaterInputStream = new InflaterInputStream();
        this(byteArrayInputStream2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this();
        byte[] arrayOfByte = new byte[4096];
        while (true) {
          i = inflaterInputStream.read(arrayOfByte);
          if (i > 0) {
            byteArrayOutputStream.write(arrayOfByte, 0, i);
            continue;
          } 
          inflaterInputStream.close();
          byteArrayOutputStream.flush();
          arrayOfByte = byteArrayOutputStream.toByteArray();
          break;
        } 
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to process restored payload: ");
        stringBuilder.append(iOException.getMessage());
        Log.w("BlobBackupHelper", stringBuilder.toString());
        byteArrayInputStream2 = byteArrayInputStream1;
      }  
    return (byte[])byteArrayInputStream2;
  }
  
  private ArrayMap<String, Long> readOldState(ParcelFileDescriptor paramParcelFileDescriptor) {
    ArrayMap<String, Long> arrayMap = new ArrayMap();
    DataInputStream dataInputStream = new DataInputStream(new FileInputStream(paramParcelFileDescriptor.getFileDescriptor()));
    try {
      int i = dataInputStream.readInt();
      if (i <= this.mCurrentBlobVersion) {
        int j = dataInputStream.readInt();
        for (i = 0; i < j; i++)
          arrayMap.put(dataInputStream.readUTF(), Long.valueOf(dataInputStream.readLong())); 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Prior state from unrecognized version ");
        stringBuilder.append(i);
        Log.w("BlobBackupHelper", stringBuilder.toString());
      } 
    } catch (EOFException eOFException) {
      arrayMap.clear();
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error examining prior backup state ");
      stringBuilder.append(exception.getMessage());
      Log.e("BlobBackupHelper", stringBuilder.toString());
      arrayMap.clear();
    } 
    return arrayMap;
  }
  
  private void writeBackupState(ArrayMap<String, Long> paramArrayMap, ParcelFileDescriptor paramParcelFileDescriptor) {
    try {
      byte b1;
      FileOutputStream fileOutputStream = new FileOutputStream();
      this(paramParcelFileDescriptor.getFileDescriptor());
      DataOutputStream dataOutputStream = new DataOutputStream();
      this(fileOutputStream);
      dataOutputStream.writeInt(this.mCurrentBlobVersion);
      if (paramArrayMap != null) {
        b1 = paramArrayMap.size();
      } else {
        b1 = 0;
      } 
      dataOutputStream.writeInt(b1);
      for (byte b2 = 0; b2 < b1; b2++) {
        String str = (String)paramArrayMap.keyAt(b2);
        long l = ((Long)paramArrayMap.valueAt(b2)).longValue();
        dataOutputStream.writeUTF(str);
        dataOutputStream.writeLong(l);
      } 
    } catch (IOException iOException) {
      Log.e("BlobBackupHelper", "Unable to write updated state", iOException);
    } 
  }
  
  protected abstract void applyRestoredPayload(String paramString, byte[] paramArrayOfbyte);
  
  protected abstract byte[] getBackupPayload(String paramString);
  
  public void performBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2) {
    ArrayMap<String, Long> arrayMap2 = readOldState(paramParcelFileDescriptor1);
    ArrayMap<String, Long> arrayMap1 = new ArrayMap();
    try {
      for (String str : this.mKeys) {
        byte[] arrayOfByte = deflate(getBackupPayload(str));
        long l = checksum(arrayOfByte);
        arrayMap1.put(str, Long.valueOf(l));
        Long long_ = (Long)arrayMap2.get(str);
        if (long_ == null || l != long_.longValue())
          if (arrayOfByte != null) {
            paramBackupDataOutput.writeEntityHeader(str, arrayOfByte.length);
            paramBackupDataOutput.writeEntityData(arrayOfByte, arrayOfByte.length);
          } else {
            paramBackupDataOutput.writeEntityHeader(str, -1);
          }  
      } 
      writeBackupState(arrayMap1, paramParcelFileDescriptor2);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Unable to record notification state: ");
      stringBuilder.append(exception.getMessage());
      Log.w("BlobBackupHelper", stringBuilder.toString());
      arrayMap1.clear();
      writeBackupState(arrayMap1, paramParcelFileDescriptor2);
    } finally {}
  }
  
  public void restoreEntity(BackupDataInputStream paramBackupDataInputStream) {
    String str = paramBackupDataInputStream.getKey();
    byte b = 0;
    try {
      StringBuilder stringBuilder;
      while (b < this.mKeys.length && !str.equals(this.mKeys[b]))
        b++; 
      if (b >= this.mKeys.length) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Unrecognized key ");
        stringBuilder.append(str);
        stringBuilder.append(", ignoring");
        Log.e("BlobBackupHelper", stringBuilder.toString());
        return;
      } 
      byte[] arrayOfByte = new byte[stringBuilder.size()];
      stringBuilder.read(arrayOfByte);
      applyRestoredPayload(str, inflate(arrayOfByte));
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Exception restoring entity ");
      stringBuilder.append(str);
      stringBuilder.append(" : ");
      stringBuilder.append(exception.getMessage());
      Log.e("BlobBackupHelper", stringBuilder.toString());
    } 
  }
  
  public void writeNewStateDescription(ParcelFileDescriptor paramParcelFileDescriptor) {
    writeBackupState(null, paramParcelFileDescriptor);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BlobBackupHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */