<template>
	<div>
		{{userStore.user.name}},欢迎进入主界面
	</div>
	<div>
		<button @click = "logout">注销</button>
	</div>
</template>

<script setup>
	// import {store} from '@/store/store.js'
	import router from '@/router/index.js'
	import useUserStore from '@/store/userStore.js'
	//第一步类似初始化
	const userStore = useUserStore()
	
	const logout = () => {
		userStore.logout()
		router.push('/')
	}
	
	//console.log(store.user)
	//console.log(store.isLogin)
	
	//如果store.count为true，说明登录成功，可以进入主界面
	//如果store.count不为false，说明未登录，需要跳转到登录界面
	// if(!store.isLogin){
	// 	console.log('用户未登录，请登录')
	// 	alert('用户未登录，请登录')
	// 	router.push({path: '/'})
	// }
</script>

<style>
</style>