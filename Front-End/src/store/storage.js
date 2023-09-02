//const定义的变量需要赋值
//let定义的变量不需要赋初始值

let storage = {
	set(key,val,expired){
		let obj = {
			data: val,
			time: Date.now(), //当前设置值的时间
			expired  //过期时间间隔  毫秒 1000 = 1s
		}
		localStorage.setItem(key,JSON.stringify(obj)) //localStorage只能设置字符串，JSON.stringify(obj)把obj这个对象转换为json字符串		
	},
	
	get(key){
		let val = localStorage.getItem(key)
		if(!val){
			return val
		}
		val = JSON.parse(val) //JSON.parse(val)把val这个json字符串转换为对象
		//如果取值当前这个时间点 - 设置值的时间点 > 过期时间间隔
		console.log(Date.now() - val.time)
		if(Date.now() - val.time > val.expired){
			localStorage.removeItem(key)
			return null
		}
		return val.data
	},
	
	remove(key){
		localStorage.removeItem(key)
	}
	
}

export default storage