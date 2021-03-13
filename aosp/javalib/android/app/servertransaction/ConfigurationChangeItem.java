package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class ConfigurationChangeItem extends ClientTransactionItem {
  public static final Parcelable.Creator<ConfigurationChangeItem> CREATOR = new Parcelable.Creator<ConfigurationChangeItem>() {
      public ConfigurationChangeItem createFromParcel(Parcel param1Parcel) {
        return new ConfigurationChangeItem(param1Parcel);
      }
      
      public ConfigurationChangeItem[] newArray(int param1Int) {
        return new ConfigurationChangeItem[param1Int];
      }
    };
  
  private Configuration mConfiguration;
  
  private ConfigurationChangeItem() {}
  
  private ConfigurationChangeItem(Parcel paramParcel) {
    this.mConfiguration = (Configuration)paramParcel.readTypedObject(Configuration.CREATOR);
  }
  
  public static ConfigurationChangeItem obtain(Configuration paramConfiguration) {
    ConfigurationChangeItem configurationChangeItem1 = ObjectPool.<ConfigurationChangeItem>obtain(ConfigurationChangeItem.class);
    ConfigurationChangeItem configurationChangeItem2 = configurationChangeItem1;
    if (configurationChangeItem1 == null)
      configurationChangeItem2 = new ConfigurationChangeItem(); 
    configurationChangeItem2.mConfiguration = paramConfiguration;
    return configurationChangeItem2;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return Objects.equals(this.mConfiguration, ((ConfigurationChangeItem)paramObject).mConfiguration);
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    paramClientTransactionHandler.handleConfigurationChanged(this.mConfiguration);
  }
  
  public int hashCode() {
    return this.mConfiguration.hashCode();
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {
    paramClientTransactionHandler.updatePendingConfiguration(this.mConfiguration);
  }
  
  public void recycle() {
    this.mConfiguration = null;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ConfigurationChangeItem{config=");
    stringBuilder.append(this.mConfiguration);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject((Parcelable)this.mConfiguration, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ConfigurationChangeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */