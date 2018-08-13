package top.limuyang2.wechatpaylibrary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import top.limuyang2.basepaylibrary.PayResource

/**
 * @author limuyang
 * @date 2018/8/11
 * @class describe
 */
abstract class BaseWXPayEntryActivity : Activity(), IWXAPIEventHandler {

//    abstract fun getWXAppId(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FastWxPay.mWXApi?.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        FastWxPay.mWXApi?.handleIntent(intent, this)
    }

    override fun onResp(resp: BaseResp?) {
        resp?.let {
            if (it.type == ConstantsAPI.COMMAND_PAY_BY_WX) {

                when {
                    it.errCode == 0 -> //成功
                        FastWxPay.wxPayLiveData?.postValue(PayResource.success())
                    it.errCode == -1 -> //错误
                        FastWxPay.wxPayLiveData?.postValue(PayResource.failed(it.errStr
                                                                                  ?: "支付发生错误"))
                    it.errCode == -2 -> //取消
                        FastWxPay.wxPayLiveData?.postValue(PayResource.cancel())
                }
                finish()
            }
        }
    }

    override fun onReq(p0: BaseReq?) {
    }
}