package schoolbot;

import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class School {
    /**
     * 
     */
    private String schoolName;
    private ArrayList<Classroom> listOfClasses;
    private ArrayList<Student> listOfStudents;
    private 
    
    School() {
        
    }

    School(String schoolName) {
        this.schoolName = schoolName;
    }

    School(String schoolname,  ArrayList<Classroom> listOfClasses, ArrayList<Student> listOfStudents) {
        this.schoolName = schoolname;
        this.listOfClasses = listOfClasses;
        this.listOfStudents = listOfStudents;
    }


    /**
     * Getters & Setters
     */

     /**
      * @return list of all classes at the university
      */
     public ArrayList<Classroom> getListOfClasses() {
         return listOfClasses;
     }
     
     /**
      * 
      * @return school name
      */
     public String getSchoolName() {
         return schoolName;
     }

     /**
      * 
      * @return
      */
     public ArrayList<Student> getListOfStudents() {
         return listOfStudents;
     }

      /**
       * 
       * @param listOfClasses
       */
      public void setListOfClasses(ArrayList<Classroom> listOfClasses) {
          this.listOfClasses = listOfClasses;
      }
      
      /**
       * 
       * @param listOfStudents
       */
      public void setListOfStudents(ArrayList<Student> listOfStudents) {
          this.listOfStudents = listOfStudents;
      }

      /**
       * 
       * @param schoolName
       */
      public void setSchoolName(String schoolName) {
          this.schoolName = schoolName;
      }

      public void addStudent(Student student) {
          listOfStudents.add(student);
      }

      public boolean removeStudent(Student student) {
         for ()
      } 


      public EmbedBuilder getAsEmbed() {
        EmbedBuilder pretyifyEmbed = new EmbedBuilder();
        p; 
          
      }

      @Override
      public String toString() {
          return "School [listOfClasses= " + listOfClasses + ", listOfStudents= " + listOfStudents + ", schoolName= " 
                  + schoolName + "]";
      }

      @Override
      public int hashCode() {
          final int prime = 31;
          int result = 1;
          result = prime * result + ((listOfClasses == null) ? 0 : listOfClasses.hashCode());
          result = prime * result + ((listOfStudents == null) ? 0 : listOfStudents.hashCode());
          result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
          return result;
      }

      @Override
      public boolean equals(Object obj) {
          if (this == obj)
              return true;
          if (obj == null)
              return false;
          if (getClass() != obj.getClass())
              return false;
          School other = (School) obj;
          if (listOfClasses == null) {
              if (other.listOfClasses != null)
                  return false;
          } else if (!listOfClasses.equals(other.listOfClasses))
              return false;
          if (listOfStudents == null) {
              if (other.listOfStudents != null)
                  return false;
          } else if (!listOfStudents.equals(other.listOfStudents))
              return false;
          if (schoolName == null) {
              if (other.schoolName != null)
                  return false;
          } else if (!schoolName.equals(other.schoolName))
              return false;
          return true;
      }

    


}
