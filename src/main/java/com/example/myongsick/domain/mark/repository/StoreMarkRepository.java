package com.example.myongsick.domain.mark.repository;

import com.example.myongsick.domain.mark.entity.MarkId;
import com.example.myongsick.domain.mark.entity.StoreMark;
import com.example.myongsick.domain.scrap.entity.Scrap;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMarkRepository extends JpaRepository<StoreMark, MarkId> {
  Page<StoreMark> findAllByUserId(Long userId, Pageable pageable);
}
