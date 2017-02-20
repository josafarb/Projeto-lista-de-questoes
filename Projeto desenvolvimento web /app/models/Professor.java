package models;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Professor extends Model{
  public Professor(int id,String nome,String sobrenome,String email,String senha){
    this.id =  id;
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.email = email;
    this.senha = senha;
  }

  private static final long serialVersionUID = 1L;

@Id
public int id;

@Constraints.Required
public String nome;

@Constraints.Required
public String sobrenome;

@Constraints.Required
public String email;

@Constraints.Required
public String senha;

@ManyToOne
public Permissao permissao;

/*public Permissao getTipoPermissao(Long id){
  return Tipo.find.where.idEq(id).
  findUnique();
}*/
public static Model.Finder<Long,Professor> find = new Model.Finder<Long,Professor>(Long.class,Professor.class);


}
