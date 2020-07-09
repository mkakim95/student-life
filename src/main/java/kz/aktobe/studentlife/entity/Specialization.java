package kz.aktobe.studentlife.entity;

import kz.aktobe.studentlife.common.entity.MultiLangEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Специализация
 */
@Entity
@Table(name = "specializations")
public class Specialization extends MultiLangEntity {
}
