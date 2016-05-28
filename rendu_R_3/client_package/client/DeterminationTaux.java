// Determination of the rate thanks to a fixed rate given by the head quarter

package client;

public class DeterminationTaux {

	int duree = 0;    // ici il faut rentrer la durée du prêt
	int age = 0;      // ici il faut rentrer l'âge de l'emprunteur
	boolean sante;	  // ici il faut 0 si l'emprunteur est en bonne santé et 1 si non
	boolean histpre; 
	double Tauxfixe = 2;
	double nouveauTaux = 0;
	
	public DeterminationTaux(int duree, int age, boolean sante, boolean histpre, double tauxfixe) {
		this.duree = duree;
		this.age = age;
		this.sante = sante;
		this.histpre = histpre;
		Tauxfixe = tauxfixe;
	}
	
	public  double changementTaux(){
		
		double tauxcalc = this.Tauxfixe;
		if (duree < 10 && duree >= 2){
			tauxcalc -=0.025;
			System.out.println("1");
		}
		else if (duree < 30 && duree > 20){
			tauxcalc += 0.025; 
			System.out.println("2");
		}
		
		if (age < 26 && age > 16){
			tauxcalc +=0.025;
			System.out.println("3");
		}
		else if (age < 60 && age > 41){
			tauxcalc -= 0.025;
			System.out.println("4");
		}
		if (sante != false){
			tauxcalc += 0.1; 
			System.out.println("5");
		}
		if (histpre == true) {
			tauxcalc -= 0.05;
			System.out.println("6");
		}
		else if (histpre == false){
			tauxcalc += 0.05;
			System.out.println("7");
		}
		return tauxcalc;
		//if	
	}
		
//	public static void main(String[] args) {
//		DeterminationTaux d1 =new DeterminationTaux(20, 18, true, false, 2);
//		System.out.println(d1.changementTaux());
//	}
 }	
	
	
