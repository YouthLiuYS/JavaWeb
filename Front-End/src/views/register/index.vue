<template>
	<!-- 注册
	<el-form label-width="120px">
		<el-form-item label="账号">
		       <el-input v-model="loginForm.account" @blur="checkAccount" />
		</el-form-item>
		<el-form-item label="密码">
		       <el-input v-model="loginForm.password" />
		</el-form-item>
		<el-form-item label="姓名">
		       <el-input v-model="loginForm.name" />
		</el-form-item>
		<el-form-item label="电话">
		       <el-input v-model="loginForm.phone" />
		</el-form-item>
		<el-form-item label="年龄">
		       <el-input v-model="loginForm.age" />
		</el-form-item>
		<el-form-item label="邮箱">
		       <el-input v-model="loginForm.email" />
		</el-form-item>
		<el-form-item label="简介">
		       <el-input v-model="loginForm.remark" />
		</el-form-item>
		<el-form-item label="用户类型">
		       <el-select v-model="loginForm.userType" placeholder="请选择用户类型">
					<el-option label="学生" value="1" />
					<el-option label="教师" value="2" />
			   </el-select>
		</el-form-item>
		<el-form-item>
		    <el-button type="primary" @click="register">提交</el-button>
		    <el-button>取消</el-button>
		</el-form-item>
	</el-form> -->
	<el-row type="flex" justify="center" align="middle" style=" height:100%;">
	    <el-col :span="8">
	      <el-card>
	        <el-form lable-width="120px">
	          <h3 class="title">注册</h3>
	          <el-form-item label="账号:">
	            <el-input v-model="registerForm.account" @blur="checkAccount" />
	          </el-form-item>
	          <el-form-item label="密码:">
	            <el-input v-model="registerForm.password" />
	          </el-form-item>
	          <el-form-item label="姓名:">
	            <el-input v-model="registerForm.name" />
	          </el-form-item>
	          <el-form-item label="电话:">
	            <el-input v-model="registerForm.phone" />
	          </el-form-item>
	          <el-form-item label="年龄:">
	            <el-input v-model="registerForm.age" />
	          </el-form-item>
	          <el-form-item label="邮箱:">
	            <el-input v-model="registerForm.email" />
	          </el-form-item>
	          <el-form-item label="简介:">
	            <el-input v-model="registerForm.remark" />
	          </el-form-item>
	          <el-form-item label="用户类型:">
	            <el-select v-model="registerForm.userType" placeholder="请选择用户类型">
	              <el-option label="学生" value="1" />
	              <el-option label="教师" value="2" />
	            </el-select>
	          </el-form-item>
	          <div style="text-align: -webkit-center">
	            <el-form-item style="width: max-content">
	              <el-button type="primary" @click="register">提交</el-button>
	              <el-button @click="cancel">取消</el-button>
	            </el-form-item>
	          </div>
	
	        </el-form>
	      </el-card>
	
	    </el-col>
	
	  </el-row>
</template>

<script setup>
	import {ref,reactive } from 'vue'
	
	import request from '@/utils/request.js'
	
	import router from '@/router/index.js'

	//表单数据对象
	const registerForm = reactive({
		account: '',
		password: '',
		name: '',
		phone: '',
		email: '',
		age: null,
		remark: '',
		userType: null
		})
	//取值：registerForm.account
	
	
	function checkAccount(){
		//调用后台逻辑校验用户名是否存在
		
	}
	function register(){
		console.log(registerForm)
		const path = '/user/userRegister'
		const params = registerForm
		request.post(path,params).then(
			res => {
				console.log(res)
				if(res.data.code == 200){
					console.log(res.data.data)
					router.push({path: '/'})
				}
				else{
					console.log(res.data.code)
					console.log(res.data.message)
				}
			}
		)
	}
	
	
	
	
</script>

<style>
</style>