
//本接口实现ATM界面的展示、接收用户按键输入功能。

public interface IChinaBankATMMenu {
	char welcomePage();           						//展示ATM欢迎界面
	String inputPasswordPage();   						//展示ATM输入密码界面
	char welcomeUserPage(ChinaBankAccount account);  	//展示用户余额，让用户进行存取款选择界面。
	int userDepositPage();								//用户存钱输入数值的界面
	int userWithdrawPage(ChinaBankAccount account);     //用户取现，展示用户可用余额，让用户输入数值的界面
	void displayMessagePage(String msg, long millis);	//显示消息的界面
	default void displayMessagePage(String msg)
	{
		displayMessagePage(msg, 3000);
	}
}
