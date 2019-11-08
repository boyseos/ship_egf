package com.ship.web.test;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mysql.cj.xdevapi.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String url = "https://map.naver.com/v5/api/search?caller=pcweb&query=풋살장&type=all&page=2&displayCount=100&lang=ko";
		Document rawData = Jsoup.connect(url).timeout(10*1000)
				.ignoreContentType(true)
				.get();
		Elements address = rawData.select("address");
		List<String> adrList = new ArrayList<String>();
		for (Element e : address) {
			adrList.add(e.html());
		}
		System.out.println(adrList);
		//JSONParser jsonParse = new JSONParser();
		//System.out.println(rawData.attr("name","name"));
		JsonParser jsonParser = new JsonParser();
		String input = rawData.text(),result="",ptnS=""; 
		List<String> x = new ArrayList<>(),result1 = x, result2 = x, result3 = x, result4 = x;
		int cut = 0;
		
		ptnS = "\"name\":\"(.*?)\"";
		cut = 7; 
		result1 = matching(input,ptnS,cut);
		
		ptnS = "\"address\":\"(.*?)\"";
		cut = 10; 
		result2 = matching(input,ptnS,cut);
		
		ptnS = "\"tel\":\"(.*?)\"";
		cut = 6; 
		result3 = matching(input,ptnS,cut);
		
		ptnS = "\"abbrAddress\":\"(.*?)\"";
		cut = 2; 
		result4 = matching(input,ptnS,cut);

		/*for(int i= 0; i < result1.size();i++)
			System.out.printf("%s  %s  %s  %s\n"
						,result1.get(i)
						,result2.get(i)
						,result3.get(i)
						,result4.get(i));*/
		
		for(int i = 0; i< 8; i++) result += ((char)((int)(Math.random()*26)+97));
		System.out.println(result);	
		
	}
	public static List<String> matching(String input, String ptnS, int cut){
		List<String> x = new ArrayList<>();
		Pattern ptn = Pattern.compile(ptnS);
		Matcher matcher = ptn.matcher(input);
		while(matcher.find()){
			x.add(matcher.group().substring(cut).replace("\"","")); 
		}
		return x;
	}
	
	public String makeTel() {
		return String.format("%03d",this.ran(100, 1))+"-"+String.format("%04d",this.ran(1000, 1));
	}
	
	public int ran(int a, int b) {
		BiFunction<Integer, Integer, Integer> f = (x,y) ->(int)(Math.random()*y)+x;
		return f.apply(a, b);
	}
	
	
}
