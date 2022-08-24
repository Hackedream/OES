<template>
<div>
<div style="width:40%; padding-top:10px; margin: auto">
    <va-card>
        <va-card-title><va-icon style="color:#9BC7FF" name="account_balance_wallet"></va-icon> 余额提现</va-card-title>
        <va-card-content>当前余额：<span>￥{{ubalance}}</span></va-card-content>
        <va-card-content >提现金额：<va-input :rules="[(v) => v<= parseFloat(ubalance) ||`提现金额应小于余额`]" style="width: 50%" v-model="withdrawMoney" @change="check" placeholder="请输入提现金额"></va-input> </va-card-content>
        <va-card-content>到账银行卡：<va-input :rules="[(v) => v.length>3 ||`请输入正确的卡号`]" style="width: 50%" v-model="withdrawCard" @change="check" placeholder="请输入到账卡号"></va-input></va-card-content>
        <va-card-content><el-button type="primary" :disabled=state @click="comfirmWithdraw">确认提现</el-button></va-card-content>
    </va-card>
    <div v-if="success"> 
        <va-card>
            <va-card-title>提现成功</va-card-title>
            <va-card-content>交易时间：{{successTime}}</va-card-content>
            <va-card-content>当前余额：{{balance}}</va-card-content>
        </va-card>
    </div>
</div>
</div>   
</template>

<script>

export default{
    data(){
        return{
            ubalance:'100',//用户余额
            withdrawMoney:0,//提现金额
            withdrawCard:'',
            success: 0, //提现操作是否成功
            successTime:'',//交易时间
            state: true,//是否可提现
            balance:0,//提现后余额
        }
    },
    methods:{
        //验证是否可提现
        check(){
            if((parseFloat(this.ubalance) >= parseFloat(this.withdrawMoney)) && this.withdrawCard!=''){
            this.state= false
            }
        },
        comfirmWithdraw(){
            //与后台连接余额减少
            this.success = 1
            this.successTime = new Date().toLocaleString()
            this.balance = parseFloat(this.ubalance) - parseFloat(this.withdrawMoney)

        }
    }
}

</script>
