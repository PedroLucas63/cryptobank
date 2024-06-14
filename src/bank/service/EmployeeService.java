package bank.service;

import java.util.Optional;

import bank.dao.CareerDAO;
import bank.dao.CurrencyDAO;
import bank.dao.UserDAO;
import bank.entity.Career;
import bank.entity.CryptoCurrency;

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

    public static Boolean addFiatCurrency(String name, String symbol, Double value){
      try {
         if(symbol.length() > 4 || name.length() > 15 || value < 0){
            return false;
         }
         FiatCurrency newCurrency = new FiatCurrency(name,symbol, value);
         currencyDAO.save(newCurrency);
         return true;
      } catch (Exception e) {
         return false;
      }
    }

    public static Boolean addCryptoCurrency(String name, String symbol, Double value, Integer suplyMaximum){
      try {
         if(symbol.length() > 4 || name.length() > 15 || suplyMaximum < 0 || value < 0){
            return false;
         }
         CryptoCurrency newCurrency = new CryptoCurrency(name,symbol, value, suplyMaximum);
         currencyDAO.save(newCurrency);
         return true;
      } catch (Exception e) {
         return false;
      }
    }

}
