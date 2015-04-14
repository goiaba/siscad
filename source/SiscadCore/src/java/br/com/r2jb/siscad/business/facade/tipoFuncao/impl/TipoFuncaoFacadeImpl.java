/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.tipoFuncao.impl;

import br.com.r2jb.siscad.business.entity.TipoFuncao;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.tipoFuncao.TipoFuncaoFacade;
import br.com.r2jb.siscad.dao.tipoFuncao.TipoFuncaoDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("TipoFuncaoFacade")
public class TipoFuncaoFacadeImpl extends BaseFacadeImpl<TipoFuncao, TipoFuncaoDAO> implements TipoFuncaoFacade {

}
