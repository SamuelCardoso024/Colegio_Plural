package com.senai.colegioplural.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.senai.colegioplural.models.Responsavel;
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer>
{
@Query(value="select * from responsavel where nome like '%'||:termo||'%';",
nativeQuery=true)
public List<Responsavel> listarResponsaveis(String termo);
}