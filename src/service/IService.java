package service;

import java.util.List;

import entities.Entity;

public interface IService <T extends Entity>{
    
    void save(T t);
    List<T> findAll();
    void delete(Integer id);
    T findById(Integer id);
}
