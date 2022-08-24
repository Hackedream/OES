<template>
    <div>
       <el-row class="tac">
      <el-menu
      router
        :default-active="this.$route.path"
        style="width:200px; min-height: calc(100vh - 50px);"
        class="el-menu-vertical-demo"
      >
        <el-menu-item index="/admin/userManagement"><el-icon><user /></el-icon> 用户管理</el-menu-item>
        <el-sub-menu index="lesson">
          <template #title>
            <el-icon><notebook /></el-icon>
            <span>课程管理</span>
          </template>
        <el-menu-item-group title="课程管理">
            <el-menu-item index="/admin/lessonManagement">课程数据</el-menu-item>
            <el-menu-item index="/admin/lessonCheckout">课程审核</el-menu-item>
            </el-menu-item-group></el-sub-menu>

        <el-menu-item index="/admin/announceManagement"  >
          <el-icon><setting /></el-icon>
          <span>公告管理</span>
        </el-menu-item>
      </el-menu>
    </el-row>
    </div>
</template>

<script>

export default{
  create(){
    console.log(this.$route.path)
  },
  data(){
    return {
      path: this.$route.path//设置默认高亮的菜单
    }
  }
}

</script>
