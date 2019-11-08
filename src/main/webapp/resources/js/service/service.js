"use strict"
var service = service || {}
service = (() =>{
	let _, img, css, js, auth_js, auth_vue_js, service_js, service_vue_js, navi_js
	, navi_vue_js, router_js, admin_js, page_vue_js, compo_vue_js, stadium_vue_js
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
		admin_js = js + '/adm/admin.js'
		page_vue_js = js + '/vue/page_vue.js'
		compo_vue_js = js + '/vue/compo_vue.js'
	}
	let setContentView = () =>{
		console.log('service 겟스크립트 진입')
		//$('#main div[class=my-3 p-3 bg-white rounded box-shadow]').remove()
		recent_update({ pxy : {pageNum : '1', pageSize : '5'}})
	}
	
	let onCreate = x =>{
		init()
		$.when(
			$.getScript(service_vue_js),
			$.getScript(admin_js),
			$.getScript(page_vue_js),
			$.getScript(compo_vue_js)		
		).done(()=>{
			setContentView()
			$('#form_join').remove()
		}).fail(()=>{
			
		})
	}
	
	let recent_update = (x) => {
		$('head').html(service_vue.service_head)
		//$('body div[class=nav-scroller bg-white box-shadow]').remove()
		$('#main').remove()
		$('body').append(service_vue.service_body)
		$('#recent_update .media').remove()
		$.getJSON(_+'/articles/list/'+x.pxy.pageNum+'__'+x.pxy.pageSize,d =>{
			$(page_vue.page_nation()).appendTo('#recent_update')			
			$.each(d.list, (i,j)=>{
				$('<div class="media text-muted pt-3">'+
				         '<img data-src="holder.js/32x32?theme=thumb&amp;bg=007bff&amp;fg=007bff&amp;size=1" alt="32x32" class="mr-2 rounded" style="width: 32px; height: 32px;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2232%22%20height%3D%2232%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%2032%2032%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_16dfcdddb72%20text%20%7B%20fill%3A%23007bff%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A2pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_16dfcdddb72%22%3E%3Crect%20width%3D%2232%22%20height%3D%2232%22%20fill%3D%22%23007bff%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2211.5390625%22%20y%3D%2216.9%22%3E32x32%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">'+
				'         <p id="id_'+ i +'" class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">'+ 
				'         </p>').prependTo('#recent_update')
				$('<strong class="d-block text-gray-dark">'+
						'<a>아이디 : '+j.uid+'</a></strong>')
				.appendTo('#id_'+i).css({'text-align' : 'center'})
				.click(()=>{
					alert('제목 클릭')
					detail(j)
				})
				$('<a>타이틀 : '+j.title+'</a>').appendTo('#id_'+i)
				.click(()=>{
					alert('타이틀클릭')
				})
			})
			let arr = [
				{tag : '<li id="prev" class="page-item"><a class="page-link" href="#">Prev</a></li>', pageNum : -5, pageSize : d.pxy.pageSize},
				{tag : '<li class="page-item"><a class="page-link" href="#">Next</a></li>', pageNum : +5, pageSize : d.pxy.pageSize},
				{tag : '<input type="submit" value="SUBMIT"/>', pageNum : 0, pageSize : $('#listSizeSelectDiv select').val()}
				]
			$.each(arr,(i,j)=>{
				$(j.tag)
				.appendTo('#pagenation')
				.click(()=>{
					alert(j.pageSize)
					recent_update({pxy : {pageNum : Math.floor(d.pxy.pageNum)+j.pageNum, pageSize : j.pageSize}})
				})
			})
			
			$.each(d.pxy.pageArr,(i,j)=>{//페이지네이션 1,2,3,4,5 추가
				$('<li class="page-item"><a class="page-link" href="#">'+j+'</a></li>')
				/*.appendTo('#pagenation')*/
				.insertAfter('#prev')
				.click(()=>{
					alert(j)
					recent_update({pxy : {pageNum : j, pageSize : d.pxy.pageSize}})
				})//json으로 바꿔보자.
			})
			
			$(compo_vue.page_size()).appendTo('#pagenation')
			$.each([5,10,15],(i,j)=>{//셀렉트 추가
				$('<option value="'+j+'">'+j+'</option>')
				.appendTo('#listSizeSelectDiv select[name=site]')
			})
			$('#listSizeSelectDiv select').val(d.pxy.pageSize)
		})
	}
	
	
	let write = x =>{
		$('#recent_update').html(service_vue.service_write(x))
		$('#write_con textarea').val(getCookie('uid'))
		$('#write_con input[name="title"]').val('임시타이틀')
		$('<button>',{
			text : '글다썻다',
			type : 'submit',
			style : "float:right;width:100px;margin-right:10px"
		})
		.addClass('btn btn-primary')
		.appendTo('#write_con')
		.click(e=>{
			e.preventDefault();
			let json = {
					uid : $('#write_con input[name=writer]').val(),
					title : $('#write_con input[name=title]').val(),
					content : $('#write_con textarea').val()
			}
			alert('타이틀'+json.title)
			$.ajax({
				url : _+'/articles/',
				type : 'POST',		//rest 상태에대한 타입.
				data : JSON.stringify(json),			// []도 제이슨이다...!?
				dataType : 'json',	// data의 타입 = {} = json
				contentType : 'application/json',
				success : d =>{
					alert('서엉공!')
					setContentView()
				},
				error : e=>{alert('실패')}
			})
		})
		
		$('<button>',{
			text : '취소하자',
			type : 'submit',
			style : "float:right;width:100px;margin-right:10px"
		})
		.addClass('btn btn-danger')
		.appendTo('#write_con')
		.click(()=>{
		})
	}
	/*+' <input type="reset"  value="CANCEL"/>'
	  +'<input name="write" type="submit" value="SUBMIT"/>'*/
	

	let detail = x => {
		$('#recent_update').html(service_vue.service_write(x))
		$('#write_con input[name="writer"]').val(x.uid)
		$('#write_con textarea').val(x.content)
		$('#write_con input[name="title"]').val(x.title)
		$('h1').html('글번호 : '+x.artseq)
		$('<button>',{
			text : '삭제',
			type : 'submit',
			style : "float:right;width:100px;margin-right:10px"
		})
		.addClass('btn btn-primary')
		.appendTo('#write_con')
		.click(e=>{
			e.preventDefault();
			$.ajax({
				url : _+'/articles/'+x.artseq,
				type : 'DELETE',		//rest 상태에대한 타입.
				data : JSON.stringify(x),			// []도 제이슨이다...!?
				dataType : 'json',	// data의 타입 = {} = json
				contentType : 'application/json',
				success : d =>{
					recent_update()
				},
				error : e=>{alert('실패')}
			})
		})
		.addClass('btn btn-primary')
		.appendTo('#write_con')
		
		$('<button>',{
			text : '수정하기',
			type : 'submit',
			style : "float:right;width:100px;margin-right:10px"
		})
		.addClass('btn btn-danger')
		.appendTo('#write_con')
		.click(e =>{
			e.preventDefault();
			let json = {
					uid : $('#write_con input[name=writer]').val(),
					title : $('#write_con input[name=title]').val(),
					content : $('#write_con textarea').val(),
					artseq : x.artseq
			}
			console.log(x.artseq)
			$.ajax({
				url : _+'/articles/'+x.artseq,
				type : 'PUT',
				data : JSON.stringify(json),
				dataType : 'json',
				contentType : 'application/json',
				success : d => {
					recent_update()
				},
				error : e => {
					alert('실패!')
				}
			})

		})
	}
	return {onCreate,recent_update,write}
})()