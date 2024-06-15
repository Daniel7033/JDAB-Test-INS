package com.sena.back_1076502369.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;

import com.sena.back_1076502369.Entity.ABaseEntity;
import com.sena.back_1076502369.IRepository.IBaseRepository;
import com.sena.back_1076502369.IService.IBaseService;

public abstract class ABaseService<T extends ABaseEntity> implements IBaseService<T> {

    protected abstract  IBaseRepository<T, Long> getRepository();

    @Override
    public List<T> all() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public List<T> findByStateTrue() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public Optional<T> findById(Long id) throws Exception {
        Optional<T> op = getRepository().findById(id);

        if (op.isEmpty()) {
            throw new Exception("Registro no encontrado");
        }   
        return op;
    }

    @Override
    public T save(T entity) throws Exception {
        return getRepository().save(entity);
    }

    @Override
    public void update(Long id, T entity) throws Exception {
        
        Optional<T> op = getRepository().findById(id);
        if (op.isEmpty()) {
            throw new Exception("Registro no encontrado");
        } 
        T entityUpdate = op.get();
        String[] ignoreProperties = { "id"};
        BeanUtils.copyProperties(entity, entityUpdate, ignoreProperties);
        getRepository().save(entityUpdate);
    }

    @Override
    public void delete(Long id) throws Exception {

        getRepository().deleteById(id);;
    }
}
