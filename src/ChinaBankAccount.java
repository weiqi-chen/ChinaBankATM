//本类实现银行账户管理
//记录银行账户户主名称、密码、账户金额。
//实现存取款的功能
public class ChinaBankAccount {
	String name;
	String gender;
	private String password;
	private int money;
	
	public ChinaBankAccount(String name, String gender, String password, int money) {
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.money = money;
	}

	//校验用户输入的密码是否正确
	boolean auth(String pwd) { 
		return password.equals(pwd); 
	}
	
	//存钱
	void deposit(int mon) {
		money += mon;
	}
	
	//取现
	boolean withdraw(int mon)
	{
		if(mon > money)	
			return false; //余额不足，返回false表示取款失败。
		
		money -= mon;
		return true;//返回true代表取现成功完成。
	}
	
	//账户金额查询
	int query()
	{
		return money;
	}
}
