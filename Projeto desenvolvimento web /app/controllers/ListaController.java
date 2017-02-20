package controllers;
import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import views.html.*;
import views.html.home.*;
import views.html.professor.*;
import models.*;
import play.data.FormFactory;

public class ListaController extends Controller {
    public  Result gerarPaginaCriarLista(){
       Form<Lista> formulario = Form.form(Lista.class);
        return ok(criarLista.render(formulario));
    }

    public Result salvarLista(){
      Form<Lista> formulario = Form.form(Lista.class).bindFromRequest();
      if(formulario.hasErrors()){
        return badRequest(criarLista.render(formulario));
      }
        flash("sucess","Lista criada com sucesso !");
        int idProfessor = Integer.parseInt(session("idUsuario"));
        Professor p = new Professor(idProfessor,"a","b","c","d");
        formulario.get().professor = p;
        formulario.get().save();
       return redirect("/indexProfessor");
     }

     public Result editarDadosLista(Long id){
         Form<Lista> formulario = Form.form(Lista.class).fill(Lista.find.byId(id));
         return ok(editarLista.render(id,formulario));
     }

     public Result salvarAtualizacaoLista(Long id){
       Form<Lista> formulario = Form.form(Lista.class).bindFromRequest();
       if(formulario.hasErrors()){
         return badRequest(editarLista.render(id,formulario));
       }
       flash("alteracao","Lista alterada com sucesso !");
       formulario.get().update();
       return redirect("/indexProfessor");

     }



}
