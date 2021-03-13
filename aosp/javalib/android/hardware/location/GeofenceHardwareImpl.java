package android.hardware.location;

import android.content.Context;
import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;

public final class GeofenceHardwareImpl {
  private static final int ADD_GEOFENCE_CALLBACK = 2;
  
  private static final int CALLBACK_ADD = 2;
  
  private static final int CALLBACK_REMOVE = 3;
  
  private static final int CAPABILITY_GNSS = 1;
  
  private static final boolean DEBUG = Log.isLoggable("GeofenceHardwareImpl", 3);
  
  private static final int FIRST_VERSION_WITH_CAPABILITIES = 2;
  
  private static final int GEOFENCE_CALLBACK_BINDER_DIED = 6;
  
  private static final int GEOFENCE_STATUS = 1;
  
  private static final int GEOFENCE_TRANSITION_CALLBACK = 1;
  
  private static final int LOCATION_HAS_ACCURACY = 16;
  
  private static final int LOCATION_HAS_ALTITUDE = 2;
  
  private static final int LOCATION_HAS_BEARING = 8;
  
  private static final int LOCATION_HAS_LAT_LONG = 1;
  
  private static final int LOCATION_HAS_SPEED = 4;
  
  private static final int LOCATION_INVALID = 0;
  
  private static final int MONITOR_CALLBACK_BINDER_DIED = 4;
  
  private static final int PAUSE_GEOFENCE_CALLBACK = 4;
  
  private static final int REAPER_GEOFENCE_ADDED = 1;
  
  private static final int REAPER_MONITOR_CALLBACK_ADDED = 2;
  
  private static final int REAPER_REMOVED = 3;
  
  private static final int REMOVE_GEOFENCE_CALLBACK = 3;
  
  private static final int RESOLUTION_LEVEL_COARSE = 2;
  
  private static final int RESOLUTION_LEVEL_FINE = 3;
  
  private static final int RESOLUTION_LEVEL_NONE = 1;
  
  private static final int RESUME_GEOFENCE_CALLBACK = 5;
  
  private static final String TAG = "GeofenceHardwareImpl";
  
  private static GeofenceHardwareImpl sInstance;
  
  private final ArrayList<IGeofenceHardwareMonitorCallback>[] mCallbacks = (ArrayList<IGeofenceHardwareMonitorCallback>[])new ArrayList[2];
  
  private Handler mCallbacksHandler = new Handler() {
      public void handleMessage(Message param1Message) {
        ArrayList<IGeofenceHardwareMonitorCallback> arrayList;
        int i = param1Message.what;
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i == 4) {
                IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)param1Message.obj;
                if (GeofenceHardwareImpl.DEBUG) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("Monitor callback reaped:");
                  stringBuilder.append(iGeofenceHardwareMonitorCallback);
                  Log.d("GeofenceHardwareImpl", stringBuilder.toString());
                } 
                arrayList = GeofenceHardwareImpl.this.mCallbacks[param1Message.arg1];
                if (arrayList != null && arrayList.contains(iGeofenceHardwareMonitorCallback))
                  arrayList.remove(iGeofenceHardwareMonitorCallback); 
              } 
            } else {
              i = ((Message)arrayList).arg1;
              IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)((Message)arrayList).obj;
              arrayList = GeofenceHardwareImpl.this.mCallbacks[i];
              if (arrayList != null)
                arrayList.remove(iGeofenceHardwareMonitorCallback); 
            } 
          } else {
            i = ((Message)arrayList).arg1;
            IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)((Message)arrayList).obj;
            ArrayList<IGeofenceHardwareMonitorCallback> arrayList1 = GeofenceHardwareImpl.this.mCallbacks[i];
            arrayList = arrayList1;
            if (arrayList1 == null) {
              arrayList = new ArrayList();
              GeofenceHardwareImpl.this.mCallbacks[i] = arrayList;
            } 
            if (!arrayList.contains(iGeofenceHardwareMonitorCallback))
              arrayList.add(iGeofenceHardwareMonitorCallback); 
          } 
        } else {
          GeofenceHardwareMonitorEvent geofenceHardwareMonitorEvent = (GeofenceHardwareMonitorEvent)((Message)arrayList).obj;
          ArrayList arrayList1 = GeofenceHardwareImpl.this.mCallbacks[geofenceHardwareMonitorEvent.getMonitoringType()];
          if (arrayList1 != null) {
            if (GeofenceHardwareImpl.DEBUG) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("MonitoringSystemChangeCallback: ");
              stringBuilder.append(geofenceHardwareMonitorEvent);
              Log.d("GeofenceHardwareImpl", stringBuilder.toString());
            } 
            for (IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback : arrayList1) {
              try {
                iGeofenceHardwareMonitorCallback.onMonitoringSystemChange(geofenceHardwareMonitorEvent);
              } catch (RemoteException remoteException) {
                Log.d("GeofenceHardwareImpl", "Error reporting onMonitoringSystemChange.", (Throwable)remoteException);
              } 
            } 
          } 
          GeofenceHardwareImpl.this.releaseWakeLock();
        } 
      }
    };
  
  private int mCapabilities;
  
  private final Context mContext;
  
  private IFusedGeofenceHardware mFusedService;
  
  private Handler mGeofenceHandler = new Handler() {
      public void handleMessage(Message param1Message) {
        // Byte code:
        //   0: aload_1
        //   1: getfield what : I
        //   4: tableswitch default -> 44, 1 -> 714, 2 -> 614, 3 -> 350, 4 -> 281, 5 -> 212, 6 -> 47
        //   44: goto -> 903
        //   47: aload_1
        //   48: getfield obj : Ljava/lang/Object;
        //   51: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   54: astore_2
        //   55: invokestatic access$500 : ()Z
        //   58: ifeq -> 92
        //   61: new java/lang/StringBuilder
        //   64: dup
        //   65: invokespecial <init> : ()V
        //   68: astore_3
        //   69: aload_3
        //   70: ldc 'Geofence callback reaped:'
        //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   75: pop
        //   76: aload_3
        //   77: aload_2
        //   78: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   81: pop
        //   82: ldc 'GeofenceHardwareImpl'
        //   84: aload_3
        //   85: invokevirtual toString : ()Ljava/lang/String;
        //   88: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
        //   91: pop
        //   92: aload_1
        //   93: getfield arg1 : I
        //   96: istore #4
        //   98: aload_0
        //   99: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   102: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   105: astore_1
        //   106: aload_1
        //   107: monitorenter
        //   108: iconst_0
        //   109: istore #5
        //   111: iload #5
        //   113: aload_0
        //   114: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   117: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   120: invokevirtual size : ()I
        //   123: if_icmpge -> 202
        //   126: aload_0
        //   127: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   130: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   133: iload #5
        //   135: invokevirtual valueAt : (I)Ljava/lang/Object;
        //   138: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   141: aload_2
        //   142: invokevirtual equals : (Ljava/lang/Object;)Z
        //   145: ifeq -> 196
        //   148: aload_0
        //   149: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   152: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   155: iload #5
        //   157: invokevirtual keyAt : (I)I
        //   160: istore #6
        //   162: aload_0
        //   163: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   166: aload_0
        //   167: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   170: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   173: iload #5
        //   175: invokevirtual keyAt : (I)I
        //   178: iload #4
        //   180: invokevirtual removeGeofence : (II)Z
        //   183: pop
        //   184: aload_0
        //   185: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   188: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   191: iload #6
        //   193: invokevirtual remove : (I)V
        //   196: iinc #5, 1
        //   199: goto -> 111
        //   202: aload_1
        //   203: monitorexit
        //   204: goto -> 903
        //   207: astore_2
        //   208: aload_1
        //   209: monitorexit
        //   210: aload_2
        //   211: athrow
        //   212: aload_1
        //   213: getfield arg1 : I
        //   216: istore #5
        //   218: aload_0
        //   219: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   222: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   225: astore_2
        //   226: aload_2
        //   227: monitorenter
        //   228: aload_0
        //   229: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   232: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   235: iload #5
        //   237: invokevirtual get : (I)Ljava/lang/Object;
        //   240: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   243: astore_3
        //   244: aload_2
        //   245: monitorexit
        //   246: aload_3
        //   247: ifnull -> 266
        //   250: aload_3
        //   251: iload #5
        //   253: aload_1
        //   254: getfield arg2 : I
        //   257: invokeinterface onGeofenceResume : (II)V
        //   262: goto -> 266
        //   265: astore_1
        //   266: aload_0
        //   267: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   270: invokestatic access$100 : (Landroid/hardware/location/GeofenceHardwareImpl;)V
        //   273: goto -> 903
        //   276: astore_1
        //   277: aload_2
        //   278: monitorexit
        //   279: aload_1
        //   280: athrow
        //   281: aload_1
        //   282: getfield arg1 : I
        //   285: istore #5
        //   287: aload_0
        //   288: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   291: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   294: astore_2
        //   295: aload_2
        //   296: monitorenter
        //   297: aload_0
        //   298: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   301: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   304: iload #5
        //   306: invokevirtual get : (I)Ljava/lang/Object;
        //   309: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   312: astore_3
        //   313: aload_2
        //   314: monitorexit
        //   315: aload_3
        //   316: ifnull -> 335
        //   319: aload_3
        //   320: iload #5
        //   322: aload_1
        //   323: getfield arg2 : I
        //   326: invokeinterface onGeofencePause : (II)V
        //   331: goto -> 335
        //   334: astore_1
        //   335: aload_0
        //   336: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   339: invokestatic access$100 : (Landroid/hardware/location/GeofenceHardwareImpl;)V
        //   342: goto -> 903
        //   345: astore_1
        //   346: aload_2
        //   347: monitorexit
        //   348: aload_1
        //   349: athrow
        //   350: aload_1
        //   351: getfield arg1 : I
        //   354: istore #5
        //   356: aload_0
        //   357: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   360: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   363: astore_3
        //   364: aload_3
        //   365: monitorenter
        //   366: aload_0
        //   367: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   370: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   373: iload #5
        //   375: invokevirtual get : (I)Ljava/lang/Object;
        //   378: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   381: astore_2
        //   382: aload_3
        //   383: monitorexit
        //   384: aload_2
        //   385: ifnull -> 599
        //   388: aload_2
        //   389: iload #5
        //   391: aload_1
        //   392: getfield arg2 : I
        //   395: invokeinterface onGeofenceRemove : (II)V
        //   400: goto -> 404
        //   403: astore_1
        //   404: aload_2
        //   405: invokeinterface asBinder : ()Landroid/os/IBinder;
        //   410: astore_1
        //   411: iconst_0
        //   412: istore #6
        //   414: aload_0
        //   415: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   418: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   421: astore_2
        //   422: aload_2
        //   423: monitorenter
        //   424: aload_0
        //   425: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   428: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   431: iload #5
        //   433: invokevirtual remove : (I)V
        //   436: iconst_0
        //   437: istore #4
        //   439: iload #6
        //   441: istore #5
        //   443: iload #4
        //   445: aload_0
        //   446: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   449: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   452: invokevirtual size : ()I
        //   455: if_icmpge -> 494
        //   458: aload_0
        //   459: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   462: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   465: iload #4
        //   467: invokevirtual valueAt : (I)Ljava/lang/Object;
        //   470: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   473: invokeinterface asBinder : ()Landroid/os/IBinder;
        //   478: aload_1
        //   479: if_acmpne -> 488
        //   482: iconst_1
        //   483: istore #5
        //   485: goto -> 494
        //   488: iinc #4, 1
        //   491: goto -> 439
        //   494: aload_2
        //   495: monitorexit
        //   496: iload #5
        //   498: ifne -> 599
        //   501: aload_0
        //   502: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   505: invokestatic access$200 : (Landroid/hardware/location/GeofenceHardwareImpl;)Ljava/util/ArrayList;
        //   508: invokevirtual iterator : ()Ljava/util/Iterator;
        //   511: astore_2
        //   512: aload_2
        //   513: invokeinterface hasNext : ()Z
        //   518: ifeq -> 599
        //   521: aload_2
        //   522: invokeinterface next : ()Ljava/lang/Object;
        //   527: checkcast android/hardware/location/GeofenceHardwareImpl$Reaper
        //   530: astore_3
        //   531: aload_3
        //   532: invokestatic access$300 : (Landroid/hardware/location/GeofenceHardwareImpl$Reaper;)Landroid/hardware/location/IGeofenceHardwareCallback;
        //   535: ifnull -> 591
        //   538: aload_3
        //   539: invokestatic access$300 : (Landroid/hardware/location/GeofenceHardwareImpl$Reaper;)Landroid/hardware/location/IGeofenceHardwareCallback;
        //   542: invokeinterface asBinder : ()Landroid/os/IBinder;
        //   547: aload_1
        //   548: if_acmpne -> 591
        //   551: aload_2
        //   552: invokeinterface remove : ()V
        //   557: aload_3
        //   558: invokestatic access$400 : (Landroid/hardware/location/GeofenceHardwareImpl$Reaper;)Z
        //   561: pop
        //   562: invokestatic access$500 : ()Z
        //   565: ifeq -> 591
        //   568: ldc 'GeofenceHardwareImpl'
        //   570: ldc 'Removed reaper %s because binder %s is no longer needed.'
        //   572: iconst_2
        //   573: anewarray java/lang/Object
        //   576: dup
        //   577: iconst_0
        //   578: aload_3
        //   579: aastore
        //   580: dup
        //   581: iconst_1
        //   582: aload_1
        //   583: aastore
        //   584: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   587: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
        //   590: pop
        //   591: goto -> 512
        //   594: astore_1
        //   595: aload_2
        //   596: monitorexit
        //   597: aload_1
        //   598: athrow
        //   599: aload_0
        //   600: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   603: invokestatic access$100 : (Landroid/hardware/location/GeofenceHardwareImpl;)V
        //   606: goto -> 903
        //   609: astore_1
        //   610: aload_3
        //   611: monitorexit
        //   612: aload_1
        //   613: athrow
        //   614: aload_1
        //   615: getfield arg1 : I
        //   618: istore #5
        //   620: aload_0
        //   621: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   624: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   627: astore_2
        //   628: aload_2
        //   629: monitorenter
        //   630: aload_0
        //   631: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   634: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   637: iload #5
        //   639: invokevirtual get : (I)Ljava/lang/Object;
        //   642: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   645: astore_3
        //   646: aload_2
        //   647: monitorexit
        //   648: aload_3
        //   649: ifnull -> 699
        //   652: aload_3
        //   653: iload #5
        //   655: aload_1
        //   656: getfield arg2 : I
        //   659: invokeinterface onGeofenceAdd : (II)V
        //   664: goto -> 699
        //   667: astore_2
        //   668: new java/lang/StringBuilder
        //   671: dup
        //   672: invokespecial <init> : ()V
        //   675: astore_1
        //   676: aload_1
        //   677: ldc 'Remote Exception:'
        //   679: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   682: pop
        //   683: aload_1
        //   684: aload_2
        //   685: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   688: pop
        //   689: ldc 'GeofenceHardwareImpl'
        //   691: aload_1
        //   692: invokevirtual toString : ()Ljava/lang/String;
        //   695: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
        //   698: pop
        //   699: aload_0
        //   700: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   703: invokestatic access$100 : (Landroid/hardware/location/GeofenceHardwareImpl;)V
        //   706: goto -> 903
        //   709: astore_1
        //   710: aload_2
        //   711: monitorexit
        //   712: aload_1
        //   713: athrow
        //   714: aload_1
        //   715: getfield obj : Ljava/lang/Object;
        //   718: checkcast android/hardware/location/GeofenceHardwareImpl$GeofenceTransition
        //   721: astore_3
        //   722: aload_0
        //   723: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   726: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   729: astore_1
        //   730: aload_1
        //   731: monitorenter
        //   732: aload_0
        //   733: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   736: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   739: aload_3
        //   740: invokestatic access$600 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)I
        //   743: invokevirtual get : (I)Ljava/lang/Object;
        //   746: checkcast android/hardware/location/IGeofenceHardwareCallback
        //   749: astore_2
        //   750: invokestatic access$500 : ()Z
        //   753: ifeq -> 852
        //   756: new java/lang/StringBuilder
        //   759: astore #7
        //   761: aload #7
        //   763: invokespecial <init> : ()V
        //   766: aload #7
        //   768: ldc 'GeofenceTransistionCallback: GPS : GeofenceId: '
        //   770: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   773: pop
        //   774: aload #7
        //   776: aload_3
        //   777: invokestatic access$600 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)I
        //   780: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   783: pop
        //   784: aload #7
        //   786: ldc ' Transition: '
        //   788: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   791: pop
        //   792: aload #7
        //   794: aload_3
        //   795: invokestatic access$700 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)I
        //   798: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   801: pop
        //   802: aload #7
        //   804: ldc ' Location: '
        //   806: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   809: pop
        //   810: aload #7
        //   812: aload_3
        //   813: invokestatic access$800 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)Landroid/location/Location;
        //   816: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   819: pop
        //   820: aload #7
        //   822: ldc ':'
        //   824: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   827: pop
        //   828: aload #7
        //   830: aload_0
        //   831: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   834: invokestatic access$000 : (Landroid/hardware/location/GeofenceHardwareImpl;)Landroid/util/SparseArray;
        //   837: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   840: pop
        //   841: ldc 'GeofenceHardwareImpl'
        //   843: aload #7
        //   845: invokevirtual toString : ()Ljava/lang/String;
        //   848: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
        //   851: pop
        //   852: aload_1
        //   853: monitorexit
        //   854: aload_2
        //   855: ifnull -> 888
        //   858: aload_2
        //   859: aload_3
        //   860: invokestatic access$600 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)I
        //   863: aload_3
        //   864: invokestatic access$700 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)I
        //   867: aload_3
        //   868: invokestatic access$800 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)Landroid/location/Location;
        //   871: aload_3
        //   872: invokestatic access$900 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)J
        //   875: aload_3
        //   876: invokestatic access$1000 : (Landroid/hardware/location/GeofenceHardwareImpl$GeofenceTransition;)I
        //   879: invokeinterface onGeofenceTransition : (IILandroid/location/Location;JI)V
        //   884: goto -> 888
        //   887: astore_1
        //   888: aload_0
        //   889: getfield this$0 : Landroid/hardware/location/GeofenceHardwareImpl;
        //   892: invokestatic access$100 : (Landroid/hardware/location/GeofenceHardwareImpl;)V
        //   895: goto -> 903
        //   898: astore_2
        //   899: aload_1
        //   900: monitorexit
        //   901: aload_2
        //   902: athrow
        //   903: return
        // Exception table:
        //   from	to	target	type
        //   111	196	207	finally
        //   202	204	207	finally
        //   208	210	207	finally
        //   228	246	276	finally
        //   250	262	265	android/os/RemoteException
        //   277	279	276	finally
        //   297	315	345	finally
        //   319	331	334	android/os/RemoteException
        //   346	348	345	finally
        //   366	384	609	finally
        //   388	400	403	android/os/RemoteException
        //   424	436	594	finally
        //   443	482	594	finally
        //   494	496	594	finally
        //   595	597	594	finally
        //   610	612	609	finally
        //   630	648	709	finally
        //   652	664	667	android/os/RemoteException
        //   710	712	709	finally
        //   732	852	898	finally
        //   852	854	898	finally
        //   858	884	887	android/os/RemoteException
        //   899	901	898	finally
      }
    };
  
  private final SparseArray<IGeofenceHardwareCallback> mGeofences = new SparseArray();
  
  private IGpsGeofenceHardware mGpsService;
  
  private Handler mReaperHandler = new Handler() {
      public void handleMessage(Message param1Message) {
        int i = param1Message.what;
        if (i != 1) {
          GeofenceHardwareImpl.Reaper reaper;
          if (i != 2) {
            if (i == 3) {
              reaper = (GeofenceHardwareImpl.Reaper)param1Message.obj;
              GeofenceHardwareImpl.this.mReapers.remove(reaper);
            } 
          } else {
            IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = (IGeofenceHardwareMonitorCallback)((Message)reaper).obj;
            i = ((Message)reaper).arg1;
            reaper = new GeofenceHardwareImpl.Reaper(iGeofenceHardwareMonitorCallback, i);
            if (!GeofenceHardwareImpl.this.mReapers.contains(reaper)) {
              GeofenceHardwareImpl.this.mReapers.add(reaper);
              IBinder iBinder = iGeofenceHardwareMonitorCallback.asBinder();
              try {
                iBinder.linkToDeath(reaper, 0);
              } catch (RemoteException remoteException) {}
            } 
          } 
        } else {
          IGeofenceHardwareCallback iGeofenceHardwareCallback = (IGeofenceHardwareCallback)((Message)remoteException).obj;
          i = ((Message)remoteException).arg1;
          GeofenceHardwareImpl.Reaper reaper = new GeofenceHardwareImpl.Reaper(iGeofenceHardwareCallback, i);
          if (!GeofenceHardwareImpl.this.mReapers.contains(reaper)) {
            GeofenceHardwareImpl.this.mReapers.add(reaper);
            IBinder iBinder = iGeofenceHardwareCallback.asBinder();
            try {
              iBinder.linkToDeath(reaper, 0);
            } catch (RemoteException remoteException1) {}
          } 
        } 
      }
    };
  
  private final ArrayList<Reaper> mReapers = new ArrayList<>();
  
  private int[] mSupportedMonitorTypes = new int[2];
  
  private int mVersion = 1;
  
  private PowerManager.WakeLock mWakeLock;
  
  private GeofenceHardwareImpl(Context paramContext) {
    this.mContext = paramContext;
    setMonitorAvailability(0, 2);
    setMonitorAvailability(1, 2);
  }
  
  private void acquireWakeLock() {
    if (this.mWakeLock == null)
      this.mWakeLock = ((PowerManager)this.mContext.getSystemService("power")).newWakeLock(1, "GeofenceHardwareImpl"); 
    this.mWakeLock.acquire();
  }
  
  public static GeofenceHardwareImpl getInstance(Context paramContext) {
    // Byte code:
    //   0: ldc android/hardware/location/GeofenceHardwareImpl
    //   2: monitorenter
    //   3: getstatic android/hardware/location/GeofenceHardwareImpl.sInstance : Landroid/hardware/location/GeofenceHardwareImpl;
    //   6: ifnonnull -> 22
    //   9: new android/hardware/location/GeofenceHardwareImpl
    //   12: astore_1
    //   13: aload_1
    //   14: aload_0
    //   15: invokespecial <init> : (Landroid/content/Context;)V
    //   18: aload_1
    //   19: putstatic android/hardware/location/GeofenceHardwareImpl.sInstance : Landroid/hardware/location/GeofenceHardwareImpl;
    //   22: getstatic android/hardware/location/GeofenceHardwareImpl.sInstance : Landroid/hardware/location/GeofenceHardwareImpl;
    //   25: astore_0
    //   26: ldc android/hardware/location/GeofenceHardwareImpl
    //   28: monitorexit
    //   29: aload_0
    //   30: areturn
    //   31: astore_0
    //   32: ldc android/hardware/location/GeofenceHardwareImpl
    //   34: monitorexit
    //   35: aload_0
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   3	22	31	finally
    //   22	26	31	finally
  }
  
  private void releaseWakeLock() {
    if (this.mWakeLock.isHeld())
      this.mWakeLock.release(); 
  }
  
  private void reportGeofenceOperationStatus(int paramInt1, int paramInt2, int paramInt3) {
    acquireWakeLock();
    Message message = this.mGeofenceHandler.obtainMessage(paramInt1);
    message.arg1 = paramInt2;
    message.arg2 = paramInt3;
    message.sendToTarget();
  }
  
  private void setMonitorAvailability(int paramInt1, int paramInt2) {
    synchronized (this.mSupportedMonitorTypes) {
      this.mSupportedMonitorTypes[paramInt1] = paramInt2;
      return;
    } 
  }
  
  private void updateFusedHardwareAvailability() {
    boolean bool;
    try {
      if (this.mVersion < 2 || (this.mCapabilities & 0x1) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (this.mFusedService != null) {
        boolean bool1 = this.mFusedService.isSupported();
        if (bool1 && bool) {
          bool = true;
        } else {
          bool = false;
        } 
      } else {
        bool = false;
      } 
    } catch (RemoteException remoteException) {
      Log.e("GeofenceHardwareImpl", "RemoteException calling LocationManagerService");
      bool = false;
    } 
    if (bool)
      setMonitorAvailability(1, 0); 
  }
  
  private void updateGpsHardwareAvailability() {
    boolean bool;
    try {
      bool = this.mGpsService.isHardwareGeofenceSupported();
    } catch (RemoteException remoteException) {
      Log.e("GeofenceHardwareImpl", "Remote Exception calling LocationManagerService");
      bool = false;
    } 
    if (bool)
      setMonitorAvailability(0, 0); 
  }
  
  public boolean addCircularFence(int paramInt, GeofenceHardwareRequestParcelable paramGeofenceHardwareRequestParcelable, IGeofenceHardwareCallback paramIGeofenceHardwareCallback) {
    SparseArray<IGeofenceHardwareCallback> sparseArray;
    IGpsGeofenceHardware iGpsGeofenceHardware;
    int i = paramGeofenceHardwareRequestParcelable.getId();
    if (DEBUG)
      Log.d("GeofenceHardwareImpl", String.format("addCircularFence: monitoringType=%d, %s", new Object[] { Integer.valueOf(paramInt), paramGeofenceHardwareRequestParcelable })); 
    synchronized (this.mGeofences) {
      boolean bool;
      this.mGeofences.put(i, paramIGeofenceHardwareCallback);
      if (paramInt != 0) {
        if (paramInt != 1) {
          bool = false;
        } else {
          IFusedGeofenceHardware iFusedGeofenceHardware = this.mFusedService;
          if (iFusedGeofenceHardware == null)
            return false; 
          try {
            iFusedGeofenceHardware.addGeofences(new GeofenceHardwareRequestParcelable[] { paramGeofenceHardwareRequestParcelable });
            bool = true;
          } catch (RemoteException remoteException) {
            Log.e("GeofenceHardwareImpl", "AddGeofence: RemoteException calling LocationManagerService");
            bool = false;
          } 
        } 
      } else {
        iGpsGeofenceHardware = this.mGpsService;
        if (iGpsGeofenceHardware == null)
          return false; 
        try {
          bool = iGpsGeofenceHardware.addCircularHardwareGeofence(remoteException.getId(), remoteException.getLatitude(), remoteException.getLongitude(), remoteException.getRadius(), remoteException.getLastTransition(), remoteException.getMonitorTransitions(), remoteException.getNotificationResponsiveness(), remoteException.getUnknownTimer());
        } catch (RemoteException remoteException1) {
          Log.e("GeofenceHardwareImpl", "AddGeofence: Remote Exception calling LocationManagerService");
          bool = false;
        } 
      } 
      if (bool) {
        Message message = this.mReaperHandler.obtainMessage(1, paramIGeofenceHardwareCallback);
        message.arg1 = paramInt;
        this.mReaperHandler.sendMessage(message);
      } else {
        synchronized (this.mGeofences) {
          this.mGeofences.remove(i);
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("addCircularFence: Result is: ");
            stringBuilder.append(bool);
            Log.d("GeofenceHardwareImpl", stringBuilder.toString());
          } 
          return bool;
        } 
      } 
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addCircularFence: Result is: ");
        stringBuilder.append(bool);
        Log.d("GeofenceHardwareImpl", stringBuilder.toString());
      } 
      return bool;
    } 
  }
  
  int getAllowedResolutionLevel(int paramInt1, int paramInt2) {
    return (this.mContext.checkPermission("android.permission.ACCESS_FINE_LOCATION", paramInt1, paramInt2) == 0) ? 3 : ((this.mContext.checkPermission("android.permission.ACCESS_COARSE_LOCATION", paramInt1, paramInt2) == 0) ? 2 : 1);
  }
  
  public int getCapabilitiesForMonitoringType(int paramInt) {
    return (this.mSupportedMonitorTypes[paramInt] == 0) ? ((paramInt != 0) ? ((paramInt == 1) ? ((this.mVersion >= 2) ? this.mCapabilities : 1) : 0) : 1) : 0;
  }
  
  int getMonitoringResolutionLevel(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? 1 : 3) : 3;
  }
  
  public int[] getMonitoringTypes() {
    synchronized (this.mSupportedMonitorTypes) {
      boolean bool1;
      boolean bool2;
      if (this.mSupportedMonitorTypes[0] != 2) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (this.mSupportedMonitorTypes[1] != 2) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      return bool1 ? (bool2 ? new int[] { 0, 1 } : new int[] { 0 }) : (bool2 ? new int[] { 1 } : new int[0]);
    } 
  }
  
  public int getStatusOfMonitoringType(int paramInt) {
    synchronized (this.mSupportedMonitorTypes) {
      if (paramInt < this.mSupportedMonitorTypes.length && paramInt >= 0) {
        paramInt = this.mSupportedMonitorTypes[paramInt];
        return paramInt;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Unknown monitoring type");
      throw illegalArgumentException;
    } 
  }
  
  public void onCapabilities(int paramInt) {
    this.mCapabilities = paramInt;
    updateFusedHardwareAvailability();
  }
  
  public boolean pauseGeofence(int paramInt1, int paramInt2) {
    SparseArray<IGeofenceHardwareCallback> sparseArray;
    StringBuilder stringBuilder;
    if (DEBUG) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Pause Geofence: GeofenceId: ");
      stringBuilder.append(paramInt1);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    synchronized (this.mGeofences) {
      if (this.mGeofences.get(paramInt1) != null) {
        boolean bool;
        if (paramInt2 != 0) {
          if (paramInt2 != 1) {
            bool = false;
          } else {
            IFusedGeofenceHardware iFusedGeofenceHardware = this.mFusedService;
            if (iFusedGeofenceHardware == null)
              return false; 
            try {
              iFusedGeofenceHardware.pauseMonitoringGeofence(paramInt1);
              bool = true;
            } catch (RemoteException remoteException) {
              Log.e("GeofenceHardwareImpl", "PauseGeofence: RemoteException calling LocationManagerService");
              bool = false;
            } 
          } 
        } else {
          IGpsGeofenceHardware iGpsGeofenceHardware = this.mGpsService;
          if (iGpsGeofenceHardware == null)
            return false; 
          try {
            bool = iGpsGeofenceHardware.pauseHardwareGeofence(paramInt1);
          } catch (RemoteException remoteException) {
            Log.e("GeofenceHardwareImpl", "PauseGeofence: Remote Exception calling LocationManagerService");
            bool = false;
          } 
        } 
        if (DEBUG) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("pauseGeofence: Result is: ");
          stringBuilder.append(bool);
          Log.d("GeofenceHardwareImpl", stringBuilder.toString());
        } 
        return bool;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append("Geofence ");
      stringBuilder1.append(paramInt1);
      stringBuilder1.append(" not registered.");
      this(stringBuilder1.toString());
      throw illegalArgumentException;
    } 
  }
  
  public boolean registerForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) {
    Message message2 = this.mReaperHandler.obtainMessage(2, paramIGeofenceHardwareMonitorCallback);
    message2.arg1 = paramInt;
    this.mReaperHandler.sendMessage(message2);
    Message message1 = this.mCallbacksHandler.obtainMessage(2, paramIGeofenceHardwareMonitorCallback);
    message1.arg1 = paramInt;
    this.mCallbacksHandler.sendMessage(message1);
    return true;
  }
  
  public boolean removeGeofence(int paramInt1, int paramInt2) {
    SparseArray<IGeofenceHardwareCallback> sparseArray;
    StringBuilder stringBuilder;
    if (DEBUG) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Remove Geofence: GeofenceId: ");
      stringBuilder.append(paramInt1);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    synchronized (this.mGeofences) {
      if (this.mGeofences.get(paramInt1) != null) {
        boolean bool;
        if (paramInt2 != 0) {
          if (paramInt2 != 1) {
            bool = false;
          } else {
            IFusedGeofenceHardware iFusedGeofenceHardware = this.mFusedService;
            if (iFusedGeofenceHardware == null)
              return false; 
            try {
              iFusedGeofenceHardware.removeGeofences(new int[] { paramInt1 });
              bool = true;
            } catch (RemoteException remoteException) {
              Log.e("GeofenceHardwareImpl", "RemoveGeofence: RemoteException calling LocationManagerService");
              bool = false;
            } 
          } 
        } else {
          IGpsGeofenceHardware iGpsGeofenceHardware = this.mGpsService;
          if (iGpsGeofenceHardware == null)
            return false; 
          try {
            bool = iGpsGeofenceHardware.removeHardwareGeofence(paramInt1);
          } catch (RemoteException remoteException) {
            Log.e("GeofenceHardwareImpl", "RemoveGeofence: Remote Exception calling LocationManagerService");
            bool = false;
          } 
        } 
        if (DEBUG) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("removeGeofence: Result is: ");
          stringBuilder.append(bool);
          Log.d("GeofenceHardwareImpl", stringBuilder.toString());
        } 
        return bool;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append("Geofence ");
      stringBuilder1.append(paramInt1);
      stringBuilder1.append(" not registered.");
      this(stringBuilder1.toString());
      throw illegalArgumentException;
    } 
  }
  
  public void reportGeofenceAddStatus(int paramInt1, int paramInt2) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AddCallback| id:");
      stringBuilder.append(paramInt1);
      stringBuilder.append(", status:");
      stringBuilder.append(paramInt2);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    reportGeofenceOperationStatus(2, paramInt1, paramInt2);
  }
  
  public void reportGeofenceMonitorStatus(int paramInt1, int paramInt2, Location paramLocation, int paramInt3) {
    setMonitorAvailability(paramInt1, paramInt2);
    acquireWakeLock();
    GeofenceHardwareMonitorEvent geofenceHardwareMonitorEvent = new GeofenceHardwareMonitorEvent(paramInt1, paramInt2, paramInt3, paramLocation);
    this.mCallbacksHandler.obtainMessage(1, geofenceHardwareMonitorEvent).sendToTarget();
  }
  
  public void reportGeofencePauseStatus(int paramInt1, int paramInt2) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("PauseCallbac| id:");
      stringBuilder.append(paramInt1);
      stringBuilder.append(", status");
      stringBuilder.append(paramInt2);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    reportGeofenceOperationStatus(4, paramInt1, paramInt2);
  }
  
  public void reportGeofenceRemoveStatus(int paramInt1, int paramInt2) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("RemoveCallback| id:");
      stringBuilder.append(paramInt1);
      stringBuilder.append(", status:");
      stringBuilder.append(paramInt2);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    reportGeofenceOperationStatus(3, paramInt1, paramInt2);
  }
  
  public void reportGeofenceResumeStatus(int paramInt1, int paramInt2) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ResumeCallback| id:");
      stringBuilder.append(paramInt1);
      stringBuilder.append(", status:");
      stringBuilder.append(paramInt2);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    reportGeofenceOperationStatus(5, paramInt1, paramInt2);
  }
  
  public void reportGeofenceTransition(int paramInt1, Location paramLocation, int paramInt2, long paramLong, int paramInt3, int paramInt4) {
    if (paramLocation == null) {
      Log.e("GeofenceHardwareImpl", String.format("Invalid Geofence Transition: location=null", new Object[0]));
      return;
    } 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("GeofenceTransition| ");
      stringBuilder.append(paramLocation);
      stringBuilder.append(", transition:");
      stringBuilder.append(paramInt2);
      stringBuilder.append(", transitionTimestamp:");
      stringBuilder.append(paramLong);
      stringBuilder.append(", monitoringType:");
      stringBuilder.append(paramInt3);
      stringBuilder.append(", sourcesUsed:");
      stringBuilder.append(paramInt4);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    GeofenceTransition geofenceTransition = new GeofenceTransition(paramInt1, paramInt2, paramLong, paramLocation, paramInt3, paramInt4);
    acquireWakeLock();
    this.mGeofenceHandler.obtainMessage(1, geofenceTransition).sendToTarget();
  }
  
  public boolean resumeGeofence(int paramInt1, int paramInt2, int paramInt3) {
    SparseArray<IGeofenceHardwareCallback> sparseArray;
    StringBuilder stringBuilder;
    if (DEBUG) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Resume Geofence: GeofenceId: ");
      stringBuilder.append(paramInt1);
      Log.d("GeofenceHardwareImpl", stringBuilder.toString());
    } 
    synchronized (this.mGeofences) {
      if (this.mGeofences.get(paramInt1) != null) {
        boolean bool;
        if (paramInt2 != 0) {
          if (paramInt2 != 1) {
            bool = false;
          } else {
            IFusedGeofenceHardware iFusedGeofenceHardware = this.mFusedService;
            if (iFusedGeofenceHardware == null)
              return false; 
            try {
              iFusedGeofenceHardware.resumeMonitoringGeofence(paramInt1, paramInt3);
              bool = true;
            } catch (RemoteException remoteException) {
              Log.e("GeofenceHardwareImpl", "ResumeGeofence: RemoteException calling LocationManagerService");
              bool = false;
            } 
          } 
        } else {
          IGpsGeofenceHardware iGpsGeofenceHardware = this.mGpsService;
          if (iGpsGeofenceHardware == null)
            return false; 
          try {
            bool = iGpsGeofenceHardware.resumeHardwareGeofence(paramInt1, paramInt3);
          } catch (RemoteException remoteException) {
            Log.e("GeofenceHardwareImpl", "ResumeGeofence: Remote Exception calling LocationManagerService");
            bool = false;
          } 
        } 
        if (DEBUG) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("resumeGeofence: Result is: ");
          stringBuilder.append(bool);
          Log.d("GeofenceHardwareImpl", stringBuilder.toString());
        } 
        return bool;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append("Geofence ");
      stringBuilder1.append(paramInt1);
      stringBuilder1.append(" not registered.");
      this(stringBuilder1.toString());
      throw illegalArgumentException;
    } 
  }
  
  public void setFusedGeofenceHardware(IFusedGeofenceHardware paramIFusedGeofenceHardware) {
    if (this.mFusedService == null) {
      this.mFusedService = paramIFusedGeofenceHardware;
      updateFusedHardwareAvailability();
    } else if (paramIFusedGeofenceHardware == null) {
      this.mFusedService = null;
      Log.w("GeofenceHardwareImpl", "Fused Geofence Hardware service seems to have crashed");
    } else {
      Log.e("GeofenceHardwareImpl", "Error: FusedService being set again");
    } 
  }
  
  public void setGpsHardwareGeofence(IGpsGeofenceHardware paramIGpsGeofenceHardware) {
    if (this.mGpsService == null) {
      this.mGpsService = paramIGpsGeofenceHardware;
      updateGpsHardwareAvailability();
    } else if (paramIGpsGeofenceHardware == null) {
      this.mGpsService = null;
      Log.w("GeofenceHardwareImpl", "GPS Geofence Hardware service seems to have crashed");
    } else {
      Log.e("GeofenceHardwareImpl", "Error: GpsService being set again.");
    } 
  }
  
  public void setVersion(int paramInt) {
    this.mVersion = paramInt;
    updateFusedHardwareAvailability();
  }
  
  public boolean unregisterForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) {
    Message message = this.mCallbacksHandler.obtainMessage(3, paramIGeofenceHardwareMonitorCallback);
    message.arg1 = paramInt;
    this.mCallbacksHandler.sendMessage(message);
    return true;
  }
  
  private class GeofenceTransition {
    private int mGeofenceId;
    
    private Location mLocation;
    
    private int mMonitoringType;
    
    private int mSourcesUsed;
    
    private long mTimestamp;
    
    private int mTransition;
    
    GeofenceTransition(int param1Int1, int param1Int2, long param1Long, Location param1Location, int param1Int3, int param1Int4) {
      this.mGeofenceId = param1Int1;
      this.mTransition = param1Int2;
      this.mTimestamp = param1Long;
      this.mLocation = param1Location;
      this.mMonitoringType = param1Int3;
      this.mSourcesUsed = param1Int4;
    }
  }
  
  class Reaper implements IBinder.DeathRecipient {
    private IGeofenceHardwareCallback mCallback;
    
    private IGeofenceHardwareMonitorCallback mMonitorCallback;
    
    private int mMonitoringType;
    
    Reaper(IGeofenceHardwareCallback param1IGeofenceHardwareCallback, int param1Int) {
      this.mCallback = param1IGeofenceHardwareCallback;
      this.mMonitoringType = param1Int;
    }
    
    Reaper(IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback, int param1Int) {
      this.mMonitorCallback = param1IGeofenceHardwareMonitorCallback;
      this.mMonitoringType = param1Int;
    }
    
    private boolean binderEquals(IInterface param1IInterface1, IInterface param1IInterface2) {
      boolean bool = true;
      null = true;
      if (param1IInterface1 == null) {
        if (param1IInterface2 != null)
          null = false; 
        return null;
      } 
      return (param1IInterface2 != null && param1IInterface1.asBinder() == param1IInterface2.asBinder()) ? bool : false;
    }
    
    private boolean callbackEquals(IGeofenceHardwareCallback param1IGeofenceHardwareCallback) {
      boolean bool;
      IGeofenceHardwareCallback iGeofenceHardwareCallback = this.mCallback;
      if (iGeofenceHardwareCallback != null && iGeofenceHardwareCallback.asBinder() == param1IGeofenceHardwareCallback.asBinder()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private boolean unlinkToDeath() {
      IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = this.mMonitorCallback;
      if (iGeofenceHardwareMonitorCallback != null)
        return iGeofenceHardwareMonitorCallback.asBinder().unlinkToDeath(this, 0); 
      IGeofenceHardwareCallback iGeofenceHardwareCallback = this.mCallback;
      return (iGeofenceHardwareCallback != null) ? iGeofenceHardwareCallback.asBinder().unlinkToDeath(this, 0) : true;
    }
    
    public void binderDied() {
      if (this.mCallback != null) {
        Message message1 = GeofenceHardwareImpl.this.mGeofenceHandler.obtainMessage(6, this.mCallback);
        message1.arg1 = this.mMonitoringType;
        GeofenceHardwareImpl.this.mGeofenceHandler.sendMessage(message1);
      } else if (this.mMonitorCallback != null) {
        Message message1 = GeofenceHardwareImpl.this.mCallbacksHandler.obtainMessage(4, this.mMonitorCallback);
        message1.arg1 = this.mMonitoringType;
        GeofenceHardwareImpl.this.mCallbacksHandler.sendMessage(message1);
      } 
      Message message = GeofenceHardwareImpl.this.mReaperHandler.obtainMessage(3, this);
      GeofenceHardwareImpl.this.mReaperHandler.sendMessage(message);
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = false;
      if (param1Object == null)
        return false; 
      if (param1Object == this)
        return true; 
      param1Object = param1Object;
      if (binderEquals(((Reaper)param1Object).mCallback, this.mCallback) && binderEquals(((Reaper)param1Object).mMonitorCallback, this.mMonitorCallback) && ((Reaper)param1Object).mMonitoringType == this.mMonitoringType)
        bool = true; 
      return bool;
    }
    
    public int hashCode() {
      byte b;
      IGeofenceHardwareCallback iGeofenceHardwareCallback = this.mCallback;
      int i = 0;
      if (iGeofenceHardwareCallback != null) {
        b = iGeofenceHardwareCallback.asBinder().hashCode();
      } else {
        b = 0;
      } 
      IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = this.mMonitorCallback;
      if (iGeofenceHardwareMonitorCallback != null)
        i = iGeofenceHardwareMonitorCallback.asBinder().hashCode(); 
      return ((17 * 31 + b) * 31 + i) * 31 + this.mMonitoringType;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */