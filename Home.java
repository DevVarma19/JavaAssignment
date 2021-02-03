/**
 * This should be used by three members of a family.
 * Plans :  "Plan 1" -- 15000
            "Plan 2" -- 22000
            "Plan 3" -- 5500
            "Plan 4" -- 18500
            "Plan 5" -- 11000
 * Class Home -> Attributes - Electricity Bill
 *                            Telephone Bill
 *                            Grocery
 *                            Person
 *                            -> Inner Class -> Person -> Attributes - Salary
 *                                                                     Saving Plan
 *                                                                     Name
 */

import java.io.*;   /////Imported for IO functionalities
import java.util.*; //// Imported java.util package for Scanner and ArrayList

class Home implements Serializable { //// Home Class
    float elec_bill;
    float tel_bill;
    float grocery;
    Person member;

    /// Constructor
    Home(float e, float t, float g) {
        elec_bill = e;
        tel_bill = t;
        grocery = g;
    }

    class Person { /// Inner class Person
        String name;
        float salary;
        String sav_plan;

        // constructor for inner class
        Person(String n, float s, String p) {
            name = n;
            salary = s;
            sav_plan = p;
        }

        ///function to display the object
        public String toString(){
            String res = "\n\n\t\tName = " + name + "\n\n\t\tSalary = " + Float.toString(salary) + "\n\n\t\tSavings_Plan= " + sav_plan;
            return res;
        }

    }
// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    ///// Defining Functions
    ///function to display the object
    public String toString() {
        String res = "\n\t\tElectricity Bill = " + Float.toString(elec_bill) + "\n\t\tTelephone Bill = " + Float.toString(tel_bill) + "\n\t\tGrocery = " + Float.toString(grocery);
        return res;
    }

    ///This function finds the Entire expenses of the Home by adding all the bills
    static float sumbills(){
        float sum = 0;
        for(Home H : AL){
            sum = H.elec_bill + H.grocery + H.tel_bill;
        }
        return sum;
    }

    ///This function checks and displays whether home budget needs additional support from members or not
    static void checkBudget(){
        ///Accessing each object stored in the ArrayList using for each loop.
        float tot_bill = sumbills();
        boolean capability = checkCap();
        float contrib  = 0;
        for(Home H : AL){
            contrib += H.member.salary * 0.25;
        }
        if(contrib < tot_bill && !capability){
            System.out.println("\n\n\t\tHome budget needs additional support");
        } else {
            System.out.println("\n\n\t\tHome budget doesnot need any additional support");
        }
    }

    ///This function checks whether the member is capable of contributing to the family budget
    static boolean checkCap(){
        int savings  = 0;
        int count = 0;
        System.out.println("\n\n\t\tChecking the capability of the Members");
        for(Home H : AL)
        {
            for(String[] S : Plans)
            {
                if(S[0].toLowerCase().equals(H.member.sav_plan.toLowerCase()))
                {
                    savings = Integer.parseInt(S[1]);
                }
            }
            ///As each person needs to contribute 25% of his salary to the house budget if a person has a savings plan that is more than 75% of his earnings then
            ///the person is unable to bear the contribution.
            if( savings > H.member.salary*0.75 )
            {
                count++;
                System.out.println("\n\n\t\t" + H.member.name + " Cannot bear the family contribution with his savings plan " + H.member.sav_plan + " which is : " + Integer.toString(savings));
            }
        }
        if(count == 0){
            System.out.println("All the members of the family can bear with the family budget.");
            return true;
        }
        else{
            return false;
        }
    }

    ////Displays the name of all the members of the family
    static void displayAll(){
        for(Home H : AL){
            System.out.println(H.member.name);
        }
    }  

    ////Displays all the available plans
    static void viewplan(){
        System.out.println("\t\tPlan  \t\tAmount(in Rs.)");
        for(String[] S : Plans){
        System.out.println("\t\t"+S[0] + "\t\t" +S[1]);
    }
    }

    /////Editing the plans
    static void editplans(){
        boolean check = false;
        while(!check)
        {
        
            System.out.println("\n\t\tModifying the Plans available");
            viewplan();

            ///Taking input from the user to modify the plans
            System.out.print("\n\tEnter the Plan Name to be modified : ");
            String pName = sc.next();
            System.out.print("\n\tEnter the Savings Amount : ");
            String sAmt = sc.next();
        
            ///Searching the for the plan to modify
            for(String[] S : Plans){
                if(S[0].equals(pName)){
                    S[1] = sAmt;
                    check = true;
                }
            }
        
            if(!check){
                System.out.println("\n\t\tNo such Plan found!");
            }
        }
        System.out.println("\n\t\tDisplaying the modified plans ");
        viewplan();
    }   

    ///////Deserializing the previously stored objects
   static void viewPrev(){
         ///Deserialization
         try{
             Home H2;
             FileInputStream fis = new FileInputStream("home.txt");
             ObjectInputStream ois = new ObjectInputStream(fis);
             H2 = (Home)ois.readObject();
             ois.close();
             System.out.println("\n\n\t\tPreviously Saved Details : \n" + H2);
             float sum = H2.elec_bill + H2.tel_bill + H2.grocery;
             System.out.println("\n\t\tTotal Bill : " + Float.toString(sum));
        }
        catch(Exception e){
            System.out.println("Exception while reading the data : " + e);
            System.out.println("\n\t\tNo previous data present");
            System.exit(0);
        } 
    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /////Declaring all the static variables
    static String[][] Plans = {{"Plan 1", "15000"},
                               {"Plan 2", "22000"},
                               {"Plan 3", "5500"}, 
                               {"Plan 4", "18500"},
                               {"Plan 5", "11000"}
                            };
    
    static ArrayList<Home> AL = new ArrayList<>();  ////ArrayList of type Home to store the objects of type Home.
    static Scanner sc = new Scanner(System.in);
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                                                                    ////Main method
    public static void main(String[] args) {
        //Printing the Header
        System.out.println("\n\n\t\t\t\t*********************************");
        System.out.println("\t\t\t\t' HOME BUDGET MANAGEMENT SYSTEM '");
        System.out.println("\t\t\t\t*********************************");

        System.out.print("\n\t\t\t1.Admin\n\t\t\t2.User\n\t\t\t");
        int choice1 = sc.nextInt();

        if(choice1 == 1){
            System.out.println("\n\t\t'ADMIN PANEL'");
            boolean check = false;
            int count = 0;
            String Opass = "";

                ///opening the passcode.txt file
                try{
                    File f1 = new File("passcode.txt");
                    Scanner reader = new Scanner(f1);
                    while(reader.hasNextLine())
                    {
                        Opass = reader.nextLine();
                    }
                    reader.close();
                }
                catch(FileNotFoundException e){
                    System.out.println("An error occurred.");
                }

            ///Checking the password for admin
            while(!check){
                System.out.print("\n\n\t\tEnter the passcode : "); ////******************PASSCODE -> 2555***************************
                String pass = sc.next();

                if(pass.equals(Opass)){
                    System.out.println("\n\t\tWelcome Admin");
                    check = true;
                }
                else{
                    count++;
                    System.out.println("\n\t\tSorry wrong password. You have " + Integer.toString(3-count) +" Chances" );
                    if(count == 3){
                        System.exit(0);
                    }
                }
            }

            ///Admin logged in
            while(true)
            {
                System.out.print("\n\n\n\t\t1.View Plans\n\t\t2.Modify Plans\n\t\t3.View Previously Stored Data\n\t\t0.Exit\n\t\t");
                int c = sc.nextInt();

                switch(c)
                {
                    case 1: viewplan(); break;
                    case 2: editplans(); break;
                    case 3: viewPrev(); break;
                    default: System.exit(0);
                }
            }
        } ////End of admin block

        else if(choice1 == 2){
            ////User Block
            ////only one person enters the details of all
            System.out.println("\n\t\t\t\t:Welcome:");
            System.out.print("\n\n\t\tDo you want to view The Savings plans we have [Y/N]");
            String s = sc.next();
            if(s.toLowerCase().equals("y")){
                viewplan();
            }
            System.out.println("\t\tEnter the Details :-");
            System.out.print("\t\tEnter the Electricity Bill  : ");
            float e_bill = sc.nextInt();
            System.out.print("\n\t\tEnter the Telephone Bill  : ");
            float t_bill = sc.nextInt();
            System.out.print("\n\t\tEnter the Cost of Grocery : ");
            float grocery = sc.nextInt();

                    
            
            System.out.println("\n\t\t\tEnter Details of the members in the House");
            ////Getting the Details of the 3 members present in the house
            int i=0;
            for(i=0; i<3; i++)
            {
                Home H = new Home(e_bill, t_bill, grocery);
                ///Creating the object of the Outer class and initialized its attributes with the values given

                System.out.println("\n\t\tEnter the details of Person " + Integer.toString(i+1));
                System.out.print("\n\t\tName   : ");
                String name = sc.next();
                sc.nextLine();                          ////Clearing the buffer
                System.out.print("\n\t\tSalary : ");
                float sal  = sc.nextFloat();
                sc.nextLine();                          ////Clearing the buffer
                System.out.println("\n\t\tChoose a Plan(enter the plan name)");
                ///Displaying the plans
                viewplan();
                System.out.print("\t\t");
                String plan = sc.nextLine();

                ///Creating a person obect
                Person P = H.new Person(name, sal, plan);   
                H.member = P;       ///Assigning the created object to the memeber
                AL.add(H);        ///Adding the data to the ArrayList
            }

            ////Serialization
            ///Storing the data entered by the user into a file
            try {
                Home H1 = new Home(e_bill, t_bill, grocery);
                FileOutputStream fos = new FileOutputStream("home.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(H1);
                oos.flush();
                oos.close();
                System.out.println("\n\n\t\tSaved your data successfully!");
            } catch (IOException e) {
                System.out.println("\n\n\t\tError While Saving the data : " + e);
                System.exit(0);
            }

            ///This runs only if the user enters all the details of the 3 members
            if(i == 3){
                System.out.println("\n\n\t\tYou Have Successfully added the Details");
            } else {
                System.err.println("\n\n\t\tError! Insufficient Data entered");
                System.exit(0);         ////The program terminates here if 3 details are not entered.
            }

            ///Making calls to the required functions
            while(true)
            {
                System.out.println("\n\n\t\t1.Display All Members\n\t\t2.Check Budget Assistance\n\t\t3.Check Members Capability\n\t\t0.Exit");
                int c2 = sc.nextInt();

                switch(c2){
                    case 1: displayAll(); break;
                    case 2: checkBudget(); break; 
                    case 3: checkCap(); break;
                    default: System.exit(0);
                }
            }
        }///End of user
    }
}