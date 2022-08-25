<template>
    <div class="content-register">
          <div class="content-box">
              <div class="content-login">
                     <div class="content-login-info">
                         <div class="content-title">Register</div>
                         <van-form ref="registerForm">
                         <div style="margin:25px 0px">
                             <van-field
                            v-model="username"
                            name="用户名"
                            label="用户名"
                            placeholder="用户名"
                            :rules="[{ required: true, message: '请填写用户名' }]"
                          />
                         </div>
                         <van-field
                            v-model="password"
                            type="password"
                            name="密码"
                            label="密码"
                            placeholder="密码"
                            :rules="[{ required: true, message: '请填写密码' }]"
                          />
                          <div style="margin:25px 0px">
                          <van-field
                            v-model="ispassword"
                            type="password"
                            name="确认密码"
                            label="确认密码"
                            placeholder="确认密码"
                            :rules="[{ required: true, message: '请填写确认密码' }]"
                          />
                          </div>
                         <div style="margin: 26px;">
                             <van-button round block type="primary" @keyup.enter.native="loginBtn" @click="registerBtn">注册</van-button>
                         </div>
                   </van-form>
                     </div>
              </div>
          </div>
    </div>
</template>
<script>
export default {
    data(){
        return {
              username: '',
              password: '',
              ispassword:'',
              userinfoList:[],
              loginData:[]
        }
    },
    methods:{
         registerBtn() {
             this.$refs.registerForm.validate().then(()=>{
                 if(this.password===this.ispassword){
                       let obj={}
                       obj.name=this.username
                       obj.password=this.password
                       this.userinfoList.push(obj)
                       localStorage.setItem('userinfo',JSON.stringify(this.userinfoList))
                       this.$toast.success('恭喜你注册成功!')
                       this.$router.push({name:'login'})
                 }else{
                        this.$toast.fail('两次密码不一致!')
                 }
             })
        },
    }
}
</script>
<style>
 .content-register{
     width: 100%;
     height: 711px;
     background-image: url('~@/assets/img/login.jpg');
     background-size:cover ;
     display: flex;
     justify-content: center;
     align-items: center;
 }
 .content-title{
     text-align: center;
     font-size:25px;
     color:#fff
 }
 /* .content-box{
     width: 550px;
     height: 400px;
     background: rgba(223,219,219,0.3);
     display: flex;
     justify-content: center;
     align-items: center;
 } */
 .content-login{
     width: 400px;
     height: 350px;
     background: rgba(223,219,219,0.2);
     display: flex;
     border-radius: 5px;
     justify-content: center;
     align-items: center;
     box-shadow: 0 25px 35px rgba(0,0,0,0.8);
 }
 .content-login-info{
     width: 90%;
 }
</style>
