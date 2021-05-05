package it.prova.gestionepizzeria.repository.utente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionepizzeria.model.Utente;


public class CustomUtenteRepositoryImpl implements CustomUtenteRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Utente> findByExample(Utente example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select u from Utente u left join fetch u.ruoli r where u.id = u.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" u.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" u.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getUsername())) {
			whereClauses.add(" u.username  like :username ");
			paramaterMap.put("username", "%" + example.getUsername() + "%");
		}
		if (StringUtils.isNotEmpty(example.getPassword())) {
			whereClauses.add(" u.password like :password ");
			paramaterMap.put("password", "%" + example.getPassword() + "%");
		}

		if (example.getDateCreated() != null) {
			whereClauses.add("u.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		if (example.getStato() != null) {
			whereClauses.add("u.stato = :stato ");
			paramaterMap.put("stato", example.getStato() );
		}

		// se cerco per ruolo devo cercare in un set di ruoli

		if (example.getRuoli() != null && !example.getRuoli().isEmpty()) {
			whereClauses.add(" r.id in :idList ");
			paramaterMap.put("idList", example.getRuoli().stream().map(ruolo -> ruolo.getId()).collect(Collectors.toList()));
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Utente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Utente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
	
	@Override
	public Utente findOneEagerRuoli(Long id) {
		TypedQuery<Utente> query = entityManager.createQuery("from Utente u left join fetch u.ruoli a where u.id = ?1",
				Utente.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
	
	@Override
	public Long countByUtenteAdminAttivo() {
		TypedQuery<Long> query = entityManager.createQuery("select count (u.id) FROM Utente u join u.ruoli r where r.id = '1' and u.stato = 'ATTIVO'",
				Long.class);
		return query.getSingleResult();
	}

}
