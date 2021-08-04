package Player;

public class Player {
	private Integer id;
	private String userName;
	private String passWord;
	private String email;
	private byte[] pic;
	private double highScore;
	private int level;
	private double xp;
	
	public Player() {}
	
	public Player(Integer id, String userName, String passWord, String email, byte[] pic, double highScore, int level, double xp) {
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.pic = pic;
		this.highScore = highScore;
		this.level = level;
		this.xp = xp;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public double getHighScore() {
		return highScore;
	}
	public void setHighScore(double highScore) {
		this.highScore = highScore;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getXp() {
		return xp;
	}
	public void setXp(double xp) {
		this.xp = xp;
	}
	
	
}
