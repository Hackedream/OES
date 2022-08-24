<template>
<div style="padding:10px">
<!--新增-->
<div style="margin: 10px 0">
<el-button type="primary" @click="add">新增课程</el-button>
</div>
<!--搜索-->
<div style="margin: 10px 0">
 <el-input v-model="search"  clearable placeholder="请输入搜索内容" style="width: 20%" />
 <el-button type="primary" style="margin-left:5px" @click="load">查询</el-button>
</div>

<div>
  <el-table 
  :data="tableData" 
  border 
  stripe
  height="250"
  size="small" 
  style="width: calc(100vw - 220px)">
    <el-table-column fixed sortable prop="lid" label="课程ID" />
    <el-table-column fixed prop="lname" label="课程名称" />
    <el-table-column  prop="lcategory" label="课程类别" />
    <el-table-column prop="lintro" label="课程简介" />
    <el-table-column prop="lcontent" label="课程详情" />
    <el-table-column sortable prop="lprice" label="课程价格" />
    <el-table-column  sortable prop="lrank" label="课程评分" />
    <el-table-column prop="ltime" label="课程时长" />
    <el-table-column prop="liscount" label="课程折扣" />
    <el-table-column sortable prop="llikeNum" label="课程点赞数" />
    <el-table-column sortable prop="lcollectNum" label="课程收藏数" />
    <el-table-column prop="lupid" label="课程上传者id" />
    <el-table-column prop="lowner" label="课程上传者" />
    <el-table-column prop="luptime" label="课程上传时间" />
     <el-table-column fixed="right" label="操作" width="120">
      <template #default>
        <!--编辑与删除-->
        <el-button type="primary" size="small" @click="handleEdit(scope.row)"
          >编辑</el-button>
        <el-popconfirm title="确认删除?" @comfirm="handleDelete(scope.row.uid)">
    <template #reference>
      <el-button type="danger">删除</el-button>
    </template>
  </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <!--数据分页-->
  <div style="margin:10px 0">
  <el-pagination 
  @size-change="handleSizeChange"
  @current-change="handleCurrentChange"
  :current-page="currentPage"
  :page-sizes="[5,10,20,40]"
  :page-size="pageSize"
  layout="total, sizes, prev, pager, next, jumper" 
  :total="total" />
  </div>

<!--弹窗-->
  <el-dialog
    v-model="dialogVisible"
    title="新增课程"
    width="30%"
  >
    <el-form :model="form" label-width="120px">
    <el-form-item label="课程名称">
      <el-input v-model="form.lname" style="width: 80%" />
    </el-form-item>
    <el-form-item label="课程类别">
  <el-select v-model="form.lcategory" class="m-2" placeholder="Select" size="medium">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
    />
  </el-select>
    </el-form-item>
    <el-form-item label="课程简介">
      <el-input v-model="form.lintro" style="width: 80%" />
    </el-form-item>
    <el-form-item label="课程详情">
      <el-input v-model="form.lcontent" style="width: 80%" />
    </el-form-item>
    <el-form-item label="课程价格">
      <el-input v-model="form.lprice" style="width: 80%" />
    </el-form-item>
    <el-form-item label="课程折扣">
      <el-input v-model="form.liscount" style="width: 80%" />
    </el-form-item>
    <el-form-item label="课程上传者ID">
      <el-input v-model="form.lupid" style="width: 80%" />
    </el-form-item>
    <el-form-item label="课程上传时间">
      <el-input v-model="form.luptime" style="width: 80%" />
    </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </span>
    </template>
  </el-dialog>
</div>
</div> 
</template>

<script>
import request from '@/utils/request';
import { ref } from 'vue'

const value = ref('')

const options = [
  {
    value: '0',
    label: '语文',
  },
  {
    value: '1',
    label: '数学',
  },
  {
    value: '2',
    label: '英语',
  },
  {
    value: '3',
    label: '物理',
  },
  {
    value: '4',
    label: '化学',
  },
  {
    value: '5',
    label: '生物',
  },
  {
    value: '6',
    label: '政治',
  },
  {
    value: '7',
    label: '历史',
  },
  {
    value: '8',
    label: '地理',
  },
  {
    value: '9',
    label: '计算机',
  },
]
export default{
  components:{
  },
  data(){
    return{
      form: {},
      dialogVisible: false,//弹窗可见性
      search:'',//搜索内容
      currentPage:1,//当前页码
      pageSize: 10,//每页大小
      total: 10,//数据总数
      options,//课程类别枚举集合
      tableData: [],//数据表
    }
  },
  //页面加载
  created(){
    this.load()
  },
  methods:{
    //查询
    load(){
      request.get("/lesson",{
        params:{//get请求传递参数不能为对象
        pageNum: this.currentPage,
        pageSize: this.pageSize,
        search: this.search
      }}).then(res=>{
        this.tableData = res.data.records//数据渲染
        this.total = res.data.total//总条数
        
      })
    },
    //新增课程
    add(){
      this.dialogVisible = true;//打开弹窗
      this.form = {};//清空表单
    },
    //新增保存
    save(){
      if(this.form.lid){//更新
      request.put("/lesson",this.form).then(res=>{
        console.log(res)
        if(res.code === '0'){
        this.$message({
          type: "success",
          message: "更新成功"
        })
        } else {
          this.$message({
          type: "error",
          message: "res.msg"
        })
        }
        this.load()//刷新表格数据
        this.dialogVisible = false
      })
      } else{//新增
      //请求后台，后台接口+参数
      request.post("/lesson",this.form).then(res=>{
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
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDelete(id){
      console.log(id)
      request.delete("/lesson/" + id).then(res=>{
         if(res.code === '0'){
        this.$message({
          type: "success",
          message: "删除成功"
        })
        } else {
          this.$message({
          type: "error",
          message: "res.msg"
        })
        }
        this.load()//删除后重新加载表格数据
      })
    },
    handleSizeChange(pageSize){//改变每页数量
    this.pageSize =  pageSize
    this.load()
    },
    handleCurrentChange(pageNum){//改变当前页码
    this.currentPage = pageNum
    this.load()
    },
  },
}
</script>