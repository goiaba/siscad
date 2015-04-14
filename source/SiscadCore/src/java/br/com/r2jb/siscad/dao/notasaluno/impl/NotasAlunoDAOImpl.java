/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.notasaluno.impl;

import br.com.r2jb.siscad.business.entity.NotasAluno;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.notasaluno.NotasAlunoDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joaquim
 */
@Repository("NotasAlunoDAO")
public class NotasAlunoDAOImpl extends BaseDAOImpl<NotasAluno> implements NotasAlunoDAO{

    @Override
    public List<NotasAluno> findNotasPorAluno(int raAluno) {

        String query = "SELECT n.ra, n.aluno, n.turma, n.disciplina, n.nota" +
                       " FROM NotasAluno n" +
                       " WHERE n.ra = " + raAluno;

        List lstResultado = getEntityManager().createQuery(query).getResultList();

        List<Object[]> objs = lstResultado;

		// Aqui vai ser manipulado o objs.
		//Cria suaLiistaModel
		List<NotasAluno> relList = new ArrayList<NotasAluno>();

		for (Object[] o : objs) {

		     Object[] aux = o;
		     NotasAluno notas = new NotasAluno();
		     //Objeto que sualistaModel recebe, vamos chamar de x

		      notas.setRa((Integer)aux[0]); // id
		      notas.setAluno((String)aux[1]);
		      notas.setTurma((String)aux[2]);
		      notas.setDisciplina((String)aux[3]);
		      notas.setNota((String)aux[4]);
		      relList.add(notas);
		}

		return relList;

    }

}
