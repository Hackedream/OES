<template>
<!--管理员header-->
    <div style="height: 50px; line-height:50px; border-bottom: 1px; display: flex">
    <div style="width:300px; padding-left: 30px; font-weight: bold; color: #1043B4;font-size:larger;">在线教育系统后台管理</div>
    <div style="flex:1;"></div>
    <div style="width:10px;"></div>
    <!--消息提示-->
    <div style="width: 70px; color: #1043B4; margin: 2px 10px;display: inline !important; " >
      <el-badge :value="messageCount"  max="99">
    <el-button color="#1043B4" :dark="isDark" plain icon= "messageBox"></el-button>
  </el-badge>
    </div>
  <!--个人中心-->
    <div class="admin">
    <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar :size="30" icon="User" />
      管理员
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="this.$router.push('/login')">退出账户</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
    </div>
    </div>

</template>

<script>
import { reactive, toRefs } from 'vue'
import { ref } from 'vue'


export default{
  data(){
    return{
      messageCount:'',//消息条数
    }
  }
}


</script>
<style scoped>
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
.item {
  margin-top: -10px;
  margin-right: -10px;
}
.el-dropdown {
  margin-top: 1.1rem;
}
.admin{
  width: 180px; 
  color: gray; 
  padding-right:15px;
}
</style>
