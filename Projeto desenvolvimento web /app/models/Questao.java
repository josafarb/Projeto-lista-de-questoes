package models;
import java.util.*;
import javax.persistence.*;
//import play.db.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Questao extends Model{

  private static final long serialVersionUID = 1L;
public Questao (long id ){
  this.id = id;
}


@Id
public long id;

@Constraints.Required
public String enunciado;

@Constraints.Required
public String gabarito;

@ManyToOne
public Lista lista;

@OneToMany
public Resposta resposta;

@Constraints.Required
public double valor;


public static Model.Finder<Long,Questao> find = new Model.Finder<Long,Questao>(Long.class,Questao.class);


}
