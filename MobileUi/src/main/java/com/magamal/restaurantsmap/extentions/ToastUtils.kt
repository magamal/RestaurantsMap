import android.widget.Toast
import androidx.annotation.StringRes
import com.magamal.restaurantsmap.app.RestaurantsMapApp

private var toast: Toast? = null

fun toast(@StringRes messageResId: Int) =
    showToast(
        RestaurantsMapApp.instance.getString(
            messageResId
        ), Toast.LENGTH_SHORT
    )

fun toast(message: String?) =
    showToast(message, Toast.LENGTH_SHORT)

fun longToast(@StringRes messageResId: Int) =
    showToast(
        RestaurantsMapApp.instance.getString(
            messageResId
        ), Toast.LENGTH_LONG
    )

fun longToast(message: String?) =
    showToast(message, Toast.LENGTH_LONG)

private fun showToast(message: String?, duration: Int) {
    toast?.cancel()
    toast = Toast.makeText(RestaurantsMapApp.instance, "$message", duration).apply { show() }
}