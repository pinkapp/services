package cc.ywxm.commonservice.dao.impl;

import cc.ywxm.commonservice.dao.ActivationCodeDao;
import cc.ywxm.commonservice.model.ActivationCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ActivationCodeDaoJpaImpl implements ActivationCodeDao {

    /**
     * 实体管理器
     */
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(ActivationCode code) {
        entityManager.persist(code);
    }

    @Transactional
    public void batch_save(List<ActivationCode> codes) {
        //System.out.print(codes);
        for (ActivationCode code : codes) {
            entityManager.persist(code);
            //entityManager.flush();
        }
    }

    @Override
    public ActivationCode get(String code) {
        Query query = entityManager.createNamedQuery("findActivationCodeByCode");
        query.setParameter("code", "%"+code+"%");
        List<ActivationCode> codes = query.getResultList();
        if (codes.size() > 0) {
            return codes.get(0);
        }
        return null;
    }

    @Override
    public ActivationCode get(String code, int serverid) {
        Query query = entityManager.createNamedQuery("findActivationCodeByCode2");
        query.setParameter("code", "%"+code+"%");
        query.setParameter("servers", "%"+serverid+"%");
        List<ActivationCode> codes = query.getResultList();
        if (codes.size() > 0) {
            return codes.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void update(ActivationCode code) {
        entityManager.merge(code);
        //entityManager.flush();
    }

}
