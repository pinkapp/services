package cc.ywxm.commonservice.dao.impl;

import cc.ywxm.commonservice.dao.ActivationCodeExchangeLogDao;
import cc.ywxm.commonservice.model.ActivationCode;
import cc.ywxm.commonservice.model.ActivationCodeExchangeLog;
import cc.ywxm.commonservice.model.ActivationCodeInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ActivationCodeExchangeLogDaoJpaImpl implements ActivationCodeExchangeLogDao {

    /**
     * 实体管理器
     */
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(ActivationCodeExchangeLog activationCodeExchangeLog) {
        entityManager.persist(activationCodeExchangeLog);
    }

    @Override
    public int count(int serverId, int eventId, int player) {
        Query query = entityManager.createNamedQuery("count");
        query.setParameter("serverId", serverId);
        query.setParameter("eventId", eventId);
        query.setParameter("player", player);
        Long amount = (Long) query.getSingleResult();
        return amount.intValue();
    }

    @Override
    public List<ActivationCodeExchangeLog> find(int serverId, int eventId, int player) {
        Query query = entityManager.createNamedQuery("find");
        query.setParameter("serverId", serverId);
        query.setParameter("eventId", eventId);
        query.setParameter("player", player);
        return query.getResultList();
    }

    @Override
    public ActivationCodeExchangeLog findByCode(String code) {
        Query query = entityManager.createNamedQuery("findByCode");
        query.setParameter("code", code);
        List<ActivationCodeExchangeLog> codes = query.getResultList();
        if (codes.size() > 0) {
            return codes.get(0);
        }
        return null;
    }

    @Override
    public List<ActivationCodeExchangeLog> findByEventId(int eventId) {
        return entityManager.createNamedQuery("findByEventId", ActivationCodeExchangeLog.class).setParameter("eventId", eventId).getResultList();
    }

}
