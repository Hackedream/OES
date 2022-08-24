<template>
    <div>
        <!--头部-->
    
  <div>
    <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span style="color:#1043B4; font-weight: bold;font-size:large;"><el-icon><coin /></el-icon>账户充值</span>
        <el-button type="primary" text @click="this.$router.push('/home')"><ins>退出</ins></el-button>
      </div>
    </template>
    <el-card shadow="never"><p style="background-color:#DEE0E0;">账户余额：<span style="color: #37C2FD">{{ubalance}}</span></p></el-card>
    <el-card shadow="hover">
        <span>充值金额：</span>
        <div>
    <el-radio-group v-model="topNum" @change="check" >
      <el-radio label="10" size="large" border>￥10</el-radio>
      <el-radio label="50" size="large" border>￥50</el-radio>
      <el-radio label="100" size="large" border>￥100</el-radio>
      <el-radio label="200" size="large" border>￥200</el-radio>
      <el-radio label="500" size="large" border>￥500</el-radio>
      <el-input v-model="topNum" style="width: 20%" placeholder="请输入充值金额">
      <template #preffix>
        ￥
      </template>
      </el-input>
    </el-radio-group>
  </div>
    </el-card>
    <el-card shadow="never">
        应支付金额：<span style="color: #37C2FD">￥{{topNum}}</span>
    </el-card>
    <el-card shadow="never">
        支付方式 ：<el-image style="width:50%" src="https://raw.githubusercontent.com/Mooyi646/ImageSaver/main/202208240143370.png"></el-image>
        <!--支付方式应付版，，-->
        <div>
          <el-radio-group v-model="payway" @change="check">
          <el-radio-button border label="支付宝"></el-radio-button>
          <el-radio-button label="微信支付"></el-radio-button>
          </el-radio-group>
        </div>
    </el-card>
    <div style="margin-left:50%; margin-top:30px">
        <el-button size="large" type="primary" @click="pay" :disabled=disabled>确认充值</el-button>
    </div>
  </el-card>
  </div>
  <!--支付弹窗-->
  <div>
    <el-dialog
    v-model="dialogVisible"
    title="支付"
    width="30%"
  >
    <span>{{QRCode}}</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
      </span>
    </template>
  </el-dialog>
  </div>
    </div>
</template>

<script>
//import Header from '@/components/Header'


export default {
  components:{
    //Header,
  },
  data(){
    return{
        ubalance:'100',//账户余额
        topNum: '',//充值金额
        payway: '',//支付方式
        QRCode:'',//支付二维码
        dialogVisible: false,//支付弹窗可见性
        disabled: true,//按钮可用性
    }
  },
  methods :{
    check(){
      if(this.topNum!='' && this.payway!=''){
    this.disabled = false
    }
    },
   pay(){
    //调用后端接口，用户余额增加
    
    this.dialogVisible = true
   }
  }
}
</script>
