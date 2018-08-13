package top.limuyang2.unionpaylibrary

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.support.v4.app.SupportActivity
import top.limuyang2.basepaylibrary.PayResource


/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
class FastUnionPay(private val activity: SupportActivity) {

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
        var unionPayLiveData: MutableLiveData<PayResource>? = null

    }


}

enum class UnionPayType(val model: String) {
    RELEASE("00"),
    TEST("01");
}

