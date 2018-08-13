package top.limuyang2.basepaylibrary

/**
 * @author limuyang
 * @date 2018/8/8
 * @class describe
 */

data class PayResource(val status: PayStatus, val message: String?) {
    companion object {
        fun success(): PayResource {
            return PayResource(PayStatus.SUCCESS, null)
        }

        fun failed(msg: String): PayResource {
            return PayResource(PayStatus.FAILED, msg)
        }

        fun cancel(): PayResource {
            return PayResource(PayStatus.CANCEL, null)
        }

    }
}

enum class PayStatus {
    SUCCESS,
    FAILED,
    CANCEL,
}
