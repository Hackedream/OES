<template>
    <!--讲师上传课程审核-->
    <div style="margin: 10px">
    <el-table :data="tableData" border stripe max-height="250" show-header style="width: calc(100vw - 220px)">
    <el-table-column prop="lid" label="课程ID" />
    <el-table-column prop="lname" label="课程名称" />
    <el-table-column prop="lupid" label="讲师ID" />
    <el-table-column prop="lowner" label="讲师姓名" />
    <el-table-column prop="lprice" label="课程价格" />
    <el-table-column prop="liscount" label="课程折扣" />
    <el-table-column prop="lcontent" label="课程详情" width="120">
        <template #default>
        <el-button type="primary" text link @click="this.$router.push('/admin/ldetails')">查看详情</el-button>
        </template>
    </el-table-column>
    <el-table-column prop="pass" fix="right" label="审核意见">
        <template #default>
            <el-button-group>
                <el-button type="primary" @click="pass"><el-icon><Check /></el-icon>通过</el-button>
                <el-button type="danger" @click="back"><el-icon><Close /></el-icon>退回</el-button>
                </el-button-group>
        </template>
    </el-table-column>
  </el-table>
    </div>
</template>

<script>
import { computed, ref } from 'vue'
import request from '@/utils/request'


export default{
    data(){
        return{
            tableData: [{
                lid: '1',
                lname:'语文',
                lupid:'1',
                lowner: '吴老师',
                lprice: '200',
                liscount: '0.8',
                lintro: '一门语文课',
            },
            {
                lid: '2',
                lname: '数学',
                lupid: '1',
                lowner: '吴老师',
                lprice: '100',
                liscount: '0',
                lintro: '无',
            }
            ],
            form: {
                caid: '',//审核管理员id
                clid: this.lid,//课程Id
                cuid: this.lupid,//讲师Id
                ctime: new Date(),
                cstate:'',
            }//审核结果表单
        }
    },
    methods:{
        load(){
      request.get("/lesson",{}).then(res=>{
        this.tableData = res.data.records//数据渲染
      })
    },
        //审核通过
        pass(){
            this.form.cstate = 1;//审核通过
            request.post("/checklist",this.form).then(res=>{
        console.log(res)
        //新增成功弹窗提示
        if(res.code === '0'){
        this.$message({
          type: "success",
          message: "新增成功"
        })
        } else {
          this.$message({
          type: "error",
          message: "res.msg"
        })
        }
      });
        },
        //审核退回
        back(){
            this.form.cstate = 0;
            request.post("/checklist",this.form).then(res=>{
        console.log(res)
        //新增成功弹窗提示
        if(res.code === '0'){
        this.$message({
          type: "success",
          message: "新增成功"
        })
        } else {
          this.$message({
          type: "error",
          message: "res.msg"
        })
        }
      });
        }
    }
}

</script>