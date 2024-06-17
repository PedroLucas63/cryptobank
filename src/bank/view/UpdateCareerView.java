package bank.view;

import bank.service.UpdateCareerService;
import bank.utils.InputValidator;

public class UpdateCareerView extends ViewAbstract {

    enum State {
        BEGIN, MENU, ADD_CAREER, REMOVE_CAREER,END,
    };
    private State state = State.BEGIN;
    private String warning;  
    private Integer entryOption;

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

        if(++i == entryOption){
            state = State.ADD_CAREER;
        } else if (++i == entryOption) {
            state = State.REMOVE_CAREER;
        } else {
            warning = "Opção inválida!";
        }
    }

    private void menu() {
        title();

        Integer i = 0;

        System.out.println(++i + ". Adicionar cargo");
        System.out.println(++i + ". Remover cargo");

        System.out.println("\n0. Voltar");
        System.out.print("Selecione uma opção: ");
    }

    private void addCareer(){
        String title;
        Boolean updateEmployees, updateCareer, updateCurrencies;
        System.out.print("Digite o nome do cargo que se deseja adicionar: ");
            title = InputValidator.getCareer();
        System.out.print("Esse cargo terá permissão para modificar funcionários? [Y/N]");
            updateEmployees = InputValidator.getYesOrNo();
        System.out.print("Esse cargo terá permissão de adicionar/remover cargos? [Y/N]");
            updateCareer = InputValidator.getYesOrNo();
        System.out.print("Esse cargo terá permissão de mexer em moedas? [Y/N]");
            updateCurrencies = InputValidator.getYesOrNo();
        System.out.println();
        if(UpdateCareerService.addCareer(title, updateEmployees, updateCareer, updateCurrencies)){
            System.out.println("A carreira foi adicionada com sucesso ao sistema.");
        } else {
            System.out.println("Não foi possível adicionar a carreira ao sistema, revise as informações. ");
        }
    }

    private void removeCareer(){
        String title;
        title = InputValidator.getString();
        if(UpdateCareerService.removeCareer(title)){
            System.out.println("A carreira foi removida com sucesso do sistema. ");
        } else {
            System.out.println("Não foi possível remover a carreira do sistema. ");
        }
    }

    @Override
    public void process() {

       warning = null;
        switch (state) {
            case MENU:
                getEntryOption();
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
            case ADD_CAREER:
                state = State.END;
                break;
            case REMOVE_CAREER:
                state = State.END;
                break;
            case END:
                state = State.BEGIN;
                break;
            default:
                break;
        }
        
    }
    @Override
    public void view() {

        switch (state) {
            case MENU:
                menu();
                break;
            case ADD_CAREER:
                addCareer();
                break;
            case REMOVE_CAREER:
                removeCareer();
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
