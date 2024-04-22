package com.iggyzxc.bankingapp.repository;

import com.iggyzxc.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
