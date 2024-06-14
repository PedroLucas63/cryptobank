package bank.view;

import bank.service.EmployeeService;
import bank.utils.InputValidator;

public class UpdateCurrencyView extends ViewAbstract{

    enum State {
        BEGIN, MENU, ADD_FIATCURRENCY, ADD_CRYPTOCURRENCY, REMOVE_CURRENCY,END,
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
            state = State.ADD_CRYPTOCURRENCY;
        } else if (++i == entryOption) {
            state = State.ADD_FIATCURRENCY;
        } else if (++i == entryOption) {
            state = State.REMOVE_CURRENCY;
        }
    }

    private void menu() {
        title();

        Integer i = 0;

        System.out.println(++i + ". Adicionar Criptomoeda");
        System.out.println(++i + ". Adicionar moeda fiduciária");
        System.out.println(++i + ". Remover moeda");

        System.out.println("\n0. Voltar");
        System.out.print("Selecione uma opção: ");
    }  

    private void addFiatCurrency(){
        String name;
        String symbol;
        Double value;
        System.out.print("Digite o nome da moeda a ser adicionada: ");
        name = InputValidator.getString();
        System.out.print("Digite o símbolo da moeda a ser adicionada: ");
        symbol = InputValidator.getString();
        System.out.print("Digite o valor da moeda a ser adicionada: ");
        value = InputValidator.getDouble();
        if(EmployeeService.addFiatCurrency(name, symbol, value)){
            System.out.println("A moeda foi adicionada com sucesso!");
        } else {
            System.out.println("Não foi possível adicionar a moede. Revise as informações e tente novamente.");
        }
    }

    @Override
    public void process() {
        warning = null;
        switch (state) {
            case BEGIN:
                break;
            case MENU:
                getEntryOption();
                break;
            case ADD_CRYPTOCURRENCY:
                break;
            case REMOVE_CURRENCY:
                break;
            case END:
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
            case ADD_FIATCURRENCY:
                state = State.END;
                break;
            case ADD_CRYPTOCURRENCY:
                state = State.END;
                break;
            case REMOVE_CURRENCY:
                break;
            case END:
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
            case ADD_CRYPTOCURRENCY:
                break;
            case ADD_FIATCURRENCY:
                addFiatCurrency();
                break;
            case REMOVE_CURRENCY:
                break;
            case END:
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
