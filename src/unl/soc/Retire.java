package unl.soc;

import static java.lang.System.exit;

/**
 * Name: Luciano Carvalho (lguedesdecarvalhon2@huskers.unl.edu)
 *
 * Date: 2023 - 09 - 12
 *
 * Uses the argument line to calculate the interest and total balance
 * of a retirement invest account
 */


public class Retire 
{

    public static void main(String[] args) {
        // validating input
        if (args.length != 5)
        {
            System.out.printf("ERROR: incorrect number of command line arguments\n\n");
            exit(1);
        }

        // verify if the values are positive
        for (int i = 0; i < args.length - 1; i++)
        {
            if (Double.parseDouble(args[i]) < 0)
            {
                System.out.printf("ERROR: incorrect number of command line arguments\n\n");
                exit(1);
            }
        }

        // verify if the year is greater than 1
        if (Integer.parseInt(args[4]) < 1)
        {
            System.out.printf("ERROR: incorrect number of command line arguments\n\n");
            exit(1);
        }

        // verify if the annual Inflation and Return are greater than 0 and smaller than 1
        for (int i = 2; i < args.length - 1; i++)
        {
            if (Double.parseDouble(args[i]) < 0 || Double.parseDouble(args[i]) > 1)
            {
                System.out.printf("ERROR: incorrect number of command line arguments\n\n");
                exit(1);
            }
        }

        // verify the annual contribution is greater tha the limit
        if (Double.parseDouble(args[1]) * 12 > 18500)
        {
            System.out.printf("'ERROR: monthly contributions exceed $18,500 annual limit'\n\n");
            exit(1);
        }

        //variables from the argument line
        double initialBalance = Double.parseDouble(args[0]);
        double monthlyContribution = Double.parseDouble(args[1]);
        double annualRateReturn = Double.parseDouble(args[2]);
        double annualInflationRate = Double.parseDouble(args[3]);
        int yearsUntilRetirement = Integer.parseInt(args[4]);

        //general variables
        double inflationAdjustedRateYear = (((1 + annualRateReturn) / (1 + annualInflationRate)) - 1);
        double inflationAdjustedRateMonthly = inflationAdjustedRateYear / 12;
        double monthsUntilRetirement = yearsUntilRetirement * 12;
        double interest = 0;
        double totalInterest = interest;
        double totalBalance = initialBalance;
        int months = 0;

        System.out.printf("Month  Interest     Balance\t\n");

        for (int i = 0; i < monthsUntilRetirement; i++)
        {
            interest = ((totalBalance)*inflationAdjustedRateMonthly);
            interest = Math.round(interest * 100) / 100.0;
            totalBalance += interest + monthlyContribution;
            totalBalance = Math.round(totalBalance * 100.0) / 100.0;
            totalInterest += interest;
            months++;
            System.out.printf("%d      $ %.2f      $ %.2f\t\n", months, interest, totalBalance);
        }

        System.out.printf("\nTotal Interest Earned: $ %.2f\t\n", totalInterest);

        System.out.printf("Luciano");

        System.out.printf("Total Nest Egg: $ %.2f\t\n", totalBalance);
    }

}
