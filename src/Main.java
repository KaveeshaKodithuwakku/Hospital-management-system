import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n\n                           ****************************************************************           ");
        System.out.println("          ****************************** WELCOME TO HOSPITAL MANAGEMENT SYSTEM *********************************");
        System.out.println("                            ****************************************************************           \n");

        System.out.println("-------------- Hospital Management System Menu --------------\n");

        Scanner scanner = new Scanner(System.in);

        String[] menu = new String[5];
        String[][] patients = new String[100][4];
        String[][] doctors = new String[50][3];
        String[][] appointments = new String[100][5];

        String[] patientMenu = new String[6];
        String[] doctorMenu = new String[6];
        String[] appointmentMenu = new String[2];
        String[] viewAppointmentMenu = new String[2];

        menu[0] = "1. Patient Management";
        menu[1] = "2. Doctor Management";
        menu[2] = "3. Appointment Management";
        menu[3] = "4. View Appointments by Doctor ID";
        menu[4] = "5. Exit";

        patientMenu[0] = "1. Add Patient";
        patientMenu[1] = "2. Find Patient";
        patientMenu[2] = "3. Update Patient";
        patientMenu[3] = "4. Delete Patient";
        patientMenu[4] = "5. View All Patient";
        patientMenu[5] = "6. Exits";

        doctorMenu[0] = "1. Add Doctor";
        doctorMenu[1] = "2. Find Doctor";
        doctorMenu[2] = "3. Update Doctor";
        doctorMenu[3] = "4. Delete Doctor";
        doctorMenu[4] = "5. View All Doctor";
        doctorMenu[5] = "6. Exits";

        appointmentMenu[0] = "1. Make an Appointment";
        appointmentMenu[1] = "2. Exits";

        viewAppointmentMenu[0] = "1. View Appointment by Doctor Id";
        viewAppointmentMenu[1] = "2. Exits";


        while (true){

            int menuNumber = printMenu(menu, scanner);

            switch (menuNumber){

                case 1:
                    patientManagement(patientMenu,patients,menu,scanner);
                    break;
                case 2:
                    doctorManagement(doctorMenu,doctors,menu,scanner);
                    break;
                case 3:
                    appointmentManagement(appointmentMenu,patients, doctors, appointments,menu,scanner);
                    break;
                case 4:
                    viewAppointmentByDId(viewAppointmentMenu,appointments,menu,scanner);
                    break;
                case 5:
                    System.exit(0);
//                    logout();
                    break;
            }
        }
    }

    //Show Menu
    public static int printMenu(String[] menu, Scanner input){

        System.out.println("Select your option....");

        for(String tempMenu : menu){
            System.out.println(tempMenu);
        }

        int selectOption = input.nextInt();
        if(selectOption <= 0){
            System.out.println("Wrong selection. Please check and enter valid menu number....!");
            System.exit(0);
        }
        return selectOption;
    }

    public static void logout(){
        System.exit(0);
    }

    public static void patientManagement(String[] pMenu, String[][] patients, String[] mainMenu ,Scanner input){


        while (true){

            int pMenuNumber = printPatientMenu(pMenu, input);

            switch (pMenuNumber){

                case 1:
                    addPatients(patients, input);
                    break;
                case 2:
                    findPatients(patients, input);
                    break;
                case 3:
                    updatePatients(patients, input);
                    break;
                case 4:
                    deletePatients(patients, input);
                    break;
                case 5:
                    viewAllPatients(patients);
                    break;
                case 6:
//                   Scanner scanner1 = new Scanner(System.in);
//                   printMenu(mainMenu,scanner1);
                  printMenu(mainMenu,input);
                    break;


            }
        }
    }

    public static int printPatientMenu(String[] menu, Scanner input){

        System.out.println("\n------------------ Patient Menu -------------------\n");
        System.out.println("Select your option : ");

        for(String tempPMenu : menu){
            System.out.println(tempPMenu);
        }

        int selectOption = input.nextInt();
        if(selectOption <= 0){
            System.out.println("Wrong selection. Please check and enter valid menu number....!");
            System.exit(0);
        }
        return selectOption;
    }

    //Add Patients
    public static void addPatients(String[][] patients, Scanner input){

        boolean addPStatus = true;

        System.out.println("\n----------------- Add Patient ----------------\n");

        while (addPStatus){
            input.nextLine();

            if(patients[patients.length-1][0] != null){
                System.out.println("Maximum patients count exceeded....");
                return;
            }

            outer: for(int i=0; i < patients.length; i++){
                inner: for(int j=0; j< patients[i].length; j++){

                    if(patients[i][j]== null) {
                        String pId = "", tele = "";

                            do {
                                System.out.println("Enter Patient Id : ");
                                pId = input.nextLine();

                            } while (isExistPatients(patients, pId));

                            patients[i][0] = pId;
                            System.out.println("Enter Patient Name : ");
                            patients[i][1] = input.nextLine();
                            System.out.println("Enter Patient Age : ");
                            patients[i][2] = input.nextLine();

                        do {
                            System.out.println("Enter Patient Contact : ");
                            tele = input.nextLine();
                        } while (isValidContact(tele));

                             patients[i][3] = tele;

                             if(patients[i][0] != null && patients[i][1] != null && patients[i][2] != null && patients[i][3] != null){
                                 System.out.println("Patient details successfully added.\n");
                             }

                        break outer;
                    }else{
                        break inner;
                    }
                }
            }

            System.out.println("Do you need to add another patient?");
            System.out.println("1). Yes");
            System.out.println("2). No");
            int selectedAnswer = input.nextInt();

            if(selectedAnswer != 1){
                addPStatus = false;
            }
        }
    }



    public static void findPatients(String[][] patients, Scanner input){

        input.nextLine();
        System.out.println("Enter patient's Id : ");
        String fPId = input.nextLine();

        if(fPId != null){
            if(patients.length > 0) {

                System.out.println("                        Patient's Information\n");
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Id" + "             " + "Name" + "              " + "Age" + "             " + "Contact");
                System.out.println("------------------------------------------------------------------------");

                for (String[] patient : patients) {
                    if (patient != null && patient[0] != null && patient[0].equals(fPId)) {
                        System.out.print(patient[0] + "          ");
                        System.out.print(patient[1] + "              ");
                        System.out.print(patient[2] + "              ");
                        System.out.println(patient[3]);
                        return;
                    }
                }
//
//                for(int i=0; i < patients.length; i++) {
//                   for (int j = 0; j < patients[i].length; j++) {
//
//                        if(patients[i][0].equals(fPId) && patients[i][0] != null){
//                            System.out.print(patients[i][0]+ "          " );
//                            System.out.print(patients[i][1] + "              " );
//                            System.out.print(patients[i][2]+ "              " );
//                            System.out.println(patients[i][3]);
//                            return;
//                        }
//                    }
//                }
                System.out.println("No records found.");
            }else{
                System.out.println("No records found.");
            }
        }else{
            System.out.println("Please enter valid patient id.");
        }
    }




    public static void updatePatients(String[][] patients, Scanner input){

        input.nextLine();
        System.out.println("Enter patient Id for update : ");
        String uPId = input.nextLine();
        String tele = "";

            if(uPId != null){
                for(int i=0; i < patients.length; i++) {
                    for (int j = 0; j < patients[i].length; j++) {

                        if(patients[i][0].equals(uPId) && patients[i][0] != null){

                            System.out.println("Enter patient name : ");
                            patients[i][1] = input.nextLine();
                            System.out.println("Enter patient age : ");
                            patients[i][2] = input.nextLine();

                            do {
                                System.out.println("Enter Patient Contact : ");
                                tele = input.nextLine();
                            } while (isValidContact(tele));

                            patients[i][3] = tele;

                            if(patients[i][1] != null && patients[i][2]!= null && patients[i][3] != null){
                                System.out.println("Patient details successfully updated.\n");
                            }

                            return;
                        }else{
                            System.out.println("No records found.");
                        }
                    }
                }
                System.out.println("No records found.");
            }else{
                System.out.println("Entered Id is wrong. Please check again.");
            }
    }

    public static void deletePatients(String[][] patients, Scanner input){

        input.nextLine();
        System.out.println("Enter Patient's Id : ");
        String dPId = input.nextLine();

        if(dPId != null){
            for(int i=0; i < patients.length; i++) {
                for (int j = 0; j < patients[i].length; j++) {

                    if (patients[i][0].equals(dPId) && patients[i][0] != null) {

                        patients[i][0] = null;
                        patients[i][1] = null;
                        patients[i][2] = null;
                        patients[i][3] = null;


                        if(patients[i][0] == null && patients[i][1] == null && patients[i][2] == null && patients[i][3] == null ){
                            System.out.println("Patient details successfully deleted.\n");
                        }

                        return;
                    } else {
                        System.out.println("No records found.");
                    }
                }
            }
        }else{
            System.out.println("Entered Id is wrong. Please check again.");
        }

    }

    public static void viewAllPatients(String[][] patients){

        if(patients.length > 0){

            System.out.println("                        Patient's Information\n");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Id" + "             " +"Name"+ "              " + "Age" + "             "+ "Contact");
            System.out.println("------------------------------------------------------------------------");

            for(int i=0; i<patients.length; i++){

                if(patients[i][0] != null){
                    System.out.print(patients[i][0]+ "          " );
                    System.out.print(patients[i][1] + "              " );
                    System.out.print(patients[i][2]+ "              " );
                    System.out.println(patients[i][3]);
                }
            }
        }else{
            System.out.println("No records");
        }

    }

    public static void doctorManagement(String[] dMenu, String[][] doctors, String[] mainMenu,Scanner input){


        while (true){

            int dMenuNumber = printDoctorMenu(dMenu, input);

            switch (dMenuNumber){

                case 1:
                    addDoctors(doctors, input);
                    break;
                case 2:
                    findDoctors(doctors, input);
                    break;
                case 3:
                    updateDoctors(doctors, input);
                    break;
                case 4:
                    deleteDoctors(doctors, input);
                    break;
                case 5:
                    viewAllDoctors(doctors);
                    break;
                case 6:
                    printMenu(mainMenu,input);
                    break;


            }
        }
    }

    public static int printDoctorMenu(String[] menu, Scanner input){

        System.out.println("Enter your option : ");

        for(String tempDMenu : menu){
            System.out.println(tempDMenu);
        }

        int selectOption = input.nextInt();
        if(selectOption <= 0){
            System.out.println("Wrong selection. Please check and enter valid menu number....!");
            System.exit(0);
        }
        return selectOption;
    }

    public static void addDoctors(String[][] doctors, Scanner input){

        boolean addDStatus = true;

        while (addDStatus){
            input.nextLine();

            if(doctors[doctors.length-1][0] != null){
                System.out.println("Maximum Doctors count exceeded....");
                return;
            }

            outer: for(int i=0; i < doctors.length; i++){
                inner: for(int j=0; j< doctors[i].length; j++){

                    if(doctors[i][j]== null) {
                        System.out.println("------ Enter Doctors Details Here -----");
                        String dId = "";

                        do {
                            System.out.println("Enter Doctor Id : ");
                            dId = input.nextLine();

                        } while (isExistDoctors(doctors, dId));

                        doctors[i][0] = dId;
                        System.out.println("Enter Doctor Name : ");
                        doctors[i][1] = input.nextLine();
                        System.out.println("Enter Doctor's Specialization : ");
                        doctors[i][2] = input.nextLine();

                        if(doctors[i][0] != null && doctors[i][1] != null && doctors[i][2] != null){
                            System.out.println("Doctor details successfully added\n");
                        }

                        break outer;
                    }else{
                        break inner;
                    }
                }
            }

            System.out.println("Do you need to add another doctor?");
            System.out.println("1). Yes");
            System.out.println("2). No");
            int selectedAnswer = input.nextInt();

            if(selectedAnswer != 1){
                addDStatus = false;
            }
        }
    }

    public static void findDoctors(String[][] doctors, Scanner input){

        input.nextLine();
        System.out.println("Enter doctor's Id : ");
        String fDId = input.nextLine();

        if(fDId != null){
            if(doctors.length > 0) {

                System.out.println("                        Doctor's Information\n");
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Id" + "             " + "Name" + "              " + "Specialization" );
                System.out.println("------------------------------------------------------------------------");

                for (String[] doctor : doctors) {
                    if (doctor != null && doctor[0] != null && doctor[0].equals(fDId)) {
                        System.out.print(doctors[0]+ "          " );
                        System.out.print(doctors[1] + "              " );
                        System.out.println(doctors[2] + "\n");
                        return;
                    }
                }

//                for(int i=0; i < doctors.length; i++) {
//                    for (int j = 0; j < doctors[i].length; j++) {
//
//                        if(doctors[i][0].equals(fDId) && doctors[i][0] != null){
//                            System.out.print(doctors[i][0]+ "          " );
//                            System.out.print(doctors[i][1] + "              " );
//                            System.out.println(doctors[i][2] + "\n");
//                            return;
//                        }
//                    }
//                }
                System.out.println("No records found.");
            }else{
                System.out.println("No records found.");
            }
        }else{
            System.out.println("Entered Id is wrong. Please check again.");
        }
    }

    public static void updateDoctors(String[][] doctors, Scanner input){

        input.nextLine();
        System.out.println("Enter patient Id for update : ");
        String uDId = input.nextLine();

        if(uDId != null){
            for(int i=0; i < doctors.length; i++) {
                for (int j = 0; j < doctors[i].length; j++) {

                    if(doctors[i][0].equals(uDId) && doctors[i][0] != null){

                        System.out.println("Enter doctor name : ");
                        doctors[i][1] = input.nextLine();
                        System.out.println("Enter doctor specialization : ");
                        doctors[i][2] = input.nextLine();

                        if(doctors[i][0] != null && doctors[i][1] != null && doctors[i][2] != null){
                            System.out.println("Doctor details successfully updated.\n");
                        }
                        return;
                    }
                }
            }
            System.out.println("No records found\n");
        }else{
            System.out.println("Entered Id is wrong. Please check again.\n");
        }

    }

    public static void deleteDoctors(String[][] doctors, Scanner input){

        input.nextLine();
        System.out.println("Enter Doctor's Id : ");
        String dDId = input.nextLine();

        if(dDId != null){

                for(int i=0; i < doctors.length; i++) {
                    for (int j = 0; j < doctors[i].length; j++) {

                        if(doctors[i][0].equals(dDId)){

                            doctors[i][0] = null;
                            doctors[i][1] = null;
                            doctors[i][2] = null;

                            if(doctors[i][0] == null && doctors[i][1] == null && doctors[i][2] == null){
                                System.out.println("Doctor details successfully deleted.\n");
                            }
                            return;
                        }else{
                            System.out.println("No records found\n");
                        }
                    }
                }
        }else{
            System.out.println("Entered Id is wrong. Please check again.");
        }
    }

    public static void viewAllDoctors(String[][] doctors){

        if(doctors.length > 0) {

            System.out.println("                        Doctor's Information\n");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Id" + "             " + "Name" + "              " + "Specialization" );
            System.out.println("------------------------------------------------------------------------");

            for(int i=0; i<doctors.length; i++){

                if(doctors[i][0] != null){
                    System.out.print(doctors[i][0]+ "          " );
                    System.out.print(doctors[i][1] + "              " );
                    System.out.println(doctors[i][2]+"\n");
                }
             }
        }else {
            System.out.println("No records found");
        }
    }


    public static void appointmentManagement(String[] aMenu, String[][] patients, String[][] doctors,String[][] appointment, String[] mainMenu,Scanner input){


        while (true){

            int pMenuNumber = printAppointmentMenu(aMenu, input);

            switch (pMenuNumber){

                case 1:
                    applyAppointment(appointment, patients, doctors,input);
                    break;
                case 2:
                    printMenu(mainMenu, input);
                    break;
            }
        }
    }

    public static int printAppointmentMenu(String[] menu, Scanner input){

        System.out.println("Enter your option : ");

        for(String tempAMenu : menu){
            System.out.println(tempAMenu);
        }

        int selectOption = input.nextInt();
        if(selectOption <= 0){
            System.out.println("Wrong selection. Please check and enter valid menu number....!");
            System.exit(0);
        }
        return selectOption;
    }

    public static void applyAppointment(String[][] appointment,String[][] patientsList,String[][] doctorsList, Scanner input){

        boolean addAStatus = true;

        while (addAStatus){
            input.nextLine();

            if(appointment[appointment.length-1][0] != null){
                System.out.println("Maximum appointment count exceeded....");
                return;
            }

            if(patientsList[0][0] != null && doctorsList[0][0] != null){

                outer: for(int i=0; i < appointment.length; i++){
                    inner: for(int j=0; j< appointment[i].length; j++){

                        if(appointment[i][j]== null) {
                            System.out.println("------ Make An Appointment Here ------");
                            String aId = "", pId = "", dId = "";

                            do {
                                System.out.println("Enter Appointment Id : ");
                                aId = input.nextLine();

                            } while (isExistAppointment(appointment, aId));

                            appointment[i][0] = aId;

                            do {
                                System.out.println("Enter Doctor Id : ");
                                dId = input.nextLine();
                            } while (isExistDoctor(doctorsList, dId));

                            appointment[i][1] = dId;

                            do {
                                System.out.println("Enter Patient Id : ");
                                pId = input.nextLine();
                            } while (isExistPatient(patientsList, dId));

                            appointment[i][2] = pId;

                            System.out.println("Enter Appointment Date : ");
                            appointment[i][3] = input.nextLine();
                            System.out.println("Enter Appointment Time : ");
                            appointment[i][4] = input.nextLine();

                            if(appointment[i][0] != null && appointment[i][1] != null && appointment[i][2] != null && appointment[i][3] != null && appointment[i][4] != null){
                                System.out.println("Successfully make an appointment. \n");
                            }

                            break outer;
                        }else{
                            break inner;
                        }
                    }
                }
            }else {
                System.out.println("No doctor and patient details.\n");
            }


            System.out.println("Do you need to add another appointment?");
            System.out.println("1). Yes");
            System.out.println("2). No");
            int selectedAnswer = input.nextInt();

            if(selectedAnswer != 1){
                addAStatus = false;
            }
        }
    }

    public static void viewAppointmentByDId(String[] vAppointmentMenu,String[][] appointment, String[] mainMenu, Scanner input){

        while (true){

            int vAMenuNumber = printViewAppointmentMenu(vAppointmentMenu, input);

            switch (vAMenuNumber){

                case 1:
                    viewAppointmentByDoctorId(appointment, input);
                    break;
                case 2:
                   printMenu(mainMenu,input);
                    break;
            }
        }
    }

    public static int printViewAppointmentMenu(String[] menu, Scanner input){

        System.out.println("Enter your option : ");

        for(String tempAMenu : menu){
            System.out.println(tempAMenu);
        }

        int selectOption = input.nextInt();
        if(selectOption <= 0){
            System.out.println("Wrong selection. Please check and enter valid menu number....!");
            System.exit(0);
        }
        return selectOption;
    }

    public static void viewAppointmentByDoctorId(String[][] appointment, Scanner input){

        input.nextLine();
        System.out.println("Enter doctor's Id : ");
        String fDId = input.nextLine();

        if(fDId != null){
            if(appointment.length > 0) {

                System.out.println("                        Appointment Information\n");
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Doctor's Id" + "             " + "Patient's Id" + "              " + "Date" + "             " + "Time");
                System.out.println("------------------------------------------------------------------------");


                for (String[] appoint : appointment) {
                    if (appoint != null && appoint[0] != null && appoint[0].equals(fDId)) {
                        System.out.print(appointment[0]+ "          " );
                        System.out.print(appointment[1] + "              " );
                        System.out.print(appointment[2]+ "              " );
                        System.out.println(appointment[3]);
                        return;
                    }
                }

//                for(int i=0; i < appointment.length; i++) {
//                    for (int j = 0; j < appointment[i].length; j++) {
//
//                        if(appointment[i][0].equals(fDId) && appointment[i][0] != null){
//                            System.out.print(appointment[i][0]+ "          " );
//                            System.out.print(appointment[i][1] + "              " );
//                            System.out.print(appointment[i][2]+ "              " );
//                            System.out.println(appointment[i][3]);
//                            return;
//                        }
//                    }
//                }
               System.out.println("No records found");
            }else {
                System.out.println("No records found");
            }
        }else{
            System.out.println("Entered Id is wrong. Please check again.");
        }
    }

    public static boolean isExistPatients(String[][] patients, String id){

        if(patients[0][0] == null){
            return false;
        }

        for(int i=0; i<patients.length; i++){

            if(patients[i][0] == null){
                return false;
            }

            if(patients[i][0].equals(id)){
                System.out.println("This patient is already exits.");
                return true;
            }
        }
        return false;
    }

    public static boolean isValidContact(String tele){

        if (tele.matches("[0-9]+")) {
            if(tele.length() == 10){
             return false;
            }else {
                System.out.println("Invalid contact number.Please check and try again.\n");
                return true;
            }
        }else{
            System.out.println("Invalid contact number. Please check and try again.\n");
            return true;
        }
    }

    public static boolean isExistDoctors(String[][] doctors, String id){

        if(doctors[0][0] == null){
            return false;
        }

        for(int i=0; i<doctors.length; i++){

            if(doctors[i][0] == null){
                return false;
            }

            if(doctors[i][0].equals(id)){
                System.out.println("This doctor is already exits.");
                return true;
            }
        }
        return false;
    }

    public static boolean isExistAppointment(String[][] appointment, String id){

        if(appointment[0][0] == null){
            return false;
        }

        for(int i=0; i<appointment.length; i++){

            if(appointment[i][0] == null){
                return false;
            }

            if(appointment[i][0].equals(id)){
                System.out.println("This appointment number is already exits.");
                return true;
            }
        }
        return false;
    }

    public static boolean isExistDoctor(String[][] doctors, String id){

        if(doctors[0][0] == null){
            return false;
        }

        for(int i=0; i<doctors.length; i++){

            if(doctors[i][0] == null){
                return false;
            }

            if(!doctors[i][0].equals(id)){
                System.out.println("Invalid doctor id");
                return true;
            }
        }
        return false;
    }


    public static boolean isExistPatient(String[][] patients, String id){

        if(patients[0][0] == null){
            System.out.println("Please add patient details firstly.");
           return false;
        }

        for(int i=0; i<patients.length; i++){

            if(patients[i][0] == null){
                return false;
            }

            if(!patients[i][0].equals(id)){
                System.out.println("Invalid patient Id. Please check and try again......");
                return true;
            }
        }
        return false;
    }

}