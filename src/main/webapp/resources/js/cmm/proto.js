"use strict"
$.prototype.nC = x =>{
	let flag = false
	let i = 0
	for (i in x){
		if (x[i] === ''){
			flag = true
		}
	}
	return flag
}