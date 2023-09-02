<template>
  <!-- 查询条件 -->
  <el-form :inline="true" :model="queryParams">
    <el-form-item label="实验室编号">
      <el-input v-model="queryParams.labNum" placeholder="请输入实验室编号" clearable />
    </el-form-item>
    <el-form-item label="申请状态">
      <el-select v-model="queryParams.applyStatus" placeholder="请选择" clearable>
        <el-option v-for="item in applyStatusOptions" :label="item.label" :value="item.value" :key="item.value" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" :icon="Search" @click="handleQuery">查询</el-button>
      <el-button :icon="Refresh" @click="handleReset">重置</el-button>
    </el-form-item>
  </el-form>
  <!-- 查询条件 -->

  <!-- 工具栏 -->
  <el-row class="mb-4">
    <el-button :icon="Plus" type="primary" @click="dialogFormVisible = true">新增</el-button>
    <el-button :icon="Download" type="success" @click="handleExport">导出</el-button>
  </el-row>
  <!-- 工具栏 -->
<!-- 提交弹出框 表单 -->
  <el-dialog v-model="dialogFormVisible" title="信息表单" width="30%">
    <el-form ref="dialogForm" :rules="rules" :model="form">
      <el-form-item label="实验室id" prop="labId" v-show="false">
        <el-input v-model="form.labId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="实验室编号" prop="labNum" :label-width="formLabelWidth">
        <el-input v-model="form.labNum" autocomplete="off" />
      </el-form-item>
	  <el-form-item label="负责老师" prop="userId" :label-width="formLabelWidth">
	    <el-input v-model="form.userId" autocomplete="off" />
	  </el-form-item>
      <el-form-item label="申请天数" prop="applyDay" :label-width="formLabelWidth">
        <el-input disabled v-model="form.applyDay" autocomplete="off" />
      </el-form-item>
      <el-form-item label="申请原因" prop="applyReason" :label-width="formLabelWidth">
        <el-input disabled v-model="form.applyReason" autocomplete="off" />
      </el-form-item>
      <el-form-item label="申请时间" prop="applyDatetime" :label-width="formLabelWidth">
        <el-date-picker disabled v-model="form.applyDatetime" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss"
          type="datetime" placeholder="请选择" />
      </el-form-item>
      <el-form-item  label="审批状态" prop="applyStatus" :label-width="formLabelWidth">
        <el-select disabled v-model="form.applyStatus" placeholder="请选择">
          <el-option v-for="item in applyStatusOptions"  :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item  label="审批意见" prop="reviewComments" :label-width="formLabelWidth">
        <el-input disabled v-model="form.reviewComments" autocomplete="off" />
      </el-form-item>
      <el-form-item label="审批时间" prop="reviewDatetime" :label-width="formLabelWidth">
        <el-input disabled v-model="form.reviewDatetime" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleSubmit">提交</el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 弹出框 表单 -->
  <!-- 列表 -->
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="labId" label="实验室id" width="80" />
    <el-table-column prop="userId" label="实验室负责老师" width="180" />
    <el-table-column prop="labNum" label="实验室编号" width="180" />
    <el-table-column prop="applyReason" label="实验室申请原因" width="180" />
    <el-table-column :formatter="formatter" prop="applyStatus" label="实验室申请状态" width="180" />
    <el-table-column prop="applyDatetime" label="实验室申请时间" width="180" />
    <el-table-column prop="reviewComments" label="实验室申请审批意见" width="180" />
    <el-table-column prop="reviewDatetime" label="实验室审批时间" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button :icon="Edit" circle type="info" @click="SubmithandleSearch(scope.$index, scope.row)" />
        <el-button :icon="Delete" circle type="danger" @click="handleDelete(scope.$index, scope.row)" />
      </template>
    </el-table-column>
  </el-table>
  <!-- 列表 -->

</template>
    
<script setup>
import { ref, reactive } from 'vue'
import request from '@/utils/request.js'
import { Delete, Edit, Search, Refresh, Plus, Download } from '@element-plus/icons-vue'
import useUserStore from '@/store/userstore.js'
import { ElMessage, ElMessageBox } from 'element-plus'



//要使用，第一步类似初始化
const userStore = useUserStore()
const userId = userStore.user.userId

//查询条件数据对象
const queryParams = reactive({
  userId: userId,
  labId: undefined,
  applyStatus: undefined,
  labNum: undefined
})
//申请状态的集合
const applyStatusOptions = [
  { value: 3, label: '待审批' },
  { value: 1, label: '审批通过' },
  { value: 2, label: '审批不通过' },
  { value: 0, label: '草稿' },
  { value: 4, label: '撤回' },
]
//格式化列
const formatter = (row, column, cellValue, index) => {
  if (cellValue === 3) {
    return '待审批'
  } else if (cellValue === 1) {
    return '审批通过'
  } else if (cellValue === 2) {
    return '审批不通过'
  }
  else {
    return ''
  }
}

//定义表格数据对象
const tableData = ref([])
//定义初始化数据方法
const getData = async () => {
  const path = '/lab/listLabs'
  console.log(queryParams)
  await request.get(path, { params: queryParams}).then(
    res => {
      //res.data是res的data对象，res.data拿到后台返回的ResultDto，res.data.data 就是ResultDto.data
      tableData.value = res.data.data
    }
  )
}
//执行初始化数据方法
getData()

//查询条件方法
const handleQuery = () => {
  getData()
}
//重置
const handleReset = () => {
  queryParams.labNum = undefined
  queryParams.applyStatus = undefined
  handleQuery()
}

//弹出框可见性，默认隐藏
const dialogFormVisible = ref(false)

const formLabelWidth = '100px'

//表单数据对象
const form = ref({
  labId: '',
  labNum: '',
  applyDay: '',
  applyReason: '',
  applyDatetime: '',
  userId: ''
})
//表单对象，用于数据校验
const dialogForm = ref(null)
//表单校验规则
const rules = {
  labNum: [{ required: true, message: '请输入实验室编号', trigger: 'blur' }],
  userId: [{ required: true, message: '请输入实验室负责老师', trigger: 'blur' }],
}

//重置表单数据
const resetFormValue = () => {
  form.value = {
    labNum: '',
	userId: '',
  }
}

//弹出框提交
const handleSubmit = () => {
  //如果labId存在，则是编辑，否则是新增
  if (form.value.labId) {
    edit()
  } else {
    add()
  }
}



//弹出框q取消
const handleCancel = () => {
  dialogFormVisible.value = false
  resetFormValue()
}

//新增
const add = () => {
	console.log("add")
	console.log(dialogForm.value)
  dialogForm.value.validate(
    (valid) => {
      if (valid) {
        //请求后台，新增申请
        const path = '/lab/insertLab'
        
        form.value.applyStatus = 0  //默认为 待审批状态
        request.post(path, form.value).then(
          res => {
            if (res.data.code === 200) {
              dialogFormVisible.value = false
              ElMessage({
                message: res.data.message,
                type: 'success',
              })
              //刷新列表
              getData()
              //重置表单数据
              resetFormValue()
            } else {
              ElMessage({
                message: res.data.message,
                type: 'error',
              })
            }
          }
        )
      } else {
        console.log('校验不通过')
      }
    }
  )
}

//编辑按钮
const handleEdit = (index, row) => {
  edit()
}

const Withdrawhandle = (index, row) => {

  ElMessageBox.confirm(
    '确认要撤回该条信息吗?',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'error',
    }
  )
    .then(() => {
      const path = '/apply/withDrawApply'
      request.get(path, { params: { applyId: row.applyId } }).then(
        res => {
          if (res.data.code === 200) {
            dialogFormVisible.value = false
            ElMessage({
              message: res.data.message,
              type: 'success',
            })
            //刷新列表
            getData()
          } else {
            ElMessage({
              message: res.data.message,
              type: 'error',
            })
          }
        }
      )
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消',
      })
    })
}


//根据applyId，获取数据
const getApplyById = (applyId) => {
  const path = '/apply/getApplyById'
  request.get(path, { params: { applyId: applyId } }).then(
    res => {
      console.log(res.data.data)
      if (res.data.code === 200) {
        form.value = res.data.data
      }
    }
  )
}

//根据labId，获取数据
const getLabById = (labId) => {
  const path = '/lab/getLabById'
  request.get(path, { params: { labId: labId } }).then(
    res => {
      console.log(res.data.data)
      if (res.data.code === 200) {
        form.value = res.data.data
      }
    }
  )
}

const SubmithandleSearch = (index, row) => {
  dialogFormVisible.value = true
  getLabById(row.labId)
}
//编辑
const edit = () => {
  dialogForm.value.validate(
    (valid) => {
      if (valid) {
        //请求后台，新增申请
        const path = '/lab/updateLab'
        form.value.userId = userId
        request.post(path, form.value).then(
          res => {
            if (res.data.code === 200) {
              dialogFormVisible.value = false
              ElMessage({
                message: res.data.message,
                type: 'success',
              })
              //刷新列表
              getData()
              //重置表单数据
              resetFormValue()
            } else {
              ElMessage({
                message: res.data.message,
                type: 'error',
              })
            }
          }
        )
      } else {
        console.log('校验不通过')
      }
    }
  )
}

//删除
const handleDelete = (index, row) => {

  ElMessageBox.confirm(
    '确认要删除该条信息吗?',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'error',
    }
  )
    .then(() => {
      const path = '/lab/deleteLabById'
      request.get(path, { params: { labId: row.labId} }).then(
        res => {
          if (res.data.code === 200) {
            dialogFormVisible.value = false
            ElMessage({
              message: res.data.message,
              type: 'success',
            })
            //刷新列表
            getData()
          } else {
            ElMessage({
              message: res.data.message,
              type: 'error',
            })
          }
        }
      )
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消',
      })
    })
}

//excel导出
const handleExport = () =>{
  ElMessageBox.confirm(
    '确认要导出数据吗?',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'info',
    }
  )
    .then(() => {
      const path = '/lab/exportLab'
      request.get(path, { params: queryParams ,responseType:'blob'}).then(
        res => {
          const link = document.createElement("a");
          const blob = new Blob([res.data],{
            type:"application/vnd.ms-excel;charset=utf-8"
          })
          link.style.display = "none"
          link.href = URL.createObjectURL(blob);
          link.setAttribute("download","实验室列表.xls");
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          ElMessage({
              message: '导出成功，请在浏览器下载查看',
              type: 'success',
            })
        }
      )
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消',
      })
    })
}
</script>
    
<style></style>