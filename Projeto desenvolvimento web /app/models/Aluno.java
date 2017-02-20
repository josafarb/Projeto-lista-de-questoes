package models;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Aluno extends Model{

  private static final long serialVersionUID = 1L;
  public Aluno(long id){
    this.id = id;
  }
@Id
public long id;

@Constraints.Required
public String nome;

@Constraints.Required
public String sobrenome;

@Constraints.Required
public String email;

@Constraints.Required
public String senha;


@ManyToMany //(cascade = CascadeType.ALL)
public List <Lista> lista = new ArrayList<Lista>();

@ManyToOne
public Permissao permissao;



public static Model.Finder<Long,Aluno> find = new Model.Finder<Long,Aluno>(Long.class,Aluno.class);


}
