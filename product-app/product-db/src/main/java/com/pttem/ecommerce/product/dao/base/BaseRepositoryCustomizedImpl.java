package com.pttem.ecommerce.product.dao.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.*;

@SuppressWarnings({
    "unchecked",
    "squid:S3011"
})
@NoRepositoryBean
public class BaseRepositoryCustomizedImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final Logger log = LoggerFactory.getLogger(BaseRepositoryCustomizedImpl.class);

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    public BaseRepositoryCustomizedImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.entityInformation = entityInformation;
        this.em = em;
    }

    @Override
    public boolean updateField(T entity, HashMap<String, Object> updateList) {
        Assert.notNull(entity, "The given entity must not be null!");

        Assert.notNull(updateList, "The update list must not be null!");

        Assert.isTrue(!updateList.isEmpty(), "The given update list is null");

        CriteriaBuilder cb = em.getCriteriaBuilder();

        Class<T> domainClass = (Class<T>) entity.getClass();

        CriteriaUpdate<T> update = cb.createCriteriaUpdate(domainClass);

        Root<T> root = update.from(domainClass);

        updateList.forEach(update::set);

        final List<Predicate> predicates = new ArrayList<>();

        if (entityInformation.hasCompositeId()) {
            for (String s : entityInformation.getIdAttributeNames()) {
                Object id = entityInformation.getId(entity);
                if (Objects.nonNull(id)) {
                    predicates.add(cb.equal(root.<ID> get(s), entityInformation.getCompositeIdAttributeValue(id, s)));
                }
            }
            update.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        } else {
            SingularAttribute<? super T, ?> idAttribute = entityInformation.getIdAttribute();
            if (Objects.nonNull(idAttribute)) {
                update.where(cb.equal(root.<ID> get(idAttribute.getName()), entityInformation.getId(entity)));
            }
        }
        return em.createQuery(update).executeUpdate() >= 0;
    }

    @Transactional
    @Override
    public List<T> findAll(Boolean baseStatus) {
        if (baseStatus) {
            return this.findAll();
        } else {
            return super.findAll();
        }
    }

    @Transactional
    @Override
    public List<T> findAll(Boolean baseStatus, Sort sort) {
        if (baseStatus) {
            return this.findAll(sort);
        } else {
            return super.findAll(sort);
        }
    }

    @Override
    public List<T> findAll(Boolean baseStatus, String fieldName, Object value) {
        Specification<T> specification = Specification.where((new FieldByValue<T, String, Object>(entityInformation, fieldName, value)));
        return this.findAll(specification);
    }

    @Transactional
    @Override
    public Page<T> findAll(Boolean baseStatus, Pageable pageable) {
        if (Boolean.TRUE.equals(baseStatus)) {
            return this.findAll(pageable);
        } else {
            return super.findAll(pageable);
        }
    }



    @Override
    public List<T> findAll() {
        return super.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        if (spec == null) {
            return super.findAll(pageable);
        }
        return super.findAll(spec, pageable);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        if (!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }
        if (entityInformation.hasCompositeId()) {
            List<T> result = new ArrayList<>();
            for (ID id : ids) {
                result.add(getOne(id));
            }
            return result;
        }
        ByIdsSpecification<T> specification = new ByIdsSpecification<>(entityInformation);
        TypedQuery<T> query = getQuery(Specification.where(specification), (Sort) null);
        return query.setParameter(specification.parameter, ids).getResultList();
    }

    @Override
    public T getOne(ID id) {
        return super.findOne(Specification.where(new ByIdSpecification<T, ID>(entityInformation, id))).orElse(null);
    }

    @Override
    public T getOne(Boolean baseStatus, ID id) {
        if (Boolean.TRUE.equals(baseStatus)) {
            return this.getOne(id);
        } else {
            Optional<T> t = this.findById(id);
            return t.orElse(null);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        return super.findOne(Specification.where(new ByIdSpecification<T, ID>(entityInformation, id)));
    }

    @Override
    public Optional<T> findOne(Boolean baseStatus, Specification<T> spec) {
        if (Boolean.TRUE.equals(baseStatus)) {
            return this.findOne(spec);
        } else {
            return super.findOne(spec);
        }
    }

    @Override
    @Transactional
    public <S extends T> S saveAndFlush(S entity) {
        S result = this.save(entity);
        flush();
        return result;
    }

    @Override
    @Transactional
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();

        if (entities == null) {
            return result;
        }

        for (S entity : entities) {
            result.add(this.save(entity));
        }

        return result;

    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
//        Set<ConstraintViolation<S>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(entity);
//
//        if (!constraintViolations.isEmpty())
//            throw new ConstraintViolationException(constraintViolations.toString(), constraintViolations);

        Class<?> entityClass = entity.getClass();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<?> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (entityInformation.hasCompositeId()) {
            for (String s : entityInformation.getIdAttributeNames())
                predicates.add(criteriaBuilder.equal(root.<ID> get(s), entityInformation.getCompositeIdAttributeValue(entityInformation.getId(entity), s)));


            criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Object> typedQuery = em.createQuery(criteriaQuery);
            List<Object> resultSet = typedQuery.getResultList();

            if (!resultSet.isEmpty()) {
                S result = (S) resultSet.get(0);
                BeanUtils.copyProperties(entity, result, getNullPropertyNames(entity));
                return super.save(result);
            }
        }
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        T t = this.findById(id).orElseThrow(() -> {
            return new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!", this.entityInformation.getJavaType(), id), 1);
        });
        super.delete(t);
    }

    @Override
    @Transactional
    public void hardDeleteById(ID id) {
        T t = super.getReferenceById(id);
        super.delete(t);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        super.delete(entity);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<? extends T> entities) {
        super.deleteAll(entities);
    }

    @Override
    @Transactional
    public void deleteAll() {
        Iterator var1 = super.findAll().iterator();

        while (var1.hasNext()) {
            T element = (T) var1.next();
            super.delete(element);
        }
    }
    @Override
    public boolean existsById(ID id) {
        Assert.notNull(id, "id must not be null!");
        return getOne(id) != null;
    }

    @Override
    public boolean existsById(Boolean baseStatus, ID id) {
        if (baseStatus) {
            return this.existsById(id);
        } else {
            return super.existsById(id);
        }
    }

    @Override
    public List<T> findAllById(Boolean baseStatus, Iterable<ID> ids) {
        if (baseStatus) {
            return this.findAllById(ids);
        } else {
            return super.findAllById(ids);
        }
    }
    private static final class ByIdSpecification<T, ID extends Serializable> implements Specification<T> {

        private final JpaEntityInformation<T, ?> entityInformation;
        private final ID id;

        public ByIdSpecification(JpaEntityInformation<T, ?> entityInformation, ID id) {
            this.entityInformation = entityInformation;
            this.id = id;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            final List<Predicate> predicates = new ArrayList<>();
            if (entityInformation.hasCompositeId()) {
                for (String s : entityInformation.getIdAttributeNames())
                    predicates.add(cb.equal(root.<ID> get(s), entityInformation.getCompositeIdAttributeValue(id, s)));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            SingularAttribute<? super T, ?> idAttribute = entityInformation.getIdAttribute();
            if (Objects.nonNull(idAttribute)) {
                return cb.equal(root.<ID> get(idAttribute.getName()), id);
            }
            return null;
        }
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> propertyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds)
            if (src.getPropertyValue(pd.getName()) == null)
                propertyNames.add(pd.getName());

        return propertyNames.toArray(new String[propertyNames.size()]);
    }

    private static final class FindAllInSpecification<T, ID extends Serializable> implements Specification<T> {

        private final JpaEntityInformation<T, ?> entityInformation;
        private final List<ID> inList;

        public FindAllInSpecification(JpaEntityInformation<T, ?> entityInformation, List<ID> inList) {
            this.entityInformation = entityInformation;
            this.inList = inList;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            final List<Predicate> predicates = new ArrayList<>();
            SingularAttribute<? super T, ?> idAttribute = entityInformation.getIdAttribute();
            if (Objects.nonNull(idAttribute)) {
                predicates.add(root.get(idAttribute.getName()).in(inList));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));

        }
    }

    private static final class ByIdsSpecification<T> implements Specification<T> {

        private final JpaEntityInformation<T, ?> entityInformation;

        ParameterExpression<Iterable> parameter;

        public ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
            this.entityInformation = entityInformation;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Path<?> path = root.get(entityInformation.getIdAttribute());
            parameter = cb.parameter(Iterable.class);
            return path.in(parameter);
        }
    }

    private static final class FieldByValue<T, String, Object> implements Specification<T> {

        private final JpaEntityInformation<T, ?> entityInformation;
        private final java.lang.String fieldName;
        private final Object value;

        public FieldByValue(JpaEntityInformation<T, ?> entityInformation, java.lang.String fieldName, Object value) {
            this.entityInformation = entityInformation;
            this.fieldName = fieldName;
            this.value = value;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.equal(root.get(fieldName), value);
        }
    }

}
