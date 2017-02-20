package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Permissao extends Model{

@Id
public long id;

@Constraints.Required
public String permissao;

public static Model.Finder<Long,Permissao> find = new Model.Finder<Long,Permissao>(Long.class,Permissao.class);

/*public static Map<String,String> options(){
  LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
  for(Professor p : tipoUsuario.find.orderBy("nome").findList()){
    options.put(p.id.toString(),p.nome);
    }
  return options;
}*/

}
