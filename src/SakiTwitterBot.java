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
				// �J�����_�[���擾
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				if(hour == 12){
					twitter.updateStatus("�O����I���[�[�[�[�I�I");
					Thread.sleep(7200000);
				}else if(hour == 0){
					twitter.updateStatus(getWeekStr() + "�I���[�[�[�[�I�I");
					Thread.sleep(7200000);
				}
				Thread.sleep(1000);
			}catch(Exception e){
				System.out.println("�c�C�[�g�Ɏ��s���܂����B");
			}
		}
	}
	
	public static String getWeekStr(){
		Calendar calender = Calendar.getInstance();
		switch(calender.get(Calendar.DAY_OF_WEEK)){
		case Calendar.MONDAY:
			return "��N��";
		case Calendar.TUESDAY:
			return "���N��";
		case Calendar.WEDNESDAY:
			return "������";
		case Calendar.THURSDAY:
			return "������";
		case Calendar.FRIDAY:
			return "�叫��";
		default:
			return null;	
		}
	}
}