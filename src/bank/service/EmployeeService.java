package bank.service;

import java.util.Optional;

import bank.dao.CareerDAO;
import bank.dao.CurrencyDAO;
import bank.dao.UserDAO;
import bank.entity.Career;
import bank.entity.CryptoCurrency;
import bank.entity.Currency;
import bank.entity.Employee;
import bank.entity.FiatCurrency;
import bank.entity.User;
import bank.exception.DAOException;
import bank.utils.DocumentTransformer;

public class EmployeeService {
    private static UserDAO userDAO = new UserDAO();
    private static CareerDAO careerDAO = new CareerDAO();
    private static CurrencyDAO currencyDAO = new CurrencyDAO();
    private static Employee employee;
    public static Boolean create(String userDocument, Career career, Float salary) {
      try {
         userDocument = DocumentTransformer.transform(userDocument);

         Optional<User> searchedUser = userDAO
               .findById((long) userDocument.hashCode());

         if (!searchedUser.isPresent()) {
            return false;
         }
         employee = new Employee(searchedUser.get(), career, salary);
         employee.setCareer(career);
         employee.setSalary(salary);
         userDAO.update((long) userDocument.hashCode(), employee);
         return true;
      } catch (DAOException e) {
         return false;
      }
    }
    public static Boolean searchEmployee(String document){
      document = DocumentTransformer.transform(document);
      try {
         Optional<User> searchedUser = userDAO
               .findById((long) document.hashCode());
         if(!searchedUser.isPresent() || !(searchedUser.get() instanceof Employee)){
            return false;
         }
         else{
            return true;
         }  
      } catch (Exception e) {
         return false;
      }
    }
    public static Boolean searchCareer(String title){
      try {
         Optional<Career> searchedCareer = careerDAO.findById((long) title.hashCode());
         if(!searchedCareer.isPresent() || !(searchedCareer.get() instanceof Career)){
            return false;
         }
         else{
            return true;
         }  
      } catch (Exception e) {
         return false;
      }
    }
    public static Boolean validSalary(Float salary){
      if(salary >= 0 && salary <= 499999.99){
         return true;
      }
      else{
         return false;
      }
    }
    public static Boolean updateInfo(String document, Float salary, String career){
      document = DocumentTransformer.transform(document);
      try {
         Optional<Career> temp = careerDAO.findById((long) career.hashCode());
         EmployeeService.create(document, temp.get(), salary);
         return true;
      } catch (Exception e) {
         return false;
      }
    }
    public static void showEmployeeInfo(String document){
      document = DocumentTransformer.transform(document);
      try {
         Optional<User> searchedUser = userDAO
               .findById((long) document.hashCode());
         if(!searchedUser.isPresent() || !(searchedUser.get() instanceof Employee)){
            System.out.println("O usuário não está registrado no sistema ou não é um funcionário.");
         }
         else{
            Employee employee = (Employee) searchedUser.get();
            System.out.println("Informações do funcionário de documento '" + employee.getDocument() + "':");
            System.out.println("Nome -> " + employee.getName());
            System.out.println("Carreira atual -> " + employee.getCareer().getTitle());
            System.out.println("Salário atual -> " + employee.getSalary());
         }  
      } catch (Exception e) {
         System.out.println(e);
      }
    }

   public static void showCurrencyInfo(String name){

      try {
         Optional<Currency> searchedCurrency = currencyDAO
               .findById((long) name.hashCode());
         if(!searchedCurrency.isPresent()){
            System.out.println("A moeda informada não está registrada no sistema.");
         }
         else{
               if(searchedCurrency.get() instanceof FiatCurrency){
                  FiatCurrency currency = (FiatCurrency) searchedCurrency.get();
                  System.out.println("Informações da moeda fiduciária '" + currency.getName() + "':");
                  System.out.println("Símbolo -> " + currency.getSymbol());
                  System.out.println("Valor atual -> " + currency.getValue());
               } else {
                  CryptoCurrency currency = (CryptoCurrency) searchedCurrency.get();
                  System.out.println("Informações da criptomoeda '" + currency.getName() + "':");
                  System.out.println("Símbolo -> " + currency.getSymbol());
                  System.out.println("Moedas em uso em relação ao fornecimento total -> " + currency.getSupplyInUse());
                  System.out.println("Fornecimento total -> " + currency.getSupplyMaximum());
                  System.out.println("Valor atual -> " + currency.getValue());
                  System.out.println("Valor original -> " + currency.getOriginalValue());
               }
         }  
      } catch (Exception e) {
         System.out.println(e);
      }

   }

}
