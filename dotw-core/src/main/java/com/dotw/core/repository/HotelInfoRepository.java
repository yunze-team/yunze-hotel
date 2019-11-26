package com.dotw.core.repository;

import com.dotw.core.domain.HotelInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by toby on 2019/11/22.
 */
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {

    HotelInfo findByDotwHotelCode(String dotwHotelCode);

    Page<HotelInfo> findAll(Specification<HotelInfo> country, Pageable pageable);
}
