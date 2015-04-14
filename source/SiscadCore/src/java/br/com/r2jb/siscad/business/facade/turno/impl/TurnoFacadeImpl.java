/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.turno.impl;


import br.com.r2jb.siscad.business.entity.Turno;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.turno.TurnoFacade;
import br.com.r2jb.siscad.dao.turno.TurnoDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("TurnoFacade")
public class TurnoFacadeImpl extends BaseFacadeImpl<Turno, TurnoDAO> implements TurnoFacade {

}
