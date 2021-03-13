package android.bluetooth;

import android.net.LocalSocket;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

public final class BluetoothSocket implements Closeable {
  static final int BTSOCK_FLAG_NO_SDP = 4;
  
  private static final boolean DBG = Log.isLoggable("BluetoothSocket", 3);
  
  static final int EADDRINUSE = 98;
  
  static final int EBADFD = 77;
  
  static final int MAX_L2CAP_PACKAGE_SIZE = 65535;
  
  public static final int MAX_RFCOMM_CHANNEL = 30;
  
  private static final int PROXY_CONNECTION_TIMEOUT = 5000;
  
  static final int SEC_FLAG_AUTH = 2;
  
  static final int SEC_FLAG_AUTH_16_DIGIT = 16;
  
  static final int SEC_FLAG_AUTH_MITM = 8;
  
  static final int SEC_FLAG_ENCRYPT = 1;
  
  private static final int SOCK_SIGNAL_SIZE = 20;
  
  private static final String TAG = "BluetoothSocket";
  
  public static final int TYPE_L2CAP = 3;
  
  public static final int TYPE_L2CAP_BREDR = 3;
  
  public static final int TYPE_L2CAP_LE = 4;
  
  public static final int TYPE_RFCOMM = 1;
  
  public static final int TYPE_SCO = 2;
  
  private static final boolean VDBG = Log.isLoggable("BluetoothSocket", 2);
  
  private String mAddress;
  
  private final boolean mAuth;
  
  private boolean mAuthMitm = false;
  
  private BluetoothDevice mDevice;
  
  private final boolean mEncrypt;
  
  private boolean mExcludeSdp = false;
  
  private int mFd;
  
  private final BluetoothInputStream mInputStream;
  
  private ByteBuffer mL2capBuffer = null;
  
  private int mMaxRxPacketSize = 0;
  
  private int mMaxTxPacketSize = 0;
  
  private boolean mMin16DigitPin = false;
  
  private final BluetoothOutputStream mOutputStream;
  
  private ParcelFileDescriptor mPfd;
  
  private int mPort;
  
  private String mServiceName;
  
  private LocalSocket mSocket;
  
  private InputStream mSocketIS;
  
  private OutputStream mSocketOS;
  
  private volatile SocketState mSocketState;
  
  private final int mType;
  
  private final ParcelUuid mUuid;
  
  BluetoothSocket(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, BluetoothDevice paramBluetoothDevice, int paramInt3, ParcelUuid paramParcelUuid) throws IOException {
    this(paramInt1, paramInt2, paramBoolean1, paramBoolean2, paramBluetoothDevice, paramInt3, paramParcelUuid, false, false);
  }
  
  BluetoothSocket(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, BluetoothDevice paramBluetoothDevice, int paramInt3, ParcelUuid paramParcelUuid, boolean paramBoolean3, boolean paramBoolean4) throws IOException {
    if (VDBG) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Creating new BluetoothSocket of type: ");
      stringBuilder1.append(paramInt1);
      Log.d("BluetoothSocket", stringBuilder1.toString());
    } 
    if (paramInt1 != 1 || paramParcelUuid != null || paramInt2 != -1 || paramInt3 == -2 || (paramInt3 >= 1 && paramInt3 <= 30)) {
      if (paramParcelUuid != null) {
        this.mUuid = paramParcelUuid;
      } else {
        this.mUuid = new ParcelUuid(new UUID(0L, 0L));
      } 
      this.mType = paramInt1;
      this.mAuth = paramBoolean1;
      this.mAuthMitm = paramBoolean3;
      this.mMin16DigitPin = paramBoolean4;
      this.mEncrypt = paramBoolean2;
      this.mDevice = paramBluetoothDevice;
      this.mPort = paramInt3;
      this.mFd = paramInt2;
      this.mSocketState = SocketState.INIT;
      if (paramBluetoothDevice == null) {
        this.mAddress = BluetoothAdapter.getDefaultAdapter().getAddress();
      } else {
        this.mAddress = paramBluetoothDevice.getAddress();
      } 
      this.mInputStream = new BluetoothInputStream(this);
      this.mOutputStream = new BluetoothOutputStream(this);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid RFCOMM channel: ");
    stringBuilder.append(paramInt3);
    throw new IOException(stringBuilder.toString());
  }
  
  private BluetoothSocket(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, String paramString, int paramInt3) throws IOException {
    this(paramInt1, paramInt2, paramBoolean1, paramBoolean2, new BluetoothDevice(paramString), paramInt3, null, false, false);
  }
  
  private BluetoothSocket(BluetoothSocket paramBluetoothSocket) {
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Creating new Private BluetoothSocket of type: ");
      stringBuilder.append(paramBluetoothSocket.mType);
      Log.d("BluetoothSocket", stringBuilder.toString());
    } 
    this.mUuid = paramBluetoothSocket.mUuid;
    this.mType = paramBluetoothSocket.mType;
    this.mAuth = paramBluetoothSocket.mAuth;
    this.mEncrypt = paramBluetoothSocket.mEncrypt;
    this.mPort = paramBluetoothSocket.mPort;
    this.mInputStream = new BluetoothInputStream(this);
    this.mOutputStream = new BluetoothOutputStream(this);
    this.mMaxRxPacketSize = paramBluetoothSocket.mMaxRxPacketSize;
    this.mMaxTxPacketSize = paramBluetoothSocket.mMaxTxPacketSize;
    this.mServiceName = paramBluetoothSocket.mServiceName;
    this.mExcludeSdp = paramBluetoothSocket.mExcludeSdp;
    this.mAuthMitm = paramBluetoothSocket.mAuthMitm;
    this.mMin16DigitPin = paramBluetoothSocket.mMin16DigitPin;
  }
  
  private BluetoothSocket acceptSocket(String paramString) throws IOException {
    LocalSocket localSocket;
    BluetoothSocket bluetoothSocket = new BluetoothSocket(this);
    bluetoothSocket.mSocketState = SocketState.CONNECTED;
    FileDescriptor[] arrayOfFileDescriptor = this.mSocket.getAncillaryFileDescriptors();
    if (DBG) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("socket fd passed by stack fds: ");
      stringBuilder1.append(Arrays.toString((Object[])arrayOfFileDescriptor));
      Log.d("BluetoothSocket", stringBuilder1.toString());
    } 
    if (arrayOfFileDescriptor != null && arrayOfFileDescriptor.length == 1) {
      bluetoothSocket.mPfd = new ParcelFileDescriptor(arrayOfFileDescriptor[0]);
      localSocket = LocalSocket.createConnectedLocalSocket(arrayOfFileDescriptor[0]);
      bluetoothSocket.mSocket = localSocket;
      bluetoothSocket.mSocketIS = localSocket.getInputStream();
      bluetoothSocket.mSocketOS = bluetoothSocket.mSocket.getOutputStream();
      bluetoothSocket.mAddress = paramString;
      bluetoothSocket.mDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(paramString);
      return bluetoothSocket;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("socket fd passed from stack failed, fds: ");
    stringBuilder.append(Arrays.toString((Object[])localSocket));
    Log.e("BluetoothSocket", stringBuilder.toString());
    bluetoothSocket.close();
    throw new IOException("bt socket acept failed");
  }
  
  private String convertAddr(byte[] paramArrayOfbyte) {
    return String.format(Locale.US, "%02X:%02X:%02X:%02X:%02X:%02X", new Object[] { Byte.valueOf(paramArrayOfbyte[0]), Byte.valueOf(paramArrayOfbyte[1]), Byte.valueOf(paramArrayOfbyte[2]), Byte.valueOf(paramArrayOfbyte[3]), Byte.valueOf(paramArrayOfbyte[4]), Byte.valueOf(paramArrayOfbyte[5]) });
  }
  
  private void createL2capRxBuffer() {
    int i = this.mType;
    if (i == 3 || i == 4) {
      if (VDBG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Creating mL2capBuffer: mMaxPacketSize: ");
        stringBuilder.append(this.mMaxRxPacketSize);
        Log.v("BluetoothSocket", stringBuilder.toString());
      } 
      this.mL2capBuffer = ByteBuffer.wrap(new byte[this.mMaxRxPacketSize]);
      if (VDBG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mL2capBuffer.remaining()");
        stringBuilder.append(this.mL2capBuffer.remaining());
        Log.v("BluetoothSocket", stringBuilder.toString());
      } 
      this.mL2capBuffer.limit(0);
      if (VDBG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mL2capBuffer.remaining() after limit(0):");
        stringBuilder.append(this.mL2capBuffer.remaining());
        Log.v("BluetoothSocket", stringBuilder.toString());
      } 
    } 
  }
  
  private int fillL2capRxBuffer() throws IOException {
    this.mL2capBuffer.rewind();
    int i = this.mSocketIS.read(this.mL2capBuffer.array());
    if (i == -1) {
      this.mL2capBuffer.limit(0);
      return -1;
    } 
    this.mL2capBuffer.limit(i);
    return i;
  }
  
  private int getSecurityFlags() {
    int i = 0;
    if (this.mAuth)
      i = 0x0 | 0x2; 
    int j = i;
    if (this.mEncrypt)
      j = i | 0x1; 
    i = j;
    if (this.mExcludeSdp)
      i = j | 0x4; 
    j = i;
    if (this.mAuthMitm)
      j = i | 0x8; 
    i = j;
    if (this.mMin16DigitPin)
      i = j | 0x10; 
    return i;
  }
  
  private int readAll(InputStream paramInputStream, byte[] paramArrayOfbyte) throws IOException {
    int i = paramArrayOfbyte.length;
    while (i > 0) {
      int j = paramInputStream.read(paramArrayOfbyte, paramArrayOfbyte.length - i, i);
      if (j > 0) {
        i -= j;
        if (i != 0) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("readAll() looping, read partial size: ");
          stringBuilder1.append(paramArrayOfbyte.length - i);
          stringBuilder1.append(", expect size: ");
          stringBuilder1.append(paramArrayOfbyte.length);
          Log.w("BluetoothSocket", stringBuilder1.toString());
        } 
        continue;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("read failed, socket might closed or timeout, read ret: ");
      stringBuilder.append(j);
      throw new IOException(stringBuilder.toString());
    } 
    return paramArrayOfbyte.length;
  }
  
  private int readInt(InputStream paramInputStream) throws IOException {
    byte[] arrayOfByte = new byte[4];
    int i = readAll(paramInputStream, arrayOfByte);
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("inputStream.read ret: ");
      stringBuilder.append(i);
      Log.d("BluetoothSocket", stringBuilder.toString());
    } 
    ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
    byteBuffer.order(ByteOrder.nativeOrder());
    return byteBuffer.getInt();
  }
  
  private String waitSocketSignal(InputStream paramInputStream) throws IOException {
    byte[] arrayOfByte = new byte[20];
    int i = readAll(paramInputStream, arrayOfByte);
    if (VDBG) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("waitSocketSignal read 20 bytes signal ret: ");
      stringBuilder1.append(i);
      Log.d("BluetoothSocket", stringBuilder1.toString());
    } 
    ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
    byteBuffer.order(ByteOrder.nativeOrder());
    short s = byteBuffer.getShort();
    if (s == 20) {
      byte[] arrayOfByte1 = new byte[6];
      byteBuffer.get(arrayOfByte1);
      int j = byteBuffer.getInt();
      i = byteBuffer.getInt();
      this.mMaxTxPacketSize = byteBuffer.getShort() & 0xFFFF;
      this.mMaxRxPacketSize = byteBuffer.getShort() & 0xFFFF;
      String str = convertAddr(arrayOfByte1);
      if (VDBG) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("waitSocketSignal: sig size: ");
        stringBuilder2.append(s);
        stringBuilder2.append(", remote addr: ");
        stringBuilder2.append(str);
        stringBuilder2.append(", channel: ");
        stringBuilder2.append(j);
        stringBuilder2.append(", status: ");
        stringBuilder2.append(i);
        stringBuilder2.append(" MaxRxPktSize: ");
        stringBuilder2.append(this.mMaxRxPacketSize);
        stringBuilder2.append(" MaxTxPktSize: ");
        stringBuilder2.append(this.mMaxTxPacketSize);
        Log.d("BluetoothSocket", stringBuilder2.toString());
      } 
      if (i == 0)
        return str; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Connection failure, status: ");
      stringBuilder1.append(i);
      throw new IOException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Connection failure, wrong signal size: ");
    stringBuilder.append(s);
    throw new IOException(stringBuilder.toString());
  }
  
  BluetoothSocket accept(int paramInt) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   4: getstatic android/bluetooth/BluetoothSocket$SocketState.LISTENING : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   7: if_acmpne -> 115
    //   10: iload_1
    //   11: ifle -> 54
    //   14: new java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial <init> : ()V
    //   21: astore_2
    //   22: aload_2
    //   23: ldc_w 'accept() set timeout (ms):'
    //   26: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: aload_2
    //   31: iload_1
    //   32: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: ldc 'BluetoothSocket'
    //   38: aload_2
    //   39: invokevirtual toString : ()Ljava/lang/String;
    //   42: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   45: pop
    //   46: aload_0
    //   47: getfield mSocket : Landroid/net/LocalSocket;
    //   50: iload_1
    //   51: invokevirtual setSoTimeout : (I)V
    //   54: aload_0
    //   55: aload_0
    //   56: getfield mSocketIS : Ljava/io/InputStream;
    //   59: invokespecial waitSocketSignal : (Ljava/io/InputStream;)Ljava/lang/String;
    //   62: astore_2
    //   63: iload_1
    //   64: ifle -> 75
    //   67: aload_0
    //   68: getfield mSocket : Landroid/net/LocalSocket;
    //   71: iconst_0
    //   72: invokevirtual setSoTimeout : (I)V
    //   75: aload_0
    //   76: monitorenter
    //   77: aload_0
    //   78: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   81: getstatic android/bluetooth/BluetoothSocket$SocketState.LISTENING : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   84: if_acmpne -> 97
    //   87: aload_0
    //   88: aload_2
    //   89: invokespecial acceptSocket : (Ljava/lang/String;)Landroid/bluetooth/BluetoothSocket;
    //   92: astore_2
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_2
    //   96: areturn
    //   97: new java/io/IOException
    //   100: astore_2
    //   101: aload_2
    //   102: ldc_w 'bt socket is not in listen state'
    //   105: invokespecial <init> : (Ljava/lang/String;)V
    //   108: aload_2
    //   109: athrow
    //   110: astore_2
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_2
    //   114: athrow
    //   115: new java/io/IOException
    //   118: dup
    //   119: ldc_w 'bt socket is not in listen state'
    //   122: invokespecial <init> : (Ljava/lang/String;)V
    //   125: athrow
    // Exception table:
    //   from	to	target	type
    //   77	95	110	finally
    //   97	110	110	finally
    //   111	113	110	finally
  }
  
  int available() throws IOException {
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("available: ");
      stringBuilder.append(this.mSocketIS);
      Log.d("BluetoothSocket", stringBuilder.toString());
    } 
    return this.mSocketIS.available();
  }
  
  int bindListen() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   4: getstatic android/bluetooth/BluetoothSocket$SocketState.CLOSED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   7: if_acmpne -> 13
    //   10: bipush #77
    //   12: ireturn
    //   13: invokestatic getDefaultAdapter : ()Landroid/bluetooth/BluetoothAdapter;
    //   16: aconst_null
    //   17: invokevirtual getBluetoothService : (Landroid/bluetooth/IBluetoothManagerCallback;)Landroid/bluetooth/IBluetooth;
    //   20: astore_1
    //   21: aload_1
    //   22: ifnonnull -> 36
    //   25: ldc 'BluetoothSocket'
    //   27: ldc_w 'bindListen fail, reason: bluetooth is off'
    //   30: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   33: pop
    //   34: iconst_m1
    //   35: ireturn
    //   36: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   39: ifeq -> 94
    //   42: new java/lang/StringBuilder
    //   45: astore_2
    //   46: aload_2
    //   47: invokespecial <init> : ()V
    //   50: aload_2
    //   51: ldc_w 'bindListen(): mPort='
    //   54: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload_2
    //   59: aload_0
    //   60: getfield mPort : I
    //   63: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload_2
    //   68: ldc_w ', mType='
    //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: aload_2
    //   76: aload_0
    //   77: getfield mType : I
    //   80: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: ldc 'BluetoothSocket'
    //   86: aload_2
    //   87: invokevirtual toString : ()Ljava/lang/String;
    //   90: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   93: pop
    //   94: aload_0
    //   95: aload_1
    //   96: invokeinterface getSocketManager : ()Landroid/bluetooth/IBluetoothSocketManager;
    //   101: aload_0
    //   102: getfield mType : I
    //   105: aload_0
    //   106: getfield mServiceName : Ljava/lang/String;
    //   109: aload_0
    //   110: getfield mUuid : Landroid/os/ParcelUuid;
    //   113: aload_0
    //   114: getfield mPort : I
    //   117: aload_0
    //   118: invokespecial getSecurityFlags : ()I
    //   121: invokeinterface createSocketChannel : (ILjava/lang/String;Landroid/os/ParcelUuid;II)Landroid/os/ParcelFileDescriptor;
    //   126: putfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   129: aload_0
    //   130: monitorenter
    //   131: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   134: ifeq -> 189
    //   137: new java/lang/StringBuilder
    //   140: astore_1
    //   141: aload_1
    //   142: invokespecial <init> : ()V
    //   145: aload_1
    //   146: ldc_w 'bindListen(), SocketState: '
    //   149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: pop
    //   153: aload_1
    //   154: aload_0
    //   155: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   158: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: aload_1
    //   163: ldc_w ', mPfd: '
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload_1
    //   171: aload_0
    //   172: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   175: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: ldc 'BluetoothSocket'
    //   181: aload_1
    //   182: invokevirtual toString : ()Ljava/lang/String;
    //   185: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   188: pop
    //   189: aload_0
    //   190: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   193: getstatic android/bluetooth/BluetoothSocket$SocketState.INIT : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   196: if_acmpeq -> 204
    //   199: aload_0
    //   200: monitorexit
    //   201: bipush #77
    //   203: ireturn
    //   204: aload_0
    //   205: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   208: ifnonnull -> 215
    //   211: aload_0
    //   212: monitorexit
    //   213: iconst_m1
    //   214: ireturn
    //   215: aload_0
    //   216: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   219: invokevirtual getFileDescriptor : ()Ljava/io/FileDescriptor;
    //   222: astore_1
    //   223: aload_1
    //   224: ifnonnull -> 240
    //   227: ldc 'BluetoothSocket'
    //   229: ldc_w 'bindListen(), null file descriptor'
    //   232: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   235: pop
    //   236: aload_0
    //   237: monitorexit
    //   238: iconst_m1
    //   239: ireturn
    //   240: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   243: ifeq -> 255
    //   246: ldc 'BluetoothSocket'
    //   248: ldc_w 'bindListen(), Create LocalSocket'
    //   251: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   254: pop
    //   255: aload_0
    //   256: aload_1
    //   257: invokestatic createConnectedLocalSocket : (Ljava/io/FileDescriptor;)Landroid/net/LocalSocket;
    //   260: putfield mSocket : Landroid/net/LocalSocket;
    //   263: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   266: ifeq -> 278
    //   269: ldc 'BluetoothSocket'
    //   271: ldc_w 'bindListen(), new LocalSocket.getInputStream()'
    //   274: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   277: pop
    //   278: aload_0
    //   279: aload_0
    //   280: getfield mSocket : Landroid/net/LocalSocket;
    //   283: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   286: putfield mSocketIS : Ljava/io/InputStream;
    //   289: aload_0
    //   290: aload_0
    //   291: getfield mSocket : Landroid/net/LocalSocket;
    //   294: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   297: putfield mSocketOS : Ljava/io/OutputStream;
    //   300: aload_0
    //   301: monitorexit
    //   302: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   305: ifeq -> 343
    //   308: new java/lang/StringBuilder
    //   311: astore_1
    //   312: aload_1
    //   313: invokespecial <init> : ()V
    //   316: aload_1
    //   317: ldc_w 'bindListen(), readInt mSocketIS: '
    //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: pop
    //   324: aload_1
    //   325: aload_0
    //   326: getfield mSocketIS : Ljava/io/InputStream;
    //   329: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   332: pop
    //   333: ldc 'BluetoothSocket'
    //   335: aload_1
    //   336: invokevirtual toString : ()Ljava/lang/String;
    //   339: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   342: pop
    //   343: aload_0
    //   344: aload_0
    //   345: getfield mSocketIS : Ljava/io/InputStream;
    //   348: invokespecial readInt : (Ljava/io/InputStream;)I
    //   351: istore_3
    //   352: aload_0
    //   353: monitorenter
    //   354: aload_0
    //   355: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   358: getstatic android/bluetooth/BluetoothSocket$SocketState.INIT : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   361: if_acmpne -> 371
    //   364: aload_0
    //   365: getstatic android/bluetooth/BluetoothSocket$SocketState.LISTENING : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   368: putfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   371: aload_0
    //   372: monitorexit
    //   373: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   376: ifeq -> 428
    //   379: new java/lang/StringBuilder
    //   382: astore_1
    //   383: aload_1
    //   384: invokespecial <init> : ()V
    //   387: aload_1
    //   388: ldc_w 'bindListen(): channel='
    //   391: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   394: pop
    //   395: aload_1
    //   396: iload_3
    //   397: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   400: pop
    //   401: aload_1
    //   402: ldc_w ', mPort='
    //   405: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: pop
    //   409: aload_1
    //   410: aload_0
    //   411: getfield mPort : I
    //   414: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   417: pop
    //   418: ldc 'BluetoothSocket'
    //   420: aload_1
    //   421: invokevirtual toString : ()Ljava/lang/String;
    //   424: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   427: pop
    //   428: aload_0
    //   429: getfield mPort : I
    //   432: iconst_m1
    //   433: if_icmpgt -> 441
    //   436: aload_0
    //   437: iload_3
    //   438: putfield mPort : I
    //   441: iconst_0
    //   442: ireturn
    //   443: astore_1
    //   444: aload_0
    //   445: monitorexit
    //   446: aload_1
    //   447: athrow
    //   448: astore_1
    //   449: aload_0
    //   450: monitorexit
    //   451: aload_1
    //   452: athrow
    //   453: astore_1
    //   454: aload_0
    //   455: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   458: astore_2
    //   459: aload_2
    //   460: ifnull -> 510
    //   463: aload_2
    //   464: invokevirtual close : ()V
    //   467: goto -> 505
    //   470: astore #4
    //   472: new java/lang/StringBuilder
    //   475: dup
    //   476: invokespecial <init> : ()V
    //   479: astore_2
    //   480: aload_2
    //   481: ldc_w 'bindListen, close mPfd: '
    //   484: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   487: pop
    //   488: aload_2
    //   489: aload #4
    //   491: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   494: pop
    //   495: ldc 'BluetoothSocket'
    //   497: aload_2
    //   498: invokevirtual toString : ()Ljava/lang/String;
    //   501: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   504: pop
    //   505: aload_0
    //   506: aconst_null
    //   507: putfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   510: new java/lang/StringBuilder
    //   513: dup
    //   514: invokespecial <init> : ()V
    //   517: astore_2
    //   518: aload_2
    //   519: ldc_w 'bindListen, fail to get port number, exception: '
    //   522: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: pop
    //   526: aload_2
    //   527: aload_1
    //   528: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   531: pop
    //   532: ldc 'BluetoothSocket'
    //   534: aload_2
    //   535: invokevirtual toString : ()Ljava/lang/String;
    //   538: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   541: pop
    //   542: iconst_m1
    //   543: ireturn
    //   544: astore_1
    //   545: ldc 'BluetoothSocket'
    //   547: new java/lang/Throwable
    //   550: dup
    //   551: invokespecial <init> : ()V
    //   554: invokestatic getStackTraceString : (Ljava/lang/Throwable;)Ljava/lang/String;
    //   557: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   560: pop
    //   561: iconst_m1
    //   562: ireturn
    // Exception table:
    //   from	to	target	type
    //   36	94	544	android/os/RemoteException
    //   94	129	544	android/os/RemoteException
    //   129	131	453	java/io/IOException
    //   131	189	448	finally
    //   189	201	448	finally
    //   204	213	448	finally
    //   215	223	448	finally
    //   227	238	448	finally
    //   240	255	448	finally
    //   255	278	448	finally
    //   278	302	448	finally
    //   302	343	453	java/io/IOException
    //   343	354	453	java/io/IOException
    //   354	371	443	finally
    //   371	373	443	finally
    //   373	428	453	java/io/IOException
    //   428	441	453	java/io/IOException
    //   444	446	443	finally
    //   446	448	453	java/io/IOException
    //   449	451	448	finally
    //   451	453	453	java/io/IOException
    //   463	467	470	java/io/IOException
  }
  
  public void close() throws IOException {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_1
    //   9: ldc_w 'close() this: '
    //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload_1
    //   17: aload_0
    //   18: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   21: pop
    //   22: aload_1
    //   23: ldc_w ', channel: '
    //   26: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: aload_1
    //   31: aload_0
    //   32: getfield mPort : I
    //   35: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_1
    //   40: ldc_w ', mSocketIS: '
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload_1
    //   48: aload_0
    //   49: getfield mSocketIS : Ljava/io/InputStream;
    //   52: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: aload_1
    //   57: ldc_w ', mSocketOS: '
    //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: pop
    //   64: aload_1
    //   65: aload_0
    //   66: getfield mSocketOS : Ljava/io/OutputStream;
    //   69: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload_1
    //   74: ldc_w 'mSocket: '
    //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload_1
    //   82: aload_0
    //   83: getfield mSocket : Landroid/net/LocalSocket;
    //   86: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: aload_1
    //   91: ldc_w ', mSocketState: '
    //   94: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: aload_1
    //   99: aload_0
    //   100: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   103: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: ldc 'BluetoothSocket'
    //   109: aload_1
    //   110: invokevirtual toString : ()Ljava/lang/String;
    //   113: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   116: pop
    //   117: aload_0
    //   118: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   121: getstatic android/bluetooth/BluetoothSocket$SocketState.CLOSED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   124: if_acmpne -> 128
    //   127: return
    //   128: aload_0
    //   129: monitorenter
    //   130: aload_0
    //   131: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   134: getstatic android/bluetooth/BluetoothSocket$SocketState.CLOSED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   137: if_acmpne -> 143
    //   140: aload_0
    //   141: monitorexit
    //   142: return
    //   143: aload_0
    //   144: getstatic android/bluetooth/BluetoothSocket$SocketState.CLOSED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   147: putfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   150: aload_0
    //   151: getfield mSocket : Landroid/net/LocalSocket;
    //   154: ifnull -> 224
    //   157: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   160: ifeq -> 198
    //   163: new java/lang/StringBuilder
    //   166: astore_1
    //   167: aload_1
    //   168: invokespecial <init> : ()V
    //   171: aload_1
    //   172: ldc_w 'Closing mSocket: '
    //   175: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: aload_1
    //   180: aload_0
    //   181: getfield mSocket : Landroid/net/LocalSocket;
    //   184: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   187: pop
    //   188: ldc 'BluetoothSocket'
    //   190: aload_1
    //   191: invokevirtual toString : ()Ljava/lang/String;
    //   194: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   197: pop
    //   198: aload_0
    //   199: getfield mSocket : Landroid/net/LocalSocket;
    //   202: invokevirtual shutdownInput : ()V
    //   205: aload_0
    //   206: getfield mSocket : Landroid/net/LocalSocket;
    //   209: invokevirtual shutdownOutput : ()V
    //   212: aload_0
    //   213: getfield mSocket : Landroid/net/LocalSocket;
    //   216: invokevirtual close : ()V
    //   219: aload_0
    //   220: aconst_null
    //   221: putfield mSocket : Landroid/net/LocalSocket;
    //   224: aload_0
    //   225: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   228: ifnull -> 243
    //   231: aload_0
    //   232: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   235: invokevirtual close : ()V
    //   238: aload_0
    //   239: aconst_null
    //   240: putfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   243: aload_0
    //   244: monitorexit
    //   245: return
    //   246: astore_1
    //   247: aload_0
    //   248: monitorexit
    //   249: aload_1
    //   250: athrow
    // Exception table:
    //   from	to	target	type
    //   130	142	246	finally
    //   143	198	246	finally
    //   198	224	246	finally
    //   224	243	246	finally
    //   243	245	246	finally
    //   247	249	246	finally
  }
  
  public void connect() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: getfield mDevice : Landroid/bluetooth/BluetoothDevice;
    //   4: ifnull -> 369
    //   7: aload_0
    //   8: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   11: getstatic android/bluetooth/BluetoothSocket$SocketState.CLOSED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   14: if_acmpeq -> 302
    //   17: invokestatic getDefaultAdapter : ()Landroid/bluetooth/BluetoothAdapter;
    //   20: aconst_null
    //   21: invokevirtual getBluetoothService : (Landroid/bluetooth/IBluetoothManagerCallback;)Landroid/bluetooth/IBluetooth;
    //   24: astore_1
    //   25: aload_1
    //   26: ifnull -> 289
    //   29: aload_0
    //   30: aload_1
    //   31: invokeinterface getSocketManager : ()Landroid/bluetooth/IBluetoothSocketManager;
    //   36: aload_0
    //   37: getfield mDevice : Landroid/bluetooth/BluetoothDevice;
    //   40: aload_0
    //   41: getfield mType : I
    //   44: aload_0
    //   45: getfield mUuid : Landroid/os/ParcelUuid;
    //   48: aload_0
    //   49: getfield mPort : I
    //   52: aload_0
    //   53: invokespecial getSecurityFlags : ()I
    //   56: invokeinterface connectSocket : (Landroid/bluetooth/BluetoothDevice;ILandroid/os/ParcelUuid;II)Landroid/os/ParcelFileDescriptor;
    //   61: putfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   64: aload_0
    //   65: monitorenter
    //   66: getstatic android/bluetooth/BluetoothSocket.DBG : Z
    //   69: ifeq -> 124
    //   72: new java/lang/StringBuilder
    //   75: astore_1
    //   76: aload_1
    //   77: invokespecial <init> : ()V
    //   80: aload_1
    //   81: ldc_w 'connect(), SocketState: '
    //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload_1
    //   89: aload_0
    //   90: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   93: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload_1
    //   98: ldc_w ', mPfd: '
    //   101: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: aload_1
    //   106: aload_0
    //   107: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   110: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   113: pop
    //   114: ldc 'BluetoothSocket'
    //   116: aload_1
    //   117: invokevirtual toString : ()Ljava/lang/String;
    //   120: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: aload_0
    //   125: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   128: getstatic android/bluetooth/BluetoothSocket$SocketState.CLOSED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   131: if_acmpeq -> 271
    //   134: aload_0
    //   135: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   138: ifnull -> 258
    //   141: aload_0
    //   142: getfield mPfd : Landroid/os/ParcelFileDescriptor;
    //   145: invokevirtual getFileDescriptor : ()Ljava/io/FileDescriptor;
    //   148: invokestatic createConnectedLocalSocket : (Ljava/io/FileDescriptor;)Landroid/net/LocalSocket;
    //   151: astore_1
    //   152: aload_0
    //   153: aload_1
    //   154: putfield mSocket : Landroid/net/LocalSocket;
    //   157: aload_0
    //   158: aload_1
    //   159: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   162: putfield mSocketIS : Ljava/io/InputStream;
    //   165: aload_0
    //   166: aload_0
    //   167: getfield mSocket : Landroid/net/LocalSocket;
    //   170: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   173: putfield mSocketOS : Ljava/io/OutputStream;
    //   176: aload_0
    //   177: monitorexit
    //   178: aload_0
    //   179: aload_0
    //   180: getfield mSocketIS : Ljava/io/InputStream;
    //   183: invokespecial readInt : (Ljava/io/InputStream;)I
    //   186: istore_2
    //   187: iload_2
    //   188: ifle -> 245
    //   191: aload_0
    //   192: iload_2
    //   193: putfield mPort : I
    //   196: aload_0
    //   197: aload_0
    //   198: getfield mSocketIS : Ljava/io/InputStream;
    //   201: invokespecial waitSocketSignal : (Ljava/io/InputStream;)Ljava/lang/String;
    //   204: pop
    //   205: aload_0
    //   206: monitorenter
    //   207: aload_0
    //   208: getfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   211: getstatic android/bluetooth/BluetoothSocket$SocketState.CLOSED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   214: if_acmpeq -> 227
    //   217: aload_0
    //   218: getstatic android/bluetooth/BluetoothSocket$SocketState.CONNECTED : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   221: putfield mSocketState : Landroid/bluetooth/BluetoothSocket$SocketState;
    //   224: aload_0
    //   225: monitorexit
    //   226: return
    //   227: new java/io/IOException
    //   230: astore_1
    //   231: aload_1
    //   232: ldc_w 'bt socket closed'
    //   235: invokespecial <init> : (Ljava/lang/String;)V
    //   238: aload_1
    //   239: athrow
    //   240: astore_1
    //   241: aload_0
    //   242: monitorexit
    //   243: aload_1
    //   244: athrow
    //   245: new java/io/IOException
    //   248: astore_1
    //   249: aload_1
    //   250: ldc_w 'bt socket connect failed'
    //   253: invokespecial <init> : (Ljava/lang/String;)V
    //   256: aload_1
    //   257: athrow
    //   258: new java/io/IOException
    //   261: astore_1
    //   262: aload_1
    //   263: ldc_w 'bt socket connect failed'
    //   266: invokespecial <init> : (Ljava/lang/String;)V
    //   269: aload_1
    //   270: athrow
    //   271: new java/io/IOException
    //   274: astore_1
    //   275: aload_1
    //   276: ldc_w 'socket closed'
    //   279: invokespecial <init> : (Ljava/lang/String;)V
    //   282: aload_1
    //   283: athrow
    //   284: astore_1
    //   285: aload_0
    //   286: monitorexit
    //   287: aload_1
    //   288: athrow
    //   289: new java/io/IOException
    //   292: astore_1
    //   293: aload_1
    //   294: ldc_w 'Bluetooth is off'
    //   297: invokespecial <init> : (Ljava/lang/String;)V
    //   300: aload_1
    //   301: athrow
    //   302: new java/io/IOException
    //   305: astore_1
    //   306: aload_1
    //   307: ldc_w 'socket closed'
    //   310: invokespecial <init> : (Ljava/lang/String;)V
    //   313: aload_1
    //   314: athrow
    //   315: astore_3
    //   316: ldc 'BluetoothSocket'
    //   318: new java/lang/Throwable
    //   321: dup
    //   322: invokespecial <init> : ()V
    //   325: invokestatic getStackTraceString : (Ljava/lang/Throwable;)Ljava/lang/String;
    //   328: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   331: pop
    //   332: new java/lang/StringBuilder
    //   335: dup
    //   336: invokespecial <init> : ()V
    //   339: astore_1
    //   340: aload_1
    //   341: ldc_w 'unable to send RPC: '
    //   344: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: pop
    //   348: aload_1
    //   349: aload_3
    //   350: invokevirtual getMessage : ()Ljava/lang/String;
    //   353: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: pop
    //   357: new java/io/IOException
    //   360: dup
    //   361: aload_1
    //   362: invokevirtual toString : ()Ljava/lang/String;
    //   365: invokespecial <init> : (Ljava/lang/String;)V
    //   368: athrow
    //   369: new java/io/IOException
    //   372: dup
    //   373: ldc_w 'Connect is called on null device'
    //   376: invokespecial <init> : (Ljava/lang/String;)V
    //   379: athrow
    // Exception table:
    //   from	to	target	type
    //   7	25	315	android/os/RemoteException
    //   29	66	315	android/os/RemoteException
    //   66	124	284	finally
    //   124	178	284	finally
    //   178	187	315	android/os/RemoteException
    //   191	207	315	android/os/RemoteException
    //   207	226	240	finally
    //   227	240	240	finally
    //   241	243	240	finally
    //   243	245	315	android/os/RemoteException
    //   245	258	315	android/os/RemoteException
    //   258	271	284	finally
    //   271	284	284	finally
    //   285	287	284	finally
    //   287	289	315	android/os/RemoteException
    //   289	302	315	android/os/RemoteException
    //   302	315	315	android/os/RemoteException
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getConnectionType() {
    int i = this.mType;
    return (i == 4) ? 3 : i;
  }
  
  public InputStream getInputStream() throws IOException {
    return this.mInputStream;
  }
  
  public int getMaxReceivePacketSize() {
    return this.mMaxRxPacketSize;
  }
  
  public int getMaxTransmitPacketSize() {
    return this.mMaxTxPacketSize;
  }
  
  public OutputStream getOutputStream() throws IOException {
    return this.mOutputStream;
  }
  
  int getPort() {
    return this.mPort;
  }
  
  public BluetoothDevice getRemoteDevice() {
    return this.mDevice;
  }
  
  public boolean isConnected() {
    boolean bool;
    if (this.mSocketState == SocketState.CONNECTED) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (VDBG) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("read in:  ");
      stringBuilder1.append(this.mSocketIS);
      stringBuilder1.append(" len: ");
      stringBuilder1.append(paramInt2);
      Log.d("BluetoothSocket", stringBuilder1.toString());
    } 
    int i = this.mType;
    if (i == 3 || i == 4) {
      i = paramInt2;
      if (VDBG) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("l2cap: read(): offset: ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(" length:");
        stringBuilder1.append(paramInt2);
        stringBuilder1.append("mL2capBuffer= ");
        stringBuilder1.append(this.mL2capBuffer);
        Log.v("BluetoothSocket", stringBuilder1.toString());
      } 
      if (this.mL2capBuffer == null)
        createL2capRxBuffer(); 
      if (this.mL2capBuffer.remaining() == 0) {
        if (VDBG)
          Log.v("BluetoothSocket", "l2cap buffer empty, refilling..."); 
        if (fillL2capRxBuffer() == -1)
          return -1; 
      } 
      paramInt2 = i;
      if (i > this.mL2capBuffer.remaining())
        paramInt2 = this.mL2capBuffer.remaining(); 
      if (VDBG) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("get(): offset: ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(" bytesToRead: ");
        stringBuilder1.append(paramInt2);
        Log.v("BluetoothSocket", stringBuilder1.toString());
      } 
      this.mL2capBuffer.get(paramArrayOfbyte, paramInt1, paramInt2);
    } else {
      if (VDBG) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("default: read(): offset: ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(" length:");
        stringBuilder1.append(paramInt2);
        Log.v("BluetoothSocket", stringBuilder1.toString());
      } 
      paramInt2 = this.mSocketIS.read(paramArrayOfbyte, paramInt1, paramInt2);
    } 
    if (paramInt2 >= 0) {
      if (VDBG) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("read out:  ");
        stringBuilder1.append(this.mSocketIS);
        stringBuilder1.append(" ret: ");
        stringBuilder1.append(paramInt2);
        Log.d("BluetoothSocket", stringBuilder1.toString());
      } 
      return paramInt2;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("bt socket closed, read return: ");
    stringBuilder.append(paramInt2);
    throw new IOException(stringBuilder.toString());
  }
  
  void removeChannel() {}
  
  public void requestMaximumTxDataLength() throws IOException {
    if (this.mDevice != null)
      try {
        if (this.mSocketState != SocketState.CLOSED) {
          IBluetooth iBluetooth = BluetoothAdapter.getDefaultAdapter().getBluetoothService(null);
          if (iBluetooth != null) {
            if (DBG)
              Log.d("BluetoothSocket", "requestMaximumTxDataLength"); 
            iBluetooth.getSocketManager().requestMaximumTxDataLength(this.mDevice);
            return;
          } 
          IOException iOException1 = new IOException();
          this("Bluetooth is off");
          throw iOException1;
        } 
        IOException iOException = new IOException();
        this("socket closed");
        throw iOException;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSocket", Log.getStackTraceString(new Throwable()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unable to send RPC: ");
        stringBuilder.append(remoteException.getMessage());
        throw new IOException(stringBuilder.toString());
      }  
    throw new IOException("requestMaximumTxDataLength is called on null device");
  }
  
  public void setExcludeSdp(boolean paramBoolean) {
    this.mExcludeSdp = paramBoolean;
  }
  
  void setServiceName(String paramString) {
    this.mServiceName = paramString;
  }
  
  int write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("write: ");
      stringBuilder.append(this.mSocketOS);
      stringBuilder.append(" length: ");
      stringBuilder.append(paramInt2);
      Log.d("BluetoothSocket", stringBuilder.toString());
    } 
    int i = this.mType;
    if (i == 3 || i == 4) {
      if (paramInt2 <= this.mMaxTxPacketSize) {
        this.mSocketOS.write(paramArrayOfbyte, paramInt1, paramInt2);
      } else {
        if (DBG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("WARNING: Write buffer larger than L2CAP packet size!\nPacket will be divided into SDU packets of size ");
          stringBuilder.append(this.mMaxTxPacketSize);
          Log.w("BluetoothSocket", stringBuilder.toString());
        } 
        i = paramInt1;
        for (paramInt1 = paramInt2; paramInt1 > 0; paramInt1 -= j) {
          int j = this.mMaxTxPacketSize;
          if (paramInt1 <= j)
            j = paramInt1; 
          this.mSocketOS.write(paramArrayOfbyte, i, j);
          i += j;
        } 
      } 
    } else {
      this.mSocketOS.write(paramArrayOfbyte, paramInt1, paramInt2);
    } 
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("write out: ");
      stringBuilder.append(this.mSocketOS);
      stringBuilder.append(" length: ");
      stringBuilder.append(paramInt2);
      Log.d("BluetoothSocket", stringBuilder.toString());
    } 
    return paramInt2;
  }
  
  private enum SocketState {
    CLOSED, CONNECTED, INIT, LISTENING;
    
    static {
      SocketState socketState = new SocketState("CLOSED", 3);
      CLOSED = socketState;
      $VALUES = new SocketState[] { INIT, CONNECTED, LISTENING, socketState };
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothSocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */