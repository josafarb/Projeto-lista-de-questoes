package models;
import java.util.*;
import javax.persistence.*;
//import play.db.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Resposta extends Model{

public Resposta(Long id,String resposta){
  this.id = id;
  this.resposta = resposta;
}
  private static final long serialVersionUID = 1L;

@Id
public long id;

//@Constraints.Required
//@Version
//@Column(name="Price", columnDefinition="Decimal(10,2) default '100.00'")
@Column(columnDefinition="VARCHAR(255) default 'resposta'")
public String resposta;



@ManyToOne
public Aluno aluno;

@ManyToOne
public Questao questao;


public static Model.Finder<Long,Resposta> find = new Model.Finder<Long,Resposta>(Long.class,Resposta.class);


}
