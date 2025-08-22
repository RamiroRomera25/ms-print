package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import rami.generic.entities.base.BaseEntity;
import rami.generic.repositories.GenericRepository;

import java.util.ArrayList;
import java.util.List;

public interface ServiceSoftDeleteList<E extends BaseEntity, I, M> extends ServiceGetById<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    @Transactional
    default List<M> delete(List<I> ids) {
        return changeActiveStatus(ids, false);
    }

    @Transactional
    default List<M> reactivate(List<I> ids) {
        return changeActiveStatus(ids, true);
    }

    private List<M> changeActiveStatus(List<I> ids, boolean isActive) {
        List<M> modelList = new ArrayList<>();
        for (I id : ids) {
            E entity = this.getById(id);
            entity.setIsActive(isActive);
            modelList.add(getMapper().map(entity, modelClass()));
        }
        return modelList;
    }
}
