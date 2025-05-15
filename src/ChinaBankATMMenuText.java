import java.time.LocalTime;
import java.util.Scanner;

public class ChinaBankATMMenuText implements IChinaBankATMMenu {
	Scanner scan;
	public ChinaBankATMMenuText()
	{
		scan = new Scanner(System.in);
	}
	@Override
	public char welcomePage() {
		String home = "欢迎使用中国银行ATM\n"
				+ "  请按键选择功能：\n"
				+ "[A]  存取款\n"
				+ "[T]  汇款\n"
				+ "[M]  账户维护\n"
				+ "你的输入是：";
		System.out.print(home);
		var s = scan.next();
		s = s.toUpperCase();
		return s.charAt(0);
	}

	@Override
	public String inputPasswordPage() {
		System.out.print("请输入密码：");
		return scan.next();
	}

	@Override
	public char welcomeUserPage(ChinaBankAccount account) {
		int now = LocalTime.now().getHour();
        String time;
        if (now >= 5 && now < 12) {
        	time = "早安";
        } else if (now >= 12 && now < 18) {
        	time = "午安";
        } else {
        	time = "晚安";
        }
        String greeting = "尊敬的%s%s，%s\n".formatted(account.name, account.gender ,time)
        		+ "余额：￥[%10.2f ]\n".formatted((float)account.query())
				+ "  请按键选择功能：\n"
				+ "[C]  存入\n"
				+ "[Q]  取现\n"
				+ "[X]  登出\n"
				+ "你的输入是：";
        System.out.print(greeting);
        var s = scan.next();
		s = s.toUpperCase();
		return s.charAt(0);
	}

	@Override
	public int userDepositPage() {
		System.out.print("请输入存入金额：");
		return scan.nextInt();
	}

	@Override
	public int userWithdrawPage(ChinaBankAccount account) {
		System.out.println("你当前余额：￥[%10.2f ]\n".formatted((float)account.query()));
		System.out.print("请输入取现金额：");
		return scan.nextInt();
	}

	@Override
	public void displayMessagePage(String msg, long millis) {
		System.out.println("消息："+msg);
		if(millis<=0)
			return;
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
