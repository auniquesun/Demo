package com.ruc.ir.bean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

    Long countByImgid(int imgid);

    @Query(value="select imgid, count(likeid) from likes where time > :startTime and time < :endTime group by imgid order by count(likeid) desc limit 50", nativeQuery = true)
    List<Object[]> findPopularCounts(@Param("startTime") String startTime, @Param("endTime") String endTime);


}
