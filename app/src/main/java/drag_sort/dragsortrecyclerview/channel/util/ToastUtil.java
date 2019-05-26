package drag_sort.dragsortrecyclerview.channel.util;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by Ji on 2016/8/23.
 */
public class ToastUtil {
    private static Toast toast;

    public static void showShort(Context context,CharSequence sequence) {

        if (toast == null) {
            toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);

        } else {
            toast.setText(sequence);
        }
        toast.show();

    }

    /**
     * short length Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * long length Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * long length Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            //    toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * set toast time
     *
     * @param context
     * @param sequence
     * @param duration
     */
    public static void show(Context context, CharSequence sequence, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, sequence, duration);
        } else {
            toast.setText(sequence);
        }
        toast.show();

    }

    /**
     * hide toast
     */
    public static void hideToast() {
        if (toast != null) {
            toast.cancel();
        }
    }


}
