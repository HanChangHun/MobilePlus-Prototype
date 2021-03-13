package android.app.job;

import android.annotation.SystemApi;
import android.app.JobSchedulerImpl;
import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.DeviceIdleManager;
import android.os.IBinder;
import android.os.IDeviceIdleController;
import android.os.PowerWhitelistManager;

@SystemApi
public class JobSchedulerFrameworkInitializer {
  public static void registerServiceWrappers() {
    SystemServiceRegistry.registerStaticService("jobscheduler", JobScheduler.class, (SystemServiceRegistry.StaticServiceProducerWithBinder)_$$Lambda$JobSchedulerFrameworkInitializer$RHUxgww0pZFMmfQWKgaRAx0YFqA.INSTANCE);
    SystemServiceRegistry.registerContextAwareService("deviceidle", DeviceIdleManager.class, (SystemServiceRegistry.ContextAwareServiceProducerWithBinder)_$$Lambda$JobSchedulerFrameworkInitializer$PtYe8PQc1PVJQXRnpm3iSxcWTR0.INSTANCE);
    SystemServiceRegistry.registerContextAwareService("power_whitelist", PowerWhitelistManager.class, (SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder)_$$Lambda$FpGlzN9oJcl8o5soW_gU_DyTvXM.INSTANCE);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobSchedulerFrameworkInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */