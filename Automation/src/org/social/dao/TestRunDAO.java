package org.social.dao;

import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "run_data")
public class TestRunDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Transient
	private int moduleId;
	
	

	@NotNull
	@OneToOne
	private ModuleDAO module;
	
	private long runId;
	private long pass;
	private long fail;
	private long todo;
	@Basic
	private java.sql.Date runDate;
	private long manualPass;
	private long envIssues;
	private long productIssues;

	

	/**
	 * @return the moduleId
	 */
	public int getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public TestRunDAO() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the module
	 */
	public ModuleDAO getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(ModuleDAO module) {
		this.module = module;
	}

	/**
	 * @return the runId
	 */
	public long getRunId() {
		return runId;
	}

	/**
	 * @param runId the runId to set
	 */
	public void setRunId(long runId) {
		this.runId = runId;
	}

	/**
	 * @return the pass
	 */
	public long getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(long pass) {
		this.pass = pass;
	}

	/**
	 * @return the fail
	 */
	public long getFail() {
		return fail;
	}

	/**
	 * @param fail the fail to set
	 */
	public void setFail(long fail) {
		this.fail = fail;
	}

	/**
	 * @return the todo
	 */
	public long getTodo() {
		return todo;
	}

	/**
	 * @param todo the todo to set
	 */
	public void setTodo(long todo) {
		this.todo = todo;
	}

	/**
	 * @return the runDate
	 */
	public java.sql.Date getRunDate() {
		return runDate;
	}

	/**
	 * @param runDate the runDate to set
	 */
	public void setRunDate(java.sql.Date runDate) {
		this.runDate = runDate;
	}

	/**
	 * @return the manualPass
	 */
	public long getManualPass() {
		return manualPass;
	}

	/**
	 * @param manualPass the manualPass to set
	 */
	public void setManualPass(long manualPass) {
		this.manualPass = manualPass;
	}

	/**
	 * @return the envIssues
	 */
	public long getEnvIssues() {
		return envIssues;
	}

	/**
	 * @param envIssues the envIssues to set
	 */
	public void setEnvIssues(long envIssues) {
		this.envIssues = envIssues;
	}

	/**
	 * @return the productIssues
	 */
	public long getProductIssues() {
		return productIssues;
	}

	/**
	 * @param productIssues the productIssues to set
	 */
	public void setProductIssues(long productIssues) {
		this.productIssues = productIssues;
	}

	@Override
	public String toString() {
		return "toString";
	}
}
