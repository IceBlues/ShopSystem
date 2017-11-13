import java.util.Scanner;

public class Menu {
    private static Scanner in = new Scanner(System.in);

    /**
     * This method should be invoked at the beginning of program.
     * The method will be invoked when all resources-files was loaded. It will judge user statement(login or not)
     * and invoke suitable method to show different main-menu to user.
     */
    public static void menuMain() {
        boolean isContinue = true;
        while (isContinue) {
            if (!StoreSystemAccount.isLogin()) {
                isContinue = menuNotLogin();
            }
            else {
                menuIsLogin();
            }
        }
    }

    /**
     * The method will be invoked by menuMain method, when the user not login.
     *
     * @return A boolean variable that control the main-menu show continue or not if it's true
     * the menuMain will invoke suitable main-menu again or it'll be end.
     */
    private static boolean menuNotLogin() {
        String selectErrorMessage = "Select Error";

        boolean isContinue = true;
        System.out.println("Log I)n E)xit");
        Scanner in = new Scanner(System.in);

        String userInput = in.nextLine().toUpperCase();
        switch (userInput) {
            case "I":
                menuLogin();
                break;
            case "E":
                isContinue = false;
                break;
            default:
                System.out.println(selectErrorMessage);
                break;
        }
        return isContinue;
    }

    /**
     *  The method will be invoked by menuMain method, when the user not login.
     */
    private static void menuIsLogin() {
        String selectErrorMessage = "Select Error";

        if (StoreSystemAccount.getAuthority().equals("E")) {
            System.out.println("P)urchase  R)eturn    Log O)ut");
            Scanner in = new Scanner(System.in);

            String userInput = in.nextLine().toUpperCase();
            switch (userInput) {
                case "P":
                    menuPurchase();
                    break;
                case "R":
                    menuReturn();
                    break;
                case "O":
                    StoreSystemAccount.logout();
                    break;
                default:
                    System.out.print(selectErrorMessage);
                    break;
            }
        }
        else {
            System.out.println("P)urchase  C)heck  R)eturn    Log O)ut");
            Scanner in = new Scanner(System.in);

            String userInput = in.nextLine().toUpperCase();
            switch (userInput) {
                case "P":
                    menuPurchase();
                    break;
                case "R":
                    menuReturn();
                    break;
                case "C":
                    menuCheck();
                    break;
                case "O":
                    StoreSystemAccount.logout();
                    break;
                default:
                    System.out.print(selectErrorMessage);
                    break;
            }
        }
    }

    /**
     *
     */
    private static void menuLogin() {
        StoreSystemAccount.login();
    }

    private static void menuPurchase() {
        if(StoreSystemAccount.getAuthority().equals("E")) {
            new ReceiptStaff().listReceipt();
        }
        else if(StoreSystemAccount.getAuthority().equals("M")){
            new ReceiptManager().listReceipt();
        }
    }

    private static void menuCheck() {
        String selectErrorMessage = "Select Error";
        String checkSelect;
        boolean isContinue = true;

        while(isContinue) {
            System.out.println("S)tock  P)roduct  R)ange Sold Products  E)xit");
            checkSelect = in.nextLine().toUpperCase();

            switch (checkSelect) {
                case "S":
                    StockHandler.checkStock();
                    break;
                case "P":
                    StockHandler.checkStockProduct();
                    break;
                case "R":
                    ReceiptStaffHandler.checkRangeProducts();
                    break;
                case "E":
                    isContinue = false;
                    break;
                default:
                    System.out.print(selectErrorMessage);
                    break;
            }
        }
    }

    private static void menuReturn() {
        if (StoreSystemAccount.getAuthority().equals("E")) {
            ReturnProductsHandler.EmployeeProductsReturn();
        }
        else if (StoreSystemAccount.getAuthority().equals("M")) {
            ReturnProductsHandler.ManagerProductsReturn();
        }
    }

}
