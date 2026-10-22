package com.utp.qaliruta.repository;

import com.utp.qaliruta.model.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategoriaId(Long categoriaId);
}
