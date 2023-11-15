package MDP;

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
				
				break;
			}
		}
		
		scan.close();
	}

}
