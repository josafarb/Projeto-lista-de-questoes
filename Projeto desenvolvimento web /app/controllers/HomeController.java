package controllers;

import play.mvc.*;

import views.html.*;
import views.html.home.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


  //  public  Result index() {
      //  return ok(index.render()); // só renderiza
  //  }

    public Result sobre(){
      return ok(sobre.render());

    }

    public Result primeira(){
      //System.out.println("aqui vai tambem?");
      session("usuario","Alguem"); // botando uma variavel na sessão
      session("tipo","Professor");
      session("parametro","outro parametro aqui");
      return ok(primeiraPagina.render());
    }

    public Result sair(){
      //session().remove("usuario");
      session().clear();
      return ok(primeiraPagina.render());
    }

}
