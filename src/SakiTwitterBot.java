import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Random;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/*
 * debug=false
 * oauth.consumerKey=P9518mK8pfvVim7Jy0R6skHpr
 * oauth.consumerSecret=SUvzqHM9OVkJrZfwPWwLjNv583iYEmqJ2h5Ddv808hReEoy8AA
 * http.proxyHost=proxy.kuins.net
 * http.proxyPort=8080
 */

public class SakiTwitterBot{
	public static String consumerKey = "P9518mK8pfvVim7Jy0R6skHpr";
	public static String consumerSecret = "SUvzqHM9OVkJrZfwPWwLjNv583iYEmqJ2h5Ddv808hReEoy8AA";
	public static String accessToken = "2470414818-5JbOyNpQvkWwnVyjAG4642dtzv0JP0rCOuhZYLD";
	public static String accessTokenSecret = "oucBrk3lpJEses9d8rYoPNmlcihz7YuHZsHYC95SKvoRp";
	public static Twitter twitter;
	
	public static void main(String args[]){
		System.out.println("Running SakiTwitterBot");
		AccessToken at = new AccessToken(accessToken, accessTokenSecret);
		TwitterFactory tf = new TwitterFactory();
		twitter = tf.getInstance(at);
		
		// コンソールに日付を表示するための MessageFormat
		MessageFormat mf = new MessageFormat("{0,date,yyyy/MM/dd HH:mm:ss}");
		int sleep_time = 1000; // デフォルトの時間確認間隔
		
		while(true){
			// 時間を取得
			Calendar calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			Object[] calendar_objects = {calendar.getTime()};
			// コンソールに表示するための時間Stringを取得
			String calendar_string = mf.format(calendar_objects);
			
			try{
				// 12時のtweet
				if(hour == 12){
					if(isWeekday(calendar)){
						twitter.updateStatus("前半戦終了ーーー！！");
						System.out.println(calendar_string + " Succeeded to Tweet");
						sleep_time = 1000;
					}else{
						// さあ、戦法前半戦開始です
						tweetTherif();
					}
					Thread.sleep(7200000);
				// 24時のtweet
				}else if(hour == 0){
					String week_string = getWeekStr(calendar);
					if(week_string.length() != 0){
						twitter.updateStatus(week_string + "終了ーーー！！");
						System.out.println(calendar_string + " Succeeded to Tweet");
						sleep_time = 1000;
					}else{
						tweetTherif();
					}
					Thread.sleep(7200000);
				}else{
					sleep_time = 1000;
				}
				Thread.sleep(sleep_time);
			}catch(Exception e){
				System.out.println(calendar_string + " Failed to Tweet");
				if(sleep_time < 60000) sleep_time = sleep_time * 2;
			}
		}
	}
	
	public static String getWeekStr(Calendar calendar){
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
		case Calendar.TUESDAY:
			return "先鋒戦";
		case Calendar.WEDNESDAY:
			return "次鋒戦";
		case Calendar.THURSDAY:
			return "中堅戦";
		case Calendar.FRIDAY:
			return "副将戦";
		case Calendar.SATURDAY:
			return "大将戦";
		default:
			return "";	
		}
	}
	
	public static void tweetTherif() throws TwitterException{
		if(therif.length == 0) return;
		Random random = new Random();
		twitter.updateStatus(therif[random.nextInt(therif.length)]);
	}
	
	public static boolean isWeekday(Calendar calendar){
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
		case Calendar.SATURDAY:
		case Calendar.SUNDAY:
			return false;
		default:
			return true;	
		}
	}
	
	public static String therif[] = {
		"清一、対々和、三暗刻、三槓子、赤一、嶺上開花！",
		"一番大好きな私になりたい〜♪",
		"あなたがいて　わたしがいて　他の人は消えてしまった〜♪"
	};
}