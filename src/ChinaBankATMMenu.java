import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalTime;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.AttributedString;

//本类实现ATM界面的展示、接收用户按键输入功能。

/**
 * Terminal是一个十分好用的类，
 * 通过它可以实现屏幕清屏、捕捉用户输入，
 * 调整光标输出位置等功能。
 * 
 * 因此不再使用java原生的以下方法：
 *   System.out.print*()
 *   Scanner.next*()
 */

public class ChinaBankATMMenu {
	Terminal terminal;
	ChinaBankATMMenu() throws IOException
	{
		terminal = TerminalBuilder.builder().encoding(Charset.forName("GBK")).system(true).build();
		terminal.enterRawMode();
	}
	
	char welcomePage() throws Exception
	{
		terminal.enterRawMode();
		terminal.puts(Capability.clear_screen);
		String home =
				  "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
				+ "┃  欢迎使用中国银行ATM    ┃\n"
				+ "┃    请按键选择功能：     ┃\n"
				+ "┃ [A]  存取款             ┃\n"
				+ "┃ [T]  汇款               ┃\n"
				+ "┃ [M]  账户维护           ┃\n"
				+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
		terminal.writer().print(home);
		int ch = terminal.reader().read();
		return (char) ch;
	}

	String inputPasswordPage()
	{
		String str =
				  "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
				+ "┃  欢迎使用中国银行ATM    ┃\n"
				+ "┃    请输入密码：         ┃\n"
				+ "┃   ╭────────────────╮    ┃\n"
				+ "┃   │                │    ┃\n"
				+ "┃   ╰────────────────╯    ┃\n"
				+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛";
		terminal.puts(Capability.clear_screen);
		terminal.writer().print(str);
		terminal.puts(Capability.cursor_address, 4, 5);
		
		LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        // 输入密码（显示*）
        String password = reader.readLine("     ", '*');
        return password;
	}
	
	char welcomeUserPage(ChinaBankAccount account) throws Exception 
	{
		terminal.enterRawMode();
		terminal.puts(Capability.clear_screen);
		int now = LocalTime.now().getHour();
        String time;
        if (now >= 5 && now < 12) {
        	time = "早安";
        } else if (now >= 12 && now < 18) {
        	time = "午安";
        } else {
        	time = "晚安";
        }
        
        
		String greeting = 
				  "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
				+ "┃  尊敬的%s%s，%s ┃\n".formatted( account.name, account.gender ,time)
				+ "┃ 余额：￥[%10.2f ]   ┃\n".formatted((float)account.query())
				+ "┃ [C]  存入               ┃\n"
				+ "┃ [Q]  取现               ┃\n"
				+ "┃ [X]  登出               ┃\n"
				+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛";
		terminal.writer().print(greeting);
		int ch = terminal.reader().read();
		return (char) ch;
	}
	
	int userDepositPage()
	{
		String str =
				  "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
				+ "┃ 请放入人民币：          ┃\n"
				+ "┃   ┌────────────────┐    ┃\n"
				+ "┃   │                │    ┃\n"
				+ "┃   └────────────────┘    ┃\n"
				+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛";
		terminal.puts(Capability.clear_screen);
		terminal.writer().print(str);
		terminal.puts(Capability.cursor_address, 3, 5);
		LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        // 输入金额
        String userinput = reader.readLine("     "); 
        //用户按键输入的字符串，例如"500"，java中它的类型是字符串不是int，
        //因此在这里通过parseInt将它转化为int类型的数值并保存至变量number中。
        int number = Integer.parseInt(userinput);
        return number;
	}
	
	int userWithdrawPage(ChinaBankAccount account)
	{
		String str =
				  "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
				+ "┃  余额：￥[%10.2f ]  ┃\n".formatted((float)account.query())
				+ "┃ 请输入取现金额：        ┃\n"
				+ "┃   ┌────────────────┐    ┃\n"
				+ "┃   │                │    ┃\n"
				+ "┃   └────────────────┘    ┃\n"
				+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛";
		terminal.puts(Capability.clear_screen);
		terminal.writer().print(str);
		terminal.puts(Capability.cursor_address, 4, 5);
		LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        // 输入金额
        String userinput = reader.readLine("     "); 
        //用户按键输入的字符串，例如"500"，java中它的类型是字符串不是int，
        //因此在这里通过parseInt将它转化为int类型的数值并保存至变量number中。
        int number = Integer.parseInt(userinput);
        return number;
	}
	void displayMessagePage(String msg) throws Exception
	{
		String message =
				  "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
				+ "┃  欢迎使用中国银行ATM    ┃\n"
				+ "┃                         ┃\n"
				+ "┃                         ┃\n"
				+ "┃                         ┃\n"
				+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛";
		terminal.puts(Capability.clear_screen);
		terminal.writer().print(message);
		
		int len = AttributedString.fromAnsi(msg).columnLength();
		int max = 25;
		int leftpad;
		leftpad =  (max-len)/2;
		terminal.puts(Capability.cursor_address, 2, leftpad);
		terminal.writer().print("┌" + "─".repeat(len+1) + "┐");
		terminal.puts(Capability.cursor_address, 4, leftpad);
		terminal.writer().print("└" + "─".repeat(len+1) + "┘");
		terminal.puts(Capability.cursor_address, 3, leftpad);
		terminal.writer().print("│" + " " + msg  + "│");
		terminal.puts(Capability.cursor_address, 6, 0);
		sleep(2000);
	}
	
	void displayMessagePage2(String msg) throws Exception
	{
		//此函数废弃了，使用另外一个。另外一个效果比较好。
		String message =
				  "┏━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
				+ "┃  欢迎使用中国银行ATM    ┃\n"
				+ "┃   ┌────────────────┐    ┃\n"
				+ "┃   │123456789abcdefg│    ┃\n"
				+ "┃   └────────────────┘    ┃\n"
				+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━┛";
      
		int len = AttributedString.fromAnsi(msg).columnLength();
		int max = AttributedString.fromAnsi("123456789abcdefg").columnLength();
		terminal.puts(Capability.clear_screen);
		terminal.writer().print(message);
		terminal.puts(Capability.cursor_address, 3, 5);
		if (len >= max) {
			terminal.writer().print(msg);
			sleep(2000);
			return;
		}
		int leftpad;
		int rightpad;
		if( (max-len) % 2 ==0 ) {
			leftpad = rightpad =  (max-len)/2;
		}else
		{
			leftpad = (max - len) / 2 + 1;
			rightpad = (max - len) /2;
		}
		terminal.writer().print(" ".repeat(leftpad) + msg + " ".repeat(rightpad));
		sleep(2000);
	}
	void sleep(long millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
