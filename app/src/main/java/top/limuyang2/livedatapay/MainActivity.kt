package top.limuyang2.livedatapay

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tencent.mm.opensdk.modelpay.PayReq
import kotlinx.android.synthetic.main.activity_main.*
import top.limuyang2.wechatpaylibrary.FastWxPay
import top.limuyang2.wechatpaylibrary.WxPayObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testBtn.setOnClickListener {
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
                    Log.d("wxPay", "onSuccess")
                }

                override fun onFailed(message: String) {
                    Log.d("wxPay", "onFailed : $message")
                }

                override fun onCancel() {
                    Log.d("wxPay", "onCancel")
                }

                override fun onComplete() {

                }
            })

            //            val str = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D"
            //            FastAliPay(this).pay(str, object : AliPayObserver {
            //                override fun onSuccess() {
            //
            //                }
            //
            //                override fun onFailed(message: String) {
            //                    Log.d("FastAliPay", message)
            //                }
            //
            //                override fun onCancel() {
            //                }
            //
            //                override fun onComplete() {
            //
            //                }
            //            })

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
//            FastUnionPay(this).pay(UnionPayType.TEST, "869278591167656016600", object : UnionPayObserver {
//                override fun onSuccess() {
//                    Log.d("FastUnionPay", "onSuccess")
//                }
//
//                override fun onFailed(message: String) {
//                    Log.d("FastUnionPay", message)
//                }
//
//                override fun onCancel() {
//                    Log.d("FastUnionPay", "onCancel")
//                }
//
//                override fun onComplete() {
//                    Log.d("FastUnionPay", "onComplete")
//                }
//            })
        }
    }
}
