<template>
    <!--用户购买课程订单界面-->
    <div>
      <div style="margin: 20px">
        <span style="color:#1043B4; font-weight: bold;font-size:large;"><el-icon><shoppingCartFull /></el-icon>课程购买</span>
        <el-button type="primary" text @click="this.$router.push('/home')"><ins>退出</ins></el-button>
      </div>
     <div class="flex md6 lg4" style="margin: auto; width:40%">
      <va-card color="#004BAC" gradient >
        <va-card-title>订单信息</va-card-title>
        <va-card-content>课程名称：<span>{{form.olessonName}}</span></va-card-content>
        <va-card-content>讲师姓名：<span>{{form.osalerName}}</span></va-card-content>
        <va-card-content>课程价格：<span>{{form.olessonPrice}}</span></va-card-content>
        <va-card-content>课程折扣：<span>{{form.oliscount}}</span></va-card-content>
        <va-card-content>购买人姓名：<span>{{form.obuyerName}}</span></va-card-content>
        <va-card-content>订单创建时间：<span>{{form.ocreateTime}}</span></va-card-content>
      </va-card>
    </div>
    <div class="flex md6 lg4" style="margin: auto;width:40%">
        <va-card> 
        <va-card-title>支付信息</va-card-title>
        <va-card-content>需支付金额：<span style="color:#37C2FD; font-weight:bold"> {{payment}}</span></va-card-content>
        <va-card-content>
        <el-button type="primary" @click="confirmPay">确认支付</el-button>
        </va-card-content>
      </va-card>
    </div>
    <div class="flex md6 lg4" style="margin: auto;width:40%" v-show= form.ostate >
    <va-card>
        <va-card-title>支付成功</va-card-title>
        <va-card-content>订单完成时间：<span>{{form.ocompleteTime}}</span></va-card-content>
        <va-card-content>账户余额：<span>{{ubalance}}</span></va-card-content>
        <va-card-content><el-button type="primary" text @click="this.$router.push('/user/myCourse')">我的课程列表</el-button></va-card-content>
    </va-card>
    </div>
    </div>
    </template>

<script>
import { callbackify } from 'util'

export default{
data(){
    return{
        form: {
            olessonName:'',
            osalerName:'',
            olessonPrice:'',
            oliscount:'',
            obuyerName:'',
            ocreateTime: new Date().toLocaleString(),
            ostate: 0,
            ocompleteTime:'',
        }, //order表
        payment: '',
        ubalance:'',
    }
},
methods: {
    confirmPay(){
        //付款状态，后台code

        this.form.ocompleteTime = new Date().toLocaleString();

        //订单状态传给后台

        //余额减少，传给后台
        this.form.ostate = 1;//成功支付
        //将课程加入我的课程列表，后台
    }
}
}
</script>
