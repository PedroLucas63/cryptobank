package bank.entity;

public class Role extends Entity {
   private String title;
   private Boolean updateEmployees;
   private Boolean updateRoles;

   public Role(String title, Boolean updateEmployees, Boolean updateRoles) {
      this.title = title;
      this.updateEmployees = updateEmployees;
      this.updateRoles = updateRoles;
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

   public Boolean getUpdateRoles() {
      return updateRoles;
   }

   public void setUpdateRoles(Boolean updateRoles) {
      this.updateRoles = updateRoles;
   }

   @Override
   public int hashCode() {
      return title.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Role) {
         return ((Role) obj).getTitle().equals(title);
      }

      return false;
   }
}
