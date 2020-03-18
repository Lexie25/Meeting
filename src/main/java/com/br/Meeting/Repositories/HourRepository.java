package com.br.Meeting.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.br.Meeting.model.Hour;

public interface HourRepository extends CrudRepository<Hour, Long>{
	
	@Query("select h from Hour h where h.hour not in (select m.startTime from Meeting m where m.date = ?1) and h.hour not in (select m.endTime from Meeting m where m.date = ?1)")
	List<Hour> findAllAvailableTimes (String date);
	
	@Query("select h.hour from Hour h")
	List<Object> findAllHoursColumn();
	
	Hour findByHour(String hour);
}
