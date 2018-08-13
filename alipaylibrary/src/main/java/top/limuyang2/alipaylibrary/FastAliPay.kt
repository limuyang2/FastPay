package top.limuyang2.alipaylibrary

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.SupportActivity
import com.alipay.sdk.app.PayTask
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import top.limuyang2.basepaylibrary.PayResource


/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
class FastAliPay(private val activity: SupportActivity) {

    private val aliPayLiveData = MutableLiveData<PayResource>()

    fun pay(orderInfo: String, observer: AliPayObserver) {
        async(UI) {
            val payResultMap = bg {
                // 构造PayTask 对象
                val aliPay = PayTask(activity)
                // 调用支付接口，获取支付结果
                aliPay.payV2(orderInfo, true)
            }.await()

            //支付结果处理
            val payResult = PayResult(payResultMap)

            val resultStatus = payResult.resultStatus
            // 判断resultStatus 为9000则代表支付成功
            when(resultStatus){
                "9000" ->{
                    aliPayLiveData.value = PayResource.success()
                }
                "6001" ->{
                    aliPayLiveData.value = PayResource.cancel()
                }
                "4000" -> {
                    aliPayLiveData.value = PayResource.failed("订单支付失败")
                }
                "5000" -> {
                    aliPayLiveData.value = PayResource.failed("重复请求")
                }
                "6002" -> {
                    aliPayLiveData.value = PayResource.failed("网络连接出错")
                }
                "6004" -> {
                    aliPayLiveData.value = PayResource.failed("支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态")
                }
                else -> {
                    aliPayLiveData.value = PayResource.failed(payResult.memo)
                }
            }


        }
        aliPayLiveData.observe(activity, observer)
    }


}