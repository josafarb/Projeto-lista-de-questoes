# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aluno (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  sobrenome                     varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  permissao_id                  bigint,
  constraint pk_aluno primary key (id)
);

create table aluno_lista (
  aluno_id                      bigint not null,
  lista_id                      bigint not null,
  constraint pk_aluno_lista primary key (aluno_id,lista_id)
);

create table lista (
  id                            bigint auto_increment not null,
  titulo                        varchar(255),
  assunto                       varchar(255),
  professor_id                  integer,
  constraint pk_lista primary key (id)
);

create table permissao (
  id                            bigint auto_increment not null,
  permissao                     varchar(255),
  constraint pk_permissao primary key (id)
);

create table professor (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  sobrenome                     varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  permissao_id                  bigint,
  constraint pk_professor primary key (id)
);

create table questao (
  id                            bigint auto_increment not null,
  enunciado                     varchar(255),
  gabarito                      varchar(255),
  lista_id                      bigint,
  valor                         double,
  constraint pk_questao primary key (id)
);

create table resposta (
  id                            bigint auto_increment not null,
  resposta                      varchar(255) default 'resposta',
  aluno_id                      bigint,
  questao_id                    bigint,
  constraint pk_resposta primary key (id)
);

alter table aluno add constraint fk_aluno_permissao_id foreign key (permissao_id) references permissao (id) on delete restrict on update restrict;
create index ix_aluno_permissao_id on aluno (permissao_id);

alter table aluno_lista add constraint fk_aluno_lista_aluno foreign key (aluno_id) references aluno (id) on delete restrict on update restrict;
create index ix_aluno_lista_aluno on aluno_lista (aluno_id);

alter table aluno_lista add constraint fk_aluno_lista_lista foreign key (lista_id) references lista (id) on delete restrict on update restrict;
create index ix_aluno_lista_lista on aluno_lista (lista_id);

alter table lista add constraint fk_lista_professor_id foreign key (professor_id) references professor (id) on delete restrict on update restrict;
create index ix_lista_professor_id on lista (professor_id);

alter table professor add constraint fk_professor_permissao_id foreign key (permissao_id) references permissao (id) on delete restrict on update restrict;
create index ix_professor_permissao_id on professor (permissao_id);

alter table questao add constraint fk_questao_lista_id foreign key (lista_id) references lista (id) on delete restrict on update restrict;
create index ix_questao_lista_id on questao (lista_id);

alter table resposta add constraint fk_resposta_aluno_id foreign key (aluno_id) references aluno (id) on delete restrict on update restrict;
create index ix_resposta_aluno_id on resposta (aluno_id);

alter table resposta add constraint fk_resposta_questao_id foreign key (questao_id) references questao (id) on delete restrict on update restrict;
create index ix_resposta_questao_id on resposta (questao_id);


# --- !Downs

alter table aluno drop foreign key fk_aluno_permissao_id;
drop index ix_aluno_permissao_id on aluno;

alter table aluno_lista drop foreign key fk_aluno_lista_aluno;
drop index ix_aluno_lista_aluno on aluno_lista;

alter table aluno_lista drop foreign key fk_aluno_lista_lista;
drop index ix_aluno_lista_lista on aluno_lista;

alter table lista drop foreign key fk_lista_professor_id;
drop index ix_lista_professor_id on lista;

alter table professor drop foreign key fk_professor_permissao_id;
drop index ix_professor_permissao_id on professor;

alter table questao drop foreign key fk_questao_lista_id;
drop index ix_questao_lista_id on questao;

alter table resposta drop foreign key fk_resposta_aluno_id;
drop index ix_resposta_aluno_id on resposta;

alter table resposta drop foreign key fk_resposta_questao_id;
drop index ix_resposta_questao_id on resposta;

drop table if exists aluno;

drop table if exists aluno_lista;

drop table if exists lista;

drop table if exists permissao;

drop table if exists professor;

drop table if exists questao;

drop table if exists resposta;

