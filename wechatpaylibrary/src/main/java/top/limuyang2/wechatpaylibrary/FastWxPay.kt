package top.limuyang2.wechatpaylibrary

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.SupportActivity
import com.tencent.mm.opensdk.constants.Build
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import top.limuyang2.basepaylibrary.PayResource


/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
class FastWxPay(appId: String, private val activity: SupportActivity) {

    init {
        wxPayLiveData = MutableLiveData()

        mWXApi = WXAPIFactory.createWXAPI(activity.applicationContext, null)
        // 将该app注册到微信
        mWXApi?.registerApp(appId)
    }

    fun pay(payReq: PayReq, observer: WxPayObserver) {
        wxPayLiveData?.observe(activity, observer)

        if (mWXApi == null) {
            wxPayLiveData?.value = PayResource.failed("WXAPI NULL")
            return
        } else {
            if (!mWXApi!!.isWXAppInstalled) {
                wxPayLiveData?.value = PayResource.failed("微信App 未安装")
                return
            }
            if (mWXApi!!.wxAppSupportAPI < Build.PAY_SUPPORTED_SDK_INT) {
                wxPayLiveData?.value = PayResource.failed("当前微信版本不支持付款")
                return
            }
        }

        mWXApi?.sendReq(payReq)
    }

    companion object {
        var wxPayLiveData: MutableLiveData<PayResource>? = null

        var mWXApi: IWXAPI? = null
    }

}