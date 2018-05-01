package br.com.traderhelper.importador.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Michael on 24/03/2017.
 */
public class BulkSaver {

	@PersistenceContext
	private EntityManager em;

	public void batchSave(List<?> objects) {
		// Batch inserting test entities
		// This code is inside transaction already, so I'm not creating a new one
		// em.flush();
		for (int i = 0; i < objects.size(); ++i) {
			em.persist(objects.get(i));
			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}
		em.flush();
		em.clear();
	}

	public void batchMerge(List<?> objects) {
		// Batch inserting test entities
		// This code is inside transaction already, so I'm not creating a new one
		// em.flush();
		for (int i = 0; i < objects.size(); ++i) {
			em.merge(objects.get(i));
			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}
		em.flush();
		em.clear();
	}
}
