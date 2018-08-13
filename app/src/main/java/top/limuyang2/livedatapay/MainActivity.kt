package top.limuyang2.livedatapay

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tencent.mm.opensdk.modelpay.PayReq
import kotlinx.android.synthetic.main.activity_main.*
import top.limuyang2.alipaylibrary.AliPayObserver
import top.limuyang2.alipaylibrary.FastAliPay
import top.limuyang2.unionpaylibrary.FastUnionPay
import top.limuyang2.unionpaylibrary.UnionPayObserver
import top.limuyang2.unionpaylibrary.UnionPayType
import top.limuyang2.wechatpaylibrary.FastWxPay
import top.limuyang2.wechatpaylibrary.WxPayObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testBtn.setOnClickListener {

            // …………后台服务器返回的订单数据，填入下方
            val request = PayReq()
            request.appId = "wx7e16cf49c52635e2"
            request.partnerId = "1900000109"
            request.prepayId = "1101000000140415649af9fc314aa427"
            request.packageValue = "Sign=WXPay"
            request.nonceStr = "1101000000140429eb40476f8896f4c9"
            request.timeStamp = "1398746574"
            request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B"

            FastWxPay("wx7e16cf49c52635e2", this).pay(request, object : WxPayObserver {
                override fun onSuccess() {
                    //成功，微信app返回的结果。注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
                }

                override fun onFailed(message: String) {
                    //失败，微信app返回的结果。注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
                }

                override fun onCancel() {
                    //取消
                }

                override fun onComplete() {
                    // onSuccess，onFailed 都会走到这里
                    // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
                }
            })

            // …………后台服务器返回的订单数据，填入下方
            val orderInfo = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D"
            FastAliPay(this).pay(orderInfo, object : AliPayObserver {
                override fun onSuccess() {
                }

                override fun onFailed(message: String) {
                }

                override fun onCancel() {
                }

                override fun onComplete() {
                    // onSuccess，onFailed 都会走到这里
                    // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
                }
            })

            /**
             * 银联 测试账号
             * 招商银行借记卡:6226090000000048
             * 手机号:18100000000
             * 密码:111101
             * 短信验证码:123456(先点获取验证码之后再输入)
             * 证件类型:01 身份证
             * 证件号:510265790128303
             * 姓名:张三
             */
            // …………后台服务器返回的 银联tn 数据，填入下方
            val tn = "869278591167656016600"
            // UnionPayType.TEST 为银联测试环境；UnionPayType.RELEASE 为银联正式环境
            FastUnionPay(this).pay(UnionPayType.TEST, tn, object : UnionPayObserver {
                override fun onSuccess() {
                    Log.d("FastUnionPay", "onSuccess")
                }

                override fun onFailed(message: String) {
                    Log.d("FastUnionPay", message)
                }

                override fun onCancel() {
                    Log.d("FastUnionPay", "onCancel")
                }

                override fun onComplete() {
                    // onSuccess，onFailed 都会走到这里
                    // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
                }
            })
        }
    }
}
