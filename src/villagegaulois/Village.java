package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtalMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtalMarche);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for(int i = 0; i<nbEtals;i++) {
				this.etals[i] = new Etal();
			}
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if(!this.etals[indiceEtal].isEtalOccupe()) {
				this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
				System.out.println("l'étal est attribué.");
			}
		}
		
		int trouverEtalLibre() {
			for(int i = 0; i < this.etals.length; i++) {
				if(!this.etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		Etal[] trouverEtals(String produit) {
			Etal[] etalProduit = new Etal[this.etals.length];
			int nbEtalProduit = 0;
			for(int i = 0; i < this.etals.length; i++) {
				if(this.etals[i].contientProduit(produit)) {
					etalProduit[nbEtalProduit] = this.etals[i];
					nbEtalProduit++;
				}
			}
			
			return etalProduit;
		}
		
		Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0; i < this.etals.length; i++) {
				if(this.etals[i].getVendeur() == gaulois) {
					return this.etals[i];
				}
			}
			return null;
		}
		
		String AfficherMarche(){
			int nbEtalVide = 0;
			
			for(int i = 0; i < this.etals.length; i++) {
				if(this.etals[i].isEtalOccupe()) {
					this.etals[i].afficherEtal();
				}else {
					nbEtalVide++;
				}
			}
			String message = "Il reste " + nbEtalVide + "étals non utilisés dans le marché.\n";
			return message;
		}
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur + "cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		chaine.append("Le vendeur " + vendeur + " vend des fleurs à l'étal n°" + this.marche.trouverEtalLibre() + "\n");
		return chaine.toString();
	}
	
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalProduit = this.marche.trouverEtals(produit);
		chaine.append("Les vendeurs qui proposent des " + produit + " sont : \n");
		for(int i = 0; i < etalProduit.length; i++) {
			chaine.append("-" + etalProduit[i].getVendeur() + "\n");
		}
		return chaine.toString();
	}
	
}