package com.shankes.util;

import static android.content.Context.CONNECTIVITY_SERVICE;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.shankes.websocketclient.R;

/**
 * 跟网络相关的工具类
 * 获取网络状态的权限
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
 *
 * @author shankes
 */
public class NetUtils {

    private static final String TAG = "NetUtils";
    private static final String IP_DEFAULT = "0.0.0,0";
    private static final String NET_TYPE_NO_NETWORK = "No Internet";

    public static void startActivityWithNet(Context context, Intent intent) {
        if (NetUtils.isNetworkAvailable(context)) {// 检测网络是否可用
            LogUtil.i(context.getString(R.string.network_available));
            ToastUtil.showShortDebug(context, R.string.network_available);
            if (!NetUtils.isWifiNetwork(context)) {// 检测是否为wifi
                LogUtil.e(context.getString(R.string.network_not_wifi));
                ToastUtil.showShort(context, R.string.network_not_wifi);
            }
            context.startActivity(intent);
        } else {
            LogUtil.e(context.getString(R.string.network_unavailable));
            ToastUtil.showShort(context, R.string.network_unavailable);
            if (!NetUtils.isWifiEnabled(context)) {// 检测wifi开关是否打开
                LogUtil.e(context.getString(R.string.network_wifi_switch_closed));
                ToastUtil.showShortDebug(context, R.string.network_wifi_switch_closed);
            }
        }
    }

    public static void startActivityWithNet(Context packageContext, Class<?> cls) {
        if (NetUtils.isNetworkAvailable(packageContext)) {// 检测网络是否可用
            LogUtil.i(packageContext.getString(R.string.network_available));
            ToastUtil.showShortDebug(packageContext, R.string.network_available);
            if (!NetUtils.isWifiNetwork(packageContext)) {// 检测是否为wifi
                LogUtil.e(packageContext.getString(R.string.network_not_wifi));
                ToastUtil.showShort(packageContext, R.string.network_not_wifi);
            }
            Intent intent = new Intent();
            intent.setClass(packageContext, cls);
            packageContext.startActivity(intent);
        } else {
            LogUtil.e(packageContext.getString(R.string.network_unavailable));
            ToastUtil.showShort(packageContext, R.string.network_unavailable);
            if (!NetUtils.isWifiEnabled(packageContext)) {// 检测wifi开关是否打开
                LogUtil.e(packageContext.getString(R.string.network_wifi_switch_closed));
                ToastUtil.showShortDebug(packageContext, R.string.network_wifi_switch_closed);
            }
        }
    }

    public static void startActivityForResultWithNet(Activity activity, Intent intent, int requestCode) {
        if (NetUtils.isNetworkAvailable(activity)) {// 检测网络是否可用
            LogUtil.i(activity.getString(R.string.network_available));
            ToastUtil.showShortDebug(activity, R.string.network_available);
            if (!NetUtils.isWifiNetwork(activity)) {// 检测是否为wifi
                LogUtil.e(activity.getString(R.string.network_not_wifi));
                ToastUtil.showShort(activity, R.string.network_not_wifi);
            }
            activity.startActivityForResult(intent, requestCode);
        } else {
            LogUtil.e(activity.getString(R.string.network_unavailable));
            ToastUtil.showShort(activity, R.string.network_unavailable);
            if (!NetUtils.isWifiEnabled(activity)) {// 检测wifi开关是否打开
                LogUtil.e(activity.getString(R.string.network_wifi_switch_closed));
                ToastUtil.showShortDebug(activity, R.string.network_wifi_switch_closed);
            }
        }
    }

    /**当我们要在程序中监听网络状态时，只要一下几个步骤即可：
     button_plus_n.定义一个Receiver重载其中的onReceive函数，在其中完成所需要的功能，如根据WIFI和GPRS是否断开来改变空间的外观
     复制代码 代码如下:*/
    /**    2.在适当的地方注册Receiver，可以在程序中注册，在onCreate中调用如下函数即可：
     IntentFilter intentFilter = new IntentFilter();
     intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
     registerReceiver(connectionReceiver, intentFilter);*/
    /**
     * 3.在适当时取消注册Receiver，可以在程序中取消，在onDestroye中调用如下函数即可：
     * if (connectionReceiver != null) {
     * unregisterReceiver(connectionReceiver);
     * }
     */
    public static void setBroadcastReciver(Context context) {
        BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                    Log.i(TAG, "unconnect");
                    // unconnect network
                } else {
                    // connect network
                }
            }
        };
    }

    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gps是否打开
     *
     * @param context
     * @return
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = ((LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = locationManager.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }

    /**
     * wifi是否打开
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifiNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 判断当前网络是否数据网络
     *
     * @param context
     * @return boolean
     */
    public static boolean isMobileNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    public static String getNetTypeName(final int pNetType) {
        switch (pNetType) {
            case 0:
                return "unknown";
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA: Either IS95A or IS95B";
            case 5:
                return "EVDO revision 0";
            case 6:
                return "EVDO revision A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDen";
            case 12:
                return "EVDO revision B";
            case 13:
                return "LTE";
            case 14:
                return "eHRPD";
            case 15:
                return "HSPA+";
            default:
                return "unknown";
        }
    }

    public static String getIPAddress() {
        try {
            final Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaceEnumeration.hasMoreElements()) {
                final NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();

                final Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();

                while (inetAddressEnumeration.hasMoreElements()) {
                    final InetAddress inetAddress = inetAddressEnumeration.nextElement();

                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }

            return NetUtils.IP_DEFAULT;
        } catch (final SocketException e) {
            return NetUtils.IP_DEFAULT;
        }
    }

    public static String getConnTypeName(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NET_TYPE_NO_NETWORK;
        } else {
            return networkInfo.getTypeName();
        }
    }
}
