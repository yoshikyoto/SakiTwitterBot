import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*
 * 1～9：マンズ
 * 11～19：ピンズ
 * 21～29：ソーズ
 * 31～37：東南西北白発中
 */

class Mahjong{

	public int hand[] = new int[14];
	public int rest[] = new int[38];
	public HashMap<String, MState> hash;
	
	Mahjong(){
		hash = new HashMap<String, MState>();
	}
	
	public MState haipai(String username){
		MState state = new MState(username);
		hash.put(username, state);
		return state;
	}
}

class MState{
	public int hand[];
	public ArrayList<Integer> yama;
	public int pointer;
	public String username;
	
	MState(String u){
		username = u;
		hand = new int[14];
		// yama = new int[38*4];
		yama = new ArrayList<Integer>();
		for(int i = 1; i <= 9; i++){
			yama.add(i);
			yama.add(i);
			yama.add(i);
			yama.add(i);
		}
		for(int i = 11; i <= 19; i++){
			yama.add(i);
			yama.add(i);
			yama.add(i);
			yama.add(i);
		}
		for(int i = 21; i <= 29; i++){
			yama.add(i);
			yama.add(i);
			yama.add(i);
			yama.add(i);
		}
		for(int i = 31; i <= 38; i++){
			yama.add(i);
			yama.add(i);
			yama.add(i);
			yama.add(i);
		}
		print(yama);
		
		Collections.shuffle(yama);
		print(yama);
		
		// 配牌
		for(int i = 1; i <= 13; i++){
			int hai = yama.get(0);
			yama.remove(0);
			hand[i] = hai;
		}
		hand[0] = 0;
		Arrays.sort(hand);
		System.out.println(getHandString());
	}
	
	public void print(int array[]){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]);
		}
		System.out.println();
	}
	
	public void print(ArrayList<Integer> array){
		for(int i = 0; i < array.size(); i++){
			System.out.print(array.get(i));
		}
		System.out.println();
	}
	
	public String[] getHandString(){
		String result[] = new String[5];
		result[1] = "┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐";
		result[2] = "│";
		result[3] = "│";
		result[4] = "└─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘";
		for(int i = 1; i <= 13; i++){
			
			if(hand[i] <= 10){
				// マンズ
				result[0] += hand[i] + "m";
				result[2] += kanji(hand[i]) + "│";
				result[3] += "萬│";
			}else if(hand[i] <= 20){
				// ピンズ
				result[0] += (hand[i] - 10) + "p";
				result[2] += kanji(hand[i] - 10) + "│";
				result[3] += "筒│";
			}else if(hand[i] <= 30){
				// ソーズ
				result[0] += (hand[i] - 20) + "s";
				result[2] += kanji(hand[i] - 20) + "│";
				result[3] += "索│";
			}else{
				switch(hand[i]){
				case 31:
					result[0] += "東";
					result[2] += "東│";
					result[3] += "　│";
					break;
				case 32:
					result[0] += "南";
					result[2] += "南│";
					result[3] += "　│";
					break;
				case 33:
					result[0] += "西";
					result[2] += "西│";
					result[3] += "　│";
					break;
				case 34:
					result[0] += "北";
					result[2] += "北│";
					result[3] += "　│";
					break;
				case 35:
					result[0] += "白";
					result[2] += "　│";
					result[3] += "　│";
					break;
				case 36:
					result[0] += "發";
					result[2] += "發│";
					result[3] += "　│";
					break;
				case 37:
					result[0] += "中";
					result[2] += "中│";
					result[3] += "　│";
					break;
				}
			}
		}
		return result;
	}
	
	public String kanji(int n){
		switch(n){
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 7:
			return "七";
		case 8:
			return "八";
		case 9:
			return "九";
		}
		return "";
	}
}