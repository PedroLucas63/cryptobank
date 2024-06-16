package bank.view;

import bank.entity.Employee;
import bank.service.AuthService;
import bank.service.EmployeeService;
import bank.utils.InputValidator;

public class EmployeeView extends ViewAbstract{
   enum State {
      BEGIN, MENU, UPDATE_EMPLOYEE, GET_INFO, GET_CURERNCYINFO, UPDATE_CAREER, UPDATE_CURRENCY, END,
   };
   private State state = State.BEGIN;
   private Integer entryOption;
   private View updateEmployeeView = new UpdateEmployeeView();
   private View updateCurrencyView = new UpdateCurrencyView();
   
   private void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 0) {
         state = State.END;
      }

      Integer i = 0;
      Employee actualEmployee = (Employee)AuthService.getUser();

      if(++i == entryOption){
         state = State.GET_INFO;
      } else if (++i == entryOption) {
         state = State.GET_CURERNCYINFO;
      } else if (++i == entryOption && actualEmployee.getCareer().getUpdateCareers()) {
         state = State.UPDATE_CAREER;
      } else if (++i == entryOption && actualEmployee.getCareer().getUpdateCurrencies()) {
         state = State.UPDATE_CURRENCY;
      } else if (++i == entryOption && actualEmployee.getCareer().getUpdateEmployees()){
         state = State.UPDATE_EMPLOYEE;
      } else {
         warning = "O funcionário em questão não possui permissões cadastradas no sistema.";
      }
   }

   private void menu() {
      title();
      System.out.println("\n=========== CONTA DE FUNCIONÁRIO ===========");

      if (warning != null) {
         System.out.println("\nAviso: " + warning + "\n");
      }

      Integer i = 0;

      Employee actualEmployee = (Employee)AuthService.getUser();

      System.out.println(++i + ". Consultar informações de funcionários");
      System.out.println(++i + ". Consultar informações de moedas");
      if (actualEmployee.getCareer().getUpdateCareers()) {
         System.out.println(++i + ". Administrar profissões");
      }
      if (actualEmployee.getCareer().getUpdateCurrencies()){
         System.out.println(++i + ". Administrar moedas");
      }
      if (actualEmployee.getCareer().getUpdateEmployees()){
         System.out.println(++i + ". Administrar funcionários");
      }

      System.out.println("\n0. Voltar");
      System.out.print("Selecione uma opção: ");
   }

   private void getInfo(){
      System.out.print("Digite o documento do funcionário que se deseja consultar: ");
      String temp = InputValidator.getString();
      EmployeeService.showEmployeeInfo(temp);
   }

   private void getCurrencyInfo(){
      System.out.print("Digite o nome da moeda que se deseja consultar: ");
      String temp = InputValidator.getString();
      EmployeeService.showCurrencyInfo(temp);
   }

   @Override
   public void process() {
      warning = null;

      switch (state) {
      case MENU:
         getEntryOption();
         break;
      case UPDATE_EMPLOYEE:
         updateEmployeeView.startView();
         break;
      case GET_INFO:
         getInfo();
         break;
      case GET_CURERNCYINFO:
         getCurrencyInfo();
         break;
      case UPDATE_CURRENCY:
         updateCurrencyView.startView();
         break;
      default:
         break;
      }
   }

   @Override
   public void update() {
      if (warning != null) {
         state = State.END;
         return;
      }

      switch (state) {
      case BEGIN:
         state = State.MENU;
         break;
      case MENU:
         validateEntry();
         break;
      case UPDATE_EMPLOYEE:
         state = State.BEGIN;
         break;
         case UPDATE_CURRENCY:
         state = State.BEGIN;
         break;
      case GET_INFO:
         state = State.BEGIN;
         break;
      case GET_CURERNCYINFO:
         state = State.BEGIN;
         break;
      case END:
         state = State.BEGIN;
         break;
      default:
         state = State.MENU;
         break;
      }
   }
   @Override
   public void view() {
      switch (state) {
      case MENU:
         menu();
         break;
      default:
         break;
      }
   }
   @Override
   public Boolean exit() {
      return state == State.END;
   }
}
