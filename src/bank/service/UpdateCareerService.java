package bank.service;

import bank.dao.CareerDAO;
import bank.entity.Career;

public class UpdateCareerService {
    private static CareerDAO careerDAO = new CareerDAO();
    public static Boolean addCareer(String title, Boolean updateEmployees, Boolean updateCareers, Boolean updateCurrencies){

      try {
        if(title.length() > 30 || title == null){
            System.out.print("Tamanho de nome inválido. ");
            return false;
        } else if(updateCareers == null || updateCareers == null || updateCurrencies == null){
            System.out.print("Não foi possível determinar as permissões do cargo em questão. ");
            return false;
        }  
        Career newCareer = new Career(title, updateEmployees, updateCareers, updateCurrencies);
        careerDAO.save(newCareer);
        return true;
      } catch (Exception e) {
        return false;
      }

    }

    public static Boolean removeCareer(String title){

      try {
        title = title.toUpperCase();
        careerDAO.delete((long) title.hashCode());
        return true;
      } catch (Exception e) {
        return false;
      }

    }
}
