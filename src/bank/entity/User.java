package bank.entity;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity {
   protected String name;
   protected String document;
   protected String password;
   protected Integer age;
   protected String email;
   protected List<Account> accounts = new ArrayList<>();

   public User(String name, String document, String password, Integer age,
         String email) {
      this.name = name;
      this.document = document;
      this.password = password;
      this.age = age;
      this.email = email;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDocument() {
      return document;
   }

   public void setDocument(String document) {
      this.document = document;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public List<Account> getAccounts() {
      return accounts;
   }

   public void addAccount(Account account) {
      accounts.add(account);
   }

   public void removeAccount(Account account) {
      accounts.remove(account);
   }

   @Override
   public int hashCode() {
      return document.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof User) {
         return ((User) obj).document == document;
      }

      return false;
   }
}
