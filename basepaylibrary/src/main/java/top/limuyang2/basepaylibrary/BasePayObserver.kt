package top.limuyang2.basepaylibrary

import androidx.annotation.CallSuper
import androidx.lifecycle.Observer

/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */

interface BasePayObserver : Observer<PayResource> {

    fun onSuccess() {}

    fun onFailed(message: String) {}

    fun onCancel()

    fun onComplete()

    @CallSuper
    override fun onChanged(t: PayResource?) {
        t?.let {
            when (t.status) {
                PayStatus.SUCCESS -> {
                    onSuccess()
                    onComplete()
                }
                PayStatus.FAILED -> {
                    onFailed(t.message ?: "未知错误")
                    onComplete()
                }
                PayStatus.CANCEL -> onCancel()
            }
        }
    }
}
