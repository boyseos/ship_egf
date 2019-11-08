"use strict"
var navi = navi || {}
navi =(()=>{
	let _, img, css, js, auth_js, auth_vue_js, service_js, service_vue_js, navi_js, navi_vue_js, router_js, cookie_js
	let init = ()=>{
		//setCookie(null)
		_ = $.ctx()
		img = $.img()
		css = $.css()
		js = $.js()
		auth_js = js + '/cmm/auth.js'
		auth_vue_js = js + '/vue/auth_vue.js'
		service_js = js + '/service/service.js'
		service_vue_js = js + '/vue/service_vue.js'
		navi_js = js + '/cmm/navi.js'
		navi_vue_js = js + '/vue/navi_vue.js'
		router_js = js + '/cmm/router.js'
		cookie_js = js + '/cmm/cookie.js'
	}
	
	let onCreate = () =>{
		alert('네비온크리')
		init()
		$.when(
			$.getScript(cookie_js),
			$.getScript(service_js),
			$.getScript(navi_vue_js),/*,()=>{
				$('body').html(navi_vue.navi)
			}),*/
			$.getScript(auth_js)
		).done(()=>{
			setContentView()
			loginMenu()			
			auth.onCreate()
		}).fail(()=>{
			
		})
	}
	
	let setContentView = () =>{
		$('body').html(navi_vue.navi)
	}
	
	let loginMenu = () =>{
		$('<a>',{
			text : '글쓰기',
			ref : '#'
		})
		.addClass('nav-link active')
		.prependTo('body nav[class="navbar navbar-expand-md fixed-top navbar-dark bg-dark"]')
		.click(()=>{
			service.write(getCookie('uid'))
		})
		if(getCookie('uid') !== null){
			$('<a>',{
				text : getCookie('uid')+' 로그아웃',
				ref : '#'
			})
			.addClass('nav-link active')
			.prependTo('body nav[class="navbar navbar-expand-md fixed-top navbar-dark bg-dark"]')
			.click(()=>{
				deleteCookie('uid')
				app.r(_)
			})
		}
	}
	
	return {onCreate,loginMenu}
	
})()
