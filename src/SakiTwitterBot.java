import java.util.Calendar;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/*
 * debug=false
 * oauth.consumerKey=P9518mK8pfvVim7Jy0R6skHpr
 * oauth.consumerSecret=SUvzqHM9OVkJrZfwPWwLjNv583iYEmqJ2h5Ddv808hReEoy8AA
 * http.proxyHost=proxy.kuins.net
 * http.proxyPort=8080
 */

class SakiTwitterBot{
	public static void main(String args[]){
		String accessToken = "2470414818-5JbOyNpQvkWwnVyjAG4642dtzv0JP0rCOuhZYLD";
		String accessTokenSecret = "oucBrk3lpJEses9d8rYoPNmlcihz7YuHZsHYC95SKvoRp";
		AccessToken at = new AccessToken(accessToken, accessTokenSecret);
		TwitterFactory tf = new TwitterFactory();
		Twitter twitter = tf.getInstance(at);
		
		while(true){
			try{
				// カレンダーを取得
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				if(hour == 12){
					twitter.updateStatus("前半戦終了ーーーー！！");
					Thread.sleep(7200000);
				}else if(hour == 0){
					twitter.updateStatus(getWeekStr() + "終了ーーーー！！");
					Thread.sleep(7200000);
				}
				Thread.sleep(1000);
			}catch(Exception e){
				System.out.println("ツイートに失敗しました。");
			}
		}
	}
	
	public static String getWeekStr(){
		Calendar calender = Calendar.getInstance();
		switch(calender.get(Calendar.DAY_OF_WEEK)){
		case Calendar.MONDAY:
			return "先鋒戦";
		case Calendar.TUESDAY:
			return "次鋒戦";
		case Calendar.WEDNESDAY:
			return "中堅戦";
		case Calendar.THURSDAY:
			return "副将戦";
		case Calendar.FRIDAY:
			return "大将戦";
		default:
			return null;	
		}
	}
}