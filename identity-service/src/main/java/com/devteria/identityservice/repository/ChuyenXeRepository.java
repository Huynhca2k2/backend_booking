package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.ChuyenXe;
import com.devteria.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChuyenXeRepository extends JpaRepository<ChuyenXe,Integer> {
//    boolean existsByChuyenXeName(String chuyenxename);

//    Optional<ChuyenXe> findByChuyenXeName(String chuyenxename);

    boolean existsById(Integer chuyenXeId);
    Optional<ChuyenXe> findById(Integer chuyenxeId);

}
