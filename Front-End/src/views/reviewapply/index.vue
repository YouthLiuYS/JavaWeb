<template>
	<!-- 查询条件 -->
	<el-form :inline="true" :model="queryParams">
	  <el-form-item label="申请编号">
	    <el-input v-model="queryParams.applyNum" placeholder="请输入申请编号" clearable />
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
	
	<!-- 列表 -->
	<el-table :data="tableData" style="width: 100%">
	    <el-table-column prop="applyId" label="申请id" width="180" />
		<el-table-column prop="userEntity.name" label="申请人" width="180" />
	    <el-table-column prop="applyNum" label="申请编号" width="180" />
	    <el-table-column prop="applyDay" label="申请天数" width="180" />
		<el-table-column prop="applyReason" label="申请原因" width="180" />
		<el-table-column :formatter="formatter" prop="applyStatus" label="申请状态" width="180" />
		<el-table-column prop="applyDatetime" label="申请时间" width="180" />
		<el-table-column prop="reviewComments" label="审批意见" width="180" />
		<el-table-column prop="reviewDatetime" label="审批时间" />
		
		<el-table-column label="操作">
		      <template #default="scope">
		        <el-button
		          type="success"
		          @click="handleEdit(scope.$index, scope.row)"
		          >审批</el-button
		        >
		      </template>
		    </el-table-column>
	  </el-table>
	  <!-- 列表 -->
	  
	  <!-- 弹出框 表单 -->
	  <el-dialog v-model="dialogFormVisible" title="审批">
	      <el-form ref='dialogForm' :rules="rules" :model="form" lable-width="120px">
	        <el-form-item label="申请编号" prop="applyNum" :label-width="formLabelWidth">
	          <el-input disabled v-model="form.applyNum" autocomplete="off" />
	        </el-form-item>
	  		<el-form-item label="申请天数" prop="applyDay" :label-width="formLabelWidth">
	  			<el-input disabled v-model="form.applyDay" autocomplete="off" />
	  		</el-form-item>
	  		<el-form-item label="申请原因" prop="applyReason" :label-width="formLabelWidth">
	  			<el-input disabled v-model="form.applyReason" autocomplete="off" />
	  		</el-form-item>
			<el-form-item label="申请时间" prop="applyDatetime" :label-width="formLabelWidth">
				<el-date-picker disabled 
					    v-model="form.applyDatetime"
						format="YYYY-MM-DD HH:mm:ss"
						value-format="YYYY-MM-DD HH:mm:ss"
					    type="datetime"
					    placeholder="请选择"
					    />
			</el-form-item>
				
			<el-form-item label="审批状态" prop="applyStatus" :label-width="formLabelWidth">
				<el-select v-model="form.applyStatus">
				    <el-option
						v-for="item in options"
				        :key="item.value"
				        :label="item.label"
				        :value="item.value"
				    />
				</el-select>
			</el-form-item>
			<el-form-item label="审批意见" prop="reviewComments" :label-width="formLabelWidth">
				<el-input v-model="form.reviewComments" autocomplete="off" />
			</el-form-item>
	  		<el-form-item label="审批时间" prop="reviewDatetime" :label-width="formLabelWidth">
	  			<el-date-picker
	  				    v-model="form.reviewDatetime"
	  					format="YYYY-MM-DD HH:mm:ss"
	  					value-format="YYYY-MM-DD HH:mm:ss"
	  				    type="datetime"
	  				    placeholder="请选择"
	  				    />
	  		</el-form-item>
	  	
	      </el-form>
	      <template #footer>
	        <span class="dialog-footer">
	          <el-button @click="dialogFormVisible = false">取消</el-button>
	          <el-button type="primary" @click="review">
	            提交
	          </el-button>
	        </span>
	      </template>
	    </el-dialog>
</template>

<script setup>
	import {ref, reactive} from 'vue'
	import request from '@/utils/request.js'
	import { Delete, Edit, Search, Refresh, Plus, Download } from '@element-plus/icons-vue'
	import { ElMessage } from 'element-plus'
	
	import useUserStore from '@/store/userStore.js'
	//第一步类似初始化
	const userStore = useUserStore()
	const userId = userStore.user.userId
	//查询条件数据对象
	const queryParams = reactive({
	  applyNum: undefined,
	  applyStatu: undefined,
	  // applyDay: undefined
	})
	//申请状态的集合
	const applyStatusOptions = [
	  { value: 3, label: '待审批' },
	  { value: 1, label: '审批通过' },
	  { value: 2, label: '审批不通过' },
	  { value: 0, label: '草稿' },
	  { value: 4, label: '撤回' }
	]
	//定义表格数据对象
	const tableData = ref([])
	//定义初始化数据方式
	const getData = async () =>{
		// const path = '/apply/listApplysTeacher'
		// await request.get(path).then(
		// 	res => {
		// 		console.log(res)
		// 		//ref.data是res的data对象，res.data拿到后台返回的ResultDto，res.data.data就是ResultDto.data
		// 		tableData.value = res.data.data
		// 	}
		// )
		// await request.get(path).then(
		//   res => {
		//     //res.data是res的data对象，res.data拿到后台返回的ResultDto，res.data.data 就是ResultDto.data
		//     tableData.value = res.data.data
		//   }
		// )
		const path = '/apply/listApplysTeacherByInfor'
		await request.get(path, { params: queryParams}).then(
		  res => {
		    //res.data是res的data对象，res.data拿到后台返回的ResultDto，res.data.data 就是ResultDto.data
		    tableData.value = res.data.data
		  }
		)
	}
	
	//执行数据初始化方法
	getData()
	//查询条件方法
	const handleQuery = () => {
	  getData()
	}
	//重置
	const handleReset = () => {
	  queryParams.applyNum = undefined
	  queryParams.applyStatus = undefined
	  handleQuery()
	}
	//格式化列
	const formatter = (row, column, cellValue, index) => {
	  if (cellValue === 3) {
		return '待审批'
	  } else if (cellValue === 1) {
		return '审批通过'
	  } else if (cellValue === 2) {
		return '审批不通过'
	  } else if(cellValue === 0){
		  return '草稿'
	  } else if(cellValue === 4){
		  return '撤回'
	  }
	  else {
		return ''
	  }
	}
	//弹出框可见性，默认隐藏
	const dialogFormVisible = ref(false)
	
	//下拉框的集合
	const options = [
	  {
	    value: 0,
	    label: '草稿',
	  },
	  {
	    value: 1,
	    label: '审批通过',
	  },
	  {
	    value: 2,
	    label: '审批不通过',
	  },
	  {
	    value: 3,
	    label: '待审批',
	  }
	]
	
	//表单对象，用于数据校验
	const dialogForm = ref(null)
	
	//表单数据对象
	const form = ref({
	  applyNum: '',
	  applyDay: '',
	  applyReason: '',
	  applyDatetime: '',
	  applyStatus: '',
	  reviewComments: '',
	  reviewDatetime: ''
	})
	
	//表单校验
	const rules = {
		applyStatus:[{required:true,message:'请选择状态',trigger:'blur'}],
		reviewComments:[{required:true,message:'请输入审批意见',trigger:'blur'}],
		reviewDatetime:[{required:true,message:'请输入审批时间',trigger:'blur'}]
	}
	//打开审批窗口
	const handleEdit = (index,row) => {
		dialogFormVisible.value = true
		getApplyById(row.applyId)
	}
	
	//从后台查询申请信息
	const getApplyById = (applyId) => {
		const path = '/apply/getApplyById'
		request.get(path,{params:{applyId:applyId}}).then(
			res => {
				console.log(res.data.data)
				if(res.data.code ===200){
					form.value = res.data.data
				}
			}
		)
	}
	
	//审批
	const review = () => {
		dialogForm.value.validate(
		(valid) => {
			if(valid){
				//请求后台，提交审批
				const path = '/apply/reviewApply'
				request.post(path,form.value).then(
					res => {
						if(res.data.code === 200){
							dialogFormVisible.value = false
							ElMessage({
								message: res.data.message,
							    type: 'success',
							  })
							//刷新列表
							getData()
						}
						else{
							ElMessage({
								message: res.data.message,
							    type: 'error',
							  })
						}
					}
				)
			}
			else{
				console.log('校验不通过')
			}
		}
		)
	}
	
</script>

<style>
</style>