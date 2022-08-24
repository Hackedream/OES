<template>
    <!--公告管理-->
    <div style="margin: 10px">
    <el-tabs value="0" class="demo-tabs">
    <el-tab-pane label="已过期公告" name="0">
        <template #default>
            已过期
        </template>
    </el-tab-pane>
    <el-tab-pane label="当前公告" name="1">Config</el-tab-pane>
    <el-tab-pane label="添加公告" name="2">Role</el-tab-pane>
  </el-tabs>
    </div>
</template>

<script>
import { ref } from 'vue'

export default{
    data(){

    },
    methods:{
        handleClick(){

        }
    }
}

</script>

<style>
</style>