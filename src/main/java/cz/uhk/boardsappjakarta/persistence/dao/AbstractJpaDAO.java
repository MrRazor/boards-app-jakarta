package cz.uhk.boardsappjakarta.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public abstract class AbstractJpaDAO< T, U > {

    private Class< T > clazz;

    @PersistenceContext(unitName = "boards-persistence-unit")
    EntityManager entityManager;

    public void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public T findOne( U id ){
        return entityManager.find( clazz, id );
    }

    public T getReference ( U id ){
        return entityManager.getReference( clazz, id );
    }

    public List< T > findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public void create( T entity ){
        entityManager.persist( entity );
    }

    public T update( T entity ){
        return entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }

    public void deleteById( U entityId ){
        T entity = getReference( entityId );
        delete( entity );
    }
}
