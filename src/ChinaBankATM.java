import java.io.IOException;

//本ATM类实现ATM工作逻辑。
//至于界面的部分则由ChinaBankATMMenu负责。
//ChinaBankAccount类负责银行账户存取款等功能。
public class ChinaBankATM {
	// 类成员函数
	ChinaBankATMMenu menu;
	ChinaBankAccount account;

	// 构造函数，调用ChinaBankATMMenu、BankAccount类的构造函数
	// 初始化menu、ba类成员对象。
	public ChinaBankATM() throws Exception {
		menu = new ChinaBankATMMenu();
		account = new ChinaBankAccount("  刘醒", "先生", "123678", 1000);
	}

	// 程序入口，java程序启动后，先执行此函数。
	public static void main(String[] args) throws Exception {
		// main函数是静态函数，想要使用ChinaBankATM类的非静态成员和函数，
		// 则需要先把它构造出来，即下方的atm对象。
		ChinaBankATM atm = new ChinaBankATM(); /* new关键词调用构造函数 */

		// ATM业务逻辑开始的地方：函数run。
		atm.run();
	}
	void run() throws Exception {
		// 使用了while(true)循环，
		// 即使执行到流程结束的
		// 地方也会重新回到ATM主界面。
		while (true) {
			// 展示ATM主界面
			char ch = menu.welcomePage();
			ch = Character.toUpperCase(ch); // 即使是小写字母，在这里也会转换为大写。
			String password;
			// 通过switch语句判断用户按键
			switch (ch) {
			case 'A':
				password = menu.inputPasswordPage();
				if(account.auth(password) == false /*auth函数检查并判断密码错误了*/)
				{
					menu.displayMessagePage("输入密码错误");
					break; //此处break是中止当前switch语句内的执行，不是中止while(true)循环。
				}
				//密码校验通过了，调用服务用户的函数
				serveUser(account);
				
				//serveUser函数返回就意味着用户已经完成了取款或者存款的事项。
				//下方的break语句将结束本次switch语句，并重新回到上方while(true)循环体继续执行。
				break;//此处break请自己理解switch的语法。
			case 'T':
			case 'M':
				menu.displayMessagePage("很抱歉，功能未实现");
				break;
				
			default:
				// 按键错误
				menu.displayMessagePage("按键错误");

			}
		}
	}
	void serveUser(ChinaBankAccount acc) throws Exception {
		while(true)
		{
			char ch = menu.welcomeUserPage(acc);
			ch = Character.toUpperCase(ch);
			int money;
			switch(ch)
			{
			case 'C':
				money = menu.userDepositPage();
				account.deposit(money);
				menu.displayMessagePage("存入"+money+"成功");
				break;
			case 'Q':
				money = menu.userWithdrawPage(acc);
				if ( acc.withdraw(money) /*函数返回真代表取现成功*/ )
				{
					menu.displayMessagePage("请取走现金");
				}else
				{
					menu.displayMessagePage("账户余额不足");
				}
				break;
			case 'X':
				menu.displayMessagePage("请携带随身物品离开");
				return; //return语句中止本函数。
			
			default:
				menu.displayMessagePage("按键错误");
			}
			
		}
	}
}