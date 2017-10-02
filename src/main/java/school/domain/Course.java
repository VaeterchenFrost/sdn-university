/*
 * Copyright [2011-2016] "Neo Technology"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */
package school.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * The course object connects a teacher
 * with a subject and the pupils who are taught the subject by the teacher
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Class")
public class Course {

	private Long id;

	private String name;

	@Relationship(type = "SUBJECT_TAUGHT")
	private Subject subject;

	@Relationship(type = "TEACHES_CLASS", direction = Relationship.INCOMING)
	private Teacher teacher;

	@Relationship(type = "ENROLLED", direction = Relationship.INCOMING)
	private Set<Student> students = new HashSet<>();

	public Long getId() {
		return id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public String getName() {
		return name;
	}

	public Subject getSubject() {
		return subject;
	}

	public Set<Student> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + getId() +
				", name='" + name + '\'' +
				", teacher=" + teacher +
				", subject=" + subject +
				", students=" + students.size() +
				'}';
	}

	public void updateFrom(Course course) {
		this.name = course.name;
	}
}
