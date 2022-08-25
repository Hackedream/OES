<template>
    <div class="content">
          <div class="content-box">
             <vue-particles
                   class="login-bg"
                   color="#00ff00"
                   :particleOpacity="0.7"
                   :particlesNumber="100"
                   shapeType="circle"
                   :particleSize="4"
                   linesColor="#00ffff"
                   :linesWidth="2"
                   :lineLinked="true"
                   :lineOpacity="0.4"
                   :linesDistance="150"
                   :moveSpeed="3"
                   :hoverEffect="true"
                   hoverMode="grab"
                   :clickEffect="true"
                   clickMode="push"
              />
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-position="left" label-width="0px"
                           class="demo-ruleForm login-container" status-icon>
                    <h3 class="title">登录</h3>
                    <el-form-item prop="account">
                      <el-input type="text" v-model="ruleForm.account" auto-complete="off" placeholder="账号"
                                id="loginEmail"></el-input>
                    </el-form-item>
                    <el-form-item prop="checkPass">
                      <el-input type="password" v-model="ruleForm.checkPass" auto-complete="on" placeholder="密码"
                                id="loginPassword"></el-input>
                      <label id="showPasswordToggle">
                        <el-checkbox v-model="checked" id="showPasswordCheck">显示密码</el-checkbox>
                      </label>
                      <router-link to="/" style="float: right; color: #bbbbbb">忘记密码？</router-link>
                    </el-form-item>
                    <el-form-item style="width:100%;">
                      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit" :loading="logining">
                        登录
                      </el-button>
                    </el-form-item>
                    <el-form-item style="width:100%;">
                      <router-link to="/register">
                        <el-button style="width:100%;">
                          注册
                        </el-button>
                      </router-link>
                    </el-form-item>
                  </el-form>
    </div>
    </div>
</template>
<script>
import sha256 from 'crypto-js/sha256'
import { requestLogin } from '@/api/user'

export default {
  name: 'app-login',
  data () {
    return {
      logining: false,
      fromUrl: '/',
      ruleForm: {
        account: 'admin',
        checkPass: '123456'
      },
      rules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        checkPass: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      checked: false
    }
  },
  methods: {
    handleSubmit (ev) {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.logining = true
          const loginParams = { username: this.ruleForm.account, password: sha256(this.ruleForm.checkPass) }
          requestLogin(loginParams).then(data => {
            this.logining = false
            this.$message({
              message: '登录成功！',
              type: 'success'
            })
            this.$router.push(this.fromUrl)
          }).catch(err => {
            this.logining = false
            console.log(err)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      if (from.fullPath !== '/register' && !from.meta.errorPage) {
        vm.fromUrl = from.fullPath
      }
    })
  }
}

</script>


<style>
 .content{
     width: 100%;
     height: 711px;
     background-image: url('~@/assets/img/login2.png');
     background-size:cover ;
     display: flex;
     justify-content: center;
     align-items: center;
 }
 .login-bg{
     position:absolute;
     top:0;
     left: 0;
     width: 100%;
     height: 100%;
 }
 .content-title{
     text-align: center;
     font-size:25px;
     color:#5555ff
 }
 .content-login{
     position: fixed;
     top:26%;
     left:36.5%;
     width: 400px;
     height: 300px;
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
 .content-bottom{
     display: flex;
     justify-content: space-between;
     color: blue;
     font-size:14px
 }
 .content-bottom :hover{
      cursor: pointer;
 }
 .title {
   text-align: center;
   margin-bottom: 15px;
 }

 .page {
   background-color: #eff3f4;
   position: absolute;
   width: 100%;
   height: 100%;
   font-size: 16px;
   font-family: 'Source Sans Pro', sans-serif;
   font-weight: 400;
   -webkit-font-smoothing: antialiased;
 }

 .login-box {
   position: absolute;
   top: 50%;
   left: 50%;
   -webkit-transform: translate(-50%, -50%);
   transform: translate(-50%, -50%);
   display: block;
   width: 100%;
   max-width: 400px;
   background-color: #FFF;
   margin: 0;
   padding: 2.25em;
   box-sizing: border-box;
   border: solid 1px #DDD;
   border-radius: .5em;
   font-family: 'Source Sans Pro', sans-serif;
 }
</style>
