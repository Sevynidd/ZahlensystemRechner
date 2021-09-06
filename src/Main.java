import java.util.Scanner;
import java.lang.Math;

public class Main {

	private boolean nochmal = true;
	private Scanner scanner;

	private int erstesZahlensystem;
	private int zweitesZahlensystem;
	private String umzuformendeZahl;

	public static void main(String[] args) {

		Main main = new Main();

		while (main.nochmal) {

			System.out.println("Geben Sie eine Zahl ein:\nVon Dezimal in anderes Zahlensystem: 1");
			System.out.println("Von Zahlensystem in Zahlensystem: 2");

			while (true) {

				main.scanner = new Scanner(System.in);
				Integer s = Integer.parseInt(main.scanner.next());

				if (s.equals(2)) {

					String zahl = main.ZahlensystemInZahlensystem();

					for (int i = zahl.length() - 1; i >= 0; i--) {
						if (zahl.charAt(i) != 'Z') {
							System.out.print(zahl.charAt(i));
						}

					}

					break;
				} else if (s.equals(1)) {
					String zahl = main.DezimalInZahlensystem();
					for (int i = zahl.length() - 1; i >= 0; i--) {
						if (zahl.charAt(i) != 'Z') {
							System.out.print(zahl.charAt(i));
						}

					}
					break;
				}

				break;
			}

			main.nochmal = false;
			main.umzuformendeZahl = null;

		}
		main.scanner.close();

	}

	private String DezimalInZahlensystem() {
		// TODO: Muss noch geschrieben werden

		System.out.println("Geben Sie die umzuformende Zahl ein: ");
		Scanner umzuformendeZahlScanner = new Scanner(System.in);

		while (true) {
			umzuformendeZahl = umzuformendeZahlScanner.next();
			break;
		}

		System.out.println("Geben Sie das zweite Zahlensystem ein (Bsp. 16 fuer Hexadezimal): ");
		Scanner scanner2tesZahlensystem = new Scanner(System.in);

		while (true) {
			zweitesZahlensystem = Integer.parseInt(scanner2tesZahlensystem.next());
			break;
		}

		String rueckgabewert = null;

		switch (zweitesZahlensystem) {
		case (2):
			rueckgabewert = UmrechnenVon10erInZahlensystem(Integer.parseInt(umzuformendeZahl), 2);
			break;
		case (8):
			rueckgabewert = UmrechnenVon10erInZahlensystem(Integer.parseInt(umzuformendeZahl), 8);
			break;
		case (16):
			// TODO: Besonderer Fall
			rueckgabewert = UmrechnenVon10erInZahlensystem(Integer.parseInt(umzuformendeZahl), 16);
			break;

		}

		umzuformendeZahlScanner.close();
		return rueckgabewert;
	}

	private String ZahlensystemInZahlensystem() {

		System.out.println("Geben Sie das erste Zahlensystem ein (Bsp. 16 fuer Hexadezimal): ");
		Scanner scanner1 = new Scanner(System.in);

		while (true) {
			erstesZahlensystem = Integer.parseInt(scanner1.next());
			break;
		}

		System.out.println("Geben Sie das zweite Zahlensystem ein (Bsp. 16 fuer Hexadezimal): ");
		Scanner scanner2 = new Scanner(System.in);

		while (true) {
			zweitesZahlensystem = Integer.parseInt(scanner2.next());
			break;
		}

		System.out.println("Geben Sie die umzuformende Zahl ein: ");
		Scanner umzuformendeZahlScanner = new Scanner(System.in);

		while (true) {
			umzuformendeZahl = umzuformendeZahlScanner.next();
			break;
		}

		// dezimalTemp ist in das dezimalFormat umgerechnet
		int dezimalTemp = 0;

		switch (erstesZahlensystem) {

		case (2):
			for (int i = 0; i < umzuformendeZahl.length(); i++) {
				dezimalTemp = UmrechnenVonZahlensystemIn10er(dezimalTemp, 2, i);
			}
			break;
		case (8):
			for (int i = 0; i < umzuformendeZahl.length(); i++) {
				dezimalTemp = UmrechnenVonZahlensystemIn10er(dezimalTemp, 8, i);
			}
			break;
		case (16):
			for (int i = 0; i < umzuformendeZahl.length(); i++) {

				switch (umzuformendeZahl.charAt(umzuformendeZahl.length() - (i + 1))) {
				case ('A'):
					dezimalTemp += 10 * ((int) Math.pow(16, i));
					break;
				case ('B'):
					dezimalTemp += 11 * ((int) Math.pow(16, i));
					break;
				case ('C'):
					dezimalTemp += 12 * ((int) Math.pow(16, i));
					break;
				case ('D'):
					dezimalTemp += 13 * ((int) Math.pow(16, i));
					break;
				case ('E'):
					dezimalTemp += 14 * ((int) Math.pow(16, i));
					break;
				case ('F'):
					dezimalTemp += 15 * ((int) Math.pow(16, i));
					break;
				default:
					dezimalTemp = UmrechnenVonZahlensystemIn10er(dezimalTemp, 16, i);
					break;
				}

			}
			break;
		}

		String rueckgabewert = null;

		switch (zweitesZahlensystem) {
		case (2):
			rueckgabewert = UmrechnenVon10erInZahlensystem(dezimalTemp, 2);
			break;
		case (8):
			rueckgabewert = UmrechnenVon10erInZahlensystem(dezimalTemp, 8);
			break;
		case (16):
			// TODO: Besonderer Fall
			rueckgabewert = UmrechnenVon10erInZahlensystem(dezimalTemp, 16);
			break;

		}

		scanner1.close();
		scanner2.close();
		umzuformendeZahlScanner.close();

		return rueckgabewert;

	}

	private int UmrechnenVonZahlensystemIn10er(int dezimalTemp, int zahlensystem, int i) {

		dezimalTemp += Character.getNumericValue(umzuformendeZahl.charAt(umzuformendeZahl.length() - (i + 1)))
				* ((int) Math.pow(zahlensystem, i));

		return dezimalTemp;
	}

	private String UmrechnenVon10erInZahlensystem(int originalZahl, int zahlensystem) {

		int zahlTemp = originalZahl;
		int rest = 0;
		String restFuerRueckgabe = "Z";

		while (zahlTemp != 0) {
			rest = zahlTemp % zahlensystem;
			restFuerRueckgabe += Integer.toString(rest);
			zahlTemp = (zahlTemp - rest) / zahlensystem;
		}

		return restFuerRueckgabe;
	}

}
