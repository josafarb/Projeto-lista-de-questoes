package models;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Lista extends Model{

  public Lista (long id){
    this.id=id;
    
  }


@Id
public Long id;

@Constraints.Required
public String titulo;

@Constraints.Required
public String assunto;



@ManyToOne
public Professor professor;




@ManyToMany(mappedBy = "lista")
public List <Aluno> aluno = new ArrayList<Aluno>();



public static Model.Finder<Long,Lista> find = new Model.Finder<Long,Lista>(Long.class,Lista.class);


}
