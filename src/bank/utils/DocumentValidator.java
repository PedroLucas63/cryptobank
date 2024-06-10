package bank.utils;

public class DocumentValidator {
    public static Boolean isValidCPF(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        firstDigit = (firstDigit > 9) ? 0 : firstDigit;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        secondDigit = (secondDigit > 9) ? 0 : secondDigit;

        return cpf.endsWith(firstDigit + "" + secondDigit);
    }

    public static Boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");

        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int sum = 0;
        int[] weightsFirst = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weightsFirst[i];
        }
        int firstDigit = 11 - (sum % 11);
        firstDigit = (firstDigit >= 10) ? 0 : firstDigit;

        sum = 0;
        int[] weightsSecond = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        for (int i = 0; i < 13; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weightsSecond[i];
        }
        int secondDigit = 11 - (sum % 11);
        secondDigit = (secondDigit >= 10) ? 0 : secondDigit;

        return cnpj.endsWith(firstDigit + "" + secondDigit);
    }
}
