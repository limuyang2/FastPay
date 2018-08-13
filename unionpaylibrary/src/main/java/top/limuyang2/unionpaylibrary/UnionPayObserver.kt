package top.limuyang2.unionpaylibrary

import top.limuyang2.basepaylibrary.BasePayObserver
import top.limuyang2.basepaylibrary.PayResource

/**
 * @author limuyang
 * @date 2018/8/12
 * @class describe
 */
interface UnionPayObserver : BasePayObserver {

    override fun onChanged(t: PayResource?) {
        super.onChanged(t)
        FastUnionPay.unionPayLiveData = null
    }
}

abstract class JavaUnionPayObserver : UnionPayObserver