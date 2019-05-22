package dao;

import entity.UserEntity;
import entity.ZoneEntity;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.List;

@NoArgsConstructor(staticName = "create")
public class ZoneDao extends GenericDao<ZoneEntity, String> {
    @Override
    public void deleteById(String id) {

        em.getTransaction().begin();
        em.createQuery("delete from ZoneEntity where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public ZoneEntity findById(String id) {
        em.getTransaction().begin();
        val zoneEntity = em.createQuery("SELECT b from ZoneEntity b where b.id = :id", ZoneEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.getTransaction().commit();
        return zoneEntity;
    }

    @Override
    public List<ZoneEntity> getAll() {
        val query = em.createQuery("FROM ZoneEntity", ZoneEntity.class);
        return query.getResultList();
    }
}
