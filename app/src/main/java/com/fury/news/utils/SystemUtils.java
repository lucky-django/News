package com.fury.news.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.util.TypedValue;
import com.fury.news.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class SystemUtils {
    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    // private static final int NETWORK_TYPE_MOBILE = -100;
    private static final int NETWORK_TYPE_WIFI = -101;

    private static final int NETWORK_CLASS_WIFI = -101;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    /** Unknown network class. */
    private static final int NETWORK_CLASS_UNKNOWN = 0;
    /** Class of broadly defined "2G" networks. */
    private static final int NETWORK_CLASS_2_G = 1;
    /** Class of broadly defined "3G" networks. */
    private static final int NETWORK_CLASS_3_G = 2;
    /** Class of broadly defined "4G" networks. */
    private static final int NETWORK_CLASS_4_G = 3;

    // 适配低版本手机
    /** Network type is unknown */
    private static final int NETWORK_TYPE_UNKNOWN = 0;
    /** Current network is GPRS */
    private static final int NETWORK_TYPE_GPRS = 1;
    /** Current network is EDGE */
    private static final int NETWORK_TYPE_EDGE = 2;
    /** Current network is UMTS */
    private static final int NETWORK_TYPE_UMTS = 3;
    /** Current network is CDMA: Either IS95A or IS95B */
    private static final int NETWORK_TYPE_CDMA = 4;
    /** Current network is EVDO revision 0 */
    private static final int NETWORK_TYPE_EVDO_0 = 5;
    /** Current network is EVDO revision A */
    private static final int NETWORK_TYPE_EVDO_A = 6;
    /** Current network is 1xRTT */
    private static final int NETWORK_TYPE_1xRTT = 7;
    /** Current network is HSDPA */
    private static final int NETWORK_TYPE_HSDPA = 8;
    /** Current network is HSUPA */
    private static final int NETWORK_TYPE_HSUPA = 9;
    /** Current network is HSPA */
    private static final int NETWORK_TYPE_HSPA = 10;
    /** Current network is iDen */
    private static final int NETWORK_TYPE_IDEN = 11;
    /** Current network is EVDO revision B */
    private static final int NETWORK_TYPE_EVDO_B = 12;
    /** Current network is LTE */
    private static final int NETWORK_TYPE_LTE = 13;
    /** Current network is eHRPD */
    private static final int NETWORK_TYPE_EHRPD = 14;
    /** Current network is HSPA+ */
    private static final int NETWORK_TYPE_HSPAP = 15;

    public static void copyToClipboard(Context context, CharSequence text) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setText(text);
    }

    public static String getAppName(Context context, int pid) {
        String processName = null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        Iterator<RunningAppProcessInfo> iterator = manager.getRunningAppProcesses().iterator();
        while (iterator.hasNext()) {
            RunningAppProcessInfo info = iterator.next();
            try {
                if (info.pid == pid) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }

    public static String readAssetsFileToString(Context context, String fileName) {
        String str = null;
        try {
            InputStream inStream = context.getAssets().open(fileName);
            int size = inStream.available();
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();
            str = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int dp2px(Context context, float value) {
        return (int) (0.5F + value * context.getResources().getDisplayMetrics().density);
    }

    public static int px2dp(Context context, float value) {
        return (int) (0.5F + value / context.getResources().getDisplayMetrics().density);
    }

    public static float sp2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics());
    }

    public static float px2sp(Context context, Float value) {
        return (value.floatValue() / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static boolean isApplicationBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static String getMetaValue(Context context, String metaKey) {
        String metaValue = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            Bundle metaData = null;
            if (info != null) {
                metaData = info.metaData;
                if (metaData != null) {
                    metaValue = metaData.get(metaKey).toString();
                }
            }
        } catch (NameNotFoundException e) {
        }
        return metaValue;
    }

    public static void startWebView(Context context, String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager teleManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((connManager.getActiveNetworkInfo() != null
                && connManager.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)
                || teleManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    public static String getNetworkType(Context context) {
        int networkClass = getNetworkClass(context);
        String type = "UNAVAILABLE";
        switch (networkClass) {
        case NETWORK_CLASS_UNAVAILABLE:
            type = "NONE";
            break;
        case NETWORK_CLASS_WIFI:
            type = "WIFI";
            break;
        case NETWORK_CLASS_2_G:
            type = "2G";
            break;
        case NETWORK_CLASS_3_G:
            type = "3G";
            break;
        case NETWORK_CLASS_4_G:
            type = "4G";
            break;
        case NETWORK_CLASS_UNKNOWN:
            type = "UNKNOWN";
            break;
        }
        return type;
    }

    private static int getNetworkClass(Context context) {
        int networkType = NETWORK_TYPE_UNKNOWN;
        try {
            final NetworkInfo network = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            if (network != null && network.isAvailable() && network.isConnected()) {
                int type = network.getType();
                if (type == ConnectivityManager.TYPE_WIFI) {
                    networkType = NETWORK_TYPE_WIFI;
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    TelephonyManager telephonyManager = (TelephonyManager) context
                            .getSystemService(Context.TELEPHONY_SERVICE);
                    networkType = telephonyManager.getNetworkType();
                }
            } else {
                networkType = NETWORK_TYPE_UNAVAILABLE;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return getNetworkClassByType(networkType);
    }

    private static int getNetworkClassByType(int networkType) {
        switch (networkType) {
        case NETWORK_TYPE_UNAVAILABLE:
            return NETWORK_CLASS_UNAVAILABLE;
        case NETWORK_TYPE_WIFI:
            return NETWORK_CLASS_WIFI;
        case NETWORK_TYPE_GPRS:
        case NETWORK_TYPE_EDGE:
        case NETWORK_TYPE_CDMA:
        case NETWORK_TYPE_1xRTT:
        case NETWORK_TYPE_IDEN:
            return NETWORK_CLASS_2_G;
        case NETWORK_TYPE_UMTS:
        case NETWORK_TYPE_EVDO_0:
        case NETWORK_TYPE_EVDO_A:
        case NETWORK_TYPE_HSDPA:
        case NETWORK_TYPE_HSUPA:
        case NETWORK_TYPE_HSPA:
        case NETWORK_TYPE_EVDO_B:
        case NETWORK_TYPE_EHRPD:
        case NETWORK_TYPE_HSPAP:
            return NETWORK_CLASS_3_G;
        case NETWORK_TYPE_LTE:
            return NETWORK_CLASS_4_G;
        default:
            return NETWORK_CLASS_UNKNOWN;
        }
    }

    public static String getProviderName(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = manager.getSubscriberId(); // sim卡信息
        if (imsi == null) {
            return context.getString(R.string.provider_unknow);
        }
        String provider = null;
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信
        if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
            provider = context.getString(R.string.provider_china_mobile);
        } else if (imsi.startsWith("46001")) {
            provider = context.getString(R.string.provider_china_unicom);
        } else if (imsi.startsWith("46003")) {
            provider = context.getString(R.string.provider_china_telecom);
        } else {
            provider = context.getString(R.string.provider_abroad);
        }
        return provider;
    }

}
