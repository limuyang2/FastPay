package top.limuyang2.wechatpaylibrary

import top.limuyang2.basepaylibrary.BasePayObserver
import top.limuyang2.basepaylibrary.PayResource
import top.limuyang2.wechatpaylibrary.FastWxPay.Companion.mWXApi

/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
interface WxPayObserver : BasePayObserver {

    override fun onChanged(t: PayResource?) {
        super.onChanged(t)
        mWXApi = null
        FastWxPay.wxPayLiveData = null
    }
}

abstract class JavaWxPayObserver : WxPayObserver