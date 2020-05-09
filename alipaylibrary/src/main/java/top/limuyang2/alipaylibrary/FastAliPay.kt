package top.limuyang2.alipaylibrary

import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.alipay.sdk.app.PayTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import top.limuyang2.basepaylibrary.PayResource


/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
class FastAliPay(private val activity: ComponentActivity) {

    private val aliPayLiveData = MutableLiveData<PayResource>()

    fun pay(orderInfo: String, observer: AliPayObserver) {
        activity.lifecycleScope.launch {
            val payResultMap = withContext(Dispatchers.IO) {
                // 构造PayTask 对象
                val aliPay = PayTask(activity)
                // 调用支付接口，获取支付结果
                aliPay.payV2(orderInfo, true)
            }

            //支付结果处理
            val payResult = PayResult(payResultMap)

            // 判断resultStatus 为9000则代表支付成功
            when (payResult.resultStatus) {
                "9000" -> {
                    aliPayLiveData.value = PayResource.success()
                }
                "6001" -> {
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