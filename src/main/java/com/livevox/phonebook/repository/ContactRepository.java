package com.livevox.phonebook.repository;

import com.livevox.phonebook.repository.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String>,
    QuerydslPredicateExecutor<ContactEntity> {
}
