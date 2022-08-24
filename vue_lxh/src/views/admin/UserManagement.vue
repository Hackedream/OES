<template>
<div style="padding:10px">
<!--新增-->
<div style="margin: 10px 0">
<el-button type="primary" @click="add">新增用户</el-button>
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
    <el-table-column fixed sortable prop="uid" label="用户ID" />
    <el-table-column fixed prop="utel" label="用户电话" />
    <el-table-column  prop="upassword" label="用户密码" />
    <el-table-column prop="unickname" label="用户昵称" />
    <el-table-column prop="urealname" label="用户姓名" />
    <el-table-column sortable prop="uregiTime" label="注册时间" />
    <el-table-column prop="ugender" label="用户性别" />
    <el-table-column prop="ubirth" label="用户生日" />
    <el-table-column prop="umail" label="用户邮箱" />
    <el-table-column sortable prop="ubalance" label="用户余额" />
    <el-table-column sortable prop="uwatching" label="观看时长" />
    <!--1:讲师，0：学生-->
    <el-table-column prop="utrank" label="讲师评分" />
    <el-table-column prop="ulessonNum" label="课程数量" />
     <el-table-column fixed="right" label="操作" width="120">
      <template #default>
        <!--编辑与删除-->
        <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
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
    title="新增用户"
    width="30%"
  >
    <el-form :model="form" label-width="120px">
    <el-form-item label="用户电话">
      <el-input v-model="form.utel" style="width: 80%" />
    </el-form-item>
    <el-form-item label="用户密码">
      <el-input v-model="form.upassword" placeholder="123456" style="width: 80%" />
    </el-form-item>
    <el-form-item label="用户昵称">
      <el-input v-model="form.unickname" style="width: 80%" />
    </el-form-item>
    <el-form-item label="用户姓名">
      <el-input v-model="form.urealname" style="width: 80%" />
    </el-form-item>
    <el-form-item label="注册时间">
      <el-input v-model="form.uregiTime" style="width: 80%" />
    </el-form-item>
    <el-form-item label="用户性别">
       <el-radio-group v-model="form.ugender" class="ml-4">
      <el-radio label="1">男</el-radio>
      <el-radio label="0">女</el-radio>
    </el-radio-group>
    </el-form-item>
    <el-form-item label="用户生日">
      <div class="block">
      <el-date-picker
        v-model="form.ubirth"
        type="date"
        placeholder="选择生日"
        :disabled-date="disabledDate"
        :shortcuts="shortcuts"
        style="width: 100%"
      />
      </div>
    </el-form-item>
    <el-form-item label="用户邮箱">
      <el-input v-model="form.umail" style="width: 80%" />
    </el-form-item>
    <el-form-item label="用户类型">
      <el-radio-group v-model="form.utypt" class="ml-4">
      <el-radio label="1">讲师</el-radio>
      <el-radio label="0">学生</el-radio>
      </el-radio-group>
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

export default{
  components:{
  },
  data(){
    return{
      form: {
        uid:'',
    utel:'',
    upassword:'',
    unickname:'',
    urealname:'',
    uregiTime:'',
    ugender:'',
    ubirth:'',
    umail:'',
    ubalance:'',
    uwatching:'',
    utype:'',
    utrank:'',
    utlessonNum:'',
      },
      dialogVisible: false,//弹窗可见性
      search:'',//搜索内容
      currentPage:1,//当前页码
      pageSize: 10,//每页大小
      total: 10,//数据总数
      tableData: [
         {
    uid:'1',
    utel:'16258496352',
    upassword:'123456',
    unickname:'LQQ',
    urealname:'刘清泉',
    uregiTime:'2022-8-17 12:05:08',
    ugender:'1',
    ubirth:'2022-8-17',
    umail:'sdfghjkl@qq.com',
    ubalance:'99999.99',
    uwatching:'999.9',
    utype:'1',
    utrank:'9.9',
    utlessonNum:'99',
  },
 {
    uid:'2',
    utel:'16258496352',
    upassword:'123456',
    unickname:'LQQ',
    urealname:'刘清泉',
    uregiTime:'2022-8-17 12:05:08',
    ugender:'1',
    ubirth:'2022-8-17',
    umail:'sdfghjkl@qq.com',
    ubalance:'99999.99',
    uwatching:'999.9',
    utype:'1',
    utrank:'9.9',
    utlessonNum:'99',
  }
      ],//数据表
    }
  },
  //页面加载
  created(){
    this.load()
  },
  methods:{
    //查询
    load(){
      request.get("/user",{
        params:{//get请求传递参数不能为对象
        pageNum: this.currentPage,
        pageSize: this.pageSize,
        search: this.search
      }}).then(res=>{
        this.tableData = res.data.records//数据渲染
        this.total = res.data.total//总条数
        
      })
    },
    //新增用户
    add(){
      this.dialogVisible = true;//打开弹窗
      this.form = {};//清空表单
    },
    //新增保存
    save(){
      if(this.form.uid){//更新
      request.put("/user",this.form).then(res=>{
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
      request.post("/user",this.form).then(res=>{
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
      request.delete("/user/" + id).then(res=>{
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