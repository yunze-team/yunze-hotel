package com.dotw.core.repository;

import com.dotw.core.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by toby on 2019/11/21.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCode(String code);

}
