package com.elearningplatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elearningplatform.entity.CourseSection;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {

	List<CourseSection> findByCourseIdOrderByDisplayOrder(Long courseId);

	List<CourseSection> findBySectionIdOrderByDisplayOrder(Long sectionId);

	Optional<CourseSection> findByCourseIdAndSectionId(Long courseId, Long sectionId);

	boolean existsByCourseIdAndSectionId(Long courseId, Long sectionId);

	@Query("SELECT cs FROM CourseSection cs WHERE cs.course.id = :courseId ORDER BY cs.displayOrder")
	List<CourseSection> findByCourseIdOrdered(@Param("courseId") Long courseId);

	@Query("SELECT MAX(cs.displayOrder) FROM CourseSection cs WHERE cs.course.id = :courseId")
	Integer findMaxOrderByCourseId(@Param("courseId") Long courseId);

	@Query("SELECT COUNT(cs) FROM CourseSection cs WHERE cs.course.id = :courseId")
	Integer countByCourseId(@Param("courseId") Long courseId);

	@Modifying
	@Query("UPDATE CourseSection cs SET cs.displayOrder = cs.displayOrder - 1 "
			+ "WHERE cs.course.id = :courseId AND cs.displayOrder BETWEEN :start AND :end "
			+ " AND cs.id != :excludeId")
	void decrementOrdersBetween(@Param("courseId") Long courseId, @Param("start") Integer start,
			@Param("end") Integer end, @Param("excludeId") Long excludeId);

	@Modifying
	@Query("UPDATE CourseSection cs SET cs.displayOrder = cs.displayOrder + 1 "
			+ "WHERE cs.course.id = :courseId AND cs.displayOrder BETWEEN :start AND :end " + "AND cs.id != :excludeId")
	void incrementOrdersBetween(@Param("courseId") Long courseId, @Param("start") Integer start,
			@Param("end") Integer end, @Param("excludeId") Long excludeId);

	@Modifying
	@Query("UPDATE CourseSection cs SET cs.displayOrder = :newOrder WHERE cs.id = :id")
	void updateOrder(@Param("id") Long id, @Param("newOrder") Integer newOrder);

	@Modifying
	@Query("DELETE FROM CourseSection cs WHERE cs.course.id = :courseId AND cs.section.id = :sectionId")
	void deleteByCourseIdAndSectionId(@Param("courseId") Long courseId, @Param("sectionId") Long sectionId);

	@Modifying
	@Query("UPDATE CourseSection cs SET cs.displayOrder = cs.displayOrder - 1 "
			+ "WHERE cs.course.id = :courseId AND cs.displayOrder > :removedOrder")
	void decrementOrdersAfter(@Param("courseId") Long courseId, @Param("removedOrder") Integer removedOrder);

	@Query("SELECT cs FROM CourseSection cs WHERE cs.course.id = :courseId AND cs.displayOrder = :order")
	Optional<CourseSection> findByCourseIdAndOrder(@Param("courseId") Long courseId, @Param("order") Integer order);

	@Query("SELECT cs FROM CourseSection cs WHERE cs.course.id = :courseId "
			+ "AND cs.displayOrder BETWEEN :fromOrder AND :toOrder " + "ORDER BY cs.displayOrder")
	List<CourseSection> findByCourseIdAndOrderRange(@Param("courseId") Long courseId,
			@Param("fromOrder") Integer fromOrder, @Param("toOrder") Integer toOrder);

	@Modifying
	@Query("UPDATE CourseSection cs SET cs.displayOrder = cs.displayOrder + :increment "
			+ "WHERE cs.course.id = :courseId AND cs.displayOrder >= :startOrder")
	void incrementOrdersFrom(@Param("courseId") Long courseId, @Param("startOrder") Integer startOrder,
			@Param("increment") Integer increment);

}
