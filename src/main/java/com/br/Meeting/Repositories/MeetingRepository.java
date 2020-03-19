package com.br.Meeting.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.br.Meeting.model.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long>{
	
}
