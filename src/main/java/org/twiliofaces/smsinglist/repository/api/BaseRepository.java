package org.twiliofaces.smsinglist.repository.api;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.giavacms.common.repository.AbstractRepository;

/**
 * BaseRepository
 * 
 * Unified {@link EntityManager} injection point.
 * 
 * @param <T>
 */
public abstract class BaseRepository<T> extends AbstractRepository<T>
{

   private static final long serialVersionUID = 1L;

   // --- JPA ---------------------------------

   @PersistenceContext(unitName = "pu")
   protected EntityManager em;

   @Override
   protected EntityManager getEm()
   {
      return em;
   }

   @Override
   public void setEm(EntityManager em)
   {
      this.em = em;
   }

}
