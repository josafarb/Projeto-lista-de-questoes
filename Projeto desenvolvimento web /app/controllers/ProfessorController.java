package controllers;

import java.util.*;
import play.mvc.*;

import views.html.*;
import views.html.home.*;
import views.html.professor.*;
import models.*;




public class ProfessorController extends Controller {


    public Result gerarIndexProfessor(){
      // List<Lista> lista = Lista.find.findList();
       String idProfessor = session("idUsuario");
       System.out.println(idProfessor +"id professor");
       List<Lista> lista = Lista.find.where().eq("professor.id",idProfessor).findList();
      // botar o id do professor ai para pegar somente a lista dele
        return ok(indexProfessor.render(lista));
    }

    public  Result listaProfessores() {
          List<Professor> professor = Professor.find.findList();
          return ok(listaProfessores.render(professor));
    }



}
