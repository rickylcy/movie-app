import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class Main_Func {

	static int memberid = -1;
	static Member memberLogined;
	static MemberCollection members = new MemberCollection();
	static MovieCollection movies = new MovieCollection();
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	
	static boolean staffMenu = false;
	static boolean memberMenu = false;
	static boolean quit = false;
	
	static String staff_id = "staff";
	static String staff_pwd = "today123";
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int option = -1;
	
		do {
			try {
				do
				{
					System.out.println();
					System.out.println("Welcome to the Community Libufferary");
					System.out.println("========Main Menu========");
					System.out.println(" 1. Staff Login");
					System.out.println(" 2. Member Login");
					System.out.println(" 0. quit");
					System.out.println("=========================");
					System.out.println();
					System.out.println(" Please make a selection (1-2, or 0 to quit):");
				try {
				option = Integer.parseInt(buffer.readLine());
				} catch (NumberFormatException nfe) {
					System.out.println("Please put correct input");
				}
				} while (option < 0 & option > 2);
				if (option == 1)
				{
					staffMenu = true;
				}
				else if (option == 2)
				{
					memberMenu = true;
				}
				else if (option == 0)
				{
					quit = true;
				}
				
				if (staffMenu) {
					System.out.println("Enter username: ");
					String user_id = buffer.readLine();
					System.out.println("Enter Password: ");
					String user_pwd = buffer.readLine();

					if (user_id.equals(staff_id) && user_pwd.equals(staff_pwd)) {
						do {
							System.out.println("========Staff Menu========");
							System.out.println("1. Add a new movie DVD");
							System.out.println("2. Remove a movie DVD");
							System.out.println("3. Register a new member");
							System.out.println("4. Find a registered member's phone number");
							System.out.println("0. Return to main menu");
							System.out.println("=========================");
							System.out.println(" Please make a selection (1-5, or 0 to quit):");
							try {
							option = Integer.parseInt(buffer.readLine());
							}catch (NumberFormatException nfe) {
								System.out.println("Please put correct input");
							}
							staffAction(option);
						}while (staffMenu);
					} else {
						System.out.println("Wrong ID or Password");
						continue;
					}
					
					
				} else if (memberMenu) {
					System.out.println("Enter username: (LastnameFirstname)");
					String member_id = buffer.readLine();
					
					int index = members.findMemberIndex(member_id);
					memberid = index;
					if (index != -1)
					{
						memberLogined = members.members[memberid];
					}
					while (index == -1)
					{
						System.out.println("Wrong ID or Password or Unregistered Member");
						System.out.println("Enter username: (LastnameFirstname)");
						member_id = buffer.readLine();
						index = members.findMemberIndex(member_id);
					}
					
					int member_pwd = -1;
					do {
						System.out.println("Enter Password: ");
						try
						{
							member_pwd = Integer.parseInt(buffer.readLine());
						}catch (NumberFormatException nfe) {
							System.out.println("Input should be integer!");
						}
					}while (member_pwd != members.returnMember(index).pwd);
					

					System.out.println("Login successfully!");
					do {
						System.out.println("========Member Menu========");
						System.out.println("1. Display all movies");
						System.out.println("2. Borrow a movie DVD");
						System.out.println("3. Return a movie DVD");
						System.out.println("4. List current borrrowed movie DVDs");
						System.out.println("5. Display top 10 most popular movies");
						System.out.println("0. Return to main menu");
						System.out.println("=========================");
						System.out.println(" Please make a selection (1-5, or 0 to quit):");
						try
						{
							option = Integer.parseInt(buffer.readLine());
						}catch (NumberFormatException nfe) {
							System.out.println("Input should be integer!");
						}	
						memberAction(option);
					}while (memberMenu);
				}
			} catch (InputMismatchException ime) {
				
			}
		}while (!quit);


}


public static void staffAction(int option) throws IOException
{
	int duration = -1;
	if (option == 1) {
		
		System.out.println("Enter the movie title: ");
		String title = buffer.readLine();
		System.out.println("Enter the starring actor(s): ");
		String actors = buffer.readLine();
		System.out.println("Enter the director(s): ");
		String director = buffer.readLine();
		System.out.println("Select the genre: ");
		String[] genres = getGenres();

		for (int i = 0; i < genres.length; i++) {
			System.out.println((i + 1) + ". " + genres[i]);
		}

		System.out.println("Make selection(1-8): ");
		String genre = genres[Integer.parseInt(buffer.readLine()) - 1];

		
		System.out.println("Select the classification: ");
		String[] classfications = getClassification();

		for (int i = 0; i < classfications.length; i++) {
			System.out.println((i + 1) + ". " + classfications[i]);
		}

		System.out.println("Make Selection(1-4): ");
		String classification = classfications[Integer.parseInt(buffer.readLine()) - 1];
		
		do {
			System.out.println("Enter the duration (minutes): ");
			try {
			duration = Integer.parseInt(buffer.readLine());
			} catch(NumberFormatException nfe) {
				System.out.println("Please enter correct input.");
			}
		}while (duration <= 0);
		

		System.out.println("Enter the release date (year): ");
		int releaseDate = Integer.parseInt(buffer.readLine());

		System.out.println("Enter the number of copies available: ");
		int copy = Integer.parseInt(buffer.readLine());

		movies.addNode(
				new Movie(title, actors, director, genre, classification, duration, releaseDate, copy));
		System.out.println("Movie added successfully!");
	}

	else if (option == 2) {

		System.out.println("Enter Remove Movie Title: ");
		String title = buffer.readLine();
		Node node = movies.findNode(title);
		try {
			movies.removeNode(node.movie);
			System.out.println("Movie removed successfully!");
		} catch (NullPointerException npe) {
			
		}

	} else if (option == 3) {

		System.out.println("Enter member's first name: ");
		String f_name = buffer.readLine();
		System.out.println("Enter member's last name: ");
		String l_name = buffer.readLine();
		boolean pass = false;
		do {
		
		for (int i = 0; i < members.count; i++) {
			if (members.members[i].last_name.equals(l_name))
			{
				if (members.members[i].first_name.equals(f_name))
				{
					System.out.println("A member with same first and last name is already existed. Please try again.");
					System.out.println("Enter member's first name: ");
					f_name = buffer.readLine();
					System.out.println("Enter member's last name: ");
					l_name = buffer.readLine();
					pass = false;
				}
				else {
					pass = true;
				}
			}
			else
			{
				pass = true;
			}
			
		}
		} while (pass = false);
		
		
		
		
			System.out.println("Enter member's address: ");
			String address = buffer.readLine();
			System.out.println("Enter member's phone number: ");
			String p_number = buffer.readLine();
			int pwd = -1;
			do {
				System.out.println("Enter member's password(4 digits): ");
				pwd = Integer.parseInt(buffer.readLine());
			} while (pwd < 1000 | pwd > 9999);
				
			try {
				members.addMember(new Member(f_name, l_name, address, p_number, pwd));
			}catch (NullPointerException npe)
			{
					
			}
			
			System.out.println("Successfully added " + f_name + " " + l_name);
		

	} else if (option == 4) {
		System.out.println("Enter member's first name: ");
		String f_name = buffer.readLine();
		System.out.println("Enter member's last name: ");
		String l_name = buffer.readLine();
		
		String username = l_name + f_name;
		String phoneNumber = members.findPhoneNum(username);
		
		while (phoneNumber == null)
		{
			System.out.println("Phone number cannot be found! Please enter correct info!");
			System.out.println("Enter member's first name: ");
			f_name = buffer.readLine();
			System.out.println("Enter member's last name: ");
			l_name = buffer.readLine();
			
			username = l_name + f_name;
			phoneNumber = members.findPhoneNum(username);
		}
		System.out.println("Your requested number is: " + phoneNumber);
		

	} else if (option == 0) {
		staffMenu = false;
	}
}

public static void memberAction(int option) throws IOException {
	if (option == 1) {

		movies.inOrderTraverseTree(movies.root);

	} else if (option == 2) {

		System.out.println("Enter movie title: ");
		String b_title = buffer.readLine();
		
		
		BorrowMovie(b_title);
		
	} else if (option == 3) {
		System.out.println("Enter movie title: ");
		String m_title = buffer.readLine();
		
		returnMovie(m_title);

	} else if (option == 4) {

		printBorrowedMovie();

	} else if (option == 5) {
		movies.storeinTraversal(movies.root);
		movies.top10sort();
		movies.printTop10();
	}

	else if (option == 0) {
		memberMenu = false;
	}
}

public static void printBorrowedMovie() {
	
	System.out.println("You are currently borrowing:");
	
	for(int i=0; i < memberLogined.moviesBorrowed.size(); i++) {
		System.out.println(memberLogined.moviesBorrowed.get(i));
	}
}

public static void returnMovie(String title) {
	
	memberLogined.moviesBorrowed.remove(title);
	movies.findNode(title).movie.available++;
	System.out.println("Return successfully.");
}

public static void BorrowMovie(String title) {
	if (movies.findNode(title).movie.available == 0)
	{
		System.out.println("There is no available copies for this movie.");
		return;
	} else if (memberLogined.moviesBorrowed.size() >= 10) {
		System.out.println("You can't borrow more than 10 movies.");
	}
	else if (memberLogined.moviesBorrowed.contains(title)) {
		System.out.println("You can't borrow the same movie twice!");
		return;
	}
	else {
		memberLogined.moviesBorrowed.add(title);
		memberLogined.moviesCount++;
		movies.findNode(title).movie.available--;
		movies.findNode(title).movie.time_borrowed++;
		System.out.println("Borrow successfully.");
		
	}
}


public static String[] getGenres() {
	
	String[] genres = new String[8];
	
	genres[0] = "Drama";
	genres[1] = "Adventure";
	genres[2] = "Family";
	genres[3] = "Action";
	genres[4] = "Sci_Fi";
	genres[5] = "Comedy";
	genres[6] = "Thriller";
	genres[7] = "Other";
	
	return genres;
}

public static String[] getClassification() {
	
	String[] classif = new String[4];
	
	classif[0] = "Genral (G)";
	classif[1] = "Parental Guidance (PG)";
	classif[2] = "Mature (M15+)";
	classif[3] = "Mature Accompanied (MA15+)";
	
	return classif; 
	
}
}