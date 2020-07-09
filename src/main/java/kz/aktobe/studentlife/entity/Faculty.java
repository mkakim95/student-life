package kz.aktobe.studentlife.entity;

import kz.aktobe.studentlife.common.entity.MultiLangEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Факультет
 */
@Entity
@Table(name = "faculties")
public class Faculty extends MultiLangEntity {

}
