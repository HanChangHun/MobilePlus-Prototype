package android.app.backup;

import android.app.IBackupAgent;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import libcore.io.IoUtils;

class BackupServiceBinder extends IBackupAgent.Stub {
  private static final String TAG = "BackupServiceBinder";
  
  private BackupServiceBinder() {}
  
  private void doRestoreInternal(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager, List<String> paramList) throws RemoteException {
    // Byte code:
    //   0: invokestatic clearCallingIdentity : ()J
    //   3: lstore #8
    //   5: aload_0
    //   6: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   9: invokestatic access$100 : (Landroid/app/backup/BackupAgent;)V
    //   12: new android/app/backup/BackupDataInput
    //   15: dup
    //   16: aload_1
    //   17: invokevirtual getFileDescriptor : ()Ljava/io/FileDescriptor;
    //   20: invokespecial <init> : (Ljava/io/FileDescriptor;)V
    //   23: astore #10
    //   25: aload_0
    //   26: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   29: astore #11
    //   31: aload #7
    //   33: ifnull -> 60
    //   36: new java/util/HashSet
    //   39: astore #12
    //   41: aload #12
    //   43: aload #7
    //   45: invokespecial <init> : (Ljava/util/Collection;)V
    //   48: aload #12
    //   50: astore #7
    //   52: goto -> 65
    //   55: astore #7
    //   57: goto -> 295
    //   60: invokestatic emptySet : ()Ljava/util/Set;
    //   63: astore #7
    //   65: aload #11
    //   67: aload #10
    //   69: lload_2
    //   70: aload #4
    //   72: aload #7
    //   74: invokevirtual onRestore : (Landroid/app/backup/BackupDataInput;JLandroid/os/ParcelFileDescriptor;Ljava/util/Set;)V
    //   77: aload_0
    //   78: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   81: invokevirtual reloadSharedPreferences : ()V
    //   84: lload #8
    //   86: invokestatic restoreCallingIdentity : (J)V
    //   89: aload #6
    //   91: aload_0
    //   92: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   95: invokestatic access$200 : (Landroid/app/backup/BackupAgent;)I
    //   98: iload #5
    //   100: lconst_0
    //   101: invokeinterface opCompleteForUser : (IIJ)V
    //   106: goto -> 111
    //   109: astore #6
    //   111: invokestatic getCallingPid : ()I
    //   114: invokestatic myPid : ()I
    //   117: if_icmpeq -> 129
    //   120: aload_1
    //   121: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   124: aload #4
    //   126: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   129: return
    //   130: astore #7
    //   132: goto -> 156
    //   135: astore #7
    //   137: goto -> 142
    //   140: astore #7
    //   142: ldc ') threw'
    //   144: astore #12
    //   146: goto -> 220
    //   149: astore #7
    //   151: goto -> 295
    //   154: astore #7
    //   156: new java/lang/StringBuilder
    //   159: astore #12
    //   161: aload #12
    //   163: invokespecial <init> : ()V
    //   166: aload #12
    //   168: ldc 'onRestore ('
    //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload #12
    //   176: aload_0
    //   177: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   180: invokevirtual getClass : ()Ljava/lang/Class;
    //   183: invokevirtual getName : ()Ljava/lang/String;
    //   186: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: pop
    //   190: aload #12
    //   192: ldc ') threw'
    //   194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: ldc 'BackupServiceBinder'
    //   200: aload #12
    //   202: invokevirtual toString : ()Ljava/lang/String;
    //   205: aload #7
    //   207: invokestatic d : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   210: pop
    //   211: aload #7
    //   213: athrow
    //   214: astore #7
    //   216: ldc ') threw'
    //   218: astore #12
    //   220: new java/lang/StringBuilder
    //   223: astore #10
    //   225: aload #10
    //   227: invokespecial <init> : ()V
    //   230: aload #10
    //   232: ldc 'onRestore ('
    //   234: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: pop
    //   238: aload #10
    //   240: aload_0
    //   241: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   244: invokevirtual getClass : ()Ljava/lang/Class;
    //   247: invokevirtual getName : ()Ljava/lang/String;
    //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: aload #10
    //   256: aload #12
    //   258: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: ldc 'BackupServiceBinder'
    //   264: aload #10
    //   266: invokevirtual toString : ()Ljava/lang/String;
    //   269: aload #7
    //   271: invokestatic d : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   274: pop
    //   275: new java/lang/RuntimeException
    //   278: astore #12
    //   280: aload #12
    //   282: aload #7
    //   284: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   287: aload #12
    //   289: athrow
    //   290: astore #7
    //   292: goto -> 151
    //   295: aload_0
    //   296: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   299: invokevirtual reloadSharedPreferences : ()V
    //   302: lload #8
    //   304: invokestatic restoreCallingIdentity : (J)V
    //   307: aload #6
    //   309: aload_0
    //   310: getfield this$0 : Landroid/app/backup/BackupAgent;
    //   313: invokestatic access$200 : (Landroid/app/backup/BackupAgent;)I
    //   316: iload #5
    //   318: lconst_0
    //   319: invokeinterface opCompleteForUser : (IIJ)V
    //   324: goto -> 329
    //   327: astore #6
    //   329: invokestatic getCallingPid : ()I
    //   332: invokestatic myPid : ()I
    //   335: if_icmpeq -> 347
    //   338: aload_1
    //   339: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   342: aload #4
    //   344: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   347: aload #7
    //   349: athrow
    // Exception table:
    //   from	to	target	type
    //   25	31	214	java/io/IOException
    //   25	31	154	java/lang/RuntimeException
    //   25	31	149	finally
    //   36	48	214	java/io/IOException
    //   36	48	154	java/lang/RuntimeException
    //   36	48	55	finally
    //   60	65	140	java/io/IOException
    //   60	65	154	java/lang/RuntimeException
    //   60	65	149	finally
    //   65	77	135	java/io/IOException
    //   65	77	130	java/lang/RuntimeException
    //   65	77	290	finally
    //   89	106	109	android/os/RemoteException
    //   156	211	290	finally
    //   211	214	290	finally
    //   220	290	290	finally
    //   307	324	327	android/os/RemoteException
  }
  
  public void doBackup(ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2, ParcelFileDescriptor paramParcelFileDescriptor3, long paramLong, IBackupCallback paramIBackupCallback, int paramInt) throws RemoteException {
    long l = Binder.clearCallingIdentity();
    BackupDataOutput backupDataOutput = new BackupDataOutput(paramParcelFileDescriptor2.getFileDescriptor(), paramLong, paramInt);
    try {
      BackupAgent backupAgent = BackupAgent.this;
      try {
        backupAgent.onBackup(paramParcelFileDescriptor1, backupDataOutput, paramParcelFileDescriptor3);
        BackupAgent.access$100(BackupAgent.this);
        Binder.restoreCallingIdentity(l);
        try {
          paramIBackupCallback.operationComplete(0L);
        } catch (RemoteException remoteException) {}
        if (Binder.getCallingPid() != Process.myPid()) {
          IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor1);
          IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor2);
          IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor3);
        } 
        return;
      } catch (IOException null) {
      
      } catch (RuntimeException null) {
      
      } finally {}
    } catch (IOException null) {
    
    } catch (RuntimeException null) {
      try {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("onBackup (");
        stringBuilder1.append(BackupAgent.this.getClass().getName());
        stringBuilder1.append(") threw");
        Log.d("BackupServiceBinder", stringBuilder1.toString(), exception);
        throw exception;
      } finally {}
    } finally {}
    StringBuilder stringBuilder = new StringBuilder();
    this();
    Exception exception;
    stringBuilder.append("onBackup (");
    stringBuilder.append(BackupAgent.this.getClass().getName());
    stringBuilder.append(") threw");
    Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
    RuntimeException runtimeException = new RuntimeException();
    this(exception);
    throw runtimeException;
  }
  
  public void doFullBackup(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) {
    long l = Binder.clearCallingIdentity();
    BackupAgent.access$100(BackupAgent.this);
    try {
      BackupAgent backupAgent = BackupAgent.this;
      FullBackupDataOutput fullBackupDataOutput = new FullBackupDataOutput();
      try {
        this(paramParcelFileDescriptor, paramLong, paramInt2);
        backupAgent.onFullBackup(fullBackupDataOutput);
        BackupAgent.access$100(BackupAgent.this);
        try {
          FileOutputStream fileOutputStream = new FileOutputStream();
          this(paramParcelFileDescriptor.getFileDescriptor());
          fileOutputStream.write(new byte[4]);
        } catch (IOException null) {
          Log.e("BackupServiceBinder", "Unable to finalize backup stream!");
        } 
        Binder.restoreCallingIdentity(l);
        try {
          paramIBackupManager.opCompleteForUser(BackupAgent.access$200(BackupAgent.this), paramInt1, 0L);
        } catch (RemoteException remoteException) {}
        if (Binder.getCallingPid() != Process.myPid())
          IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor); 
        return;
      } catch (IOException null) {
      
      } catch (RuntimeException null) {
      
      } finally {}
    } catch (IOException null) {
    
    } catch (RuntimeException null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append("onFullBackup (");
      stringBuilder1.append(BackupAgent.this.getClass().getName());
      stringBuilder1.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder1.toString(), exception);
      throw exception;
    } finally {}
    StringBuilder stringBuilder = new StringBuilder();
    this();
    Exception exception;
    stringBuilder.append("onFullBackup (");
    stringBuilder.append(BackupAgent.this.getClass().getName());
    stringBuilder.append(") threw");
    Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
    RuntimeException runtimeException = new RuntimeException();
    this(exception);
    throw runtimeException;
  }
  
  public void doMeasureFullBackup(long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) {
    Exception exception;
    long l = Binder.clearCallingIdentity();
    FullBackupDataOutput fullBackupDataOutput = new FullBackupDataOutput(paramLong, paramInt2);
    BackupAgent.access$100(BackupAgent.this);
    try {
      BackupAgent.this.onFullBackup(fullBackupDataOutput);
      Binder.restoreCallingIdentity(l);
      try {
        paramIBackupManager.opCompleteForUser(BackupAgent.access$200(BackupAgent.this), paramInt1, fullBackupDataOutput.getSize());
      } catch (RemoteException remoteException) {}
      return;
    } catch (IOException null) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("onFullBackup[M] (");
      stringBuilder.append(BackupAgent.this.getClass().getName());
      stringBuilder.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
      RuntimeException runtimeException = new RuntimeException();
      this(exception);
      throw runtimeException;
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("onFullBackup[M] (");
      stringBuilder.append(BackupAgent.this.getClass().getName());
      stringBuilder.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder.toString(), runtimeException);
      throw runtimeException;
    } finally {}
    Binder.restoreCallingIdentity(l);
    try {
      remoteException.opCompleteForUser(BackupAgent.access$200(BackupAgent.this), paramInt1, fullBackupDataOutput.getSize());
    } catch (RemoteException remoteException1) {}
    throw exception;
  }
  
  public void doQuotaExceeded(long paramLong1, long paramLong2, IBackupCallback paramIBackupCallback) {
    Exception exception;
    long l = Binder.clearCallingIdentity();
    try {
      BackupAgent.this.onQuotaExceeded(paramLong1, paramLong2);
      BackupAgent.access$100(BackupAgent.this);
      Binder.restoreCallingIdentity(l);
      try {
        paramIBackupCallback.operationComplete(0L);
      } catch (RemoteException remoteException) {}
      return;
    } catch (Exception exception1) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("onQuotaExceeded(");
      stringBuilder.append(BackupAgent.this.getClass().getName());
      stringBuilder.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder.toString(), exception1);
      throw exception1;
    } finally {}
    BackupAgent.access$100(BackupAgent.this);
    Binder.restoreCallingIdentity(l);
    try {
      remoteException.operationComplete(-1L);
    } catch (RemoteException remoteException1) {}
    throw exception;
  }
  
  public void doRestore(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager) throws RemoteException {
    doRestoreInternal(paramParcelFileDescriptor1, paramLong, paramParcelFileDescriptor2, paramInt, paramIBackupManager, null);
  }
  
  public void doRestoreFile(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, int paramInt1, String paramString1, String paramString2, long paramLong2, long paramLong3, int paramInt2, IBackupManager paramIBackupManager) throws RemoteException {
    long l = Binder.clearCallingIdentity();
    try {
      BackupAgent.this.onRestoreFile(paramParcelFileDescriptor, paramLong1, paramInt1, paramString1, paramString2, paramLong2, paramLong3);
      BackupAgent.access$100(BackupAgent.this);
      BackupAgent.this.reloadSharedPreferences();
      Binder.restoreCallingIdentity(l);
      try {
        paramIBackupManager.opCompleteForUser(BackupAgent.access$200(BackupAgent.this), paramInt2, 0L);
      } catch (RemoteException remoteException) {}
      if (Binder.getCallingPid() != Process.myPid())
        IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor); 
      return;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("onRestoreFile (");
      stringBuilder.append(BackupAgent.this.getClass().getName());
      stringBuilder.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder.toString(), iOException);
      RuntimeException runtimeException = new RuntimeException();
      this(iOException);
      throw runtimeException;
    } finally {}
    BackupAgent.access$100(BackupAgent.this);
    BackupAgent.this.reloadSharedPreferences();
    Binder.restoreCallingIdentity(l);
    try {
      paramIBackupManager.opCompleteForUser(BackupAgent.access$200(BackupAgent.this), paramInt2, 0L);
    } catch (RemoteException remoteException) {}
    if (Binder.getCallingPid() != Process.myPid())
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor); 
    throw paramString1;
  }
  
  public void doRestoreFinished(int paramInt, IBackupManager paramIBackupManager) {
    Exception exception;
    long l = Binder.clearCallingIdentity();
    try {
      BackupAgent.this.onRestoreFinished();
      BackupAgent.access$100(BackupAgent.this);
      Binder.restoreCallingIdentity(l);
      try {
        paramIBackupManager.opCompleteForUser(BackupAgent.access$200(BackupAgent.this), paramInt, 0L);
      } catch (RemoteException remoteException) {}
      return;
    } catch (Exception null) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("onRestoreFinished (");
      stringBuilder.append(BackupAgent.this.getClass().getName());
      stringBuilder.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
      throw exception;
    } finally {}
    BackupAgent.access$100(BackupAgent.this);
    Binder.restoreCallingIdentity(l);
    try {
      remoteException.opCompleteForUser(BackupAgent.access$200(BackupAgent.this), paramInt, 0L);
    } catch (RemoteException remoteException1) {}
    throw exception;
  }
  
  public void doRestoreWithExcludedKeys(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager, List<String> paramList) throws RemoteException {
    doRestoreInternal(paramParcelFileDescriptor1, paramLong, paramParcelFileDescriptor2, paramInt, paramIBackupManager, paramList);
  }
  
  public void fail(String paramString) {
    BackupAgent.this.getHandler().post(new BackupAgent.FailRunnable(paramString));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupAgent$BackupServiceBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */