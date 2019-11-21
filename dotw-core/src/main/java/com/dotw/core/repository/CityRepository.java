package com.dotw.core.repository;

import com.dotw.core.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by toby on 2019/11/21.
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByCode(String code);

}
