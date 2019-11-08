"use strict";
var auth = auth || {}
auth =(()=>{
	let _, img, css, js, auth_js, auth_vue_js, service_js, service_vue_js
	, navi_js, navi_vue_js, router_js, cookie_js, page_vue_js
	let init = ()=>{
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
		page_vue_js = js + '/vue/page_vue.js'
	}
	
	let onCreate =()=>{
		init()
		$.when(
			$.getScript(auth_vue_js,()=>{
			setContentView()
			}),
			$.getScript(cookie_js),
			$.getScript(router_js),
			$.getScript(service_js),
			$.getScript(page_vue_js))
		.done(()=>{
			
		})
		.fail(()=>{
			
		})
		
	}
	
	let setContentView =()=>{
		$('body').append(auth_vue.login_body)
    	login()
    }
	//$().on('click',()=>{}) 원래문법
	//$().click(()=>{}) resig이 문법
	//$.ajax('sdf',{}) 원래문법
	let login =()=>{
		$('<a>',{
				text : '회원가입 이동',
				href : '#',
				click : () =>{
					join()
				}
			})
			.appendTo('#a_go_join')
		$('<a>',{
				text : '   관리자모드 ',
				href : '#',
			})
			.appendTo('div[class="checkbox mb-3"]')
			.click(() =>{
				access()
			})
		$('<button>',{
			text : 'Sign in',
			type : 'submit',
			click : e=>{
				e.preventDefault();
				let x = { uid : $('#uid').val(),
						upw : $('#upw').val()
				}
				alert('로그인 전송아이디 : '+x.uid + x.upw)
				$.ajax({
					url : _+'/users/'+x.uid,
					type : 'POST',
					dataType : 'json',
					data : JSON.stringify(x),
					contentType : 'application/json',
					success : d =>{
						alert(d.uid+' 님 환영합니다.')
						
						$.when(
							setCookie("uid",d.uid)
						)
						.done(()=> service.onCreate())
						.fail(()=> alert('왜 실패?'))
					},
					error : e => {
						alert('로그인 ajax 실패')
					}
				})
			}
		})
		.addClass('btn btn-lg btn-primary btn-block')
		.appendTo('#a_login_button')
	}
	let join =()=>{
		alert('회원가입 클릭!!');
		$('#form_join').remove()
		$('body').append(auth_vue.join_body)
		$('#uid').keyup(()=>{
			if ($('#uid').val().length > 1)
				//alert($('#uid').val())
				existid($('#uid'));
		})
		$('<button>',{
			text : 'Continue to checkout',
			href : '#',
			type : 'submit',
			click : e=>{
				e.preventDefault();
				let data = { uid : $('#uid').val(),
						upw : $('#upw').val(),
						uname : $('#uname').val(),
						age : $('#age').val()
				}
				alert('조인 전송아이디 :'+data.uid)
				$.ajax({
					url : _+'/users/',
					type : 'POST',
					dataType : 'json',
					data : JSON.stringify(data),
						//{ uId : $('#uid').val ,uPw : $('#upw)}이 원형태
					contentType : 'application/json',      //meam
					success : d => {
						console.log('조인'+d.uid)
						//$('div [class="container"]').remove()
						$('#join_page').remove()
						setContentView()
					},
					error : e => {
						console.log('조인실패')
					}
				})
			}
		})
		.addClass('btn btn-primary btn-lg btn-block')
		.appendTo('#a_join_button')
	}
	
	let existid = x =>{
		$.ajax({
			url : _+'/users/'+$('#uid').val()+'/exist',
			// type : 'GET', default 지워도 된다.
			contentType : 'application/json',      //meam
			success : d => {
				if(d.boolean === 'FALSE') $('#dupl_check').val('사용가능한 ID입니다').css('color','blue')
				else $('#dupl_check').val('쓰고있는 ID입니다').css('color','red')
			},
			error : e => {
				alert('중복체크 ajax 실패..!!!!!!!!!!')
			}
		})
	}
	let access =()=>{
/*		let ok = confirm('사원입니까?')
		if(ok){ //predicate 판단하나봐?    alert confirm prompt 3개
			let eid = prompt('사원번호를 입력하시오')
			alert('입력한 사번 : '+eid)
			$.ajax({
				url : _ + '/admins/'+ eid,
				type : 'POST',
				dataType : 'json',
				data : JSON.stringify({
					eid : eid,
					epw : prompt('사원비번을 입력하세요')
				}),
				contentType : 'application/json',
				success : d =>{
					admin.onCreate(d)
				},
				error : e => {
					
				}
			})
		} */
		admin.onCreate('x')
	}
	return {onCreate};
})();