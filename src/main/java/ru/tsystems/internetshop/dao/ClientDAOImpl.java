package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.ClientDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
////public class ClientDAOImpl extends AbstractDAO<ClientDto, Integer> implements ClientDAO {
public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveClient(ClientDto client) {
        sessionFactory.getCurrentSession().save(client);
    }


////    @Override
////    public List<User> list() {
////        @SuppressWarnings("unchecked")
////        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
////        return query.getResultList();
////    }
////
////    @Override
////    public ClientDto findById(int id) {
////        return getByKey(id);
////    }
////
////    @Override
////    public ClientDto findByEmail(String email) {
////        Criteria criteria = createEntityCriteria();
////        criteria.add(Restrictions.eq("email", email));
////        return (ClientDto) criteria.uniqueResult();
////    }
////
//    @Override
//    public void saveClient(ClientDto client) {
//        persist(client);
//    }
////
////    @Override
////    public void updateClient(ClientDto client) {
////
////    }
////
////    @Override
////    public void deleteClientById(int id) {
//////        Query query = getSession().createSQLQuery("delete from Client where id = :id");
//////        query.setString("id", id);
//////        query.executeUpdate();
////    }
////
////    @Override
////    public List<ClientDto> findAllClients() {
////        return null;
////    }
}
