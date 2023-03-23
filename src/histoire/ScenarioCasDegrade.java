package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois obelix = new Gaulois("Obélix", 25);
		
		//NullPointeur exception
		etal.libererEtal();
		
		//etal.acheterProduit(1, null);
		
		try {
			etal.acheterProduit(-1, obelix);

		}
		catch(IllegalArgumentException e) {
			System.out.println("--- La quantité de produit à acheter n'est pas positive. ---");
		}
		
		
		try {
			etal.acheterProduit(1, obelix);
		}
		catch(IllegalStateException e) {
			System.out.println("--- L'étal n'est pas occupé. ---");
		}
		
		System.out.println("Fin du test");
		}

}
