package com.migi.migi_project.repository.user;

import com.migi.migi_project.entity.ServiceWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceWorkerRepository extends JpaRepository<ServiceWorker, Integer> {
}
