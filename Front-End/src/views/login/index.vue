<template>
	<!-- <el-form label-width="120px" ref="ruleForm" :rules="rules" :model="loginForm">
		<el-form-item label="账号" prop="account">
		       <el-input v-model="loginForm.account" />
		</el-form-item>
		<el-form-item label="密码" prop="password">
		       <el-input v-model="loginForm.password" />
		</el-form-item>
		<el-form-item>
		    <el-button type="primary" @click="sub">登录</el-button>
		    <router-link :to="'/register'">
				<el-button>注册</el-button>
			</router-link> -->
			<!-- <el-button @click="toRegister">注册</el-button> -->
		<!-- </el-form-item> -->
		
	<!-- </el-form> -->
	<el-row type="flex" justify="center" align="middle" style=" height:100%;">
	    <el-col :span="8">
	      <el-card>
	        <el-form lable-width="120px" ref="ruleForm" :rules="rules" :model="loginForm">
	         <h3 class="title">虚拟仿真实训教学管理及资源共享云平台管理系统</h3>
	          <el-form-item label="账号：" prop="account">
	            <el-input v-model="loginForm.account" />
	          </el-form-item>
	          <el-form-item label="密码：" prop="password">
	            <el-input v-model="loginForm.password" />
	          </el-form-item>
	          <el-form-item>
	            <el-button style="width: 100%;" type="primary" @click="sub">登录</el-button>
	            <!-- <router-link :to="'/register'">
	                <el-button>注册</el-button>
	            </router-link> -->
	            <!-- <router-link :to="'/register'">
	            	<el-button>注册</el-button>
	            </router-link> -->
				<el-button style="width: 100%; margin-top: 20px;margin-left: 0;"  @click="toRegister">注册</el-button>
	            <!-- <el-button @click="toRegister">注册</el-button> -->
	          </el-form-item>
	        </el-form>
	      </el-card>
	
	    </el-col>
	
	  </el-row>
	
</template>



<script setup>
	import {ref,reactive } from 'vue'
	
	import request from '@/utils/request.js'
	
	import router from '@/router/index.js'
	
	// import {store} from '@/store/store.js'
	
	// import storage from '@/store/storage.js'
	
	import useUserStore from '@/store/userStore.js'
	//第一步类似初始化
	const userStore = useUserStore()
	
	
	//console.log(store.isLogin)
	// store.isLogin = false
	
	// //设置值
	// localStorage.setItem('isLogin',false)
	// //取值
	// console.log('localStorage:'+localStorage.getItem('isLogin'))
	// //移除
	// localStorage.removeItem('isLogin')
	
	
	//表单的dom对象
	const ruleForm = ref(null);
	
	//ref()和reactive()都是用作数据双向绑定
	
	//ref()可以用在基本数据类型，也可以用在对象类型
	//如果使用ref()，取值需要加.value
	
	//表单数据对象
	// const loginForm = ref({
	// 	account: '',
	// 	password: ''
	// })
	//取值：loginForm.value.account
	
	
	//reactive()只能用在对象类型
	//如果使用reactive()，直接取值
	
	//表单数据对象
	const loginForm = reactive({
		account: '',
		password: ''
	})
	//取值：loginForm.account
	
	//定义表单校验规则
	const rules = {
		account: [
			{
				required: true,
				message: '请输入账号',
				trigger: 'blur'
			}
		],
		password: [
			{
				required: true,
				message: '请输入密码',
				trigger: 'blur'
			}
		]
	}
	
	// const account = ref('');
	// const password = ref('');
	
	//登录
	function sub(){
		console.log(loginForm)
		console.log(loginForm.account)
		console.log(loginForm.password)
		
		//表单校验
		ruleForm.value.validate(
		(valid) => {
			if(valid){
				console.log('校验通过')
				const path = '/user/userLogin'
				const params = {account:loginForm.account,password:loginForm.password}
				request.post(path,params).then(
					res => {
						console.log(res)
						if(res.data.code == 200){
							//登录成功，把用户信息放到store
							userStore.login(res.data.data)
							//菜单信息放到store
							userStore.setMenu(res.data.data.menuList)
							console.log(res.data.data)
							router.push({path: '/main'})
						}
						else{
							console.log(res.data.code)
							console.log(res.data.message)
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
	//跳转界面，两种方式
	//1 router.push({path: '/register'})
	
	//2 <router-link :to="'/register'"><el-button>注册</el-button></router-link>
	//跳转到注册界面
	function toRegister(){
		router.push({path: '/register'})
	}
	
</script>

<style>
</style>