package com.xf.dao;

import com.xf.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
    public Account LoginSelect(String username);
    public void RegisterInsert(Account account);
}
