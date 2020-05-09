package top.limuyang2.unionpaylibrary

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.unionpay.UPPayAssistEx
import top.limuyang2.basepaylibrary.PayResource


/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
class UnionPayAssistActivity : AppCompatActivity() {

    companion object {
        const val PAY_TYPE = "payType"
        const val TN = "tn"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        val payModel = intent.getSerializableExtra(PAY_TYPE) as UnionPayType
        val tn = intent.getStringExtra(TN)

        UPPayAssistEx.startPay(this, null, null, tn, payModel.model)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            finish()
            return
        }

        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        val extras = data.extras
        if (extras == null) {
            finish()
            return
        } else {
            val str = extras.getString("pay_result")
            when {
                str.equals("success", ignoreCase = true) -> {
                    FastUnionPay.unionPayLiveData?.value = PayResource.success()
                }
                str.equals("fail", ignoreCase = true) -> {
                    FastUnionPay.unionPayLiveData?.value = PayResource.failed("支付失败!")
                }
                str.equals("cancel", ignoreCase = true) -> {
                    FastUnionPay.unionPayLiveData?.value = PayResource.cancel()
                }
            }
        }

    }
}