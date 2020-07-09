package kz.aktobe.studentlife.entity;

import kz.aktobe.studentlife.common.entity.MultiLangEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Кафедра
 */
@Entity
@Table(name = "departments")
public class Department extends MultiLangEntity {
}
