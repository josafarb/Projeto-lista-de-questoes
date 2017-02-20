package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import views.html.*;
import views.html.home.*;
import views.html.professor.*;
import views.html.aluno.*;
import models.*;
import play.data.FormFactory;
import java.util.ArrayList;

public class QuestaoController extends Controller {
    public  Result gerarPaginaQuestao(long id){
      List<Questao> lista = Questao.find.where().eq("lista.id",id).findList();
        return ok(questoes.render(id,lista));
    }

    public  Result gerarPaginaQuestaoAluno(long id){
        List<Questao> lista = Questao.find.where().eq("lista.id",id).findList();
        Long idUsuario = Long.parseLong(session("idUsuario"));
        List<Resposta> listaResposta = Resposta.find.where().eq("questao.lista.id",id).eq("aluno.id",idUsuario).findList();
        List<Double> notas = new ArrayList<Double>();

        Long idResposta = Long.parseLong("1"); //cria uma resposta qualquer só para preencher a lista
        Resposta resposta = new Resposta(idResposta," ----- ");//cria uma resposta qualquer só para preencher a lista

        if(listaResposta.isEmpty()){
            for(int i = 0; i < lista.size(); i++){
                listaResposta.add(resposta);
            }
        }else if(lista.size() > listaResposta.size()){
              int indice = lista.size();
              for(int i= listaResposta.size(); i < lista.size(); i++){
                  listaResposta.add(i,resposta);
              }
        }
        double notaAluno = 0;
        for(int i=0;i < lista.size(); i++){
            Questao listaQuestao = lista.get(i);
            String gabarito = listaQuestao.gabarito;
            Resposta resultadoQuestao = listaResposta.get(i);
            String respostaAluno = resultadoQuestao.resposta ;
            if(gabarito.equalsIgnoreCase(respostaAluno)){
              notas.add(listaQuestao.valor);
              notaAluno += listaQuestao.valor;
            }else{
                notas.add(0.0);
            }

        }
        return ok(questoesAluno.render(id,lista,listaResposta,notas,notaAluno));
    }

    public Result gerarPaginaAdicionarQuestao(Long id){
       Form<Questao> formulario = Form.form(Questao.class);
       return ok(adicionarQuestao.render(id,formulario));
    }


    public Result salvarQuestao(long id){
      Form<Questao> formulario = Form.form(Questao.class).bindFromRequest();
      if(formulario.hasErrors()){
        return badRequest(adicionarQuestao.render(id,formulario));
      }
        Lista listaa = new Lista(id);
        formulario.get().lista = listaa;
        formulario.get().lista = listaa;
        formulario.get().save();
        flash("questaoAdicionada","Questão adicionada com sucesso!");
        List<Questao> lista = Questao.find.where().eq("lista.id",id).findList();
        return ok(questoes.render(id,lista));
     }

     public Result gerarPaginaAdicionarResposta(long idQuestao){
       Form<Resposta> formulario = Form.form(Resposta.class);
       System.out.println("id questao ====" + idQuestao);
       return ok(adicionarResposta.render(idQuestao,formulario));
     }



     public  Result salvarRespostaAluno(long idQuestao){
           Form<Resposta> formulario = Form.form(Resposta.class).bindFromRequest();
            if(formulario.hasErrors()){
             return badRequest(adicionarResposta.render(idQuestao,formulario));
           }
             Questao questaoo = new Questao(idQuestao);
             int idAluno = Integer.parseInt(session("idUsuario"));
             Aluno alunoo  = new Aluno(idAluno);
             formulario.get().questao = questaoo;
             formulario.get().aluno = alunoo;
             formulario.get().save();
             flash("questaoRespondida","Sua resposta foi salva");

              Questao questao =   Questao.find.where().eq("id", idQuestao).findUnique();

              List<Questao> lista = Questao.find.where().eq("lista.id",questao.lista.id).findList();


              //List<Resposta> listaResposta = Resposta.find.where().eq("questao.lista.id",questao.lista.id).findList();
              Long idUsuario = Long.parseLong(session("idUsuario"));
              List<Resposta> listaResposta = Resposta.find.where().eq("questao.lista.id",questao.lista.id).eq("aluno.id",idUsuario).findList();

              Long idResposta = Long.parseLong("1");
              Resposta resposta = new Resposta(idResposta," ----- ");
              List<Double> notas = new ArrayList<Double>();
            if(listaResposta.isEmpty()){
                //List <Resposta> listaResposta2 = new ArrayList <Resposta>();
                for(int i = 0; i < lista.size(); i++){
                    listaResposta.add(resposta);
                }
                //return ok(questoesAluno.render(questao.lista.id,lista,listaResposta2));
            }else if(lista.size() > listaResposta.size()){
              List <Resposta> listaResposta2 = new ArrayList <Resposta>();
                  int indice = lista.size();
                  for(int i= listaResposta.size(); i < lista.size(); i++){
                      listaResposta.add(i,resposta);
                  }
                  //return ok(questoesAluno.render(questao.lista.id,lista,listaResposta));
            }

            Double notaAluno = 0.0;
            for(int i=0;i < lista.size(); i++){
                Questao listaQuestao = lista.get(i);
                String gabarito = listaQuestao.gabarito;


                Resposta resultadoQuestao = listaResposta.get(i);
                String respostaAluno = resultadoQuestao.resposta;

                System.out.println(resposta + " Resposta Aluno ");
                if(gabarito.equalsIgnoreCase(respostaAluno)){
                  notas.add(listaQuestao.valor);
                  notaAluno += listaQuestao.valor;
                }else{
                    notas.add(0.0);
                }
          }
           //return ok(questoesAluno.render(questao.lista.id,lista,listaResposta,notas,notaAluno));
           String id=  String.valueOf(questao.lista.id);
           return redirect ("/questoesAluno/" +id);
     }

   }
