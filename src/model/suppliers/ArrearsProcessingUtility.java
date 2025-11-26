package model.suppliers;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for testing and managing monthly arrears processing.
 * Use this to diagnose issues and manually trigger arrears processing.
 */
public class ArrearsProcessingUtility {
    
    private static final Logger logger = Logger.getLogger("tea_sys");
    
    public static void main(String[] args) {
        MonthlyArrearsProcessor processor = new MonthlyArrearsProcessor();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===========================================");
        System.out.println("  ARREARS PROCESSING UTILITY");
        System.out.println("===========================================\n");
        
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Show Diagnostic Information");
            System.out.println("2. Check if Arrears Need Processing");
            System.out.println("3. Force Process Current Month");
            System.out.println("4. Force Process Specific Month");
            System.out.println("5. Show Processing Status");
            System.out.println("0. Exit");
            System.out.print("\nYour choice: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    showDiagnosticInfo(processor);
                    break;
                    
                case "2":
                    checkProcessing(processor);
                    break;
                    
                case "3":
                    forceProcessCurrentMonth(processor);
                    break;
                    
                case "4":
                    forceProcessSpecificMonth(processor, scanner);
                    break;
                    
                case "5":
                    showStatus(processor);
                    break;
                    
                case "0":
                    System.out.println("\nExiting...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
    
    private static void showDiagnosticInfo(MonthlyArrearsProcessor processor) {
        System.out.println("\n" + processor.getDiagnosticInfo());
    }
    
    private static void checkProcessing(MonthlyArrearsProcessor processor) {
        System.out.println("\nChecking if arrears need processing...\n");
        
        boolean processed = processor.checkAndProcessMonthlyArrears();
        
        if (processed) {
            System.out.println("✓ Arrears processed successfully!");
        } else {
            System.out.println("✗ Arrears not processed (already done, no leaf rate, or not yet time)");
        }
        
        System.out.println("\nCheck the application logs for details.");
    }
    
    private static void forceProcessCurrentMonth(MonthlyArrearsProcessor processor) {
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);
        int year = previousMonth.getYear();
        int month = previousMonth.getMonthValue();
        
        System.out.println("\n⚠ WARNING: Force processing will OVERWRITE existing records!");
        System.out.printf("Month to process: %d-%02d\n", year, month);
        System.out.print("Are you sure? (yes/no): ");
        
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("yes")) {
            System.out.println("\nForce processing arrears...");
            processor.forceProcessArrears(year, month);
            System.out.println("✓ Force processing complete. Check logs for details.");
        } else {
            System.out.println("✗ Cancelled.");
        }
    }
    
    private static void forceProcessSpecificMonth(MonthlyArrearsProcessor processor, Scanner scanner) {
        System.out.println("\n⚠ WARNING: Force processing will OVERWRITE existing records!");
        
        try {
            System.out.print("Enter year (e.g., 2025): ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter month (1-12): ");
            int month = Integer.parseInt(scanner.nextLine().trim());
            
            if (month < 1 || month > 12) {
                System.out.println("✗ Invalid month. Must be between 1 and 12.");
                return;
            }
            
            System.out.printf("\nMonth to process: %d-%02d\n", year, month);
            System.out.print("Are you sure? (yes/no): ");
            
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("yes")) {
                System.out.println("\nForce processing arrears...");
                processor.forceProcessArrears(year, month);
                System.out.println("✓ Force processing complete. Check logs for details.");
            } else {
                System.out.println("✗ Cancelled.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input. Please enter numbers only.");
        }
    }
    
    private static void showStatus(MonthlyArrearsProcessor processor) {
        // System.out.println("\n" + processor.getProcessingStatus());
    }
}
