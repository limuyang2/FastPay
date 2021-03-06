package top.limuyang2.unionpaylibrary

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import top.limuyang2.basepaylibrary.PayResource


/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
class FastUnionPay(private val activity: ComponentActivity) {

    init {
        unionPayLiveData = MutableLiveData()
    }

    fun pay(payModel: UnionPayType, tn: String, observer: UnionPayObserver) {
        unionPayLiveData?.observe(activity, observer)

        val intent = Intent(activity, UnionPayAssistActivity::class.java)
        intent.putExtra(UnionPayAssistActivity.PAY_TYPE, payModel)
        intent.putExtra(UnionPayAssistActivity.TN, tn)
        activity.startActivity(intent)
    }

    companion object {
        internal var unionPayLiveData: MutableLiveData<PayResource>? = null

    }


}

enum class UnionPayType(val model: String) {
    RELEASE("00"),
    TEST("01");
}

