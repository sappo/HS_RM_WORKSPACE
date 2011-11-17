package org.ks.stringtest.pseudoTests;

import org.ks.stringtest.WoerterBuch;

public class WoerterBuchIdiotTest {
	public static void testeGibEintrag(WoerterBuch wb, String schluessel, String eintrag) {
		System.out.println("gibEintrag("+schluessel+"): "+wb.get(schluessel)+" ["+eintrag+"]");
	}
	public static void main(String [] args) {
		System.out.println("+-----+");
		System.out.println("| wb1 |");
		System.out.println("+-----+");
		WoerterBuch wb1 = new WoerterBuch(-17);
		wb1.put("public","oeffentlich");
		System.out.print("toString(): "+wb1.toString());
		System.out.println(" [leeres Woerterbuch]");
		testeGibEintrag(wb1, "public", null);
		System.out.println();
	
		System.out.println("+-----+");
		System.out.println("| wb2 |");
		System.out.println("+-----+");
		WoerterBuch wb2 = new WoerterBuch(0);
		wb2.put("public","oeffentlich");
		System.out.print("toString(): "+wb2.toString());
		System.out.println(" [leeres Woerterbuch]");
		testeGibEintrag(wb2, "public", null);
		System.out.println();
	
		System.out.println("+-----+");
		System.out.println("| wb3 |");
		System.out.println("+-----+");
		WoerterBuch wb3 = new WoerterBuch(5);
		wb3.put("extends","erweitert");
		wb3.put("private","privat");
		wb3.put("public","oeffentlich");
		wb3.put("protected","geschuetzt");
		wb3.put("package","Paket");
		wb3.put("implements","implementiert");
		System.out.print("toString(): "+wb3.toString());
		System.out.print(" [(extends->erweitert), (package->Paket), (private->privat)");
		System.out.println(", (protected->geschuetzt), (public->oeffentlich)]");
		testeGibEintrag(wb3, "public","oeffentlich");
		testeGibEintrag(wb3, "protected","geschuetzt");
		testeGibEintrag(wb3, "private","privat");
		testeGibEintrag(wb3, "package","Paket");
		testeGibEintrag(wb3, "implements", null);
		testeGibEintrag(wb3, "extends","erweitert");
		System.out.println();
	}
}
