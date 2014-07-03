import java.util.Arrays;

class Mahjong{
	/*
	 * 1～9：マンズ
     * 11～19：ピンズ
     * 21～29：ソーズ
     * 31～38：東南西北白発中
	 */
	public int hand[] = new int[14];
	public int rest[] = new int[38];
	
	public void haipai(){
		Arrays.fill(rest, 4);
	}
}