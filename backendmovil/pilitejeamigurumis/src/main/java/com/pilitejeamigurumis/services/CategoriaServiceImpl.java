package com.pilitejeamigurumis.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pilitejeamigurumis.entities.Categoria;
import com.pilitejeamigurumis.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findByAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    @Transactional
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Optional<Categoria> delete(Categoria categoria) {
        Optional<Categoria> c = categoriaRepository.findById(categoria.getId());
        c.ifPresent(categoriaRepository::delete);
        return c;
    }
}
