package demo.stpl.com.tpsmergedbuild.baseClass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.widget.Toast;

/**
 * Created by stpl on 29-Dec-16.
 */
public class UsbBroadCastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        UsbManager manager = (UsbManager)context.getSystemService(Context.USB_SERVICE);
        UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
        System.out.println("BroadcastReceiver Event");
        if (manager.hasPermission(device)) {



                if (device != null) {
                    // call your method that cleans up and closes communication with the device
                }

//                Parcelable targetParcelable = intent.getParcelableExtra(Intent.EXTRA_INTENT);
//                Intent target = (Intent)targetParcelable;
//                UsbDevice mDevice = (UsbDevice)target.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//                PackageManager pm = context.getPackageManager();
//                try{
//                    ApplicationInfo ai = pm.getApplicationInfo( "demo.stpl.com.tpsmergedbuild", 0 );
//                    if( ai != null )
//                    {
//                        UsbManager manager = (UsbManager) context.getSystemService( Context.USB_SERVICE );
//                        IBinder b = ServiceManager.getService(Context.USB_SERVICE);
//                        IUsbManager service = IUsbManager.Stub.asInterface( b );
//
//                        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
//                        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
//                        while( deviceIterator.hasNext() )
//                        {
//                            UsbDevice device = deviceIterator.next();
//                            int vender = device.getVendorId();
//                            if( service.hasDevicePermission(device) )
//                            {
////                                service.requestAccessoryPermission( device, ai.uid );
////                                service.grantDevicePermission( device, ai.uid );
////                                service.setDevicePackage( device, "demo.stpl.com.tpsmergedbuild" );
//                            }
//                        }
//                    }
//                }catch (Exception e){
//
//                    System.out.print(e.getLocalizedMessage());
//                }
//
////                mSerial.usbAttached(intent);
////                mSerial.begin(mBaudrate);
////                loadDefaultSettingValues();
////                Run = true;
////                start();
//                System.out.println("BroadcastReceiver USB Connected");
            TpsGamesClass.getInstance().getTps515PrintCall().onUsbPermissionSuccess();

        } else if (!manager.hasPermission(device)) {
//                mSerial.usbDetached(intent);
//                mSerial.end();
//                Run = false;
            TpsGamesClass.getInstance().showAToast("no permission found!",context, Toast.LENGTH_LONG);
            TpsGamesClass.getInstance().getTps515PrintCall().onPermissionDenied();
            System.out.println("BroadcastReceiver USB Disconnected");
        }
    }
}
