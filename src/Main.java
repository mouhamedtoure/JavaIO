import java.io.*;
import java.net.URL;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {

		// Premier exemple: écriture de types primitifs java
		try {

			FileOutputStream fos = new FileOutputStream("monfichier.dat");
			// filtre qui permet de manipuler les objets java

			BufferedOutputStream bos = new BufferedOutputStream(fos);
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeDouble(18.5);
			dos.writeInt(14);
			dos.close();

			FileInputStream fis = new FileInputStream("monfichier.dat");
			DataInputStream dis = new DataInputStream(fis);

			System.out.println(dis.readDouble());
			System.out.println(dis.readInt());

			dis.close();

			// Deuxieme exemple avec un fichier texte

			FileWriter fw = new FileWriter("monfichier.txt");

			BufferedWriter bw = new BufferedWriter(fw);

			PrintWriter pw = new PrintWriter(bw);
			// les methodes de PrintWiter ne retournent pas d'exception

			pw.println("une ligne de texte");
			pw.println("une deuxième ligne de texte");

			bw.write("une troisième ligne de texte");
			bw.newLine();
			bw.write("une quatrième ligne de texte");
			bw.close();
			pw.close();

			// Troisieme exemple: lecture depuis un fichier texte
			FileReader fr = new FileReader("monfichier.txt");
			BufferedReader br = new BufferedReader(fr);

			String ligne;
			while ((ligne = br.readLine()) != null) {

				System.out.println(ligne);

			}
			br.close();

			// 4 ème exemple lecture depuis une url
			//
			// URL url = new URL("http://www.lemonde.fr");
			// InputStream is = url.openStream();
			//
			// Reader ir = new InputStreamReader(is, "ISO-8859-1");
			// BufferedReader br2 = new BufferedReader(ir);
			//
			// String ligne2;
			// while ((ligne2 = br2.readLine()) != null) {
			// System.out.println(ligne2);
			//
			// }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// Cinquième exemple: sauvegarde d'objets dans un ficheir
		String dateString = "2000-02-14";
		String dateString2 = "1996-10-03";
		String dateString3 = "1993-11-05";

		LocalDate date = LocalDate.parse(dateString);
		LocalDate date2 = LocalDate.parse(dateString2);
		LocalDate date3 = LocalDate.parse(dateString3);

		Compte c1 = new Compte("Grapinet", "Emilie", date, "emilie.grapinet@hotmail.com");
		Compte c2 = new Compte("Antonio", "Julien", date2, "julien.antonio@hotmail.com");
		Compte c3 = new Compte("Belcourt", "William", date3, "william.belcourt@hotmail.com");

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("mescomptes.dat")));
			oos.writeObject(c1);
			oos.writeObject(c2);
			oos.writeObject(c3);
			oos.flush();
			oos.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("mescomptes.dat")));
			Compte c4 = (Compte) ois.readObject();
			Compte c5 = (Compte) ois.readObject();
			Compte c6 = (Compte) ois.readObject();
			
			System.out.println(c4);
			System.out.println(c5);
			System.out.println(c6);
			ois.close();

		} catch (ClassNotFoundException xc) {
			xc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		
	}
	
	

}
