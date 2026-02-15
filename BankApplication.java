package benkyou.exam03;

import java.util.Scanner;

public class BankApplication {
	private static Account[] accountArray = new Account[10];
	private static int nextAccountNumber = 1;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("1. 口座開設 | 2. 口座リスト | 3. 入金 | 4. 出金 | 5. 振込 | 6. 口座削除 | 7. 終了");
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("選択>");
			
			int selectNo;
			
			try {
				selectNo = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e){
				System.out.println("数字で入力してください");
				continue;
			}
			
			switch (selectNo) {
			case 1:
				createAccount();
				break;
			case 2:
				accountList();
				break;
			case 3:
				deposit();
				break;
			case 4:
				withdraw();
				break;
			case 5:
				Transfer();
				break;
			case 6:
				deleteAccount();
				break;
			case 7:
				run = false;
				break;
			default:
				System.out.println("存在しないメニュー番号です");
				break;
			}
		}
		System.out.println("プログラム終了");
		scanner.close();		
		
	}
	
	//↓ 口座検索メソッド
	private static Account findAccount(String accountNum) {
		Account account = null;
		for (int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] != null && accountArray[i].getAccountNum().equals(accountNum)) {
					account = accountArray[i];
			}
		}
		return account;
	}
	
	//空白入力防止
	private static String readString(String message) {
		while (true) {
			System.out.println(message);
			String input = scanner.nextLine();
			
			if (input.isBlank()) {
				System.out.println("空白は入力できません");
				continue;
			}
		}
	}
	
	//↑ 口座検索メソッド
	
	
	//↓ 1.口座開設メソッド
	//6桁の口座番号を000001から連番で生成するメソッド
	private static String generateAccountNumber() {
		return String.format("%06d", nextAccountNumber++);
	}
	
	private static void createAccount() {
		System.out.println("--------------------------------------");
		System.out.println("口座開設");
		System.out.println("--------------------------------------");
		
		//口座番号を自動生成
		String accountNum = generateAccountNumber();
		System.out.println("口座番号: " + accountNum);
		
		String owner;
		
		while (true) {
			System.out.println("口座名を入力してください ");
			owner = scanner.nextLine();

			if(owner.isBlank()) {
				System.out.println("空白は不可です。");
				continue;
			}
		
			if(!owner.matches("^[a-zA-Zぁ-んァ-ン一-龥]+$")) {
				System.out.println("文字列のみ入力可能です");
				continue;
			}
		
		break;
		
		}
		
		
		
		
		int balance;
		
		while (true) {
			System.out.println("初回入金額を入力してください ");

			try {
				balance = Integer.parseInt(scanner.nextLine());
				if (balance < 1) {
					System.out.println("初回入金は1円以上です");
					continue;
				} 
			//条件クリア
			break;
			
			} catch (NumberFormatException e) {
				System.out.println("金額は数字で入力してください。");
			}
		
		}
		
		Account newAccount = new Account(accountNum, owner, balance);
		

		for (int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] == null) {
				accountArray[i] = newAccount;
				System.out.println("---- 口座が開設されました -----");
				System.out.printf(
						"口座番号: %s | 口座名: %s | 残高: %d円\n",
						newAccount.getAccountNum(),
						newAccount.getOwner(),
						newAccount.getBalance()
				);
			return;
			}
		}
		System.out.println("口座は10口までです");
	}
	

	//↑ 1.口座開設メソッド

	//↓ 2.口座リスト
	private static void accountList() {
		System.out.println("--------------------------------------");
		System.out.println("口座リスト");
		System.out.println("--------------------------------------");
		
		boolean exists = false;
		
		for (int i = 0; i < accountArray.length; i++) {
			Account account = accountArray[i];
			if (account != null) {
			System.out.printf(
					"口座番号: %s | 口座名: %s | 残高: %d円\n",
					account.getAccountNum(),
					account.getOwner(),
					account.getBalance()
			);
			exists = true;
			}
		}
	
		if(!exists) {
			System.out.println("開設された口座がありません。");
		}	
	}
	//↑ 2. 口座メソッド
	
	
	//↓ 3.入金メソッド
	private static void deposit() {
		System.out.println("--------------------------------------");
		System.out.println("入金");
		System.out.println("--------------------------------------");
		System.out.println("口座番号: 例)000203の場合 203も入力可能");
		
		int input = Integer.parseInt(scanner.nextLine());
		String accountNum = String.format("%06d", input);
		
		Account account = findAccount(accountNum);
		if( account == null ) {
			System.out.println("結果：存在しない口座です。");
			return;
		}
		
		System.out.println("入金額: ");
		int money;
		try {
			money = Integer.parseInt(scanner.nextLine());
			if (money <= 0) {
				System.out.println("入金額が1以上です");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("金額は数字で入力してください");
			return;
		}
		
		account.SetBalance(account.getBalance() + money);
		System.out.printf
		("結果：　入金が成功しました。（現在の残高：%d円\n)", account.getBalance());
		
	}
	//↑ 3.入金メソッド
	
	//↓ 4.出金メソッド
	private static void withdraw() {
		System.out.println("--------------------------------------");
		System.out.println("出金");
		System.out.println("--------------------------------------");
		
		System.out.println("口座番号: 例）000203の場合 203も入力可能");
		
		int input = Integer.parseInt(scanner.nextLine());
		String accountNum = String.format("%06d", input);
		
		Account account = findAccount(accountNum);
		if (account == null) {
			System.out.println("結果： 存在しない口座です。");
			return;
		}
		
		System.out.println("出金額: ");
		int money;
		try {
			money = Integer.parseInt(scanner.nextLine());
			if(money <= 0) {
				System.out.println("出金額は1円以上です");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("金額は数字で入力してください");
			return;
		}
		
		if (account.getBalance() < money) {
			System.out.printf("結果：残高不足です。(現在残高: %d円)\n", account.getBalance());
		} else {
			account.SetBalance(account.getBalance() - money);
			System.out.printf("結果： 出金が成功しました。（現在残高: %d円）\n", account.getBalance());
		}
	}
	
	//↑ 4.出金メソッド
	
	//↓ 5.振込メソッド
	private static void Transfer() {
		System.out.println("--------------------------------------");
		System.out.println("振込");
		System.out.println("--------------------------------------");
		System.out.println("振込元の口座番号: 例)000203の場合 203も入力可能");
	
		int input = Integer.parseInt(scanner.nextLine());
		String fromAccountNum = String.format("%06d", input);
		System.out.println(fromAccountNum);
		
		Account fromAccount = findAccount(fromAccountNum);
		if (fromAccount == null) {
			System.out.println("結果： 振込元口座が存在しません");
			return;
		}
		
		System.out.println("振込先の口座番号: 例)000203の場合 203も入力可能");
		
		int input2 = Integer.parseInt(scanner.nextLine());
		String toAccountNum = String.format("%06d", input2);
		System.out.println(toAccountNum);
		
		Account toAccount = findAccount(toAccountNum);
		if (toAccount == null) {
			System.out.println("結果： 振込先口座が存在しません");
			return;
		}
		
		if(fromAccount == toAccount) {
			System.out.println("結果：同一口座への振込はできません");
			return;
		}
		
		System.out.println("振込金額を入力してください");
		int amount;
		
		try {
			amount = Integer.parseInt(scanner.nextLine());
			if(amount <= 0) {
				System.out.println("振込金額は1円以上です");
				return;
			}
			
		} catch(NumberFormatException e) {
			System.out.println("金額は数字で入力してください");
			return;
		}
		
		if(fromAccount.getBalance() < amount) {
			System.out.printf(
					"結果：残高不足です。(現在残高: %d円)\n", 
					fromAccount.getBalance());
			return;
		}
		
		fromAccount.SetBalance(fromAccount.getBalance() - amount); 
		toAccount.SetBalance(toAccount.getBalance() + amount);
		
		System.out.printf(
				"結果：振込が完了しました。(振込元の残高: %d円)\n", 
				fromAccount.getBalance());
	}
	//↑ 5.振込メソッド

	//↓ 6.口座削除メソッド
	private static void deleteAccount() {
		System.out.println("--------------------------------------");
		System.out.println("口座削除");
		System.out.println("--------------------------------------");
		
		System.out.println("口座番号: 例)000203の場合 203も入力可能");
		
		int input = Integer.parseInt(scanner.nextLine());
		String accountNum = String.format("%06d", input);
		
		for (int i = 0; i < accountArray.length; i++) {
			Account account = accountArray[i];
			if (accountArray[i] != null &&
				accountArray[i].getAccountNum().equals(accountNum)) {
				
			//確認メッセージ
				System.out.println("口座番号: " + account.getAccountNum());
				System.out.println("口座名: " + account.getOwner());
				System.out.println("残高: " + account.getBalance() + "円");
				
				
				while (true) {
					System.out.println("本当に削除しますか？(はい/いいえ): ");
					String answer = scanner.nextLine();
					
					if (answer.equals("はい")) {
						accountArray[i] =null;
						System.out.println("口座を削除しました");
						return;
					} else if (answer.equals("いいえ")) {
						System.out.println("削除をキャンセルしました");
						return;
					} else {
						System.out.println("「はい」または「いいえ」で入力してください");
					}
				
				}
			}
		}
		
		System.out.println("口座が見つかりませんでした");
	}
	//↑ 6.口座削除メソッド

}
