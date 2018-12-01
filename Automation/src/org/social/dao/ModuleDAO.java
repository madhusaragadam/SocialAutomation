package org.social.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modules_table")
public class ModuleDAO {

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	@Column(name="moduleName")
	String moduleName;
	String componentName;
	String jenkinsJobLink;
	String environment;
	String categoryOfTestCase;
	
	public ModuleDAO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getJenkinsJobLink() {
		return jenkinsJobLink;
	}
	public void setJenkinsJobLink(String jenkinsJobLink) {
		this.jenkinsJobLink = jenkinsJobLink;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getCategoryOfTestCase() {
		return categoryOfTestCase;
	}
	public void setCategoryOfTestCase(String categoryOfTestCase) {
		this.categoryOfTestCase = categoryOfTestCase;
	}
	
	@Override
	public String toString() {
		return moduleName+" "+componentName+" = "+jenkinsJobLink+" = "+environment+" = "+categoryOfTestCase;
	}
}
