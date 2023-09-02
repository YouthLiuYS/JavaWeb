<template>
  <!-- 查询条件 -->
  <el-form :inline="true" :model="queryParams">
    <el-form-item label="设备编号">
      <el-input v-model="queryParams.facilityId" placeholder="请输入设备编号" clearable />
    </el-form-item>
    <el-form-item label="仿真设备名称">
		<el-input v-model="queryParams.facilityName" placeholder="请输入仿真设备名称" clearable />
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
  <el-dialog v-model="dialogFormVisible" title="仿真设备信息表单" width="30%">
    <el-form ref="dialogForm" :rules="rules" :model="form">
      <el-form-item label="仿真设备id" prop="facilityId" v-show="false">
        <el-input v-model="form.facilityId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="仿真设备名称" prop="facilityName" :label-width="formLabelWidth">
        <el-input v-model="form.facilityName" autocomplete="off" />
      </el-form-item>
	  <el-form-item label="仿真设备类型" prop="facilityType" :label-width="formLabelWidth">
	    <el-input v-model="form.facilityType" autocomplete="off" />
	  </el-form-item>
      <el-form-item label="所属实验室id" prop="labId" :label-width="formLabelWidth">
        <el-input v-model="form.labId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="所属实验室编号" prop="labNum" :label-width="formLabelWidth">
        <el-input v-model="form.labNum" autocomplete="off" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createDate" :label-width="formLabelWidth">
        <el-date-picker v-model="form.createDate" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss"
          type="datetime" placeholder="请选择" />
      </el-form-item>
		<el-form-item label="更新时间" prop="updateDate" :label-width="formLabelWidth">
		  <el-date-picker v-model="form.updateDate" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss"
			type="datetime" placeholder="请选择" />
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
    <el-table-column prop="facilityId" label="仿真设备id" width="80" />
    <el-table-column prop="facilityName" label="仿真设备名称" width="180" />
    <el-table-column prop="facilityType" label="仿真设备类型" width="180" />
    <el-table-column prop="labId" label="所属实验室id" width="180" />
    <el-table-column prop="labNum" label="所属实验室编号" width="180" />
    <el-table-column prop="createDate" label="创建时间" width="180" />
    <el-table-column prop="updateDate" label="更新时间" width="180"/>
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
  facilityId: undefined,
  facilityName: undefined,
  facilityType: undefined,
  labId: undefined,
  labNum: undefined,
  createDate: undefined,
  updateDate: undefined
})

//定义表格数据对象
const tableData = ref([])
//定义初始化数据方法
const getData = async () => {
  const path = '/facility/listFacilities'
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
  queryParams.facilityId = undefined
  queryParams.facilityName = undefined
  handleQuery()
}

//弹出框可见性，默认隐藏
const dialogFormVisible = ref(false)

const formLabelWidth = '100px'

//表单数据对象
const form = ref({
  facilityId: '',
  facilityName: '',
  facilityType: '',
  labId: '',
  labNum: '',
  createDate: '',
  updateDate: '',
  userId: ''
})
//表单对象，用于数据校验
const dialogForm = ref(null)
//表单校验规则
const rules = {
  facilityName: [{ required: true, message: '请输入仿真设备编号', trigger: 'blur' }],
  facilityType: [{ required: true, message: '请输入仿真设备类型', trigger: 'blur' }],
  labId: [{ required: true, message: '请输入所属实验室id', trigger: 'blur' }],
  labNum: [{ required: true, message: '请输入所属实验室编号', trigger: 'blur' }],
  createdate: [{ required: true, message: '请选择创建时间', trigger: 'blur' }],
  updatedate: [{ required: true, message: '请选择更新时间', trigger: 'blur' }]
}

//重置表单数据
const resetFormValue = () => {
  form.value = {
    facilityId: '',
    facilityName: '',
    facilityType: '',
    labId: '',
    labNum: '',
    createDate: '',
    updateDate: '',
    userId: ''
  }
}

//弹出框提交
const handleSubmit = () => {
  //如果facilityId存在，则是编辑，否则是新增
  if (form.value.facilityId) {
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
  dialogForm.value.validate(
    (valid) => {
      if (valid) {
        //请求后台，新增申请
        const path = '/facility/insertFacility'
        form.value.userId = userId
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


//根据labId，获取数据
const getFacilityById = (facilityId) => {
  const path = '/facility/getFacilityById'
  request.get(path, { params: { facilityId: facilityId } }).then(
    res => {
      console.log(res.data.data)
      if (res.data.code === 200) {
        form.value = res.data.data
		console.log(form.value)
      }
    }
  )
}

const SubmithandleSearch = (index, row) => {
  dialogFormVisible.value = true
  getFacilityById(row.facilityId)
}
//编辑
const edit = () => {
  dialogForm.value.validate(
    (valid) => {
      if (valid) {
        //请求后台，新增申请
        const path = '/facility/updateFacility'
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
    '确认要删除该个设备吗?',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'error',
    }
  )
    .then(() => {
      const path = '/facility/deleteFacility'
      request.get(path, { params: { facilityId: row.facilityId} }).then(
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
      const path = '/facility/exportFacility'
      request.get(path, { params: queryParams ,responseType:'blob'}).then(
        res => {
          const link = document.createElement("a");
          const blob = new Blob([res.data],{
            type:"application/vnd.ms-excel;charset=utf-8"
          })
          link.style.display = "none"
          link.href = URL.createObjectURL(blob);
          link.setAttribute("download","仿真设备列表.xls");
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