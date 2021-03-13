package android.app;

import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.Adjustment;
import android.service.notification.Condition;
import android.service.notification.IConditionProvider;
import android.service.notification.INotificationListener;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import java.util.List;

class Proxy implements INotificationManager {
  public static INotificationManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public String addAutomaticZenRule(AutomaticZenRule paramAutomaticZenRule) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramAutomaticZenRule != null) {
        parcel1.writeInt(1);
        paramAutomaticZenRule.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(121, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().addAutomaticZenRule(paramAutomaticZenRule); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void allowAssistantAdjustment(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().allowAssistantAdjustment(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void applyAdjustmentFromAssistant(INotificationListener paramINotificationListener, Adjustment paramAdjustment) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAdjustment != null) {
        parcel1.writeInt(1);
        paramAdjustment.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(90, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().applyAdjustmentFromAssistant(paramINotificationListener, paramAdjustment);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void applyAdjustmentsFromAssistant(INotificationListener paramINotificationListener, List<Adjustment> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeTypedList(paramList);
      if (!this.mRemote.transact(91, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().applyAdjustmentsFromAssistant(paramINotificationListener, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void applyEnqueuedAdjustmentFromAssistant(INotificationListener paramINotificationListener, Adjustment paramAdjustment) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAdjustment != null) {
        parcel1.writeInt(1);
        paramAdjustment.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(89, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().applyEnqueuedAdjustmentFromAssistant(paramINotificationListener, paramAdjustment);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void applyRestore(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(128, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().applyRestore(paramArrayOfbyte, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean areBubblesAllowed(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(26, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().areBubblesAllowed(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean areChannelsBypassingDnd() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(54, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().areChannelsBypassingDnd();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean areNotificationsEnabled(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(18, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().areNotificationsEnabled(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean areNotificationsEnabledForPackage(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(17, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().areNotificationsEnabledForPackage(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean canNotifyAsPackage(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(132, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().canNotifyAsPackage(paramString1, paramString2, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean canShowBadge(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(10, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().canShowBadge(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelAllNotifications(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().cancelAllNotifications(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelNotificationFromListener(INotificationListener paramINotificationListener, String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(67, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().cancelNotificationFromListener(paramINotificationListener, paramString1, paramString2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelNotificationWithTag(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().cancelNotificationWithTag(paramString1, paramString2, paramString3, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelNotificationsFromListener(INotificationListener paramINotificationListener, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(68, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().cancelNotificationsFromListener(paramINotificationListener, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelToast(String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().cancelToast(paramString, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearData(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().clearData(paramString, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearRequestedListenerHints(INotificationListener paramINotificationListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(78, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().clearRequestedListenerHints(paramINotificationListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void createConversationNotificationChannelForPackage(String paramString1, int paramInt, String paramString2, NotificationChannel paramNotificationChannel, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (paramNotificationChannel != null) {
        parcel1.writeInt(1);
        paramNotificationChannel.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().createConversationNotificationChannelForPackage(paramString1, paramInt, paramString2, paramNotificationChannel, paramString3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void createNotificationChannelGroups(String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().createNotificationChannelGroups(paramString, paramParceledListSlice);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void createNotificationChannels(String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().createNotificationChannels(paramString, paramParceledListSlice);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void createNotificationChannelsForPackage(String paramString, int paramInt, ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().createNotificationChannelsForPackage(paramString, paramInt, paramParceledListSlice);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteConversationNotificationChannels(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().deleteConversationNotificationChannels(paramString1, paramInt, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteNotificationChannel(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().deleteNotificationChannel(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteNotificationChannelGroup(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(49, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().deleteNotificationChannelGroup(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteNotificationHistoryItem(String paramString, int paramInt, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(58, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().deleteNotificationHistoryItem(paramString, paramInt, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disallowAssistantAdjustment(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().disallowAssistantAdjustment(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enqueueNotificationWithTag(String paramString1, String paramString2, String paramString3, int paramInt1, Notification paramNotification, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeString(paramString3);
            try {
              parcel1.writeInt(paramInt1);
              if (paramNotification != null) {
                parcel1.writeInt(1);
                paramNotification.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(paramInt2);
                if (!this.mRemote.transact(7, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
                  INotificationManager.Stub.getDefaultImpl().enqueueNotificationWithTag(paramString1, paramString2, paramString3, paramInt1, paramNotification, paramInt2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public void enqueueTextToast(String paramString, IBinder paramIBinder, CharSequence paramCharSequence, int paramInt1, int paramInt2, ITransientNotificationCallback paramITransientNotificationCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      try {
        parcel1.writeString(paramString);
        try {
          parcel1.writeStrongBinder(paramIBinder);
          if (paramCharSequence != null) {
            parcel1.writeInt(1);
            TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt1);
            try {
              IBinder iBinder;
              parcel1.writeInt(paramInt2);
              if (paramITransientNotificationCallback != null) {
                iBinder = paramITransientNotificationCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                if (!this.mRemote.transact(3, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
                  INotificationManager.Stub.getDefaultImpl().enqueueTextToast(paramString, paramIBinder, paramCharSequence, paramInt1, paramInt2, paramITransientNotificationCallback);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public void enqueueToast(String paramString, IBinder paramIBinder, ITransientNotification paramITransientNotification, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      if (paramITransientNotification != null) {
        iBinder = paramITransientNotification.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().enqueueToast(paramString, paramIBinder, paramITransientNotification, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finishToken(String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().finishToken(paramString, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StatusBarNotification[] getActiveNotifications(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(60, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getActiveNotifications(paramString); 
      parcel2.readException();
      return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getActiveNotificationsFromListener(INotificationListener paramINotificationListener, String[] paramArrayOfString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(76, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getActiveNotificationsFromListener(paramINotificationListener, paramArrayOfString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramINotificationListener = null;
      } 
      return (ParceledListSlice)paramINotificationListener;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StatusBarNotification[] getActiveNotificationsWithAttribution(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(61, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getActiveNotificationsWithAttribution(paramString1, paramString2); 
      parcel2.readException();
      return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getAllowedAssistantAdjustments(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getAllowedAssistantAdjustments(paramString); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getAllowedNotificationAssistant() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(107, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        componentName = INotificationManager.Stub.getDefaultImpl().getAllowedNotificationAssistant();
        return componentName;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        componentName = null;
      } 
      return componentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getAllowedNotificationAssistantForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(106, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        componentName = INotificationManager.Stub.getDefaultImpl().getAllowedNotificationAssistantForUser(paramInt);
        return componentName;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        componentName = null;
      } 
      return componentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getAppActiveNotifications(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(129, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getAppActiveNotifications(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getAppsBypassingDndCount(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(55, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramInt = INotificationManager.Stub.getDefaultImpl().getAppsBypassingDndCount(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public AutomaticZenRule getAutomaticZenRule(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(119, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getAutomaticZenRule(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        AutomaticZenRule automaticZenRule = (AutomaticZenRule)AutomaticZenRule.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (AutomaticZenRule)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public byte[] getBackupPayload(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(127, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getBackupPayload(paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getBlockedAppCount(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(53, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramInt = INotificationManager.Stub.getDefaultImpl().getBlockedAppCount(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getBlockedChannelCount(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(48, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramInt = INotificationManager.Stub.getDefaultImpl().getBlockedChannelCount(paramString, paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getBubblePreferenceForPackage(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramInt = INotificationManager.Stub.getDefaultImpl().getBubblePreferenceForPackage(paramString, paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      NotificationManager.Policy policy;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(110, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        policy = INotificationManager.Stub.getDefaultImpl().getConsolidatedNotificationPolicy();
        return policy;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        policy = (NotificationManager.Policy)NotificationManager.Policy.CREATOR.createFromParcel(parcel2);
      } else {
        policy = null;
      } 
      return policy;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationChannel getConversationNotificationChannel(String paramString1, int paramInt, String paramString2, String paramString3, boolean paramBoolean, String paramString4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeInt(paramInt);
          try {
            parcel1.writeString(paramString2);
            try {
              boolean bool;
              parcel1.writeString(paramString3);
              if (paramBoolean) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel1.writeInt(bool);
              try {
                parcel1.writeString(paramString4);
                try {
                  if (!this.mRemote.transact(39, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
                    NotificationChannel notificationChannel = INotificationManager.Stub.getDefaultImpl().getConversationNotificationChannel(paramString1, paramInt, paramString2, paramString3, paramBoolean, paramString4);
                    parcel2.recycle();
                    parcel1.recycle();
                    return notificationChannel;
                  } 
                  parcel2.readException();
                  if (parcel2.readInt() != 0) {
                    NotificationChannel notificationChannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel2);
                  } else {
                    paramString1 = null;
                  } 
                  parcel2.recycle();
                  parcel1.recycle();
                  return (NotificationChannel)paramString1;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public ParceledListSlice getConversations(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = INotificationManager.Stub.getDefaultImpl().getConversations(paramBoolean);
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getConversationsForPackage(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getConversationsForPackage(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getDeletedChannelCount(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramInt = INotificationManager.Stub.getDefaultImpl().getDeletedChannelCount(paramString, paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getEffectsSuppressor() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(94, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        componentName = INotificationManager.Stub.getDefaultImpl().getEffectsSuppressor();
        return componentName;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        componentName = null;
      } 
      return componentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getEnabledNotificationListenerPackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(104, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getEnabledNotificationListenerPackages(); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ComponentName> getEnabledNotificationListeners(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(105, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getEnabledNotificationListeners(paramInt); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ComponentName.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getHintsFromListener(INotificationListener paramINotificationListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(80, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getHintsFromListener(paramINotificationListener); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StatusBarNotification[] getHistoricalNotifications(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(62, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getHistoricalNotifications(paramString, paramInt, paramBoolean); 
      parcel2.readException();
      return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StatusBarNotification[] getHistoricalNotificationsWithAttribution(String paramString1, String paramString2, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(63, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getHistoricalNotificationsWithAttribution(paramString1, paramString2, paramInt, paramBoolean); 
      parcel2.readException();
      return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.INotificationManager";
  }
  
  public int getInterruptionFilterFromListener(INotificationListener paramINotificationListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(82, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getInterruptionFilterFromListener(paramINotificationListener); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationChannel getNotificationChannel(String paramString1, int paramInt, String paramString2, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannel(paramString1, paramInt, paramString2, paramString3); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        NotificationChannel notificationChannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (NotificationChannel)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationChannel getNotificationChannelForPackage(String paramString1, int paramInt, String paramString2, String paramString3, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(41, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelForPackage(paramString1, paramInt, paramString2, paramString3, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        NotificationChannel notificationChannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (NotificationChannel)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationChannelGroup getNotificationChannelGroup(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(50, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroup(paramString1, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (NotificationChannelGroup)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationChannelGroup getNotificationChannelGroupForPackage(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroupForPackage(paramString1, paramString2, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (NotificationChannelGroup)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getNotificationChannelGroups(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(51, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroups(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getNotificationChannelGroupsForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroupsForPackage(paramString, paramInt, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(88, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroupsFromPrivilegedListener(paramINotificationListener, paramString, paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramINotificationListener = null;
      } 
      return (ParceledListSlice)paramINotificationListener;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getNotificationChannels(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannels(paramString1, paramString2, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ParceledListSlice)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getNotificationChannelsBypassingDnd(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(56, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelsBypassingDnd(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getNotificationChannelsForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelsForPackage(paramString, paramInt, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(87, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationChannelsFromPrivilegedListener(paramINotificationListener, paramString, paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramINotificationListener = null;
      } 
      return (ParceledListSlice)paramINotificationListener;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getNotificationDelegate(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(131, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramString = INotificationManager.Stub.getDefaultImpl().getNotificationDelegate(paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationHistory getNotificationHistory(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(64, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationHistory(paramString1, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        NotificationHistory notificationHistory = (NotificationHistory)NotificationHistory.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (NotificationHistory)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationManager.Policy getNotificationPolicy(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(114, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getNotificationPolicy(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        NotificationManager.Policy policy = (NotificationManager.Policy)NotificationManager.Policy.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (NotificationManager.Policy)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getNumNotificationChannelsForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramInt = INotificationManager.Stub.getDefaultImpl().getNumNotificationChannelsForPackage(paramString, paramInt, paramBoolean);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPackageImportance(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getPackageImportance(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String paramString1, int paramInt, String paramString2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getPopulatedNotificationChannelGroupForPackage(paramString1, paramInt, paramString2, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (NotificationChannelGroup)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getPrivateNotificationsAllowed() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(134, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().getPrivateNotificationsAllowed();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getRuleInstanceCount(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(125, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getRuleInstanceCount(paramComponentName); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(77, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getSnoozedNotificationsFromListener(paramINotificationListener, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramINotificationListener = null;
      } 
      return (ParceledListSlice)paramINotificationListener;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getZenMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(108, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getZenMode(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ZenModeConfig getZenModeConfig() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ZenModeConfig zenModeConfig;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(109, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        zenModeConfig = INotificationManager.Stub.getDefaultImpl().getZenModeConfig();
        return zenModeConfig;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        zenModeConfig = (ZenModeConfig)ZenModeConfig.CREATOR.createFromParcel(parcel2);
      } else {
        zenModeConfig = null;
      } 
      return zenModeConfig;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(120, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
        return INotificationManager.Stub.getDefaultImpl().getZenRules(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ZenModeConfig.ZenRule.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasSentValidMsg(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(11, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().hasSentValidMsg(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasUserDemotedInvalidMsgApp(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(13, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().hasUserDemotedInvalidMsgApp(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isInInvalidMsgState(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(12, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isInInvalidMsgState(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isNotificationAssistantAccessGranted(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(99, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isNotificationAssistantAccessGranted(paramComponentName);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isNotificationListenerAccessGranted(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(97, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isNotificationListenerAccessGranted(paramComponentName);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isNotificationListenerAccessGrantedForUser(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(98, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isNotificationListenerAccessGrantedForUser(paramComponentName, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isNotificationPolicyAccessGranted(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(113, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isNotificationPolicyAccessGranted(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isNotificationPolicyAccessGrantedForPackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(116, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isNotificationPolicyAccessGrantedForPackage(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isPackagePaused(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(57, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isPackagePaused(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isSystemConditionProviderEnabled(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(96, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().isSystemConditionProviderEnabled(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean matchesCallFilter(Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(95, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().matchesCallFilter(paramBundle);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyConditions(String paramString, IConditionProvider paramIConditionProvider, Condition[] paramArrayOfCondition) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.INotificationManager");
      parcel.writeString(paramString);
      if (paramIConditionProvider != null) {
        iBinder = paramIConditionProvider.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeTypedArray((Parcelable[])paramArrayOfCondition, 0);
      if (!this.mRemote.transact(112, parcel, null, 1) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().notifyConditions(paramString, paramIConditionProvider, paramArrayOfCondition);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public boolean onlyHasDefaultChannel(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(52, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().onlyHasDefaultChannel(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long pullStats(long paramLong, int paramInt, boolean paramBoolean, List<ParcelFileDescriptor> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeLong(paramLong);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(135, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        paramLong = INotificationManager.Stub.getDefaultImpl().pullStats(paramLong, paramInt, paramBoolean, paramList);
        return paramLong;
      } 
      parcel2.readException();
      paramLong = parcel2.readLong();
      return paramLong;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerListener(INotificationListener paramINotificationListener, ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(65, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().registerListener(paramINotificationListener, paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeAutomaticZenRule(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(123, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().removeAutomaticZenRule(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeAutomaticZenRules(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(124, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().removeAutomaticZenRules(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestBindListener(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(71, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().requestBindListener(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestBindProvider(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(73, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().requestBindProvider(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestHintsFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(79, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().requestHintsFromListener(paramINotificationListener, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestInterruptionFilterFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(81, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().requestInterruptionFilterFromListener(paramINotificationListener, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestUnbindListener(INotificationListener paramINotificationListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(72, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().requestUnbindListener(paramINotificationListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestUnbindProvider(IConditionProvider paramIConditionProvider) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramIConditionProvider != null) {
        iBinder = paramIConditionProvider.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(74, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().requestUnbindProvider(paramIConditionProvider);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAutomaticZenRuleState(String paramString, Condition paramCondition) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (paramCondition != null) {
        parcel1.writeInt(1);
        paramCondition.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(126, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setAutomaticZenRuleState(paramString, paramCondition);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setBubblesAllowed(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setBubblesAllowed(paramString, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setHideSilentStatusIcons(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setHideSilentStatusIcons(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setInterruptionFilter(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(84, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setInterruptionFilter(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setInvalidMsgAppDemoted(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setInvalidMsgAppDemoted(paramString, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationAssistantAccessGranted(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(101, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationAssistantAccessGranted(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationAssistantAccessGrantedForUser(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(103, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationAssistantAccessGrantedForUser(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationDelegate(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(130, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationDelegate(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationListenerAccessGranted(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(100, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationListenerAccessGranted(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationListenerAccessGrantedForUser(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(102, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationListenerAccessGrantedForUser(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationPolicy(String paramString, NotificationManager.Policy paramPolicy) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (paramPolicy != null) {
        parcel1.writeInt(1);
        paramPolicy.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(115, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationPolicy(paramString, paramPolicy);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationPolicyAccessGranted(String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(117, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationPolicyAccessGranted(paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationPolicyAccessGrantedForUser(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(118, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationPolicyAccessGrantedForUser(paramString, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationsEnabledForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationsEnabledForPackage(paramString, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationsEnabledWithImportanceLockForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationsEnabledWithImportanceLockForPackage(paramString, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNotificationsShownFromListener(INotificationListener paramINotificationListener, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(75, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setNotificationsShownFromListener(paramINotificationListener, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setOnNotificationPostedTrimFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(83, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setOnNotificationPostedTrimFromListener(paramINotificationListener, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPrivateNotificationsAllowed(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(133, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setPrivateNotificationsAllowed(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setShowBadge(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setShowBadge(paramString, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setZenMode(int paramInt, Uri paramUri, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.INotificationManager");
      parcel.writeInt(paramInt);
      if (paramUri != null) {
        parcel.writeInt(1);
        paramUri.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (!this.mRemote.transact(111, parcel, null, 1) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().setZenMode(paramInt, paramUri, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public boolean shouldHideSilentStatusIcons(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(23, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().shouldHideSilentStatusIcons(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void silenceNotificationSound() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (!this.mRemote.transact(59, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().silenceNotificationSound();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void snoozeNotificationUntilContextFromListener(INotificationListener paramINotificationListener, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(69, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().snoozeNotificationUntilContextFromListener(paramINotificationListener, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void snoozeNotificationUntilFromListener(INotificationListener paramINotificationListener, String paramString, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(70, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().snoozeNotificationUntilFromListener(paramINotificationListener, paramString, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(66, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().unregisterListener(paramINotificationListener, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unsnoozeNotificationFromAssistant(INotificationListener paramINotificationListener, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(92, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().unsnoozeNotificationFromAssistant(paramINotificationListener, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unsnoozeNotificationFromSystemListener(INotificationListener paramINotificationListener, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(93, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().unsnoozeNotificationFromSystemListener(paramINotificationListener, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean updateAutomaticZenRule(String paramString, AutomaticZenRule paramAutomaticZenRule) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramAutomaticZenRule != null) {
        parcel1.writeInt(1);
        paramAutomaticZenRule.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(122, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        bool = INotificationManager.Stub.getDefaultImpl().updateAutomaticZenRule(paramString, paramAutomaticZenRule);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateNotificationChannelForPackage(String paramString, int paramInt, NotificationChannel paramNotificationChannel) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramNotificationChannel != null) {
        parcel1.writeInt(1);
        paramNotificationChannel.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().updateNotificationChannelForPackage(paramString, paramInt, paramNotificationChannel);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateNotificationChannelFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle, NotificationChannel paramNotificationChannel) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramNotificationChannel != null) {
        parcel1.writeInt(1);
        paramNotificationChannel.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(86, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().updateNotificationChannelFromPrivilegedListener(paramINotificationListener, paramString, paramUserHandle, paramNotificationChannel);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateNotificationChannelGroupForPackage(String paramString, int paramInt, NotificationChannelGroup paramNotificationChannelGroup) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramNotificationChannelGroup != null) {
        parcel1.writeInt(1);
        paramNotificationChannelGroup.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().updateNotificationChannelGroupForPackage(paramString, paramInt, paramNotificationChannelGroup);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle, NotificationChannelGroup paramNotificationChannelGroup) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.INotificationManager");
      if (paramINotificationListener != null) {
        iBinder = paramINotificationListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramNotificationChannelGroup != null) {
        parcel1.writeInt(1);
        paramNotificationChannelGroup.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(85, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
        INotificationManager.Stub.getDefaultImpl().updateNotificationChannelGroupFromPrivilegedListener(paramINotificationListener, paramString, paramUserHandle, paramNotificationChannelGroup);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/INotificationManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */