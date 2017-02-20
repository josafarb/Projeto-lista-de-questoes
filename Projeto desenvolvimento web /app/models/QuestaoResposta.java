package models;
import java.util.*;
import javax.persistence.*;
//import play.db.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;


public class QuestaoResposta extends Model{

  private static final long serialVersionUID = 1L;



public String enunciadoQuestao;


public String gabaritoQuestao;


public Lista lista;


public Resposta resposta;


public int valor;

public static Model.Finder<Long,Questao> find = new Model.Finder<Long,Questao>(Long.class,Questao.class);


}
