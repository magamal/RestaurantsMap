package com.magamal.restaurantsmap.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magamal.common.AppException
import com.magamal.restaurantsmap.R

/**
 * @author Mahmoud Gamal on 1/7/21.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutRes(), container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupData()
        setupViews()
        setupObservers()
        if (savedInstanceState == null) // we fetch data only when the page is not recreated
            fetchData()
    }

    abstract fun getLayoutRes(): Int

    /**
     * this method is a callback used to initialize your view
     * and called after @link #setupData()
     */
    protected open fun setupViews() {}

    /**
     * this method is a callback used to setup your data
     * and called after @link #getLayoutRes()
     */
    protected open fun setupData() {}


    /**
     * this method is a callback used to setup your observers
     * and called after @link #setupViews()
     */
    protected open fun setupObservers() {}

    /**
     * this method is a callback used to fetching data
     * and called after @link #setupObservers()
     * this callback will be called automatic when you first load the fragment
     */
    protected open fun fetchData() {}

    fun getMessageForThrowable(e: Throwable?): String {
        return if (e is AppException) {
            if (!e.errorMsg.isNullOrEmpty())
                return e.errorMsg!!
            return when (e.exceptionType) {
                AppException.ExceptionType.NETWORK ->
                    getString(R.string.network_error)
                AppException.ExceptionType.UNAUTHORIZED ->
                    getString(R.string.credential_error)
                AppException.ExceptionType.SERVER_FAILED, AppException.ExceptionType.PARSING ->
                    getString(R.string.something_wrong)
                else -> getString(R.string.unknown_error)
            }
        } else getString(R.string.unknown_error)
    }

}