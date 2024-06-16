package bank.view;

import bank.service.EmployeeService;
import bank.utils.InputValidator;

public class UpdateEmployeeView extends AbstractView {
  enum State {
    BEGIN, TITLE, DOCUMENT, SALARY, CAREER, END,
  };

  private State state = State.BEGIN;
  private String warning;
  private String document;
  private Float salary;
  private String career;

  private void getUserDocument() {
    document = InputValidator.getString();

    if (!EmployeeService.searchEmployee(document)) {
      warning = "O usuário não foi encontrado ou não é um funcionário.";
    }
  }

  private void getUserCareer() {
    career = InputValidator.getCareer();
    if (!EmployeeService.searchCareer(career)) {
      warning = "Essa carreira não está registrada.";
    } else {
      System.out.println("A carreira do funcionário de documento '" + document
          + "' foi atualiazada com sucesso!");
    }
  }

  private void getUserSalary() {
    salary = InputValidator.getSalary();
    if (!EmployeeService.validSalary(salary)) {
      warning = "Esse salário não é válido.";
    } else {
      System.out.println("O salário do funcionário de documento '" + document
          + "' foi atualizado com sucesso!");
    }
  }

  private void validateUser() {
    if (EmployeeService.updateInfo(document, salary, career)) {
      System.out.println(
          "As informações do funcionário foram atualizadas com sucesso!");
    } else {
      warning = "Houve algum problema na mudança dos dados! Revise-os e tente novamente.";
    }

    state = State.END;
  }

  @Override
  public void process() {
    warning = null;
    switch (state) {
    case BEGIN:
      break;
    case TITLE:
      break;
    case DOCUMENT:
      getUserDocument();
      break;
    case SALARY:
      getUserSalary();
      break;
    case CAREER:
      getUserCareer();
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
      state = State.TITLE;
      break;
    case TITLE:
      state = State.DOCUMENT;
      break;
    case DOCUMENT:
      state = State.SALARY;
      break;
    case SALARY:
      state = State.CAREER;
      break;
    case CAREER:
      validateUser();
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
    case TITLE:
      title();
      break;
    case DOCUMENT:
      System.out
          .print("Digite o documento do funcionário que se deseja atualizar: ");
      break;
    case SALARY:
      System.out.print("Digite o novo salário do funcionário de documento '"
          + document + "' : ");
      break;
    case CAREER:
      System.out.print("Digite a nova carreira do funcionário de documento '"
          + document + "' : ");
      break;
    case END:
      if (warning != null) {
        System.out.println("WARNING: " + warning);
      }
    default:
      break;
    }
  }

  @Override
  public Boolean exit() {
    return state == State.END;
  }
}
