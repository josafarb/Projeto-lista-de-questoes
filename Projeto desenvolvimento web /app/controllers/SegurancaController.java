package controllers;
import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import views.html.*;
import views.html.home.*;
import views.html.aluno.*;
import views.html.professor.*;
import models.*;
import play.data.FormFactory;


public class SegurancaController extends Controller {
    private String tipoUsuario = " ";

    public Result gerarTelaLogin(){
      Form<Login> formulario = Form.form(Login.class);
      return ok(telaLogin.render(formulario));
    }

    // public Result login() {

        /*tipoUsuario = "professor";
        session("nomeUsuario","José");

        // aqui tembem deve identificar se é um aluno ou um professor
        //e redirecionar para sua pagina
        if(tipoUsuario.equals("professor")){
          session("tipo","professor");
          return redirect("/indexProfessor");//pagina inicial do sistema do professor

        }else if(tipoUsuario.equals("aluno")){
          session("tipo","aluno");
          return redirect("/indexAluno");
        }
        // ainda vai ter a parte do aluno
        return null;*/
    // }


    public Result acessar(){
      Form<Login> formulario  = Form.form(Login.class).bindFromRequest();
      if(formulario.get().usuario == "" || formulario.get().senha== ""){
        flash("erroLogin","Usuário e senha devem ser informados !");
        return badRequest(telaLogin.render(formulario));
      }

      Aluno aluno = Aluno.find.where().eq("email", formulario.get().usuario).eq("senha", formulario.get().senha).findUnique();
      if(aluno == null){
        Professor professor = Professor.find.where().eq("email", formulario.get().usuario).eq("senha", formulario.get().senha).findUnique();
        if(professor == null){
          flash("usuarioNaoEncontrado","Usuário e/ou senha incorreto(s)");
          return badRequest(telaLogin.render(formulario));
        }else{
          session("nomeUsuario",professor.nome);
          session("idUsuario",Integer.toString(professor.id));
          // return ok(indexProfessor.render());
          return redirect ("/indexProfessor");
        }
      }

      session("nomeUsuario",aluno.nome);
      session("idUsuario",Long.toString(aluno.id));
      // return ok(ProfessorController.gerarIndexProfessor);
      return redirect("/indexAluno");
    }





    public Result logout(){
      session().clear();
      return redirect("/");//volta para a tela de login que ta com a url raiz
    }



}
