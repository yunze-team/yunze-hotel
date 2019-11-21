package com.dotw.core.repository;

import com.dotw.core.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lazyb
 * @create 2019/11/20
 * @desc
 **/
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByCode(String code);

    Country findByName(String name);

}
