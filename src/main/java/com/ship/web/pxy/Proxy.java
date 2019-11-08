package com.ship.web.pxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ship.web.board.Article;
import com.ship.web.board.ArticleMapper;
import com.ship.web.cmm.IFunction;
import com.ship.web.cmm.ISupplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component @Data @Lazy
@AllArgsConstructor
@NoArgsConstructor
public class Proxy {
	private int startRow, pageSize, pageNum, endRow, nextBlock, prevBlock,startPage,endPage;
	private boolean existPrev,existNext;
	private List<Integer> pageArr;
	private List<Article> listArr;
	private String search;
	private final int BLOCK_SIZE = 5;
	@Autowired ArticleMapper articleMapper;
	
	public void paging() {
		ISupplier<Integer> s = () -> articleMapper.allBoardCount();//이미 시작될때 실행된 결과값을 가져온다. 그래서 뉴때리면 없어진다..?
		int totalCount = s.get(),blockCount=0,blockNum=0,pageCount=0;
		blockCount = (totalCount-1)/(pageSize*BLOCK_SIZE)+1;
		pageCount = (totalCount-1) / pageSize +1;
		pageNum = (pageCount < pageNum) ? pageCount :
					pageNum <=0 ? 1 : pageNum;
		blockNum = (pageNum-1) / BLOCK_SIZE;
		startRow = pageSize * (pageNum-1);
		endRow = (pageCount != pageNum) ? (pageSize * pageNum)-1 : totalCount -1;
		startPage = blockNum * BLOCK_SIZE + 1;
		//endPage = ((blockNum+1) != blockCount) ? startPage + (BLOCK_SIZE-1) : pageCount;
		endPage = (blockNum+1) * BLOCK_SIZE;
		endPage = endPage > pageCount ? pageCount : endPage;
		existPrev = blockNum == 0;
		existNext = blockNum == blockCount -1;
		pageArr = new ArrayList<>();
		for(int i = endPage; startPage<= i; i--) {
			pageArr.add(i);
		}
		/*pageArr = Arrays.asList(endPage
							,endPage-1
							,endPage-2
							,endPage-3
							,endPage-4);*/
		prevBlock = startPage - BLOCK_SIZE;
		nextBlock = startPage + BLOCK_SIZE;
	}
	
	public int parseInt(String param) {
		Function<String, Integer> f = x -> Integer.parseInt(x);
		return f.apply(param);
	}
	
	public List<?> crawl(String site, String search){
		System.out.println(site);
		String url = "https://"+(site.matches("(.*?)naver(.*?)") ? 
				"search.naver.com/search.naver?query=" : site+"/search?q=")+search;
		List<String> proxyList = new ArrayList<String>();
		proxyList.clear();
		try {
			Connection.Response response = Jsoup.connect(url)
											.method(Connection.Method.GET)
											.execute();
			Document document = response.parse();
			String text = document.html();
			proxyList.add(text);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return proxyList;
	}
	
	public int ran(int a, int b) {
		BiFunction<Integer, Integer, Integer> f = (x,y) ->(int)(Math.random()*y)+x;
		return f.apply(a, b);
	}
	
	public static String makeUsers() {
		//uid, uname, upw, age, gender, loc, tel, email, upoint, score, mvp, win, hitmap, km, heart, author, lolname;
	    List<String> 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
	        "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
	        "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
	        "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
	        "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
	    List<String> 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
	        "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
	        "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
	        "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
	        "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
	        "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
	        "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
	        "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
	        "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
	        "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
	    Collections.shuffle(성);
	    Collections.shuffle(이름);
	    List<String> name = new ArrayList<>();	    
	    return 성.get(0) + 이름.get(0) + 이름.get(1);
	  }
	
	public String makeAge () {
		return String.format("%02d", this.ran(100, 1)); 
	}
	
	public String makeGender () {
		return (this.ran(10, 10))%2 == 0 ? "남" : "여"; 
	}
	
	public String makeTel () {
		return String.format("%03d",this.ran(100, 1))+"-"+String.format("%04d",this.ran(1000, 1));
	}
	
	public String makeUpoint () {
		return String.format("%04d",this.ran(1000, 1));
	}
	
	public String makeScore () {
		return String.format("%03d",this.ran(100, 1));
	}
	
	public String makeMvp () {
		return String.format("%02d",this.ran(100, 1));
	}
	
	public String makeWin () {
		return String.format("%02d",this.ran(100, 1));
	}
	public String makeKm () {
		return String.format("%03d",this.ran(300, 1));
	}
	
	public String makeAuthor () {
		return (this.ran(10, 10))%2 == 0 ? "1" : "0"; 
	}
	
	public String setCalendars(){
		  int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		  int iMinMonth = 1;
	      int iMaxMonth = 12;
	      int iRandomMonth = (int)(Math.random() * iMaxMonth - iMinMonth + 1) + iMinMonth;
	      int iRandomDay = (int)(Math.random() * (maxDays[iRandomMonth-1] -2) + 1);
	      int iUserBirthMonth = iRandomMonth;
	      int iUserBirthDay = iRandomDay;
	      return iUserBirthMonth +"-"+ iUserBirthDay;
	      
	    }
	
	public String eng(int x) {
		String result = "";
		for(int i = 0; i< x; i++)System.out.println((char)((int)(Math.random()*26)+97));
		return result;
	}
	
	public String uid() {
		return this.eng(8);
	}
	
	public String eMail() {
		String result = this.eng((int)this.ran(1, 10))+"@";
		switch((int)this.ran(1, 10)) {
		case 0 : result += "naver.com"; break;
		case 1 : result += "google.com"; break;
		case 2 : result += "daum.com"; break;
		case 3 : result += "dreamwiz.com"; break;
		case 4 : result += "yahoo.com"; break;
		case 5 : result += "oohoo.com"; break;
		case 6 : result += "fff.com"; break;
		case 7 : result += "yyy.com"; break;
		case 8 : result += "xxxx.com"; break;
		case 9 : result += "zzzzz.com"; break;
		default : break;
		}
		return result;
	}
	
	
}
