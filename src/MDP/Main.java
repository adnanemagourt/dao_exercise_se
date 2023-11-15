package MDP;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		SingleConnexion.getConnection();
		int req = 0;
		System.out.println("Welcome aboard!");
		Scanner scan = new Scanner(System.in);
		PlaceDao placedao = new PlaceDao();
		TripDao tripdao = new TripDao();
		loop: while(req!=8) {
			System.out.printf("\nWhat do you want to do?\n1-Add a place\n2-Find a place\n3-Edit a place"
					+ "\n4-Remove a place\n5-Add a trip\n6-Find a trip\n7-Remove a trip\n8-Quit\n");
			req = scan.nextInt();
			switch(req) {
			case 8:
				break loop;
			case 1:
				System.out.print("Name: ");
				String name = scan.nextLine();
				Place place = new Place((long) 0, name);
				placedao.add(place);
				break;
			case 2:
				System.out.print("Id(0 if unknown): ");
				long id = scan.nextLong();
				if(id == 0) {
					System.out.print("Name (or beginning of name): ");
					String name1 = scan.nextLine();
					placedao.getAll(name1);
				}else {
					placedao.getOne(id);
				}
				break;
			case 3:
				System.out.print("Id: ");
				long id1 = scan.nextLong();
				System.out.println("Current information:");
				placedao.getOne(id1);
				System.out.print("\nNew name: ");
				String name2 = scan.nextLine();
				placedao.edit(id1, name2);
				System.out.println("\nValues edited successfully!");
				break;
			case 4:
				System.out.print("Id: ");
				long id2 = scan.nextLong();
				placedao.delete(id2);
				System.out.println("\nEntry deleted successfully!");
				break;
			case 5:
				System.out.print("Id of departure: ");
				long id3 = scan.nextLong();
				Place place1 = placedao.getOne(id3);
				System.out.print("Id of destination: ");
				id3 = scan.nextLong();
				Place place2 = placedao.getOne(id3);
				System.out.print("Price of trip: ");
				double price = scan.nextDouble();
				tripdao.add(new Trip((long) 0, place1, place2, price));
				break;
			case 6:
				break;
			case 7:
				break;
			default:
				break;
			}
		}
		
		scan.close();
	}

}
