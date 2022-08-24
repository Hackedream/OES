<template>
    <div style="height:60px; background-color:#C9E4F6; line-height:50px; border-bottom: 1px; display: flex">
    <div style="width:300px; padding-left: 20px; font-weight: bold; color: #1043B4; font-size: large;">
    <el-image style="width: 20%; height: 100%;vertical-align: middle;" :src= "'https://raw.githubusercontent.com/Mooyi646/ImageSaver/main/202208231917926.png'" />
    在 线 教 育 系 统
    </div>
    <div style="flex:1;"></div>
    <!--搜索框-->
    <div style="width: 25%; margin: auto">
    <div class="demo-input-size">
    <Input v-model="searchContent" clearable search enter-button placeholder="请输入搜索内容" @click="router.push('/user/search')" />
    </div>
    </div>
    <!--消息提示-->
    <div style="width: 70px; color: #1043B4; margin: 2px 10px;display: inline !important; " >
      <el-badge :value="messageCount"  max="99">
    <el-button color="#1043B4" :dark="isDark" plain icon= "messageBox"></el-button>
  </el-badge>
    </div>
  <!--个人中心-->
    <div style="padding-right: 15px;">
    <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar style="vertical-align: middle;" :size="30" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      {{unickName}}
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>我的课程</el-dropdown-item>
        <el-dropdown-item @click="this.$router.push('/login')">退出账户</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
    </div>
    </div>
    <router-view style="flex:1" />

</template>

<script>

export default{
  component:{
  },
  data(){
    return{
      searchContent:'',//搜索框内容
      messageCount:'10',//消息条数
      unickName:'User',//用户昵称
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
.user{
  width: 180px; 
  color: gray; 
  padding-right:15px;
}
</style>
