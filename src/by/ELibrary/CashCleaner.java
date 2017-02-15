package by.ELibrary;

import java.util.concurrent.TimeUnit;

// Класс для очистки кэша приложения от книг, к которым не обращались последние 60 секунд
public class CashCleaner implements Runnable {
	private int key;
	
	public CashCleaner(int key) {
		this.key = key;
	}
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(60);
			Controller.booksCash.remove(key);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
