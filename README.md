[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![](https://jitpack.io/v/limuyang2/FastPay.svg)](https://jitpack.io/#limuyang2/FastPay)  

[RxJava版本](https://github.com/limuyang2/FastPayRx)

# FastPay
**（LiveData版本）**一个集成微信、支付宝、银联支付的小巧库，本库采用LiveData响应数据，具有LiveData的所有优点，例如生命周期的感知。  
本库不提供任何逻辑，主要是提供库的集成，省去配置时间，以简单、轻量为主，不做过度封装。  
三个支付平台库相互独立，可根据项目，单独使用，避免臃肿

## 获取 
先在 build.gradle 的 repositories 添加仓库：  
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
在dependencies中添加：  
> 最新版本[![](https://jitpack.io/v/limuyang2/FastPay.svg)](https://jitpack.io/#limuyang2/FastPay)
```gradle
dependencies {
    //（根据需要，选择对应包导入）
    //支付宝
    implementation 'com.github.limuyang2.FastPay:alipaylibrary:0.1'
    //微信
    implementation 'com.github.limuyang2.FastPay:wechatpaylibrary:0.1'
    //银联
    implementation 'com.github.limuyang2.FastPay:unionpaylibrary:0.1'
}
```
## 使用 
微信  
![](https://github.com/limuyang2/FastPay/blob/master/pic/wxtips.png)  
银联  
![](https://github.com/limuyang2/FastPay/blob/master/pic/uniontips.png)  
* 根据以上官方文档所示，移动端属于不安全端，数据容易被篡改，所以不能以客户端返回结果作为用户支付的结果  

> **(重要) 使用建议：```onSuccess```和```onFailed```方法可以不用重写，所有的结果均在```onComplete```中去进行服务器查询**  
> 以下示例为kotlin。唯一区别在于java实例化Observer时，请在"xxObserver"前加上"Java"，例如：WxPayObserver，java使用"JavaWxPayObserver"
>
> [JavaDemoActivity](https://github.com/limuyang2/FastPay/blob/master/app/src/main/java/top/limuyang2/livedatapay/JavaDemoActivity.java)

#### 微信使用  
```kotlin
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
        //成功，微信app返回的结果。
        //(可以不重写；如果重写，建议仅作为调试打印Log使用)
        //注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
    }

    override fun onFailed(message: String) {
        //失败，微信app返回的结果。
        //(可以不重写；如果重写，建议仅作为调试打印Log使用)
        //注意一定不能以客户端返回作为用户支付的结果，应以服务器端的接收的支付通知或查询API返回的结果为准
    }

    override fun onCancel() {
        //取消
    }

    override fun onComplete() {
       // onSuccess，onFailed 都会走到这里
       // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
    }
})
```
#### 支付宝使用 
支付宝官方需要获取```android.permission.READ_PHONE_STATE```权限，建议动态获取给与
```kotlin
// …………后台服务器返回的订单数据，填入下方
val orderInfo = "app_id=2015052600090779&biz………………………………"
FastAliPay(this).pay(orderInfo, object : AliPayObserver {
    override fun onSuccess() {
        //(可以不重写；如果重写，建议仅作为调试打印Log使用)
    }

    override fun onFailed(message: String) {
        //(可以不重写；如果重写，建议仅作为调试打印Log使用)
    }

    override fun onCancel() {
    }

    override fun onComplete() {
        // onSuccess，onFailed 都会走到这里
        // 不论成功与否，都在此对服务器进行查询，一切数据，以服务器为准
    }
})
```
#### 银联支付 
```kotlin
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
```



## License
```
2018 limuyang
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```