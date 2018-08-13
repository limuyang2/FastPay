package top.limuyang2.livedatapay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelpay.PayReq;

import org.jetbrains.annotations.NotNull;

import top.limuyang2.alipaylibrary.FastAliPay;
import top.limuyang2.alipaylibrary.JavaAliPayObserver;
import top.limuyang2.unionpaylibrary.FastUnionPay;
import top.limuyang2.unionpaylibrary.JavaUnionPayObserver;
import top.limuyang2.unionpaylibrary.UnionPayType;
import top.limuyang2.wechatpaylibrary.FastWxPay;
import top.limuyang2.wechatpaylibrary.JavaWxPayObserver;

/**
 * @author limuyang
 * @date 2018/8/13
 * @class describe
 */
public class JavaDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* 微信 */

        // …………后台服务器返回的订单数据，填入下方
        PayReq request = new PayReq();
        request.appId = "wx7e16cf49c52635e2";
        request.partnerId = "1900000109";
        request.prepayId = "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay"; //固定值
        request.nonceStr = "1101000000140429eb40476f8896f4c9";
        request.timeStamp = "1398746574";
        request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        new FastWxPay("wx7e16cf49c52635e2", this).pay(request, new JavaWxPayObserver() {
            @Override
            public void onSuccess() {
                //成功，微信app返回的结果。注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
            }

            @Override
            public void onFailed(@NotNull String message) {
                //失败，微信app返回的结果。注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
            }

            @Override
            public void onCancel() {
                //取消
            }

            @Override
            public void onComplete() {
                // onSuccess，onFailed 都会走到这里
                // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
            }

        });


        /* 支付宝 */
        // …………后台服务器返回的订单数据，填入下方
        String orderInfo = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D";
        new FastAliPay(this).pay(orderInfo, new JavaAliPayObserver() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onComplete() {
                // onSuccess，onFailed 都会走到这里
                // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
            }
        });

        /* 银联 */
        // …………后台服务器返回的 tn 数据，填入下方
        String tn = "869278591167656016600";
        new FastUnionPay(this).pay(UnionPayType.TEST, tn, new JavaUnionPayObserver() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onComplete() {
                // onSuccess，onFailed 都会走到这里
                // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
            }
        });
    }
}
