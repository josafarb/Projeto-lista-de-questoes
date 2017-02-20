package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import views.html.*;
import java.util.*;
import play.data.FormFactory;
import views.html.home.*;
import views.html.aluno.*;
import controllers.*;
import models.*;


public class AlunoController extends Controller {


    public Result gerarIndexAluno(){
        List<Lista> lista = Lista.find.findList();
        return ok(indexAluno.render(lista));
      //return ok(index.render());
    }


}
