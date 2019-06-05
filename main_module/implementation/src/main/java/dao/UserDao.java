package dao;

import entity.UserEntity;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.List;

@NoArgsConstructor(staticName = "create")
public class UserDao extends GenericDao<UserEntity, String> {
    @Override
    public void deleteById(String id) {

        em.getTransaction().begin();
        em.createQuery("delete from UserEntity where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public UserEntity findById(String id) {
        em.getTransaction().begin();
        val userEntity = em.createQuery("SELECT b from UserEntity b where b.id = :id", UserEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.getTransaction().commit();
        return userEntity;
    }

    @Override
    public List<UserEntity> getAll() {
        em.getTransaction().begin();
        val query = em.createQuery("FROM UserEntity", UserEntity.class).getResultList();
        em.getTransaction().commit();
        return query;
    }

    public List<UserEntity> findByZoneId(String zoneId) {
        em.getTransaction().begin();
        val list = em.createQuery("SELECT b from UserEntity b inner join b.zones as z where z.id= :zoneId", UserEntity.class)
                .setParameter("zoneId", zoneId)
                .getResultList();
        em.getTransaction().commit();
        return list;
    }
}

