/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.ws;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.NotasAluno;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.aluno.AlunoFacade;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.notasaluno.NotasAlunoFacade;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author bruno
 */
@WebService()
public class Consulta {

    String nomeAluno = null;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "alunosPorTurma")
    public List alunosPorTurma(@WebParam(name = "idTurma") final String idTurma) {

        List<String> lstAluno = new ArrayList<String>();

        Turma turma = ServiceLocator.getFacade(TurmaFacade.class).find(Integer.parseInt(idTurma));

        if (null != turma) {

            for (Aluno aluno : ServiceLocator.getFacade(AlunoFacade.class).findByTurma(turma)) {

                String msgToReturn = montaStringAluno(aluno);

                if (null != msgToReturn) {

                    lstAluno.add(msgToReturn);

                }

            }

        }

        return lstAluno;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "turmasPorEscola")
    public List turmasPorEscola(@WebParam(name = "idEscola") final String idEscola) {

        List<String> lstEscola = new ArrayList<String>();

        Escola escola = ServiceLocator.getFacade(EscolaFacade.class).find(Integer.parseInt(idEscola));

        if (null != escola) {

            for (Turma turma : ServiceLocator.getFacade(TurmaFacade.class).findTurmasAtivasByEscola(escola)) {

                String msgToReturn = montaStringTurma(turma);

                if (null != msgToReturn) {

                    lstEscola.add(msgToReturn);

                }

            }

        }

        return lstEscola;

    }

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "notasPorAluno")
    public String notasPorAluno(@WebParam(name = "raAluno") final int raAluno) {

        String notas = new String();

        for (NotasAluno notasAluno : ServiceLocator.getFacade(NotasAlunoFacade.class).findNotasPorAluno(raAluno)) {

            String msgToReturn = montaStringNotasAluno(notasAluno);

            if (null != msgToReturn) {
                notas = notas + msgToReturn;

            }
        }

        System.out.println(notas);
        
        return notas;
    }

    private String montaStringAluno(Aluno aluno) {

        if (null != aluno) {

            return aluno.getRa() + " - " + aluno.getNome();

        }

        return null;

    }

    private String montaStringNotasAluno(NotasAluno notasAluno) {

        if (null != notasAluno) {
           
            return notasAluno.getRa().toString() + "," + notasAluno.getAluno() + "," + notasAluno.getTurma() + "," + notasAluno.getDisciplina() + "," + notasAluno.getNota() + ";";

        }

        return null;

    }

    private String montaStringTurma(Turma turma) {

        if (null != turma) {

            return turma.getSerie().getDescricao() + " - " + turma.getDescricao();

        }

        return null;

    }
}
