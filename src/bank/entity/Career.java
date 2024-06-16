package bank.entity;

public class Career extends Entity {
   private String title;
   private Boolean updateEmployees;
   private Boolean updateCareers;
   private Boolean updateCurrencies;

   public Career(String title, Boolean updateEmployees, Boolean updateCareers,
         Boolean updateCurrencies) {
      this.title = title.toLowerCase();
      this.updateEmployees = updateEmployees;
      this.updateCareers = updateCareers;
      this.updateCurrencies = updateCurrencies;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Boolean getUpdateEmployees() {
      return updateEmployees;
   }

   public void setUpdateEmployees(Boolean updateEmployees) {
      this.updateEmployees = updateEmployees;
   }

   public Boolean getUpdateCareers() {
      return updateCareers;
   }

   public void setUpdateCareers(Boolean updateCareers) {
      this.updateCareers = updateCareers;
   }

   public Boolean getUpdateCurrencies() {
      return updateCurrencies;
   }

   public void setUpdateCurrencies(Boolean updateCurrencies) {
      this.updateCurrencies = updateCurrencies;
   }

   @Override
   public int hashCode() {
      return title.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Career) {
         return ((Career) obj).getTitle().equalsIgnoreCase(title);
      }

      return false;
   }
}
