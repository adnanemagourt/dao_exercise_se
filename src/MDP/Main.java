package MDP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		SingleConnexion.getConnection();
		int req = 0;
		System.out.print("Welcome aboard!\nWhich language do you wish to continue with?\n en/fr: ");
		Scanner scan = new Scanner(System.in);
		String lang = scan.nextLine();
		File f = new File(lang + ".properties");
		Properties properties = new Properties();
		try {
			FileInputStream i = new FileInputStream(f);
        	properties.load(i);
        }catch(IOException e) {
        	e.printStackTrace();
        }
		PlaceDao placedao = new PlaceDao();
		TripDao tripdao = new TripDao();
		loop: while(req!=8) {
			System.out.printf(properties.getProperty("menu"));
			req = scan.nextInt();
			switch(req) {
			case 8:
				break loop;
			case 1:
				System.out.print(properties.getProperty("1_1"));
				String name = scan.nextLine();
				Place place = new Place((long) 0, name);
				placedao.add(place);
				break;
			case 2:
				System.out.print(properties.getProperty("2_1"));
				long id = scan.nextLong();
				if(id == 0) {
					System.out.print(properties.getProperty("2_2"));
					String name1 = scan.nextLine();
					placedao.getAll(name1);
				}else {
					placedao.getOne(id);
				}
				break;
			case 3:
				System.out.print(properties.getProperty("3_1"));
				long id1 = scan.nextLong();
				System.out.println(properties.getProperty("3_2"));
				placedao.getOne(id1);
				System.out.print(properties.getProperty("3_3"));
				String name2 = scan.nextLine();
				placedao.edit(id1, name2);
				System.out.println(properties.getProperty("3_4"));
				break;
			case 4:
				System.out.print(properties.getProperty("4_1"));
				long id2 = scan.nextLong();
				placedao.delete(id2);
				System.out.println(properties.getProperty("4_2"));
				break;
			case 5:
				System.out.print(properties.getProperty("5_1"));
				long id3 = scan.nextLong();
				Place place1 = placedao.getOne(id3);
				System.out.print(properties.getProperty("5_2"));
				id3 = scan.nextLong();
				Place place2 = placedao.getOne(id3);
				System.out.print(properties.getProperty("5_3"));
				double price = scan.nextDouble();
				tripdao.add(new Trip((long) 0, place1, place2, price));
				break;
			case 6:
				System.out.print(properties.getProperty("6_1"));
				String depart = scan.nextLine();
				System.out.print(properties.getProperty("6_2"));
				String dest = scan.nextLine();
				tripdao.getAll(depart, dest);
				break;
			case 7:
				System.out.print(properties.getProperty("7_1"));
				long id4 = scan.nextLong();
				tripdao.delete(id4);
				System.out.println(properties.getProperty("7_2"));
				break;
			default:
				break;
			}
		}
		
		scan.close();
	}

}
