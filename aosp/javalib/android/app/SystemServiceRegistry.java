package android.app;

import android.accounts.AccountManager;
import android.accounts.IAccountManager;
import android.annotation.SystemApi;
import android.app.admin.DevicePolicyManager;
import android.app.admin.IDevicePolicyManager;
import android.app.blob.BlobStoreManagerFrameworkInitializer;
import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.IContentSuggestionsManager;
import android.app.job.JobSchedulerFrameworkInitializer;
import android.app.prediction.AppPredictionManager;
import android.app.role.RoleControllerManager;
import android.app.role.RoleManager;
import android.app.slice.SliceManager;
import android.app.timedetector.TimeDetector;
import android.app.timedetector.TimeDetectorImpl;
import android.app.timezone.RulesManager;
import android.app.timezonedetector.TimeZoneDetector;
import android.app.timezonedetector.TimeZoneDetectorImpl;
import android.app.trust.TrustManager;
import android.app.usage.IStorageStatsManager;
import android.app.usage.IUsageStatsManager;
import android.app.usage.NetworkStatsManager;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.companion.CompanionDeviceManager;
import android.companion.ICompanionDeviceManager;
import android.content.ClipboardManager;
import android.content.ContentCaptureOptions;
import android.content.Context;
import android.content.IRestrictionsManager;
import android.content.RestrictionsManager;
import android.content.integrity.AppIntegrityManager;
import android.content.integrity.IAppIntegrityManager;
import android.content.om.IOverlayManager;
import android.content.om.OverlayManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.CrossProfileApps;
import android.content.pm.DataLoaderManager;
import android.content.pm.ICrossProfileApps;
import android.content.pm.IDataLoaderManager;
import android.content.pm.IPackageManager;
import android.content.pm.IShortcutService;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.content.rollback.IRollbackManager;
import android.content.rollback.RollbackManager;
import android.debug.AdbManager;
import android.debug.IAdbManager;
import android.hardware.ConsumerIrManager;
import android.hardware.ISerialManager;
import android.hardware.SensorManager;
import android.hardware.SensorPrivacyManager;
import android.hardware.SerialManager;
import android.hardware.SystemSensorManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.IAuthService;
import android.hardware.camera2.CameraManager;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.DisplayManager;
import android.hardware.face.FaceManager;
import android.hardware.face.IFaceService;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.IFingerprintService;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.IHdmiControlService;
import android.hardware.input.InputManager;
import android.hardware.iris.IIrisService;
import android.hardware.iris.IrisManager;
import android.hardware.lights.LightsManager;
import android.hardware.location.ContextHubManager;
import android.hardware.radio.RadioManager;
import android.hardware.usb.IUsbManager;
import android.hardware.usb.UsbManager;
import android.location.CountryDetector;
import android.location.ICountryDetector;
import android.location.ILocationManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.IMidiManager;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.soundtrigger.SoundTriggerManager;
import android.media.tv.ITvInputManager;
import android.media.tv.TvInputManager;
import android.media.tv.tunerresourcemanager.ITunerResourceManager;
import android.media.tv.tunerresourcemanager.TunerResourceManager;
import android.net.ConnectivityDiagnosticsManager;
import android.net.ConnectivityManager;
import android.net.ConnectivityThread;
import android.net.EthernetManager;
import android.net.IConnectivityManager;
import android.net.IEthernetManager;
import android.net.IIpSecService;
import android.net.INetworkPolicyManager;
import android.net.ITestNetworkManager;
import android.net.IpSecManager;
import android.net.NetworkPolicyManager;
import android.net.NetworkScoreManager;
import android.net.NetworkWatchlistManager;
import android.net.TestNetworkManager;
import android.net.TetheringManager;
import android.net.VpnManager;
import android.net.lowpan.ILowpanManager;
import android.net.lowpan.LowpanManager;
import android.net.nsd.INsdManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiFrameworkInitializer;
import android.net.wifi.nl80211.WifiNl80211Manager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.BatteryStatsManager;
import android.os.BugreportManager;
import android.os.DropBoxManager;
import android.os.HardwarePropertiesManager;
import android.os.IBatteryPropertiesRegistrar;
import android.os.IBinder;
import android.os.IDumpstate;
import android.os.IHardwarePropertiesManager;
import android.os.IPowerManager;
import android.os.IRecoverySystem;
import android.os.ISystemUpdateManager;
import android.os.IThermalService;
import android.os.IUserManager;
import android.os.IncidentManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.RecoverySystem;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StatsFrameworkInitializer;
import android.os.SystemConfigManager;
import android.os.SystemUpdateManager;
import android.os.SystemVibrator;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.health.SystemHealthManager;
import android.os.image.DynamicSystemManager;
import android.os.image.IDynamicSystemService;
import android.os.incremental.IIncrementalService;
import android.os.incremental.IncrementalManager;
import android.os.storage.StorageManager;
import android.permission.PermissionControllerManager;
import android.permission.PermissionManager;
import android.print.IPrintManager;
import android.print.PrintManager;
import android.security.FileIntegrityManager;
import android.security.IFileIntegrityService;
import android.service.oemlock.IOemLockService;
import android.service.oemlock.OemLockManager;
import android.service.persistentdata.IPersistentDataBlockService;
import android.service.persistentdata.PersistentDataBlockManager;
import android.service.vr.IVrManager;
import android.telecom.TelecomManager;
import android.telephony.MmsManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.TelephonyRegistryManager;
import android.text.ClipboardManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.autofill.AutofillManager;
import android.view.autofill.IAutoFillManager;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.IContentCaptureManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassificationManager;
import android.view.textservice.TextServicesManager;
import com.android.internal.app.IAppOpsService;
import com.android.internal.app.IBatteryStats;
import com.android.internal.app.ISoundTriggerService;
import com.android.internal.appwidget.IAppWidgetService;
import com.android.internal.net.INetworkWatchlistManager;
import com.android.internal.os.IDropBoxManagerService;
import com.android.internal.policy.PhoneLayoutInflater;
import com.android.internal.util.Preconditions;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@SystemApi
public final class SystemServiceRegistry {
  private static final Map<String, String> SYSTEM_SERVICE_CLASS_NAMES;
  
  private static final Map<String, ServiceFetcher<?>> SYSTEM_SERVICE_FETCHERS;
  
  private static final Map<Class<?>, String> SYSTEM_SERVICE_NAMES;
  
  private static final String TAG = "SystemServiceRegistry";
  
  public static boolean sEnableServiceNotFoundWtf = false;
  
  private static volatile boolean sInitializing;
  
  private static int sServiceCacheSize;
  
  static {
    SYSTEM_SERVICE_NAMES = (Map<Class<?>, String>)new ArrayMap();
    SYSTEM_SERVICE_FETCHERS = (Map<String, ServiceFetcher<?>>)new ArrayMap();
    SYSTEM_SERVICE_CLASS_NAMES = (Map<String, String>)new ArrayMap();
    registerService("accessibility", AccessibilityManager.class, new CachedServiceFetcher<AccessibilityManager>() {
          public AccessibilityManager createService(ContextImpl param1ContextImpl) {
            return AccessibilityManager.getInstance(param1ContextImpl);
          }
        });
    registerService("captioning", CaptioningManager.class, new CachedServiceFetcher<CaptioningManager>() {
          public CaptioningManager createService(ContextImpl param1ContextImpl) {
            return new CaptioningManager(param1ContextImpl);
          }
        });
    registerService("account", AccountManager.class, new CachedServiceFetcher<AccountManager>() {
          public AccountManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new AccountManager(param1ContextImpl, IAccountManager.Stub.asInterface(ServiceManager.getServiceOrThrow("account")));
          }
        });
    registerService("activity", ActivityManager.class, new CachedServiceFetcher<ActivityManager>() {
          public ActivityManager createService(ContextImpl param1ContextImpl) {
            return new ActivityManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("activity_task", ActivityTaskManager.class, new CachedServiceFetcher<ActivityTaskManager>() {
          public ActivityTaskManager createService(ContextImpl param1ContextImpl) {
            return new ActivityTaskManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("uri_grants", UriGrantsManager.class, new CachedServiceFetcher<UriGrantsManager>() {
          public UriGrantsManager createService(ContextImpl param1ContextImpl) {
            return new UriGrantsManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("alarm", AlarmManager.class, new CachedServiceFetcher<AlarmManager>() {
          public AlarmManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new AlarmManager(IAlarmManager.Stub.asInterface(ServiceManager.getServiceOrThrow("alarm")), param1ContextImpl);
          }
        });
    registerService("audio", AudioManager.class, new CachedServiceFetcher<AudioManager>() {
          public AudioManager createService(ContextImpl param1ContextImpl) {
            return new AudioManager(param1ContextImpl);
          }
        });
    registerService("media_router", MediaRouter.class, new CachedServiceFetcher<MediaRouter>() {
          public MediaRouter createService(ContextImpl param1ContextImpl) {
            return new MediaRouter(param1ContextImpl);
          }
        });
    registerService("bluetooth", BluetoothManager.class, new CachedServiceFetcher<BluetoothManager>() {
          public BluetoothManager createService(ContextImpl param1ContextImpl) {
            return new BluetoothManager(param1ContextImpl);
          }
        });
    registerService("hdmi_control", HdmiControlManager.class, new StaticServiceFetcher<HdmiControlManager>() {
          public HdmiControlManager createService() throws ServiceManager.ServiceNotFoundException {
            return new HdmiControlManager(IHdmiControlService.Stub.asInterface(ServiceManager.getServiceOrThrow("hdmi_control")));
          }
        });
    registerService("textclassification", TextClassificationManager.class, new CachedServiceFetcher<TextClassificationManager>() {
          public TextClassificationManager createService(ContextImpl param1ContextImpl) {
            return new TextClassificationManager(param1ContextImpl);
          }
        });
    registerService("clipboard", ClipboardManager.class, new CachedServiceFetcher<ClipboardManager>() {
          public ClipboardManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new ClipboardManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
          }
        });
    SYSTEM_SERVICE_NAMES.put(ClipboardManager.class, "clipboard");
    registerService("connectivity", ConnectivityManager.class, new StaticApplicationContextServiceFetcher<ConnectivityManager>() {
          public ConnectivityManager createService(Context param1Context) throws ServiceManager.ServiceNotFoundException {
            return new ConnectivityManager(param1Context, IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")));
          }
        });
    registerService("netd", IBinder.class, new StaticServiceFetcher<IBinder>() {
          public IBinder createService() throws ServiceManager.ServiceNotFoundException {
            return ServiceManager.getServiceOrThrow("netd");
          }
        });
    registerService("tethering", TetheringManager.class, new CachedServiceFetcher<TetheringManager>() {
          public TetheringManager createService(ContextImpl param1ContextImpl) {
            return new TetheringManager(param1ContextImpl, (Supplier)_$$Lambda$SystemServiceRegistry$16$s6mZ42tuGUunhKa_5iwjLY5FGdM.INSTANCE);
          }
        });
    registerService("ipsec", IpSecManager.class, new CachedServiceFetcher<IpSecManager>() {
          public IpSecManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new IpSecManager(param1ContextImpl, IIpSecService.Stub.asInterface(ServiceManager.getService("ipsec")));
          }
        });
    registerService("vpn_management", VpnManager.class, new CachedServiceFetcher<VpnManager>() {
          public VpnManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new VpnManager(param1ContextImpl, IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity")));
          }
        });
    registerService("connectivity_diagnostics", ConnectivityDiagnosticsManager.class, new CachedServiceFetcher<ConnectivityDiagnosticsManager>() {
          public ConnectivityDiagnosticsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new ConnectivityDiagnosticsManager(param1ContextImpl, IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")));
          }
        });
    registerService("test_network", TestNetworkManager.class, new StaticApplicationContextServiceFetcher<TestNetworkManager>() {
          public TestNetworkManager createService(Context param1Context) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("connectivity");
            IConnectivityManager iConnectivityManager = IConnectivityManager.Stub.asInterface(iBinder);
            try {
              IBinder iBinder1 = iConnectivityManager.startOrGetTestNetworkService();
              return new TestNetworkManager(ITestNetworkManager.Stub.asInterface(iBinder1));
            } catch (RemoteException remoteException) {
              throw new ServiceManager.ServiceNotFoundException("test_network");
            } 
          }
        });
    registerService("country_detector", CountryDetector.class, new StaticServiceFetcher<CountryDetector>() {
          public CountryDetector createService() throws ServiceManager.ServiceNotFoundException {
            return new CountryDetector(ICountryDetector.Stub.asInterface(ServiceManager.getServiceOrThrow("country_detector")));
          }
        });
    registerService("device_policy", DevicePolicyManager.class, new CachedServiceFetcher<DevicePolicyManager>() {
          public DevicePolicyManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new DevicePolicyManager(param1ContextImpl, IDevicePolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow("device_policy")));
          }
        });
    registerService("download", DownloadManager.class, new CachedServiceFetcher<DownloadManager>() {
          public DownloadManager createService(ContextImpl param1ContextImpl) {
            return new DownloadManager(param1ContextImpl);
          }
        });
    registerService("batterymanager", BatteryManager.class, new CachedServiceFetcher<BatteryManager>() {
          public BatteryManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBatteryStats iBatteryStats = IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats"));
            return new BatteryManager(param1ContextImpl, iBatteryStats, IBatteryPropertiesRegistrar.Stub.asInterface(ServiceManager.getServiceOrThrow("batteryproperties")));
          }
        });
    registerService("nfc", NfcManager.class, new CachedServiceFetcher<NfcManager>() {
          public NfcManager createService(ContextImpl param1ContextImpl) {
            return new NfcManager(param1ContextImpl);
          }
        });
    registerService("dropbox", DropBoxManager.class, new CachedServiceFetcher<DropBoxManager>() {
          public DropBoxManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new DropBoxManager(param1ContextImpl, IDropBoxManagerService.Stub.asInterface(ServiceManager.getServiceOrThrow("dropbox")));
          }
        });
    registerService("input", InputManager.class, new StaticServiceFetcher<InputManager>() {
          public InputManager createService() {
            return InputManager.getInstance();
          }
        });
    registerService("display", DisplayManager.class, new CachedServiceFetcher<DisplayManager>() {
          public DisplayManager createService(ContextImpl param1ContextImpl) {
            return new DisplayManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("color_display", ColorDisplayManager.class, new CachedServiceFetcher<ColorDisplayManager>() {
          public ColorDisplayManager createService(ContextImpl param1ContextImpl) {
            return new ColorDisplayManager();
          }
        });
    registerService("input_method", InputMethodManager.class, new ServiceFetcher<InputMethodManager>() {
          public InputMethodManager getService(ContextImpl param1ContextImpl) {
            return InputMethodManager.forContext(param1ContextImpl.getOuterContext());
          }
        });
    registerService("textservices", TextServicesManager.class, new CachedServiceFetcher<TextServicesManager>() {
          public TextServicesManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return TextServicesManager.createInstance(param1ContextImpl);
          }
        });
    registerService("keyguard", KeyguardManager.class, new CachedServiceFetcher<KeyguardManager>() {
          public KeyguardManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new KeyguardManager(param1ContextImpl);
          }
        });
    registerService("layout_inflater", LayoutInflater.class, new CachedServiceFetcher<LayoutInflater>() {
          public LayoutInflater createService(ContextImpl param1ContextImpl) {
            return (LayoutInflater)new PhoneLayoutInflater(param1ContextImpl.getOuterContext());
          }
        });
    registerService("location", LocationManager.class, new CachedServiceFetcher<LocationManager>() {
          public LocationManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new LocationManager(param1ContextImpl, ILocationManager.Stub.asInterface(ServiceManager.getServiceOrThrow("location")));
          }
        });
    registerService("netpolicy", NetworkPolicyManager.class, new CachedServiceFetcher<NetworkPolicyManager>() {
          public NetworkPolicyManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new NetworkPolicyManager(param1ContextImpl, INetworkPolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow("netpolicy")));
          }
        });
    registerService("notification", NotificationManager.class, new CachedServiceFetcher<NotificationManager>() {
          public NotificationManager createService(ContextImpl param1ContextImpl) {
            Context context = param1ContextImpl.getOuterContext();
            return new NotificationManager((Context)new ContextThemeWrapper(context, Resources.selectSystemTheme(0, (context.getApplicationInfo()).targetSdkVersion, 16973835, 16973935, 16974126, 16974130)), param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("servicediscovery", NsdManager.class, new CachedServiceFetcher<NsdManager>() {
          public NsdManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            INsdManager iNsdManager = INsdManager.Stub.asInterface(ServiceManager.getServiceOrThrow("servicediscovery"));
            return new NsdManager(param1ContextImpl.getOuterContext(), iNsdManager);
          }
        });
    registerService("power", PowerManager.class, new CachedServiceFetcher<PowerManager>() {
          public PowerManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IPowerManager iPowerManager = IPowerManager.Stub.asInterface(ServiceManager.getServiceOrThrow("power"));
            IThermalService iThermalService = IThermalService.Stub.asInterface(ServiceManager.getServiceOrThrow("thermalservice"));
            return new PowerManager(param1ContextImpl.getOuterContext(), iPowerManager, iThermalService, param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("recovery", RecoverySystem.class, new CachedServiceFetcher<RecoverySystem>() {
          public RecoverySystem createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new RecoverySystem(IRecoverySystem.Stub.asInterface(ServiceManager.getServiceOrThrow("recovery")));
          }
        });
    registerService("search", SearchManager.class, new CachedServiceFetcher<SearchManager>() {
          public SearchManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new SearchManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("sensor", SensorManager.class, new CachedServiceFetcher<SensorManager>() {
          public SensorManager createService(ContextImpl param1ContextImpl) {
            return (SensorManager)new SystemSensorManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler().getLooper());
          }
        });
    registerService("sensor_privacy", SensorPrivacyManager.class, new CachedServiceFetcher<SensorPrivacyManager>() {
          public SensorPrivacyManager createService(ContextImpl param1ContextImpl) {
            return SensorPrivacyManager.getInstance(param1ContextImpl);
          }
        });
    registerService("statusbar", StatusBarManager.class, new CachedServiceFetcher<StatusBarManager>() {
          public StatusBarManager createService(ContextImpl param1ContextImpl) {
            return new StatusBarManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("storage", StorageManager.class, new CachedServiceFetcher<StorageManager>() {
          public StorageManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new StorageManager(param1ContextImpl, param1ContextImpl.mMainThread.getHandler().getLooper());
          }
        });
    registerService("storagestats", StorageStatsManager.class, new CachedServiceFetcher<StorageStatsManager>() {
          public StorageStatsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new StorageStatsManager(param1ContextImpl, IStorageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("storagestats")));
          }
        });
    registerService("system_update", SystemUpdateManager.class, new CachedServiceFetcher<SystemUpdateManager>() {
          public SystemUpdateManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new SystemUpdateManager(ISystemUpdateManager.Stub.asInterface(ServiceManager.getServiceOrThrow("system_update")));
          }
        });
    registerService("system_config", SystemConfigManager.class, new CachedServiceFetcher<SystemConfigManager>() {
          public SystemConfigManager createService(ContextImpl param1ContextImpl) {
            return new SystemConfigManager();
          }
        });
    registerService("telephony_registry", TelephonyRegistryManager.class, new CachedServiceFetcher<TelephonyRegistryManager>() {
          public TelephonyRegistryManager createService(ContextImpl param1ContextImpl) {
            return new TelephonyRegistryManager(param1ContextImpl);
          }
        });
    registerService("telecom", TelecomManager.class, new CachedServiceFetcher<TelecomManager>() {
          public TelecomManager createService(ContextImpl param1ContextImpl) {
            return new TelecomManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("mms", MmsManager.class, new CachedServiceFetcher<MmsManager>() {
          public MmsManager createService(ContextImpl param1ContextImpl) {
            return new MmsManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("uimode", UiModeManager.class, new CachedServiceFetcher<UiModeManager>() {
          public UiModeManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new UiModeManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("usb", UsbManager.class, new CachedServiceFetcher<UsbManager>() {
          public UsbManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new UsbManager(param1ContextImpl, IUsbManager.Stub.asInterface(ServiceManager.getServiceOrThrow("usb")));
          }
        });
    registerService("adb", AdbManager.class, new CachedServiceFetcher<AdbManager>() {
          public AdbManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new AdbManager(param1ContextImpl, IAdbManager.Stub.asInterface(ServiceManager.getServiceOrThrow("adb")));
          }
        });
    registerService("serial", SerialManager.class, new CachedServiceFetcher<SerialManager>() {
          public SerialManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new SerialManager(param1ContextImpl, ISerialManager.Stub.asInterface(ServiceManager.getServiceOrThrow("serial")));
          }
        });
    registerService("vibrator", Vibrator.class, new CachedServiceFetcher<Vibrator>() {
          public Vibrator createService(ContextImpl param1ContextImpl) {
            return (Vibrator)new SystemVibrator(param1ContextImpl);
          }
        });
    registerService("wallpaper", WallpaperManager.class, new CachedServiceFetcher<WallpaperManager>() {
          public WallpaperManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getService("wallpaper");
            if (iBinder == null) {
              ApplicationInfo applicationInfo = param1ContextImpl.getApplicationInfo();
              if (applicationInfo.targetSdkVersion < 28 || !applicationInfo.isInstantApp()) {
                if (!Resources.getSystem().getBoolean(17891454))
                  return DisabledWallpaperManager.getInstance(); 
                Log.e("SystemServiceRegistry", "No wallpaper service");
                return new WallpaperManager(IWallpaperManager.Stub.asInterface(iBinder), param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
              } 
              throw new ServiceManager.ServiceNotFoundException("wallpaper");
            } 
            return new WallpaperManager(IWallpaperManager.Stub.asInterface(iBinder), param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("lowpan", LowpanManager.class, new CachedServiceFetcher<LowpanManager>() {
          public LowpanManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            ILowpanManager iLowpanManager = ILowpanManager.Stub.asInterface(ServiceManager.getServiceOrThrow("lowpan"));
            return new LowpanManager(param1ContextImpl.getOuterContext(), iLowpanManager, ConnectivityThread.getInstanceLooper());
          }
        });
    registerService("ethernet", EthernetManager.class, new CachedServiceFetcher<EthernetManager>() {
          public EthernetManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IEthernetManager iEthernetManager = IEthernetManager.Stub.asInterface(ServiceManager.getServiceOrThrow("ethernet"));
            return new EthernetManager(param1ContextImpl.getOuterContext(), iEthernetManager);
          }
        });
    registerService("wifinl80211", WifiNl80211Manager.class, new CachedServiceFetcher<WifiNl80211Manager>() {
          public WifiNl80211Manager createService(ContextImpl param1ContextImpl) {
            return new WifiNl80211Manager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("window", WindowManager.class, new CachedServiceFetcher<WindowManager>() {
          public WindowManager createService(ContextImpl param1ContextImpl) {
            return (WindowManager)new WindowManagerImpl(param1ContextImpl);
          }
        });
    registerService("user", UserManager.class, new CachedServiceFetcher<UserManager>() {
          public UserManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new UserManager(param1ContextImpl, IUserManager.Stub.asInterface(ServiceManager.getServiceOrThrow("user")));
          }
        });
    registerService("appops", AppOpsManager.class, new CachedServiceFetcher<AppOpsManager>() {
          public AppOpsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new AppOpsManager(param1ContextImpl, IAppOpsService.Stub.asInterface(ServiceManager.getServiceOrThrow("appops")));
          }
        });
    registerService("camera", CameraManager.class, new CachedServiceFetcher<CameraManager>() {
          public CameraManager createService(ContextImpl param1ContextImpl) {
            return new CameraManager(param1ContextImpl);
          }
        });
    registerService("launcherapps", LauncherApps.class, new CachedServiceFetcher<LauncherApps>() {
          public LauncherApps createService(ContextImpl param1ContextImpl) {
            return new LauncherApps(param1ContextImpl);
          }
        });
    registerService("restrictions", RestrictionsManager.class, new CachedServiceFetcher<RestrictionsManager>() {
          public RestrictionsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new RestrictionsManager(param1ContextImpl, IRestrictionsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("restrictions")));
          }
        });
    registerService("print", PrintManager.class, new CachedServiceFetcher<PrintManager>() {
          public PrintManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IPrintManager iPrintManager = null;
            if (param1ContextImpl.getPackageManager().hasSystemFeature("android.software.print"))
              iPrintManager = IPrintManager.Stub.asInterface(ServiceManager.getServiceOrThrow("print")); 
            int i = param1ContextImpl.getUserId();
            int j = UserHandle.getAppId((param1ContextImpl.getApplicationInfo()).uid);
            return new PrintManager(param1ContextImpl.getOuterContext(), iPrintManager, i, j);
          }
        });
    registerService("companiondevice", CompanionDeviceManager.class, new CachedServiceFetcher<CompanionDeviceManager>() {
          public CompanionDeviceManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            ICompanionDeviceManager iCompanionDeviceManager = null;
            if (param1ContextImpl.getPackageManager().hasSystemFeature("android.software.companion_device_setup"))
              iCompanionDeviceManager = ICompanionDeviceManager.Stub.asInterface(ServiceManager.getServiceOrThrow("companiondevice")); 
            return new CompanionDeviceManager(iCompanionDeviceManager, param1ContextImpl.getOuterContext());
          }
        });
    registerService("consumer_ir", ConsumerIrManager.class, new CachedServiceFetcher<ConsumerIrManager>() {
          public ConsumerIrManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new ConsumerIrManager(param1ContextImpl);
          }
        });
    registerService("media_session", MediaSessionManager.class, new CachedServiceFetcher<MediaSessionManager>() {
          public MediaSessionManager createService(ContextImpl param1ContextImpl) {
            return new MediaSessionManager(param1ContextImpl);
          }
        });
    registerService("trust", TrustManager.class, new StaticServiceFetcher<TrustManager>() {
          public TrustManager createService() throws ServiceManager.ServiceNotFoundException {
            return new TrustManager(ServiceManager.getServiceOrThrow("trust"));
          }
        });
    registerService("fingerprint", FingerprintManager.class, new CachedServiceFetcher<FingerprintManager>() {
          public FingerprintManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder;
            if ((param1ContextImpl.getApplicationInfo()).targetSdkVersion >= 26) {
              iBinder = ServiceManager.getServiceOrThrow("fingerprint");
            } else {
              iBinder = ServiceManager.getService("fingerprint");
            } 
            IFingerprintService iFingerprintService = IFingerprintService.Stub.asInterface(iBinder);
            return new FingerprintManager(param1ContextImpl.getOuterContext(), iFingerprintService);
          }
        });
    registerService("face", FaceManager.class, new CachedServiceFetcher<FaceManager>() {
          public FaceManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder;
            if ((param1ContextImpl.getApplicationInfo()).targetSdkVersion >= 26) {
              iBinder = ServiceManager.getServiceOrThrow("face");
            } else {
              iBinder = ServiceManager.getService("face");
            } 
            IFaceService iFaceService = IFaceService.Stub.asInterface(iBinder);
            return new FaceManager(param1ContextImpl.getOuterContext(), iFaceService);
          }
        });
    registerService("iris", IrisManager.class, new CachedServiceFetcher<IrisManager>() {
          public IrisManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IIrisService iIrisService = IIrisService.Stub.asInterface(ServiceManager.getServiceOrThrow("iris"));
            return new IrisManager(param1ContextImpl.getOuterContext(), iIrisService);
          }
        });
    registerService("biometric", BiometricManager.class, new CachedServiceFetcher<BiometricManager>() {
          public BiometricManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("auth");
            IAuthService iAuthService = IAuthService.Stub.asInterface(iBinder);
            return new BiometricManager(param1ContextImpl.getOuterContext(), iAuthService);
          }
        });
    registerService("tv_input", TvInputManager.class, new CachedServiceFetcher<TvInputManager>() {
          public TvInputManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new TvInputManager(ITvInputManager.Stub.asInterface(ServiceManager.getServiceOrThrow("tv_input")), param1ContextImpl.getUserId());
          }
        });
    registerService("tv_tuner_resource_mgr", TunerResourceManager.class, new CachedServiceFetcher<TunerResourceManager>() {
          public TunerResourceManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new TunerResourceManager(ITunerResourceManager.Stub.asInterface(ServiceManager.getServiceOrThrow("tv_tuner_resource_mgr")), param1ContextImpl.getUserId());
          }
        });
    registerService("network_score", NetworkScoreManager.class, new CachedServiceFetcher<NetworkScoreManager>() {
          public NetworkScoreManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new NetworkScoreManager(param1ContextImpl);
          }
        });
    registerService("usagestats", UsageStatsManager.class, new CachedServiceFetcher<UsageStatsManager>() {
          public UsageStatsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IUsageStatsManager iUsageStatsManager = IUsageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("usagestats"));
            return new UsageStatsManager(param1ContextImpl.getOuterContext(), iUsageStatsManager);
          }
        });
    registerService("netstats", NetworkStatsManager.class, new CachedServiceFetcher<NetworkStatsManager>() {
          public NetworkStatsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new NetworkStatsManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("persistent_data_block", PersistentDataBlockManager.class, new StaticServiceFetcher<PersistentDataBlockManager>() {
          public PersistentDataBlockManager createService() throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("persistent_data_block");
            IPersistentDataBlockService iPersistentDataBlockService = IPersistentDataBlockService.Stub.asInterface(iBinder);
            return (iPersistentDataBlockService != null) ? new PersistentDataBlockManager(iPersistentDataBlockService) : null;
          }
        });
    registerService("oem_lock", OemLockManager.class, new StaticServiceFetcher<OemLockManager>() {
          public OemLockManager createService() throws ServiceManager.ServiceNotFoundException {
            IOemLockService iOemLockService = IOemLockService.Stub.asInterface(ServiceManager.getServiceOrThrow("oem_lock"));
            return (iOemLockService != null) ? new OemLockManager(iOemLockService) : null;
          }
        });
    registerService("media_projection", MediaProjectionManager.class, new CachedServiceFetcher<MediaProjectionManager>() {
          public MediaProjectionManager createService(ContextImpl param1ContextImpl) {
            return new MediaProjectionManager(param1ContextImpl);
          }
        });
    registerService("appwidget", AppWidgetManager.class, new CachedServiceFetcher<AppWidgetManager>() {
          public AppWidgetManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new AppWidgetManager(param1ContextImpl, IAppWidgetService.Stub.asInterface(ServiceManager.getServiceOrThrow("appwidget")));
          }
        });
    registerService("midi", MidiManager.class, new CachedServiceFetcher<MidiManager>() {
          public MidiManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new MidiManager(IMidiManager.Stub.asInterface(ServiceManager.getServiceOrThrow("midi")));
          }
        });
    registerService("broadcastradio", RadioManager.class, new CachedServiceFetcher<RadioManager>() {
          public RadioManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new RadioManager(param1ContextImpl);
          }
        });
    registerService("hardware_properties", HardwarePropertiesManager.class, new CachedServiceFetcher<HardwarePropertiesManager>() {
          public HardwarePropertiesManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("hardware_properties");
            return new HardwarePropertiesManager(param1ContextImpl, IHardwarePropertiesManager.Stub.asInterface(iBinder));
          }
        });
    registerService("soundtrigger", SoundTriggerManager.class, new CachedServiceFetcher<SoundTriggerManager>() {
          public SoundTriggerManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new SoundTriggerManager(param1ContextImpl, ISoundTriggerService.Stub.asInterface(ServiceManager.getServiceOrThrow("soundtrigger")));
          }
        });
    registerService("shortcut", ShortcutManager.class, new CachedServiceFetcher<ShortcutManager>() {
          public ShortcutManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new ShortcutManager(param1ContextImpl, IShortcutService.Stub.asInterface(ServiceManager.getServiceOrThrow("shortcut")));
          }
        });
    registerService("overlay", OverlayManager.class, new CachedServiceFetcher<OverlayManager>() {
          public OverlayManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new OverlayManager(param1ContextImpl, IOverlayManager.Stub.asInterface(ServiceManager.getServiceOrThrow("overlay")));
          }
        });
    registerService("network_watchlist", NetworkWatchlistManager.class, new CachedServiceFetcher<NetworkWatchlistManager>() {
          public NetworkWatchlistManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new NetworkWatchlistManager(param1ContextImpl, INetworkWatchlistManager.Stub.asInterface(ServiceManager.getServiceOrThrow("network_watchlist")));
          }
        });
    registerService("systemhealth", SystemHealthManager.class, new CachedServiceFetcher<SystemHealthManager>() {
          public SystemHealthManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new SystemHealthManager(IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")));
          }
        });
    registerService("contexthub", ContextHubManager.class, new CachedServiceFetcher<ContextHubManager>() {
          public ContextHubManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new ContextHubManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler().getLooper());
          }
        });
    registerService("incident", IncidentManager.class, new CachedServiceFetcher<IncidentManager>() {
          public IncidentManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new IncidentManager(param1ContextImpl);
          }
        });
    registerService("bugreport", BugreportManager.class, new CachedServiceFetcher<BugreportManager>() {
          public BugreportManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("bugreport");
            return new BugreportManager(param1ContextImpl.getOuterContext(), IDumpstate.Stub.asInterface(iBinder));
          }
        });
    registerService("autofill", AutofillManager.class, new CachedServiceFetcher<AutofillManager>() {
          public AutofillManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IAutoFillManager iAutoFillManager = IAutoFillManager.Stub.asInterface(ServiceManager.getService("autofill"));
            return new AutofillManager(param1ContextImpl.getOuterContext(), iAutoFillManager);
          }
        });
    registerService("content_capture", ContentCaptureManager.class, new CachedServiceFetcher<ContentCaptureManager>() {
          public ContentCaptureManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            Context context = param1ContextImpl.getOuterContext();
            ContentCaptureOptions contentCaptureOptions = context.getContentCaptureOptions();
            if (contentCaptureOptions != null && (contentCaptureOptions.lite || contentCaptureOptions.isWhitelisted(context))) {
              IContentCaptureManager iContentCaptureManager = IContentCaptureManager.Stub.asInterface(ServiceManager.getService("content_capture"));
              if (iContentCaptureManager != null)
                return new ContentCaptureManager(context, iContentCaptureManager, contentCaptureOptions); 
            } 
            return null;
          }
        });
    registerService("app_prediction", AppPredictionManager.class, new CachedServiceFetcher<AppPredictionManager>() {
          public AppPredictionManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            AppPredictionManager appPredictionManager;
            if (ServiceManager.getService("app_prediction") == null) {
              param1ContextImpl = null;
            } else {
              appPredictionManager = new AppPredictionManager(param1ContextImpl);
            } 
            return appPredictionManager;
          }
        });
    registerService("content_suggestions", ContentSuggestionsManager.class, new CachedServiceFetcher<ContentSuggestionsManager>() {
          public ContentSuggestionsManager createService(ContextImpl param1ContextImpl) {
            IBinder iBinder = ServiceManager.getService("content_suggestions");
            IContentSuggestionsManager iContentSuggestionsManager = IContentSuggestionsManager.Stub.asInterface(iBinder);
            return new ContentSuggestionsManager(param1ContextImpl.getUserId(), iContentSuggestionsManager);
          }
        });
    registerService("vrmanager", VrManager.class, new CachedServiceFetcher<VrManager>() {
          public VrManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new VrManager(IVrManager.Stub.asInterface(ServiceManager.getServiceOrThrow("vrmanager")));
          }
        });
    registerService("timezone", RulesManager.class, new CachedServiceFetcher<RulesManager>() {
          public RulesManager createService(ContextImpl param1ContextImpl) {
            return new RulesManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("crossprofileapps", CrossProfileApps.class, new CachedServiceFetcher<CrossProfileApps>() {
          public CrossProfileApps createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("crossprofileapps");
            return new CrossProfileApps(param1ContextImpl.getOuterContext(), ICrossProfileApps.Stub.asInterface(iBinder));
          }
        });
    registerService("slice", SliceManager.class, new CachedServiceFetcher<SliceManager>() {
          public SliceManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new SliceManager(param1ContextImpl.getOuterContext(), param1ContextImpl.mMainThread.getHandler());
          }
        });
    registerService("time_detector", TimeDetector.class, new CachedServiceFetcher<TimeDetector>() {
          public TimeDetector createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return (TimeDetector)new TimeDetectorImpl();
          }
        });
    registerService("time_zone_detector", TimeZoneDetector.class, new CachedServiceFetcher<TimeZoneDetector>() {
          public TimeZoneDetector createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return (TimeZoneDetector)new TimeZoneDetectorImpl();
          }
        });
    registerService("permission", PermissionManager.class, new CachedServiceFetcher<PermissionManager>() {
          public PermissionManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IPackageManager iPackageManager = AppGlobals.getPackageManager();
            return new PermissionManager(param1ContextImpl.getOuterContext(), iPackageManager);
          }
        });
    registerService("permission_controller", PermissionControllerManager.class, new CachedServiceFetcher<PermissionControllerManager>() {
          public PermissionControllerManager createService(ContextImpl param1ContextImpl) {
            return new PermissionControllerManager(param1ContextImpl.getOuterContext(), param1ContextImpl.getMainThreadHandler());
          }
        });
    registerService("role", RoleManager.class, new CachedServiceFetcher<RoleManager>() {
          public RoleManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new RoleManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("role_controller", RoleControllerManager.class, new CachedServiceFetcher<RoleControllerManager>() {
          public RoleControllerManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new RoleControllerManager(param1ContextImpl.getOuterContext());
          }
        });
    registerService("rollback", RollbackManager.class, new CachedServiceFetcher<RollbackManager>() {
          public RollbackManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("rollback");
            return new RollbackManager(param1ContextImpl.getOuterContext(), IRollbackManager.Stub.asInterface(iBinder));
          }
        });
    registerService("dynamic_system", DynamicSystemManager.class, new CachedServiceFetcher<DynamicSystemManager>() {
          public DynamicSystemManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new DynamicSystemManager(IDynamicSystemService.Stub.asInterface(ServiceManager.getServiceOrThrow("dynamic_system")));
          }
        });
    registerService("batterystats", BatteryStatsManager.class, new CachedServiceFetcher<BatteryStatsManager>() {
          public BatteryStatsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new BatteryStatsManager(IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")));
          }
        });
    registerService("dataloader_manager", DataLoaderManager.class, new CachedServiceFetcher<DataLoaderManager>() {
          public DataLoaderManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new DataLoaderManager(IDataLoaderManager.Stub.asInterface(ServiceManager.getServiceOrThrow("dataloader_manager")));
          }
        });
    registerService("lights", LightsManager.class, new CachedServiceFetcher<LightsManager>() {
          public LightsManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new LightsManager(param1ContextImpl);
          }
        });
    registerService("incremental", IncrementalManager.class, new CachedServiceFetcher<IncrementalManager>() {
          public IncrementalManager createService(ContextImpl param1ContextImpl) {
            IBinder iBinder = ServiceManager.getService("incremental");
            return (iBinder == null) ? null : new IncrementalManager(IIncrementalService.Stub.asInterface(iBinder));
          }
        });
    registerService("file_integrity", FileIntegrityManager.class, new CachedServiceFetcher<FileIntegrityManager>() {
          public FileIntegrityManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            IBinder iBinder = ServiceManager.getServiceOrThrow("file_integrity");
            return new FileIntegrityManager(param1ContextImpl.getOuterContext(), IFileIntegrityService.Stub.asInterface(iBinder));
          }
        });
    registerService("app_integrity", AppIntegrityManager.class, new CachedServiceFetcher<AppIntegrityManager>() {
          public AppIntegrityManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new AppIntegrityManager(IAppIntegrityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("app_integrity")));
          }
        });
    registerService("dream", DreamManager.class, new CachedServiceFetcher<DreamManager>() {
          public DreamManager createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return new DreamManager(param1ContextImpl);
          }
        });
    sInitializing = true;
    try {
      JobSchedulerFrameworkInitializer.registerServiceWrappers();
      BlobStoreManagerFrameworkInitializer.initialize();
      TelephonyFrameworkInitializer.registerServiceWrappers();
      WifiFrameworkInitializer.registerServiceWrappers();
      StatsFrameworkInitializer.registerServiceWrappers();
      return;
    } finally {
      sInitializing = false;
    } 
  }
  
  public static Object[] createServiceCache() {
    return new Object[sServiceCacheSize];
  }
  
  private static void ensureInitializing(String paramString) {
    boolean bool = sInitializing;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Internal error: ");
    stringBuilder.append(paramString);
    stringBuilder.append(" can only be called during class initialization.");
    Preconditions.checkState(bool, stringBuilder.toString());
  }
  
  public static Object getSystemService(ContextImpl paramContextImpl, String paramString) {
    if (paramString == null)
      return null; 
    ServiceFetcher<StringBuilder> serviceFetcher = (ServiceFetcher)SYSTEM_SERVICE_FETCHERS.get(paramString);
    if (serviceFetcher == null) {
      if (sEnableServiceNotFoundWtf) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown manager requested: ");
        stringBuilder.append(paramString);
        Slog.wtf("SystemServiceRegistry", stringBuilder.toString());
      } 
      return null;
    } 
    StringBuilder stringBuilder = serviceFetcher.getService((ContextImpl)stringBuilder);
    if (sEnableServiceNotFoundWtf && stringBuilder == null) {
      byte b = -1;
      int i = paramString.hashCode();
      if (i != -769002131) {
        if (i != 974854528) {
          if (i == 1085372378 && paramString.equals("incremental"))
            b = 2; 
        } else if (paramString.equals("content_capture")) {
          b = 0;
        } 
      } else if (paramString.equals("app_prediction")) {
        b = 1;
      } 
      if (b != 0 && b != 1 && b != 2) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Manager wrapper not available: ");
        stringBuilder.append(paramString);
        Slog.wtf("SystemServiceRegistry", stringBuilder.toString());
        return null;
      } 
      return null;
    } 
    return stringBuilder;
  }
  
  public static String getSystemServiceClassName(String paramString) {
    return SYSTEM_SERVICE_CLASS_NAMES.get(paramString);
  }
  
  public static String getSystemServiceName(Class<?> paramClass) {
    if (paramClass == null)
      return null; 
    String str = SYSTEM_SERVICE_NAMES.get(paramClass);
    if (sEnableServiceNotFoundWtf && str == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown manager requested: ");
      stringBuilder.append(paramClass.getCanonicalName());
      Slog.wtf("SystemServiceRegistry", stringBuilder.toString());
    } 
    return str;
  }
  
  public static void onServiceNotFound(ServiceManager.ServiceNotFoundException paramServiceNotFoundException) {
    if (Process.myUid() < 10000) {
      Log.wtf("SystemServiceRegistry", paramServiceNotFoundException.getMessage(), (Throwable)paramServiceNotFoundException);
    } else {
      Log.w("SystemServiceRegistry", paramServiceNotFoundException.getMessage());
    } 
  }
  
  @SystemApi
  public static <TServiceClass> void registerContextAwareService(final String serviceName, Class<TServiceClass> paramClass, final ContextAwareServiceProducerWithBinder<TServiceClass> serviceProducer) {
    ensureInitializing("registerContextAwareService");
    Preconditions.checkStringNotEmpty(serviceName);
    Objects.requireNonNull(paramClass);
    Objects.requireNonNull(serviceProducer);
    registerService(serviceName, paramClass, new CachedServiceFetcher<TServiceClass>() {
          public TServiceClass createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException {
            return serviceProducer.createService(param1ContextImpl.getOuterContext(), ServiceManager.getServiceOrThrow(serviceName));
          }
        });
  }
  
  @SystemApi
  public static <TServiceClass> void registerContextAwareService(String paramString, Class<TServiceClass> paramClass, final ContextAwareServiceProducerWithoutBinder<TServiceClass> serviceProducer) {
    ensureInitializing("registerContextAwareService");
    Preconditions.checkStringNotEmpty(paramString);
    Objects.requireNonNull(paramClass);
    Objects.requireNonNull(serviceProducer);
    registerService(paramString, paramClass, new CachedServiceFetcher<TServiceClass>() {
          public TServiceClass createService(ContextImpl param1ContextImpl) {
            return serviceProducer.createService(param1ContextImpl.getOuterContext());
          }
        });
  }
  
  private static <T> void registerService(String paramString, Class<T> paramClass, ServiceFetcher<T> paramServiceFetcher) {
    SYSTEM_SERVICE_NAMES.put(paramClass, paramString);
    SYSTEM_SERVICE_FETCHERS.put(paramString, paramServiceFetcher);
    SYSTEM_SERVICE_CLASS_NAMES.put(paramString, paramClass.getSimpleName());
  }
  
  @SystemApi
  public static <TServiceClass> void registerStaticService(final String serviceName, Class<TServiceClass> paramClass, final StaticServiceProducerWithBinder<TServiceClass> serviceProducer) {
    ensureInitializing("registerStaticService");
    Preconditions.checkStringNotEmpty(serviceName);
    Objects.requireNonNull(paramClass);
    Objects.requireNonNull(serviceProducer);
    registerService(serviceName, paramClass, new StaticServiceFetcher<TServiceClass>() {
          public TServiceClass createService() throws ServiceManager.ServiceNotFoundException {
            return serviceProducer.createService(ServiceManager.getServiceOrThrow(serviceName));
          }
        });
  }
  
  @SystemApi
  public static <TServiceClass> void registerStaticService(String paramString, Class<TServiceClass> paramClass, final StaticServiceProducerWithoutBinder<TServiceClass> serviceProducer) {
    ensureInitializing("registerStaticService");
    Preconditions.checkStringNotEmpty(paramString);
    Objects.requireNonNull(paramClass);
    Objects.requireNonNull(serviceProducer);
    registerService(paramString, paramClass, new StaticServiceFetcher<TServiceClass>() {
          public TServiceClass createService() {
            return serviceProducer.createService();
          }
        });
  }
  
  static abstract class CachedServiceFetcher<T> implements ServiceFetcher<T> {
    private final int mCacheIndex = SystemServiceRegistry.access$008();
    
    public abstract T createService(ContextImpl param1ContextImpl) throws ServiceManager.ServiceNotFoundException;
    
    public final T getService(ContextImpl param1ContextImpl) {
      // Byte code:
      //   0: aload_1
      //   1: getfield mServiceCache : [Ljava/lang/Object;
      //   4: astore_2
      //   5: aload_1
      //   6: getfield mServiceInitializationStateArray : [I
      //   9: astore_3
      //   10: iconst_0
      //   11: istore #4
      //   13: iconst_0
      //   14: istore #5
      //   16: aload_2
      //   17: monitorenter
      //   18: aload_2
      //   19: aload_0
      //   20: getfield mCacheIndex : I
      //   23: aaload
      //   24: astore #6
      //   26: aload #6
      //   28: ifnonnull -> 254
      //   31: aload_3
      //   32: aload_0
      //   33: getfield mCacheIndex : I
      //   36: iaload
      //   37: iconst_3
      //   38: if_icmpne -> 44
      //   41: goto -> 254
      //   44: aload_3
      //   45: aload_0
      //   46: getfield mCacheIndex : I
      //   49: iaload
      //   50: iconst_2
      //   51: if_icmpne -> 61
      //   54: aload_3
      //   55: aload_0
      //   56: getfield mCacheIndex : I
      //   59: iconst_0
      //   60: iastore
      //   61: aload_3
      //   62: aload_0
      //   63: getfield mCacheIndex : I
      //   66: iaload
      //   67: ifne -> 80
      //   70: iconst_1
      //   71: istore #5
      //   73: aload_3
      //   74: aload_0
      //   75: getfield mCacheIndex : I
      //   78: iconst_1
      //   79: iastore
      //   80: aload_2
      //   81: monitorexit
      //   82: iload #5
      //   84: ifeq -> 197
      //   87: aconst_null
      //   88: astore #6
      //   90: aload_0
      //   91: aload_1
      //   92: invokevirtual createService : (Landroid/app/ContextImpl;)Ljava/lang/Object;
      //   95: astore_1
      //   96: aload_2
      //   97: monitorenter
      //   98: aload_2
      //   99: aload_0
      //   100: getfield mCacheIndex : I
      //   103: aload_1
      //   104: aastore
      //   105: aload_3
      //   106: aload_0
      //   107: getfield mCacheIndex : I
      //   110: iconst_2
      //   111: iastore
      //   112: aload_2
      //   113: invokevirtual notifyAll : ()V
      //   116: aload_2
      //   117: monitorexit
      //   118: goto -> 160
      //   121: astore_1
      //   122: aload_2
      //   123: monitorexit
      //   124: aload_1
      //   125: athrow
      //   126: astore_1
      //   127: goto -> 168
      //   130: astore_1
      //   131: aload_1
      //   132: invokestatic onServiceNotFound : (Landroid/os/ServiceManager$ServiceNotFoundException;)V
      //   135: aload_2
      //   136: monitorenter
      //   137: aload_2
      //   138: aload_0
      //   139: getfield mCacheIndex : I
      //   142: aconst_null
      //   143: aastore
      //   144: aload_3
      //   145: aload_0
      //   146: getfield mCacheIndex : I
      //   149: iconst_3
      //   150: iastore
      //   151: aload_2
      //   152: invokevirtual notifyAll : ()V
      //   155: aload_2
      //   156: monitorexit
      //   157: aload #6
      //   159: astore_1
      //   160: goto -> 259
      //   163: astore_1
      //   164: aload_2
      //   165: monitorexit
      //   166: aload_1
      //   167: athrow
      //   168: aload_2
      //   169: monitorenter
      //   170: aload_2
      //   171: aload_0
      //   172: getfield mCacheIndex : I
      //   175: aconst_null
      //   176: aastore
      //   177: aload_3
      //   178: aload_0
      //   179: getfield mCacheIndex : I
      //   182: iconst_3
      //   183: iastore
      //   184: aload_2
      //   185: invokevirtual notifyAll : ()V
      //   188: aload_2
      //   189: monitorexit
      //   190: aload_1
      //   191: athrow
      //   192: astore_1
      //   193: aload_2
      //   194: monitorexit
      //   195: aload_1
      //   196: athrow
      //   197: aload_2
      //   198: monitorenter
      //   199: aload_3
      //   200: aload_0
      //   201: getfield mCacheIndex : I
      //   204: iaload
      //   205: istore #5
      //   207: iload #5
      //   209: iconst_2
      //   210: if_icmpge -> 244
      //   213: iload #4
      //   215: invokestatic interrupted : ()Z
      //   218: ior
      //   219: istore #4
      //   221: aload_2
      //   222: invokevirtual wait : ()V
      //   225: goto -> 199
      //   228: astore #6
      //   230: ldc 'SystemServiceRegistry'
      //   232: ldc 'getService() interrupted'
      //   234: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
      //   237: pop
      //   238: iconst_1
      //   239: istore #4
      //   241: goto -> 225
      //   244: aload_2
      //   245: monitorexit
      //   246: goto -> 13
      //   249: astore_1
      //   250: aload_2
      //   251: monitorexit
      //   252: aload_1
      //   253: athrow
      //   254: aload #6
      //   256: astore_1
      //   257: aload_2
      //   258: monitorexit
      //   259: iload #4
      //   261: ifeq -> 270
      //   264: invokestatic currentThread : ()Ljava/lang/Thread;
      //   267: invokevirtual interrupt : ()V
      //   270: aload_1
      //   271: areturn
      //   272: astore_1
      //   273: aload_2
      //   274: monitorexit
      //   275: aload_1
      //   276: athrow
      // Exception table:
      //   from	to	target	type
      //   18	26	272	finally
      //   31	41	272	finally
      //   44	61	272	finally
      //   61	70	272	finally
      //   73	80	272	finally
      //   80	82	272	finally
      //   90	96	130	android/os/ServiceManager$ServiceNotFoundException
      //   90	96	126	finally
      //   98	118	121	finally
      //   122	124	121	finally
      //   131	135	126	finally
      //   137	157	163	finally
      //   164	166	163	finally
      //   170	190	192	finally
      //   193	195	192	finally
      //   199	207	249	finally
      //   213	225	228	java/lang/InterruptedException
      //   213	225	249	finally
      //   230	238	249	finally
      //   244	246	249	finally
      //   250	252	249	finally
      //   257	259	272	finally
      //   273	275	272	finally
    }
  }
  
  @SystemApi
  public static interface ContextAwareServiceProducerWithBinder<TServiceClass> {
    TServiceClass createService(Context param1Context, IBinder param1IBinder);
  }
  
  @SystemApi
  public static interface ContextAwareServiceProducerWithoutBinder<TServiceClass> {
    TServiceClass createService(Context param1Context);
  }
  
  static interface ServiceFetcher<T> {
    T getService(ContextImpl param1ContextImpl);
  }
  
  static abstract class StaticApplicationContextServiceFetcher<T> implements ServiceFetcher<T> {
    private T mCachedInstance;
    
    public abstract T createService(Context param1Context) throws ServiceManager.ServiceNotFoundException;
    
    public final T getService(ContextImpl param1ContextImpl) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mCachedInstance : Ljava/lang/Object;
      //   6: ifnonnull -> 40
      //   9: aload_1
      //   10: invokevirtual getApplicationContext : ()Landroid/content/Context;
      //   13: astore_2
      //   14: aload_2
      //   15: ifnull -> 23
      //   18: aload_2
      //   19: astore_1
      //   20: goto -> 23
      //   23: aload_0
      //   24: aload_0
      //   25: aload_1
      //   26: invokevirtual createService : (Landroid/content/Context;)Ljava/lang/Object;
      //   29: putfield mCachedInstance : Ljava/lang/Object;
      //   32: goto -> 40
      //   35: astore_1
      //   36: aload_1
      //   37: invokestatic onServiceNotFound : (Landroid/os/ServiceManager$ServiceNotFoundException;)V
      //   40: aload_0
      //   41: getfield mCachedInstance : Ljava/lang/Object;
      //   44: astore_1
      //   45: aload_0
      //   46: monitorexit
      //   47: aload_1
      //   48: areturn
      //   49: astore_1
      //   50: aload_0
      //   51: monitorexit
      //   52: aload_1
      //   53: athrow
      // Exception table:
      //   from	to	target	type
      //   2	14	49	finally
      //   23	32	35	android/os/ServiceManager$ServiceNotFoundException
      //   23	32	49	finally
      //   36	40	49	finally
      //   40	47	49	finally
      //   50	52	49	finally
    }
  }
  
  static abstract class StaticServiceFetcher<T> implements ServiceFetcher<T> {
    private T mCachedInstance;
    
    public abstract T createService() throws ServiceManager.ServiceNotFoundException;
    
    public final T getService(ContextImpl param1ContextImpl) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mCachedInstance : Ljava/lang/Object;
      //   6: astore_1
      //   7: aload_1
      //   8: ifnonnull -> 27
      //   11: aload_0
      //   12: aload_0
      //   13: invokevirtual createService : ()Ljava/lang/Object;
      //   16: putfield mCachedInstance : Ljava/lang/Object;
      //   19: goto -> 27
      //   22: astore_1
      //   23: aload_1
      //   24: invokestatic onServiceNotFound : (Landroid/os/ServiceManager$ServiceNotFoundException;)V
      //   27: aload_0
      //   28: getfield mCachedInstance : Ljava/lang/Object;
      //   31: astore_1
      //   32: aload_0
      //   33: monitorexit
      //   34: aload_1
      //   35: areturn
      //   36: astore_1
      //   37: aload_0
      //   38: monitorexit
      //   39: aload_1
      //   40: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	36	finally
      //   11	19	22	android/os/ServiceManager$ServiceNotFoundException
      //   11	19	36	finally
      //   23	27	36	finally
      //   27	34	36	finally
      //   37	39	36	finally
    }
  }
  
  @SystemApi
  public static interface StaticServiceProducerWithBinder<TServiceClass> {
    TServiceClass createService(IBinder param1IBinder);
  }
  
  @SystemApi
  public static interface StaticServiceProducerWithoutBinder<TServiceClass> {
    TServiceClass createService();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */