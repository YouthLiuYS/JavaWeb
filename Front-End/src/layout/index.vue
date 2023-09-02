<template>
	<!-- 框架页面 -->
	<div class="layout df">
		
		<div class="navs fn df fdc">
	
		<button class="link df jcc aic mb10" v-for="item in routes" @click="handleClick(item.menuPath)">
			{{item.menuName}}
		</button>
		</div>
		
		<el-container>
<!-- 			<el-header>
				虚拟仿真项目
			</el-header> -->
		        <el-main>
					<div class="app-main f1 p15">
						<router-view></router-view>
					</div>
				</el-main>
		        <el-footer>
					Copyright © 2023 XNFZ. author: LiuYangsen XiaZehao LiangXubo LiuChang YeYifan. All rights reserved. 
				</el-footer>
		</el-container>
	</div>
	
</template>

<script setup>
	//import {routes} from '@/router/index.js'
	import useUserStore from '@/store/userStore.js';
	import router from '@/router/index.js'
	import { computed } from 'vue';
	//第一步类似初始化
	const userStore = useUserStore()
	
	//const routes = userStore.menuList
	const routes = computed( ()=> userStore.menuList )
	
	const handleClick = (path) =>{
		router.push({path})
	}
	
</script>

<style lang="scss">
	.layout{
		height: 100%;
		.navs{
			width: 140px;
			background-color: goldenrod;
			.link{
				height: 40px;
				background-color: cornflowerblue;
				color: white;

			}
		}
	}
	
	.router-link-active{
		background-color: forestgreen !important;
	}
	
	.title{
		size: 200px;
		
	}
</style>