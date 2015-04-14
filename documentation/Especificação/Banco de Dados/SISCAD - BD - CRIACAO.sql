
CREATE SEQUENCE public.sec_recurso_idrecurso_seq;

CREATE TABLE public.sec_recurso (
                idRecurso INTEGER NOT NULL DEFAULT nextval('public.sec_recurso_idrecurso_seq'),
                tipoRecurso VARCHAR(20) NOT NULL,
                recurso VARCHAR(300) NOT NULL,
                seguro BOOLEAN NOT NULL,
                CONSTRAINT sec_recurso_pk PRIMARY KEY (idRecurso)
);


ALTER SEQUENCE public.sec_recurso_idrecurso_seq OWNED BY public.sec_recurso.idRecurso;

CREATE UNIQUE INDEX recurso_uk
 ON public.sec_recurso
 ( recurso );

CREATE SEQUENCE public.sec_perfil_idperfil_seq;

CREATE TABLE public.sec_perfil (
                idPerfil INTEGER NOT NULL DEFAULT nextval('public.sec_perfil_idperfil_seq'),
                perfil VARCHAR(30) NOT NULL,
                CONSTRAINT sec_perfil_pk PRIMARY KEY (idPerfil)
);


ALTER SEQUENCE public.sec_perfil_idperfil_seq OWNED BY public.sec_perfil.idPerfil;

CREATE SEQUENCE public.sec_perfilrecurso_idperfilrecurso_seq;

CREATE TABLE public.sec_perfilRecurso (
                idPerfilRecurso INTEGER NOT NULL DEFAULT nextval('public.sec_perfilrecurso_idperfilrecurso_seq'),
                idPerfil INTEGER NOT NULL,
                idRecurso INTEGER NOT NULL,
                CONSTRAINT sec_perfilrecurso_pk PRIMARY KEY (idPerfilRecurso)
);


ALTER SEQUENCE public.sec_perfilrecurso_idperfilrecurso_seq OWNED BY public.sec_perfilRecurso.idPerfilRecurso;

CREATE UNIQUE INDEX sec_perfilrecurso_idx
 ON public.sec_perfilRecurso
 ( idPerfil, idRecurso );

CREATE SEQUENCE public.disciplina_iddisciplina_seq;

CREATE TABLE public.Disciplina (
                idDisciplina INTEGER NOT NULL DEFAULT nextval('public.disciplina_iddisciplina_seq'),
                nome VARCHAR(50) NOT NULL,
                descricao VARCHAR(100) NOT NULL,
                abreviacao VARCHAR(10) NOT NULL,
                CONSTRAINT disciplina_pk PRIMARY KEY (idDisciplina)
);


ALTER SEQUENCE public.disciplina_iddisciplina_seq OWNED BY public.Disciplina.idDisciplina;

CREATE UNIQUE INDEX disciplina_idx
 ON public.Disciplina
 ( abreviacao );

CREATE SEQUENCE public.turno_idturno_seq;

CREATE TABLE public.Turno (
                idTurno INTEGER NOT NULL DEFAULT nextval('public.turno_idturno_seq'),
                descricao VARCHAR(100) NOT NULL,
                horaInicio TIME NOT NULL,
                horaTermino TIME NOT NULL,
                CONSTRAINT turno_pk PRIMARY KEY (idTurno)
);


ALTER SEQUENCE public.turno_idturno_seq OWNED BY public.Turno.idTurno;

CREATE UNIQUE INDEX turno_idx
 ON public.Turno
 ( descricao );

CREATE SEQUENCE public.curso_idcurso_seq;

CREATE TABLE public.Curso (
                idCurso INTEGER NOT NULL DEFAULT nextval('public.curso_idcurso_seq'),
                descricao VARCHAR(100) NOT NULL,
                ementa VARCHAR(1000) NOT NULL,
                CONSTRAINT curso_pk PRIMARY KEY (idCurso)
);


ALTER SEQUENCE public.curso_idcurso_seq OWNED BY public.Curso.idCurso;

CREATE UNIQUE INDEX curso_idx
 ON public.Curso
 ( descricao );

CREATE SEQUENCE public.periodoavaliacao_idperiodoavaliacao_seq;

CREATE TABLE public.PeriodoAvaliacao (
                idPeriodoAvaliacao INTEGER NOT NULL DEFAULT nextval('public.periodoavaliacao_idperiodoavaliacao_seq'),
                idCurso INTEGER NOT NULL,
                ano VARCHAR(5) NOT NULL,
                semestre VARCHAR(15) NOT NULL,
                abreviacao VARCHAR(10) NOT NULL,
                descricao VARCHAR(100) NOT NULL,
                CONSTRAINT periodoavaliacao_pk PRIMARY KEY (idPeriodoAvaliacao)
);


ALTER SEQUENCE public.periodoavaliacao_idperiodoavaliacao_seq OWNED BY public.PeriodoAvaliacao.idPeriodoAvaliacao;

CREATE UNIQUE INDEX periodoavaliacao_idx
 ON public.PeriodoAvaliacao
 ( abreviacao, semestre );

CREATE SEQUENCE public.avaliacao_idavaliacao_seq;

CREATE TABLE public.Avaliacao (
                idAvaliacao INTEGER NOT NULL DEFAULT nextval('public.avaliacao_idavaliacao_seq'),
                idPeriodoAvaliacao INTEGER NOT NULL,
                abreviacao VARCHAR(5) NOT NULL,
                descricao VARCHAR(100) NOT NULL,
                peso REAL NOT NULL,
                CONSTRAINT avaliacao_pk PRIMARY KEY (idAvaliacao)
);


ALTER SEQUENCE public.avaliacao_idavaliacao_seq OWNED BY public.Avaliacao.idAvaliacao;

CREATE UNIQUE INDEX avaliacao_idx
 ON public.Avaliacao
 ( idPeriodoAvaliacao, abreviacao );

CREATE SEQUENCE public.serie_idserie_seq;

CREATE TABLE public.Serie (
                idSerie INTEGER NOT NULL DEFAULT nextval('public.serie_idserie_seq'),
                idCurso INTEGER NOT NULL,
                descricao VARCHAR(100) NOT NULL,
                ordem INTEGER NOT NULL,
                idSerieSucessora INTEGER NOT NULL,
                CONSTRAINT serie_pk PRIMARY KEY (idSerie)
);


ALTER SEQUENCE public.serie_idserie_seq OWNED BY public.Serie.idSerie;

CREATE UNIQUE INDEX serie_idx
 ON public.Serie
 ( descricao );

CREATE SEQUENCE public.telefone_idtelefone_seq;

CREATE TABLE public.Telefone (
                idTelefone INTEGER NOT NULL DEFAULT nextval('public.telefone_idtelefone_seq'),
                codigoArea INTEGER NOT NULL,
                numero VARCHAR(12) NOT NULL,
                tipo VARCHAR(20) NOT NULL,
                CONSTRAINT telefone_pk PRIMARY KEY (idTelefone)
);


ALTER SEQUENCE public.telefone_idtelefone_seq OWNED BY public.Telefone.idTelefone;

CREATE UNIQUE INDEX telefone_idx
 ON public.Telefone
 ( codigoArea, numero );

CREATE SEQUENCE public.endereco_idendereco_seq;

CREATE TABLE public.Endereco (
                idEndereco INTEGER NOT NULL DEFAULT nextval('public.endereco_idendereco_seq'),
                rua VARCHAR(200) NOT NULL,
                numero INTEGER NOT NULL,
                complemento VARCHAR(50),
                tipo VARCHAR(20) NOT NULL,
                bairro VARCHAR(100) NOT NULL,
                cidade VARCHAR(100) NOT NULL,
                estado VARCHAR(2) NOT NULL,
                cep VARCHAR(8) NOT NULL,
                CONSTRAINT endereco_pk PRIMARY KEY (idEndereco)
);


ALTER SEQUENCE public.endereco_idendereco_seq OWNED BY public.Endereco.idEndereco;

CREATE UNIQUE INDEX endereco_idx
 ON public.Endereco
 ( rua );

CREATE SEQUENCE public.escola_idescola_seq;

CREATE TABLE public.Escola (
                idEscola INTEGER NOT NULL DEFAULT nextval('public.escola_idescola_seq'),
                cnpj VARCHAR(15) NOT NULL,
                ie VARCHAR(20) NOT NULL,
                nome VARCHAR(200) NOT NULL,
                idEndereco INTEGER NOT NULL,
                CONSTRAINT escola_pk PRIMARY KEY (idEscola)
);


ALTER SEQUENCE public.escola_idescola_seq OWNED BY public.Escola.idEscola;

CREATE UNIQUE INDEX escola_idx
 ON public.Escola
 ( cnpj );

CREATE UNIQUE INDEX escola_nome_unique
 ON public.Escola
 ( nome );

CREATE SEQUENCE public.matrizcurricular_idmatrizcurricular_seq;

CREATE TABLE public.MatrizCurricular (
                idMatrizCurricular INTEGER NOT NULL DEFAULT nextval('public.matrizcurricular_idmatrizcurricular_seq'),
                idEscola INTEGER NOT NULL,
                idSerie INTEGER NOT NULL,
                ano VARCHAR(10) NOT NULL,
                semestre VARCHAR(50) NOT NULL,
                CONSTRAINT matrizcurricular_pk PRIMARY KEY (idMatrizCurricular)
);


ALTER SEQUENCE public.matrizcurricular_idmatrizcurricular_seq OWNED BY public.MatrizCurricular.idMatrizCurricular;

CREATE UNIQUE INDEX matrizcurricular_idx
 ON public.MatrizCurricular
 ( idEscola, idSerie );

CREATE SEQUENCE public.matrizcurriculardisciplina_idmatrizcurriculardisciplina_seq;

CREATE TABLE public.MatrizCurricularDisciplina (
                idMatrizCurricularDisciplina INTEGER NOT NULL DEFAULT nextval('public.matrizcurriculardisciplina_idmatrizcurriculardisciplina_seq'),
                idDisciplina INTEGER NOT NULL,
                idMatrizCurricular INTEGER NOT NULL,
                ementa VARCHAR(10000) NOT NULL,
                CONSTRAINT matrizcurriculardisciplina_pk PRIMARY KEY (idMatrizCurricularDisciplina)
);


ALTER SEQUENCE public.matrizcurriculardisciplina_idmatrizcurriculardisciplina_seq OWNED BY public.MatrizCurricularDisciplina.idMatrizCurricularDisciplina;

CREATE UNIQUE INDEX matrizcurriculardisciplina_idx
 ON public.MatrizCurricularDisciplina
 ( idDisciplina, idMatrizCurricular );

CREATE SEQUENCE public.escolacurso_idescolacurso_seq;

CREATE TABLE public.EscolaCurso (
                idEscolaCurso INTEGER NOT NULL DEFAULT nextval('public.escolacurso_idescolacurso_seq'),
                idCurso INTEGER NOT NULL,
                idEscola INTEGER NOT NULL,
                CONSTRAINT escolacurso_pk PRIMARY KEY (idEscolaCurso)
);


ALTER SEQUENCE public.escolacurso_idescolacurso_seq OWNED BY public.EscolaCurso.idEscolaCurso;

CREATE SEQUENCE public.turma_idturma_seq;

CREATE TABLE public.Turma (
                idTurma INTEGER NOT NULL DEFAULT nextval('public.turma_idturma_seq'),
                idSerie INTEGER NOT NULL,
                descricao VARCHAR(100) NOT NULL,
                codigo VARCHAR(10) NOT NULL,
                semestre VARCHAR(50) NOT NULL,
                ano VARCHAR(10) NOT NULL,
                idTurno INTEGER NOT NULL,
                idEscola INTEGER NOT NULL,
                status VARCHAR(20) NOT NULL,
                CONSTRAINT turma_pk PRIMARY KEY (idTurma)
);


ALTER SEQUENCE public.turma_idturma_seq OWNED BY public.Turma.idTurma;

CREATE UNIQUE INDEX turma_idx
 ON public.Turma
 ( descricao, codigo, semestre, ano );

CREATE SEQUENCE public.escolatelefone_idescolatelefone_seq;

CREATE TABLE public.EscolaTelefone (
                idEscolaTelefone INTEGER NOT NULL DEFAULT nextval('public.escolatelefone_idescolatelefone_seq'),
                idEscola INTEGER NOT NULL,
                idTelefone INTEGER NOT NULL,
                CONSTRAINT escolatelefone_pk PRIMARY KEY (idEscolaTelefone)
);


ALTER SEQUENCE public.escolatelefone_idescolatelefone_seq OWNED BY public.EscolaTelefone.idEscolaTelefone;

CREATE UNIQUE INDEX escolatelefone_idx
 ON public.EscolaTelefone
 ( idEscola, idTelefone );

CREATE SEQUENCE public.pessoa_idpessoa_seq;

CREATE TABLE public.Pessoa (
                idPessoa INTEGER NOT NULL DEFAULT nextval('public.pessoa_idpessoa_seq'),
                cpf VARCHAR(15) NOT NULL,
                nome VARCHAR(150) NOT NULL,
                estadoCivil VARCHAR(50) NOT NULL,
                email VARCHAR(100) NOT NULL,
                CONSTRAINT pessoa_pk PRIMARY KEY (idPessoa)
);


ALTER SEQUENCE public.pessoa_idpessoa_seq OWNED BY public.Pessoa.idPessoa;

CREATE UNIQUE INDEX pessoa_idx
 ON public.Pessoa
 ( cpf );

CREATE SEQUENCE public.tipofuncao_idtipofuncao_seq;

CREATE TABLE public.TipoFuncao (
                idTipoFuncao INTEGER NOT NULL DEFAULT nextval('public.tipofuncao_idtipofuncao_seq'),
                idPessoa INTEGER NOT NULL,
                codigoFuncao VARCHAR(20) NOT NULL,
                dtype VARCHAR(30) NOT NULL,
                ativo BOOLEAN NOT NULL,
                CONSTRAINT tipofuncao_pk PRIMARY KEY (idTipoFuncao)
);


ALTER SEQUENCE public.tipofuncao_idtipofuncao_seq OWNED BY public.TipoFuncao.idTipoFuncao;

CREATE UNIQUE INDEX tipofuncao_idx
 ON public.TipoFuncao
 ( idPessoa, codigoFuncao );

CREATE TABLE public.Professor (
                idTipoFuncao INTEGER NOT NULL,
                matricula INTEGER NOT NULL,
                CONSTRAINT professor_pk PRIMARY KEY (idTipoFuncao)
);


CREATE UNIQUE INDEX professor_idx
 ON public.Professor
 ( matricula );

CREATE SEQUENCE public.alocacao_idalocacao_seq;

CREATE TABLE public.Alocacao (
                idAlocacao INTEGER NOT NULL DEFAULT nextval('public.alocacao_idalocacao_seq'),
                idTipoFuncao INTEGER NOT NULL,
                idTurma INTEGER NOT NULL,
                idDisciplina INTEGER NOT NULL,
                CONSTRAINT alocacao_pk PRIMARY KEY (idAlocacao)
);


ALTER SEQUENCE public.alocacao_idalocacao_seq OWNED BY public.Alocacao.idAlocacao;

CREATE UNIQUE INDEX alocacao_idx
 ON public.Alocacao
 ( idTurma, idDisciplina, idTipoFuncao );

CREATE TABLE public.Responsavel (
                idTipoFuncao INTEGER NOT NULL,
                profissao VARCHAR(40) NOT NULL,
                localTrabalho VARCHAR(100) NOT NULL,
                renda REAL NOT NULL,
                CONSTRAINT responsavel_pk PRIMARY KEY (idTipoFuncao)
);


CREATE SEQUENCE public.aluno_idaluno_seq;

CREATE TABLE public.Aluno (
                idAluno INTEGER NOT NULL DEFAULT nextval('public.aluno_idaluno_seq'),
                idTipoFuncao INTEGER NOT NULL,
                ra INTEGER NOT NULL,
                nome VARCHAR(200) NOT NULL,
                dataNascimento DATE NOT NULL,
                sexo VARCHAR(10) NOT NULL,
                localNascimento VARCHAR(100) NOT NULL,
                nacionalidade VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL,
                ativo BOOLEAN NOT NULL,
                CONSTRAINT aluno_pk PRIMARY KEY (idAluno)
);


ALTER SEQUENCE public.aluno_idaluno_seq OWNED BY public.Aluno.idAluno;

CREATE UNIQUE INDEX aluno_idx
 ON public.Aluno
 ( ra );

CREATE SEQUENCE public.sec_usuario_idusuario_seq;

CREATE TABLE public.sec_usuario (
                idusuario INTEGER NOT NULL DEFAULT nextval('public.sec_usuario_idusuario_seq'),
                idPessoa INTEGER,
                idAluno INTEGER,
                idPerfil INTEGER NOT NULL,
                usuario VARCHAR(15) NOT NULL,
                senha VARCHAR(256) NOT NULL,
                dataCriacao TIMESTAMP NOT NULL,
                dataUltimoAcesso TIMESTAMP,
                dataExpiracao TIMESTAMP,
                localePadrao VARCHAR(5) NOT NULL,
                ativo BOOLEAN NOT NULL,
                CONSTRAINT sec_usuario_pk PRIMARY KEY (idusuario)
);


ALTER SEQUENCE public.sec_usuario_idusuario_seq OWNED BY public.sec_usuario.idusuario;

CREATE UNIQUE INDEX sec_usuario_idx
 ON public.sec_usuario
 ( usuario );

CREATE SEQUENCE public.matricula_idmatricula_seq;

CREATE TABLE public.Matricula (
                idMatricula INTEGER NOT NULL DEFAULT nextval('public.matricula_idmatricula_seq'),
                idTurma INTEGER NOT NULL,
                idAluno INTEGER NOT NULL,
                numeroOrdem INTEGER NOT NULL,
                situacao VARCHAR(50) NOT NULL,
                dataEntrada DATE NOT NULL,
                dataSaida DATE,
                CONSTRAINT matricula_pk PRIMARY KEY (idMatricula)
);


ALTER SEQUENCE public.matricula_idmatricula_seq OWNED BY public.Matricula.idMatricula;

CREATE UNIQUE INDEX matricula_idx
 ON public.Matricula
 ( idAluno, idTurma );

CREATE SEQUENCE public.frequencia_idfrequencia_seq;

CREATE TABLE public.Frequencia (
                idFrequencia INTEGER NOT NULL DEFAULT nextval('public.frequencia_idfrequencia_seq'),
                idMatricula INTEGER NOT NULL,
                idPeriodoAvaliacao INTEGER NOT NULL,
                idDisciplina INTEGER NOT NULL,
                valor REAL NOT NULL,
                CONSTRAINT frequencia_pk PRIMARY KEY (idFrequencia)
);


ALTER SEQUENCE public.frequencia_idfrequencia_seq OWNED BY public.Frequencia.idFrequencia;

CREATE UNIQUE INDEX frequencia_idx
 ON public.Frequencia
 ( idMatricula, idPeriodoAvaliacao, idDisciplina );

CREATE SEQUENCE public.nota_idnota_seq;

CREATE TABLE public.Nota (
                idNota INTEGER NOT NULL DEFAULT nextval('public.nota_idnota_seq'),
                idAvaliacao INTEGER NOT NULL,
                idDisciplina INTEGER NOT NULL,
                idMatricula INTEGER NOT NULL,
                valor REAL NOT NULL,
                CONSTRAINT nota_pk PRIMARY KEY (idNota)
);


ALTER SEQUENCE public.nota_idnota_seq OWNED BY public.Nota.idNota;

CREATE UNIQUE INDEX nota_idx
 ON public.Nota
 ( idAvaliacao, idDisciplina, idMatricula );

CREATE SEQUENCE public.pessoatelefone_idpessoatelefone_seq;

CREATE TABLE public.PessoaTelefone (
                idPessoaTelefone INTEGER NOT NULL DEFAULT nextval('public.pessoatelefone_idpessoatelefone_seq'),
                idTelefone INTEGER NOT NULL,
                idPessoa INTEGER NOT NULL,
                CONSTRAINT pessoatelefone_pk PRIMARY KEY (idPessoaTelefone)
);


ALTER SEQUENCE public.pessoatelefone_idpessoatelefone_seq OWNED BY public.PessoaTelefone.idPessoaTelefone;

CREATE UNIQUE INDEX pessoatelefone_idx
 ON public.PessoaTelefone
 ( idTelefone, idPessoa );

CREATE SEQUENCE public.pessoaendereco_idpessoaendereco_seq;

CREATE TABLE public.PessoaEndereco (
                idPessoaEndereco INTEGER NOT NULL DEFAULT nextval('public.pessoaendereco_idpessoaendereco_seq'),
                idEndereco INTEGER NOT NULL,
                idPessoa INTEGER NOT NULL,
                CONSTRAINT pessoaendereco_pk PRIMARY KEY (idPessoaEndereco)
);


ALTER SEQUENCE public.pessoaendereco_idpessoaendereco_seq OWNED BY public.PessoaEndereco.idPessoaEndereco;

CREATE UNIQUE INDEX pessoaendereco_idx
 ON public.PessoaEndereco
 ( idEndereco, idPessoa );

ALTER TABLE public.sec_perfilRecurso ADD CONSTRAINT sec_recurso_sec_perfilrecurso_fk
FOREIGN KEY (idRecurso)
REFERENCES public.sec_recurso (idRecurso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sec_perfilRecurso ADD CONSTRAINT sec_perfil_sec_perfilrecurso_fk
FOREIGN KEY (idPerfil)
REFERENCES public.sec_perfil (idPerfil)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sec_usuario ADD CONSTRAINT sec_perfil_sec_usuario_fk
FOREIGN KEY (idPerfil)
REFERENCES public.sec_perfil (idPerfil)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Nota ADD CONSTRAINT disciplina_nota_fk
FOREIGN KEY (idDisciplina)
REFERENCES public.Disciplina (idDisciplina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Alocacao ADD CONSTRAINT disciplina_alocacao_fk
FOREIGN KEY (idDisciplina)
REFERENCES public.Disciplina (idDisciplina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Frequencia ADD CONSTRAINT disciplina_frequencia_fk
FOREIGN KEY (idDisciplina)
REFERENCES public.Disciplina (idDisciplina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.MatrizCurricularDisciplina ADD CONSTRAINT disciplina_matrizcurriculardisciplina_fk
FOREIGN KEY (idDisciplina)
REFERENCES public.Disciplina (idDisciplina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Turma ADD CONSTRAINT turno_turma_fk
FOREIGN KEY (idTurno)
REFERENCES public.Turno (idTurno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.EscolaCurso ADD CONSTRAINT curso_escolacurso_fk
FOREIGN KEY (idCurso)
REFERENCES public.Curso (idCurso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Serie ADD CONSTRAINT curso_serie_fk
FOREIGN KEY (idCurso)
REFERENCES public.Curso (idCurso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.PeriodoAvaliacao ADD CONSTRAINT curso_periodoavaliacao_fk
FOREIGN KEY (idCurso)
REFERENCES public.Curso (idCurso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Frequencia ADD CONSTRAINT periodoavaliacao_frequencia_fk
FOREIGN KEY (idPeriodoAvaliacao)
REFERENCES public.PeriodoAvaliacao (idPeriodoAvaliacao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Avaliacao ADD CONSTRAINT periodoavaliacao_avaliacao_fk
FOREIGN KEY (idPeriodoAvaliacao)
REFERENCES public.PeriodoAvaliacao (idPeriodoAvaliacao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Nota ADD CONSTRAINT avaliacao_nota_fk
FOREIGN KEY (idAvaliacao)
REFERENCES public.Avaliacao (idAvaliacao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Serie ADD CONSTRAINT serie_serie_fk
FOREIGN KEY (idSerieSucessora)
REFERENCES public.Serie (idSerie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.MatrizCurricular ADD CONSTRAINT serie_matrizcurricular_fk
FOREIGN KEY (idSerie)
REFERENCES public.Serie (idSerie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Turma ADD CONSTRAINT serie_turma_fk
FOREIGN KEY (idSerie)
REFERENCES public.Serie (idSerie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.PessoaTelefone ADD CONSTRAINT telefone_pessoatelefone_fk
FOREIGN KEY (idTelefone)
REFERENCES public.Telefone (idTelefone)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.EscolaTelefone ADD CONSTRAINT telefone_escolatelefone_fk
FOREIGN KEY (idTelefone)
REFERENCES public.Telefone (idTelefone)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.PessoaEndereco ADD CONSTRAINT endereco_pessoaendereco_fk
FOREIGN KEY (idEndereco)
REFERENCES public.Endereco (idEndereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Escola ADD CONSTRAINT endereco_escola_fk
FOREIGN KEY (idEndereco)
REFERENCES public.Endereco (idEndereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.EscolaTelefone ADD CONSTRAINT escola_escolatelefone_fk
FOREIGN KEY (idEscola)
REFERENCES public.Escola (idEscola)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Turma ADD CONSTRAINT escola_turma_fk
FOREIGN KEY (idEscola)
REFERENCES public.Escola (idEscola)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.EscolaCurso ADD CONSTRAINT escola_escolacurso_fk
FOREIGN KEY (idEscola)
REFERENCES public.Escola (idEscola)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.MatrizCurricular ADD CONSTRAINT escola_matrizcurricular_fk
FOREIGN KEY (idEscola)
REFERENCES public.Escola (idEscola)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.MatrizCurricularDisciplina ADD CONSTRAINT matrizcurricular_matrizcurriculardisciplina_fk
FOREIGN KEY (idMatrizCurricular)
REFERENCES public.MatrizCurricular (idMatrizCurricular)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Matricula ADD CONSTRAINT turma_matricula_fk
FOREIGN KEY (idTurma)
REFERENCES public.Turma (idTurma)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Alocacao ADD CONSTRAINT turma_alocacao_fk
FOREIGN KEY (idTurma)
REFERENCES public.Turma (idTurma)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.PessoaEndereco ADD CONSTRAINT pessoa_pessoaendereco_fk
FOREIGN KEY (idPessoa)
REFERENCES public.Pessoa (idPessoa)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.PessoaTelefone ADD CONSTRAINT pessoa_pessoatelefone_fk
FOREIGN KEY (idPessoa)
REFERENCES public.Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.TipoFuncao ADD CONSTRAINT pessoa_tipofuncao_fk
FOREIGN KEY (idPessoa)
REFERENCES public.Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sec_usuario ADD CONSTRAINT pessoa_sec_usuario_fk
FOREIGN KEY (idPessoa)
REFERENCES public.Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Responsavel ADD CONSTRAINT tipofuncao_responsavel_fk
FOREIGN KEY (idTipoFuncao)
REFERENCES public.TipoFuncao (idTipoFuncao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Professor ADD CONSTRAINT tipofuncao_professor_fk
FOREIGN KEY (idTipoFuncao)
REFERENCES public.TipoFuncao (idTipoFuncao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Alocacao ADD CONSTRAINT professor_alocacao_fk
FOREIGN KEY (idTipoFuncao)
REFERENCES public.Professor (idTipoFuncao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Aluno ADD CONSTRAINT responsavel_aluno_fk
FOREIGN KEY (idTipoFuncao)
REFERENCES public.Responsavel (idTipoFuncao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Matricula ADD CONSTRAINT aluno_matricula_fk
FOREIGN KEY (idAluno)
REFERENCES public.Aluno (idAluno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sec_usuario ADD CONSTRAINT aluno_sec_usuario_fk
FOREIGN KEY (idAluno)
REFERENCES public.Aluno (idAluno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Nota ADD CONSTRAINT matricula_nota_fk
FOREIGN KEY (idMatricula)
REFERENCES public.Matricula (idMatricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Frequencia ADD CONSTRAINT matricula_frequencia_fk
FOREIGN KEY (idMatricula)
REFERENCES public.Matricula (idMatricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
