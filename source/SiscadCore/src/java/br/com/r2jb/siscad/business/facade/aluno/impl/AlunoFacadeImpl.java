/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.aluno.impl;


import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import br.com.r2jb.siscad.business.facade.aluno.AlunoFacade;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.responsavel.ResponsavelFacade;
import br.com.r2jb.siscad.dao.aluno.AlunoDAO;
import br.com.r2jb.siscad.util.facade.security.usuario.UsuarioFacade;
import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import java.util.Collections;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("AlunoFacade")
public class AlunoFacadeImpl extends BaseFacadeImpl<Aluno, AlunoDAO> implements AlunoFacade {

    @Autowired private AlunoDAO alunoDao;
    @Autowired private ResponsavelFacade responsavelFacade;
    @Autowired private UsuarioFacade usuarioFacade;

//    public byte[] getRelatorioListagemDeAlunos(Turma turma, Locale locale) throws JRException {
//
//        System.out.println("-------------------------------------------" + locale.getCountry());
//        System.out.println("-------------------------------------------" + locale.getLanguage());
//
//        FileOutputStream fos = null;
//        BufferedOutputStream bos = null;
//
//        try {
//            Map parameters = new HashMap();
//            parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, new I18NResourceBundle(locale));
//            parameters.put(JRParameter.REPORT_LOCALE, locale);
//            parameters.put("pTurma", new Integer(1));
//
//            JasperPrint print = JasperFillManager.fillReport("/home/bruno/Projetos/netbeans/siscad/branches/siscad-jsf2/source/SiscadCore/resources/resources/reports/ListagemAluno.jasper", parameters, new JRBeanCollectionDataSource(turma.getListaMatricula()));
//            fos = new FileOutputStream(new File("/home/bruno/Desktop/teste.pdf"));
//            bos = new BufferedOutputStream(fos);
//            bos.write(JasperExportManager.exportReportToPdf(print));
//            return JasperExportManager.exportReportToPdf(print);
//        } catch (Exception ex) {
//            System.err.println(ex.getCause());
//            Logger.getLogger(AlunoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            try {
//                if (null != fos)
//                    fos.close();
//            } catch (IOException ex) {
//                System.err.println(ex.getCause());
//                Logger.getLogger(AlunoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//
//    public static void main(String[] args) {
//        try {
//            AlunoFacadeImpl a = new AlunoFacadeImpl();
//
//            Turma t = new Turma();
//            t.setDescricao("Turma tal");
//            t.setPeriodo(Periodo._0_ANUAL);
//            t.setAno(Ano._2007);
//
//            Escola e = new Escola();
//            e.setNome("Escola nome");
//
//            t.setEscola(e);
//
//            Serie s = new Serie();
//            s.setDescricao("Desc serie");
//
//            Curso c = new Curso();
//            c.setDescricao("desc Curso");
//
//            s.setCurso(c);
//            t.setSerie(s);
//
//            List<Matricula> lst = new ArrayList<Matricula>();
//
//            Matricula m = new Matricula();
//
//            m.setTurma(t);
//
//            lst.add(m);
//
//            t.setListaMatricula(lst);
//
//
//            a.getRelatorioListagemDeAlunos(t, new Locale("pt_BR"));
//        } catch (JRException ex) {
//            Logger.getLogger(AlunoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public Aluno findByRA(String ra) {
        
        verifyArgument(ra, String.class);

        int iRa;

        try {

            iRa = Integer.parseInt(ra);
            return alunoDao.findByRA(iRa);

        } catch (NumberFormatException ex) {

            return null;

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public List<Aluno> findByTurma(Turma turma) {

        verifyArgument(turma, Turma.class);
        
        try {

            return alunoDao.findAlunosPorTurma(turma);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

        @Override
    public List<Aluno> findByNome(String nome) {
        
        verifyArgument(nome, String.class);
        
        try {

            return alunoDao.findByNome(nome);

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Aluno> findByNome(String nome, Boolean ativo) {

        verifyArgument(nome, String.class);

        try {

            return alunoDao.findByNome(nome, ativo);

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public boolean alunoExiste(String ra) {

        verifyArgument(ra, String.class);
        
        return (findByRA(ra) != null) ? true : false;

    }

    @Override
    public int gerarRA() {

        return alunoDao.getProximoRA().intValue();

    }

    @Override
    public boolean inserirAlunoEResponsavel(Aluno aluno, Responsavel responsavel, List<Endereco> enderecos, List<Telefone> telefones, Locale locale) throws PessoaJaExisteException {
        
        verifyArgument("aluno", aluno, Aluno.class);
        
        boolean bRespInserted = responsavelFacade.inserirResponsavel(responsavel, enderecos, telefones, locale);

        aluno.setResponsavel(responsavel);
        aluno.setAtivo(Boolean.TRUE);
        
        return bRespInserted && inserirAluno(aluno, locale);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean inserirAluno(Aluno aluno, Locale locale) {

        verifyArgument(aluno, Aluno.class);
        
        aluno.setRa(gerarRA());
        aluno.setAtivo(Boolean.TRUE);

        Responsavel responsavel = aluno.getResponsavel();

        if (null == responsavel) {

            throw new RuntimeException(ResourceBundleUtils.getMessage("error.msg.AFI.iA.responsavelNull"));
            
        }

        responsavel.addAluno(aluno);
        
        alunoDao.save(aluno);
        usuarioFacade.criaUsuarioParaAluno(aluno, locale);

        return true;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Aluno alterarAluno(Aluno aluno) {

        verifyArgument(aluno, Aluno.class);
        
        return alunoDao.merge(aluno);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerAluno(Aluno aluno) {

        verifyArgument(aluno, Aluno.class);

        aluno.setAtivo(Boolean.FALSE);
        
        alunoDao.merge(aluno);

    }

    public void setAlunoDAO(AlunoDAO alunoDAO) {
        this.alunoDao = alunoDAO;
    }

    public void setResponsavelFacade(ResponsavelFacade responsavelFacade) {
        this.responsavelFacade = responsavelFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

}
