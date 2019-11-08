var admin = admin || {}
var admin = (()=>{
	let _, img, css, js, auth_js, auth_vue_js, service_js, service_vue_js, navi_js, navi_vue_js, router_js, proto_js, stadium_vue_js
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
		proto_js = js + '/cmm/proto.js'
		stadium_vue_js = js + '/vue/stadium_vue.js'
	}
	let onCreate = (x =>{
		alert(x.msg+'성공')
		init()
		$.getScript(proto_js)
		$.getScript(stadium_vue_js)
		setContentView()
	})
	let setContentView = ()=>{
		$('#form_join').remove()
		let arr = [	{txt : '경기장등록', name: 'registerStadium'},
					{txt : '웹크롤링', name: 'web_crawling'},
					{txt : '상품명', name: 'item_reg'},
					{txt : 'asdf', name: 'item_srch'},
					{txt : 'asdf', name: 'item_mod'},
					{txt : 'weg', name: 'item_del'},
					{txt : 'sdf', name: 'item_xxx'}]
		tableCreate(arr.length,arr,20)
	}
	
	let tableCreate = (x,arr,y) =>{		
		$('<table id="tab" style="width: 80%; height: 70%; margin: auto"><tbody></tbody></table>')
		.appendTo('body')
		//<td rowspan="'+x+'" id="right" style="border: 3px solid rgb(0, 0, 0);width: '+(100-y)+'%;text-align: center;">main</td>
		$.each(arr,(i,j)=>{
			$('<tr name="'+i+'"></tr>').appendTo('tbody')
			$('<td name="'+j.name+'" style="text-align: center;">'+j.txt+'</td>')
			.css({border : '3px solid #000000', width: y+'%'})
			.appendTo('tbody tr[name="'+i+'"]')
			.click(function(){
				$(this).addClass('active')
				$(this).siblings().removeClass('active')
				alert(i+'번째 메뉴클릭 :'+$(this).attr('name'))
				switch($(this).attr('name')){				
					case 'web_crawling':
						webCrawl()
						break;
					case 'registerStadium' : 
						std_reg()
						break;
					case 'item_reg' : break;
					case 'item_srch' : break;
					case 'item_mod' : break;
					case 'item_del' : break;
					case 'item_xxx' : break;
				}
			})		
		})
		$('tbody tr[name=0]').append('<td rowspan="'+x+'" id="right" style="border: 3px solid rgb(0, 0, 0);width: '+(100-y)+'%;text-align: center;"></td>')
	}
	
	let std_reg = () =>{
		$('#right').empty()
		$('#right').append(stadium.stadium_vue())
	}
	
	let webCrawl = () =>{
		$('#right').empty()
		$('<form action= "'+_+'/service/" style="margin: auto;"><select name="site" size="1" style="width: 300px;"></select>'+
			'<br></br><input type="search" placeholder="Search" aria-label="Search" style="width: 300px; text-align: center;">'+
			'<br></br></form><text style="width: 700px; height: 450px; text-align: center;"></text>')
		.appendTo('#right')
		let array = [	{value: 'https://naver.com/', name: "네이버"},
						{value: 'https://www.google.com/', name: "구글"},
						{value: '피아트', name: "fiat"},
						{value: '아우디', name: "audi"},
						{value: '현대', name: "hyundai"}
					]
		$.each(array,(i,j)=>{
			$('<option value="'+j.value+'">'+j.name+'</option>')
			.css({border : '1px solid #000000'})
			.appendTo('form select[name=site]')
		})
		$('<input type="submit">').appendTo('#right form[action="/web/service/"]')
		.click( e =>{
			e.preventDefault()
			if(!$.fn.nC([$('form input[type=search]').val(),
				$('form select[name=site]').val()])){
				$.getJSON(_+'/service/search'+$('form input[type=search]').val()+'/site'+
					$('form select[name=site]').val(), d =>{
					$('text').html(d)
				}
			)}
			else alert('뭔가없다.')
		})
	}
	return{onCreate}
})()