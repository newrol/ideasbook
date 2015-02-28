/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.persistance.daointerfaces;

import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IDAO<T,K> {
    public T create(T t);
    public T read(K k);
    public void update(T t);
    public void delete(T t);
    public List<T> findAll();
}
